/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var tipoPerColegPago = 'C';
var colegSeleccionado;
var listaTipoPagos;
$(document).ready(function(){
    $(".nombre").hide();
    $(".estado").hide();
    $(".pagos").hide();
    $(".periodosDeuda").hide();
    $("#txtPeriodo").datepicker();
    $("#btnComprobar").on("click", function(){
        comprobarIdentificacion();
    });
    
    $("#btnLimpiar").on("click", limpiarInterfaz);
    
    $("#btnPagar").on("click", grabarDeuda);
    
});

var comprobarIdentificacion = function(){
    var expRegDni = /[0-9]{8}/;
    var expRegNumColegiatura = /^[0-9]{1,10}$/; //^[0-9]*$
    var tipoDocumento = 2;
    var tipoOperacion = tipoPerColegPago;
    var colegiado = new Colegiado();
    var doc = $("#txtNumColegiaturaDNI").val();
    
    if (doc === '') {
        mostrarMensaje('danger', '[Error: El Numero Colegiatura o DNI no puede ser vacio]');
        return;
    }

    if (doc.length < 1 || doc.length > 10){
        mostrarMensaje('danger', '[Error: El Numero Colegiatura acepta maximo 10 numeros y DNI debe tener 8 numeros]');
        return;
    }

    if(!expRegNumColegiatura.test(doc)){
        mostrarMensaje('danger', '[Error: El Numero Colegiatura o DNI solo acepta numeros]');
        return;
    }
    colegiado.numColegiatura = doc;

    var listaColegiados = new ListColegiado();
    var listaColegiadoRep = listaColegiados.buscarColegiadosPagos(tipoOperacion, tipoDocumento, colegiado);
    if(listaColegiadoRep.indError === 0){
        if(listaColegiadoRep.listaColegiados.length > 1){
            mostrarMensaje('danger', 'busqueda trae dos valores. Comuniquese con el administrador');
            return;
        }
        
        if(listaColegiadoRep.listaColegiados.length === 0){
            mostrarMensaje('danger', 'Comprobación no trae resultado');
            return;
        }
        colegSeleccionado = listaColegiadoRep.listaColegiados[0];
        
        var tipoPago = new TipoPago();
        tipoPago.conceptoPara = tipoOperacion;
        tipoPago.estadosColegiados = ','+colegSeleccionado.estado+',';
        var tipoOperacionTipoPago = 6;
        var listTipoPagoRep = tipoPago.listarTipoPago(tipoOperacionTipoPago);
        if(listTipoPagoRep.indError === 0){
            listaTipoPagos = listTipoPagoRep.listTipoPago;
            var $option;
            $("#cboPagos").html("<option value=''>--SELECCIONE--</option>");
            for(var i = 0; i < listaTipoPagos.length; i++){
                $option = $("<option/>",{
                    "value" : i,
                    "text" : listaTipoPagos[i].concepto
                });
                $("#cboPagos").append($option);
                listaTipoPagos[i].annadido = 0;
            }
            
            $("#lblNombresApellidos").text(colegSeleccionado.nombres);

            if(colegSeleccionado.estado === 1){
                $("#lblEstado").text("HABILITADO");
            } else if(colegSeleccionado.estado === 2){
                $("#lblEstado").text("INHABILITADO");
            } else if(colegSeleccionado.estado === 3){
                $("#lblEstado").text("EXONERADO");
            } else if(colegSeleccionado.estado === 4){
                $("#lblEstado").text("EGRESADO");
            }

            if(tipoOperacion === 'C'){
                $(".nombre").show();
                $(".estado").show();
            } 
            
            $("#btnComprobar").prop("disabled", true);
            $("#btnComprobar").hide();
            $("#txtNumColegiaturaDNI").prop("disabled", true);
            $(".pagos").show();
            $(".periodosDeuda").show();
        } else {
            mostrarMensaje('danger',listTipoPagoRep.msjError);
        }    
    } else {
        mostrarMensaje('danger',listaColegiadoRep.msjError);
    }
    
};

var limpiarInterfaz = function(){
    tipoPerColegPago = 'C';
    $("#comprobarTipo").show();
    $(".comprobarIdentificacion").show();
    $("#btnComprobar").prop("disabled", false);
    $("#btnComprobar").show();
    $(".nombre").hide();
    $(".estado").hide();
    $("#txtNumColegiaturaDNI").val("");
    $("#txtNumColegiaturaDNI").prop("disabled", false);

    colegSeleccionado = undefined;
    listaTipoPagos = undefined;
    $(".pagos").hide();
    $("#cboPagos").html("");
    $(".periodosDeuda").hide();
};

var grabarDeuda = function(){
    var expReg = /^[0-9]{6}$/;
    if(colegSeleccionado === undefined){
        mostrarMensaje('danger', 'Debe realizar comprobación');
        return;
    }

    var colegiado = new Colegiado();
    colegiado.numColegiatura = colegSeleccionado.numColegiatura;
    
    var indexTipoPago = $("#cboPagos").val();
    if(indexTipoPago === ''){
        mostrarMensaje('danger', 'Debe seleccionar tipo de pago');
        return;
    }
    
    var tipoPago = new TipoPago();
    tipoPago.id = listaTipoPagos[parseInt(indexTipoPago)].id;
    
    var periodo = $("#txtPeriodo").val();
    if(periodo === ''){
        mostrarMensaje('danger', 'Debe seleccionar un periodo');
        return;
    }
    
    if(!expReg.test(periodo)){
        mostrarMensaje('danger', 'valores incorrecto en el perido');
        return;
    }
    
    var deuda = new Deuda();
    deuda.colegiad = colegiado;
    deuda.tipoPago = tipoPago;
    deuda.periodo = periodo;

    var tipoOperacion = 1;
    var deudaRep = deuda.mantDeuda(tipoOperacion);
    if(deudaRep.indError === 0){
        mostrarMensaje('success', deudaRep.msjError);
        limpiarInterfaz();
    } else {
        mostrarMensaje('danger', deudaRep.msjError);
    }
};

var mostrarMensaje = function(tipo,mensaje){
    var htmlAlert;
    var $strong;
    htmlAlert = '<div class="alert alert-'+tipo+'">';
    htmlAlert += '<a href="#" class="close" data-dismiss="alert"'
            + 'aria-label="close">&times;</a></div>';
    $("#panelError").html(htmlAlert);
    $strong = $("<strong/>",{
        text : mensaje
    });
    $("#panelError div").append($strong);
    $("#panelError").show();
};