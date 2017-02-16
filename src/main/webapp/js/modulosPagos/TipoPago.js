/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function TipoPago(id, concepto, conceptoPara, esInhabilitadora, numPagosActivos, tipoGeneracion,
    estado, estadosColegiados, precioActual, precioEspera, preciosHistoricos){
    this.id = id;
    this.concepto = concepto;
    this.conceptoPara = conceptoPara;
    this.esInhabilitadora = esInhabilitadora;
    this.numPagosActivos = numPagosActivos;
    this.tipoGeneracion = tipoGeneracion;
    this.estado = estado;
    this.estadosColegiados = estadosColegiados;
    this.precioActual = precioActual;
    this.precioEspera = precioEspera;
    this.preciosHistoricos = preciosHistoricos;
    this.indError = "";
    this.msjError = "";
    this.listarTipoPago = listarTipoPago;
    this.mantTipoPago = mantTipoPago;
}

function ListTipoPago(listTipoPago, indError, msjError){
    this.listTipoPago = listTipoPago;
    this.indError = indError;
    this.msjError = msjError;
}


function listarTipoPago(tipoOperacion){
    var listaTipoPagoRep;
    var tipoPago ={
        "id": this.id,
        "conceptoPara" : this.conceptoPara,
        "estado" : this.estado
    };
    listaTipoPagoRep = new ListTipoPago();
    $.ajax({
        url : "../pagos/listarTipoPago.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : JSON.stringify({tipoOperacion: tipoOperacion, tipoPago : tipoPago}),
        success : function(data) {
            listaTipoPagoRep.listTipoPago = data.listTipoPago;
            listaTipoPagoRep.indError = data.indError;
            listaTipoPagoRep.msjError = data.msjError;
        }
    });
    return listaTipoPagoRep;
}

function mantTipoPago(tipoOperacion){
    var tipoPagoRep;
    var tipoPago ={
        "id" : this.id,
        "concepto" : this.concepto,
        "conceptoPara" : this.conceptoPara,
        "esInhabilitadora" : this.esInhabilitadora,
        "numPagosActivos" : this.numPagosActivos,
        "tipoGeneracion" : this.tipoGeneracion,
        "estado" : this.estado,
        "estadosColegiados" : this.estadosColegiados,
        "precioActual" : this.precioActual
    };
    tipoPagoRep = new TipoPago();
    $.ajax({
        url : "../pagos/mantTipoPago.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : JSON.stringify({tipoOperacion: tipoOperacion, tipoPago : tipoPago}),
        success : function(data) {
            tipoPagoRep.indError = data.indError;
            tipoPagoRep.msjError = data.msjError;
        }
    });
    return tipoPagoRep;
}
