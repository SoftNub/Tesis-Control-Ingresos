/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function Departamento (idDepartamento, nombreDepartamento , habilitado){
    this.idDepartamento = idDepartamento;
    this.nombreDepartamento = nombreDepartamento;
    this.habilitado = habilitado;
    this.indError = -1;
    this.msjError = "";
    this.listarAllDepartamento = listarAllDepartamento;
    this.grabarDepartamento = grabarDepartamento;
    this.editarDepartamento = editarDepartamento;
    this.eliminarDepartamento = eliminarDepartamento;
    this.listarDepartamentoId = listarDepartamentoId;
    this.listarProvinciaDeDepartamento = listarProvinciaDeDepartamento;
    this.grabarProvinciaDeDepartamento = grabarProvinciaDeDepartamento;
    this.eliminarProvinciaDeDepartamento = eliminarProvinciaDeDepartamento;
    this.listarDepartamentosHabilitados = listarDepartamentosHabilitados;
}

function ListDepartemento(Departamentos, indError, msjError){
    this.Departamentos = Departamentos;
    this.indError = indError;
    this.msjError = msjError;
}

function listarAllDepartamento(){
    var listDepartamento = new ListDepartemento();
    $.ajax({
        url : "../parametria/ListarAllDepartamentos.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : {},
        success : function(data) {
            listDepartamento = new ListDepartemento(data.listaDepartamentos, data.indError, data.msjError);
        }
    });
    return listDepartamento;
}

function grabarDepartamento(){
    var departamento;
    var departamento2 ={
        "nombreDepartamento": this.nombreDepartamento,
        "habilitado": this.habilitado
    };
    departamento = new Departamento();
    $.ajax({
        url : "../parametria/GrabarDepartamento.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : JSON.stringify(departamento2),
        success : function(data) {
            departamento.indError = data.indError;
            departamento.msjError = data.msjError;
        }
    });
    return departamento;
}

function editarDepartamento(){
    var departamento;
    var departamento2 ={
        "idDepartamento": this.idDepartamento,
        "nombreDepartamento": this.nombreDepartamento,
        "habilitado": this.habilitado
    };
    departamento = new Departamento();
    $.ajax({
        url : "../parametria/EditarDepartamento.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : JSON.stringify(departamento2),
        success : function(data) {
            departamento.indError = data.indError;
            departamento.msjError = data.msjError;
        }
    });
    return departamento;
}

function eliminarDepartamento(){
    var departamento;
    var departamento2 ={
        "idDepartamento": this.idDepartamento
    };
    departamento = new Departamento();
    $.ajax({
        url : "../parametria/EliminarDepartamento.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : JSON.stringify(departamento2),
        success : function(data) {
            departamento.indError = data.indError;
            departamento.msjError = data.msjError;
        }
    });
    return departamento;
}

function listarDepartamentoId(){
    var departamento;
    var departamento2 ={
        "idDepartamento": this.idDepartamento
    };
    departamento = new Departamento();
    $.ajax({
        url : "../parametria/ListarDepartamentoId.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : JSON.stringify(departamento2),
        success : function(data) {
            departamento.nombreDepartamento = data.nombreDepartamento;
            departamento.habilitado = data.habilitado;
            departamento.indError = data.indError;
            departamento.msjError = data.msjError;
        }
    });
    return departamento;
}

function listarProvinciaDeDepartamento(){
    var listaProvincias;
    var departamento2 ={
        "idDepartamento": this.idDepartamento
    };
    listaProvincias = new ListProvincia();
    $.ajax({
        url : "../parametria/ListarProvinciaDepartamento.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : JSON.stringify(departamento2),
        success : function(data) {
            listaProvincias = new ListProvincia(data.listaProvincias, data.indError, data.msjError);
        }
    });
    return listaProvincias;
}

function grabarProvinciaDeDepartamento(idProvincia){
    var departamento;
    var departamento2 ={
        "idDepartamento": this.idDepartamento
    };
    var provincia2 ={
        "idProvincia" : idProvincia
    };
    departamento = new Departamento();
    $.ajax({
        url : "../parametria/GrabarProvinciaDepartamento.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : JSON.stringify({departamento: departamento2, provincia : provincia2}),
        success : function(data) {
            departamento.indError = data.indError;
            departamento.msjError = data.msjError;
        }
    });
    return departamento;
}

function eliminarProvinciaDeDepartamento(idProvincia){
    var departamento;
    var departamento2 ={
        "idDepartamento": this.idDepartamento
    };
    var provincia2 ={
        "idProvincia" : idProvincia
    };
    departamento = new Departamento();
    $.ajax({
        url : "../parametria/EliminarProvinciaDepartamento.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : JSON.stringify({departamento: departamento2, provincia : provincia2}),
        success : function(data) {
            departamento.indError = data.indError;
            departamento.msjError = data.msjError;
        }
    });
    return departamento;
}

function listarDepartamentosHabilitados(){
    var departamento2 = {
        "habilitado" : this.habilitado
    };
    var listDepartamentos = new ListDepartemento();
    $.ajax({
        url : "../parametria/ListarDepartamentosHabilitados.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : JSON.stringify(departamento2),
        success : function(data) {
            listDepartamentos = new ListDepartemento(data.listaDepartamentos, data.indError, data.msjError);
        }
    });
    return listDepartamentos;
}