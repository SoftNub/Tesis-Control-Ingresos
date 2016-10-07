/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    habilitarBotonesCrud(true, true, false);
    $("#txtDni").focus();
    $("#txtDni").on("focusout",function(){
       buscarPersona(); 
    });
    listarEstadosCiviles();
    listarDepartamentos();
    listarCondicionCasa();
    listarDistritos();
    listarProvincias();
    
    $("#cboDepartamentoOrigen").on("change",function(){
        var id = $("#cboDepartamentoOrigen").val();
        if (id !== '0'){
            listarProvinciasXDepartamento();
        } else {
            $('#cboProvinciaOrigen').html('<option value="0">--seleccione--</option>');
            $('#cboDistritoOrigen').html('<option value="0">--seleccione--</option>');
        }
    });
    
    $("#cboProvinciaOrigen").on("change",function(){
        var id = $('#cboProvinciaOrigen').val();
        if (id !== '0'){
            listarDistritosXProvincia();
        } else {
            $('#cboDistritoOrigen').html('<option value="0">--seleccione--</option>');
        }
    });
    
    $("#Opcgrabar").on("click", function(){
       grabarPreInscripcion(); 
    });
    
    $("#Opceditar").on("click", function(){
        deshabilitarFormulario(false);
        habilitarBotonesCrud(false, true, false);
        $("#txtApellidoPat").focus();
    });
    
    $("#OpcCancelar").on("click", function(){
       limpiarTodo();
       deshabilitarFormulario(false);
       habilitarBotonesCrud(true, true, false);
       $("#panelError").html("");
       $("#txtDni").val("");
       $("#txtDni").prop("disabled", false);
       $("#txtDni").focus();
    });
});

function buscarPersona(){
    var dni, expRegDni;
    var htmlAlert, $strong;
    var persona;
    dni = $("#txtDni").val();
    expRegDni = /[0-9]{8}/;
    $("#panelError").html("");
    //validacion del dni
    if (dni === '') {
        htmlAlert = '<div class="alert alert-danger"><a href="#" class="close"'
                + 'data-dismiss="alert" aria-label="close">&times;</a>'
                + '<strog>[Error: El DNI no puede ser vacio]'
                + '</strong></div>';
        $("#panelError").html(htmlAlert);
        $("#panelError").show();
        $("#txtDni").focus();
        return;
    }
    
    if (dni.length < 8 || dni.length > 9){
        htmlAlert = '<div class="alert alert-danger"><a href="#" class="close"'
                + 'data-dismiss="alert" aria-label="close">&times;</a>'
                + '<strog>[Error: El DNI solo debe tener 8 caracteres]'
                + '</strong></div>';
        $("#panelError").html(htmlAlert);
        $("#panelError").show();
        $("#txtDni").focus();
        return;
    }
    
    if(!expRegDni.test(dni)){
        htmlAlert = '<div class="alert alert-danger"><a href="#" class="close"'
                + 'data-dismiss="alert" aria-label="close">&times;</a>'
                + '<strog>[Error: El DNI debe tener 8 numeros]'
                + '</strong></div>';
        $("#panelError").html(htmlAlert);
        $("#panelError").show();
        $("#txtDni").focus();
        return;
    }
    
    persona = new Persona();
    persona.dni = dni;
    personaRep = persona.buscarPersonaPreInscripcion(1);
    if (personaRep.indError === 0) {
        if (personaRep.indExiste === 1) {
            llenarFormulario(personaRep);
            deshabilitarFormulario(true);
            habilitarBotonesCrud(true, false, false);
            $("#txtDni").prop("disabled", true);
            $("#Opceditar").focus();
        } else {
            habilitarBotonesCrud(false, true, false);
            $("#txtDni").prop("disabled", true);
            $("#txtApellidoPat").focus();
        }
    } else {
        htmlAlert = '<div class="alert alert-danger">';
        htmlAlert += '<a href="#" class="close" data-dismiss="alert"'
                + 'aria-label="close">&times;</a></div>';
        $("#panelError").html(htmlAlert);
        $strong = $("<strong/>",{
            text : personaRep.msjError
        });
        $("#panelError div").append($strong);
        $("#panelError").show();
        $("#txtDni").focus();
    }
}

