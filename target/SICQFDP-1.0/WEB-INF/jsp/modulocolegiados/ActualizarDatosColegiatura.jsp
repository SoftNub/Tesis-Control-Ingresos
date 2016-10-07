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
        <script src="<%=request.getContextPath()%>/js/modulocolegiado/Colegiado.js"></script>
        <script src="<%=request.getContextPath()%>/js/modulocolegiado/ExperienciaLaboral.js"></script>
        <script src="<%=request.getContextPath()%>/js/modulocolegiado/Familiar.js"></script>
        <script src="<%=request.getContextPath()%>/js/modulocolegiado/OtrosGrados.js"></script>
        <script src="<%=request.getContextPath()%>/js/modulocolegiado/Titulacion.js"></script>
        <script src="<%=request.getContextPath()%>/js/parametriageneral/EstadoCivil.js"></script>
        <script src="<%=request.getContextPath()%>/js/parametriageneral/Departamento.js"></script>
        <script src="<%=request.getContextPath()%>/js/parametriageneral/Provincia.js"></script>
        <script src="<%=request.getContextPath()%>/js/parametriageneral/Distrito.js"></script>
        <script src="<%=request.getContextPath()%>/js/parametriageneral/CondicionCasa.js"></script>
        <script src="<%=request.getContextPath()%>/js/modulocolegiado/AdmColegiado2.js"></script>
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
                <button type="button" class="btn btn-primary" id="OpcCancelar" 
                        title="<c:out value="${MsjCancelar}" />" accesskey="c">
                    <span class="glyphicon glyphicon-apple"></span>
                </button>
                </div>    
            </div>
            <div id = "panelError">
                
            </div>        
            <br/>        
            <div class="row form-group"> 
                <div class="col-lg-offset-4 col-lg-4">
                    <select class="form-control" id="cboOpcionBusqueda" tabindex="1">
                        <option value="1">BUSCAR POR DNI</option>
                        <option value="2">BUSCAR POR NUMERO COLEGIATURA</option>
                    </select>
                </div>
                <div class="col-lg-4">
<!--                    <div class="input-group">-->
                        <input type="text" class="form-control" id="txtDniNumColegiatura"
                               placeholder="Ingrese DNI o NUMERO COLEGIATURA" tabindex="2" maxlength="10">
<!--                        <div class="input-group-btn">
                            <button class="btn btn-default" type="button">
                                <span class="glyphicon glyphicon-search"></span>
                            </button>
                        </div>-->
                    <!--</div>-->
                </div>
            </div>
            <ul class="nav nav-tabs">
                <li class="active"><a data-toggle="tab" href="#datosPersonales">Datos Personales</a></li>
                <li><a data-toggle="tab" href="#expLaboral">Experiencia Laboral</a></li>
                <li><a data-toggle="tab" href="#datosFamiliares">Datos Familiares</a></li>
                <li><a data-toggle="tab" href="#gradosTitulos">Grados y Titulos</a></li>
            </ul>
            <div class="tab-content">
                <div id="datosPersonales" class="tab-pane fade in active">
                    <div id = "panelGrabarColegiados">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h3 class="panel-title">Datos Personales</h3>
                            </div>
                            <div class="panel-body">
                                <div class="row form-group"> 
<!--                                    <div class="col-lg-2">
                                        <label>Numero Colegiatura (*):</label>
                                    </div>
                                    <div class="col-lg-2">
                                        <input type="text" class="form-control" id="txtNumColegiatura" 
                                               placeholder = "Num Colegiatura" tabindex="3" maxlength="10">
                                    </div>-->
                                    <div class="col-lg-3">
                                        <label>Fecha Inscripcion DD/MM/YYYY:</label>
                                    </div>
                                    <div class="col-lg-3">
                                        <input type="text" class="form-control" id="txtFechaInscripcion" 
                                               placeholder = "Fecha Inscripcion." tabindex="3" maxlength="10">
                                    </div>
