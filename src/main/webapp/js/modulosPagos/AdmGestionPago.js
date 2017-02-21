/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var listTiposPagos;
var accion;
var interfaz;
var estados = [
    {id:"1", descripcion:"Habilitado"},
    {id:"0", descripcion:"Inhabilitado"}
];
$(document).ready(function(){
    $("#panelGrabar").hide();
    listarTablaTipoPago();
    
    $(".inhabilitador").hide();
    
    $("#chkConceptoInh").on("change", function(){
        if($(this).is(":checked")){
            $(".inhabilitador").show();
        } else {
            $(".inhabilitador").hide();
        }
    });
    
    $("#Opcgrabar").on("click", function(){
        mostrarTipoPago(); 
    });
    
    $("#btnAceptar").on("click", function(){
       mantenimientoTipoPago(); 
    });
    listarTodosEstados();

    $("#txtFechaNacimiento").datepicker({
       startDate : '0d'     
    });

    $("#txtFechaInicioVigenciaNuevo").datepicker({
       startDate : '0d'     
    });
});

var listarTablaTipoPago = function(){
    var tipoPago = new TipoPago();
    var listaTiposPagos = tipoPago.listarTipoPago(1);
    if(listaTiposPagos.indError === 0){
        var idDiv = $("#panelConsultar");
        var $tr, $td, $button;
        idDiv.html("<table id='tablaTipoPago'><thead>"+
                    "<th>Concepto</th><th>Para</th><th>Estado</th><th>Editar</th><th>Editar Precio</th>"+
                    "</thead><tbody id='bodyTipoPago'></tbody></table>");
        listTiposPagos =  listaTiposPagos.listTipoPago;   
        for(var i = 0; i < listTiposPagos.length; i++){
            $tr = $("<tr/>");
            $td = $("<td/>",{
                "text" : listTiposPagos[i].concepto
            });
            $tr.append($td);

            if(listTiposPagos[i].conceptoPara === 'C'){
                $td = $("<td/>",{
                    "text" : "Colegiado"
                });
                $tr.append($td);
            } else if(listTiposPagos[i].conceptoPara === 'N'){
                $td = $("<td/>",{
                    "text" : "No Colegiado"
                });
                $tr.append($td);
            } else{
                $td = $("<td/>",{
                    "text" : "Ambos"
                });
                $tr.append($td);
            }
            if(listTiposPagos[i].estado === 1){
                $td = $("<td><span class='label label-success'>Habilitado</span></td>");
                $tr.append($td);
            } else {
                $td = $("<td><span class='label label-danger'>Inhabilitado</span></td>");
                $tr.append($td);
            }
            
            
            $button = $("<button class='btn btn-primary' onclick='editarTipoPago("+i+")'>"+
                    "<span class='glyphicon glyphicon-edit'></span></button>");
            $td = $("<td/>");
            $td.append($button);
            $tr.append($td);

            $button = $("<button class='btn btn-primary' onclick='mostrarPreciosTipoPago("+i+")'>"+
                    "<span class='glyphicon glyphicon-edit'></span></button>");
            $td = $("<td/>");
            $td.append($button);
            $tr.append($td);

            $("#bodyTipoPago").append($tr);  
        }
        $("#tablaTipoPago").dataTable();
    } else {
        mostrarMensaje('danger', listaTiposPagos.msjError);
    }    
};

var mostrarTipoPago = function(){
    limpiarFormulario();
    accion = 1;
    interfaz = 1;
    $("#panelGrabar").show();
    $("#divTipoPago").show();
    $("#divPrecioTipoPago").hide();
    $("#panelConsultar").hide();
    $("#panelOpciones").hide();
    $(".divPrecio").show();
    $(".titleTipoPago").html("Tipo Pago - Grabar Nuevo");
};

