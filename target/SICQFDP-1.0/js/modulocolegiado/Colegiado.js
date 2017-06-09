/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function Colegiado(dni, apePaterno, apeMaterno, nombres,grupoSanguineo,rh
        , fechaNacimiento, libretaMilitar, carnetExt, ffaa_pnp, carnetEssalud
        , domicilio, idEstadoCivil, idPaisOri, idDepartamentoOri
        , idProvinciaOri, idDistritoOri, idCondicionCasa, idDistritoAct
        , idProvinciaAct, telfDomicilio, emailPrincipal, emailSecundario
        , codigoPostal, indExiste, numColegiatura, estado, indSolicitud
        , fechaIngreso, fechaInscripcion, esColegiado, expeLaborales, familiares,
        titulacion, otrosEstudios){
    this.dni = dni;
    this.apePaterno = apePaterno;
    this.apeMaterno = apeMaterno;
    this.nombres = nombres;
    this.grupoSanguineo = grupoSanguineo;
    this.rh = rh;
    this.fechaNacimiento = fechaNacimiento;
    this.libretaMilitar = libretaMilitar;
    this.carnetExt = carnetExt;
    this.ffaa_pnp = ffaa_pnp;
    this.carnetEssalud = carnetEssalud;
    this.domicilio = domicilio;
    this.idEstadoCivil = idEstadoCivil;
    this.idPaisOri = idPaisOri;
    this.idDepartamentoOri = idDepartamentoOri;
    this.idProvinciaOri = idProvinciaOri;
    this.idDistritoOri = idDistritoOri;
    this.idCondicionCasa = idCondicionCasa;
    this.idDistritoAct = idDistritoAct;
    this.idProvinciaAct = idProvinciaAct;
    this.telfDomicilio = telfDomicilio;
    this.emailPrincipal = emailPrincipal;
    this.emailSecundario = emailSecundario;
    this.codigoPostal = codigoPostal;
    this.indExiste = indExiste;
    this.numColegiatura = numColegiatura;
    this.estado = estado;
    this.indSolicitud = indSolicitud;
    this.fechaIngreso = fechaIngreso;
    this.fechaInscripcion = fechaInscripcion;
    this.esColegiado = esColegiado;
    this.expeLaborales = expeLaborales;
    this.familiares = familiares;
    this.titulacion = titulacion;
    this.otrosEstudios = otrosEstudios;
    this.idCampoError = "";
    this.indError = -1;
    this.msjError = "";
    this.buscarPersonaColegiadoInscripIngre = buscarPersonaColegiadoInscripIngre;
    this.mantPersonaColegiadoInscripIngre = mantPersonaColegiadoInscripIngre;
    this.grabarSolictudEgresoColegiatura = grabarSolictudEgresoColegiatura;
}

function ListColegiado(listaColegiados, indError, msjError){
    this.listaColegiados = listaColegiados;
    this.indError = indError;
    this.msjError = msjError;
    this.buscarColegiadosEgreso = buscarColegiadosEgreso;
    this.grabarEgresoColegiatura = grabarEgresoColegiatura;
    this.buscarColegiadosPagos = buscarColegiadosPagos;
}

function buscarPersonaColegiadoInscripIngre(opc){
    var colegiado;
    var colegiado2 ={
        "dni": this.dni,
        "numColegiatura" : this.numColegiatura
    };
    colegiado = new Colegiado();
    $.ajax({
        url : "buscarColegiado.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : JSON.stringify({opcion: opc, colegiado : colegiado2}),
        success : function(data) {
            colegiado.indError = data.indError;
            colegiado.msjError = data.msjError;
            if (data.indError === 0) {
                colegiado.dni = data.dni;
                colegiado.numColegiatura = data.numColegiatura;
                colegiado.apePaterno = data.apePaterno;
                colegiado.apeMaterno = data.apeMaterno;
                colegiado.nombres = data.nombres;
                colegiado.grupoSanguineo = data.grupoSanguineo;
                colegiado.rh = data.rh;
                colegiado.fechaNacimiento = data.fechaNacimiento;
                colegiado.libretaMilitar = data.libretaMilitar;
                colegiado.carnetExt = data.carnetExt;
                colegiado.ffaa_pnp = data.ffaa_pnp;
                colegiado.carnetEssalud = data.carnetEssalud;
                colegiado.domicilio = data.domicilio;
                colegiado.idEstadoCivil = data.idEstadoCivil;
                colegiado.idPaisOri = data.idPaisOri;
                colegiado.idDepartamentoOri = data.idDepartamentoOri;
                colegiado.idProvinciaOri = data.idProvinciaOri;
                colegiado.idDistritoOri = data.idDistritoOri;
                colegiado.idCondicionCasa = data.idCondicionCasa;
                colegiado.idDistritoAct = data.idDistritoAct;
                colegiado.idProvinciaAct = data.idProvinciaAct;
                colegiado.emailPrincipal = data.emailPrincipal;
                colegiado.emailSecundario = data.emailSecundario;
                colegiado.codigoPostal = data.codigoPostal;
                colegiado.indExiste = data.indExiste;
                colegiado.telfDomicilio = data.telfDomicilio;
                colegiado.expeLaborales = data.expeLaborales;
                colegiado.familiares = data.familiares;
                colegiado.titulacion = data.titulacion;
                colegiado.otrosEstudios = data.otrosEstudios;
                colegiado.indSolicitud = data.indSolicitud;
                colegiado.fechaIngreso = data.fechaIngreso;
                colegiado.fechaInscripcion = data.fechaInscripcion;
                colegiado.esColegiado = data.esColegiado;
            }
        }
    });
    return colegiado;
}