<!--                                    <div class="col-lg-3">
                                        <label>Fecha Ingreso DD/MM/YYYY:</label>
                                    </div>
                                    <div class="col-lg-3">
                                        <input type="text" class="form-control" id="txtFechaIngreso" 
                                               placeholder = "Fecha Ingreso." tabindex="4" maxlength="10">
                                    </div>-->
                                </div>
                                <div class="row form-group"> 
                                    <div class="col-lg-1">
                                        <label for="apellidos">Apellidos (*):</label>
                                    </div>
                                    <div class="col-lg-2">
                                        <input type="text" class="form-control" id="txtApellidoPat" 
                                               placeholder = "Ape. Pat." tabindex="5" maxlength="50">
                                    </div>
                                    <div class="col-lg-2">
                                        <input type="text" class="form-control" id="txtApellidoMat" 
                                               placeholder = "Ape. Mat." tabindex="6" maxlength="50">
                                    </div>
                                    <div class="col-lg-offset-1 col-lg-2">
                                        <label for="estadoCivil">Estado Civil (*):</label>
                                    </div>
                                    <div class="col-lg-4">
                                        <select class="form-control" id="cboEstadoCivil" tabindex="8">
                                            
                                        </select>
                                    </div>
                                </div>
                                <div class="row form-group"> 
                                    <div class="col-lg-1">
                                        <label for="nombres">Nombres (*):</label>
                                    </div>
                                    <div class="col-lg-4">
                                        <input type="text" class="form-control" id="txtNombres" 
                                               placeholder = "Nombres" tabindex="7" maxlength="100">
                                    </div>
                                    <div class="col-lg-offset-1 col-lg-2">
                                        <label for="grupoSanguineo">Grupo Sanguineo:</label>
                                    </div>
                                    <div class="col-lg-2">
                                        <input type="text" class="form-control" id="txtGrupoSan" 
                                               placeholder="Grup. Sanguineo" tabindex="9" maxlength="20">
                                    </div>
                                    <div class="col-lg-1">
                                        <label for="rh">Rh:</label>
                                    </div>
                                    <div class="col-lg-1">
                                        <input type="text" class="form-control" id="txtRh" 
                                               placeholder="Rh" tabindex="10" maxlength="10">
                                    </div>
                                </div>
                                <div class="row form-group"> 
                                    <div class="col-lg-3">
                                        <label for="nombres">Fecha Nacimiento DD/MM/YYYY (*):</label>
                                        <input type="text" class="form-control" id="txtFechaNacimiento" 
                                               placeholder = "Fecha. Nac." tabindex="11" maxlength="10">
                                    </div>
                                    <div class="col-lg-offset-1 col-lg-2">
                                        <label for="paisOrigen">Pais (*):</label>
                                        <select class="form-control" id="cboPaisOrigen" tabindex="12">
                                            <option value="0">--Seleccione--</option>
                                            <option value="1">PERU</option>
                                        </select>
                                    </div>
                                    <div class="col-lg-2">
                                        <label for="departamentoOrigen">Departamento (*):</label>
                                        <select class="form-control" id="cboDepartamentoOrigen" tabindex="13">
                                            <option>--Seleccione--</option>
                                            <option>Perú</option>
                                        </select>
                                    </div>
                                    <div class="col-lg-2">
                                        <label for="provinciaOrigen">Provincia (*):</label>
                                        <select class="form-control" id="cboProvinciaOrigen" tabindex="14">
                                            <option>--Seleccione--</option>
                                        </select>
                                    </div>
                                    <div class="col-lg-2">
                                        <label for="distritoOrigen">Distrito (*):</label>
                                        <select class="form-control" id="cboDistritoOrigen" tabindex="15">
                                            <option>--Seleccione--</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="row form-group"> 
                                    <div class="col-lg-3">
                                        <label for="libretaMilitar">N° Libreta Militar:</label>
                                        <input type="text" class="form-control" id="txtLibretaMilitar" 
                                               placeholder = "Libreta Militar" tabindex="16" maxlength="20">
                                    </div>
                                    <div class="col-lg-3">
                                        <label for="carnetExtranjeria">N° Carnet de Extranjeria</label>
                                        <input type="text" class="form-control" id="txtCarnetExtranjeria" 
                                               placeholder = "Carnet Extranjeria" tabindex="16" maxlength="20">
                                    </div>
                                    <div class="col-lg-3">
                                        <label for="tarjetaIdentidad">T. Identidad (FFAA y PNP):</label>
                                        <input type="text" class="form-control" id="txtTarjetaIdentidad" 
                                               placeholder = "T. Identidad (FFAA Y PNP)" tabindex="18" maxlength="20">
                                    </div>
                                    <div class="col-lg-3">
                                        <label for="carnetEssalud">N° Carnet del Essalud:</label>
                                        <input type="text" class="form-control" id="txtCarnetEssalud" 
                                               placeholder = "Carnet Essalud" tabindex="19" maxlength="20">
                                    </div>
                                </div>
                                <div class="row form-group"> 
                                    <div class="col-lg-5">
                                        <label for="domicilioActual">Domicilio Actual (*):</label>
                                        <input type="text" class="form-control" id="txtDomicilioActual" 
                                               placeholder = "Domicilio Actual" tabindex="20" maxlength="200">
                                    </div>
                                    <div class="col-lg-offset-1 col-lg-2">
                                        <label for="distritoActual">Distrito Actual:</label>
                                        <select class="form-control" id="cboDistritoActual" tabindex="21">
                                            <option>--Seleccione--</option>
                                        </select>
                                    </div>
                                    <div class="col-lg-2">
                                        <label for="tarjetaIdentidad">Codigo Postal:</label>
                                        <input type="text" class="form-control" id="txtCodigoPostal" 
                                               placeholder = "Cod. Postal" tabindex="22" maxlength="10">
                                    </div>
                                    <div class="col-lg-2">
                                        <label for="distritoActual">Provincia Actual:</label>
                                        <select class="form-control" id="cboProvinciaActual" tabindex="23">
                                            <option>--Seleccione--</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="row form-group"> 
                                    <div class="col-lg-2">
                                        <label for="condCasa">Cond. Casa:</label>
                                        <select class="form-control" id="cboCondCasa" tabindex="24">
                                            <option>--Seleccione--</option>
                                        </select>
                                    </div>
                                    <div class="col-lg-2">
                                        <label for="telfDomicilio">Telf. Domicilio (*):</label>
                                        <input type="text" class="form-control" id="txtTelfDomicilio" 
                                               placeholder = "Telf. Domicilio" tabindex="25" maxlength="15">
                                    </div>
                                    <div class="col-lg-4">
                                        <label for="email1">Email 1 (*):</label>
                                        <input type="text" class="form-control" id="txtEmail1" 
                                               placeholder = "Email 1" tabindex="26" maxlength="100">
                                    </div>
                                    <div class="col-lg-4">
                                        <label for="email2">Email 2:</label>
                                        <input type="text" class="form-control" id="txtEmail2" 
                                               placeholder = "Email 2" tabindex="27" maxlength="100">
                                    </div>
                                </div>
                            </div>
                            <div class="panel-footer">
                                <div class="row">
                                    <div class="col-lg-offset-9 col-lg-3">
                                        <button type="button" class="btn btn-primary btn-block" onclick = "visibleTab(2)">Siguiente &GT;&GT;</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="expLaboral" class="tab-pane fade">
                    <div id = "panelExperienciaLaboral">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h3 class="panel-title">Experiencia Laboral</h3>
                            </div>
                            <div class="panel-body">
                                <div class="row form-group"> 
                                    <div class="col-lg-1">
                                        <button type="button" id="nuevaExpLaboral" class="btn btn-primary btn-block"><span class="glyphicon glyphicon-plus"></span></button>
                                    </div>
                                </div>
                                <div id="expLaborales">
                                </div>  
                            </div>
                            <div class="panel-footer">
                                <div class="row">
                                    <div class="col-lg-offset-6 col-lg-3">
                                        <button type="button" class="btn btn-primary btn-block" onclick = "visibleTab(1)">&LT;&LT; Anterior</button>
                                    </div>
                                    <div class="col-lg-3">
                                        <button type="button" class="btn btn-primary btn-block" onclick = "visibleTab(3)">Siguiente &GT;&GT;</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="datosFamiliares" class="tab-pane fade">
                    <div id = "panelDatosFamiliares">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h3 class="panel-title">Datos Familiares</h3>
                            </div>
                            <div class="panel-body">
                                <div class="row form-group"> 
                                    <div class="col-lg-1">
                                        <button type="button" id="btnDatosFamilia" class="btn btn-primary btn-block"><span class="glyphicon glyphicon-plus"></span></button>
                                    </div>
                                </div>
                                <div id="datosFamilia">
