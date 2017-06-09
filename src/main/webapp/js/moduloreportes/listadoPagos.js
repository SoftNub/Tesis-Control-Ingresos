/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var tipoBusqueda;
var listColegiados;
var reporteActual;
var codColegiado;
var codPersona;

$(document).ready(function(){
    $("#txtPeriodo").datepicker();
    $(".reporte1").show();
    $(".reporte2").hide();
    reporteActual = 1;
    $("#chkConceptoCol").on("change",function(){
        if($(this).is(":checked")){
            $(".reporte1").show();
            $(".reporte2").hide();
            reporteActual = 1;
            $("#colegRep1").val("TODOS LOS COLEGIADOS");
            codColegiado = 'ALL';
        } 
    });
    
    $("#chkConceptoNoCol").on("change",function(){
        if($(this).is(":checked")){
            $(".reporte1").hide();
            $(".reporte2").show();
            reporteActual = 2;
            $("#colegRep1").val("TODAS LAS PERSONAS");
            codPersona = 'ALL';
        } 
    });
    
    $("#btnExportaExcel").on("click", function(){
        generarReporte("XLS");
    });
    
    $("#btnExportarPDF").on("click", function(){
        generarReporte("PDF");
    });
    
    $("#btnBuscarColegiado").on("click", function(){
        buscarColegiado();
    });
    
    $("#btnBuscarPersona").on("click", function(){
        buscarPersona();
    });
    
    $("#btnAceptarColegiado").on("click", function(){
        $("#colegRep1").val(listColegiados[0].nombres);
        codColegiado = listColegiados[0].numColegiatura;
        $("#myModalColegiado").modal("hide");
    });
    
    $("#btnAceptarPersona").on("click", function(){
        $("#colegRep2").val(personaRep.nombres+" "+personaRep.apePaterno+" "+ personaRep.apeMaterno);
        codPersona = personaRep.dni;
        $("#myModalPersona").modal("hide");
    });
    
    listarTipoPagos();
});

var seleccionaColegiado = function(indBusqueda){
    if(reporteActual === 1){
        $("#divTabla").html("");
        if(indBusqueda === 1){
            tipoBusqueda = 1;
            $("#labelNum").text("Numero Colegiatura:");
            //$("#txtNum").prop("maxlength", 10);
            $("#txtNum").val("");
            $("#myModalColegiado").modal({backdrop: false});
        } else if(indBusqueda === 2){
            tipoBusqueda = 2;
            $("#labelNum").text("DNI:");
            //$("#txtNum").prop("maxlength", 8);
            $("#txtNum").val("");
            $("#myModalColegiado").modal({backdrop: false});
        } else if(indBusqueda === 3){
            codColegiado = 'ALL';
            $("#colegRep1").val("TODOS LOS COLEGIADOS");
        } else {
            mostrarMensaje("danger", "Busqueda no permitida");
        }
    } else if(reporteActual === 2){
        $("#divTablaPersona").html("");
        if(indBusqueda === 1){
            tipoBusqueda = 1;
            $("#myModalColegiado").modal({backdrop: false});
        } else if(indBusqueda === 2){
            codColegiado = 'ALL';
            $("#colegRep2").val("TODAS LAS PERSONAS");
        } else {
            mostrarMensaje("danger", "Busqueda no permitida");
        }
    } else {
        mostrarMensaje("danger", "reporte no permitido");
    }
};

var buscarColegiado = function(){
    var doc;
    var colegiado = new Colegiado();
    var expRegDni = /[0-9]{8}/;
    var expRegNumColegiatura = /^[0-9]{1,10}$/; //^[0-9]*$
    var tipoDocumento = 2;
    var tipoOperacion = 'C';
    doc = $("#txtNum").val();
    if(tipoBusqueda === 1){
        if (doc === '') {
            mostrarMensajeInterno('danger', '[Error: El Numero Colegiatura o DNI no puede ser vacio]');
            return;
        }

        if (doc.length < 1 || doc.length > 10){
            mostrarMensajeInterno('danger', '[Error: El Numero Colegiatura acepta maximo 10 numeros y DNI debe tener 8 numeros]');
            return;
        }

        if(!expRegNumColegiatura.test(doc)){
            mostrarMensajeInterno('danger', '[Error: El Numero Colegiatura o DNI solo acepta numeros]');
            return;
        }
        colegiado.numColegiatura = doc;
    } else if (tipoBusqueda === 2){
        if (doc === '') {
            mostrarMensajeInterno('danger','[Error: El DNI no puede ser vacio]');
            return;
        }

        if (doc.length < 8 || doc.length > 9){
            mostrarMensajeInterno('danger', '[Error: El DNI solo debe tener 8 numeros]');
            return;
        }

        if(!expRegDni.test(doc)){
            mostrarMensajeInterno('danger','[Error: El DNI solo acepta numeros]');
            return;
        }
        colegiado.numColegiatura = doc;
    } else {
        mostrarMensajeInterno("danger", "tipo de busqueda no permitida");
        return;
    }
    var $tr, $td;
    var listaColegiados = new ListColegiado();
    var listaColegiadoRep = listaColegiados.buscarColegiadosPagos(tipoOperacion, tipoDocumento, colegiado);
    if(listaColegiadoRep.indError === 0){
        $("#divTabla").html("<table class='table table-bordered'><thead><th>Num. Colegiatura</th><th>DNI</th><th>Nombre y Apellidos</th></thead><tbody id='bodyTabla'></tbody></table>");
        listColegiados = listaColegiadoRep.listaColegiados;
        for(var i = 0; i < listColegiados.length; i++){
            $tr = $("<tr/>");
            $td = $("<td/>",{
               "text" :  listColegiados[i].numColegiatura
            });
            $tr.append($td);
            
            $td = $("<td/>",{
               "text" :  listColegiados[i].dni
            });
            $tr.append($td);
            
            $td = $("<td/>",{
               "text" :  listColegiados[i].nombres
            });
            $tr.append($td);
            
            $("#bodyTabla").append($tr);
        }
    } else {
        mostrarMensajeInterno("danger", listaColegiadoRep.msjError);
    }
};


