/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var listaUsuarios;
var idUsuario;
var listaMenus;
$(document).ready(function() {
    $(".divDetalle").hide();

    $("#btnAtras").click(function(){
        $(".divDetalle").hide();
        $(".divPerfiles").show();
    });
    
    $("#btnGenerarMenu").on('click', function(){
       generarMenuPerfil(); 
    });
    listarTodosUsuarios();
});

var mostrarMenuPerfil = function(index){
    listarMenusUsuario(index);
    $(".divPerfiles").hide();
    $(".divDetalle").show();
    $("#tituloPanelMenu").text("MENUS DE USUARIO: "+listaUsuarios[index].nombre);
};

var listarTodosUsuarios = function(){
    var usuario;
    var listUsuarios;
    var  i = 0;
    var $tr, $td, $button, $span, $body;
    usuario = new Usuarios();
    usuario.username = "";
    listUsuarios = usuario.listarUsuarios(3);
    $("#divTabla").html(
        '<table id="tablaPerfiles" class="table table-bordered table-hover">' +
            '<thead>' +
                '<th class="text-center">Usuario</th>' +
                '<th class="text-center">Nombre</th>' +
                '<th class="text-center">Menu</th>' +
            '</thead>' +
            '<tbody id="bodytablaPerfiles"></tbody>'+
        '</table>'
    );
    $body = $('#bodytablaPerfiles');
    listaUsuarios = listUsuarios.listUsuarios;
    for (i = 0; i < listaUsuarios.length; i++){
        $tr = $("<tr/>");
        $td = $("<td/>",{
           "class" : "text-center",
           "text" : listaUsuarios[i].username
        });
        $tr.append($td);
        
        $td = $("<td/>",{
           "class" : "text-center",
           "text" : listaUsuarios[i].nombre
        });
        $tr.append($td);
        
        $td = $('<td class="text-center">' +
                    '<button type="button" class="btn btn-primary"'+
                    'onclick="mostrarMenuPerfil('+i+')">' +
                        '<span class="glyphicon glyphicon-list-alt"></span>' +
                    '</button>' +
                '</td>');
        $tr.append($td);
        $body.append($tr);
    }
    $("#tablaPerfiles").dataTable();
};

var listarMenusUsuario = function(index){
    var $perror = $("#panelError");
    var usuario = new Usuarios();
    var menu = new Menus();
    var listMenus;
    var i = 0;
    var $tr, $td, $body;
    idUsuario = listaUsuarios[index].username;
    usuario.username = idUsuario;
    listMenus = menu.listarMenus(2, new Perfiles() , usuario);
    listaMenus = listMenus.listMenus;
    $perror.html("");
    if(listMenus.indError === 0){
        $("#divMenu").html(
            '<table id="tablaPerfilesMenu" class="table table-bordered table-hover">'+
                '<thead>'+
                    '<th class="text-center">Cod. Menu</th>'+
                    '<th class="text-center">Descripcion</th>'+
                    '<th class="text-center">Rol</th>'+
                    '<th class="text-center">Seleccionar</th>'+
                '</thead>'+
                '<tbody id="bodytablaPerfilesMenu">'+
                '</tbody>'+
            '</table>');
        $body = $("#bodytablaPerfilesMenu");    
        for(i = 0; i < listaMenus.length; i++){
            $tr = $("<tr/>");
            $td = $("<td/>",{
               "class" : "text-center",
               "text" : listaMenus[i].idMenu
            });
            $tr.append($td);
            
            $td = $("<td/>",{
               "class" : "text-center",
               "text" : listaMenus[i].descripMenu
            });
            $tr.append($td);
            
            $td = $("<td/>",{
               "class" : "text-center",
               "text" : listaMenus[i].descripRol
            });
            $tr.append($td);
            
            if(listaMenus[i].estado === 0){
                $td = $('<td class="text-center">'+
                            '<input type="checkbox" id="chkMenuPerfil'+i+'" onchange="mantMenuIndividual('+i+')"/>'+
                        '</td>');
            } else {
                $td = $('<td class="text-center">'+
                            '<input type="checkbox" id="chkMenuPerfil'+i+'" onchange="mantMenuIndividual('+i+')"'+
                            ' checked = "cheked"/>'+
                        '</td>');
            }
            $tr.append($td);
            
            $body.append($tr);
        }
        $("#tablaPerfilesMenu").dataTable();
    } else {
        $perror.html('<div class="alert alert-danger">' +
                        '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>' +
                        '<strong>'+listMenus.msjError+'</strong>.'+
                     '</div>');
    }
};

var mantMenuIndividual = function(index){
    var $perror = $("#panelError");
    var usuario = new Usuarios();
    var menu = new Menus();
    var menuRep;
    var ope;
    usuario.username = idUsuario;
    menu.idMenu = listaMenus[index].idMenu;
    if($("#chkMenuPerfil"+index).is(":checked")){
        ope = 3;
        menuRep = menu.mantMenu(3, new Perfiles(), usuario);
    } else {
        ope = 4;
        menuRep = menu.mantMenu(4, new Perfiles(), usuario);
    }
    
    if(menuRep.indError === 0){
        $perror.html('<div class="alert alert-success">' +
                        '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>' +
                        '<strong>'+menuRep.msjError+'</strong>.'+
                     '</div>');
    } else {
        $perror.html('<div class="alert alert-danger">' +
                        '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>' +
                        '<strong>'+menuRep.msjError+'</strong>.'+
                     '</div>');
        if(ope === 3){
            $("#chkMenuPerfil"+index).prop("checked", false);
        } else {
            $("#chkMenuPerfil"+index).prop("checked", true);
        }    
    }
};

var generarMenuPerfil = function(){
    var $perror = $("#panelError");
    var perfil = new Perfiles();
    var menu = new Menus();
    var menuRep;
    perfil.idPerfil = 0;
    menuRep = menu.generarMenu(perfil, idUsuario);
    if(menuRep.indError === 0){
        $perror.html('<div class="alert alert-success">' +
                        '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>' +
                        '<strong>'+menuRep.msjError+'</strong>.'+
                     '</div>');
        $(".divDetalle").hide();
        $(".divPerfiles").show();     
    } else {
        $perror.html('<div class="alert alert-danger">' +
                        '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>' +
                        '<strong>'+menuRep.msjError+'</strong>.'+
                     '</div>');
    }
};