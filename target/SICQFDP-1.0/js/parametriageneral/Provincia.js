/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Objeto Provincia
 * @param {type} idProvincia
 * @param {type} nombreProvincia
 * @param {type} habilitado
 * @returns {Provincia}
 */
function Provincia (idProvincia, nombreProvincia , habilitado){
    this.idProvincia = idProvincia;
    this.nombreProvincia = nombreProvincia;
    this.habilitado = habilitado;
    this.indError = -1;
    this.msjError = "";
    this.listarAllProvincias = listarAllProvincias;
    this.grabarProvincia = grabarProvincia;
    this.editarProvincia = editarProvincia;
    this.eliminarProvincia = eliminarProvincia;
    this.listarProvinciaId = listarProvinciaId;
    this.listarDistritosDeProvincia = listarDistritosDeProvincia;
    this.grabarDistritoDeProvincia = grabarDistritoDeProvincia;
    this.eliminarDistritoDeProvincia = eliminarDistritoDeProvincia;
    this.listarProvinciasHabilitadas = listarProvinciasHabilitadas;
}

/**
 * Lista de Provincias
 * @param {type} Provincias
 * @param {type} indError
 * @param {type} msjError
 * @returns {ListProvincia}
 */
function ListProvincia(Provincias, indError, msjError){
    this.Provincias = Provincias;
    this.indError = indError;
    this.msjError = msjError;
}

function listarAllProvincias(){
    var listProvincias = new ListProvincia();
    $.ajax({
        url : "ListarAllProvincias.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : {},
        success : function(data) {
            listProvincias = new ListProvincia(data.listaProvincias, data.indError, data.msjError);
        }
    });
    return listProvincias;
}

function grabarProvincia(){
    var provincia;
    var provincia2 ={
        "nombreProvincia": this.nombreProvincia,
        "habilitado": this.habilitado
    };
    provincia = new Provincia();
    $.ajax({
        url : "GrabarProvincia.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : JSON.stringify(provincia2),
        success : function(data) {
            provincia.indError = data.indError;
            provincia.msjError = data.msjError;
        }
    });
    return provincia;
}

function editarProvincia(){
    var provincia;
    var provincia2 ={
        "idProvincia": this.idProvincia,
        "nombreProvincia": this.nombreProvincia,
        "habilitado": this.habilitado
    };
    provincia = new Provincia();
    $.ajax({
        url : "EditarProvincia.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : JSON.stringify(provincia2),
        success : function(data) {
            provincia.indError = data.indError;
            provincia.msjError = data.msjError;
        }
    });
    return provincia;
}

function eliminarProvincia(){
    var provincia;
    var provincia2 ={
        "idProvincia": this.idProvincia
    };
    provincia = new Provincia();
    $.ajax({
        url : "EliminarProvincia.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : JSON.stringify(provincia2),
        success : function(data) {
            provincia.indError = data.indError;
            provincia.msjError = data.msjError;
        }
    });
    return provincia;
}

function listarProvinciaId(){
    var provincia;
    var provincia2 ={
        "idProvincia": this.idProvincia
    };
    provincia = new Provincia();
    $.ajax({
        url : "ListarProvinciaId.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : JSON.stringify(provincia2),
        success : function(data) {
            provincia.nombreProvincia = data.nombreProvincia;
            provincia.habilitado = data.habilitado;
            provincia.indError = data.indError;
            provincia.msjError = data.msjError;
        }
    });
    return provincia;
}

function listarDistritosDeProvincia(){
    var listaDistritos;
    var provincia2 ={
        "idProvincia": this.idProvincia
    };
    listaDistritos = new ListDistritos();
    $.ajax({
        url : "ListarDistritosProvincia.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : JSON.stringify(provincia2),
        success : function(data) {
            listaDistritos = new ListDistritos(data.listaDistritos, data.indError, data.msjError);
        }
    });
    return listaDistritos;
}

function grabarDistritoDeProvincia(idDistrito){
    var provincia;
    var provincia2 ={
        "idProvincia": this.idProvincia
    };
    var distrito2 ={
        "idDistrito" : idDistrito
    };
    provincia = new Provincia();
    $.ajax({
        url : "GrabarDistritosProvincia.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : JSON.stringify({provincia: provincia2, distrito : distrito2}),
        success : function(data) {
            provincia.indError = data.indError;
            provincia.msjError = data.msjError;
        }
    });
    return provincia;
}

function eliminarDistritoDeProvincia(idDistrito){
    var provincia;
    var provincia2 ={
        "idProvincia": this.idProvincia
    };
    var distrito2 ={
        "idDistrito" : idDistrito
    };
    provincia = new Provincia();
    $.ajax({
        url : "EliminarDistritosProvincia.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : JSON.stringify({provincia: provincia2, distrito : distrito2}),
        success : function(data) {
            provincia.indError = data.indError;
            provincia.msjError = data.msjError;
        }
    });
    return provincia;
}

function listarProvinciasHabilitadas(){
    var provincia2 = {
        "habilitado" : this.habilitado
    };
    var listProvincias = new ListProvincia();
    $.ajax({
        url : "ListarProvinciasHabilitadas.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : JSON.stringify(provincia2),
        success : function(data) {
            listProvincias = new ListProvincia(data.listaProvincias, data.indError, data.msjError);
        }
    });
    return listProvincias;
}