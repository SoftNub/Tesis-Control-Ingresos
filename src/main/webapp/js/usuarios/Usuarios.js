/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function Usuarios(username, nombre, tipo, apellidos, id_perfil, habilitado,
        es_usuario, password){
    this.username = username;
    this.nombre = nombre;
    this.tipo = tipo;
    this.apellidos = apellidos;
    this.password = password;
    this.id_perfil = id_perfil;
    this.habilitado = habilitado;
    this.es_usuario = es_usuario;
    this.indError = "";
    this.msjError = "";
    this.listarUsuarios = listarUsuarios;
    this.mantUsuarios = mantUsuarios;
}

function ListUsuarios(listUsuarios, indError, msjError){
    this.listUsuarios = listUsuarios;
    this.indError = indError;
    this.msjError = msjError;
}

function listarUsuarios(tipoOpe){
    var listUsuarios = new ListUsuarios();
    var usuario2 = {
        "username" : this.username
    }; 
    $.ajax({
        url : "../usuarios/listarUsuarios.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : JSON.stringify({tipoOperacion: tipoOpe, usuario : usuario2}),
        success : function(data) {
            listUsuarios = new ListUsuarios(data.listUsuarios, data.indError, data.msjError);
        }
    });
    return listUsuarios;
}

function mantUsuarios(tipoOpe){
    var usuarioRep = new Usuarios();
    var usuario2 = {
        "username" : this.username,
        "password" : this.password,
        "habilitado" : this.habilitado,
        "id_perfil" : this.id_perfil,
        "tipo" : this.tipo
    }; 
    $.ajax({
        url : "../usuarios/mantUsuarios.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : JSON.stringify({tipoOperacion: tipoOpe, usuario : usuario2}),
        success : function(data) {
            usuarioRep.indError = data.indError;
            usuarioRep.msjError = data.msjError;
        }
    });
    return usuarioRep;
}

