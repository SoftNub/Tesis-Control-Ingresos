/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var listaTipoPago;
var pagoSeleccionado;

$(document).ready(function(){
    listarTipoPagos();
    
    $("#btnBuscar").on("click", function(){
        buscarPagos();
    });
    
    $("#btnRevertir").on("click", function(){
        revertirPagos();
    });
    
    $("#btnLimpiar").on("click", function(){
        limpiar();
    });
});


var listarTipoPagos = function(){
    var tipoPago = new TipoPago();
    var tipoOperacionTipoPago = 1;
    var listTipoPagoRep = tipoPago.listarTipoPago(tipoOperacionTipoPago);
    if(listTipoPagoRep.indError === 0){
        listaTipoPago = listTipoPagoRep.listTipoPago;
    } else {
        mostrarMensaje('danger',listTipoPagoRep.msjError);
    }    
};

var buscarPagos = function(){
    var expReg = /^[0-9]+$/;
    var codigo = $("#txtCodigoPago").val();
    if(codigo === ''){
        mostrarMensaje('danger','Debe ingresar un codigo de pago');
        return;
    }
    
    if(!expReg.test(codigo)){
        mostrarMensaje('danger','Debe ingresar solo números');
        return;
    }
    
    var tipoPago = new TipoPago();
    pagoSeleccionado = new Pago();
    pagoSeleccionado.codigo = parseInt(codigo);
    pagoSeleccionado.estado = 1;
    var operacion = 16;

    var listPagoRep = pagoSeleccionado.listarPagos(operacion, tipoPago);
    if(listPagoRep.indError === 0){
        listaPagos = listPagoRep.listaGenerico;
        
        if(listaPagos.length === 0){
            mostrarMensaje('danger','No se ha encontrado resultados con ese codigo de pago');
            return;
        }
        
        if(listaPagos.length > 1){
            mostrarMensaje('danger','Se ha encontrado mas de un resultado. Comuniquese con el administrador');
            return;
        }
        
        var $tr, $td;
        $("#divTablaCabecera").html("<table class='table table-bordered' id='tablaPagos'><thead><th>Codigo Pago</th><th>DNI</th>"+
                "<th>Num. Colegiatura</th><th>Nombre</th>"+
                "<th>Monto Total</th><th>Fecha Pago</th><th>Estado</th></thead><tbody id='bodyPagos'></tbody>");
        for(var i = 0; i < listaPagos.length; i++){
            $tr = $("<tr/>");
            $td = $("<td/>",{
                "text" : listaPagos[i].codigo
            });
            $tr.append($td);
            $td = $("<td/>",{
                "text" : listaPagos[i].colegiado.dni
            });
            $tr.append($td);
            $td = $("<td/>",{
                "text" : listaPagos[i].colegiado.numColegiatura
            });
            $tr.append($td);
            $td = $("<td/>",{
                "text" : listaPagos[i].colegiado.nombres
            });
            $tr.append($td);
            $td = $("<td/>",{
                "text" : listaPagos[i].total
            });
            $tr.append($td);
            $td = $("<td/>",{
                "text" : listaPagos[i].fechaPago
            });
            $tr.append($td);
            if(listaPagos[i].estado === 1){
                $td = $("<td/>",{
                    "text" : "Pagado"
                });
            } else {
                $td = $("<td/>",{
                    "text" : "Revertido"
                });
            }
            $tr.append($td);
            $("#bodyPagos").append($tr);
            
            $("#divTablaDetalle").html("<table class='table table-bordered' id='tablaPagoDetalle'><thead><th>Secuencia</th><th>Tipo Pago</th>"+
                "<th>Cantidad</th><th>Precio Unitario</th>"+
                "<th>Importe</th></thead><tbody id='bodyPagoDetalle'><t/body>");
            var $tr2, $td2;    
            for(var j = 0; j < listaPagos[i].listPagoDetalle.length; j++){
                $tr2 = $("<tr/>");
                $td2 = $("<td/>",{
                    "text" : listaPagos[i].listPagoDetalle[j].secuencia
                });
                $tr2.append($td2);
                
                for(var k = 0; k < listaTipoPago.length; k++){
                    if(listaTipoPago[k].id === listaPagos[i].listPagoDetalle[j].tipoPago.id){
                        break;
                    }
                }
                $td2 = $("<td/>",{
                    "text" : listaTipoPago[k].concepto
                });
                $tr2.append($td2);
                $td2 = $("<td/>",{
                    "text" : listaPagos[i].listPagoDetalle[j].cantidad
                });
                $tr2.append($td2);
                $td2 = $("<td/>",{
                    "text" : listaPagos[i].listPagoDetalle[j].tipoPago.precioActual.precio
                });
                $tr2.append($td2);
                
                $td2 = $("<td/>",{
                    "text" : listaPagos[i].listPagoDetalle[j].importe
                });
                $tr2.append($td2);
                $("#bodyPagoDetalle").append($tr2);
            }    
        }
        $("#btnBuscar").prop("disabled", true);
        $("#btnBuscar").hide();
        $("#txtCodigoPago").prop("disabled", true);
    } else {
        pagoSeleccionado = undefined;    
        mostrarMensaje('danger',listPagoRep.msjError);
    }    
};

var revertirPagos = function(){
    if(pagoSeleccionado === undefined){
        mostrarMensaje('danger', 'Debe ingresar un codigo pago');
        return;
    }
    var operacion = 2;
    var pagoRep = pagoSeleccionado.mantPagos(operacion);
    if(pagoRep.indError === 0){
       mostrarMensaje('success',pagoRep.msjError);
       limpiar();
    } else { 
        mostrarMensaje('danger',pagoRep.msjError);
    }   
};

var limpiar = function(){
    pagoSeleccionado = undefined;
    $("#btnBuscar").prop("disabled", false);
    $("#btnBuscar").show();
    $("#txtCodigoPago").prop("disabled", false);
    $("#txtCodigoPago").val("");
    $("#divTablaCabecera").html("");
    $("#divTablaDetalle").html("");
    listarTipoPago();
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