var editarTipoPago = function(index){
    limpiarFormulario();
    accion = 2;
    interfaz = 1;
    tipoPagoSeleccionado = listTiposPagos[index];
    $(".titleTipoPago").html("Tipo Pago - Actualizar "+tipoPagoSeleccionado.concepto);
    $(".divPrecio").hide();
    $("#panelGrabar").show();
    $("#divTipoPago").show();
    $("#divPrecioTipoPago").hide();
    $("#panelConsultar").hide();
    $("#panelOpciones").hide();
    $("#txtConcepto").val(tipoPagoSeleccionado.concepto);
    if(tipoPagoSeleccionado.conceptoPara === 'A'){
        $("#chkConceptoCol").prop("checked", true);
        $("#chkConceptoNoCol").prop("checked", true);
    } else if (tipoPagoSeleccionado.conceptoPara === 'C') {
        $("#chkConceptoCol").prop("checked", true);
    } else {
        $("#chkConceptoNoCol").prop("checked", true);
    }
    
    if(tipoPagoSeleccionado.esInhabilitadora === 1){
        $("#chkConceptoInh").prop("checked", true);
        $("#txtNumPagosActivos").val(tipoPagoSeleccionado.numPagosActivos);
        if(tipoPagoSeleccionado.tipoGeneracion === 3){
            $("#chkConceptoManual").prop("checked", true);
            $("#chkConceptoMensual").prop("checked", true);
        } else if(tipoPagoSeleccionado.tipoGeneracion === 2){
            $("#chkConceptoMensual").prop("checked", true);
        } else{
            $("#chkConceptoManual").prop("checked", true);
        }
        $(".inhabilitador").show();
    } else{
        $(".inhabilitador").hide();
    }
    if(tipoPagoSeleccionado.estado === 1){
         $("#cboEstado").val("0");
    } else {
        $("#cboEstado").val("1");
    }
    
    var splitEstados = tipoPagoSeleccionado.estadosColegiados.split(",");
    if(splitEstados.length === 6){
        $("#chkEstColHab").prop("checked", true);
        $("#chkEstColInh").prop("checked", true);
        $("#chkEstColExo").prop("checked", true);
        $("#chkEstColEgre").prop("checked", true);
    } else if(splitEstados.length === 5) {
        if(splitEstados[1] === '1'){
            $("#chkEstColHab").prop("checked", true);
        } else if (splitEstados[1] === '2'){
            $("#chkEstColInh").prop("checked", true);
        } else if (splitEstados[1] === '2'){
            $("#chkEstColExo").prop("checked", true);
        } else if (splitEstados[1] === '4'){
            $("#chkEstColEgre").prop("checked", true);
        }
        
        if(splitEstados[2] === '1'){
            $("#chkEstColHab").prop("checked", true);
        } else if (splitEstados[2] === '2'){
            $("#chkEstColInh").prop("checked", true);
        } else if (splitEstados[2] === '2'){
            $("#chkEstColExo").prop("checked", true);
        } else if (splitEstados[2] === '4'){
            $("#chkEstColEgre").prop("checked", true);
        }
        
        if(splitEstados[3] === '1'){
            $("#chkEstColHab").prop("checked", true);
        } else if (splitEstados[3] === '2'){
            $("#chkEstColInh").prop("checked", true);
        } else if (splitEstados[3] === '2'){
            $("#chkEstColExo").prop("checked", true);
        } else if (splitEstados[3] === '4'){
            $("#chkEstColEgre").prop("checked", true);
        }
    } else if(splitEstados.length === 4) {
        if(splitEstados[1] === '1'){
            $("#chkEstColHab").prop("checked", true);
        } else if (splitEstados[1] === '2'){
            $("#chkEstColInh").prop("checked", true);
        } else if (splitEstados[1] === '2'){
            $("#chkEstColExo").prop("checked", true);
        } else if (splitEstados[1] === '4'){
            $("#chkEstColEgre").prop("checked", true);
        }
        
        if(splitEstados[2] === '1'){
            $("#chkEstColHab").prop("checked", true);
        } else if (splitEstados[2] === '2'){
            $("#chkEstColInh").prop("checked", true);
        } else if (splitEstados[2] === '2'){
            $("#chkEstColExo").prop("checked", true);
        } else if (splitEstados[2] === '4'){
            $("#chkEstColEgre").prop("checked", true);
        }
    } else if(splitEstados.length === 3) {
        if(splitEstados[1] === '1'){
            $("#chkEstColHab").prop("checked", true);
        } else if (splitEstados[1] === '2'){
            $("#chkEstColInh").prop("checked", true);
        } else if (splitEstados[1] === '2'){
            $("#chkEstColExo").prop("checked", true);
        } else if (splitEstados[1] === '4'){
            $("#chkEstColEgre").prop("checked", true);
        }    
    }
};

