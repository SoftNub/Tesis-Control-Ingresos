/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var usuarioSelect;
var listaPerfiles;
$(document).ready(function(){
    $(".divDetalle").hide();
    $("#btnBuscar").on("click", function(){
        buscarUsuarios();
    });
    
    $("#btnAtras").on("click", function(){
        limpiarFormulario();
    });
    
    $("#btnAceptar").on("click", function(){
        mantenimientoUsuarios();
    });
    
    listarTodosPerfiles();
});

var buscarUsuarios = function(){
    var usuario;
    var listUsuarios;
    var $perror = $("#panelError");
    var opcion = $("#cboOpcionBusqueda").val();
    var dniNumColeg = $("#txtDNINumColeg").val();
    $(".divDetalle").hide();
    $perror.html("");
    var expRegDni = /[0-9]{8}/;
    var i = 0;
    if(opcion === ""){
        $perror.html('<div class="alert alert-danger">' +
                        '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>' +
                        '<strong>Debe Seleccionar tipo de usuario</strong>.'+
                     '</div>');
        return;
    }
    
    if(dniNumColeg === ""){
        $perror.html('<div class="alert alert-danger">' +
                        '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>' +
                        '<strong>Debe Ingresar DNI o Numero Colegiatura</strong>.'+
                     '</div>');
        return;
    }
    
    if(opcion === "1"){
        if (dniNumColeg.length < 8 || dniNumColeg.length > 9){
            mostrarMensaje('danger','[Error: El DNI solo debe tener 8 caracteres]');
            return;
        }

        if(!expRegDni.test(dniNumColeg)){
            mostrarMensaje('danger','[Error: El DNI debe tener 8 numeros]');
            return;
        }
        
        usuario = new Usuarios();
        usuario.username = dniNumColeg;
        listUsuarios = usuario.listarUsuarios(1);
    } else if (opcion === "2"){
        var expRegNumColegiatura = /^[0-9]{1,10}$/; //^[0-9]*$
        if (dniNumColeg === '') {
            mostrarMensaje('danger', '[Error: El Numero Colegiatura no puede ser vacio]');
            return;
        }

        if (dniNumColeg.length < 1 || dniNumColeg.length > 10){
            mostrarMensaje('danger', '[Error: El Numero Colegiatura acepta maximo 10 numeros]');
            return;
        }

        if(!expRegNumColegiatura.test(dniNumColeg)){
            mostrarMensaje('danger', '[Error: El Numero Colegiatura solo acepta numeros]');
            return;
        }
        usuario = new Usuarios();
        usuario.username = dniNumColeg;
        listUsuarios = usuario.listarUsuarios(2);
    }
    
    if(listUsuarios.indError === 0){
        usuarioSelect = listUsuarios.listUsuarios[0];
        if(usuarioSelect.es_usuario === 0){
            $("#txtNombre").text(usuarioSelect.nombre);
            $("#txtApellidos").text(usuarioSelect.apellidos);
            $("#txtTipoUsuario").text(usuarioSelect.tipo);
            $("#cboPerfil").val("");
            $("#txtUsuario").text(usuarioSelect.username);
            $("#txtPassword").text("");
            $("#chkEstado").prop("checked", false);
            $(".divDetalle").show();
        } else {
            $("#txtNombre").text(usuarioSelect.nombre);
            $("#txtApellidos").text(usuarioSelect.apellidos);
            $("#txtTipoUsuario").text(usuarioSelect.tipo);
            
            for(i  = 0; i < listaPerfiles.length; i++){
                if(listaPerfiles[i].idPerfil === usuarioSelect.id_perfil){
                    break;
                }
            }
            $("#cboPerfil").val(i);
            
            $("#txtUsuario").text(usuarioSelect.username);
            $("#txtPassword").text(""); 
            if(usuarioSelect.habilitado === 0){
                $("#chkEstado").prop("checked", false);
            } else {
                $("#chkEstado").prop("checked", true);
            }
            $(".divDetalle").show();
        }
    } else {
        mostrarMensaje('danger', listUsuarios.msjError);
    }

};

var listarTodosPerfiles = function(){
    var perfil;
    var listPerfiles;
    var  i = 0;
    var $option;
    perfil = new Perfiles();
    perfil.idPerfil = 0;
    perfil.estado = 1;
    listPerfiles = perfil.listarPerfiles(3);
    $("#cboPerfil").html(
            '<option value="">--Seleccione--</option>'
    );
    listaPerfiles = listPerfiles.listPerfil;
    for (i = 0; i < listaPerfiles.length; i++){
        $option = $("<option/>",{
            "value" : i,
            "text" : listaPerfiles[i].descripcion
        });
        $("#cboPerfil").append($option);
    }
};


function mostrarMensaje(tipo,mensaje){
    var htmlAlert;
    var $strong;
    htmlAlert = '<div class="alert alert-'+tipo+'">';
    htmlAlert += '<a href="#" class="close" data-dismiss="alert"'
            + 'aria-label="close">&times;</a></div>';
    $("#panelError").html(htmlAlert);
    $strong = $("<strong/>",{
        text : mensaje
    });
    $("#panelError div").append($strong);
    $("#panelError").show();
}

var limpiarFormulario = function(){
    $(".divDetalle").hide();
    $("#panelError").html("");
    $("#cboOpcionBusqueda").val("");
    $("#txtDNINumColeg").val("");
    $("#txtNombre").text("");
    $("#txtApellidos").text("");
    $("#txtTipoUsuario").text("");
    $("#cboPerfil").val("");
    $("#txtUsuario").text("");
    $("#txtPassword").val("");
    $("#chkEstado").prop("checked",false);
    usuarioSelect = undefined;
};

var mantenimientoUsuarios = function(){
    var usuario;
    var usuarioRep;
    var pass;
    var cod_perfil;
    var estado;
    usuario = new Usuarios();
    cod_perfil = $("#cboPerfil").val();
    if(cod_perfil === ""){
        mostrarMensaje('danger', 'Debe seleccionar un perfil');
        return;
    }

    if(usuarioSelect.es_usuario === 1){
        usuario.username = usuarioSelect.username;
        pass = $("#txtPassword").val();
        if(pass !== ''){
            usuario.password = pass;
        } else {
            usuario.password = '';
        }
        
        if($("#chkEstado").is(":checked")){
            estado = 1;
        } else {
            estado  = 0;
        }
        
        usuario.habilitado = estado;
        usuario.id_perfil = listaPerfiles[cod_perfil].idPerfil;
        usuarioRep = usuario.mantUsuarios(2);
    } else {
        pass = $("#txtPassword").val();
        if(pass === ''){
            mostrarMensaje('danger', 'Debe ingresar una contraseña');
            return;
        }
        
        if($("#chkEstado").is(":checked")){
            estado = 1;
        } else {
            estado  = 0;
        }
        usuario.username = usuarioSelect.username;
        usuario.tipo = usuarioSelect.tipo;
        usuario.password = pass;
        usuario.habilitado = estado;
        usuario.id_perfil = listaPerfiles[cod_perfil].idPerfil;
        usuarioRep = usuario.mantUsuarios(1);
    }
    
    if(usuarioRep.indError === 0){
        mostrarMensaje('success', usuarioRep.msjError);
    } else {
        mostrarMensaje('danger', usuarioRep.msjError);
    }
}; 