function llenarFormulario(persona){
    $("#txtApellidoPat").val(persona.apePaterno);
    $("#txtApellidoMat").val(persona.apeMaterno);
    $("#txtNombres").val(persona.nombres);
    $("#txtGrupoSan").val(persona.grupoSanguineo);
    $("#txtRh").val(persona.rh);
    $("#cboEstadoCivil").val(persona.idEstadoCivil);
    $("#txtFechaNacimiento").val(persona.fechaNacimiento);
    $("#cboPaisOrigen").val(persona.idPaisOri);
    $("#txtLibretaMilitar").val(persona.libretaMilitar);
    $("#txtCarnetExtranjeria").val(persona.carnetExt);
    $("#txtTarjetaIdentidad").val(persona.ffaa_pnp);
    $("#txtCarnetEssalud").val(persona.carnetEssalud);
    $("#cboDistritoActual").val(persona.idDistritoAct);
    $("#txtCodigoPostal").val(persona.codigoPostal);
    $("#txtDomicilioActual").val(persona.domicilio);
    $("#cboProvinciaActual").val(persona.idProvinciaAct);
    $("#cboCondCasa").val(persona.idCondicionCasa);
    $("#txtTelfDomicilio").val(persona.telfDomicilio);
    $("#txtEmail1").val(persona.emailPrincipal);
    $("#txtEmail2").val(persona.emailSecundario);
    $("#cboDepartamentoOrigen").val(persona.idDepartamentoOri);
    listarProvinciasXDepartamento();
    $("#cboProvinciaOrigen").val(persona.idProvinciaOri);
    listarDistritosXProvincia();
    $("#cboDistritoOrigen").val(persona.idDistritoOri);
}

function deshabilitarFormulario(band){
    $("#txtApellidoPat").prop("disabled", band);
    $("#txtApellidoMat").prop("disabled", band);
    $("#txtNombres").prop("disabled", band);
    $("#txtGrupoSan").prop("disabled", band);
    $("#txtRh").prop("disabled", band);
    $("#cboEstadoCivil").prop("disabled", band);
    $("#txtFechaNacimiento").prop("disabled", band);
    $("#cboPaisOrigen").prop("disabled", band);
    $("#cboDepartamentoOrigen").prop("disabled", band);
    $("#cboProvinciaOrigen").prop("disabled", band);
    $("#cboDistritoOrigen").prop("disabled", band);
    $("#txtLibretaMilitar").prop("disabled", band);
    $("#txtCarnetExtranjeria").prop("disabled", band);
    $("#txtTarjetaIdentidad").prop("disabled", band);
    $("#txtCarnetEssalud").prop("disabled", band);
    $("#cboDistritoActual").prop("disabled", band);
    $("#txtCodigoPostal").prop("disabled", band);
    $("#txtDomicilioActual").prop("disabled", band);
    $("#cboProvinciaActual").prop("disabled", band);
    $("#cboCondCasa").prop("disabled", band);
    $("#txtTelfDomicilio").prop("disabled", band);
    $("#txtEmail1").prop("disabled", band);
    $("#txtEmail2").prop("disabled", band);  
}
function listarEstadosCiviles(){
    var estadoCivil;
    var listEstadosCiviles;
    var htmlAlert, $strong;
    var i;
    var $option, $select;
    estadoCivil = new EstadoCivil();
    estadoCivil.habilitado = 1;
    estadoCivil.idEstadoCivil = 0;
    listEstadosCiviles = estadoCivil.listEstadosCiviles(3);
    if(listEstadosCiviles.indError === 0){
        $select = $("#cboEstadoCivil");
        $select.html("<option value='0'>--Seleccione--</option>");
        for(i = 0; i < listEstadosCiviles.EstadosCiviles.length; i++ ){
            $option =$("<option/>",{
               value : listEstadosCiviles.EstadosCiviles[i].idEstadoCivil,
               text : listEstadosCiviles.EstadosCiviles[i].denominacion
            });
            $select.append($option);
        }
    } else {
        htmlAlert = '<div class="alert alert-danger">';
        htmlAlert += '<a href="#" class="close" data-dismiss="alert"'
                + 'aria-label="close">&times;</a></div>';
        $("#panelError").html(htmlAlert);
        $strong = $("<strong/>",{
            text : listEstadosCiviles.msjError
        });
        $("#panelError div").append($strong);
        $("#panelError").show();
    }
}

function listarDepartamentos(){
    var departamento;
    var listDepartamentos;
    var htmlAlert, $strong;
    var i;
    var $option, $select;
    departamento = new Departamento();
    departamento.habilitado = 1;
    listDepartamentos = departamento.listarDepartamentosHabilitados();
    if(listDepartamentos.indError === 0){
        $select = $("#cboDepartamentoOrigen");
        $select.html("<option value='0'>--Seleccione--</option>");
        for(i = 0; i < listDepartamentos.Departamentos.length; i++ ){
            $option =$("<option/>",{
               value : listDepartamentos.Departamentos[i].idDepartamento,
               text : listDepartamentos.Departamentos[i].nombreDepartamento
            });
            $select.append($option);
        }
    } else {
        htmlAlert = '<div class="alert alert-danger">';
        htmlAlert += '<a href="#" class="close" data-dismiss="alert"'
                + 'aria-label="close">&times;</a></div>';
        $("#panelError").html(htmlAlert);
        $strong = $("<strong/>",{
            text : listDepartamentos.msjError
        });
        $("#panelError div").append($strong);
        $("#panelError").show();
    }
}