function mantPersonaColegiadoInscripIngre(opc,indIncripIngre){
    var colegiado;
    var colegiado2 ={
        "numColegiatura" : this.numColegiatura,
        "fechaIngreso" : this.fechaIngreso,
        "fechaInscripcion" : this.fechaInscripcion,
        "esColegiado" : this.esColegiado,
        "dni": this.dni,
        "apePaterno" : this.apePaterno,
        "apeMaterno" : this.apeMaterno,
        "nombres" : this.nombres,
        "grupoSanguineo" : this.grupoSanguineo,
        "rh" : this.rh,
        "fechaNacimiento" : this.fechaNacimiento,
        "libretaMilitar" : this.libretaMilitar,
        "carnetExt" : this.carnetExt,
        "ffaa_pnp" : this.ffaa_pnp,
        "carnetEssalud" : this.carnetEssalud,
        "domicilio" : this.domicilio,
        "idEstadoCivil" : this.idEstadoCivil,
        "idPaisOri" : this.idPaisOri,
        "idDepartamentoOri" : this.idDepartamentoOri,
        "idProvinciaOri" : this.idProvinciaOri,
        "idDistritoOri" : this.idDistritoOri,
        "idCondicionCasa" : this.idCondicionCasa,
        "idDistritoAct" : this.idDistritoAct,
        "idProvinciaAct" : this.idProvinciaAct,
        "emailPrincipal" : this.emailPrincipal,
        "emailSecundario" : this.emailSecundario,
        "codigoPostal" : this.codigoPostal,
        "telfDomicilio" : this.telfDomicilio,
        "indExiste" : this.indExiste,
        "expeLaborales" : this.expeLaborales,
        "familiares" : this.familiares,
        "titulacion" : this.titulacion,
        "otrosEstudios" : this.otrosEstudios
    };
    colegiado = new Colegiado();
    $.ajax({
        url : "mantColegiadoInscripcion.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : JSON.stringify({opcion: opc, indicador : indIncripIngre, colegiado : colegiado2}),
        success : function(data) {
            colegiado.indError = data.indError;
            colegiado.msjError = data.msjError;
            colegiado.idCampoError = data.idCampoError;
        }
    });
    return colegiado;
}

function grabarSolictudEgresoColegiatura(){
    var colegiado;
    var colegiado2 ={
        "numColegiatura" : this.numColegiatura,
        "dni": this.dni
    };
    colegiado = new Colegiado();
    $.ajax({
        url : "grabarSolicitudEgreso.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : JSON.stringify(colegiado2),
        success : function(data) {
            colegiado.indError = data.indError;
            colegiado.msjError = data.msjError;
        }
    });
    return colegiado;
}

function buscarColegiadosEgreso(){
    var listColegiados;
    listColegiados = new ListColegiado();
    $.ajax({
        url : "listarColegiadosEgreso.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : {},
        success : function(data) {
            listColegiados.listaColegiados = data.listaColegiados;
            listColegiados.indError = data.indError;
            listColegiados.msjError = data.msjError;
        }
    });
    return listColegiados;
}

function grabarEgresoColegiatura(){
    var listColegiado;
    var listColegiado2 ={
        "listaColegiados" : this.listaColegiados
    };
    listColegiado = new ListColegiado();
    $.ajax({
        url : "grabarEgresoColegiatura.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : JSON.stringify(listColegiado2),
        success : function(data) {
            listColegiado.indError = data.indError;
            listColegiado.msjError = data.msjError;
        }
    });
    return listColegiado;
}

function buscarColegiadosPagos(tipoOperacion, tipoDocumento, col){
    var listColegiados;
    listColegiados = new ListColegiado();
    var colegiado2 ={
        "numColegiatura" : col.numColegiatura,
        "dni": col.dni
    };
    $.ajax({
        url : "../colegiados/buscarColegiadosPagos.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : JSON.stringify({tipoOperacion:tipoOperacion, tipoDocumento:tipoDocumento, colegiado: colegiado2}),
        success : function(data) {
            listColegiados.listaColegiados = data.listaColegiados;
            listColegiados.indError = data.indError;
            listColegiados.msjError = data.msjError;
        }
    });
    return listColegiados;
}