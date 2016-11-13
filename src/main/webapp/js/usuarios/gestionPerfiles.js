/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
    $("#tablaPerfiles").dataTable();
    $(".divDetalle").hide();
    $("#tablaPerfilesMenu").dataTable();
    $("#btnAtras").click(function(){
        $(".divDetalle").hide();
        $(".divPerfiles").show();
    });
});

var mostrarMenuPerfil = function(index){
    $(".divDetalle").show();
    $(".divPerfiles").hide();
};


