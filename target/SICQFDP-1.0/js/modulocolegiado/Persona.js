/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function Persona(dni, apePaterno, apeMaterno, nombres,grupoSanguineo,rh
        , fechaNacimiento, libretaMilitar, carnetExt, ffaa_pnp, carnetEssalud
        , domicilio, idEstadoCivil, idPaisOri, idDepartamentoOri
        , idProvinciaOri, idDistritoOri, idCondicionCasa, idDistritoAct
        , idProvinciaAct, telfDomicilio, emailPrincipal, emailSecundario
        , codigoPostal, indExiste){
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
    this.indError = -1;
    this.msjError = "";
    this.idCampoError = "";
    this.buscarPersonaPreInscripcion = buscarPersonaPreInscripcion;
    this.mantPersonaPreInscripcion = mantPersonaPreInscripcion;
    //this.editarPersonaPreInscripcion = editarPersonaPreInscripcion;
}

function buscarPersonaPreInscripcion(opc){
    var persona;
    var persona2 ={
        "dni": this.dni
    };
    persona = new Persona();
    $.ajax({
        url : "buscarPersona.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : JSON.stringify({opcion: opc, persona : persona2}),
        success : function(data) {
            persona.indError = data.indError;
            persona.msjError = data.msjError;
            if (data.indError === 0) {
                persona.dni = data.dni;
                persona.apePaterno = data.apePaterno;
                persona.apeMaterno = data.apeMaterno;
                persona.nombres = data.nombres;
                persona.grupoSanguineo = data.grupoSanguineo;
                persona.rh = data.rh;
                persona.fechaNacimiento = data.fechaNacimiento;
                persona.libretaMilitar = data.libretaMilitar;
                persona.carnetExt = data.carnetExt;
                persona.ffaa_pnp = data.ffaa_pnp;
                persona.carnetEssalud = data.carnetEssalud;
                persona.domicilio = data.domicilio;
                persona.idEstadoCivil = data.idEstadoCivil;
                persona.idPaisOri = data.idPaisOri;
                persona.idDepartamentoOri = data.idDepartamentoOri;
                persona.idProvinciaOri = data.idProvinciaOri;
                persona.idDistritoOri = data.idDistritoOri;
                persona.idCondicionCasa = data.idCondicionCasa;
                persona.idDistritoAct = data.idDistritoAct;
                persona.idProvinciaAct = data.idProvinciaAct;
                persona.emailPrincipal = data.emailPrincipal;
                persona.emailSecundario = data.emailSecundario;
                persona.codigoPostal = data.codigoPostal;
                persona.indExiste = data.indExiste;
                persona.telfDomicilio = data.telfDomicilio;
            }
        }
    });
    return persona;
}

function mantPersonaPreInscripcion(opc){
    var persona;
    var persona2 ={
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
        "telfDomicilio" : this.telfDomicilio
    };
    persona = new Persona();
    $.ajax({
        url : "mantPersonaPreInscripcion.action",
        type : "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async: false,
        mimeType: 'application/json',
        data : JSON.stringify({opcion: opc, persona : persona2}),
        success : function(data) {
            persona.indError = data.indError;
            persona.msjError = data.msjError;
            persona.idCampoError = data.idCampoError;
        }
    });
    return persona;
}

//function editarPersonaPreInscripcion(){
//    var persona;
//    var persona2 ={
//        "dni": this.dni,
//        "apePaterno" : this.apePaterno,
//        "apeMaterno" : this.apeMaterno,
//        "nombres" : this.nombres,
//        "grupoSanguineo" : this.grupoSanguineo,
//        "rh" : this.rh,
//        "fechaNacimiento" : this.fechaNacimiento,
//        "libretaMilitar" : this.libretaMilitar,
//        "carnetExt" : this.carnetExt,
//        "ffaa_pnp" : this.ffaa_pnp,
//        "carnetEssalud" : this.carnetEssalud,
//        "domicilio" : this.domicilio,
//        "idEstadoCivil" : this.idEstadoCivil,
//        "idPaisOri" : this.idPaisOri,
//        "idDepartamentoOri" : this.idDepartamentoOri,
//        "idProvinciaOri" : this.idProvinciaOri,
//        "idDistritoOri" : this.idDistritoOri,
//        "idCondicionCasa" : this.idCondicionCasa,
//        "idDistritoAct" : this.idDistritoAct,
//        "idProvinciaAct" : this.idProvinciaAct,
//        "emailPrincipal" : this.emailPrincipal,
//        "emailSecundario" : this.emailSecundario,
//        "codigoPostal" : this.codigoPostal,
//        "telfDomicilio" : this.telfDomicilio
//    };
//    persona = new Persona();
//    $.ajax({
//        url : "editarPersona.action",
//        type : "POST",
//        contentType : 'application/json; charset=utf-8',
//        dataType : 'json',
//        async: false,
//        mimeType: 'application/json',
//        data : JSON.stringify(persona2),
//        success : function(data) {
//            persona.indError = data.indError;
//            persona.msjError = data.msjError;
//        }
//    });
//    return persona;
//}