<!--                                    <div class="row form-group"> 
                                        <div class="col-lg-offset-1 col-lg-6">
                                            <label for="nombreDependientes">Nombre de Dependientes:</label>
                                            <input type="text" class="form-control" id="txtNombreDependiente" name="nombreDependientes" placeholder = "Nombre Dependientes">
                                        </div>
                                        <div class="col-lg-2">
                                            <label for="parentesco">Parentesco:</label>
                                            <input type="text" class="form-control" id="txtParentesco" name="parentesco" placeholder = "Parentesco">
                                        </div>
                                        <div class="col-lg-1">
                                            <label for="edadPariente">Edad:</label>
                                            <input type="text" class="form-control" id="txtEdadPariente" name="edadParientes" placeholder = "Edad">
                                        </div>
                                        <div class="col-lg-1">
                                            <label></label>
                                            <a class="btn btn-danger" href="#">Quitar</a>
                                        </div>
                                    </div>-->
                                </div>    
                            </div>
                            <div class="panel-footer">
                                <div class="row">
                                    <div class="col-lg-offset-6 col-lg-3">
                                        <button type="button" class="btn btn-primary btn-block" onclick = "visibleTab(2)" >&LT;&LT; Anterior</button>
                                    </div>
                                    <div class="col-lg-3">
                                        <button type="button" class="btn btn-primary btn-block" onclick = "visibleTab(4)">Siguiente &GT;&GT;</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="gradosTitulos" class="tab-pane fade">
                     <div id = "panelGradosTitulos">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h3 class="panel-title">Grados y Titulos</h3>
                            </div>
                            <div class="panel-body">
                                <div class="row form-group"> 
                                    <div class="col-lg-4">
                                        <label for="gradoQF">Quimico Farmacéutico expedido por:</label>
                                        <input type="text" class="form-control" id="txtUniversidad" placeholder = "Universidad Titulación">
                                    </div>
                                    <div class="col-lg-2">
                                        <label for="añoTitulo">Año :</label>
                                        <input type="text" class="form-control" id="txtAnnoTitulo" placeholder = "fecha titulo">
                                    </div>
                                    <div class="col-lg-3">
                                        <label for="numTitulo">N° Titulo:</label>
                                        <input type="text" class="form-control" id="txtNumTitulo" placeholder = "Num. Titulo">
                                    </div>
                                    <div class="col-lg-3">
                                        <label for="numRegistro">N° Registro:</label>
                                        <input type="text" class="form-control" id="txtNumRegistro" placeholder = "Num. Registro">
                                    </div>
                                </div>
                                <div class="row form-group"> 
                                    <div class="col-lg-4">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <label for="especialidad">Especialidad: <button type="button" id="btnEspecialidad" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span></button></label>
                                            </div>
                                        </div>
                                       <div id="especialidades">
