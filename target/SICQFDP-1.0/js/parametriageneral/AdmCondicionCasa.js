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
    var condicionCasa;
    var listaCondicionesCasas;
    var htmlAlert;
    var $strong;
    var $tr;
    var $td;
    var $tbody;
    var html;
    condicionCasa = new CondicionCasa();
//    condicionCasa.idCondicion = 0;
//    condicionCasa.habilitado = -1;
    listaCondicionesCasas = condicionCasa.listCondicionCasa(1);
    if(listaCondicionesCasas.indError === 0){
        html = "<table id='listaCondicionesCasas' class='table table-bordered table-hover' ><thead>";
        html += "<th>Descripcion</th></thead><tbody id='bodyCondicionesCasas'></tbody></table>";
        $("#panelConsultar").html(html);
        $tbody = $("#bodyCondicionesCasas");
        for ( var i = 0; i < listaCondicionesCasas.CondicionesCasa.length; i++) {
            $tr = $("<tr/>",{
               id : listaCondicionesCasas.CondicionesCasa[i].idCondicion 
            });
            $td = $("<td/>",{
               text : listaCondicionesCasas.CondicionesCasa[i].denominacion  
            });
            $tr.append($td);
            $tbody.append($tr);
        }
        table = $('#listaCondicionesCasas').DataTable();
        //permite marcar fila
        $('#listaCondicionesCasas tbody').on( 'click', 'tr', function () {
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
            text : listaCondicionesCasas.msjError
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
    html += '<div class="panel-heading">'+nombreOpt+' Condicion Casa</div>';
    html += '<div class="panel-body">';
    html +=  '<form role="form">';
    html += '<div class="form-group"><label>Descripcion</label>';
    html += '<input type="text" class="form-control col-lg-4" id="descCondicionCasa"></div>';
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
    var condicionCasa;
    var listaCondicionesCasas;
    var $strong;
    var pError = $("#panelError");
    condicionCasa = new CondicionCasa();
    condicionCasa.idCondicion = id;
    
    listaCondicionesCasas = condicionCasa.listCondicionCasa(2);
    
    if(listaCondicionesCasas.indError === 0){
        $("#descCondicionCasa").val(listaCondicionesCasas.CondicionesCasa[0].denominacion);
        if(listaCondicionesCasas.CondicionesCasa[0].habilitado === 1){
            $("#habilitado").attr("checked", true);
        } else {
            $("#habilitado").attr("checked", false);
        }
    } else {
        htmlAlert = '<div class="alert alert-danger">';
        htmlAlert += '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a></div>';
        pError.html(htmlAlert);
        $strong = $("<strong/>",{
            text : listaCondicionesCasas.msjError
        });
        $("#panelError div").append($strong);
        pError.show();
    }
}

function formularioEliminar(nombreOpt, id){
    var html;
    html = '<div class="panel panel-primary">';
    html += '<div class="panel-heading">'+nombreOpt+' Condicion Casa</div>';
    html += '<div class="panel-body">';
    html +=  '<form role="form">';
    html += '<div class="form-group"><label>¿Esta seguro(a) que desea eliminar la Condicion de Casa?</label></div>';
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
    var condicionCasa;
    var condiconCasaRep;
    var descParametria;
    var abbr;
    var habilitad;
    var htmlAlert;
    var $strong;
    var pError;
    descParametria = $("#descCondicionCasa").val();
    condicionCasa = new CondicionCasa();
    pError =  $("#panelError");
    if ($("#habilitado").is(':checked')){
        habilitad = 1;
    } else {
        habilitad = 0;
    }
    if(opt === 2){
        condicionCasa.idCondicion = id;
        condicionCasa.denominacion = descParametria;
        condicionCasa.habilitado = habilitad;
        condiconCasaRep = condicionCasa.mantCondicionCasa(2);
    } else {
        condicionCasa.denominacion = descParametria;
        condicionCasa.habilitado = habilitad;
        condiconCasaRep = condicionCasa.mantCondicionCasa(1);
    }
    
    if(condiconCasaRep.indError === 0){
        htmlAlert = '<div class="alert alert-success">';
        htmlAlert += '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>';
        htmlAlert += '</div>';
        pError.html(htmlAlert);
        $strong = $("<strong/>",{
            text : condiconCasaRep.msjError
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
            text : condiconCasaRep.msjError
        });
        $("#panelError div").append($strong); 
        pError.show();
    }
}

function eliminarParametria(id){
    var condicionCasa;
    var condicinCasaRep;
    var htmlAlert;
    var $strong;
    var pError;
    condicionCasa = new CondicionCasa();
    pError =  $("#panelError");
    condicionCasa.idCondicion = id;
    condicinCasaRep = condicionCasa.mantCondicionCasa(3);
    if(condicinCasaRep.indError === 0){
        htmlAlert = '<div class="alert alert-success">';
        htmlAlert += '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>';
        htmlAlert += '</div>';
        pError.html(htmlAlert);
        $strong = $("<strong/>",{
            text : condicinCasaRep.msjError
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
            text : condicinCasaRep.msjError
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
    $("#descCondicionCasa").val("");
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
    html += '<button type="button" class="btn btn-primary" id="Opcgrabar" title="Grabar Condicion Casa">';
    html += '<span class="glyphicon glyphicon-floppy-disk"></span>';
    html += '</button>';
    html += '</div>';
    html += '<div class="btn-group" role="group">';
    html += '<button type="button" class="btn btn-primary" id="Opceditar" title="Editar Condicion Casa">';
    html +=  '<span class="glyphicon glyphicon-edit"></span>';
    html += '</button>';
    html += '</div>';
    html += '<div class="btn-group" role="group">';
    html += '<button type="button" class="btn btn-primary" id = "Opceliminar" title="Eliminar Condicion Casa">';
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