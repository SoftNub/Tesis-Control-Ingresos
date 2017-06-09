/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function Deuda(colegiad, tipoPago, detalleDeuda, deudaTotal, periodo){
    this.colegiad = colegiad;
    this.tipoPago = tipoPago;
    this.periodo = periodo;
    this.detalleDeuda = detalleDeuda;
    this.deudaTotal = deudaTotal;
    this.indError = "";
    this.msjError = "";
    this.listarDeuda = listarDeuda;
    this.mantDeuda = mantDeuda;
}

function DeudaDetalle(tipoPago, periodo, deudaTotal){
    this.tipoPago = tipoPago;
    this.periodo = periodo;
    this.deudaTotal = deudaTotal;
    this.indError = "";
    this.msjError = "";
}

function listarDeuda(tipoOperacion){
    var deudaRep;
    var colegiado = {
        "dni" : this.colegiad.dni,
        "numColegiatura" : this.colegiad.numColegiatura
    };
    var deuda ={
        "colegiad": colegiado,
        "tipoPago": this.tipoPago
    };
    deudaRep = new Deuda();
    $.ajax({
        url : "../pagos/listarDeuda.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : JSON.stringify({tipoOperacion: tipoOperacion, deuda : deuda}),
        success : function(data) {
            deudaRep = data;
        }
    });
    return deudaRep;
}

function mantDeuda(tipoOperacion){
    var deudaRep;

    var deuda ={
        "colegiad": this.colegiad,
        "tipoPago": this.tipoPago,
        "periodo": this.periodo
    };
    deudaRep = new Deuda();
    $.ajax({
        url : "../pagos/mantDeuda.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : JSON.stringify({tipoOperacion: tipoOperacion, deuda : deuda}),
        success : function(data) {
            deudaRep = data;
        }
    });
    return deudaRep;
}


