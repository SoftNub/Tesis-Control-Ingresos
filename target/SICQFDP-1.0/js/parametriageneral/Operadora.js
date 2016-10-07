/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function Operadora(idOperadora, denominacion, habilitado){
    this.idOperadora = idOperadora;
    this.denominacion = denominacion;
    this.habilitado = habilitado;
    this.mantOperadora = mantOperadora;
    this.listOperadoras = listOperadoras;
}

function ListOperadoras(Operadoras, indError, msjError){
    this.Operadoras = Operadoras;
    this.indError =indError;
    this.msjError = msjError;
}

function mantOperadora(opcCrud){
    var operadora;
    var operadora2 ={
        "idOperadora": this.idOperadora,
        "denominacion": this.denominacion,
        "habilitado": this.habilitado
    };
    operadora = new Operadora();
    $.ajax({
        url : "mantOperadora.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : JSON.stringify({opc : opcCrud, operadora : operadora2}),
        success : function(data) {
            operadora.indError = data.indError;
            operadora.msjError = data.msjError;
        }
    });
    return operadora;
}

function listOperadoras(opcListado){
    var listOperadoras = new ListOperadoras();
    var operadora2 ={
        "idOperadora": this.idOperadora,
        "habilitado": this.habilitado
    };
    $.ajax({
        url : "listOperadoras.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : JSON.stringify({opc : opcListado, operadora : operadora2}),
        success : function(data) {
            listOperadoras = new ListOperadoras(data.listaOperadoras, data.indError, data.msjError);
        }
    });
    return listOperadoras;
}

