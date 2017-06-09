/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function Pago(codigo, colegiado, total, estado, fechaPago, fechaPagoFin, listPagoDetalle){
    this.codigo = codigo;
    this.colegiado = colegiado;
    this.total = total;
    this.estado = estado;
    this.fechaPago = fechaPago;
    this.fechaPagoFin = fechaPagoFin;
    this.indError = "";
    this.msjError = "";
    this.listPagoDetalle = listPagoDetalle;
    this.mantPagos = mantPagos;
    this.listarPagos = listarPagos;
}

function PagoDetalle(codigo, secuencia, tipoPago, cantidad, importe, fechaPago){
    this.codigo = codigo;
    this.secuencia = secuencia;
    this.tipoPago = tipoPago;
    this.cantidad = cantidad;
    this.importe = importe;
    this.fechaPago = fechaPago;
    this.indError = "";
    this.msjError = "";
}

function mantPagos(tipoOperacion){
    var pagoRep;
    var pago ={
        "codigo": this.codigo,
        "colegiado": this.colegiado,
        "listPagoDetalle": this.listPagoDetalle
    };
    pagoRep = new Pago();
    $.ajax({
        url : "../pagos/mantPagos.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : JSON.stringify({tipoOperacion: tipoOperacion, pago : pago}),
        success : function(data) {
            pagoRep.indError = data.indError;
            pagoRep.msjError = data.msjError;
        }
    });
    return pagoRep;
}

function listarPagos(tipoOperacion, tipoPago){
    var listpagoRep;
    var tPago ={
        "id" : tipoPago.id
    };
    var pago ={
        "codigo": this.codigo,
        "estado": this.estado,
        "colegiado": this.colegiado,
        "fechaPago": this.fechaPago,
        "fechaPagoFin": this.fechaPagoFin
    };
    listpagoRep = new ListaGenerica();
    $.ajax({
        url : "../pagos/listarPagos.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : JSON.stringify({tipoOperacion: tipoOperacion, pago : pago, tPago : tPago}),
        success : function(data) {
            listpagoRep.listaGenerico = data.listaGenerico;
            listpagoRep.indError = data.indError;
            listpagoRep.msjError = data.msjError;
        }
    });
    return listpagoRep;
}