var mostrarPreciosTipoPago = function(index){
    var tipoPago, tipoPagoRep;
    $("#panelGrabar").show();
    $("#divTipoPago").hide();
    $("#panelConsultar").hide();
    $("#panelOpciones").hide();
    interfaz = 2;
    tipoPagoSeleccionado = listTiposPagos[index];
    $(".titleTipoPago").html("Tipo Pago - Actualizar Precio "+tipoPagoSeleccionado.concepto);
    $("#txtPrecioNuevo").val("");
    $("#txtFechaInicioVigenciaNuevo").val("");
    tipoPago = new TipoPago();
    tipoPago.id = tipoPagoSeleccionado.id;
    tipoPagoRep = tipoPago.listarTipoPagoPrecio();
    if(tipoPagoRep.indError === 0){
        tipoPagoSeleccionado = tipoPagoRep;
        if(tipoPagoRep.indTablaPrecio === 1){
            if(tipoPagoRep.indTmpPrecio === 1){
                $(".precioVigente").show();
                $(".precioProximo").show();
                $("#txtPrecioVigente").val(tipoPagoRep.precioActual.precio);
                $("#txtPrecioProxVigente").val(tipoPagoRep.precioEspera.precio);
                $("#txtFechaInicioVigencia").val(tipoPagoRep.precioActual.fechaInicioVigencia);
                $("#txtFechaInicioProxVigencia").val(tipoPagoRep.precioEspera.fechaInicioVigencia);
            } else {
                $(".precioVigente").show();
                $(".precioProximo").hide();
                $("#txtPrecioVigente").val(tipoPagoRep.precioActual.precio);
                $("#txtFechaInicioVigencia").val(tipoPagoRep.precioActual.fechaInicioVigencia);
            }
        } else if(tipoPagoRep.indTablaPrecio === 2){
            if(tipoPagoRep.indTmpPrecio === 1){
                $(".precioVigente").hide();
                $(".precioProximo").show();
                $("#txtPrecioProxVigente").val(tipoPagoRep.precioEspera.precio);
                $("#txtFechaInicioProxVigencia").val(tipoPagoRep.precioEspera.fechaInicioVigencia);
            } else {
                $(".precioVigente").hide();
                $(".precioProximo").hide();
            }
        }
        $("#divPrecioTipoPago").show();
    } else {
        mostrarMensaje('danger', tipoPagoRep.msjError);
    }    
};

var regresarPrincipal = function(){
    $("#panelGrabar").hide();
    $("#panelConsultar").show();
    $("#panelOpciones").show();
    $("#panelError").html("");
    $(".titleTipoPago").html("Tipo Pago");
};

