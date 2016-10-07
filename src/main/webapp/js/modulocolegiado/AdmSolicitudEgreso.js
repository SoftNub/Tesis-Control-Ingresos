/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    habilitarBotonesCrud(true, true, false);
    
    $("#cboOpcionBusqueda").focus();
//    $("#cboOpcionBusqueda").on("focusout",function(){
//       
//    });
    
    $("#txtDniNumColegiatura").on("focusout",function(){
       buscarPersonaColegiado(); 
    });
    
    $("#Opcgrabar").on("click", function(){
       grabarSolicitudEgresoColegiatura(); 
    });
    
    
    $("#OpcCancelar").on("click", function(){
       limpiarTodo();
       //deshabilitarFormulario(false);
       habilitarBotonesCrud(true, true, false);
       $("#panelError").html("");
       $("#txtDniNumColegiatura").val("");
       $("#txtDniNumColegiatura").prop("disabled", false);
       $("#cboOpcionBusqueda").val("1");
       $("#cboOpcionBusqueda").prop("disabled", false);
       $("#cboOpcionBusqueda").focus();
    });
});

function buscarPersonaColegiado(){
    var dni, opcionBusq, expRegDni;
    var htmlAlert, $strong;
    var colegiado;
    dni = $("#txtDniNumColegiatura").val();
    opcionBusq = $("#cboOpcionBusqueda").val();
    expRegDni = /[0-9]{8}/;
    $("#panelError").html("");
    //validacion del dni
    colegiado = new Colegiado();
    if(opcionBusq === '1'){
        if (dni === '') {
            mostrarMensaje('danger','[Error: El DNI no puede ser vacio]');
            $("#txtDniNumColegiatura").focus();
            return;
        }

        if (dni.length < 8 || dni.length > 9){
            mostrarMensaje('danger','[Error: El DNI solo debe tener 8 caracteres]');
            $("#txtDniNumColegiatura").focus();
            return;
        }

        if(!expRegDni.test(dni)){
            mostrarMensaje('danger','[Error: El DNI debe tener 8 numeros]');
            $("#txtDniNumColegiatura").focus();
            return;
        }
        colegiado.dni = dni;
        colegiado.numColegiatura = '';
    } else {
        var expRegNumColegiatura = /^[0-9]{1,10}$/; //^[0-9]*$
        if (dni === '') {
            mostrarMensaje('danger', '[Error: El Numero Colegiatura no puede ser vacio]');
            $("#txtDniNumColegiatura").focus();
            return;
        }

        if (dni.length < 1 || dni.length > 10){
            mostrarMensaje('danger', '[Error: El Numero Colegiatura acepta maximo 10 numeros]');
            $("#txtDniNumColegiatura").focus();
            return;
        }

        if(!expRegNumColegiatura.test(dni)){
            mostrarMensaje('danger', '[Error: El Numero Colegiatura solo acepta numeros]');
            $("#txtDniNumColegiatura").focus();
            return;
        }
        colegiado.dni = '';
        colegiado.numColegiatura = dni;
    }

    colegiadoRep = colegiado.buscarPersonaColegiadoInscripIngre(4);
   

    if (colegiadoRep.indError === 0) {
        if(colegiadoRep.esColegiado === 1){
            llenarFormularioColegiado(colegiadoRep);
            habilitarBotonesCrud(false, false, false);
            $("#cboOpcionBusqueda").prop("disabled", true);
            $("#txtDniNumColegiatura").prop("disabled", true);
            $("#Opcgrabar").focus();
        } 
    } else {
        mostrarMensaje('danger',colegiadoRep.msjError);
        $("#txtDniNumColegiatura").focus();
    }
}

 function llenarFormularioColegiado(colegiadoRep){
     $("#lblApePaterno").text(colegiadoRep.apePaterno);
     $("#lblApeMaterno").text(colegiadoRep.apeMaterno);
     $("#lblNombres").text(colegiadoRep.nombres);
     $("#lblFechaInscripcion").text(colegiadoRep.fechaInscripcion);
     $("#lblFechaIngreso").text(colegiadoRep.fechaIngreso);
 }

