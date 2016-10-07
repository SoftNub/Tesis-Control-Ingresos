<%-- 
    Document   : PreInscripcion
    Created on : 24-ago-2016, 20:56:21
    Author     : wilson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="../util/Header.jsp"/>
        <script src="<%=request.getContextPath()%>/js/modulocolegiado/Persona.js"></script>
        <script src="<%=request.getContextPath()%>/js/parametriageneral/EstadoCivil.js"></script>
        <script src="<%=request.getContextPath()%>/js/parametriageneral/Departamento.js"></script>
        <script src="<%=request.getContextPath()%>/js/parametriageneral/Provincia.js"></script>
        <script src="<%=request.getContextPath()%>/js/parametriageneral/Distrito.js"></script>
        <script src="<%=request.getContextPath()%>/js/parametriageneral/CondicionCasa.js"></script>
        <script src="<%=request.getContextPath()%>/js/modulocolegiado/AdmPersona.js"></script>
    </head>
    <body>
        <header>
            <nav>
                <jsp:include page="../util/Navbar.jsp"/>
                <h3 class="page-header"><c:out value="${TituloModulo}" /></h3>
            </nav>
        </header>
        <section class="container">
            <div class="btn-group btn-group-justified" id="panelOpciones">
                <div class="btn-group" role="group">
                <button type="button" class="btn btn-primary" id="Opcgrabar" 
                        title="<c:out value="${MsjGrabar}" />" accesskey="g">
                    <span class="glyphicon glyphicon-floppy-disk"></span>
                </button>
                </div>
                <div class="btn-group" role="group">
                <button type="button" class="btn btn-primary" id="Opceditar" 
                        title="<c:out value="${MsjEditar}" />" accesskey="u"
                        disabled>
                    <span class="glyphicon glyphicon-edit"></span>
                </button>
                </div>
                <div class="btn-group" role="group">
                <button type="button" class="btn btn-primary" id="OpcCancelar" 
                        title="<c:out value="${MsjCancelar}" />" accesskey="c">
                    <span class="glyphicon glyphicon-apple"></span>
                </button>
                </div>    
            </div>
            <div id = "panelError">
                
            </div>
            <div id = "panelGrabarColegiados">
                <br/>
                <div class="row"> 
                    <div class="col-lg-offset-9 col-lg-3">
                        <!--<div class="form-group">-->
                            <input type="text" id ="txtDni" class="form-control" 
                                   placeholder="Ingrese DNI" tabindex="1" maxlength="8">
<!--                            <div class="input-group-btn">
                                <button class="btn btn-default" type="button">
                                    <span class="glyphicon glyphicon-search"></span>
                                </button>
                            </div>-->
