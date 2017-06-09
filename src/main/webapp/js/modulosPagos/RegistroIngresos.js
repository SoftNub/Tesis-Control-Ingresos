/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var tipoPerColegPago;
var colegSeleccionado;
var listaTipoPagos;
var detalleDeudas;
var importeTotal = 0;
$(document).ready(function(){
    $(".nombre").hide();
    $(".estado").hide();
    $(".pagos").hide();
    $(".periodosDeuda2").hide();
    $("#chkConceptoCol").on("change", function(){
        if($("#chkConceptoCol").is(":checked")){
            tipoPerColegPago = 'C';
            $("#comprobarTipo").show();
            $(".comprobarIdentificacion").show();
            $(".nombre").hide();
            $(".estado").hide();
            $("#lblDocComprobacion").text("Num. Colegiatura o DNI:");
            $("#txtNumColegiaturaDNI").attr("placeholder","num Coleg. o DNI");
            $("#txtNumColegiaturaDNI").attr("maxlength","10");
            $("#txtNumColegiaturaDNI").val("");
        }
    });
    
    $("#chkConceptoNoCol").on("change", function(){
        if($("#chkConceptoNoCol").is(":checked")){
            tipoPerColegPago = 'N';
            $("#comprobarTipo").show();
            $(".comprobarIdentificacion").show();
            $(".nombre").hide();
            $(".estado").hide();  
            $("#lblDocComprobacion").text("DNI:");
            $("#txtNumColegiaturaDNI").attr("placeholder","DNI");
            $("#txtNumColegiaturaDNI").attr("maxlength","8");
            $("#txtNumColegiaturaDNI").val("");
        }
    });
    
    $("#cboPagos").on("change", listarDeudaCuota);
    
    $("#btnComprobar").on("click", function(){
        comprobarIdentificacion();
    });
    
    $("#btnAnnadir").on("click", annadirItemPago);
    
    $("#btnLimpiar").on("click", limpiarInterfaz);
    
    $("#btnPagar").on("click", grabarPago);
    
});

var comprobarIdentificacion = function(){
    var tipoOperacion;
    var expRegDni = /[0-9]{8}/;
    var expRegNumColegiatura = /^[0-9]{1,10}$/; //^[0-9]*$
    var tipoDocumento = 2;
    if($("#chkConceptoCol").is(":checked")){
        tipoOperacion = 'C';
        tipoPerColegPago = 'C';
    }
    
    if($("#chkConceptoNoCol").is(":checked")){
        tipoOperacion = 'N';
        tipoPerColegPago = 'N';
    }
    
    if(tipoOperacion === undefined){
        mostrarMensaje('danger', 'Debe elegir un tipo');
        return;
    }
    
    var colegiado = new Colegiado();
    var doc = $("#txtNumColegiaturaDNI").val();
    
    if(tipoOperacion === 'C'){
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
    } else {
        if (doc === '') {
            mostrarMensaje('danger','[Error: El DNI no puede ser vacio]');
            $("#txtDni").focus();
            return;
        }

        if (doc.length < 8 || doc.length > 9){
            mostrarMensaje('danger', '[Error: El DNI solo debe tener 8 numeros]');
            return;
        }

        if(!expRegDni.test(doc)){
            mostrarMensaje('danger','[Error: El DNI solo acepta numeros]');
            return;
        }
        colegiado.dni = doc;
    }
    
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
        var tipoOperacionTipoPago = 5;
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
            } else{
                $(".nombre").show();
                $(".estado").hide();
            }
            $("#btnComprobar").prop("disabled", true);
            $("#btnComprobar").hide();
            $("#txtNumColegiaturaDNI").prop("disabled", true);
            $(".tipo").hide();
            $("#chkConceptoCol").prop("disabled", true);
            $("#chkConceptoNoCol").prop("disabled", true);
            $(".pagos").show();
        } else {
            mostrarMensaje('danger',listTipoPagoRep.msjError);
        }    
    } else {
        mostrarMensaje('danger',listaColegiadoRep.msjError);
    }
    
};


