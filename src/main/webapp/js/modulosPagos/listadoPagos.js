/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var listaTipoPago;

$(document).ready(function(){
    $("#txtFechaInicio").datepicker();
    $("#txtFechaFin").datepicker();
    
    $("#chkDNI").on("change",function(){
        if($(this).is(":checked")){
            $("#txtDNI").prop("disabled", false);
        } else{
            $("#txtDNI").val("");
            $("#txtDNI").prop("disabled", true);
        }
    });
    
    $("#chkNumColeg").on("change",function(){
        if($(this).is(":checked")){
            $("#txtNumColegiatura").prop("disabled", false);
        } else{
            $("#txtNumColegiatura").val("");
            $("#txtNumColegiatura").prop("disabled", true);
        }
    });
    
    $("#chkTipo").on("change",function(){
        if($(this).is(":checked")){
            $("#cboTipo").prop("disabled", false);
        } else{
            $("#cboTipo").val("");
            $("#cboTipo").prop("disabled", true);
        }
    });
    
    listarTipoPagos();
    
    $("#btnBuscar").on("click", function(){
        buscarPagos();
    });
});


var listarTipoPagos = function(){
    var tipoPago = new TipoPago();
        tipoPago.estado = 1;
        var tipoOperacionTipoPago = 2;
        var listTipoPagoRep = tipoPago.listarTipoPago(tipoOperacionTipoPago);
        if(listTipoPagoRep.indError === 0){
            listaTipoPago = listTipoPagoRep.listTipoPago;
            var $option;
            $("#cboTipo").html("<option value=''>--SELECCIONE--</option>");
            for(var i = 0; i < listaTipoPago.length; i++){
                $option = $("<option/>",{
                    "value" : i,
                    "text" : listaTipoPago[i].concepto
                });
                $("#cboTipo").append($option);
            }
        } else {
            mostrarMensaje('danger',listTipoPagoRep.msjError);
        }    
};

