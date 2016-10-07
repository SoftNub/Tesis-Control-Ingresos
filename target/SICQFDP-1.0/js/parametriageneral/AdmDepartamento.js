/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var table;
var table2;
$(document).ready(function(){
    var pGrabarEditar;
    var pEliminar;
    var pConsultar;
    var pOpciones;
    var pError;
    pGrabarEditar = $("#panelGrabarEditar");
    pEliminar = $("#panelEliminar");
    pConsultar = $("#panelConsultar");
    pOpciones = $("#panelOpciones"); 
    pError = $("#panelError");
    listarParametria();
    
    $("#Opcgrabar").click(function(){
       pConsultar.hide();
       pConsultar.html("");
       pOpciones.hide();
       armarFormulario(1, 0);
       pGrabarEditar.show();
       pOpciones.html("");
   });

   $("#Opceditar").click(function(){
       var html;
       pError.hide();
       pError.html("");
       html = '<div class="alert alert-danger">';
       html += '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>';
       html += '<strong>Error!</strong> Debe seleccionar un registro.</div>';
       id = parseInt(table.$('tr.selected').attr("id"));
       if (!Number.isNaN(id)){
            pConsultar.hide();
            pConsultar.html("");
            pOpciones.hide();
            armarFormulario(2,id);
            llenarFormulario(id);
            pGrabarEditar.show();
            pOpciones.html("");
       } else {
           pError.html(html);
           pError.show();
       }
   });
   $("#Opceliminar").click(function(){
      var htmlPrin;
       var html;
       pError.hide();
       pError.html("");
       html = '<div class="alert alert-danger">';
       html += '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>';
       html += '<strong>Error!</strong> Debe seleccionar un registro.</div>';
       id = parseInt(table.$('tr.selected').attr("id"));
       if(!Number.isNaN(id)){
           pConsultar.hide();
           pConsultar.html("");
           pOpciones.hide();
           armarFormulario(3,id);
           pEliminar.show();
       } else {
           pError.html(html);
           pError.show();
       }
   });
   
    $("#OpcAdd").click(function(){
       pError.hide();
       pError.html("");
       var html = '<div class="alert alert-danger">';
       html += '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>';
       html += '<strong>Error!</strong> Debe seleccionar un registro.</div>';
       id = parseInt(table.$('tr.selected').attr("id"));
       if(!Number.isNaN(id)){
           pConsultar.hide();
           pConsultar.html("");
           pOpciones.hide();
           $("#panelErrorAdd").hide();
           $("#panelErrorAdd").html("");
           armarOpcionesAdd();
           armarFormularioAdd();
           llenarTablaAdd(id);
           $("#panelAdd").show();
           pOpciones.html("");
       } else {
           pError.html(html);
           pError.show();
       }
   });
});

function listarParametria(){
    var departamento;
    var listaDepartamento;
    var htmlAlert;
    var $strong;
    var $tr;
    var $td;
    var $tbody;
    var html;
    departamento = new Departamento();
    listaDepartamento = departamento.listarAllDepartamento();
    if(listaDepartamento.indError === 0){
        html = "<table id='listaDepartamentos' class='table table-bordered table-hover' ><thead>";
        html += "<th>Descripcion</th></thead><tbody id='bodyDepartamentos'></tbody></table>";
        $("#panelConsultar").html(html);
        $tbody = $("#bodyDepartamentos");
        for ( var i = 0; i < listaDepartamento.Departamentos.length; i++) {
            $tr = $("<tr/>",{
               id : listaDepartamento.Departamentos[i].idDepartamento 
            });
            $td = $("<td/>",{
               text : listaDepartamento.Departamentos[i].nombreDepartamento  
            });
            $tr.append($td);
            $tbody.append($tr);
        }
        table = $('#listaDepartamentos').DataTable();
        //permite marcar fila
        $('#listaDepartamentos tbody').on( 'click', 'tr', function () {
            if ( $(this).hasClass('selected') ) {
                $(this).removeClass('selected');
            }
            else {
                table.$('tr.selected').removeClass('selected');
                $(this).addClass('selected');
            }
        });            
    }else{
        htmlAlert = '<div class="alert alert-danger">';
        htmlAlert += '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a></div>';
        $strong = $("<strong/>",{
            text : listaDepartamento.msjError
        });
        $("#panelError div").append($strong);
        $("#panelError").show();
    }   
}


function armarFormulario(opt, id){
    switch (opt){
        case 1:
            formularioGrabarEditar(opt, "Grabar", id);
            break;
        case 2:    
            formularioGrabarEditar(opt, "Editar", id);
            break;
        case 3:
            formularioEliminar("Eliminar", id);
            break;
    }
    
}

