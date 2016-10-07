/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    //habilitarBotonesCrud(true, true, false);
    
    $("#Opcgrabar").on("click", function(){
       grabarEgreso(); 
    });
    
    
    $("#OpcCancelar").on("click", function(){
       //limpiarTodo();
       //deshabilitarFormulario(false);
       //habilitarBotonesCrud(true, true, false);
       $("#panelError").html("");
        buscarColegiados();
    });
    
    buscarColegiados();
});

function buscarColegiados(){
    var colegiado;
    $("#panelError").html("");
    colegiado = new ListColegiado();
    gColegiadoListRep = colegiado.buscarColegiadosEgreso();
   

    if (gColegiadoListRep.indError === 0) {
            armarTabla(gColegiadoListRep.listaColegiados);
            //habilitarBotonesCrud(false, false, false);
            $("#Opcgrabar").focus();
    } else {
        mostrarMensaje('danger',gColegiadoListRep.msjError);
    }
}

 function armarTabla(lista){
    var $body, $tr, $td, $inputchk;
    var i;
    $body = $("#bodyNumColegiatura");
    $body.html("");
    for(i = 0; i < lista.length; i++){
        $tr = $("<tr/>");
        $td = $("<td/>",{
           text : lista[i].numColegiatura 
        });
        $tr.append($td);
        $td = $("<td/>",{
           text : lista[i].dni 
        });
        $tr.append($td);
        $td = $("<td/>",{
           text : lista[i].nombres +" "+lista[i].apePaterno+" "+lista[i].apeMaterno 
        });
        $tr.append($td);
        $td = $("<td/>");
        $inputchk = $("<input/>",{
            type : "checkbox",
            name : "chkListaEgresados",
            class : "chkListaEgresados",
            id : "chkEgreso"+i,
            value : i,
            onchange : "marcarColegiado("+i+")"
        });
        $td.append($inputchk);
        $tr.append($td);
        $body.append($tr);
    }
    $("#egresosColegiatura").DataTable();
 }

function habilitarBotonesCrud(grabar, editar, cancelar){
    $("#Opcgrabar").prop("disabled", grabar);
//    $("#Opceditar").prop("disabled", editar);
    $("#OpcCancelar").prop("disabled", cancelar);
}

function grabarEgreso(){
    /*validacion de informacion*/
    $("#panelError").html("");
    var i;
    var band = false;
    var arrayChk = $(".chkListaEgresados");
    
    if(arrayChk.length === 0){
        mostrarMensaje('danger','[Error: No hay informacion para egresos]');
        return;
    }
    
    for(i = 0; i < arrayChk.length; i++){
        if(arrayChk[i].checked){
            band = true;
            break;
        } 
    }
    
    if (!band){
        mostrarMensaje('danger','[Error: No hay ningun colegiado seleccionado para egreso]');
        return;
    }
    
    var colegiadoResp;
    colegiadoResp = gColegiadoListRep.grabarEgresoColegiatura();
    if(colegiadoResp.indError === 0){
        mostrarMensaje('success',colegiadoResp.msjError);
        //habilitarBotonesCrud(true, true, false);
         setTimeout(function(){ buscarColegiados(); }, 1000);
    } else {
        mostrarMensaje('danger',colegiadoResp.msjError);
    }
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

function marcarColegiado(index){
    var value = parseInt($("#chkEgreso"+index).val());
    if(value === index ){
        if($("#chkEgreso"+index).is(":checked")){
            gColegiadoListRep.listaColegiados[index].indSolicitud = 2;
        } else {
            gColegiadoListRep.listaColegiados[index].indSolicitud = undefined;
        }
    } else {
        mostrarMensaje('danger','[Error: Se ha perdido la referencia, por favor recarge'+
                    ' la pagina. Si el problema persiste comuniquese con el administrador');
    }
}