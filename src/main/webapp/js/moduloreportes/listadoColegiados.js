/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var reporteActual = 1;
var tipoBusqueda = 0;
var codColegiado = '';
var listColegiados;
var colegiadoSeleccionado;

$(document).ready(function(){
    $(".reporte1").show();
    $(".reporte2").hide();
    
    $("#cboReporte").on("change",function(){
        var valor = $(this).val();
        if(valor === '1'){
            reporteActual = 1;
            codColegiado = '';
            $("#colegRep1").val("SELECCIONE COLEGIADO");
            $(".reporte1").show();
            $(".reporte2").hide();
        } else if (valor === '2'){
            reporteActual = 2;
            codColegiado = 'ALL';
            $("#colegRep2").val("TODOS LOS COLEGIADOS");
            $(".reporte1").hide();
            $(".reporte2").show();
        } else {
            reporteActual = 0;
            codColegiado = '';
            mostrarMensaje("danger", "reporte no permitido");
        }
    });
    
    $("#btnExportaPDF1").on("click", function(){
        if(reporteActual === 1){
            generarReporteColegiados("PDF");
        } else {
            mostrarMensaje("danger", "reporte no permitido");
        }        
    });
    
    $("#btnExportarPDF").on("click", function(){
        if(reporteActual === 2){
            generarReporteColegiados("PDF");
        } else {
            mostrarMensaje("danger", "reporte no permitido");
        }        
    });
    
    $("#btnExportaExcel").on("click", function(){
        if(reporteActual === 2){
            generarReporteColegiados("XLS");
        } else {
            mostrarMensaje("danger", "reporte no permitido");
        }        
    });
    
    $("#btnBuscarColegiado").on("click", function(){
        buscarColegiado();  
    });
    
     $("#btnAceptarColegiado").on("click", function(){
        colegiadoSeleccionado = listColegiados[0];
        if(reporteActual === 1){
            $("#colegRep1").val(colegiadoSeleccionado.nombres);
            codColegiado = colegiadoSeleccionado.numColegiatura;
            $("#myModalColegiado").modal("hide");
        } else if(reporteActual === 2){
            $("#colegRep2").val(colegiadoSeleccionado.nombres);
            codColegiado = colegiadoSeleccionado.numColegiatura;
            $("#myModalColegiado").modal("hide");
        } else {
            mostrarMensaje("danger", "reporte no permitido");
        }
    });
});


var generarReporteColegiados = function(formato){
    var url = "";
    var estado;
    if(reporteActual === 1 && formato === 'PDF'){
        url += "../reportes/reporteColegiados.action?reporte="+reporteActual+"&opc="+6+"&dni=0&numeroColegiatura="+
                codColegiado+"&estado="+0+"&formato="+formato;
        location.href = url;
    } else if (reporteActual === 2 && (formato === 'PDF' || formato === 'XLS')){
        estado = parseInt($("#cboEstado").val());
        url += "../reportes/reporteColegiados.action?reporte="+reporteActual+"&opc="+7+"&dni=0&numeroColegiatura="+
                codColegiado+"&estado="+estado+"&formato="+formato;
        location.href = url;
    } else {
         mostrarMensaje("danger", "reporte no permitido");
    }
};

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
            codColegiado = '';
            $("#colegRep1").val("SELECCIONE COLEGIADO");
        } else {
            mostrarMensaje("danger", "Busqueda no permitida");
        }
    } else if(reporteActual === 2){
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
            $("#colegRep2").val("TODOS LOS COLEGIADOS");
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