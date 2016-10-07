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
       grabarIngresoInscripcion(); 
    });
    
//    $("#Opceditar").on("click", function(){
//        deshabilitarFormulario(false);
//        habilitarBotonesCrud(false, true, false);
//        $("#txtApellidoPat").focus();
//    });
    
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
    
    gnumExpLaboral = 0;
    $("#nuevaExpLaboral").on("click",function(){
       crearFilaExperienciaLaboral(); 
    });
    
    gnumDatoFamilia = 0;
    $("#btnDatosFamilia").on("click",function(){
        crearFilaFamilia(); 
    });
    
    gnumOtrosEstudios = 0;
    $("#btnEspecialidad").on("click",function(){
        crearFilaEspecialidad(); 
    });
    
    $("#btnMaestrias").on("click",function(){
        crearFilaMaestrias(); 
    });
    
    $("#btnDoctorado").on("click",function(){
        crearFilaDoctorado(); 
    });
    
    $("#btnSocNacionales").on("click",function(){
        crearFilaSocNacional(); 
    });
    
    $("#btnSocInternacional").on("click",function(){
        crearFilaSocInternacional(); 
    });

    $("#btnRevCientifica").on("click",function(){
        crearFilaRevCientifica(); 
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

    colegiadoRep = colegiado.buscarPersonaColegiadoInscripIngre(3);
   

    if (colegiadoRep.indError === 0) {
        if(colegiadoRep.esColegiado === 1){
            llenarFormularioColegiado(colegiadoRep);
            llenarFormularioExpLaborales(colegiadoRep.expeLaborales);
            llenarFormularioFamiliares(colegiadoRep.familiares);
            llenarFormularioTitulacion(colegiadoRep.titulacion);
            llenarFormularioOtrosEstudios(colegiadoRep.otrosEstudios);
            habilitarBotonesCrud(false, false, false);
            $("#cboOpcionBusqueda").prop("disabled", true);
            $("#txtDniNumColegiatura").prop("disabled", true);
        } 
    } else {
        mostrarMensaje('danger',colegiadoRep.msjError);
        $("#txtDniNumColegiatura").focus();
    }
}

function llenarFormulario(colegiado){
    $("#txtApellidoPat").val(colegiado.apePaterno);
    $("#txtApellidoMat").val(colegiado.apeMaterno);
    $("#txtNombres").val(colegiado.nombres);
    $("#txtGrupoSan").val(colegiado.grupoSanguineo);
    $("#txtRh").val(colegiado.rh);
    $("#cboEstadoCivil").val(colegiado.idEstadoCivil);
    $("#txtFechaNacimiento").val(colegiado.fechaNacimiento);
    $("#cboPaisOrigen").val(colegiado.idPaisOri);
    $("#txtLibretaMilitar").val(colegiado.libretaMilitar);
    $("#txtCarnetExtranjeria").val(colegiado.carnetExt);
    $("#txtTarjetaIdentidad").val(colegiado.ffaa_pnp);
    $("#txtCarnetEssalud").val(colegiado.carnetEssalud);
    $("#cboDistritoActual").val(colegiado.idDistritoAct);
    $("#txtCodigoPostal").val(colegiado.codigoPostal);
    $("#txtDomicilioActual").val(colegiado.domicilio);
    $("#cboProvinciaActual").val(colegiado.idProvinciaAct);
    $("#cboCondCasa").val(colegiado.idCondicionCasa);
    $("#txtTelfDomicilio").val(colegiado.telfDomicilio);
    $("#txtEmail1").val(colegiado.emailPrincipal);
    $("#txtEmail2").val(colegiado.emailSecundario);
    $("#cboDepartamentoOrigen").val(colegiado.idDepartamentoOri);
    listarProvinciasXDepartamento();
    $("#cboProvinciaOrigen").val(colegiado.idProvinciaOri);
    listarDistritosXProvincia();
    $("#cboDistritoOrigen").val(colegiado.idDistritoOri);
}

 function llenarFormularioColegiado(colegiadoRep){
     llenarFormulario(colegiadoRep);
     $("#txtFechaInscripcion").val(colegiadoRep.fechaInscripcion);
     $("#txtFechaIngreso").val(colegiadoRep.fechaIngreso);
//     $("#txtNumColegiatura").val(colegiadoRep.numColegiatura);
 }

function llenarFormularioExpLaborales(expeLaborales){
    var i = 0;
    var $div1, $div2, $label, $input,$a, $span, $inputhidden;
    var expLab;
    for(i = 0; i < expeLaborales.length; i++){
        expLab = expeLaborales[i];
        $div1 = $("<div/>",{
           class : "row form-group",
           id : "expLab"+i 
        });
        $inputhidden = $("<input/>",{
            type : "hidden",
            value : i,
            class : "txtIndExpLaboral"
        });
        $div1.append($inputhidden);
        $div2 = $("<div/>",{
            class : "col-lg-offset-1 col-lg-4"
        });
        $label = $("<label/>",{
           for :  "centroLaboral",
           text : "Centro Laboral:" 
        });
        $input = $('<input/>',{
            type : "text",
            class : "form-control txtCentroLaboral",
            name : "centroLaboral",
            placeholder : "Centro Laboral",
            value : expLab.centroTrabajo === " " ? "" : expLab.centroTrabajo
        });
        $div2.append($label);
        $div2.append($input);
        $div1.append($div2);
        
        $div2 = $("<div/>",{
            class : "col-lg-2"
        });
        $label = $("<label/>",{
           for :  "regimenLaboral",
           text : "Regimen Laboral:" 
        });
        $input = $('<input/>',{
            type : "text",
            class : "form-control txtRegimenLaboral",
            name : "regimenLaboral",
            placeholder : "Regimen Laboral",
            value : expLab.regimenLaboral === " " ? "" : expLab.regimenLaboral
        });
        $div2.append($label);
        $div2.append($input);
        $div1.append($div2);
        
        $div2 = $("<div/>",{
            class : "col-lg-2"
        });
        $label = $("<label/>",{
           for :  "telefonoLaboral",
           text : "Telefono:" 
        });
        $input = $('<input/>',{
            type : "text",
            class : "form-control txtTelefonoLaboral",
            name : "telefonoLaboral",
            placeholder : "Telefono",
            value : expLab.telefono === " " ? "" : expLab.telefono
        });
        $div2.append($label);
        $div2.append($input);
        $div1.append($div2);
        
        $div2 = $("<div/>",{
            class : "col-lg-2"
        });
        $label = $("<label/>",{
           for :  "faxLaboral",
           text : "Fax:" 
        });
        $input = $('<input/>',{
            type : "text",
            class : "form-control txtFaxLaboral",
            name : "faxLaboral",
            placeholder : "Fax",
            value : expLab.fax === " " ? "" : expLab.fax
        });
        $div2.append($label);
        $div2.append($input);
        $div1.append($div2);
        
        $div2 = $("<div/>",{
            class : "col-lg-1"
        });
        $label = $("<label/>",{
           text : "Quitar" 
        });
        $a = $('<a/>',{
            class : "btn btn-danger",
            onclick : "cerrarExpLab('expLab"+i+"')"
        });
        $span = $('<span/>',{
           class :  "glyphicon glyphicon-minus"
        });
        $a.append($span);
        $div2.append($label);
        $div2.append($a);
        $div1.append($div2);
        $("#expLaborales").append($div1);
    }
    gnumExpLaboral = i;
}

function llenarFormularioFamiliares(familiares){
    var i = 0;
    var $div1, $div2, $label, $input,$a, $span, $inputhidden;
    var fam;
    for(i = 0; i < familiares.length; i++){
        fam = familiares[i];
        $div1 = $("<div/>",{
           class : "row form-group",
           id : "datoFam"+i 
        });
        $inputhidden = $("<input/>",{
            type : "hidden",
            value : i,
            class : "txtIndFamilia"
        });
        $div1.append($inputhidden);
        $div2 = $("<div/>",{
            class : "col-lg-offset-1 col-lg-6"
        });
        $label = $("<label/>",{
           for :  "nombreDependientes",
           text : "Nombre de Dependientes:" 
        });
        $input = $('<input/>',{
            type : "text",
            class : "form-control txtNombreDependientes",
            name : "txtNombreDependientes",
            placeholder : "Nombre Dependientes",
            value : fam.nombre === " " ? "" : fam.nombre
        });
        $div2.append($label);
        $div2.append($input);
        $div1.append($div2);
        
        $div2 = $("<div/>",{
            class : "col-lg-2"
        });
        $label = $("<label/>",{
           for :  "parentesco",
           text : "Parentesco:" 
        });
        $input = $('<input/>',{
            type : "text",
            class : "form-control txtParentesco",
            name : "txtParentesco",
            placeholder : "Parentesco",
            value : fam.parentesco === " " ? "" : fam.parentesco
        });
        $div2.append($label);
        $div2.append($input);
        $div1.append($div2);
        
        $div2 = $("<div/>",{
            class : "col-lg-1"
        });
        $label = $("<label/>",{
           for :  "edadPariente",
           text : "Edad:" 
        });
        $input = $('<input/>',{
            type : "text",
            class : "form-control txtEdadParientes",
            name : "txtEdadParientes",
            placeholder : "Edad",
            value : fam.edad === " " ? "" : fam.edad
        });
        $div2.append($label);
        $div2.append($input);
        $div1.append($div2);

        $div2 = $("<div/>",{
            class : "col-lg-1"
        });
        $label = $("<label/>",{
           text : "Quitar" 
        });
        $a = $('<a/>',{
            class : "btn btn-danger",
            onclick : "cerrarFamilia('datoFam"+i+"')"
        });
        $span = $('<span/>',{
           class :  "glyphicon glyphicon-minus"
        });
        $a.append($span);
        $div2.append($label);
        $div2.append($a);
        $div1.append($div2);
        $("#datosFamilia").append($div1);
    }
    gnumDatoFamilia = i;
}

function llenarFormularioTitulacion(titulacion){
    $("#txtUniversidad").val(titulacion.univTitulacion === " " ? "" : titulacion.univTitulacion);
    $("#txtAnnoTitulo").val(titulacion.annoTitulacion === " " ? "" : titulacion.annoTitulacion);
    $("#txtNumTitulo").val(titulacion.numTitulo === " " ? "" : titulacion.numTitulo);
    $("#txtNumRegistro").val(titulacion.numRegistro === " " ? "" : titulacion.numRegistro);
}

function llenarFormularioOtrosEstudios(otrosEstudios){
    var i = 0;
    var $div, $div2, $input, $inputhidden, $a, $span;
    for(i = 0; i < otrosEstudios.length; i++){
        switch(otrosEstudios[i].tipo){
            case 1:
                $div = $("<div/>",{
                   class : "row form-group",
                   id : "esp"+i
                });
                $inputhidden = $("<input/>",{
                   type: "hidden",
                   value: i,
                   class : "txtIndEspecialidades"
                });
                $div.append($inputhidden);
                $div2 = $("<div/>",{
                   class : "col-lg-9" 
                });
                $input = $("<input/>",{
                   type : "text",
                   class : "form-control txtEspecialidad",
                   name : "txtEspecialidad",
                   placeholder : "Especialidad",
                   value : otrosEstudios[i].denominacion === " " ? "" : otrosEstudios[i].denominacion
                });
                $div2.append($input);
                $div.append($div2);
                 $div2 = $("<div/>",{
                   class : "col-lg-2" 
                });
                $a = $("<a/>",{
                   class : "btn btn-danger",
                   onclick : "cerrarEspecialidad('esp"+i+"')",
                   role : "button"
                });
                $span = $('<span/>',{
                   class :  "glyphicon glyphicon-minus"
                });
                $a.append($span);
                $div2.append($a);
                $div.append($div2);
                $("#especialidades").append($div);
                break;
            case 2:
                 $div = $("<div/>",{
                   class : "row form-group",
                   id : "maes"+i
                });
                $inputhidden = $("<input/>",{
                   type: "hidden",
                   value: i,
                   class : "txtIndMaestrias"
                });
                $div.append($inputhidden);
                $div2 = $("<div/>",{
                   class : "col-lg-9" 
                });
                $input = $("<input/>",{
                   type : "text",
                   class : "form-control txtMaestria",
                   name : "txtMaestria",
                   placeholder : "Maestria",
                   value : otrosEstudios[i].denominacion === " " ? "" : otrosEstudios[i].denominacion
                });
                $div2.append($input);
                $div.append($div2);
                 $div2 = $("<div/>",{
                   class : "col-lg-2" 
                });
                $a = $("<a/>",{
                   class : "btn btn-danger",
                   onclick : "cerrarMaestria('maes"+i+"')",
                   role : "button"
                });
                $span = $('<span/>',{
                   class :  "glyphicon glyphicon-minus"
                });
                $a.append($span);
                $div2.append($a);
                $div.append($div2);
                $("#maestrias").append($div);
                break;
            case 3:
                 $div = $("<div/>",{
                   class : "row form-group",
                   id : "doc"+i
                });
                $inputhidden = $("<input/>",{
                   type: "hidden",
                   value: i,
                   class : "txtIndDoctorado"
                });
                $div.append($inputhidden);
                $div2 = $("<div/>",{
                   class : "col-lg-9" 
                });
                $input = $("<input/>",{
                   type : "text",
                   class : "form-control txtDoctorado",
                   name : "txtDoctorado",
                   placeholder : "Doctorado",
                   value : otrosEstudios[i].denominacion === " "? "" : otrosEstudios[i].denominacion
                });
                $div2.append($input);
                $div.append($div2);
                 $div2 = $("<div/>",{
                   class : "col-lg-2" 
                });
                $a = $("<a/>",{
                   class : "btn btn-danger",
                   onclick : "cerrarDoctorado('doc"+i+"')",
                   role : "button"
                });
                $span = $('<span/>',{
                   class :  "glyphicon glyphicon-minus"
                });
                $a.append($span);
                $div2.append($a);
                $div.append($div2);
                $("#doctorados").append($div);
                break;
            case 4:
                $div = $("<div/>",{
                   class : "row form-group",
                   id : "socNac"+i
                });
                $inputhidden = $("<input/>",{
                   type: "hidden",
                   value: i,
                   class : "txtIndSocNac"
                });
                $div.append($inputhidden);
                $div2 = $("<div/>",{
                   class : "col-lg-9" 
                });
                $input = $("<input/>",{
                   type : "text",
                   class : "form-control txtSocCientificasNac",
                   name : "txtSocCientificasNac",
                   placeholder : "Soc. Cientifica Nacional",
                   value : otrosEstudios[i].denominacion === " "? "" : otrosEstudios[i].denominacion
                });
                $div2.append($input);
                $div.append($div2);
                 $div2 = $("<div/>",{
                   class : "col-lg-2" 
                });
                $a = $("<a/>",{
                   class : "btn btn-danger",
                   onclick : "cerrarSocNac('socNac"+i+"')",
                   role : "button"
                });
                $span = $('<span/>',{
                   class :  "glyphicon glyphicon-minus"
                });
                $a.append($span);
                $div2.append($a);
                $div.append($div2);
                $("#sociedadesNacioneales").append($div); 
                break;
            case 5:
                $div = $("<div/>",{
                   class : "row form-group",
                   id : "socInt"+i
                });
                $inputhidden = $("<input/>",{
                   type: "hidden",
                   value: i,
                   class : "txtIndSocIntNac"
                });
                $div.append($inputhidden);
                $div2 = $("<div/>",{
                   class : "col-lg-9" 
                });
                $input = $("<input/>",{
                   type : "text",
                   class : "form-control txtSocCientificasIntnac",
                   name : "txtSocCientificasIntnac",
                   placeholder : "Soc. Cientifica Internacional",
                   value : otrosEstudios[i].denominacion === " "? "" : otrosEstudios[i].denominacion
                });
                $div2.append($input);
                $div.append($div2);
                 $div2 = $("<div/>",{
                   class : "col-lg-2" 
                });
                $a = $("<a/>",{
                   class : "btn btn-danger",
                   onclick : "cerrarSocInt('socInt"+i+"')",
                   role : "button"
                });
                $span = $('<span/>',{
                   class :  "glyphicon glyphicon-minus"
                });
                $a.append($span);
                $div2.append($a);
                $div.append($div2);
                $("#SociedadesInternacionales").append($div); 
                break;
            case 6:
                $div = $("<div/>",{
                   class : "row form-group",
                   id : "revC"+i
                });
                $inputhidden = $("<input/>",{
                   type: "hidden",
                   value: i,
                   class : "txtIndRevC"
                });
                $div.append($inputhidden);
                $div2 = $("<div/>",{
                   class : "col-lg-9" 
                });
                $input = $("<input/>",{
                   type : "text",
                   class : "form-control txtRevCientifica",
                   name : "txtRevCientifica",
                   placeholder : "Revista Cientifica",
                   value : otrosEstudios[i].denominacion === " "? "" : otrosEstudios[i].denominacion
                });
                $div2.append($input);
                $div.append($div2);
                 $div2 = $("<div/>",{
                   class : "col-lg-2" 
                });
                $a = $("<a/>",{
                   class : "btn btn-danger",
                   onclick : "cerrarRevC('revC"+i+"')",
                   role : "button"
                });
                $span = $('<span/>',{
                   class :  "glyphicon glyphicon-minus"
                });
                $a.append($span);
                $div2.append($a);
                $div.append($div2);
                $("#revistaCientifica").append($div);     
                break;
        }
    }
    gnumOtrosEstudios = i;
}

function deshabilitarFormulario(band){
    $("#txtNumColegiatura").prop("disabled", band);
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

function deshabilitarDatosColegiado(band){
    $("#txtApellidoPat").prop("disabled", band);
    $("#txtApellidoMat").prop("disabled", band);
    $("#txtNombres").prop("disabled", band);
    $("#cboEstadoCivil").prop("disabled", band);
    $("#txtFechaNacimiento").prop("disabled", band);
    $("#txtNumColegiatura").prop("disabled", band);
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
//    $("#Opceditar").prop("disabled", editar);
    $("#OpcCancelar").prop("disabled", cancelar);
}

function grabarIngresoInscripcion(){
    var indBusqueda, dni, numColegiatura, fechaInscrip, fechaIngreso;
    var apePaterno, apeMaterno, nombres, idEstadoCivil;
    var grupoSanguineo, rh, fechaNacimiento, idPaisOrigen;
    var idDepartamentoOrigen, idProvinciaOrigen, idDistritoOrigen;
    var numLibretaMilitar, numCarnetExtranjeria, numTarjFFAA, numCarnetEssalud;
    var domicilioActual, idDistritoActual, codigoPostal, idProvinciaActual;
    var idCondicionCasa, TelfDomicilio, emailPrin, emailSec;
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
    fechaInscrip = $("#txtFechaInscripcion").val();
    var RegExPattern = /^\d{1,2}\/\d{1,2}\/\d{2,4}$/;
    if (fechaInscrip !== ''){
        if (fechaInscrip.length > 10){
            mostrarMensaje('danger','[Error: El campo Fecha Inscripcion/Ingreso tiene formato incorrecto. Recuerde DD/MM/YYYY]');
            $("#txtFechaInscripcion").focus();
            return;
        }

        if (!RegExPattern.test(fechaInscrip)){
            mostrarMensaje('danger','[Error: El campo Fecha Inscripcion/Ingreso tiene formato incorrecto. Recuerde DD/MM/YYYY]');
            $("#txtFechaInscripcion").focus();
            return;
        }
        var fechaDividida1 = fechaInscrip.split('/');
        var dia1 = fechaDividida1[0];
        var mes1 = fechaDividida1[1];
        var anno1 = fechaDividida1[2];
        var valor1 = new Date(anno1,mes1,dia1);
        if (isNaN(valor1)){
            mostrarMensaje('danger','[Error: En campo Fecha Inscripcion/Ingreso. Debe ingresar fecha real');
            $("#txtFechaInscripcion").focus();
            return;
        }
    } else {
        fechaInscrip = undefined;
    }
    
//    fechaIngreso = $("#txtFechaIngreso").val();
//    if (fechaIngreso !== ''){
//        if (fechaInscrip.length > 10){
//            mostrarMensaje('danger','[Error: El campo Fecha Inscripcion/Ingreso tiene formato incorrecto. Recuerde DD/MM/YYYY]');
//            $("#txtFechaIngreso").focus();
//            return;
//        }
//
//        if (!RegExPattern.test(fechaIngreso)){
//            mostrarMensaje('danger','[Error: El campo Fecha Inscripcion/Ingreso tiene formato incorrecto. Recuerde DD/MM/YYYY]');
//            $("#txtFechaIngreso").focus();
//            return;
//        }
//        var fechaDividida1 = fechaIngreso.split('/');
//        var dia1 = fechaDividida1[0];
//        var mes1 = fechaDividida1[1];
//        var anno1 = fechaDividida1[2];
//        var valor1 = new Date(anno1,mes1,dia1);
//        if (isNaN(valor1)){
//            mostrarMensaje('danger','[Error: En campo Fecha Inscripcion/Ingreso. Debe ingresar fecha real');
//            $("#txtFechaIngreso").focus();
//            return;
//        }
//    } else {
//        fechaIngreso = undefined;
//    }
    
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

    
    colegiadoRep.apePaterno = apePaterno;
    colegiadoRep.apeMaterno = apeMaterno;
    colegiadoRep.nombres = nombres;
    colegiadoRep.fechaNacimiento = fechaNacimiento;
    colegiadoRep.idEstadoCivil = idEstadoCivil;
    colegiadoRep.grupoSanguineo = grupoSanguineo;
    colegiadoRep.rh = rh;
    colegiadoRep.libretaMilitar = numLibretaMilitar;
    colegiadoRep.carnetExt = numCarnetExtranjeria;
    colegiadoRep.ffaa_pnp = numTarjFFAA;
    colegiadoRep.carnetEssalud = numCarnetEssalud;
    colegiadoRep.domicilio = domicilioActual;
    colegiadoRep.idPaisOri = idPaisOrigen;
    colegiadoRep.idDepartamentoOri = idDepartamentoOrigen;
    colegiadoRep.idProvinciaOri = idProvinciaOrigen;
    colegiadoRep.idDistritoOri = idDistritoOrigen;
    colegiadoRep.idCondicionCasa = idCondicionCasa;
    colegiadoRep.idDistritoAct = idDistritoActual;
    colegiadoRep.idProvinciaAct = idProvinciaActual;
    colegiadoRep.telfDomicilio = TelfDomicilio;
    colegiadoRep.emailPrincipal = emailPrin;
    colegiadoRep.emailSecundario = emailSec;
    colegiadoRep.codigoPostal = codigoPostal;
//    colegiadoRep.fechaIngreso = fechaIngreso;
    colegiadoRep.fechaInscripcion = fechaInscrip;
    analizarExpLaboral();
    analizarFamiliares();
    analizarGradosyTitulos();
    var colegiadoResp;
    colegiadoResp = colegiadoRep.mantPersonaColegiadoInscripIngre(2,0);
    if(colegiadoResp.indError === 0){
        mostrarMensaje('success',colegiadoResp.msjError);
        limpiarTodo();
        habilitarBotonesCrud(true, true, false);
        $("#txtDniNumColegiatura").val("");
        $("#txtDniNumColegiatura").prop("disabled", false);
        $("#cboOpcionBusqueda").prop("disabled", false);
        $("#cboOpcionBusqueda").val("0");
        $("#cboOpcionBusqueda").focus();
    } else {
        mostrarMensaje('danger',colegiadoResp.msjError);
        if (colegiadoResp.idCampoError !== undefined)
            $('#'+colegiadoResp.idCampoError).focus();
    }
}

function analizarExpLaboral(){
    var arrayHidden, arrayCentroLaboral, arrayRegimen;
    var arrayTelefono, arrayFax;
    var i, indice;
    var expLab, tamArray;
    arrayHidden = $(".txtIndExpLaboral");
    arrayCentroLaboral = $(".txtCentroLaboral");
    arrayRegimen = $(".txtRegimenLaboral");
    arrayTelefono = $(".txtTelefonoLaboral");
    arrayFax = $(".txtFaxLaboral");
    
    if(arrayHidden !== undefined){
        tamArray = colegiadoRep.expeLaborales.length;
        for(i = 0; i < arrayHidden.length; i++){
            indice = parseInt(arrayHidden[i].value);
            if(tamArray > 0){
                if(tamArray > indice){
                   colegiadoRep.expeLaborales[i].centroTrabajo = arrayCentroLaboral[i].value === "" ? " ":arrayCentroLaboral[i].value;  
                   colegiadoRep.expeLaborales[i].regimenLaboral = arrayRegimen[i].value === "" ? " " : arrayRegimen[i].value;
                   colegiadoRep.expeLaborales[i].telefono = arrayTelefono[i].value === "" ? " " : arrayTelefono[i].value; 
                   colegiadoRep.expeLaborales[i].fax = arrayFax[i].value === "" ? " " : arrayFax[i].value;
                   colegiadoRep.expeLaborales[i].indEliminacion = 0;
                } else {
                    expLab = new ExperienciaLaboral();
                    expLab.centroTrabajo = arrayCentroLaboral[i].value === "" ? " ":arrayCentroLaboral[i].value;  
                    expLab.regimenLaboral = arrayRegimen[i].value === "" ? " " : arrayRegimen[i].value;
                    expLab.telefono = arrayTelefono[i].value === "" ? " " : arrayTelefono[i].value; 
                    expLab.fax = arrayFax[i].value === "" ? " " : arrayFax[i].value;
                    expLab.indEliminacion = 0;
                    colegiadoRep.expeLaborales[colegiadoRep.expeLaborales.length] = expLab;
                }
            } else{
                expLab = new ExperienciaLaboral();
                expLab.centroTrabajo = arrayCentroLaboral[i].value === "" ? " ":arrayCentroLaboral[i].value;  
                expLab.regimenLaboral = arrayRegimen[i].value === "" ? " " : arrayRegimen[i].value;
                expLab.telefono = arrayTelefono[i].value === "" ? " " : arrayTelefono[i].value; 
                expLab.fax = arrayFax[i].value === "" ? " " : arrayFax[i].value;
                expLab.indEliminacion = 0;
                colegiadoRep.expeLaborales[colegiadoRep.expeLaborales.length] = expLab;
            }
        }
    }
}

function analizarFamiliares(){
    var arrayHidden, arrayDependientes, arrayParentesco;
    var arrayEdad;
    var i, indice;
    var fam, tamArray;
    arrayHidden = $(".txtIndFamilia");
    arrayDependientes = $(".txtNombreDependientes");
    arrayParentesco = $(".txtParentesco");
    arrayEdad = $(".txtEdadParientes");
    
    if(arrayHidden !== undefined){
        tamArray = colegiadoRep.familiares.length;
        for(i = 0; i < arrayHidden.length; i++){
            indice = parseInt(arrayHidden[i].value);
            if(tamArray > 0){
                if(tamArray > indice){
                   colegiadoRep.familiares[i].nombre = arrayDependientes[i].value === "" ? " " : arrayDependientes[i].value;  
                   colegiadoRep.familiares[i].parentesco = arrayParentesco[i].value === "" ? " " : arrayParentesco[i].value;
                   colegiadoRep.familiares[i].edad = arrayEdad[i].value === "" ? " " : arrayEdad[i].value;
                   colegiadoRep.familiares[i].indEliminacion = 0;
                } else {
                    fam = new Familiar();
                    fam.nombre = arrayDependientes[i].value === "" ? " " : arrayDependientes[i].value;
                    fam.parentesco = arrayParentesco[i].value === "" ? " " : arrayParentesco[i].value;
                    fam.edad = arrayEdad[i].value === "" ? " " : arrayEdad[i].value;
                    fam.indEliminacion = 0;
                    colegiadoRep.familiares[colegiadoRep.familiares.length] = fam;
                }
            } else {
                fam = new Familiar();
                fam.nombre = arrayDependientes[i].value === "" ? " " : arrayDependientes[i].value;
                fam.parentesco = arrayParentesco[i].value === "" ? " " : arrayParentesco[i].value;
                fam.edad = arrayEdad[i].value === "" ? " " : arrayEdad[i].value;
                fam.indEliminacion = 0;
                colegiadoRep.familiares[colegiadoRep.familiares.length] = fam;
            }
        }
    }
}

function analizarGradosyTitulos(){
    var arrayHidden, arrayMaestrias, arrayEspecialidades;
    var arrayDoctorados, arraySocNac, arraySocInt, arrayRevC;
    var i, indice;
    var otroEstudio, tamArray;
    
    colegiadoRep.titulacion.univTitulacion = $("#txtUniversidad").val() === "" ? " " :  $("#txtUniversidad").val();
    colegiadoRep.titulacion.annoTitulacion = $("#txtAnnoTitulo").val() === "" ? " " : $("#txtAnnoTitulo").val();
    colegiadoRep.titulacion.numTitulo = $("#txtNumTitulo").val() === "" ? " " : $("#txtNumTitulo").val();
    colegiadoRep.titulacion.numRegistro = $("#txtNumRegistro").val() === "" ? " " : $("#txtNumRegistro").val();
            
    arrayHidden = $(".txtIndEspecialidades");
    arrayEspecialidades = $(".txtEspecialidad");
    tamArray = colegiadoRep.otrosEstudios.length;
    if(arrayHidden !== undefined){
        for(i = 0; i < arrayHidden.length; i++){
            indice = parseInt(arrayHidden[i].value);
            if(tamArray > 0){
                if(tamArray > indice){
                   colegiadoRep.otrosEstudios[i].denominacion = arrayEspecialidades[i].value === "" ? " " : arrayEspecialidades[i].value;  
                   colegiadoRep.otrosEstudios[i].tipo = 1;
                   colegiadoRep.otrosEstudios[i].indEliminacion = 0;
                } else {
                    otroEstudio = new OtrosGrados();
                    otroEstudio.denominacion = arrayEspecialidades[i].value === "" ? " " : arrayEspecialidades[i].value;
                    otroEstudio.tipo = 1;
                    otroEstudio.indEliminacion = 0;
                    colegiadoRep.otrosEstudios[colegiadoRep.otrosEstudios.length] = otroEstudio;
                }
            } else {
                otroEstudio = new OtrosGrados();
                otroEstudio.denominacion = arrayEspecialidades[i].value === "" ? " " : arrayEspecialidades[i].value;
                otroEstudio.tipo = 1;
                otroEstudio.indEliminacion = 0;
                colegiadoRep.otrosEstudios[colegiadoRep.otrosEstudios.length] = otroEstudio;
            }
        }
    }
    
    arrayHidden = $(".txtIndMaestrias");
    arrayMaestrias = $(".txtMaestria");
    
    if(arrayHidden !== undefined){
        for(i = 0; i < arrayHidden.length; i++){
            indice = parseInt(arrayHidden[i].value);
            if(tamArray > 0){
                if(tamArray > indice){
                   colegiadoRep.otrosEstudios[i].denominacion = arrayMaestrias[i].value === "" ? " ":arrayMaestrias[i].value;  
                   colegiadoRep.otrosEstudios[i].tipo = 2;
                   colegiadoRep.otrosEstudios[i].indEliminacion = 0;
                } else {
                    otroEstudio = new OtrosGrados();
                    otroEstudio.denominacion = arrayMaestrias[i].value === "" ? " ": arrayMaestrias[i].value;
                    otroEstudio.tipo = 2;
                    otroEstudio.indEliminacion = 0;
                    colegiadoRep.otrosEstudios[colegiadoRep.otrosEstudios.length] = otroEstudio;
                }
            } else {
                otroEstudio = new OtrosGrados();
                otroEstudio.denominacion = arrayMaestrias[i].value === "" ? " ": arrayMaestrias[i].value;
                otroEstudio.tipo = 2;
                otroEstudio.indEliminacion = 0;
                colegiadoRep.otrosEstudios[colegiadoRep.otrosEstudios.length] = otroEstudio;
            }
        }
    }
    
    arrayHidden = $(".txtIndDoctorado");
    arrayDoctorados = $(".txtDoctorado");
    
    if(arrayHidden !== undefined){
        for(i = 0; i < arrayHidden.length; i++){
            indice = parseInt(arrayHidden[i].value);
            if(tamArray > 0){
                if(tamArray > indice){
                   colegiadoRep.otrosEstudios[i].denominacion = arrayDoctorados[i].value === "" ? " ": arrayDoctorados[i].value;  
                   colegiadoRep.otrosEstudios[i].tipo = 3;
                   colegiadoRep.otrosEstudios[i].indEliminacion = 0;
                } else {
                    otroEstudio = new OtrosGrados();
                    otroEstudio.denominacion = arrayDoctorados[i].value === ""? " ": arrayDoctorados[i].value;
                    otroEstudio.tipo = 3;
                    otroEstudio.indEliminacion = 0;
                    colegiadoRep.otrosEstudios[colegiadoRep.otrosEstudios.length] = otroEstudio;
                }
            } else {
                otroEstudio = new OtrosGrados();
                otroEstudio.denominacion = arrayDoctorados[i].value === ""? " ": arrayDoctorados[i].value;
                otroEstudio.tipo = 3;
                otroEstudio.indEliminacion = 0;
                colegiadoRep.otrosEstudios[colegiadoRep.otrosEstudios.length] = otroEstudio;
            }
        }
    }
    
    arrayHidden = $(".txtIndSocNac");
    arraySocNac = $(".txtSocCientificasNac");
    
    if(arrayHidden !== undefined){
        for(i = 0; i < arrayHidden.length; i++){
            indice = parseInt(arrayHidden[i].value);
            if(tamArray > 0){
                if(tamArray > indice){
                   colegiadoRep.otrosEstudios[i].denominacion = arraySocNac[i].value === "" ? " ": arraySocNac[i].value;  
                   colegiadoRep.otrosEstudios[i].tipo = 4;
                   colegiadoRep.otrosEstudios[i].indEliminacion = 0;
                } else {
                    otroEstudio = new OtrosGrados();
                    otroEstudio.denominacion = arraySocNac[i].value === "" ? " ":arraySocNac[i].value;
                    otroEstudio.tipo = 4;
                    otroEstudio.indEliminacion = 0;
                    colegiadoRep.otrosEstudios[colegiadoRep.otrosEstudios.length] = otroEstudio;
                }
            } else {
                otroEstudio = new OtrosGrados();
                otroEstudio.denominacion = arraySocNac[i].value === "" ? " ":arraySocNac[i].value;
                otroEstudio.tipo = 4;
                otroEstudio.indEliminacion = 0;
                colegiadoRep.otrosEstudios[colegiadoRep.otrosEstudios.length] = otroEstudio;
            }
        }
    }
    
    arrayHidden = $(".txtIndSocIntNac");
    arraySocInt = $(".txtSocCientificasIntnac");
    
    if(arrayHidden !== undefined){
        for(i = 0; i < arrayHidden.length; i++){
            indice = parseInt(arrayHidden[i].value);
            if(tamArray > 0){
                if(tamArray > indice){
                   colegiadoRep.otrosEstudios[i].denominacion = arraySocInt[i].value === "" ? " " : arraySocInt[i].value;  
                   colegiadoRep.otrosEstudios[i].tipo = 5;
                   colegiadoRep.otrosEstudios[i].indEliminacion = 0;
                } else {
                    otroEstudio = new OtrosGrados();
                    otroEstudio.denominacion = arraySocInt[i].value === "" ? " " : arraySocInt[i].value;
                    otroEstudio.tipo = 5;
                    otroEstudio.indEliminacion = 0;
                    colegiadoRep.otrosEstudios[colegiadoRep.otrosEstudios.length] = otroEstudio;
                }
            } else {
                otroEstudio = new OtrosGrados();
                otroEstudio.denominacion = arraySocInt[i].value === "" ? " " : arraySocInt[i].value;
                otroEstudio.tipo = 5;
                otroEstudio.indEliminacion = 0;
                colegiadoRep.otrosEstudios[colegiadoRep.otrosEstudios.length] = otroEstudio;
            }
        }
    }
    
    arrayHidden = $(".txtIndRevC");
    arrayRevC = $(".txtRevCientifica");
    
    if(arrayHidden !== undefined){
        for(i = 0; i < arrayHidden.length; i++){
            indice = parseInt(arrayHidden[i].value);
            if(tamArray > 0){
                if(tamArray > indice){
                   colegiadoRep.otrosEstudios[i].denominacion = arrayRevC[i].value === "" ? " ":arrayRevC[i].value;  
                   colegiadoRep.otrosEstudios[i].tipo = 6;
                   colegiadoRep.otrosEstudios[i].indEliminacion = 0;
                } else {
                    otroEstudio = new OtrosGrados();
                    otroEstudio.denominacion = arrayRevC[i].value === "" ? " ":arrayRevC[i].value;
                    otroEstudio.tipo = 6;
                    otroEstudio.indEliminacion = 0;
                    colegiadoRep.otrosEstudios[colegiadoRep.otrosEstudios.length] = otroEstudio;
                }
            } else {
                otroEstudio = new OtrosGrados();
                otroEstudio.denominacion = arrayRevC[i].value === "" ? " ":arrayRevC[i].value;
                otroEstudio.tipo = 6;
                otroEstudio.indEliminacion = 0;
                colegiadoRep.otrosEstudios[colegiadoRep.otrosEstudios.length] = otroEstudio;  
            }
        }
    }
}

function limpiarTodo(){
    $("#txtFechaInscripcion").val("");
    $("#txtFechaIngreso").val("");
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
    $("#expLaborales").html("");
    $("#datosFamilia").html("");
    $("#txtUniversidad").val("");
    $("#txtAnnoTitulo").val("");
    $("#txtNumTitulo").val("");
    $("#txtNumRegistro").val("");
    $("#especialidades").html("");
    $("#maestrias").html("");
    $("#doctorados").html("");
    $("#sociedadesNacioneales").html("");
    $("#SociedadesInternacionales").html("");
    $("#revistaCientifica").html("");
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

function crearFilaExperienciaLaboral(){
    var html = "";
    html += '<div class="row form-group" id="expLab'+gnumExpLaboral+'" >';
    html += '<input type="hidden" value="'+gnumExpLaboral+'" class="txtIndExpLaboral" />';
    html += '<div class="col-lg-offset-1 col-lg-4">';
    html += '<label for="centroLaboral">Centro Laboral:</label>';
    html += '<input type="text" class="form-control txtCentroLaboral" name="txtCentroLaboral" placeholder = "Centro Laboral">';
    html += '</div>';
    html += '<div class="col-lg-2">';
    html += '<label for="regimenLaboral">Regimen Laboral:</label>';
    html += '<input type="text" class="form-control txtRegimenLaboral" name="txtRegimenLaboral" placeholder = "Regimen Laboral">';
    html += '</div>';
    html += '<div class="col-lg-2">';
    html += '<label for="telefonoLaboral">Telefono:</label>';
    html += '<input type="text" class="form-control txtTelefonoLaboral" name="txtTelefonoLaboral" placeholder = "Telefono">';
    html += '</div>';
    html += '<div class="col-lg-2">';
    html += '<label for="faxLaboral">Fax:</label>';
    html += '<input type="text" class="form-control txtFaxLaboral" name="txtFaxLaboral" placeholder = "Fax">';
    html += '</div>';
    html += '<div class="col-lg-1">';
    html += '<label>Quitar</label>';
    html += '<a class="btn btn-danger" onclick="cerrarExpLab(\'expLab'+gnumExpLaboral+'\')"><span class="glyphicon glyphicon-minus"></span></a>';
    html += '</div>';
    html += '</div>';
    $("#expLaborales").append(html);
    gnumExpLaboral++;
}

function cerrarExpLab(id){
    var indice = parseInt($("#"+id+" .txtIndExpLaboral").val());
    if (colegiadoRep.expeLaborales.length > 0){
        if (colegiadoRep.expeLaborales.length > indice){
            colegiadoRep.expeLaborales[indice].indEliminacion = 1;
        }
    }    
    $('#'+id).remove();
}

function cerrar(id){
    $('#'+id).remove();
}

function crearFilaFamilia(){
    var html = "";
    html += '<div class="row form-group" id="datoFam'+gnumDatoFamilia+'">';
    html += '<input type="hidden" value="'+gnumDatoFamilia+'" class="txtIndFamilia" />';
    html += '<div class="col-lg-offset-1 col-lg-6">';
    html += '<label for="nombreDependientes">Nombre de Dependientes:</label>';
    html += '<input type="text" class="form-control txtNombreDependientes" name="txtNombreDependientes" placeholder = "Nombre Dependientes">';
    html += '</div>';
    html += '<div class="col-lg-2">';
    html += '<label for="parentesco">Parentesco:</label>';
    html += '<input type="text" class="form-control txtParentesco" name="txtParentesco" placeholder = "Parentesco">';
    html += '</div>';
    html += '<div class="col-lg-1">';
    html += '<label for="edadPariente">Edad:</label>';
    html += '<input type="text" class="form-control txtEdadParientes" name="txtEdadParientes" placeholder = "Edad">';
    html += '</div>';
    html += '<div class="col-lg-1">';
    html += '<label>Quitar</label>';
    html += '<a class="btn btn-danger" onclick="cerrarFamilia(\'datoFam'+gnumDatoFamilia+'\')"><span class="glyphicon glyphicon-minus"></span></a>';
    html += '</div>';
    html += '</div>';
    $("#datosFamilia").append(html);
    gnumDatoFamilia++;
}

function cerrarFamilia(id){
    var indice = parseInt($("#"+id+" .txtIndFamilia").val());
    if (colegiadoRep.familiares.length > 0){
        if (colegiadoRep.familiares.length > indice){
            colegiadoRep.familiares[indice].indEliminacion = 1;
        }
    }    
    $('#'+id).remove();
}

function crearFilaEspecialidad(){
    var html = "";
    html += '<div class="row form-group" id="esp'+gnumOtrosEstudios+'">';
    html += '<input type="hidden" value="'+gnumOtrosEstudios+'" class="txtIndEspecialidades" />';
    html += '<div class="col-lg-9">';
    html += '<input type="text" class="form-control txtEspecialidad" name="txtEspecialidad" placeholder = "Especialidad">';
    html += '</div>';
    html += '<div class="col-lg-2">';
    html += '<a class="btn btn-danger" onclick="cerrarEspecialidad(\'esp'+gnumOtrosEstudios+'\')" role="button"><span class="glyphicon glyphicon-minus"></span></a>';
    html += '</div>';
    html += '</div>';
    $("#especialidades").append(html);
    gnumOtrosEstudios++;
}

function cerrarEspecialidad(id){
    var indice = parseInt($("#"+id+" .txtIndEspecialidades").val());
    if (colegiadoRep.otrosEstudios.length > 0){
        if(colegiadoRep.otrosEstudios.length > indice){
            colegiadoRep.otrosEstudios[indice].indEliminacion = 1;
        }
    }
    $('#'+id).remove();
}

function crearFilaMaestrias(){
    var html = "";
    html += '<div class="row form-group" id="maes'+gnumOtrosEstudios+'">';
    html += '<input type="hidden" value="'+gnumOtrosEstudios+'" class="txtIndMaestrias" />';
    html += '<div class="col-lg-9">';
    html += '<input type="text" class="form-control txtMaestria" name="txtMaestria" placeholder = "Maestria">';
    html += '</div>';
    html += '<div class="col-lg-2">';
    html += '<a class="btn btn-danger" onclick="cerrarMaestria(\'maes'+gnumOtrosEstudios+'\')" role="button"><span class="glyphicon glyphicon-minus"></span></a>';
    html += '</div>';
    html += '</div>';
    $("#maestrias").append(html);
    gnumOtrosEstudios++;
}

function cerrarMaestria(id){
    var indice = parseInt($("#"+id+" .txtIndMaestrias").val());
    if (colegiadoRep.otrosEstudios.length > 0){
        if(colegiadoRep.otrosEstudios.length > indice){
            colegiadoRep.otrosEstudios[indice].indEliminacion = 1;
        }
    }
    $('#'+id).remove();
}

function crearFilaDoctorado(){
    var html = "";
    html += '<div class="row form-group" id="doc'+gnumOtrosEstudios+'">';
    html += '<input type="hidden" value="'+gnumOtrosEstudios+'" class="txtIndDoctorado" />';
    html += '<div class="col-lg-9">';
    html += '<input type="text" class="form-control txtDoctorado" name="txtDoctorado" placeholder = "Doctorado">';
    html += '</div>';
    html += '<div class="col-lg-2">';
    html += '<a class="btn btn-danger" onclick="cerrarDoctorado(\'doc'+gnumOtrosEstudios+'\')" role="button"><span class="glyphicon glyphicon-minus"></span></a>';
    html += '</div>';
    html += '</div>';
    $("#doctorados").append(html);
    gnumOtrosEstudios++;
}

function cerrarDoctorado(id){
    var indice = parseInt($("#"+id+" .txtIndDoctorado").val());
    if (colegiadoRep.otrosEstudios.length > 0){
        if(colegiadoRep.otrosEstudios.length > indice){
            colegiadoRep.otrosEstudios[indice].indEliminacion = 1;
        }
    }
    $('#'+id).remove();
}

function crearFilaSocNacional(){
    var html = "";
    html += '<div class="row form-group" id="socNac'+gnumOtrosEstudios+'">';
    html += '<input type="hidden" value="'+gnumOtrosEstudios+'" class="txtIndSocNac" />';
    html += '<div class="col-lg-9">';
    html += '<input type="text" class="form-control txtSocCientificasNac" name="txtSocCientificasNac" placeholder = "Soc. Cientifica Nacional">';
    html += '</div>';
    html += '<div class="col-lg-2">';
    html += '<a class="btn btn-danger" onclick="cerrarSocNac(\'socNac'+gnumOtrosEstudios+'\')" role="button"><span class="glyphicon glyphicon-minus"></span></a>';
    html += '</div>';
    html += '</div>';
    $("#sociedadesNacioneales").append(html);
    gnumOtrosEstudios++;
}

function cerrarSocNac(id){
    var indice = parseInt($("#"+id+" .txtIndSocNac").val());
    if (colegiadoRep.otrosEstudios.length > 0){
        if(colegiadoRep.otrosEstudios.length > indice){
            colegiadoRep.otrosEstudios[indice].indEliminacion = 1;
        }
    }
    $('#'+id).remove();
}

function crearFilaSocInternacional(){
    var html = "";
    html += '<div class="row form-group" id="socInt'+gnumOtrosEstudios+'">';
    html += '<input type="hidden" value="'+gnumOtrosEstudios+'" class="txtIndSocIntNac" />';
    html += '<div class="col-lg-9">';
    html += '<input type="text" class="form-control txtSocCientificasIntnac" name="txtSocCientificasIntnac" placeholder = "Soc. Cientifica Internacional">';
    html += '</div>';
    html += '<div class="col-lg-2">';
    html += '<a class="btn btn-danger" onclick="cerrarSocInt(\'socInt'+gnumOtrosEstudios+'\')" role="button"><span class="glyphicon glyphicon-minus"></span></a>';
    html += '</div>';
    html += '</div>';
    $("#SociedadesInternacionales").append(html);
    gnumOtrosEstudios++;
}

function cerrarSocInt(id){
    var indice = parseInt($("#"+id+" .txtIndSocIntNac").val());
    if (colegiadoRep.otrosEstudios.length > 0){
        if(colegiadoRep.otrosEstudios.length > indice){
            colegiadoRep.otrosEstudios[indice].indEliminacion = 1;
        }
    }
    $('#'+id).remove();
}

function crearFilaRevCientifica(){
    var html = "";
    html += '<div class="row form-group" id="revC'+gnumOtrosEstudios+'">';
    html += '<input type="hidden" value="'+gnumOtrosEstudios+'" class="txtIndRevC" />';
    html += '<div class="col-lg-9">';
    html += '<input type="text" class="form-control txtRevCientifica" name="txtRevCientifica" placeholder = "Revista Cientifica">';
    html += '</div>';
    html += '<div class="col-lg-2">';
    html += '<a class="btn btn-danger" onclick="cerrarRevC(\'revC'+gnumOtrosEstudios+'\')" role="button"><span class="glyphicon glyphicon-minus"></span></a>';
    html += '</div>';
    html += '</div>';
    $("#revistaCientifica").append(html);
    gnumOtrosEstudios++;
}

function cerrarRevC(id){
    var indice = parseInt($("#"+id+" .txtIndRevC").val());
    if (colegiadoRep.otrosEstudios.length > 0){
        if(colegiadoRep.otrosEstudios.length > indice){
            colegiadoRep.otrosEstudios[indice].indEliminacion = 1;
        }
    }
    $('#'+id).remove();
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