var buscarPagos = function(){
    var fechaInicio = "";
    var fechaFin = "";
    var dni = "";
    var numColeg = "";
    var indexTipoPago = "";
    var operacion = 0;
    var  expRegDni = /[0-9]{8}/;
    var expRegNumColegiatura = /^[0-9]{1,10}$/;
    var RegExPattern = /^\d{1,2}\/\d{1,2}\/\d{2,4}$/;
    var pago;
    var tipoPago;
    if($("#chkFechas").is(":checked") && $("#chkDNI").is(":checked") && $("#chkNumColeg").is(":checked")
     && $("#chkTipo").is(":checked")){
        operacion = 1;
        fechaInicio = $("#txtFechaInicio").val();
        if (fechaInicio === ''){
            mostrarMensaje('danger', 'Debe ingresar fecha inicio');
            return;
        }

        if (fechaInicio.length > 10){
            mostrarMensaje('danger', 'Formato incorrecto dd/mm/yyyy');
            return;
        }

        if (!RegExPattern.test(fechaInicio)){
            mostrarMensaje('danger', 'Formato incorrecto dd/mm/yyyy');
            return;
        }
        var fechaDividida = fechaInicio.split('/');
        var dia = fechaDividida[0];
        var mes = fechaDividida[1];
        var anno = fechaDividida[2];
        var valor = new Date(anno,mes,dia);
        if (isNaN(valor)){
            mostrarMensaje('danger', 'Debe ingresar fecha inicio real');
            return;
        }
        
        fechaFin = $("#txtFechaFin").val();
        if (fechaFin === ''){
            mostrarMensaje('danger', 'Debe ingresar fecha fin');
            return;
        }

        if (fechaFin.length > 10){
            mostrarMensaje('danger', 'Formato incorrecto dd/mm/yyyy');
            return;
        }

        if (!RegExPattern.test(fechaFin)){
            mostrarMensaje('danger', 'Formato incorrecto dd/mm/yyyy');
            return;
        }
        var fechaDividida = fechaFin.split('/');
        var dia = fechaDividida[0];
        var mes = fechaDividida[1];
        var anno = fechaDividida[2];
        var valor = new Date(anno,mes,dia);
        if (isNaN(valor)){
            mostrarMensaje('danger', 'Debe ingresar fecha fin real');
            return;
        }
        
        dni = $("#txtDNI").val();
        if (dni === '') {
            mostrarMensaje('danger','[Error: El DNI no puede ser vacio]');
            return;
        }

        if (dni.length < 8 || dni.length > 9){
            mostrarMensaje('danger', '[Error: El DNI solo debe tener 8 numeros]');
            return;
        }

        if(!expRegDni.test(dni)){
            mostrarMensaje('danger','[Error: El DNI solo acepta numeros]');
            return;
        }
        
        var numColeg = $("#txtNumColegiatura").val();
        if (numColeg === '') {
            mostrarMensaje('danger', '[Error: El Numero Colegiatura no puede ser vacio]');
            return;
        }

        if (numColeg.length < 1 || numColeg.length > 10){
            mostrarMensaje('danger', '[Error: El Numero Colegiatura acepta maximo 10 numeros]');
            return;
        }

        if(!expRegNumColegiatura.test(numColeg)){
            mostrarMensaje('danger', '[Error: El Numero Colegiatura solo acepta numeros]');
            return;
        }
        
        var indexTipoPago = $("#cboTipo").val();
        
        if(indexTipoPago === ''){
            mostrarMensaje('danger', 'Debe seleccionar un tipo de pago');
            return;
        }
        
        var idtTipoPago = listaTipoPago[parseInt(indexTipoPago)].id;
        
        var colegiado = new Colegiado();
        colegiado.dni = dni;
        colegiado.numColegiatura = numColeg;
        
        tipoPago = new TipoPago();
        tipoPago.id = idtTipoPago;
        
        pago = new Pago();
        pago.fechaPago = fechaInicio;
        pago.fechaPagoFin = fechaFin;
        pago.colegiado = colegiado;
    } else if($("#chkFechas").is(":checked") && $("#chkDNI").is(":checked") && $("#chkNumColeg").is(":checked")){
        operacion = 2;
        fechaInicio = $("#txtFechaInicio").val();
        if (fechaInicio === ''){
            mostrarMensaje('danger', 'Debe ingresar fecha inicio');
            return;
        }

        if (fechaInicio.length > 10){
            mostrarMensaje('danger', 'Formato incorrecto dd/mm/yyyy');
            return;
        }

        if (!RegExPattern.test(fechaInicio)){
            mostrarMensaje('danger', 'Formato incorrecto dd/mm/yyyy');
            return;
        }
        var fechaDividida = fechaInicio.split('/');
        var dia = fechaDividida[0];
        var mes = fechaDividida[1];
        var anno = fechaDividida[2];
        var valor = new Date(anno,mes,dia);
        if (isNaN(valor)){
            mostrarMensaje('danger', 'Debe ingresar fecha inicio real');
            return;
        }
        
        fechaFin = $("#txtFechaFin").val();
        if (fechaFin === ''){
            mostrarMensaje('danger', 'Debe ingresar fecha fin');
            return;
        }

        if (fechaFin.length > 10){
            mostrarMensaje('danger', 'Formato incorrecto dd/mm/yyyy');
            return;
        }

        if (!RegExPattern.test(fechaFin)){
            mostrarMensaje('danger', 'Formato incorrecto dd/mm/yyyy');
            return;
        }
        var fechaDividida = fechaFin.split('/');
        var dia = fechaDividida[0];
        var mes = fechaDividida[1];
        var anno = fechaDividida[2];
        var valor = new Date(anno,mes,dia);
        if (isNaN(valor)){
            mostrarMensaje('danger', 'Debe ingresar fecha fin real');
            return;
        }
        
        dni = $("#txtDNI").val();
        if (dni === '') {
            mostrarMensaje('danger','[Error: El DNI no puede ser vacio]');
            return;
        }

        if (dni.length < 8 || dni.length > 9){
            mostrarMensaje('danger', '[Error: El DNI solo debe tener 8 numeros]');
            return;
        }

        if(!expRegDni.test(dni)){
            mostrarMensaje('danger','[Error: El DNI solo acepta numeros]');
            return;
        }
        
        var numColeg = $("#txtNumColegiatura").val();
        if (numColeg === '') {
            mostrarMensaje('danger', '[Error: El Numero Colegiatura no puede ser vacio]');
            return;
        }

        if (numColeg.length < 1 || numColeg.length > 10){
            mostrarMensaje('danger', '[Error: El Numero Colegiatura acepta maximo 10 numeros]');
            return;
        }

        if(!expRegNumColegiatura.test(numColeg)){
            mostrarMensaje('danger', '[Error: El Numero Colegiatura solo acepta numeros]');
            return;
        }

        var colegiado = new Colegiado();
        colegiado.dni = dni;
        colegiado.numColegiatura = numColeg;
        
        tipoPago = new tipoPago();
        
        pago = new Pago();
        pago.fechaPago = fechaInicio;
        pago.fechaPagoFin = fechaFin;
        pago.colegiado = colegiado;
    } else if($("#chkFechas").is(":checked") && $("#chkDNI").is(":checked") && $("#chkTipo").is(":checked")){
        operacion = 3;
        fechaInicio = $("#txtFechaInicio").val();
        if (fechaInicio === ''){
            mostrarMensaje('danger', 'Debe ingresar fecha inicio');
            return;
        }

        if (fechaInicio.length > 10){
            mostrarMensaje('danger', 'Formato incorrecto dd/mm/yyyy');
            return;
        }

        if (!RegExPattern.test(fechaInicio)){
            mostrarMensaje('danger', 'Formato incorrecto dd/mm/yyyy');
            return;
        }
        var fechaDividida = fechaInicio.split('/');
        var dia = fechaDividida[0];
        var mes = fechaDividida[1];
        var anno = fechaDividida[2];
        var valor = new Date(anno,mes,dia);
        if (isNaN(valor)){
            mostrarMensaje('danger', 'Debe ingresar fecha inicio real');
            return;
        }
        
        fechaFin = $("#txtFechaFin").val();
        if (fechaFin === ''){
            mostrarMensaje('danger', 'Debe ingresar fecha fin');
            return;
        }

        if (fechaFin.length > 10){
            mostrarMensaje('danger', 'Formato incorrecto dd/mm/yyyy');
            return;
        }

        if (!RegExPattern.test(fechaFin)){
            mostrarMensaje('danger', 'Formato incorrecto dd/mm/yyyy');
            return;
        }
        var fechaDividida = fechaFin.split('/');
        var dia = fechaDividida[0];
        var mes = fechaDividida[1];
        var anno = fechaDividida[2];
        var valor = new Date(anno,mes,dia);
        if (isNaN(valor)){
            mostrarMensaje('danger', 'Debe ingresar fecha fin real');
            return;
        }
        
        dni = $("#txtDNI").val();
        if (dni === '') {
            mostrarMensaje('danger','[Error: El DNI no puede ser vacio]');
            return;
        }

        if (dni.length < 8 || dni.length > 9){
            mostrarMensaje('danger', '[Error: El DNI solo debe tener 8 numeros]');
            return;
        }

        if(!expRegDni.test(dni)){
            mostrarMensaje('danger','[Error: El DNI solo acepta numeros]');
            return;
        }
        
        var indexTipoPago = $("#cboTipo").val();
        
        if(indexTipoPago === ''){
            mostrarMensaje('danger', 'Debe seleccionar un tipo de pago');
            return;
        }
        
        var idtTipoPago = listaTipoPago[parseInt(indexTipoPago)].id;
        
        var colegiado = new Colegiado();
        colegiado.dni = dni;
        colegiado.numColegiatura = numColeg;
        
        tipoPago = new TipoPago();
        tipoPago.id = idtTipoPago;
        
        pago = new Pago();
        pago.fechaPago = fechaInicio;
        pago.fechaPagoFin = fechaFin;
        pago.colegiado = colegiado;
    } else if($("#chkFechas").is(":checked") && $("#chkNumColeg").is(":checked") && $("#chkTipo").is(":checked")){
        operacion = 4;
        fechaInicio = $("#txtFechaInicio").val();
        if (fechaInicio === ''){
            mostrarMensaje('danger', 'Debe ingresar fecha inicio');
            return;
        }

        if (fechaInicio.length > 10){
            mostrarMensaje('danger', 'Formato incorrecto dd/mm/yyyy');
            return;
        }

        if (!RegExPattern.test(fechaInicio)){
            mostrarMensaje('danger', 'Formato incorrecto dd/mm/yyyy');
            return;
        }
        var fechaDividida = fechaInicio.split('/');
        var dia = fechaDividida[0];
        var mes = fechaDividida[1];
        var anno = fechaDividida[2];
        var valor = new Date(anno,mes,dia);
        if (isNaN(valor)){
            mostrarMensaje('danger', 'Debe ingresar fecha inicio real');
            return;
        }
        
        fechaFin = $("#txtFechaFin").val();
        if (fechaFin === ''){
            mostrarMensaje('danger', 'Debe ingresar fecha fin');
            return;
        }

        if (fechaFin.length > 10){
            mostrarMensaje('danger', 'Formato incorrecto dd/mm/yyyy');
            return;
        }

        if (!RegExPattern.test(fechaFin)){
            mostrarMensaje('danger', 'Formato incorrecto dd/mm/yyyy');
            return;
        }
        var fechaDividida = fechaFin.split('/');
        var dia = fechaDividida[0];
        var mes = fechaDividida[1];
        var anno = fechaDividida[2];
        var valor = new Date(anno,mes,dia);
        if (isNaN(valor)){
            mostrarMensaje('danger', 'Debe ingresar fecha fin real');
            return;
        }
        
        var numColeg = $("#txtNumColegiatura").val();
        if (numColeg === '') {
            mostrarMensaje('danger', '[Error: El Numero Colegiatura no puede ser vacio]');
            return;
        }

        if (numColeg.length < 1 || numColeg.length > 10){
            mostrarMensaje('danger', '[Error: El Numero Colegiatura acepta maximo 10 numeros]');
            return;
        }

        if(!expRegNumColegiatura.test(numColeg)){
            mostrarMensaje('danger', '[Error: El Numero Colegiatura solo acepta numeros]');
            return;
        }
        
        var indexTipoPago = $("#cboTipo").val();
        
        if(indexTipoPago === ''){
            mostrarMensaje('danger', 'Debe seleccionar un tipo de pago');
            return;
        }
        
        var idtTipoPago = listaTipoPago[parseInt(indexTipoPago)].id;
        
        var colegiado = new Colegiado();
        colegiado.dni = dni;
        colegiado.numColegiatura = numColeg;
        
        tipoPago = new TipoPago();
        tipoPago.id = idtTipoPago;
        
        pago = new Pago();
        pago.fechaPago = fechaInicio;
        pago.fechaPagoFin = fechaFin;
        pago.colegiado = colegiado;
    } else if($("#chkDNI").is(":checked") && $("#chkNumColeg").is(":checked") && $("#chkTipo").is(":checked")){
        operacion = 5;
        dni = $("#txtDNI").val();
        if (dni === '') {
            mostrarMensaje('danger','[Error: El DNI no puede ser vacio]');
            return;
        }

        if (dni.length < 8 || dni.length > 9){
            mostrarMensaje('danger', '[Error: El DNI solo debe tener 8 numeros]');
            return;
        }

        if(!expRegDni.test(dni)){
            mostrarMensaje('danger','[Error: El DNI solo acepta numeros]');
            return;
        }
        
        var numColeg = $("#txtNumColegiatura").val();
        if (numColeg === '') {
            mostrarMensaje('danger', '[Error: El Numero Colegiatura no puede ser vacio]');
            return;
        }

        if (numColeg.length < 1 || numColeg.length > 10){
            mostrarMensaje('danger', '[Error: El Numero Colegiatura acepta maximo 10 numeros]');
            return;
        }

        if(!expRegNumColegiatura.test(numColeg)){
            mostrarMensaje('danger', '[Error: El Numero Colegiatura solo acepta numeros]');
            return;
        }
        
        var indexTipoPago = $("#cboTipo").val();
        
        if(indexTipoPago === ''){
            mostrarMensaje('danger', 'Debe seleccionar un tipo de pago');
            return;
        }
        
        var idtTipoPago = listaTipoPago[parseInt(indexTipoPago)].id;
        
        var colegiado = new Colegiado();
        colegiado.dni = dni;
        colegiado.numColegiatura = numColeg;
        
        tipoPago = new TipoPago();
        tipoPago.id = idtTipoPago;
        
        pago = new Pago();
        pago.fechaPago = fechaInicio;
        pago.fechaPagoFin = fechaFin;
        pago.colegiado = colegiado;
    } else if($("#chkFechas").is(":checked") && $("#chkDNI").is(":checked")){
        operacion = 6;
        fechaInicio = $("#txtFechaInicio").val();
        if (fechaInicio === ''){
            mostrarMensaje('danger', 'Debe ingresar fecha inicio');
            return;
        }

        if (fechaInicio.length > 10){
            mostrarMensaje('danger', 'Formato incorrecto dd/mm/yyyy');
            return;
        }

        if (!RegExPattern.test(fechaInicio)){
            mostrarMensaje('danger', 'Formato incorrecto dd/mm/yyyy');
            return;
        }
        var fechaDividida = fechaInicio.split('/');
        var dia = fechaDividida[0];
        var mes = fechaDividida[1];
        var anno = fechaDividida[2];
        var valor = new Date(anno,mes,dia);
        if (isNaN(valor)){
            mostrarMensaje('danger', 'Debe ingresar fecha inicio real');
            return;
        }
        
        fechaFin = $("#txtFechaFin").val();
        if (fechaFin === ''){
            mostrarMensaje('danger', 'Debe ingresar fecha fin');
            return;
        }

        if (fechaFin.length > 10){
            mostrarMensaje('danger', 'Formato incorrecto dd/mm/yyyy');
            return;
        }

        if (!RegExPattern.test(fechaFin)){
            mostrarMensaje('danger', 'Formato incorrecto dd/mm/yyyy');
            return;
        }
        var fechaDividida = fechaFin.split('/');
        var dia = fechaDividida[0];
        var mes = fechaDividida[1];
        var anno = fechaDividida[2];
        var valor = new Date(anno,mes,dia);
        if (isNaN(valor)){
            mostrarMensaje('danger', 'Debe ingresar fecha fin real');
            return;
        }
        
        dni = $("#txtDNI").val();
        if (dni === '') {
            mostrarMensaje('danger','[Error: El DNI no puede ser vacio]');
            return;
        }

        if (dni.length < 8 || dni.length > 9){
            mostrarMensaje('danger', '[Error: El DNI solo debe tener 8 numeros]');
            return;
        }

        if(!expRegDni.test(dni)){
            mostrarMensaje('danger','[Error: El DNI solo acepta numeros]');
            return;
        }
        
        var colegiado = new Colegiado();
        colegiado.dni = dni;
        colegiado.numColegiatura = numColeg;
        
        tipoPago = new TipoPago();
        
        pago = new Pago();
        pago.fechaPago = fechaInicio;
        pago.fechaPagoFin = fechaFin;
        pago.colegiado = colegiado;
    } else if($("#chkFechas").is(":checked") && $("#chkNumColeg").is(":checked")){
        operacion = 7;
        fechaInicio = $("#txtFechaInicio").val();
        if (fechaInicio === ''){
            mostrarMensaje('danger', 'Debe ingresar fecha inicio');
            return;
        }

        if (fechaInicio.length > 10){
            mostrarMensaje('danger', 'Formato incorrecto dd/mm/yyyy');
            return;
        }

        if (!RegExPattern.test(fechaInicio)){
            mostrarMensaje('danger', 'Formato incorrecto dd/mm/yyyy');
            return;
        }
        var fechaDividida = fechaInicio.split('/');
        var dia = fechaDividida[0];
        var mes = fechaDividida[1];
        var anno = fechaDividida[2];
        var valor = new Date(anno,mes,dia);
        if (isNaN(valor)){
            mostrarMensaje('danger', 'Debe ingresar fecha inicio real');
            return;
        }
        
        fechaFin = $("#txtFechaFin").val();
        if (fechaFin === ''){
            mostrarMensaje('danger', 'Debe ingresar fecha fin');
            return;
        }

        if (fechaFin.length > 10){
            mostrarMensaje('danger', 'Formato incorrecto dd/mm/yyyy');
            return;
        }

        if (!RegExPattern.test(fechaFin)){
            mostrarMensaje('danger', 'Formato incorrecto dd/mm/yyyy');
            return;
        }
        var fechaDividida = fechaFin.split('/');
        var dia = fechaDividida[0];
        var mes = fechaDividida[1];
        var anno = fechaDividida[2];
        var valor = new Date(anno,mes,dia);
        if (isNaN(valor)){
            mostrarMensaje('danger', 'Debe ingresar fecha fin real');
            return;
        }
        
        var numColeg = $("#txtNumColegiatura").val();
        if (numColeg === '') {
            mostrarMensaje('danger', '[Error: El Numero Colegiatura no puede ser vacio]');
            return;
        }

        if (numColeg.length < 1 || numColeg.length > 10){
            mostrarMensaje('danger', '[Error: El Numero Colegiatura acepta maximo 10 numeros]');
            return;
        }

        if(!expRegNumColegiatura.test(numColeg)){
            mostrarMensaje('danger', '[Error: El Numero Colegiatura solo acepta numeros]');
            return;
        }
        
        var colegiado = new Colegiado();
        colegiado.dni = dni;
        colegiado.numColegiatura = numColeg;
        
        tipoPago = new TipoPago();
        
        pago = new Pago();
        pago.fechaPago = fechaInicio;
        pago.fechaPagoFin = fechaFin;
        pago.colegiado = colegiado;
    } else if($("#chkFechas").is(":checked") && $("#chkTipo").is(":checked")){
        operacion = 8;
        fechaInicio = $("#txtFechaInicio").val();
        if (fechaInicio === ''){
            mostrarMensaje('danger', 'Debe ingresar fecha inicio');
            return;
        }

        if (fechaInicio.length > 10){
            mostrarMensaje('danger', 'Formato incorrecto dd/mm/yyyy');
            return;
        }

        if (!RegExPattern.test(fechaInicio)){
            mostrarMensaje('danger', 'Formato incorrecto dd/mm/yyyy');
            return;
        }
        var fechaDividida = fechaInicio.split('/');
        var dia = fechaDividida[0];
        var mes = fechaDividida[1];
        var anno = fechaDividida[2];
        var valor = new Date(anno,mes,dia);
        if (isNaN(valor)){
            mostrarMensaje('danger', 'Debe ingresar fecha inicio real');
            return;
        }
        
        fechaFin = $("#txtFechaFin").val();
        if (fechaFin === ''){
            mostrarMensaje('danger', 'Debe ingresar fecha fin');
            return;
        }

        if (fechaFin.length > 10){
            mostrarMensaje('danger', 'Formato incorrecto dd/mm/yyyy');
            return;
        }

        if (!RegExPattern.test(fechaFin)){
            mostrarMensaje('danger', 'Formato incorrecto dd/mm/yyyy');
            return;
        }
        var fechaDividida = fechaFin.split('/');
        var dia = fechaDividida[0];
        var mes = fechaDividida[1];
        var anno = fechaDividida[2];
        var valor = new Date(anno,mes,dia);
        if (isNaN(valor)){
            mostrarMensaje('danger', 'Debe ingresar fecha fin real');
            return;
        }
        
        var indexTipoPago = $("#cboTipo").val();
        
        if(indexTipoPago === ''){
            mostrarMensaje('danger', 'Debe seleccionar un tipo de pago');
            return;
        }
        
        var idtTipoPago = listaTipoPago[parseInt(indexTipoPago)].id;
        
        var colegiado = new Colegiado();
        colegiado.dni = dni;
        colegiado.numColegiatura = numColeg;
        
        tipoPago = new TipoPago();
        tipoPago.id = idtTipoPago;
        
        pago = new Pago();
        pago.fechaPago = fechaInicio;
        pago.fechaPagoFin = fechaFin;
        pago.colegiado = colegiado;
    } else if($("#chkNumColeg").is(":checked") && $("#chkDNI").is(":checked")){
        operacion = 9;
        dni = $("#txtDNI").val();
        if (dni === '') {
            mostrarMensaje('danger','[Error: El DNI no puede ser vacio]');
            return;
        }

        if (dni.length < 8 || dni.length > 9){
            mostrarMensaje('danger', '[Error: El DNI solo debe tener 8 numeros]');
            return;
        }

        if(!expRegDni.test(dni)){
            mostrarMensaje('danger','[Error: El DNI solo acepta numeros]');
            return;
        }
        
        var numColeg = $("#txtNumColegiatura").val();
        if (numColeg === '') {
            mostrarMensaje('danger', '[Error: El Numero Colegiatura no puede ser vacio]');
            return;
        }

        if (numColeg.length < 1 || numColeg.length > 10){
            mostrarMensaje('danger', '[Error: El Numero Colegiatura acepta maximo 10 numeros]');
            return;
        }

        if(!expRegNumColegiatura.test(numColeg)){
            mostrarMensaje('danger', '[Error: El Numero Colegiatura solo acepta numeros]');
            return;
        }
        
        var colegiado = new Colegiado();
        colegiado.dni = dni;
        colegiado.numColegiatura = numColeg;
        
        tipoPago = new TipoPago();
        
        pago = new Pago();
        pago.fechaPago = fechaInicio;
        pago.fechaPagoFin = fechaFin;
        pago.colegiado = colegiado;
    } else if($("#chkTipo").is(":checked") && $("#chkDNI").is(":checked")){
        operacion = 10;
        
        dni = $("#txtDNI").val();
        if (dni === '') {
            mostrarMensaje('danger','[Error: El DNI no puede ser vacio]');
            return;
        }

        if (dni.length < 8 || dni.length > 9){
            mostrarMensaje('danger', '[Error: El DNI solo debe tener 8 numeros]');
            return;
        }

        if(!expRegDni.test(dni)){
            mostrarMensaje('danger','[Error: El DNI solo acepta numeros]');
            return;
        }
        
        var indexTipoPago = $("#cboTipo").val();
        
        if(indexTipoPago === ''){
            mostrarMensaje('danger', 'Debe seleccionar un tipo de pago');
            return;
        }
        
        var idtTipoPago = listaTipoPago[parseInt(indexTipoPago)].id;
        
        var colegiado = new Colegiado();
        colegiado.dni = dni;
        colegiado.numColegiatura = numColeg;
        
        tipoPago = new TipoPago();
        tipoPago.id = idtTipoPago;
        
        pago = new Pago();
        pago.fechaPago = fechaInicio;
        pago.fechaPagoFin = fechaFin;
        pago.colegiado = colegiado;
    } else if($("#chkTipo").is(":checked") && $("#chkNumColeg").is(":checked")){
        operacion = 11;

        var numColeg = $("#txtNumColegiatura").val();
        if (numColeg === '') {
            mostrarMensaje('danger', '[Error: El Numero Colegiatura no puede ser vacio]');
            return;
        }

        if (numColeg.length < 1 || numColeg.length > 10){
            mostrarMensaje('danger', '[Error: El Numero Colegiatura acepta maximo 10 numeros]');
            return;
        }

        if(!expRegNumColegiatura.test(numColeg)){
            mostrarMensaje('danger', '[Error: El Numero Colegiatura solo acepta numeros]');
            return;
        }
        
        var indexTipoPago = $("#cboTipo").val();
        
        if(indexTipoPago === ''){
            mostrarMensaje('danger', 'Debe seleccionar un tipo de pago');
            return;
        }
        
        var idtTipoPago = listaTipoPago[parseInt(indexTipoPago)].id;
        
        var colegiado = new Colegiado();
        colegiado.dni = dni;
        colegiado.numColegiatura = numColeg;
        
        tipoPago = new TipoPago();
        tipoPago.id = idtTipoPago;
        
        pago = new Pago();
        pago.fechaPago = fechaInicio;
        pago.fechaPagoFin = fechaFin;
        pago.colegiado = colegiado;
    } else if($("#chkFechas").is(":checked")){
        operacion = 12;
        fechaInicio = $("#txtFechaInicio").val();
        if (fechaInicio === ''){
            mostrarMensaje('danger', 'Debe ingresar fecha inicio');
            return;
        }

        if (fechaInicio.length > 10){
            mostrarMensaje('danger', 'Formato incorrecto dd/mm/yyyy');
            return;
        }

        if (!RegExPattern.test(fechaInicio)){
            mostrarMensaje('danger', 'Formato incorrecto dd/mm/yyyy');
            return;
        }
        var fechaDividida = fechaInicio.split('/');
        var dia = fechaDividida[0];
        var mes = fechaDividida[1];
        var anno = fechaDividida[2];
        var valor = new Date(anno,mes,dia);
        if (isNaN(valor)){
            mostrarMensaje('danger', 'Debe ingresar fecha inicio real');
            return;
        }
        
        fechaFin = $("#txtFechaFin").val();
        if (fechaFin === ''){
            mostrarMensaje('danger', 'Debe ingresar fecha fin');
            return;
        }

        if (fechaFin.length > 10){
            mostrarMensaje('danger', 'Formato incorrecto dd/mm/yyyy');
            return;
        }

        if (!RegExPattern.test(fechaFin)){
            mostrarMensaje('danger', 'Formato incorrecto dd/mm/yyyy');
            return;
        }
        var fechaDividida = fechaFin.split('/');
        var dia = fechaDividida[0];
        var mes = fechaDividida[1];
        var anno = fechaDividida[2];
        var valor = new Date(anno,mes,dia);
        if (isNaN(valor)){
            mostrarMensaje('danger', 'Debe ingresar fecha fin real');
            return;
        }
   
        var colegiado = new Colegiado();
        colegiado.dni = dni;
        colegiado.numColegiatura = numColeg;
        
        tipoPago = new TipoPago();
        
        pago = new Pago();
        pago.fechaPago = fechaInicio;
        pago.fechaPagoFin = fechaFin;
        pago.colegiado = colegiado;
    } else if($("#chkDNI").is(":checked")){
        operacion = 13;
        
        dni = $("#txtDNI").val();
        if (dni === '') {
            mostrarMensaje('danger','[Error: El DNI no puede ser vacio]');
            return;
        }

        if (dni.length < 8 || dni.length > 9){
            mostrarMensaje('danger', '[Error: El DNI solo debe tener 8 numeros]');
            return;
        }

        if(!expRegDni.test(dni)){
            mostrarMensaje('danger','[Error: El DNI solo acepta numeros]');
            return;
        }
        
        var colegiado = new Colegiado();
        colegiado.dni = dni;
        colegiado.numColegiatura = numColeg;
        
        tipoPago = new TipoPago();
        
        pago = new Pago();
        pago.fechaPago = fechaInicio;
        pago.fechaPagoFin = fechaFin;
        pago.colegiado = colegiado;
    } else if($("#chkNumColeg").is(":checked")){
        operacion = 14;
        
        var numColeg = $("#txtNumColegiatura").val();
        if (numColeg === '') {
            mostrarMensaje('danger', '[Error: El Numero Colegiatura no puede ser vacio]');
            return;
        }

        if (numColeg.length < 1 || numColeg.length > 10){
            mostrarMensaje('danger', '[Error: El Numero Colegiatura acepta maximo 10 numeros]');
            return;
        }

        if(!expRegNumColegiatura.test(numColeg)){
            mostrarMensaje('danger', '[Error: El Numero Colegiatura solo acepta numeros]');
            return;
        }
        
        var colegiado = new Colegiado();
        colegiado.dni = dni;
        colegiado.numColegiatura = numColeg;
        
        tipoPago = new TipoPago();
        
        pago = new Pago();
        pago.fechaPago = fechaInicio;
        pago.fechaPagoFin = fechaFin;
        pago.colegiado = colegiado;
    } else if($("#chkTipo").is(":checked")){
        operacion = 15;
        
        var indexTipoPago = $("#cboTipo").val();
        
        if(indexTipoPago === ''){
            mostrarMensaje('danger', 'Debe seleccionar un tipo de pago');
            return;
        }
        
        var idtTipoPago = listaTipoPago[parseInt(indexTipoPago)].id;
        
        var colegiado = new Colegiado();
        colegiado.dni = dni;
        colegiado.numColegiatura = numColeg;
        
        tipoPago = new TipoPago();
        tipoPago.id = idtTipoPago;
        
        pago = new Pago();
        pago.fechaPago = fechaInicio;
        pago.fechaPagoFin = fechaFin;
        pago.colegiado = colegiado;
    } else {
        mostrarMensaje('danger', 'Debe seleccionar un filtro');
        return;
    }
    
    var listPagoRep = pago.listarPagos(operacion, tipoPago);
    if(listPagoRep.indError === 0){
        listaPagos = listPagoRep.listaGenerico;
        var $tr, $td;
        $("#divTablaPagos").html("<table id='tablaPagos'><thead><th>Codigo Pago</th><th>DNI</th>"+
                "<th>Num. Colegiatura</th><th>Nombre</th>"+
                "<th>Monto Total</th><th>Fecha Pago</th><th>Estado</th></thead><tbody id='bodyPagos'></tbody></table>");
        for(var i = 0; i < listaPagos.length; i++){
            $tr = $("<tr/>");
            $td = $("<td/>",{
                "text" : listaPagos[i].codigo
            });
            $tr.append($td);
            $td = $("<td/>",{
                "text" : listaPagos[i].colegiado.dni
            });
            $tr.append($td);
            $td = $("<td/>",{
                "text" : listaPagos[i].colegiado.numColegiatura
            });
            $tr.append($td);
            $td = $("<td/>",{
                "text" : listaPagos[i].colegiado.nombres
            });
            $tr.append($td);
            $td = $("<td/>",{
                "text" : listaPagos[i].total
            });
            $tr.append($td);
            $td = $("<td/>",{
                "text" : listaPagos[i].fechaPago
            });
            $tr.append($td);
            if(listaPagos[i].estado === 1){
                $td = $("<td/>",{
                    "text" : "Pagado"
                });
            } else {
                $td = $("<td/>",{
                    "text" : "Revertido"
                });
            }
            $tr.append($td);
            $("#bodyPagos").append($tr);
        }
        $("#tablaPagos").dataTable();
    } else {
        mostrarMensaje('danger',listPagoRep.msjError);
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