function habilitarBotonesCrud(grabar, editar, cancelar){
    $("#Opcgrabar").prop("disabled", grabar);
//    $("#Opceditar").prop("disabled", editar);
    $("#OpcCancelar").prop("disabled", cancelar);
}

function grabarSolicitudEgresoColegiatura(){
    var indBusqueda, dni, numColegiatura;
    var htmlAlert;
    /*validacion de informacion*/
    $("#panelError").html("");
    indBusqueda = $("#cboOpcionBusqueda").val();
    
    if(indBusqueda === '1'){
        dni = $("#txtDniNumColegiatura").val();
        var expRegDni = /[0-9]{8}/;
        //validacion del dni
        if (dni === '') {
            mostrarMensaje('danger','[Error: El DNI no puede ser vacio]');
            $("#txtDniNumColegiatura").focus();
            return;
        }

        if (dni.length < 8 || dni.length > 9){
            htmlAlert = '<div class="alert alert-danger"><a href="#" class="close"'
                    + 'data-dismiss="alert" aria-label="close">&times;</a>'
                    + '<strog>[Error: El DNI solo debe tener 8 caracteres]'
                    + '</strong></div>';
            $("#panelError").html(htmlAlert);
            $("#panelError").show();
            mostrarMensaje('danger', '[Error: El DNI solo debe tener 8 numeros]');
            $("#txtDniNumColegiatura").focus();
            return;
        }

        if(!expRegDni.test(dni)){
            mostrarMensaje('danger','[Error: El DNI solo acepta numeros]');
            $("#txtDniNumColegiatura").focus();
            return;
        }
    } else {
        numColegiatura = $("#txtDniNumColegiatura").val();
        var expRegNumColegiatura = /^[0-9]{1,10}$/; //^[0-9]*$
        //validacion del dni
        if (numColegiatura === '') {
            mostrarMensaje('danger', '[Error: El Numero Colegiatura no puede ser vacio]');
            $("#txtDniNumColegiatura").focus();
            return;
        }

        if (numColegiatura.length < 1 || numColegiatura.length > 10){
            mostrarMensaje('danger', '[Error: El Numero Colegiatura acepta maximo 10 numeros]');
            $("#txtDniNumColegiatura").focus();
            return;
        }

        if(!expRegNumColegiatura.test(numColegiatura)){
            mostrarMensaje('danger', '[Error: El Numero Colegiatura solo acepta numeros]');
            $("#txtDniNumColegiatura").focus();
            return;
        }
    }
    
    var colegiadoResp;
    colegiadoResp = colegiadoRep.grabarSolictudEgresoColegiatura();
    if(colegiadoResp.indError === 0){
        mostrarMensaje('success',colegiadoResp.msjError);
        limpiarTodo();
        habilitarBotonesCrud(true, true, false);
        $("#txtDniNumColegiatura").val("");
        $("#txtDniNumColegiatura").prop("disabled", false);
        $("#cboOpcionBusqueda").prop("disabled", false);
        $("#cboOpcionBusqueda").val("1");
        $("#cboOpcionBusqueda").focus();
    } else {
        mostrarMensaje('danger',colegiadoResp.msjError);
    }
}

function limpiarTodo(){
    $("#lblApePaterno").text("");
    $("#lblApeMaterno").text("");
    $("#lblNombres").text("");
    $("#lblFechaInscripcion").text("");
    $("#lblFechaIngreso").text("");
}

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

function visibleTab(tab){
    switch(tab){
        case 1:
            $('.nav-tabs a[href="#datosPersonales"]').tab('show');
            break;
        case 2:
            $('.nav-tabs a[href="#expLaboral"]').tab('show');
            break;
        case 3:
            $('.nav-tabs a[href="#datosFamiliares"]').tab('show');
            break;
        case 4:
            $('.nav-tabs a[href="#gradosTitulos"]').tab('show');
            break;
    }
}