<!--                        </div>-->
                    </div>
                </div>
                <br/>
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">Datos Personales</h3>
                    </div>
                    <div class="panel-body">
                        <div class="row form-group"> 
                            <div class="col-lg-1">
                                <label for="apellidos">Apellidos (*):</label>
                            </div>
                            <div class="col-lg-2">
                                 <input type="text" class="form-control" id="txtApellidoPat" 
                                        placeholder = "Ape. Pat." tabindex="2" maxlength="50">
                            </div>
                            <div class="col-lg-2">
                                <input type="text" class="form-control" id="txtApellidoMat" 
                                       placeholder = "Ape. Mat." tabindex="3" maxlength="50">
                            </div>
                            <div class="col-lg-offset-1 col-lg-2">
                                <label for="estadoCivil">Estado Civil(*):</label>
                            </div>
                             <div class="col-lg-4">
                                 <select class="form-control" id="cboEstadoCivil" tabindex="5">
                                 </select>
                            </div>
                        </div>
                        <div class="row form-group"> 
                            <div class="col-lg-1">
                                <label for="nombres">Nombres(*):</label>
                            </div>
                            <div class="col-lg-4">
                                <input type="text" class="form-control" id="txtNombres" 
                                       placeholder = "Nombres" tabindex="4" maxlength="100">
                            </div>
                            <div class="col-lg-offset-1 col-lg-2">
                                 <label for="grupoSanguineo">Grupo Sanguineo:</label>
                            </div>
                            <div class="col-lg-2">
                                <input type="text" class="form-control" id="txtGrupoSan" 
                                       placeholder="Grup. Sanguineo" tabindex="6" maxlength="20">
                            </div>
                            <div class="col-lg-1">
                                <label for="rh">Rh:</label>
                            </div>
                            <div class="col-lg-1">
                               <input type="text" class="form-control" id="txtRh" 
                                      placeholder="Rh" tabindex="7" maxlength="10">
                            </div>
                        </div>
                        <div class="row form-group"> 
                            <div class="col-lg-3">
                                <label for="nombres">Fecha Nacimiento DD/MM/YYYY(*):</label>
                                <input type="text" class="form-control" id="txtFechaNacimiento" 
                                       placeholder = "Fecha. Nac." tabindex="8" maxlength="10">
                            </div>
                            <div class="col-lg-offset-1 col-lg-2">
                                 <label for="paisOrigen">Pais(*):</label>
                                  <select class="form-control" id="cboPaisOrigen" tabindex="8">
                                     <option value="0">--Seleccione--</option>
                                     <option value="1">Perú</option>
                                 </select>
                            </div>
                            <div class="col-lg-2">
                                <label for="departamentoOrigen">Departamento(*):</label>
                                <select class="form-control" id="cboDepartamentoOrigen" tabindex="9">
                                    <option value="0">--Seleccione--</option>
                                </select>
                            </div>
                            <div class="col-lg-2">
                                <label for="provinciaOrigen">Provincia(*):</label>
                                <select class="form-control" id="cboProvinciaOrigen" tabindex="10">
                                    <option value="0">--Seleccione--</option>
                                </select>
                            </div>
                            <div class="col-lg-2">
                                <label for="distritoOrigen">Distrito(*):</label>
                                <select class="form-control" id="cboDistritoOrigen" tabindex="11">
                                    <option value="0">--Seleccione--</option>
                                </select>
                            </div>
                        </div>
                        <div class="row form-group"> 
                            <div class="col-lg-3">
                                <label for="libretaMilitar">N° Libreta Militar:</label>
                                <input type="text" class="form-control" id="txtLibretaMilitar" 
                                       placeholder = "Libreta Militar" tabindex="12" maxlength="20">
                            </div>
                            <div class="col-lg-3">
                                <label for="carnetExtranjeria">N° Carnet de Extranjeria</label>
                                <input type="text" class="form-control" id="txtCarnetExtranjeria" 
                                       placeholder = "Carnet Extranjeria" tabindex="13" maxlength="20">
                            </div>
                            <div class="col-lg-3">
                                <label for="tarjetaIdentidad">T. Identidad (FFAA y PNP):</label>
                                <input type="text" class="form-control" id="txtTarjetaIdentidad" 
                                       placeholder = "T. Identidad (FFAA Y PNP)" tabindex="14" maxlength="20">
                            </div>
                            <div class="col-lg-3">
                                <label for="carnetEssalud">N° Carnet del Essalud:</label>
                                <input type="text" class="form-control" id="txtCarnetEssalud" 
                                       placeholder = "Carnet Essalud" tabindex="15" maxlength="20">
                            </div>
                        </div>
                        <div class="row form-group"> 
                            <div class="col-lg-5">
                                <label for="domicilioActual">Domicilio Actual(*):</label>
                                <input type="text" class="form-control" id="txtDomicilioActual" 
                                       placeholder = "Domicilio Actual" tabindex="16" maxlength="200">
                            </div>
                            <div class="col-lg-offset-1 col-lg-2">
                                <label for="distritoActual">Distrito Actual:</label>
                                <select class="form-control" id="cboDistritoActual" tabindex="17">
                                </select>
                            </div>
                            <div class="col-lg-2">
                                <label for="tarjetaIdentidad">Codigo Postal:</label>
                                <input type="text" class="form-control" id="txtCodigoPostal" 
                                       placeholder = "Cod. Postal" tabindex="18" maxlength="10">
                            </div>
                            <div class="col-lg-2">
                                <label for="distritoActual">Provincia Actual:</label>
                                <select class="form-control" id="cboProvinciaActual" tabindex="19">
                                </select>
                            </div>
                        </div>
                        <div class="row form-group"> 
                            <div class="col-lg-2">
                                <label for="condCasa">Cond. Casa:</label>
                                <select class="form-control" id="cboCondCasa" tabindex="20">
                                </select>
                            </div>
                            <div class="col-lg-2">
                                <label for="telfDomicilio">Telf. Domicilio (*):</label>
                                <input type="text" class="form-control" id="txtTelfDomicilio" 
                                       placeholder = "Telf. Domicilio" tabindex="21" maxlength="15">
                            </div>
                            <div class="col-lg-4">
                                <label for="email1">Email 1 (*):</label>
                                <input type="text" class="form-control" id="txtEmail1" 
                                       placeholder = "Email 1" tabindex="22" maxlength="100">
                            </div>
                            <div class="col-lg-4">
                                <label for="email2">Email 2:</label>
                                <input type="text" class="form-control" id="txtEmail2" 
                                       placeholder = "Email 2" tabindex="23" maxlength="100">
                            </div>
                        </div>
                    </div>
                    <div class="panel-footer">
<!--                        <div class="row">
                            <div class="col-lg-offset-6 col-lg-3">
                                <button type="button" class="btn btn-primary btn-block">Grabar</button>
                            </div>
                            <div class="col-lg-3">
                                <button type="button" class="btn btn-primary btn-block">Cancelar</button>
                            </div>
                        </div>-->
                    </div>
                </div>
            </div>
        </section>
        <footer>
            <jsp:include page="../util/Footer.jsp"/>
        </footer>    
    </body>
</html>
