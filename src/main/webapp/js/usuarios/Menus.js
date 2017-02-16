/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function Menus (idMenu, idRol, descripMenu, descripRol, estado){
    this.idMenu = idMenu;
    this.idRol = idRol;
    this.descripMenu = descripMenu;
    this.descripRol = descripRol;
    this.estado = estado;
    this.indError = "";
    this.msjError = "";
    this.listarMenus = listarMenus;
    this.mantMenu = mantMenu;
    this.generarMenu = generarMenu;
}

function ListMenus(listMenus, indError, msjError){
    this.listMenus = listMenus;
    this.indError = indError;
    this.msjError = msjError;
}

function listarMenus(tipoOpe, perfil, usuario){
    var listMenus = new ListMenus();
    var menus2 = {
        "idMenu" : this.idMenu
    }; 
    var perfil2 = {
        "idPerfil" : perfil.idPerfil
    }; 
    var usuario2 = {
        "username" : usuario.username
    };
    $.ajax({
        url : "../usuarios/listarMenus.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : JSON.stringify({tipoOperacion: tipoOpe, perfil : perfil2, menu : menus2,
                usuario : usuario2}),
        success : function(data) {
            listMenus = new ListMenus(data.listMenus, data.indError, data.msjError);
        }
    });
    return listMenus;
}

function mantMenu(tipoOpe, perfil, usuario){
    var menuRep = new Menus();
    var menus2 = {
        "idMenu" : this.idMenu
    }; 
    var perfil2 = {
        "idPerfil" : perfil.idPerfil
    }; 
    var usuario2 = {
        "username" : usuario.username
    };
    $.ajax({
        url : "../usuarios/mantMenu.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : JSON.stringify({tipoOperacion: tipoOpe, perfil : perfil2,
                menu : menus2, usuarioMenu : usuario2}),
        success : function(data) {
            menuRep.indError = data.indError;
            menuRep.msjError = data.msjError;
        }
    });
    return menuRep;
}

function generarMenu(perfil, usuario){
    var menuRep = new Menus();
    var perfil2 = {
        "idPerfil" : perfil.idPerfil
    }; 
    $.ajax({
        url : "../usuarios/generarMenu.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : JSON.stringify({ perfil : perfil2, usuarioMenu : usuario}),
        success : function(data) {
            menuRep.indError = data.indError;
            menuRep.msjError = data.msjError;
        }
    });
    return menuRep;
}