var listarDeudaCuota = function(){
    var index = $(this).val();
    if(index !== ''){
        var tipoPago = listaTipoPagos[parseInt(index)];
        if(tipoPago.esInhabilitadora === 1){
            if(tipoPerColegPago === 'C'){
                if(tipoPago.esInhabilitadora === 1 && tipoPerColegPago === 'N'){
                    mostrarMensaje('danger', 'Se ha encontrado una incoherencia en la parametrización del tipo de pago. ' + tipoPago.concepto +
                            ' Una persona no puede tener pagos inhabilitadores');
                    $("#panelGrabar").hide();
                    return;
                }

                if(tipoPago.esInhabilitadora === 1 && tipoPerColegPago === 'C' && (colegSeleccionado.estado === 3 || colegSeleccionado.estado === 4)){
                    mostrarMensaje('danger', 'Se ha encontrado una incoherencia en la parametrización del tipo de pago. '+ tipoPago.concepto +
                            ' Un Colegiado Exonerado o Egresado no puede tener pagos inhabilitadores');
                    $("#panelGrabar").hide();
                    return;
                }

                var colegiado = new Colegiado();
                colegiado.dni = colegSeleccionado.dni;
                colegiado.numColegiatura = colegSeleccionado.numColegiatura;
                var tipoOperacion = 1;

                var deuda = new Deuda();
                deuda.colegiad = colegiado;
                deuda.tipoPago = tipoPago;
                var deudadRep = deuda.listarDeuda(tipoOperacion);
                if(deudadRep.indError === 0){
                    if(deudadRep.detalleDeuda.length === 0){
                        $("#lblPeridosDeuda").text("Esta cuota es inhabilitadora y no tiene deuda actualmente");
                        $(".periodosDeuda2").show();
                        cantidadDeuda = 0;
                    } else {
                        var cadena = "Esta cuota es inhabilitadora sus meses de deuda son: ";
                        cantidadDeuda = deudadRep.detalleDeuda.length;
                        for(var i = 0; i < deudadRep.detalleDeuda.length; i++){
                            cadena += deudadRep.detalleDeuda[i].periodo + " ";
                        }                     
                        $("#lblPeridosDeuda").text(cadena);
                        $(".periodosDeuda2").show();
                    }
                } else {
                    mostrarMensaje('danger', deudadRep.msjError);
                }
            }
        }
    } else {
        $("#lblPeridosDeuda").text("");
        $(".periodosDeuda2").hide();
    }
};

var annadirItemPago = function(){
    var indexTipoPago = $("#cboPagos").val();
    
    if(indexTipoPago === ''){
        mostrarMensaje('danger', 'Debe seleccionar un tipo de pago');
        return;
    }
    
    var tipoPago = listaTipoPagos[parseInt(indexTipoPago)];
    
    if(tipoPago.annadido === 1){
        mostrarMensaje('danger', 'Este pago ya esta añadido. Si se permite debe cambiar la cantidad solamente');
        return;
    }
    
    var $body = $("#bodyConceptosDetalle");
    var $tr, $td, $boton, $input;
    var tipoCantidad = 1;
    if(tipoPago.esInhabilitadora === 1){
        $tr = $("<tr/>",{
           "id" : indexTipoPago
        });
       
        if(tipoPago.tipoGeneracion === 1){
            if(cantidadDeuda === 0){
                mostrarMensaje('danger', 'Ud. No tiene deuda en este pago. No esta permitido adelantar pagos');
                return;
            }
            $td = $("<td/>",{
                "id" : "cantidad_"+indexTipoPago,
                "text" : (cantidadDeuda === 0 ? (cantidadDeuda +1) : cantidadDeuda)
            });
            $td.append($input);
            $tr.append($td);
            tipoCantidad = 1;
        } else if(tipoPago.tipoGeneracion === 2){
            if(cantidadDeuda === 0){
                mostrarMensaje('danger', 'Ud. No tiene deuda en este pago. No esta permitido adelantar pagos');
                return;
            }
            $td = $("<td/>",{
                "text" : (cantidadDeuda === 0 ? (cantidadDeuda +1) : cantidadDeuda)
            });
            $tr.append($td);
            tipoCantidad = 1;
        } else if(tipoPago.tipoGeneracion === 3){ 
           $input = $('<input type="text" class="form-control" id="txtCantidad_'+indexTipoPago+'"'+
                        ' value="'+ (cantidadDeuda === 0 ? (cantidadDeuda +1) : cantidadDeuda) +'"'+
                        ' onfocus = "asignaValorAnt(\'txtCantidad_'+indexTipoPago+'\')" onblur = "cambiarCantidad(\''+indexTipoPago+'\','+ cantidadDeuda+')"/>');
            $td = $("<td/>");
            $td.append($input);
            $tr.append($td);
            tipoCantidad = 2;
        }
        
       $td = $("<td/>",{
           "text" : tipoPago.concepto
       });
       $tr.append($td);
       
        $td = $("<td/>",{
           "text" : tipoPago.precioActual.precio
       });
       $tr.append($td);
       
       
       var importeParcial  =  (cantidadDeuda === 0 ? (cantidadDeuda +1) : cantidadDeuda) * tipoPago.precioActual.precio;
       importeTotal = importeTotal + importeParcial;
       $td = $("<td/>",{
           "id" : "importe_"+indexTipoPago,
           "text" : importeParcial
       });
       $tr.append($td);
       
       $boton = $('<button type="button" class="btn btn-primary btn-block" onclick="quitarFila(\''+indexTipoPago+'\','+tipoCantidad+')">' +
                    '<span class="glyphicon glyphicon-minus"></span>' +
                '</button>');
       $td = $("<td/>");
       $td.append($boton);
       $tr.append($td);
    } else {
       $tr = $("<tr/>",{
           "id" : indexTipoPago
       });
       $td = $("<td/>",{
           "id" : "cantidad_"+indexTipoPago,
           "text" : "1"
       });
       $tr.append($td);
       
       $td = $("<td/>",{
           "text" : tipoPago.concepto
       });
       $tr.append($td);
       
        $td = $("<td/>",{
           "text" : tipoPago.precioActual.precio
       });
       $tr.append($td);
       
       
       var importe  =  tipoPago.precioActual.precio;
       importeTotal = importeTotal + importe;
       $td = $("<td/>",{
           "id" : "importe_"+indexTipoPago,
           "text" : importe
       });
       $tr.append($td);
       
       $boton = $('<button type="button" class="btn btn-primary btn-block" onclick="quitarFila(\''+indexTipoPago+'\','+tipoCantidad+')">' +
                    '<span class="glyphicon glyphicon-minus"></span>' +
                '</button>');
       $td = $("<td/>");
       $td.append($boton);
       $tr.append($td);
    }
    $("#totalImporte").text(importeTotal);
    listaTipoPagos[parseInt(indexTipoPago)].annadido = 1;
    listaTipoPagos[parseInt(indexTipoPago)].tipoCantidad = tipoCantidad;
    $body.append($tr);    
};

