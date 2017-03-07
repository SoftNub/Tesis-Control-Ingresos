/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var listLTiposPagos;
//var accion;
//var interfaz;
//var estados = [
//    {id:"1", descripcion:"Habilitado"},
//    {id:"0", descripcion:"Inhabilitado"}
//];
$(document).ready(function(){  
    $("#btnEjecutar").on("click", function(){
        ejecutarGenCuotaMensual(); 
    });
  
    $("#btnVerLog").on("click", function(){
       verLogGenCoutaMensual(); 
    });


    $("#txtEjecucion").datepicker({  
    });

    $("#txtFechaInicioLog").datepicker({   
    });
    
    $("#txtFechaFinLog").datepicker({   
    });
});

var verLogGenCoutaMensual = function(){
    var fechaIni = $("#txtFechaInicioLog").val();
    var fechaFin = $("#txtFechaFinLog").val();
    
    if(fechaIni === ''){
        mostrarMensaje('danger', 'Debe seleccionar fecha de inicio');
        return;
    }
    
    if(fechaFin === ''){
        mostrarMensaje('danger', 'Debe seleccionar fecha de fin');
        return;
    }
    
    var tipoOperacion = 1;
    var logTipoPago = new LogTipoPago();
    var listaLogTiposPagos = logTipoPago.verLogCoutasMensuales(tipoOperacion,fechaIni,fechaFin);
    if(listaLogTiposPagos.indError === 0){
        var idDiv = $(".divLog");
        var $tr, $td, $button;
        idDiv.html("<table id='tablaLTipoPago'><thead>"+
                    "<th>Id</th><th>Periodo</th><th>Fecha Generacion</th><th>Indicador</th><th>Mensaje</th>"+
                    "</thead><tbody id='bodyLTipoPago'></tbody></table>");
        listLTiposPagos =  listaLogTiposPagos.listLogTipoPago;   
        for(var i = 0; i < listLTiposPagos.length; i++){
            $tr = $("<tr/>");
            $td = $("<td/>",{
                "text" : listLTiposPagos[i].id
            });
            $tr.append($td);
            
            $td = $("<td/>",{
                "text" : listLTiposPagos[i].periodo
            });
            $tr.append($td);
            
            $td = $("<td/>",{
                "text" : listLTiposPagos[i].fechaGeneracion
            });
            $tr.append($td);

            if(listLTiposPagos[i].indicador === 0){
                $td = $("<td><span class='label label-success'>OK</span></td>");
                $tr.append($td);
            } else {
                $td = $("<td><span class='label label-danger'>ERROR</span></td>");
                $tr.append($td);
            }
            
            $td = $("<td/>",{
                "text" : listLTiposPagos[i].mensaje
            });
            $tr.append($td);

            $("#bodyLTipoPago").append($tr);  
        }
        $("#tablaLTipoPago").dataTable();
    } else {
        mostrarMensaje('danger', listaLogTiposPagos.msjError);
    }    
};

var ejecutarGenCuotaMensual = function(){
    var fecha = $("#txtEjecucion").val();
    var fechaSplit = fecha.split('/');
    var fechaActual = new Date();
    var fechaSelect = new Date(parseInt(fechaSplit[2]), (parseInt(fechaSplit[1])-1), parseInt(fechaSplit[0]));
    if(fecha === ''){
        mostrarMensaje('danger', 'Debe seleccionar fecha. Recuerda que debe ser fin de mes');
        return;
    }
    
    if(esMayorAFechaActual(fechaSelect, fechaActual)){
        mostrarMensaje('danger', 'Fecha Seleccionada es mayor a la fecha actual');
        return;
    }
    
    var mesSelect = fechaSelect.getMonth()+1;
    var mesActual = fechaActual.getMonth()+1;
    var diaSelect = fechaSelect.getDate();
    var diaActual = fechaActual.getDate();
    if(mesSelect === mesActual){
        var fechaFinMes = new Date(fechaActual.getFullYear(), fechaActual.getMonth()+1, 0);
        var diaFinMes = fechaFinMes.getDate();
        if(diaSelect !== diaFinMes){
            mostrarMensaje('danger', 'Debe Ejecutar con fecha de fin de mes');
            return;
        }
    } else {
        var fechaFinMes2 = new Date(fechaSelect.getFullYear(), fechaSelect.getMonth()+1, 0);
        var diaFinMes2 = fechaFinMes2.getDate();
        if(diaSelect !== diaFinMes2){
            mostrarMensaje('danger', 'Debe Ejecutar con fecha de fin de mes');
            return;
        }
    } 
    var tipoPago = new TipoPago();
    var tipoPagoRep = tipoPago.generarCoutasMensuales(fecha);
    if(tipoPagoRep.indError === 0){
        mostrarMensaje('success', tipoPagoRep.msjError);
    } else {
        mostrarMensaje('danger', tipoPagoRep.msjError);
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

var esMayorAFechaActual =  function(fechaSelect, fechaActualTotal){
    var diaActual = fechaActualTotal.getDate();
    var mesActual = fechaActualTotal.getMonth();
    var annoActual = fechaActualTotal.getFullYear();
    var fechaActual = new Date(annoActual,mesActual, diaActual);
    return (fechaSelect.getTime() > fechaActual.getTime());   
};