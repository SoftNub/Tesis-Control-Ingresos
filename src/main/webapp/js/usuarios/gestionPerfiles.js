/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var listaPerfiles;
var idPerfil;
var listaMenus;
$(document).ready(function() {
    $(".divDetalle").hide();

    $("#btnAtras").click(function(){
        $(".divDetalle").hide();
        $(".divPerfiles").show();
    });
    
    $("#btnCrearPerfil").on('click', function(){
        grabarPerfil();
    });
    
    $("#btnEditar").on('click', function(){
        var $perror = $("#panelError");
        var $perrorInt = $("#panelErrorInt");
        var perfilRep;
        var perfil = new Perfiles();
        perfil.idPerfil = idPerfil;
        perfil.descripcion = $("#txtDescripcion").val();
        if(perfil.descripcion === ''){
            $perrorInt.html('<div class="alert alert-danger">' +
                        '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>' +
                        '<strong>Error!</strong> Debe Ingresar Descripcion.'+
                     '</div>');
            return;
        }
        if ($("#chkEstado").is(":checked")) {
            perfil.estado = 1;
        } else {
            perfil.estado = 0;
        }
        perfilRep = perfil.mantPerfiles(2);
        if (perfilRep.indError === 0) {
            $perror.html('<div class="alert alert-success">' +
                        '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>' +
                        '<strong>'+perfilRep.msjError+'</strong>.'+
                     '</div>');
            listarTodosPerfiles();
            $("#myModal").modal("hide");
        } else {
            $perrorInt.html('<div class="alert alert-danger">' +
                        '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>' +
                        '<strong>'+perfilRep.msjError+'</strong>.'+
                     '</div>');
        }
    });
    
    $("#btnGenerarMenu").on('click', function(){
       generarMenuPerfil(); 
    });
    listarTodosPerfiles();
});

var mostrarMenuPerfil = function(index){
    listarMenusPerfil(index);
    $(".divPerfiles").hide();
    $(".divDetalle").show();
    $("#tituloPanelMenu").text("MENUS DE PERFIL: "+listaPerfiles[index].descripcion);
};

var listarTodosPerfiles = function(){
    var perfil;
    var listPerfiles;
    var  i = 0;
    var $tr, $td, $button, $span, $body;
    perfil = new Perfiles();
    perfil.idPerfil = 0;
    perfil.estado = -1;
    listPerfiles = perfil.listarPerfiles(1);
    $("#divTabla").html(
        '<table id="tablaPerfiles" class="table table-bordered table-hover">' +
            '<thead>' +
                '<th class="text-center">Cod. Perfil</th>' +
                '<th class="text-center">Descripcion</th>' +
                '<th class="text-center">Estado</th>' +
                '<th class="text-center">Editar</th>' +
                '<th class="text-center">Menu</th>' +
            '</thead>' +
            '<tbody id="bodytablaPerfiles"></tbody>'+
        '</table>'
    );
    $body = $('#bodytablaPerfiles');
    listaPerfiles = listPerfiles.listPerfil;
    for (i = 0; i < listaPerfiles.length; i++){
        $tr = $("<tr/>");
        $td = $("<td/>",{
           "class" : "text-center",
           "text" : listaPerfiles[i].idPerfil
        });
        $tr.append($td);
        
        $td = $("<td/>",{
           "class" : "text-center",
           "text" : listaPerfiles[i].descripcion
        });
        $tr.append($td);
        
        if(listaPerfiles[i].estado === 1) {
            $td = $('<td class="text-center"><span class="label label-success">'+
                'Habilitado</span></td>');
        } else {
            $td = $('<td class="text-center"><span class="label label-danger">'+
                'Inhabilitado</span></td>');
        }
        $tr.append($td);
        
        $td = $('<td class="text-center">' +
                    '<button type="button" class="btn btn-primary"'+ 
                    'data-toggle="modal" onclick = "mostrarEditar('+i+')">' + //data-target="#myModal
                        '<span class="glyphicon glyphicon-edit"></span>' +
                    '</button>' +
                '</td>');
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

var grabarPerfil = function(){
    var $perror = $("#panelError");
    var perfil, perfilRep;
    $perror.html('');
    var descrip = $("#txtDescripcionPerfil").val();
    if(descrip === ''){
        $perror.html('<div class="alert alert-danger">' +
                        '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>' +
                        '<strong>Error!</strong> Debe Ingresar Descripcion.'+
                     '</div>');
        return;
    }
    
    perfil = new Perfiles();
    perfil.descripcion = descrip;
    perfil.estado = 1;
    perfilRep = perfil.mantPerfiles(1);
    if(perfilRep.indError === 0){
        $perror.html('<div class="alert alert-success">' +
                        '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>' +
                        '<strong>'+perfilRep.msjError+'</strong>.'+
                     '</div>');
        listarTodosPerfiles();
        $("#txtDescripcionPerfil").val('');
    } else {
        $perror.html('<div class="alert alert-danger">' +
                        '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>' +
                        '<strong>'+perfilRep.msjError+'</strong>.'+
                     '</div>');
    }
};

var mostrarEditar = function(index){
    limpiarModal();
    listarPerfilEditar(index);
    $("#myModal").modal({backdrop: false});
};

var limpiarModal = function(){
    $("#txtDescripcion").val("");
    $("#chkEstado").prop("checked", false);
};

var listarPerfilEditar = function(index){
    var $perror = $("#panelError");
    var regPerfil = listaPerfiles[index];
    var perfil = new Perfiles();
    perfil.idPerfil = regPerfil.idPerfil;
    perfil.estado = -1;
    var listPerfil = perfil.listarPerfiles(2);
    if(listPerfil.indError === 0){
        $("#txtDescripcion").val(listPerfil.listPerfil[0].descripcion);
        if(listPerfil.listPerfil[0].estado === 0){
            $("#chkEstado").prop("checked", false);
        } else {
            $("#chkEstado").prop("checked", true);
        }
        idPerfil = listPerfil.listPerfil[0].idPerfil;
    } else {
        $perror.html('<div class="alert alert-danger">' +
                        '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>' +
                        '<strong>'+listPerfil.msjError+'</strong>.'+
                     '</div>');
    }
};

var listarMenusPerfil = function(index){
    var $perror = $("#panelError");
    var perfil = new Perfiles();
    var menu = new Menus();
    var listMenus;
    var i = 0;
    var $tr, $td, $body;
    idPerfil = listaPerfiles[index].idPerfil;
    perfil.idPerfil = idPerfil;
    listMenus = menu.listarMenus(1, perfil, new Usuarios());
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
                            '<input type="checkbox" id="chkMenuPerfil'+i+'" onchange="mantMenuPerfil('+i+')"/>'+
                        '</td>');
            } else {
                $td = $('<td class="text-center">'+
                            '<input type="checkbox" id="chkMenuPerfil'+i+'" onchange="mantMenuPerfil('+i+')"'+
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

var mantMenuPerfil = function(index){
    var $perror = $("#panelError");
    var perfil = new Perfiles();
    var menu = new Menus();
    var menuRep;
    perfil.idPerfil = idPerfil;
    menu.idMenu = listaMenus[index].idMenu;
    if($("#chkMenuPerfil"+index).is(":checked")){
        menuRep = menu.mantMenu(1, perfil, new Usuarios());
    } else {
        menuRep = menu.mantMenu(2, perfil, new Usuarios());
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
    }
};

var generarMenuPerfil = function(){
    var $perror = $("#panelError");
    var perfil = new Perfiles();
    var menu = new Menus();
    var menuRep;
    perfil.idPerfil = idPerfil;
    menuRep = menu.generarMenu(perfil, '');
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