function listarCondicionCasa(){
    var condCasa;
    var listCondCasas;
    var htmlAlert, $strong;
    var i;
    var $option, $select;
    condCasa = new CondicionCasa();
    condCasa.habilitado = 1;
    listCondCasas = condCasa.listCondicionCasa(3);
    if(listCondCasas.indError === 0){
        $select = $("#cboCondCasa");
        $select.html("<option value='0'>--Seleccione--</option>");
        for(i = 0; i < listCondCasas.CondicionesCasa.length; i++ ){
            $option =$("<option/>",{
               value : listCondCasas.CondicionesCasa[i].idCondicion,
               text : listCondCasas.CondicionesCasa[i].denominacion
            });
            $select.append($option);
        }
    } else {
        htmlAlert = '<div class="alert alert-danger">';
        htmlAlert += '<a href="#" class="close" data-dismiss="alert"'
                + 'aria-label="close">&times;</a></div>';
        $("#panelError").html(htmlAlert);
        $strong = $("<strong/>",{
            text : listCondCasas.msjError
        });
        $("#panelError div").append($strong);
        $("#panelError").show();
    }
}

function listarDistritos(){
    var distrito;
    var listDistritos;
    var htmlAlert, $strong;
    var i;
    var $option, $select;
    distrito = new Distrito();
    distrito.habilitado = 1;
    listDistritos = distrito.listarDistritosHabilitados();
    if(listDistritos.indError === 0){
        $select = $("#cboDistritoActual");
        $select.html("<option value='0'>--Seleccione--</option>");
        for(i = 0; i < listDistritos.Distritos.length; i++ ){
            $option =$("<option/>",{
               value : listDistritos.Distritos[i].idDistrito,
               text : listDistritos.Distritos[i].nombreDistrito
            });
            $select.append($option);
        }
    } else {
        htmlAlert = '<div class="alert alert-danger">';
        htmlAlert += '<a href="#" class="close" data-dismiss="alert"'
                + 'aria-label="close">&times;</a></div>';
        $("#panelError").html(htmlAlert);
        $strong = $("<strong/>",{
            text : listDistritos.msjError
        });
        $("#panelError div").append($strong);
        $("#panelError").show();
    }
}

function listarProvincias(){
     var provincia;
    var listProvincia;
    var htmlAlert, $strong;
    var i;
    var $option, $select;
    provincia = new Provincia();
    provincia.habilitado = 1;
    listProvincia = provincia.listarProvinciasHabilitadas();
    if(listProvincia.indError === 0){
        $select = $("#cboProvinciaActual");
        $select.html("<option value='0'>--Seleccione--</option>");
        for(i = 0; i < listProvincia.Provincias.length; i++ ){
            $option =$("<option/>",{
               value : listProvincia.Provincias[i].idProvincia,
               text : listProvincia.Provincias[i].nombreProvincia
            });
            $select.append($option);
        }
    } else {
        htmlAlert = '<div class="alert alert-danger">';
        htmlAlert += '<a href="#" class="close" data-dismiss="alert"'
                + 'aria-label="close">&times;</a></div>';
        $("#panelError").html(htmlAlert);
        $strong = $("<strong/>",{
            text : listProvincia.msjError
        });
        $("#panelError div").append($strong);
        $("#panelError").show();
    }
}

function listarProvinciasXDepartamento(){
    var departamento;
    var listProvincias;
    var htmlAlert, $strong;
    var $option, $select;
    var i;
    var idDepartamento = parseInt($("#cboDepartamentoOrigen").val());
    departamento = new Departamento();
    departamento.idDepartamento = idDepartamento;
    listProvincias = departamento.listarProvinciaDeDepartamento();
    if (listProvincias.indError === 0){
        $select = $("#cboProvinciaOrigen");
        $select.html("<option value='0'>--Seleccione--</option>");
        for(i = 0; i < listProvincias.Provincias.length; i++ ){
            $option =$("<option/>",{
               value : listProvincias.Provincias[i].idProvincia,
               text : listProvincias.Provincias[i].nombreProvincia
            });
            $select.append($option);
        }
    } else {
        htmlAlert = '<div class="alert alert-danger">';
        htmlAlert += '<a href="#" class="close" data-dismiss="alert"'
                + 'aria-label="close">&times;</a></div>';
        $("#panelError").html(htmlAlert);
        $strong = $("<strong/>",{
            text : listProvincias.msjError
        });
        $("#panelError div").append($strong);
        $("#panelError").show();
    }
}