function formularioGrabarEditar(opt, nombreOpt, id){
    var html;
    var pError;
    pError = $("#panelError");
    html = '<div class="panel panel-primary">';
    html += '<div class="panel-heading">'+nombreOpt+' Departamento</div>';
    html += '<div class="panel-body">';
    html +=  '<form role="form">';
    html += '<div class="form-group"><label>Descripcion</label>';
    html += '<input type="text" class="form-control col-lg-4" id="descDepartamento"></div>';
    html += '<div class="checkbox"><label><input type="checkbox" id="habilitado" checked> Habilitado';
    html += '</label></div>';
    html += '<button type="button" id="'+nombreOpt+'" class="btn btn-primary">'+nombreOpt+'</button>';
    html += '&nbsp;&nbsp;&nbsp;<button type="button" id="Regresar" class="btn btn-primary">Regresar</button>';
    html += '</form><br/>';
    html += '</div>';
    html += '</div>';
    $("#panelGrabarEditar").html(html);
    
    $("#"+nombreOpt).click(function(){
        pError.hide();
        pError.html("");
        grabarEditarParametria(opt, id);
   });
   
    $("#Regresar").click(function(){
        regresarConsultar();
   });
}

function llenarFormulario(id){
    var htmlAlert;
    var pError;
    var $strong;
    var departamento, departamentoRep;
    pError = $("#panelError");
    departamento = new Departamento();
    departamento.idDepartamento = id;
    departamentoRep = departamento.listarDepartamentoId();
    if(departamentoRep.indError === 0){
        $("#descDepartamento").val(departamentoRep.nombreDepartamento);
        if (departamentoRep.habilitado===1){
            $("#habilitado").attr("checked", true);
        } else {
            $("#habilitado").attr("checked", false);
        } 
    } else {
        htmlAlert = '<div class="alert alert-danger">';
        htmlAlert += '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>';
        htmlAlert += '</div>';
        pError.html(htmlAlert);
        $strong = $("<strong/>",{
            text : departamentoRep.msjError
        });
        $("#panelError div").append($strong); 
        pError.show();
    }
}

function formularioEliminar(nombreOpt, id){
    var html;
    html = '<div class="panel panel-primary">';
    html += '<div class="panel-heading">'+nombreOpt+' Departamento</div>';
    html += '<div class="panel-body">';
    html +=  '<form role="form">';
    html += '<div class="form-group"><label>¿Esta seguro(a) que desea eliminar el Departamento?</label></div>';
    html += '<button type="button" id="'+nombreOpt+'" class="btn btn-primary">SI</button>';
    html += '&nbsp;&nbsp;&nbsp;<button type="button" id="RegresarElim" class="btn btn-primary">NO</button>';
    html += '</form>';
    html += '</div>';
    html += '</div>';
    $("#panelEliminar").html(html);
    
    $("#"+nombreOpt).click(function(){
        eliminarParametria(id);
   });
   
    $("#RegresarElim").click(function(){
        regresarConsultar();
   });
}

function grabarEditarParametria(opt, id){
    var departamento;
    var departamentoRep;
    var descParametria;
    var habilitad;
    var htmlAlert;
    var $strong;
    var pError;
    descParametria = $("#descDepartamento").val();
    departamento = new Departamento();
    pError =  $("#panelError");
    if ($("#habilitado").is(':checked')){
        habilitad = 1;
    } else {
        habilitad = 0;
    }
    if(opt === 2){
        departamento.idDepartamento = id;
        departamento.nombreDepartamento = descParametria;
        departamento.habilitado = habilitad;
        departamentoRep = departamento.editarDepartamento();
    } else {
        departamento.nombreDepartamento = descParametria;
        departamento.habilitado = habilitad;
        departamentoRep = departamento.grabarDepartamento();
    }
    
    if(departamentoRep.indError === 0){
        htmlAlert = '<div class="alert alert-success">';
        htmlAlert += '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>';
        htmlAlert += '</div>';
        pError.html(htmlAlert);
        $strong = $("<strong/>",{
            text : departamentoRep.msjError
        });
        $("#panelError div").append($strong);       
        if(opt === 1){
            limpiarFormularioGrabarEditar();
        }
        pError.show();
    } else {
        htmlAlert = '<div class="alert alert-danger">';
        htmlAlert += '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>';
        htmlAlert += '</div>';
        pError.html(htmlAlert);
        $strong = $("<strong/>",{
            text : departamentoRep.msjError
        });
        $("#panelError div").append($strong); 
        pError.show();
    }
}

