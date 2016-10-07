/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var table;
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
});

/**
 * Genera una tabla con la lista de distritos.
 * @returns {void}
 */
function listarParametria(){
    var estadoCivil;
    var listaEstadosCiviles;
    var htmlAlert;
    var $strong;
    var $tr;
    var $td;
    var $tbody;
    var html;
    estadoCivil = new EstadoCivil();
    estadoCivil.idEstadoCivil = 0;
    estadoCivil.habilitado = -1;
    listaEstadosCiviles = estadoCivil.listEstadosCiviles(1);
    if(listaEstadosCiviles.indError === 0){
        html = "<table id='listaEstadosCiviles' class='table table-bordered table-hover' ><thead>";
        html += "<th>Descripcion</th><th>Abreviatura</th></thead><tbody id='bodyEstadosCiviles'></tbody></table>";
        $("#panelConsultar").html(html);
        $tbody = $("#bodyEstadosCiviles");
        for ( var i = 0; i < listaEstadosCiviles.EstadosCiviles.length; i++) {
            $tr = $("<tr/>",{
               id : listaEstadosCiviles.EstadosCiviles[i].idEstadoCivil 
            });
            $td = $("<td/>",{
               text : listaEstadosCiviles.EstadosCiviles[i].denominacion  
            });
            $tr.append($td);
            $td = $("<td/>",{
               text : listaEstadosCiviles.EstadosCiviles[i].abbr  
            });
            $tr.append($td);
            $tbody.append($tr);
        }
        table = $('#listaEstadosCiviles').DataTable();
        //permite marcar fila
        $('#listaEstadosCiviles tbody').on( 'click', 'tr', function () {
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
            text : listaEstadosCiviles.msjError
        });
        $("#panelError").html(htmlAlert);
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
    html += '<div class="panel-heading">'+nombreOpt+' Estado Civil</div>';
    html += '<div class="panel-body">';
    html +=  '<form role="form">';
    html += '<div class="form-group"><label>Descripcion</label>';
    html += '<input type="text" class="form-control col-lg-4" id="descEstadoCivil"></div>';
    html += '<div class="form-group"><label>Abreviatura</label>';
    html += '<input type="text" class="form-control col-lg-4" id="abbrEstadoCivil"></div>';
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
    var lista;
    var habilitado;
    var htmlAlert;
    var estadoCivil;
    var listaEstadoCivil;
    var $strong;
    var pError = $("#panelError");
    estadoCivil = new EstadoCivil();
    estadoCivil.idEstadoCivil = id;
    
    listaEstadoCivil = estadoCivil.listEstadosCiviles(2);
    
    if(listaEstadoCivil.indError === 0){
        $("#descEstadoCivil").val(listaEstadoCivil.EstadosCiviles[0].denominacion);
        $("#abbrEstadoCivil").val(listaEstadoCivil.EstadosCiviles[0].abbr);
        if(listaEstadoCivil.EstadosCiviles[0].habilitado === 1){
            $("#habilitado").attr("checked", true);
        } else {
            $("#habilitado").attr("checked", false);
        }
    } else {
        htmlAlert = '<div class="alert alert-danger">';
        htmlAlert += '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a></div>';
        pError.html(htmlAlert);
        $strong = $("<strong/>",{
            text : listaEstadoCivil.msjError
        });
        $("#panelError div").append($strong);
        pError.show();
    }
}

function formularioEliminar(nombreOpt, id){
    var html;
    html = '<div class="panel panel-primary">';
    html += '<div class="panel-heading">'+nombreOpt+' Estado Civil</div>';
    html += '<div class="panel-body">';
    html +=  '<form role="form">';
    html += '<div class="form-group"><label>¿Esta seguro(a) que desea eliminar el Estado Civil?</label></div>';
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
    var estadoCivil;
    var estadoCivilRep;
    var descParametria;
    var abbr;
    var habilitad;
    var htmlAlert;
    var $strong;
    var pError;
    descParametria = $("#descEstadoCivil").val();
    abbr = $("#abbrEstadoCivil").val();
    estadoCivil = new EstadoCivil();
    pError =  $("#panelError");
    if ($("#habilitado").is(':checked')){
        habilitad = 1;
    } else {
        habilitad = 0;
    }
    if(opt === 2){
        estadoCivil.idEstadoCivil = id;
        estadoCivil.denominacion = descParametria;
        estadoCivil.abbr = abbr;
        estadoCivil.habilitado = habilitad;
        estadoCivilRep = estadoCivil.mantEstadoCivil(2);
    } else {
        estadoCivil.denominacion = descParametria;
        estadoCivil.abbr = abbr;
        estadoCivil.habilitado = habilitad;
        estadoCivilRep = estadoCivil.mantEstadoCivil(1);
    }
    
    if(estadoCivilRep.indError === 0){
        htmlAlert = '<div class="alert alert-success">';
        htmlAlert += '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>';
        htmlAlert += '</div>';
        pError.html(htmlAlert);
        $strong = $("<strong/>",{
            text : estadoCivilRep.msjError
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
            text : estadoCivilRep.msjError
        });
        $("#panelError div").append($strong); 
        pError.show();
    }
}

function eliminarParametria(id){
    var estadoCivil;
    var estadoCivilRep;
    var htmlAlert;
    var $strong;
    var pError;
    estadoCivil = new EstadoCivil();
    pError =  $("#panelError");
    estadoCivil.idEstadoCivil = id;
    estadoCivilRep = estadoCivil.mantEstadoCivil(3);
    if(estadoCivilRep.indError === 0){
        htmlAlert = '<div class="alert alert-success">';
        htmlAlert += '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>';
        htmlAlert += '</div>';
        pError.html(htmlAlert);
        $strong = $("<strong/>",{
            text : estadoCivilRep.msjError
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
            text : estadoCivilRep.msjError
        });
        $("#panelError div").append($strong); 
        pError.show();
    }           
}

function regresarConsultar(){
    var pGrabarEditar;
    var pEliminar;
    var pError;
    pGrabarEditar =  $("#panelGrabarEditar");
    pEliminar = $("#panelEliminar");
    pError = $("#panelError");
    generarOpciones();
    listarParametria();
    pGrabarEditar.hide();
    pGrabarEditar.html("");
    pEliminar.hide();
    pEliminar.html("");
    pError.hide();
    pError.html("");
    $("#panelOpciones").show();
    $("#panelConsultar").show();
}

function limpiarFormularioGrabarEditar() {
    $("#descEstadoCivil").val("");
    $("#abbrEstadoCivil").val("");
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
    html += '<button type="button" class="btn btn-primary" id="Opcgrabar" title="Grabar Estado Civil">';
    html += '<span class="glyphicon glyphicon-floppy-disk"></span>';
    html += '</button>';
    html += '</div>';
    html += '<div class="btn-group" role="group">';
    html += '<button type="button" class="btn btn-primary" id="Opceditar" title="Editar Estado Civil">';
    html +=  '<span class="glyphicon glyphicon-edit"></span>';
    html += '</button>';
    html += '</div>';
    html += '<div class="btn-group" role="group">';
    html += '<button type="button" class="btn btn-primary" id = "Opceliminar" title="Eliminar Estado Civil">';
    html += '<span class="glyphicon glyphicon-floppy-remove"></span>';
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
}