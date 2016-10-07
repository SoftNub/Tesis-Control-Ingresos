/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function TipoEquipo(idTipoEquipo, denominacion, habilitado){
    this.idTipoEquipo = idTipoEquipo;
    this.denominacion = denominacion;
    this.habilitado = habilitado;
    this.mantTipoEquipo = mantTipoEquipo;
    this.listTipoEquipo = listTipoEquipo;
}

function ListTipoEquipo(TiposEquipos, indError, msjError){
    this.TiposEquipos = TiposEquipos;
    this.indError =indError;
    this.msjError = msjError;
}

function mantTipoEquipo(opcCrud){
    var tipoEquipo;
    var tipoEquipo2 ={
        "idTipoEquipo": this.idTipoEquipo,
        "denominacion": this.denominacion,
        "habilitado": this.habilitado
    };
    tipoEquipo = new TipoEquipo();
    $.ajax({
        url : "mantTipoEquipo.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : JSON.stringify({opc : opcCrud, tipoEquipo : tipoEquipo2}),
        success : function(data) {
            tipoEquipo.indError = data.indError;
            tipoEquipo.msjError = data.msjError;
        }
    });
    return tipoEquipo;
}

function listTipoEquipo(opcListado){
    var listTipoEquipo = new ListTipoEquipo();
    var tipoEquipo2 ={
        "idTipoEquipo": this.idTipoEquipo,
        "habilitado": this.habilitado
    };
    $.ajax({
        url : "listTipoEquipo.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : JSON.stringify({opc : opcListado, tipoEquipo : tipoEquipo2}),
        success : function(data) {
            listTipoEquipo = new ListTipoEquipo(data.listaTiposEquipos, data.indError, data.msjError);
        }
    });
    return listTipoEquipo;
}

