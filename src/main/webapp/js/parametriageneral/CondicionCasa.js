/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function CondicionCasa(idCondicion, denominacion, habilitado){
    this.idCondicion = idCondicion;
    this.denominacion = denominacion;
    this.habilitado = habilitado;
    this.mantCondicionCasa = mantCondicionCasa;
    this.listCondicionCasa = listCondicionCasa;
}

function ListCondicionCasa(CondicionesCasa, indError, msjError){
    this.CondicionesCasa = CondicionesCasa;
    this.indError =indError;
    this.msjError = msjError;
}

function mantCondicionCasa(opcCrud){
    var condicionCasa;
    var condicionCasa2 ={
        "idCondicion": this.idCondicion,
        "denominacion": this.denominacion,
        "habilitado": this.habilitado
    };
    condicionCasa = new CondicionCasa();
    $.ajax({
        url : "mantCondicionCasa.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : JSON.stringify({opc : opcCrud, condCasa : condicionCasa2}),
        success : function(data) {
            condicionCasa.indError = data.indError;
            condicionCasa.msjError = data.msjError;
        }
    });
    return condicionCasa;
}

function listCondicionCasa(opcListado){
    var listCondicionCasas = new ListCondicionCasa();
    var condicionCasa2 ={
        "idCondicion": this.idCondicion,
        "habilitado": this.habilitado
    };
    $.ajax({
        url : "listCondicionCasa.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : JSON.stringify({opc : opcListado, condCasa : condicionCasa2}),
        success : function(data) {
            listCondicionCasas = new ListCondicionCasa(data.listaCondicionesCasas, data.indError, data.msjError);
        }
    });
    return listCondicionCasas;
}