var limpiarInterfaz = function(){
    $(".tipo").show();
    $("#chkConceptoCol").prop("disabled", false);
    $("#chkConceptoNoCol").prop("disabled", false);
    if($("#chkConceptoCol").is(":checked")){
        tipoPerColegPago = 'C';
        $("#comprobarTipo").show();
        $(".comprobarIdentificacion").show();
        $("#btnComprobar").prop("disabled", false);
        $("#btnComprobar").show();
        $(".nombre").hide();
        $(".estado").hide();
        $("#lblDocComprobacion").text("Num. Colegiatura o DNI:");
        $("#txtNumColegiaturaDNI").attr("placeholder","num Coleg. o DNI");
        $("#txtNumColegiaturaDNI").attr("maxlength","10");
        $("#txtNumColegiaturaDNI").val("");
        $("#txtNumColegiaturaDNI").prop("disabled", false);
    }
    
    if($("#chkConceptoNoCol").is(":checked")){
        tipoPerColegPago = 'N';
        $("#comprobarTipo").show();
        $(".comprobarIdentificacion").show();
        $("#btnComprobar").prop("disabled", false);
        $("#btnComprobar").show();
        $(".nombre").hide();
        $(".estado").hide();  
        $("#lblDocComprobacion").text("DNI:");
        $("#txtNumColegiaturaDNI").attr("placeholder","DNI");
        $("#txtNumColegiaturaDNI").attr("maxlength","8");
        $("#txtNumColegiaturaDNI").val("");
        $("#txtNumColegiaturaDNI").prop("disabled", false);
    }
    colegSeleccionado = undefined;
    listaTipoPagos = undefined;
    detalleDeudas = undefined;
    importeTotal = 0;
    $(".pagos").hide();
    $("#cboPagos").html("");
    $("#lblPeridosDeuda").text("");
    $(".periodosDeuda2").hide();
    $("#bodyConceptosDetalle").html("");
    $("#totalImporte").text(0);
};

var asignaValorAnt =  function(idTextPago){
    var expReg = /^[0-9]+$/;
    var valor = $("#"+idTextPago).val();
    if(valor === ''){
        mostrarMensaje('danger', 'debe ingresar una cantidad');
        return;
    }
    
    if(valor === '0'){
        mostrarMensaje('danger', 'la cantidad minima es 1');
        return;
    }
    
    if(!expReg.test(valor)){
        mostrarMensaje('danger', 'Debe ingresar solo numeros');
        return;
    }
    $("#panelError").html("");
    valorPagoLinea = parseInt(valor);
};

