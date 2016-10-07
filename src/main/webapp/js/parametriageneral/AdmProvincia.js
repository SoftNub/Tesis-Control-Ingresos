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
    var provincia;
    var listaProvincia;
    var htmlAlert;
    var $strong;
    var $tr;
    var $td;
    var $tbody;
    var html;
    provincia = new Provincia();
    listaProvincia = provincia.listarAllProvincias();
    if(listaProvincia.indError === 0){
        html = "<table id='listaProvincias' class='table table-bordered table-hover' ><thead>";
        html += "<th>Descripcion</th></thead><tbody id='bodyProvincias'></tbody></table>";
        $("#panelConsultar").html(html);
        $tbody = $("#bodyProvincias");
        for ( var i = 0; i < listaProvincia.Provincias.length; i++) {
            $tr = $("<tr/>",{
               id : listaProvincia.Provincias[i].idProvincia 
            });
            $td = $("<td/>",{
               text : listaProvincia.Provincias[i].nombreProvincia  
            });
            $tr.append($td);
            $tbody.append($tr);
        }
        table = $('#listaProvincias').DataTable();
        //permite marcar fila
        $('#listaProvincias tbody').on( 'click', 'tr', function () {
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
            text : listaProvincia.msjError
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
    html += '<div class="panel-heading">'+nombreOpt+' Provincia</div>';
    html += '<div class="panel-body">';
    html +=  '<form role="form">';
    html += '<div class="form-group"><label>Descripcion</label>';
    html += '<input type="text" class="form-control col-lg-4" id="descProvincia"></div>';
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
    var provincia, provinciaRep;
    pError = $("#panelError");
    provincia = new Provincia();
    provincia.idProvincia = id;
    provinciaRep = provincia.listarProvinciaId();
    if(provinciaRep.indError === 0){
        $("#descProvincia").val(provinciaRep.nombreProvincia);
        if (provinciaRep.habilitado===1){
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
            text : provinciaRep.msjError
        });
        $("#panelError div").append($strong); 
        pError.show();
    }
}

function formularioEliminar(nombreOpt, id){
    var html;
    html = '<div class="panel panel-primary">';
    html += '<div class="panel-heading">'+nombreOpt+' Provincia</div>';
    html += '<div class="panel-body">';
    html +=  '<form role="form">';
    html += '<div class="form-group"><label>¿Esta seguro(a) que desea eliminar la Provincia?</label></div>';
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
    var provincia;
    var provinciaRep;
    var descParametria;
    var habilitad;
    var htmlAlert;
    var $strong;
    var pError;
    descParametria = $("#descProvincia").val();
    provincia = new Provincia();
    pError =  $("#panelError");
    if ($("#habilitado").is(':checked')){
        habilitad = 1;
    } else {
        habilitad = 0;
    }
    if(opt === 2){
        provincia.idProvincia = id;
        provincia.nombreProvincia = descParametria;
        provincia.habilitado = habilitad;
        provinciaRep = provincia.editarProvincia();
    } else {
        provincia.nombreProvincia = descParametria;
        provincia.habilitado = habilitad;
        provinciaRep = provincia.grabarProvincia();
    }
    
    if(provinciaRep.indError === 0){
        htmlAlert = '<div class="alert alert-success">';
        htmlAlert += '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>';
        htmlAlert += '</div>';
        pError.html(htmlAlert);
        $strong = $("<strong/>",{
            text : provinciaRep.msjError
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
            text : provinciaRep.msjError
        });
        $("#panelError div").append($strong); 
        pError.show();
    }
}

function eliminarParametria(id){
    var provincia;
    var provinciaRep;
    var htmlAlert;
    var $strong;
    var pError;
    provincia = new Provincia();
    pError =  $("#panelError");
    provincia.idProvincia = id;
    provinciaRep = provincia.eliminarProvincia();
    if(provinciaRep.indError === 0){
        htmlAlert = '<div class="alert alert-success">';
        htmlAlert += '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>';
        htmlAlert += '</div>';
        pError.html(htmlAlert);
        $strong = $("<strong/>",{
            text : provinciaRep.msjError
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
            text : provinciaRep.msjError
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
    $("#descProvincia").val("");
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
    html += '<button type="button" class="btn btn-primary" id="Opcgrabar" title="Grabar Distrito">';
    html += '<span class="glyphicon glyphicon-floppy-disk"></span>';
    html += '</button>';
    html += '</div>';
    html += '<div class="btn-group" role="group">';
    html += '<button type="button" class="btn btn-primary" id="Opceditar" title="Editar Distrito">';
    html +=  '<span class="glyphicon glyphicon-edit"></span>';
    html += '</button>';
    html += '</div>';
    html += '<div class="btn-group" role="group">';
    html += '<button type="button" class="btn btn-primary" id = "Opceliminar" title="Eliminar Distrito">';
    html += '<span class="glyphicon glyphicon-floppy-remove"></span>';
    html += '</button>';
    html += '</div>';
    html += '<div class="btn-group" role="group">';
    html += '<button type="button" class="btn btn-primary" id = "OpcAdd" title="Asociar Distrito a Provincia">';
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
    var distrito;
    var listaDistrito;
    html = '<form class="form-horizontal" role="form">';
    html += '<div class="form-group">';
    html += '<label class="control-label col-sm-2">Distritos</label>';
    html += '<div class="col-sm-4">';
    html += '<select class="form-control" id="listaParametria2">';
    html += '</select>';
    html += '</div>';
    html += '</div>';
    html += '</form>';
    $("#panelFormularioAdd").html(html);
    $("#listaParametria2").append('<option value="">Seleccione:</option>');
    distrito = new Distrito();
    distrito.habilitado = 1;
    listaDistrito = distrito.listarDistritosHabilitados();
    if (listaDistrito.indError === 0){
       for (var i = 0; i < listaDistrito.Distritos.length; i++){
           $option = $('<option/>',{
              value : listaDistrito.Distritos[i].idDistrito,
              text : listaDistrito.Distritos[i].nombreDistrito
           });
           $("#listaParametria2").append($option);  
       } 
    } else {
        htmlAlert = '<div class="alert alert-danger">';
        htmlAlert += '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a></div>';
        $("#panelError").html(htmlAlert);
        $strong = $("<strong/>",{
            text : listaDistrito.msjError
        });
        $("#panelError div").append($strong);
        $("#panelError").show();
    }
}

function armarOpcionesAdd(){
    var html;
    html = '<div class="btn-group" role="group">';
    html += '<button type="button" class="btn btn-primary" id="OpcgrabarAdd" title="Asociar Distrito">';
    html += '<span class="glyphicon glyphicon-floppy-disk"></span>';
    html += '</button>';
    html += '</div>';
    html += '<div class="btn-group" role="group">';
    html +=  '<button type="button" class="btn btn-primary" id = "OpceliminarAdd" title="Desasociar Distrito">';
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
           grabarDistritoProvincia(id, id2);
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
           eliminarDistritoProvincia(id, id2);
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
    var provincia;
    var listaDistritos;
    var htmlAlert;
    var html;
    var $tbody;
    var $tr;
    var $td;
    var $strong;
    provincia = new Provincia();
    provincia.idProvincia = id;
    listaDistritos = provincia.listarDistritosDeProvincia();
    if(listaDistritos.indError === 0){
        html = "<table id='listaDistritosProvincia' class='table table-bordered table-hover' ><thead>";
        html += "<th>Descripcion</th></thead><tbody id='bodyDistritosProvincia'></tbody></table>";
        $("#panelTablaAdd").html(html);
        $tbody = $("#bodyDistritosProvincia");
        for ( var i = 0; i < listaDistritos.Distritos.length; i++) {
            $tr = $("<tr/>",{
               id : listaDistritos.Distritos[i].idDistrito 
            });
            $td = $("<td/>",{
               text : listaDistritos.Distritos[i].nombreDistrito  
            });
            $tr.append($td);
            $tbody.append($tr);
        }
        table2 = $('#listaDistritosProvincia').DataTable();
        //permite marcar fila
        $('#listaDistritosProvincia tbody').on( 'click', 'tr', function () {
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
            text : listaDistritos.msjError
        });
        $("#panelError div").append($strong);
        $("#panelError").show();
    }   
}

function grabarDistritoProvincia(idProvincia, idDistrito){
    var htmlAlert;
    var provincia;
    var provinciaRep;
    var pErrorAdd;
    var $strong;
    pErrorAdd = $("#panelErrorAdd");
    provincia = new Provincia();
    provincia.idProvincia = idProvincia;
    provinciaRep = provincia.grabarDistritoDeProvincia(idDistrito);
    if(provinciaRep.indError === 0){
        htmlAlert = '<div class="alert alert-success">';
        htmlAlert += '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>';
        htmlAlert += '</div>';
        pErrorAdd.html(htmlAlert);
        $strong = $("<strong/>",{
            text : provinciaRep.msjError
        });
        $("#panelErrorAdd div").append($strong);       
        pErrorAdd.show();
        llenarTablaAdd(idProvincia);
    } else {
        htmlAlert = '<div class="alert alert-danger">';
        htmlAlert += '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>';
        htmlAlert += '</div>';
        pErrorAdd.html(htmlAlert);
        $strong = $("<strong/>",{
            text : provinciaRep.msjError
        });
        $("#panelErrorAdd div").append($strong); 
        pErrorAdd.show();
    }         
}

function eliminarDistritoProvincia(idProvincia, idDistrito){
    var htmlAlert;
    var provincia;
    var provinciaRep;
    var pErrorAdd;
    var $strong;
    pErrorAdd = $("#panelErrorAdd");
    provincia = new Provincia();
    provincia.idProvincia = idProvincia;
    provinciaRep = provincia.eliminarDistritoDeProvincia(idDistrito);
    if(provinciaRep.indError === 0){
        htmlAlert = '<div class="alert alert-success">';
        htmlAlert += '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>';
        htmlAlert += '</div>';
        pErrorAdd.html(htmlAlert);
        $strong = $("<strong/>",{
            text : provinciaRep.msjError
        });
        $("#panelErrorAdd div").append($strong);       
        pErrorAdd.show();
        llenarTablaAdd(idProvincia);
    } else {
        htmlAlert = '<div class="alert alert-danger">';
        htmlAlert += '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>';
        htmlAlert += '</div>';
        pErrorAdd.html(htmlAlert);
        $strong = $("<strong/>",{
            text : provinciaRep.msjError
        });
        $("#panelErrorAdd div").append($strong); 
        pErrorAdd.show();
    }         
}