/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function LogTipoPago(id, periodo, fechaGeneracion, indicador, mensaje){
    this.id = id;
    this.periodo = periodo;
    this.fechaGeneracion = fechaGeneracion;
    this.indicador = indicador;
    this.mensaje = mensaje;
    this.indError = "";
    this.msjError = "";
    this.verLogCoutasMensuales = verLogCoutasMensuales;
}

function ListLogTipoPago(listLogTipoPago, indError, msjError){
    this.listLogTipoPago = listLogTipoPago;
    this.indError = indError;
    this.msjError = msjError;
}

function verLogCoutasMensuales(tipoOperacion, fechaInicio, fechaFin){
    var listaLogTipoPagoRep;
    listaLogTipoPagoRep = new ListLogTipoPago();
    $.ajax({
        url : "../pagos/verLogCoutasMensuales.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : JSON.stringify({tipoOperacion: tipoOperacion,fechaInicio: fechaInicio, fechaFin : fechaFin}),
        success : function(data) {
            listaLogTipoPagoRep.listLogTipoPago = data.listLogTipoPago;
            listaLogTipoPagoRep.indError = data.indError;
            listaLogTipoPagoRep.msjError = data.msjError;
        }
    });
    return listaLogTipoPagoRep;
}