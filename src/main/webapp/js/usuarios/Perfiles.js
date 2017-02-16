/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function Perfiles(idPerfil, descripcion, estado){
    this.idPerfil = idPerfil;
    this.descripcion = descripcion;
    this.estado = estado;
    this.indError = "";
    this.msjError = "";
    this.listarPerfiles = listarPerfiles;
    this.mantPerfiles = mantPerfiles;
}

function ListPerfil(listPerfil, indError, msjError){
    this.listPerfil = listPerfil;
    this.indError = indError;
    this.msjError = msjError;
}

function listarPerfiles(tipoOpe){
    var listPerfiles = new ListPerfil();
    var perfil2 = {
        "idPerfil" : this.idPerfil,
        "estado" : this.estado
    }; 
    $.ajax({
        url : "../usuarios/listarPerfil.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : JSON.stringify({tipoOperacion: tipoOpe, perfil : perfil2}),
        success : function(data) {
            listPerfiles = new ListPerfil(data.listPerfil, data.indError, data.msjError);
        }
    });
    return listPerfiles;
}

function mantPerfiles(tipoOpe){
    var perfilRep = new Perfiles();
    var perfil2 = {
        "idPerfil" : this.idPerfil,
        "estado" : this.estado,
        "descripcion" : this.descripcion
    }; 
    $.ajax({
        url : "../usuarios/mantPerfiles.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : JSON.stringify({tipoOperacion: tipoOpe, perfil : perfil2}),
        success : function(data) {
            perfilRep.indError = data.indError;
            perfilRep.msjError = data.msjError;
        }
    });
    return perfilRep;
}