function eliminarParametria(id){
    var departamento;
    var departamentoRep;
    var htmlAlert;
    var $strong;
    var pError;
    departamento = new Departamento();
    pError =  $("#panelError");
    departamento.idDepartamento = id;
    departamentoRep = departamento.eliminarDepartamento();
    if(departamentoRep.indError === 0){
        htmlAlert = '<div class="alert alert-success">';
        htmlAlert += '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>';
        htmlAlert += '</div>';
        pError.html(htmlAlert);
        $strong = $("<strong/>",{
            text : departamentoRep.msjError
        });
        $("#panelError div").append($strong);       
        pError.show();
        setTimeout(function(){ regresarConsultar(); }, 1000);
    } else {
        htmlAlert = '<div class="alert alert-danger">';
        htmlAlert += '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>';
        htmlAlert += '</div>';
        pError.html(htmlAlert);
        $strong = $("<strong/>",{
            text : departamentoRep.msjError
        });
        $("#panelError div").append($strong); 
        pError.show();
    }         
}

function regresarConsultar(){
    var pGrabarEditar;
    var pEliminar;
    var pError;
    var pErrorAdd;
    var pOpcionesAdd;
    var pTablaAdd;
    var pFormularioAdd;
    pGrabarEditar =  $("#panelGrabarEditar");
    pEliminar = $("#panelEliminar");
    pError = $("#panelError");
    pErrorAdd = $("#panelErrorAdd");
    pOpcionesAdd = $("#panelOpcionesAdd");
    pTablaAdd = $("#panelTablaAdd");
    pFormularioAdd = $("#panelFormularioAdd");
    generarOpciones();
    listarParametria();
    pGrabarEditar.hide();
    pGrabarEditar.html("");
    pEliminar.hide();
    pEliminar.html("");
    pError.hide();
    pError.html("");
    pErrorAdd.html("");
    pOpcionesAdd.html("");
    pTablaAdd.html("");
    pFormularioAdd.html("");
    $("#panelOpciones").show();
    $("#panelConsultar").show();
}

function limpiarFormularioGrabarEditar() {
    $("#descDepartamento").val("");
    $("#habilitado").attr("checked", true);
}

function generarOpciones(){
    var html;
    var pGrabarEditar = $("#panelGrabarEditar");
    var pEliminar = $("#panelEliminar");
    var pConsultar = $("#panelConsultar");
    var pOpciones = $("#panelOpciones"); 
    var pError = $("#panelError");
    html = '<div class="btn-group" role="group">';
    html += '<button type="button" class="btn btn-primary" id="Opcgrabar" title="Grabar Departamento">';
    html += '<span class="glyphicon glyphicon-floppy-disk"></span>';
    html += '</button>';
    html += '</div>';
    html += '<div class="btn-group" role="group">';
    html += '<button type="button" class="btn btn-primary" id="Opceditar" title="Editar Departamento">';
    html +=  '<span class="glyphicon glyphicon-edit"></span>';
    html += '</button>';
    html += '</div>';
    html += '<div class="btn-group" role="group">';
    html += '<button type="button" class="btn btn-primary" id = "Opceliminar" title="Eliminar Departamento">';
    html += '<span class="glyphicon glyphicon-floppy-remove"></span>';
    html += '</button>';
    html += '</div>';
    html += '<div class="btn-group" role="group">';
    html += '<button type="button" class="btn btn-primary" id = "OpcAdd" title="Asociar Provincia a Departamento">';
    html += '<span class="glyphicon glyphicon-check"></span>';
    html += '</button>';
    html += '</div>';
    pOpciones.html(html);
    
    $("#Opcgrabar").click(function(){
       pConsultar.hide();
       pConsultar.html("");
       pOpciones.hide();
       armarFormulario(1, 0);
       pGrabarEditar.show();
       pOpciones.html("");
   });

   $("#Opceditar").click(function(){
       var html;
       pError.hide();
       pError.html("");
       var htmlPrin;
       html = '<div class="alert alert-danger">';
       html += '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>';
       html += '<strong>Error!</strong> Debe seleccionar un registro.</div>';
       id = parseInt(table.$('tr.selected').attr("id"));
       if (!Number.isNaN(id)){
           pConsultar.hide();
           pConsultar.html("");
           pOpciones.hide();
           armarFormulario(2,id);
           llenarFormulario(id);
           pGrabarEditar.show();
           pOpciones.html("");
       } else {
           pError.html(html);
           pError.show();
       }
   });
   $("#Opceliminar").click(function(){
       var htmlPrin;
       var html;
       pError.hide();
       pError.html("");
       html = '<div class="alert alert-danger">';
       html += '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>';
       html += '<strong>Error!</strong> Debe seleccionar un registro.</div>';
       id = parseInt(table.$('tr.selected').attr("id"));
       if(!Number.isNaN(id)){
           pConsultar.hide();
           pConsultar.html("");
           pOpciones.hide();
           armarFormulario(3,id);
           pEliminar.show();
       } else {
           pError.html(html);
           pError.show();
       }
   });
   
   $("#OpcAdd").click(function(){
       pError.hide();
       pError.html("");
       var html = '<div class="alert alert-danger">';
       html += '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>';
       html += '<strong>Error!</strong> Debe seleccionar un registro.</div>';
       id = parseInt(table.$('tr.selected').attr("id"));
       if(!Number.isNaN(id)){
           pConsultar.hide();
           pConsultar.html("");
           pOpciones.hide();
           $("#panelErrorAdd").hide();
           $("#panelErrorAdd").html("");
           armarOpcionesAdd();
           armarFormularioAdd();
           llenarTablaAdd(id);
           $("#panelAdd").show();
           pOpciones.html("");
       } else {
           pError.html(html);
           pError.show();
       }
   });
}