function buscarPersona(){
    var dni, expRegDni;
    var persona;
    var $tr, $td;
    dni = $("#txtDni").val();
    expRegDni = /[0-9]{8}/;
    $("#panelError").html("");
    //validacion del dni
    if (dni === '') {
        mostrarMensaje("danger", "[Error: El DNI no puede ser vacio]");
        return;
    }
    
    if (dni.length < 8 || dni.length > 9){
        mostrarMensaje("danger", "[Error: El DNI solo debe tener 8 caracteres]");
        $("#txtDni").focus();
        return;
    }
    
    if(!expRegDni.test(dni)){
        mostrarMensaje("danger", "[Error: El DNI debe tener 8 numeros]");
        return;
    }
    
    persona = new Persona();
    persona.dni = dni;
    personaRep = persona.buscarPersonaPreInscripcion(1);
    if (personaRep.indError === 0) {
        $("#divTabla").html("<table class='table table-bordered'><thead><th>DNI</th><th>Nombre y Apellidos</th></thead><tbody id='bodyTablaPersona'></tbody></table>");
        $tr = $("<tr/>");           
        $td = $("<td/>",{
           "text" :  personaRep.dni
        });
        $tr.append($td);

        $td = $("<td/>",{
           "text" :  personaRep.nombres+" "+personaRep.apePaterno+" "+ personaRep.apeMaterno
        });
        $tr.append($td);

        $("#bodyTablaPersona").append($tr);
    } else {
        mostrarMensaje("danger", personaRep.msjError);
    }
}

var generarReporte = function(formato){
    var url = "";
    var estado;
    var periodo = $("#txtPeriodo").val();
    var tipoPago = $("#cboPagos").val();
    
    if(tipoPago !== '0'){
        tipoPago = listaTipoPago[(parseInt(tipoPago)-1)].id;
    }
    
    if($("#chkEstadoPago").is(":checked")){
        estado = -1;
    } else {
        estado = 1;
    }
    
    if(reporteActual === 1){
        url += "../reportes/reportePagos.action?opc="+17+"&dni=&numeroColegiatura="+
                codColegiado+"&periodo="+periodo+"&codTipoPago="+tipoPago+
                "&estadoTipoPago="+estado+"&fechaFin=&codPago=0&formato="+formato;
        location.href = url;
    } else if (reporteActual === 2){
//        estado = parseInt($("#cboEstado").val());
        url += "../reportes/reportePagos.action?opc="+17+"&dni="+codPersona+"&numeroColegiatura="+
                "&periodo="+periodo+"&codTipoPago="+tipoPago+
                "&estadoTipoPago="+estado+"&fechaFin=&codPago=0&formato="+formato;
        location.href = url;
    } else {
         mostrarMensaje("danger", "reporte no permitido");
    }
};

var listarTipoPagos = function(){
    var tipoPago = new TipoPago();
        //tipoPago.estado = 1;
    var tipoOperacionTipoPago = 1;
    var listTipoPagoRep = tipoPago.listarTipoPago(tipoOperacionTipoPago);
    if(listTipoPagoRep.indError === 0){
        listaTipoPago = listTipoPagoRep.listTipoPago;
        var $option;
        $("#cboPagos").html("<option value='0'>--TODOS--</option>");
        for(var i = 0; i < listaTipoPago.length; i++){
            $option = $("<option/>",{
                "value" : (i+1),
                "text" : listaTipoPago[i].concepto
            });
            $("#cboPagos").append($option);
        }
    } else {
        mostrarMensaje('danger',listTipoPagoRep.msjError);
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

var mostrarMensajeInterno = function(tipo,mensaje){
    var htmlAlert;
    var $strong;
    htmlAlert = '<div class="alert alert-'+tipo+'">';
    htmlAlert += '<a href="#" class="close" data-dismiss="alert"'
            + 'aria-label="close">&times;</a></div>';
    $("#panelErrorInt").html(htmlAlert);
    $strong = $("<strong/>",{
        text : mensaje
    });
    $("#panelErrorInt div").append($strong);
    $("#panelErrorInt").show();
};