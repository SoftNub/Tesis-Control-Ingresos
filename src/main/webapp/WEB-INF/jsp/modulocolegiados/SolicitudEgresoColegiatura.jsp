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
        <script src="<%=request.getContextPath()%>/js/modulocolegiado/AdmSolicitudEgreso.js"></script>
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
<!--            <div class="row form-group"> 
                <div class="col-lg-offset-9 col-lg-3">
                    <div class="input-group">
                        <input type="text" class="form-control" id ="txtDni" placeholder="Ingrese DNI">
                        <div class="input-group-btn">
                            <button class="btn btn-default" type="button">
                                <span class="glyphicon glyphicon-search"></span>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row form-group"> 
                <div class="col-lg-offset-9 col-lg-3">
                    <div class="input-group">
                        <input type="text" class="form-control" id = "txtNumColegiatura" placeholder="Numero Colegiatura">
                        <div class="input-group-btn">
                            <button class="btn btn-default" type="button">
                                <span class="glyphicon glyphicon-search"></span>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div id = "panelError">
                
            </div>-->
            <div id = "panelConsultar">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">Informacion Colegiado</h3>
                    </div>
                    <div class="panel-body">
                        <div class="row form-group"> 
                            <div class="col-lg-2">
                                <label for="apellidos">Apellido Paterno:</label>
                            </div>
                            <div class="col-lg-2">
                                 <label id="lblApePaterno"></label>
                            </div>
                        </div>
                        <div class="row form-group"> 
                            <div class="col-lg-2">
                                <label for="apellidos">Apellido Materno:</label>
                            </div>
                            <div class="col-lg-2">
                                 <label id="lblApeMaterno"></label>
                            </div>
                        </div>
                        <div class="row form-group"> 
                            <div class="col-lg-2">
                                <label for="nombres">Nombres:</label>
                            </div>
                            <div class="col-lg-4">
                                <label id="lblNombres"></label>
                            </div>
                        </div>
                        <div class="row form-group"> 
                            <div class="col-lg-2">
                                <label for="nombres">Fecha Inscripcion:</label>
                            </div>
                            <div class="col-lg-2">
                                <label id="lblFechaInscripcion"></label>
                            </div>
                        </div>
                        <div class="row form-group"> 
                            <div class="col-lg-2">
                                <label for="nombres">Fecha Ultimo Ingreso:</label>
                            </div>
                            <div class="col-lg-2">
                                <label id="lblFechaIngreso"></label>
                            </div>
                        </div>
                    </div>
                    <div class="panel-footer">
<!--                        <div class="row">
                            <div class="col-lg-offset-9 col-lg-3">
                                <button type="button" class="btn btn-primary btn-block">Enviar Solicitud</button>
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