function armarFormularioAdd(){
    var html;
    var htmlAlert;
    var $strong;
    var $option;
    var provincia;
    var listaProvincia;
    html = '<form class="form-horizontal" role="form">';
    html += '<div class="form-group">';
    html += '<label class="control-label col-sm-2">Provincias</label>';
    html += '<div class="col-sm-4">';
    html += '<select class="form-control" id="listaParametria2">';
    html += '</select>';
    html += '</div>';
    html += '</div>';
    html += '</form>';
    $("#panelFormularioAdd").html(html);
    $("#listaParametria2").append('<option value="">Seleccione:</option>');
    provincia = new Provincia();
    provincia.habilitado = 1;
    listaProvincia = provincia.listarProvinciasHabilitadas();
    if (listaProvincia.indError === 0){
       for (var i = 0; i < listaProvincia.Provincias.length; i++){
           $option = $('<option/>',{
              value : listaProvincia.Provincias[i].idProvincia,
              text : listaProvincia.Provincias[i].nombreProvincia
           });
           $("#listaParametria2").append($option);  
       } 
    } else {
        htmlAlert = '<div class="alert alert-danger">';
        htmlAlert += '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a></div>';
        $("#panelError").html(htmlAlert);
        $strong = $("<strong/>",{
            text : listaProvincia.msjError
        });
        $("#panelError div").append($strong);
        $("#panelError").show();
    }
}

function armarOpcionesAdd(){
    var html;
    html = '<div class="btn-group" role="group">';
    html += '<button type="button" class="btn btn-primary" id="OpcgrabarAdd" title="Asociar Provincia">';
    html += '<span class="glyphicon glyphicon-floppy-disk"></span>';
    html += '</button>';
    html += '</div>';
    html += '<div class="btn-group" role="group">';
    html +=  '<button type="button" class="btn btn-primary" id = "OpceliminarAdd" title="Desasociar Provincia">';
    html += '<span class="glyphicon glyphicon-floppy-remove"></span>';
    html += '</button>';
    html += '</div>';
    html += '<div class="btn-group" role="group">';
    html += '<button type="button" class="btn btn-primary" id = "OpcregresarAdd" title="Regresar">';
    html += '<span class="glyphicon glyphicon-share"></span>';
    html += '</button>';
    html += '</div>';
    $("#panelOpcionesAdd").html(html);
    
   $("#OpcgrabarAdd").click(function(){
       $("#panelErrorAdd").hide();
       $("#panelErrorAdd").html("");
       var html = '<div class="alert alert-danger">';
       html += '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>';
       html += '<strong>Error!</strong> Debe seleccionar una opcion de la lista.</div>';
       id = parseInt(table.$('tr.selected').attr("id"));
       id2 = parseInt($("#listaParametria2").val());
       if(!Number.isNaN(id2)){
           grabarProvinciaDepartamento(id, id2);
       } else {
           $("#panelErrorAdd").html(html);
           $("#panelErrorAdd").show();
       }
   });
   
   $("#OpceliminarAdd").click(function(){
       $("#panelErrorAdd").hide();
       $("#panelErrorAdd").html("");
       var html = '<div class="alert alert-danger">';
       html += '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>';
       html += '<strong>Error!</strong> Debe seleccionar un registro de la tabla.</div>';
       id = parseInt(table.$('tr.selected').attr("id"));
       id2 = parseInt(table2.$('tr.selected').attr("id"));
       if(!Number.isNaN(id2)){
           eliminarProvinciaDepartamento(id, id2);
       } else {
           $("#panelErrorAdd").html(html);
           $("#panelErrorAdd").show();
       }
   });
   
   $("#OpcregresarAdd").click(function(){
        regresarConsultar();
   });
}