function listarDistritosXProvincia(){
    var provincia;
    var listDistritos;
    var htmlAlert, $strong;
    var i;
    var $option, $select;
    var idProvincia = parseInt($("#cboProvinciaOrigen").val());
    provincia = new Provincia();
    provincia.idProvincia = idProvincia;
    listDistritos = provincia.listarDistritosDeProvincia();
    if(listDistritos.indError === 0){
        $select = $("#cboDistritoOrigen");
        $select.html("<option value='0'>--Seleccione--</option>");
        for(i = 0; i < listDistritos.Distritos.length; i++ ){
            $option =$("<option/>",{
               value : listDistritos.Distritos[i].idDistrito,
               text : listDistritos.Distritos[i].nombreDistrito
            });
            $select.append($option);
        }
    } else {
        htmlAlert = '<div class="alert alert-danger">';
        htmlAlert += '<a href="#" class="close" data-dismiss="alert"'
                + 'aria-label="close">&times;</a></div>';
        $("#panelError").html(htmlAlert);
        $strong = $("<strong/>",{
            text : listDistritos.msjError
        });
        $("#panelError div").append($strong);
        $("#panelError").show();
    }
}

function habilitarBotonesCrud(grabar, editar, cancelar){
    $("#Opcgrabar").prop("disabled", grabar);
    $("#Opceditar").prop("disabled", editar);
    $("#OpcCancelar").prop("disabled", cancelar);
}