var cambiarCantidad = function(indexTipoPago, cantidadDeudaIni){
    var expReg = /^[0-9]+$/;
    var valor = $("#txtCantidad_"+indexTipoPago).val();
    if(valor === ''){
        mostrarMensaje('danger', 'debe ingresar una cantidad');
        return;
    }
    
    if(valor === '0'){
        mostrarMensaje('danger', 'la cantidad minima es 1');
        return;
    }
    
    if(!expReg.test(valor)){
        mostrarMensaje('danger', 'Debe ingresar solo numeros');
        return;
    }
    $("#panelError").html("");
    var tipoPago = listaTipoPagos[parseInt(indexTipoPago)];
    importeTotal = importeTotal - (valorPagoLinea*tipoPago.precioActual.precio);
    
    importeTotal = importeTotal + (parseInt(valor)*tipoPago.precioActual.precio);
    $("#importe_"+indexTipoPago).text(parseInt(valor)*tipoPago.precioActual.precio);
    $("#totalImporte").text(importeTotal);
};

var quitarFila = function(indexTipoPago, tipoCantidad){
    var expReg = /^[0-9]+$/;
    var valor;
    if(tipoCantidad === 2){
       valor = $("#txtCantidad_"+indexTipoPago).val();
    } else if(tipoCantidad === 1){
       valor = $("#cantidad_"+indexTipoPago).text(); 
       
        if(valor !== '1'){
            mostrarMensaje('danger', 'Ingresa valor correcto.');
            return;
        }
    }
           
    if(valor === ''){
        mostrarMensaje('danger', 'debe ingresar una cantidad');
        return;
    }
    
    if(valor === '0'){
        mostrarMensaje('danger', 'la cantidad minima es 1.');
        return;
    }
    
    if(!expReg.test(valor)){
        mostrarMensaje('danger', 'Debe ingresar solo numeros.');
        return;
    }
    $("#panelError").html("");
    var tipoPago = listaTipoPagos[parseInt(indexTipoPago)];
    importeTotal = importeTotal - (valor*tipoPago.precioActual.precio);
    $("#totalImporte").text(importeTotal);
    listaTipoPagos[parseInt(indexTipoPago)].annadido = 0;
    $('#'+indexTipoPago).remove();
};

var grabarPago = function(){
    var arrayDetallePagos = [];
    var pagoDetalle;
    var j = 0;
    var expReg = /^[0-9]+$/;
    var valor;
    var colegiado = new Colegiado();
    colegiado.dni = colegSeleccionado.dni;
    colegiado.numColegiatura = colegSeleccionado.numColegiatura;
    colegiado.nombres = colegSeleccionado.nombres;
    var pago = new Pago();
    pago.colegiado = colegiado;
    for(var i = 0; i < listaTipoPagos.length; i++){
        var tipoPago = listaTipoPagos[i];
        if(tipoPago.annadido === 1){
            pagoDetalle = new PagoDetalle();
            pagoDetalle.tipoPago= tipoPago;
            pagoDetalle.secuencia = (j + 1);
            if(tipoPago.tipoCantidad === 1){
                valor = $("#cantidad_"+i).text(); 
       
                if(valor !== '1'){
                    mostrarMensaje('danger', 'Ingresa valor correcto.');
                    return;
                }
            } else if(tipoPago.tipoCantidad === 2){
                valor = $("#txtCantidad_"+i).val();
            }
            
            if(valor === ''){
                mostrarMensaje('danger', 'debe ingresar una cantidad');
                return;
            }

            if(valor === '0'){
                mostrarMensaje('danger', 'la cantidad minima es 1.');
                return;
            }

            if(!expReg.test(valor)){
                mostrarMensaje('danger', 'Debe ingresar solo numeros.');
                return;
            }
            
            pagoDetalle.cantidad = parseInt(valor);
            arrayDetallePagos[j] = pagoDetalle;
            j++;
        }
    }
    pago.listPagoDetalle = arrayDetallePagos;
    var tipoOperacion = 1;
    var pagoRep = pago.mantPagos(tipoOperacion);
    if(pagoRep.indError === 0){
        mostrarMensaje('success', pagoRep.msjError);
        limpiarInterfaz();
    } else {
        mostrarMensaje('danger', pagoRep.msjError);
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