function llenarTablaAdd(id){
    var departamento;
    var listaProvincias;
    var htmlAlert;
    var html;
    var $tbody;
    var $tr;
    var $td;
    var $strong;
    departamento = new Departamento();
    departamento.idDepartamento = id;
    listaProvincias = departamento.listarProvinciaDeDepartamento();
    if(listaProvincias.indError === 0){
        html = "<table id='listaProvinciaDepartamento' class='table table-bordered table-hover' ><thead>";
        html += "<th>Descripcion</th></thead><tbody id='bodyProvinciaDepartamento'></tbody></table>";
        $("#panelTablaAdd").html(html);
        $tbody = $("#bodyProvinciaDepartamento");
        for ( var i = 0; i < listaProvincias.Provincias.length; i++) {
            $tr = $("<tr/>",{
               id : listaProvincias.Provincias[i].idProvincia 
            });
            $td = $("<td/>",{
               text : listaProvincias.Provincias[i].nombreProvincia  
            });
            $tr.append($td);
            $tbody.append($tr);
        }
        table2 = $('#listaProvinciaDepartamento').DataTable();
        //permite marcar fila
        $('#listaProvinciaDepartamento tbody').on( 'click', 'tr', function () {
            if ( $(this).hasClass('selected') ) {
                $(this).removeClass('selected');
            }
            else {
                table2.$('tr.selected').removeClass('selected');
                $(this).addClass('selected');
            }
        });            
    }else{
        htmlAlert = '<div class="alert alert-danger">';
        htmlAlert += '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a></div>';
        $("#panelError").html(htmlAlert);
        $strong = $("<strong/>",{
            text : listaProvincias.msjError
        });
        $("#panelError div").append($strong);
        $("#panelError").show();
    }   
}

function grabarProvinciaDepartamento(idDepartamento, idProvincia){
    var htmlAlert;
    var departamento;
    var departamentoRep;
    var pErrorAdd;
    var $strong;
    pErrorAdd = $("#panelErrorAdd");
    departamento = new Departamento();
    departamento.idDepartamento = idDepartamento;
    departamentoRep = departamento.grabarProvinciaDeDepartamento(idProvincia);
    if(departamentoRep.indError === 0){
        htmlAlert = '<div class="alert alert-success">';
        htmlAlert += '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>';
        htmlAlert += '</div>';
        pErrorAdd.html(htmlAlert);
        $strong = $("<strong/>",{
            text : departamentoRep.msjError
        });
        $("#panelErrorAdd div").append($strong);       
        pErrorAdd.show();
        llenarTablaAdd(idDepartamento);
    } else {
        htmlAlert = '<div class="alert alert-danger">';
        htmlAlert += '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>';
        htmlAlert += '</div>';
        pErrorAdd.html(htmlAlert);
        $strong = $("<strong/>",{
            text : departamentoRep.msjError
        });
        $("#panelErrorAdd div").append($strong); 
        pErrorAdd.show();
    }         
}

function eliminarProvinciaDepartamento(idDepartamento, idProvincia){
    var htmlAlert;
    var departamento;
    var departamentoRep;
    var pErrorAdd;
    var $strong;
    pErrorAdd = $("#panelErrorAdd");
    departamento = new Departamento();
    departamento.idDepartamento = idDepartamento;
    departamentoRep = departamento.eliminarProvinciaDeDepartamento(idProvincia);
    if(departamentoRep.indError === 0){
        htmlAlert = '<div class="alert alert-success">';
        htmlAlert += '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>';
        htmlAlert += '</div>';
        pErrorAdd.html(htmlAlert);
        $strong = $("<strong/>",{
            text : departamentoRep.msjError
        });
        $("#panelErrorAdd div").append($strong);       
        pErrorAdd.show();
        llenarTablaAdd(idDepartamento);
    } else {
        htmlAlert = '<div class="alert alert-danger">';
        htmlAlert += '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>';
        htmlAlert += '</div>';
        pErrorAdd.html(htmlAlert);
        $strong = $("<strong/>",{
            text : departamentoRep.msjError
        });
        $("#panelErrorAdd div").append($strong); 
        pErrorAdd.show();
    }         
}