function grabarPreInscripcion(){
    var dni,apePaterno, apeMaterno, nombres, idEstadoCivil;
    var grupoSanguineo, rh, fechaNacimiento, idPaisOrigen;
    var idDepartamentoOrigen, idProvinciaOrigen, idDistritoOrigen;
    var numLibretaMilitar, numCarnetExtranjeria, numTarjFFAA, numCarnetEssalud;
    var domicilioActual, idDistritoActual, codigoPostal, idProvinciaActual;
    var idCondicionCasa, TelfDomicilio, emailPrin, emailSec;
    var htmlAlert;
    /*validacion de informacion*/
    $("#panelError").html("");
    
    dni = $("#txtDni").val();
    var expRegDni = /[0-9]{8}/;
    $("#panelError").html("");
    //validacion del dni
    if (dni === '') {
        htmlAlert = '<div class="alert alert-danger"><a href="#" class="close"'
                + 'data-dismiss="alert" aria-label="close">&times;</a>'
                + '<strog>[Error: El DNI no puede ser vacio]'
                + '</strong></div>';
        $("#panelError").html(htmlAlert);
        $("#panelError").show();
        $("#txtDni").focus();
        return;
    }
    
    if (dni.length < 8 || dni.length > 9){
        htmlAlert = '<div class="alert alert-danger"><a href="#" class="close"'
                + 'data-dismiss="alert" aria-label="close">&times;</a>'
                + '<strog>[Error: El DNI solo debe tener 8 caracteres]'
                + '</strong></div>';
        $("#panelError").html(htmlAlert);
        $("#panelError").show();
        $("#txtDni").focus();
        return;
    }
    
    if(!expRegDni.test(dni)){
        htmlAlert = '<div class="alert alert-danger"><a href="#" class="close"'
                + 'data-dismiss="alert" aria-label="close">&times;</a>'
                + '<strog>[Error: El DNI debe tener 8 numeros]'
                + '</strong></div>';
        $("#panelError").html(htmlAlert);
        $("#panelError").show();
        $("#txtDni").focus();
        return;
    }
    
    apePaterno = $("#txtApellidoPat").val();
    
    if (apePaterno === ''){
        htmlAlert = '<div class="alert alert-danger"><a href="#" class="close"'
                + 'data-dismiss="alert" aria-label="close">&times;</a>'
                + '<strog>[Error: El campo Apellido Paterno no puede ser vacio]'
                + '</strong></div>';
        $("#panelError").html(htmlAlert);
        $("#panelError").show();
        $("#txtApellidoPat").focus();
        return;
    }
    
    if (apePaterno.length > 50){
        htmlAlert = '<div class="alert alert-danger"><a href="#" class="close"'
                + 'data-dismiss="alert" aria-label="close">&times;</a>'
                + '<strog>[Error: El campo Apellido Paterno solo acepta hasta 50 caracteres]'
                + '</strong></div>';
        $("#panelError").html(htmlAlert);
        $("#panelError").show();
        $("#txtApellidoPat").focus();
        return;
    }
    
    apeMaterno = $("#txtApellidoMat").val();
    
    if (apeMaterno === ''){
        htmlAlert = '<div class="alert alert-danger"><a href="#" class="close"'
                + 'data-dismiss="alert" aria-label="close">&times;</a>'
                + '<strog>[Error: El campo Apellido Materno no puede ser vacio]'
                + '</strong></div>';
        $("#panelError").html(htmlAlert);
        $("#panelError").show();
        $("#txtApellidoMat").focus();
        return;
    }
    
    if (apeMaterno.length > 50){
        htmlAlert = '<div class="alert alert-danger"><a href="#" class="close"'
                + 'data-dismiss="alert" aria-label="close">&times;</a>'
                + '<strog>[Error: El campo Apellido Materno solo acepta hasta 50 caracteres]'
                + '</strong></div>';
        $("#panelError").html(htmlAlert);
        $("#panelError").show();
        $("#txtApellidoMat").focus();
        return;
    }
    
    nombres = $("#txtNombres").val();
    
    if (nombres === ''){
        htmlAlert = '<div class="alert alert-danger"><a href="#" class="close"'
                + 'data-dismiss="alert" aria-label="close">&times;</a>'
                + '<strog>[Error: El campo Nombres no puede ser vacio]'
                + '</strong></div>';
        $("#panelError").html(htmlAlert);
        $("#panelError").show();
        $("#txtNombres").focus();
        return;
    }
    
    if (nombres.length > 100){
        htmlAlert = '<div class="alert alert-danger"><a href="#" class="close"'
                + 'data-dismiss="alert" aria-label="close">&times;</a>'
                + '<strog>[Error: El campo Nombres solo acepta hasta 100 caracteres]'
                + '</strong></div>';
        $("#panelError").html(htmlAlert);
        $("#panelError").show();
        $("#txtNombres").focus();
        return;
    }
    
    idEstadoCivil = parseInt($("#cboEstadoCivil").val());
    
    if (idEstadoCivil === 0 || Number.isNaN(idEstadoCivil)){
        htmlAlert = '<div class="alert alert-danger"><a href="#" class="close"'
                + 'data-dismiss="alert" aria-label="close">&times;</a>'
                + '<strog>[Error: Debe seleccionar un Estado Civil]'
                + '</strong></div>';
        $("#panelError").html(htmlAlert);
        $("#panelError").show();
        $("#cboEstadoCivil").focus();
        return;
    }
    
    grupoSanguineo = $("#txtGrupoSan").val();
    
    if(grupoSanguineo !== ''){
        if (grupoSanguineo.length > 20){
            htmlAlert = '<div class="alert alert-danger"><a href="#" class="close"'
                    + 'data-dismiss="alert" aria-label="close">&times;</a>'
                    + '<strog>[Error: El campo Grupo Sanguineo solo acepta hasta 20 caracteres]'
                    + '</strong></div>';
            $("#panelError").html(htmlAlert);
            $("#panelError").show();
            $("#txtGrupoSan").focus();
            return;
        }
    } else {
        grupoSanguineo = undefined;
    }

    rh = $("#txtRh").val();
    
    if(rh !== ''){
        if (rh.length > 10){
            htmlAlert = '<div class="alert alert-danger"><a href="#" class="close"'
                    + 'data-dismiss="alert" aria-label="close">&times;</a>'
                    + '<strog>[Error: El campo Rh solo acepta hasta 10 caracteres]'
                    + '</strong></div>';
            $("#panelError").html(htmlAlert);
            $("#panelError").show();
            $("#txtRh").focus();
            return;
        }
    } else {
        rh = undefined;
    }
    
    fechaNacimiento = $("#txtFechaNacimiento").val();
    var RegExPattern = /^\d{1,2}\/\d{1,2}\/\d{2,4}$/;
    if (fechaNacimiento === ''){
        htmlAlert = '<div class="alert alert-danger"><a href="#" class="close"'
                + 'data-dismiss="alert" aria-label="close">&times;</a>'
                + '<strog>[Error: El campo Fecha Nacimiento no puede ser vacio]'
                + '</strong></div>';
        $("#panelError").html(htmlAlert);
        $("#panelError").show();
        $("#txtFechaNacimiento").focus();
        return;
    }
    
    if (fechaNacimiento.length > 10){
        htmlAlert = '<div class="alert alert-danger"><a href="#" class="close"'
                + 'data-dismiss="alert" aria-label="close">&times;</a>'
                + '<strog>[Error: El campo Fecha Nacimiento tiene formato incorrecto. Recuerde DD/MM/YYYY]'
                + '</strong></div>';
        $("#panelError").html(htmlAlert);
        $("#panelError").show();
        $("#txtFechaNacimiento").focus();
        return;
    }
    
    if (!RegExPattern.test(fechaNacimiento)){
        htmlAlert = '<div class="alert alert-danger"><a href="#" class="close"'
                + 'data-dismiss="alert" aria-label="close">&times;</a>'
                + '<strog>[Error: El campo Fecha Nacimiento tiene formato incorrecto. Recuerde DD/MM/YYYY]'
                + '</strong></div>';
        $("#panelError").html(htmlAlert);
        $("#panelError").show();
        $("#txtFechaNacimiento").focus();
        return;
    }
    var fechaDividida = fechaNacimiento.split('/');
    var dia = fechaDividida[0];
    var mes = fechaDividida[1];
    var anno = fechaDividida[2];
    var valor = new Date(anno,mes,dia);
    if (isNaN(valor)){
        htmlAlert = '<div class="alert alert-danger"><a href="#" class="close"'
                + 'data-dismiss="alert" aria-label="close">&times;</a>'
                + '<strog>[Error: El campo Fecha Nacimiento. Debe ingresar fecha real]'
                + '</strong></div>';
        $("#panelError").html(htmlAlert);
        $("#panelError").show();
        $("#txtFechaNacimiento").focus();
        return;
    }
    
    idPaisOrigen = parseInt($("#cboPaisOrigen").val());
    
    if (idPaisOrigen === 0 || Number.isNaN(idPaisOrigen)){
        htmlAlert = '<div class="alert alert-danger"><a href="#" class="close"'
                + 'data-dismiss="alert" aria-label="close">&times;</a>'
                + '<strog>[Error: Debe seleccionar un Pais]'
                + '</strong></div>';
        $("#panelError").html(htmlAlert);
        $("#panelError").show();
        $("#cboPaisOrigen").focus();
        return;
    }
    idDepartamentoOrigen = parseInt($("#cboDepartamentoOrigen").val());
    
    if (idDepartamentoOrigen === 0 || Number.isNaN(idDepartamentoOrigen)){
        htmlAlert = '<div class="alert alert-danger"><a href="#" class="close"'
                + 'data-dismiss="alert" aria-label="close">&times;</a>'
                + '<strog>[Error: Debe seleccionar un Departamento]'
                + '</strong></div>';
        $("#panelError").html(htmlAlert);
        $("#panelError").show();
        $("#cboDepartamentoOrigen").focus();
        return;
    }
    idProvinciaOrigen = parseInt($("#cboProvinciaOrigen").val());
    
    if (idProvinciaOrigen === 0 || Number.isNaN(idProvinciaOrigen)){
        htmlAlert = '<div class="alert alert-danger"><a href="#" class="close"'
                + 'data-dismiss="alert" aria-label="close">&times;</a>'
                + '<strog>[Error: Debe seleccionar una Provincia]'
                + '</strong></div>';
        $("#panelError").html(htmlAlert);
        $("#panelError").show();
        $("#cboProvinciaOrigen").focus();
        return;
    }
    idDistritoOrigen = parseInt($("#cboDistritoOrigen").val());
    
    if (idDistritoOrigen === 0 || Number.isNaN(idDistritoOrigen)){
        htmlAlert = '<div class="alert alert-danger"><a href="#" class="close"'
                + 'data-dismiss="alert" aria-label="close">&times;</a>'
                + '<strog>[Error: Debe seleccionar un Departamento]'
                + '</strong></div>';
        $("#panelError").html(htmlAlert);
        $("#panelError").show();
        $("#cboDistritoOrigen").focus();
        return;
    }
    
    numLibretaMilitar = $("#txtLibretaMilitar").val();
    
    if (numLibretaMilitar !== ''){
        if (numLibretaMilitar.length > 20){
            htmlAlert = '<div class="alert alert-danger"><a href="#" class="close"'
                    + 'data-dismiss="alert" aria-label="close">&times;</a>'
                    + '<strog>[Error: El campo Libreta Militar solo acepta hasta 20 caracteres]'
                    + '</strong></div>';
            $("#panelError").html(htmlAlert);
            $("#panelError").show();
            $("#txtLibretaMilitar").focus();
            return;
        }
    } else {
        numLibretaMilitar = undefined;
    }
    numCarnetExtranjeria= $("#txtCarnetExtranjeria").val();
    
    if(numCarnetExtranjeria !== ''){
        if (numCarnetExtranjeria.length > 20){
            htmlAlert = '<div class="alert alert-danger"><a href="#" class="close"'
                    + 'data-dismiss="alert" aria-label="close">&times;</a>'
                    + '<strog>[Error: El campo Carnet Extranjeria solo acepta hasta 20 caracteres]'
                    + '</strong></div>';
            $("#panelError").html(htmlAlert);
            $("#panelError").show();
            $("#txtCarnetExtranjeria").focus();
            return;
        }
    } else {
        numCarnetExtranjeria = undefined;
    }
   
    numTarjFFAA= $("#txtTarjetaIdentidad").val();
    
    if(numTarjFFAA !== ''){
        if (numTarjFFAA.length > 20){
            htmlAlert = '<div class="alert alert-danger"><a href="#" class="close"'
                    + 'data-dismiss="alert" aria-label="close">&times;</a>'
                    + '<strog>[Error: El campo Tarjeta FF.AA. y PNP solo acepta hasta 20 caracteres]'
                    + '</strong></div>';
            $("#panelError").html(htmlAlert);
            $("#panelError").show();
            $("#txtTarjetaIdentidad").focus();
            return;
        }
    } else {
        numTarjFFAA = undefined;
    }
    
    numCarnetEssalud= $("#txtCarnetEssalud").val();
    if(numCarnetEssalud !== ''){
        if (numCarnetEssalud.length > 20){
            htmlAlert = '<div class="alert alert-danger"><a href="#" class="close"'
                    + 'data-dismiss="alert" aria-label="close">&times;</a>'
                    + '<strog>[Error: El campo Carnet Essalud solo acepta hasta 20 caracteres]'
                    + '</strong></div>';
            $("#panelError").html(htmlAlert);
            $("#panelError").show();
            $("#txtCarnetEssalud").focus();
            return;
        }
    } else {
        numCarnetEssalud = undefined;
    }
    
    domicilioActual= $("#txtDomicilioActual").val();
    
    if (domicilioActual === ''){
        htmlAlert = '<div class="alert alert-danger"><a href="#" class="close"'
                + 'data-dismiss="alert" aria-label="close">&times;</a>'
                + '<strog>[Error: El campo Domicilio no puede ser vacio]'
                + '</strong></div>';
        $("#panelError").html(htmlAlert);
        $("#panelError").show();
        $("#txtDomicilioActual").focus();
        return;
    }
    
    if (domicilioActual.length > 200){
        htmlAlert = '<div class="alert alert-danger"><a href="#" class="close"'
                + 'data-dismiss="alert" aria-label="close">&times;</a>'
                + '<strog>[Error: El campo Domicilio solo acepta hasta 200 caracteres]'
                + '</strong></div>';
        $("#panelError").html(htmlAlert);
        $("#panelError").show();
        $("#txtDomicilioActual").focus();
        return;
    }
    
    idDistritoActual = parseInt($("#cboDistritoActual").val());
    if(idDistritoActual === 0 || Number.isNaN(idDistritoActual)){
        idDistritoActual = undefined;
    }
    
    codigoPostal = $("#txtCodigoPostal").val();
    
    if (codigoPostal !== ''){
        if (codigoPostal.length > 10){
            htmlAlert = '<div class="alert alert-danger"><a href="#" class="close"'
                    + 'data-dismiss="alert" aria-label="close">&times;</a>'
                    + '<strog>[Error: El campo Codigo postal solo acepta hasta 10 caracteres]'
                    + '</strong></div>';
            $("#panelError").html(htmlAlert);
            $("#panelError").show();
            $("#txtCodigoPostal").focus();
            return;
        }
    } else {
        codigoPostal = undefined;
    }
    
    idProvinciaActual = parseInt($("#cboProvinciaActual").val());
    if(idProvinciaActual === 0 || Number.isNaN(idProvinciaActual)){
        idProvinciaActual = undefined;
    }
    idCondicionCasa = parseInt($("#cboCondCasa").val());
    if(idCondicionCasa === 0 || Number.isNaN(idCondicionCasa)){
        idCondicionCasa = undefined;
    }
    
    TelfDomicilio = $("#txtTelfDomicilio").val();
    
    if (TelfDomicilio === ''){
        htmlAlert = '<div class="alert alert-danger"><a href="#" class="close"'
                + 'data-dismiss="alert" aria-label="close">&times;</a>'
                + '<strog>[Error: El campo Telf. Domicilio no puede ser vacio]'
                + '</strong></div>';
        $("#panelError").html(htmlAlert);
        $("#panelError").show();
        $("#txtTelfDomicilio").focus();
        return;
    }
    
    if (TelfDomicilio.length > 15){
        htmlAlert = '<div class="alert alert-danger"><a href="#" class="close"'
                + 'data-dismiss="alert" aria-label="close">&times;</a>'
                + '<strog>[Error: El campo Telf. Domicilio solo acepta hasta 15 caracteres]'
                + '</strong></div>';
        $("#panelError").html(htmlAlert);
        $("#panelError").show();
        $("#txtTelfDomicilio").focus();
        return;
    }
    
    emailPrin = $("#txtEmail1").val();
    RegExPattern = /^[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*(.[a-z]{2,4})$/;
    if (emailPrin === ''){
        htmlAlert = '<div class="alert alert-danger"><a href="#" class="close"'
                + 'data-dismiss="alert" aria-label="close">&times;</a>'
                + '<strog>[Error: El campo Email 1 no puede ser vacio]'
                + '</strong></div>';
        $("#panelError").html(htmlAlert);
        $("#panelError").show();
        $("#txtEmail1").focus();
        return;
    }
    
    if (!RegExPattern.test(emailPrin)){
        htmlAlert = '<div class="alert alert-danger"><a href="#" class="close"'
                + 'data-dismiss="alert" aria-label="close">&times;</a>'
                + '<strog>[Error: El campo Email 1. Formato de email incorrecta]'
                + '</strong></div>';
        $("#panelError").html(htmlAlert);
        $("#panelError").show();
        $("#txtEmail1").focus();
        return;
    }
    
    if (emailPrin.length > 100){
        htmlAlert = '<div class="alert alert-danger"><a href="#" class="close"'
                + 'data-dismiss="alert" aria-label="close">&times;</a>'
                + '<strog>[Error: El campo Email 1 solo acepta hasta 100 caracteres]'
                + '</strong></div>';
        $("#panelError").html(htmlAlert);
        $("#panelError").show();
        $("#txtEmail1").focus();
        return;
    }
    
    emailSec = $("#txtEmail2").val();
    if (emailSec !== ''){
        if (!RegExPattern.test(emailSec)){
            htmlAlert = '<div class="alert alert-danger"><a href="#" class="close"'
                    + 'data-dismiss="alert" aria-label="close">&times;</a>'
                    + '<strog>[Error: El campo Email 2. Formato de email incorrecta]'
                    + '</strong></div>';
            $("#panelError").html(htmlAlert);
            $("#panelError").show();
            $("#txtEmail2").focus();
            return;
        }

        if (emailSec.length > 100){
            htmlAlert = '<div class="alert alert-danger"><a href="#" class="close"'
                    + 'data-dismiss="alert" aria-label="close">&times;</a>'
                    + '<strog>[Error: El campo Email 2 solo acepta hasta 100 caracteres]'
                    + '</strong></div>';
            $("#panelError").html(htmlAlert);
            $("#panelError").show();
            $("#txtEmail2").focus();
            return;
        }
    } else {
        emailSec = undefined;
    }
    
    personaRep.apePaterno = apePaterno;
    personaRep.apeMaterno = apeMaterno;
    personaRep.nombres = nombres;
    personaRep.grupoSanguineo = grupoSanguineo;
    personaRep.rh = rh;
    personaRep.fechaNacimiento = fechaNacimiento;
    personaRep.libretaMilitar = numLibretaMilitar;
    personaRep.carnetExt = numCarnetExtranjeria;
    personaRep.ffaa_pnp = numTarjFFAA;
    personaRep.carnetEssalud = numCarnetEssalud;
    personaRep.domicilio = domicilioActual;
    personaRep.idEstadoCivil = idEstadoCivil;
    personaRep.idPaisOri = idPaisOrigen;
    personaRep.idDepartamentoOri = idDepartamentoOrigen;
    personaRep.idProvinciaOri = idProvinciaOrigen;
    personaRep.idDistritoOri = idDistritoOrigen;
    personaRep.idCondicionCasa = idCondicionCasa;
    personaRep.idDistritoAct = idDistritoActual;
    personaRep.idProvinciaAct = idProvinciaActual;
    personaRep.telfDomicilio = TelfDomicilio;
    personaRep.emailPrincipal = emailPrin;
    personaRep.emailSecundario = emailSec;
    personaRep.codigoPostal = codigoPostal;
    var personaResp;
    if (personaRep.indExiste === 0){
        personaRep.dni = dni;
        personaResp = personaRep.mantPersonaPreInscripcion(1); 
    } else {
        personaResp = personaRep.mantPersonaPreInscripcion(2); 
    }
    var $strong;
    if(personaResp.indError === 0){
        htmlAlert = '<div class="alert alert-success">';
        htmlAlert += '<a href="#" class="close" data-dismiss="alert"'
                + 'aria-label="close">&times;</a></div>';
        $("#panelError").html(htmlAlert);
        $strong = $("<strong/>",{
            text : personaResp.msjError
        });
        $("#panelError div").append($strong);
        $("#panelError").show();
        if (personaRep.indExiste === 0){
            limpiarTodo();
            //deshabilitarFormulario(false);
            habilitarBotonesCrud(true, true, false);
            $("#txtDni").val("");
            $("#txtDni").prop("disabled", false);
            $("#txtDni").focus();
        } else {
            deshabilitarFormulario(true);
            habilitarBotonesCrud(true, false, false);
            $("#Opceditar").focus();
        }
    } else {
        htmlAlert = '<div class="alert alert-danger">';
        htmlAlert += '<a href="#" class="close" data-dismiss="alert"'
                + 'aria-label="close">&times;</a></div>';
        $("#panelError").html(htmlAlert);
        $strong = $("<strong/>",{
            text : personaResp.msjError
        });
        $("#panelError div").append($strong);
        $("#panelError").show();
        if (personaResp.idCampoError !== undefined)
            $('#'+personaResp.idCampoError).focus();
    }
    
}

function limpiarTodo(){
    $("#txtApellidoPat").val("");
    $("#txtApellidoMat").val("");
    $("#txtNombres").val("");
    $("#txtGrupoSan").val("");
    $("#txtRh").val("");
    $("#cboEstadoCivil").val("0");
    $("#txtFechaNacimiento").val("");
    $("#cboPaisOrigen").val("0");
    $("#txtLibretaMilitar").val("");
    $("#txtCarnetExtranjeria").val("");
    $("#txtTarjetaIdentidad").val("");
    $("#txtCarnetEssalud").val("");
    $("#cboDistritoActual").val("0");
    $("#txtCodigoPostal").val("");
    $("#txtDomicilioActual").val("");
    $("#cboProvinciaActual").val("0");
    $("#cboCondCasa").val("0");
    $("#txtTelfDomicilio").val("");
    $("#txtEmail1").val("");
    $("#txtEmail2").val("");
    $("#cboDepartamentoOrigen").val("0");
    $("#cboProvinciaOrigen").html("<option value='0'>--Seleccione--</option>");
    $("#cboDistritoOrigen").html("<option value='0'>--Seleccione--</option>");
}

