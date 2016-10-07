/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function EstadoCivil(idEstadoCivil, denominacion, abbr){
    this.idEstadoCivil = idEstadoCivil;
    this.denominacion = denominacion;
    this.abbr = abbr;
    this.mantEstadoCivil = mantEstadoCivil;
    this.listEstadosCiviles = listEstadosCiviles;
}

function ListEstadoCivil(EstadosCiviles, indError, msjError){
    this.EstadosCiviles = EstadosCiviles;
    this.indError =indError;
    this.msjError = msjError;
}

function mantEstadoCivil(opcCrud){
    var estadoCivil;
    var estadoCivil2 ={
        "idEstadoCivil": this.idEstadoCivil,
        "denominacion": this.denominacion,
        "abbr" : this.abbr,
        "habilitado": this.habilitado
    };
    estadoCivil = new EstadoCivil();
    $.ajax({
        url : "mantEstadoCivil.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : JSON.stringify({opc : opcCrud, estadoCivil : estadoCivil2}),
        success : function(data) {
            estadoCivil.indError = data.indError;
            estadoCivil.msjError = data.msjError;
        }
    });
    return estadoCivil;
}

function listEstadosCiviles(opcListado){
    var listEstadoCivil = new ListEstadoCivil();
    var estadoCivil2 ={
        "idEstadoCivil": this.idEstadoCivil,
        "habilitado": this.habilitado
    }; 
    $.ajax({
        url : "listEstadosCiviles.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : JSON.stringify({opc : opcListado, estadoCivil : estadoCivil2}),
        success : function(data) {
            listEstadoCivil = new ListEstadoCivil(data.listaEstadosCiviles, data.indError, data.msjError);
        }
    });
    return listEstadoCivil;
}