<!--                                            <div class="row form-group">
                                                <div class="col-lg-9">
                                                    <input type="text" class="form-control" name="especialidad" placeholder = "Especialidad">
                                                </div>
                                                <div class="col-lg-2">
                                                    <a class="btn btn-danger" href="#" role="button"><span class="glyphicon glyphicon-minus"></span></a>
                                                </div>
                                            </div>-->
                                        </div>
                                    </div>
                                    <div class="col-lg-4">
                                        <label for="maestria">Maestria: <button type="button" id="btnMaestrias" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span></button></label>
                                        <div id="maestrias">
<!--                                            <div class="row">
                                                <div class="col-lg-9">
                                                     <input type="text" class="form-control" name="maestria" placeholder = "Maestria">
                                                </div>
                                                <div class="col-lg-2">
                                                    <a class="btn btn-danger" href="#"><span class="glyphicon glyphicon-minus"></span></a>
                                                </div>
                                            </div>-->
                                        </div>
                                    </div>
                                    <div class="col-lg-4">
                                        <label for="doctorado">Doctorado: <button type="button" id="btnDoctorado" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span></button></label>
                                        <div id="doctorados">
<!--                                            <div class="row">
                                                <div class="col-lg-9">
                                                    <input type="text" class="form-control" name="doctorado" placeholder = "Doctorado">
                                                </div>
                                                <div class="col-lg-2">
                                                    <a class="btn btn-danger" href="#"><span class="glyphicon glyphicon-minus"></span></a>
                                                </div>
                                            </div>-->
                                        </div>  
                                    </div>
                                </div>
                                <div class="row form-group"> 
                                    <div class="col-lg-4">
                                        <label for="socCientificasNac">Soc. Cientificas Nacionales: <button type="button" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span></button></label>
                                       <div id="sociedadesNacioneales">