var mantenimientoTipoPago = function(){
    var tipoPago, tipoPagoRep;
    var concepto, conceptoPara, esInhabilitadora, numPagosActivos, tipoGeneracion,
        estado, estadosColegiados, precio, fechaVigencia;
    var contenido;    
    if(interfaz === 1){    
        concepto = $("#txtConcepto").val();
        if(concepto === ''){
            mostrarMensaje('danger', "Debe ingresar denominacion de concepto");
            return;
        }
        if($("#chkConceptoCol").is(":checked") && $("#chkConceptoNoCol").is(":checked")){
            conceptoPara = 'A';
        } else if($("#chkConceptoCol").is(":checked")){
            conceptoPara = 'C';
        } else if($("#chkConceptoNoCol").is(":checked")){
            conceptoPara = 'N';
        } else {
            mostrarMensaje('danger', "Debe elegir un opcion para asignar el concepto");
            return;
        }

        if($("#chkConceptoInh").is(":checked")){
            esInhabilitadora = 1;
            numPagosActivos = parseInt($("#txtNumPagosActivos").val());
            if(isNaN(numPagosActivos)){
                mostrarMensaje('danger', "Debe ingresar un numero de pagos activos");
                return;
            }

            if($("#chkConceptoManual").is(":checked") && $("#chkConceptoMensual").is(":checked")){
                tipoGeneracion = 3;
            } else if($("#chkConceptoMensual").is(":checked")){
                tipoGeneracion = 2;
            } else if($("#chkConceptoManual").is(":checked")){
                tipoGeneracion = 1;
            } else {
                mostrarMensaje('danger', "Debe elegir el tipo de generacion del tipo pago");
                return;
            }
        } else {
            esInhabilitadora = 0;
            tipoGeneracion = 1;
            numPagosActivos = 0;
        }
        if($("#cboEstado").val() === ''){
            mostrarMensaje('danger', "Debe seleccionar un estado");
            return;
        }
        estado = estados[parseInt($("#cboEstado").val())].id;

        estadosColegiados = '';
        if(esInhabilitadora === 1){
            if($("#chkEstColExo").is(":checked")){
                mostrarMensaje('danger', "No puede seleccionar estado Exonerado");
                return;
            }

            if($("#chkEstColEgre").is(":checked")){
                mostrarMensaje('danger', "No puede seleccionar estado Egresado");
                return;
            }

            if(!$("#chkEstColHab").is(":checked") && !$("#chkEstColHab").is(":checked")){
                mostrarMensaje('danger', "Debe seleccionar al menos un estado del colegiado");
                return;
            }

            if($("#chkEstColHab").is(":checked")){
                estadosColegiados += ',1';
            }

            if($("#chkEstColInh").is(":checked")){
                estadosColegiados += ',2';
            }

            estadosColegiados += ',';
        } else {
            if(!$("#chkEstColHab").is(":checked") && !$("#chkEstColHab").is(":checked") && 
                    !$("#chkEstColExo").is(":checked") && !$("#chkEstColEgre").is(":checked")){
                mostrarMensaje('danger', "Debe seleccionar al menos un estado del colegiado");
                return;
            }

            if($("#chkEstColHab").is(":checked")){
                estadosColegiados += ',1';
            }

            if($("#chkEstColInh").is(":checked")){
                estadosColegiados += ',2';
            }

            if($("#chkEstColExo").is(":checked")){
                estadosColegiados += ',3';
            }

            if($("#chkEstColEgre").is(":checked")){
                estadosColegiados += ',4';
            }
            estadosColegiados += ',';
        }    
        if(accion === 1){
            precio = parseFloat($("#txtPrecio").val());
            if(isNaN(precio)){
                mostrarMensaje('danger', "Debe ingresar un precio");
                return;
            }
            fechaVigencia = $("#txtFechaNacimiento").val();
            var date = new Date();
            if(fechaVigencia === ''){
                 mostrarMensaje('danger', "Debe seleccionar una fecha");
                 return;
            }
            var fechaSplit = fechaVigencia.split("/");
            var fechaHoy = new Date((date.getMonth()+1), date.getDate(), date.getFullYear());
            var fechaSeleccionada = new Date(fechaSplit[1],fechaSplit[0], fechaSplit[2]);
            if(fechaSeleccionada.getTime() < fechaHoy.getTime()){
                mostrarMensaje('danger', "La fecha debe ser mayor o igual a la actual");
                return;
            }

            tipoPago = new TipoPago();
            tipoPago.concepto = concepto;
            tipoPago.conceptoPara = conceptoPara;
            tipoPago.esInhabilitadora= esInhabilitadora;
            tipoPago.numPagosActivos= numPagosActivos;
            tipoPago.tipoGeneracion = tipoGeneracion;
            tipoPago.estado = estado;
            tipoPago.estadosColegiados= estadosColegiados;

            var precioActual = new Precio();
            precioActual.precio =  precio;
            precioActual.fechaInicioVigencia =  fechaVigencia;

            tipoPago.precioActual = precioActual;

            tipoPagoRep = tipoPago.mantTipoPago(1);
            if(tipoPagoRep.indError === 0){
                mostrarMensaje('success', tipoPagoRep.msjError);
                listarTablaTipoPago();
                setTimeout(regresarPrincipal(), 3000);
            }else{
                mostrarMensaje('danger', tipoPagoRep.msjError);
            }
        } else if(accion === 2) {
            tipoPago = new TipoPago();
            tipoPago.concepto = concepto;
            tipoPago.conceptoPara = conceptoPara;
            tipoPago.esInhabilitadora= esInhabilitadora;
            tipoPago.numPagosActivos= numPagosActivos;
            tipoPago.tipoGeneracion = tipoGeneracion;
            tipoPago.estado = estado;
            tipoPago.estadosColegiados= estadosColegiados;
            tipoPago.id = tipoPagoSeleccionado.id;

            tipoPagoRep = tipoPago.mantTipoPago(2);
            if(tipoPagoRep.indError === 0){
                mostrarMensaje('success', tipoPagoRep.msjError);
                listarTablaTipoPago();
    //            setTimeout(regresarPrincipal(), 3000);
            }else{
                mostrarMensaje('danger', tipoPagoRep.msjError);
            }
        }
    } else if(interfaz === 2){
        var precioNuevo, fechaNueva;
        precioNuevo = parseFloat($("#txtPrecioNuevo").val());
        if(isNaN(precioNuevo)){
            mostrarMensaje('danger', "Debe ingresar un precio nuevo");
            return;
        }
        
        fechaNueva = $("#txtFechaInicioVigenciaNuevo").val();
        var date = new Date();
        if(fechaNueva === ''){
             mostrarMensaje('danger', "Debe seleccionar una fecha");
             return;
        }
        var fechaSplit = fechaNueva.split("/");
        var fechaHoy = new Date((date.getMonth()+1), date.getDate(), date.getFullYear());
        var fechaSeleccionada = new Date(fechaSplit[1],fechaSplit[0], fechaSplit[2]);
        if(fechaSeleccionada.getTime() < fechaHoy.getTime()){
            mostrarMensaje('danger', "La fecha debe ser mayor o igual a la actual");
            return;
        }

        if (tipoPagoSeleccionado.indTablaPrecio === 1 &&
                tipoPagoSeleccionado.indTmpPrecio === 1){
            if(fechaSeleccionada.getTime() === fechaHoy.getTime()){
                contenido = 'Esta Acción quitará el cambio de precio Programado y actualizar'+
                        ' el precio actual en vigencia. Esta seguro(a) de realizar este cambio?';
            }else{
                contenido = 'Esta Acción actualizará el cambio de precio programado'+
                        '. Esta seguro(a) de realizar este cambio?';
            }
            $.jAlert({
                'title': 'CONFIRMACION',
                'content': contenido,
                'theme': 'blue',
                'btns': [
                            {'text': 'SI', 
                             'theme': 'blue', 
                             'closeAlert': true,
                             'onClick': function(e){
                                    e.preventDefault();
                                    tipoPago = new TipoPago();
                                    tipoPago.id = tipoPagoSeleccionado.id;
                                    tipoPago.indTablaPrecio = tipoPagoSeleccionado.indTablaPrecio;
                                    tipoPago.indTmpPrecio = tipoPagoSeleccionado.indTmpPrecio;
                                    
                                    var precioActual = new Precio();
                                    precioActual.precio =  precioNuevo;
                                    precioActual.fechaInicioVigencia =  fechaNueva;
                                    tipoPago.precioActual = precioActual;
                                    tipoPagoRep = tipoPago.actualizaTipoPagoPrecio();
                                    if(tipoPagoRep.indError === 0){
                                        mostrarMensaje('success', tipoPagoRep.msjError);
                                        listarTablaTipoPago();
                                        setTimeout(regresarPrincipal, 3000);
                                    }else{
                                        mostrarMensaje('danger', tipoPagoRep.msjError);
                                    }
                                    return;
                              }
                            },
                            { 'text': 'NO' }
                         ]
            });
        } else if (tipoPagoSeleccionado.indTablaPrecio === 1 &&
                tipoPagoSeleccionado.indTmpPrecio === 0){
            if(fechaSeleccionada.getTime() === fechaHoy.getTime()){
                contenido = 'Esta acción actualizara el precio actual en vigencia.'+
                        ' Esta seguro(a) de realizar este cambio?';
            } else {
                contenido = 'Esta acción programará un cambio de precio. Esta seguro(a) de realizar este cambio?';
            }
            $.jAlert({
                'title': 'CONFIRMACION',
                'content': contenido,
                'theme': 'blue',
                'btns': [
                            {'text': 'SI', 
                             'theme': 'blue', 
                             'closeAlert': true,
                             'onClick': function(e){
                                    e.preventDefault();
                                    tipoPago = new TipoPago();
                                    tipoPago.id = tipoPagoSeleccionado.id;
                                    tipoPago.indTablaPrecio = tipoPagoSeleccionado.indTablaPrecio;
                                    tipoPago.indTmpPrecio = tipoPagoSeleccionado.indTmpPrecio;
                                    
                                    var precioActual = new Precio();
                                    precioActual.precio =  precioNuevo;
                                    precioActual.fechaInicioVigencia =  fechaNueva;
                                    tipoPago.precioActual = precioActual;
                                    tipoPagoRep = tipoPago.actualizaTipoPagoPrecio();
                                    if(tipoPagoRep.indError === 0){
                                        mostrarMensaje('success', tipoPagoRep.msjError);
                                        listarTablaTipoPago();
                                        setTimeout(regresarPrincipal, 3000);
                                    }else{
                                        mostrarMensaje('danger', tipoPagoRep.msjError);
                                    } 
                              }
                            },
                            { 'text': 'NO' }
                         ]
            });
        } else if (tipoPagoSeleccionado.indTablaPrecio === 2){
            if(fechaSeleccionada.getTime() === fechaHoy.getTime()){
                contenido = 'Esta acción quitará el cambio de precio programado y pondra'+
                        ' en vigencia el nuevo precio del pago. Esta seguro(a) de realizar este cambio?';
            } else {
                contenido = 'Esta acción actualizara el cambio de precio programado.'+
                        ' Esta seguro(a) de realizar este cambio?';
            }
            $.jAlert({
                'title': 'CONFIRMACION',
                'content': contenido,
                'theme': 'blue',
                'btns': [
                            {'text': 'SI', 
                             'theme': 'blue', 
                             'closeAlert': true,
                             'onClick': function(e){
                                    e.preventDefault();
                                    tipoPago = new TipoPago();
                                    tipoPago.id = tipoPagoSeleccionado.id;
                                    tipoPago.indTablaPrecio = tipoPagoSeleccionado.indTablaPrecio;
                                    tipoPago.indTmpPrecio = tipoPagoSeleccionado.indTmpPrecio;
                                    
                                    var precioActual = new Precio();
                                    precioActual.precio =  precioNuevo;
                                    precioActual.fechaInicioVigencia =  fechaNueva;
                                    tipoPago.precioActual = precioActual;
                                    tipoPagoRep = tipoPago.actualizaTipoPagoPrecio();
                                    if(tipoPagoRep.indError === 0){
                                        mostrarMensaje('success', tipoPagoRep.msjError);
                                        listarTablaTipoPago();
                                        setTimeout(regresarPrincipal, 3000);
                                    }else{
                                        mostrarMensaje('danger', tipoPagoRep.msjError);
                                    }
                                    return;
                              }
                            },
                            { 'text': 'NO' }
                         ]
            });
        }
    }    
};

var mostrarMensaje = function(tipo,mensaje){
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
};

var listarTodosEstados = function(){
    var $option;
    $("#cboEstado").html("<option value=''>--Selecciona--</option>");
    for(var i = 0;i < estados.length; i++){
        $option = $("<option/>",{
           "value" : i,
           "text" : estados[i].descripcion
        });
        $("#cboEstado").append($option);
    }
};

var limpiarFormulario = function(){
    $("#txtConcepto").val("");
    $("#chkConceptoCol").prop("checked", false);
    $("#chkConceptoNoCol").prop("checked", false);
    $("#chkConceptoInh").prop("checked", false);
    $("#txtNumPagosActivos").val("");
    $("#chkConceptoManual").prop("checked", false);
    $("#chkConceptoMensual").prop("checked", false);
    $("#cboEstado").val("");
    $("#chkEstColHab").prop("checked", false);
    $("#chkEstColInh").prop("checked", false);
    $("#chkEstColExo").prop("checked", false);
    $("#chkEstColEgre").prop("checked", false);
    $("#txtPrecio").val("");
};