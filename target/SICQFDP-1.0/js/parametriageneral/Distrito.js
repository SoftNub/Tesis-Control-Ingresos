/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Objeto Distrito
 * @param {int} idDistrito
 * @param {String} nombreDistrito
 * @param {int} habilitado
 * @returns {Distrito}
 */
function Distrito(idDistrito, nombreDistrito, habilitado){
    this.idDistrito = idDistrito;
    this.nombreDistrito = nombreDistrito;
    this.habilitado = habilitado;
    this.indError = -1;
    this.msjError = "";
    this.listarAllDistritos = listarTodosDistritos;
    this.grabarDistrito = grabarDistrito;
    this.editarDistrito = editarDistrito;
    this.eliminarDistrito = eliminarDistrito;
    this.listarDistritoId = listarDistritoId;
    this.listarDistritosHabilitados = listarDistritosHabilitados;
};

/**
 * Lista de Distritos
 * @param {Array Distrito} Distritos
 * @param {int} indError
 * @param {String} msjError
 * @returns {ListDistritos}
 */
function ListDistritos(Distritos, indError, msjError){
    this.Distritos = Distritos;
    this.indError = indError;
    this.msjError = msjError;
}

function listarTodosDistritos(){
    var listDistritos = new ListDistritos();
    $.ajax({
        url : "../parametria/ListarAllDistritos.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : {},
        success : function(data) {
            listDistritos = new ListDistritos(data.listaDistritos, data.indError, data.msjError);
        }
    });
    return listDistritos;
}

function grabarDistrito(){
    var distrito;
    var distrito2 ={
        "nombreDistrito": this.nombreDistrito,
        "habilitado": this.habilitado
    };
    distrito = new Distrito();
    $.ajax({
        url : "../parametria/GrabarDistrito.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : JSON.stringify(distrito2),
        success : function(data) {
            distrito.indError = data.indError;
            distrito.msjError = data.msjError;
        }
    });
    return distrito;
}

function editarDistrito(){
    var distrito;
    var distrito2 ={
        "idDistrito": this.idDistrito,
        "nombreDistrito": this.nombreDistrito,
        "habilitado": this.habilitado
    };
    distrito = new Distrito();
    $.ajax({
        url : "../parametria/EditarDistrito.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : JSON.stringify(distrito2),
        success : function(data) {
            distrito.indError = data.indError;
            distrito.msjError = data.msjError;
        }
    });
    return distrito;
}

function eliminarDistrito(){
    var distrito;
    var distrito2 ={
        "idDistrito": this.idDistrito
    };
    distrito = new Distrito();
    $.ajax({
        url : "../parametria/EliminarDistrito.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : JSON.stringify(distrito2),
        success : function(data) {
            distrito.indError = data.indError;
            distrito.msjError = data.msjError;
        }
    });
    return distrito;
}

function listarDistritoId(){
    var distrito;
    var distrito2 ={
        "idDistrito": this.idDistrito
    };
    distrito = new Distrito();
    $.ajax({
        url : "../parametria/ListarDistritoId.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : JSON.stringify(distrito2),
        success : function(data) {
            distrito.nombreDistrito = data.nombreDistrito;
            distrito.habilitado = data.habilitado;
            distrito.indError = data.indError;
            distrito.msjError = data.msjError;
        }
    });
    return distrito;
}

function listarDistritosHabilitados(){
    var distrito2 = {
        "habilitado" : this.habilitado
    };
    var listDistritos = new ListDistritos();
    $.ajax({
        url : "../parametria/ListarDistritosHabilitados.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : JSON.stringify(distrito2),
        success : function(data) {
            listDistritos = new ListDistritos(data.listaDistritos, data.indError, data.msjError);
        }
    });
    return listDistritos;
}