<!--                                            <div class="row">
                                                <div class="col-lg-9">
                                                    <input type="text" class="form-control" name="socCientificasNac" placeholder = "Soc. Cientifica Nacional">
                                                </div>
                                                <div class="col-lg-2">
                                                    <a class="btn btn-danger" href="#"><span class="glyphicon glyphicon-minus"></span></a>
                                                </div>
                                            </div>-->
                                        </div>  
                                    </div>
                                    <div class="col-lg-4">
                                        <label for="socCientificasIntnac">Soc. Cientificas Internacionales: <button type="button" id="btnSocInternacional" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span></button></label>
                                        <div id="SociedadesInternacionales">
<!--                                            <div class="row">
                                                <div class="col-lg-9">
                                                     <input type="text" class="form-control" name="socCientificasIntnac" placeholder = "Soc. Cientifica Internacional">
                                                </div>
                                                <div class="col-lg-2">
                                                    <a class="btn btn-danger" href="#"><span class="glyphicon glyphicon-minus"></span></a>
                                                </div>
                                            </div>-->
                                        </div>    
                                    </div>
                                    <div class="col-lg-4">
                                        <label for="revCientifica">Revistas Cientificas: <button type="button" id="btnRevCientifica" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span></button></label>
                                        <div id="revistaCientifica">
<!--                                            <div class="row">
                                                <div class="col-lg-9">
                                                    <input type="text" class="form-control" name="revCientifica" placeholder = "Revista Cientifica">
                                                </div>
                                                <div class="col-lg-2">
                                                    <a class="btn btn-danger" href="#"><span class="glyphicon glyphicon-minus"></span></a>
                                                </div>
                                            </div>-->
                                        </div> 
                                    </div>
                                </div>
                            </div>
                            <div class="panel-footer">
                                <div class="row">
                                    <div class="col-lg-offset-9 col-lg-3">
                                        <button type="button" class="btn btn-primary btn-block" onclick = "visibleTab(3)">&LT;&LT; Anterior</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
<!--            <div class="row">
                <div class="col-lg-offset-6 col-lg-3">
                    <button type="button" class="btn btn-primary btn-block">Grabar</button>
                </div>
                <div class="col-lg-3">
                    <button type="button" class="btn btn-primary btn-block">Cancelar</button>
                </div>
            </div>-->
        </section>
        <footer>
            <jsp:include page="../util/Footer.jsp"/>
        </footer>    
    </body>
</html>
