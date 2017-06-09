<%-- 
    Document   : PreInscripcion
    Created on : 24-ago-2016, 20:56:21
    Author     : wilson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="../util/Header.jsp"/>
        <script src="<%=request.getContextPath()%>/js/modulocolegiado/Colegiado.js"></script>
        <script src="<%=request.getContextPath()%>/js/modulosPagos/TipoPago.js"></script>
        <script src="<%=request.getContextPath()%>/js/modulosPagos/Deuda.js"></script>
        <script src="<%=request.getContextPath()%>/js/modulosPagos/registroCuotasManual.js"></script>
        <%
            Date fecha = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("MMyyyy");
        %>
    </head>
    <body>
        <header>
            <nav>
                <jsp:include page="../util/Navbar.jsp"/>
                <h3 class="page-header"><c:out value="${TituloModulo}" /></h3>
            </nav>
        </header>
        <section class="container">
            <div id = "panelError">
                
            </div>
            <div id = "panelGrabar">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title text-center"><c:out value="${TituloModulo}" /></h3>
                    </div>
                    <div class="panel-body">
                        <div>
                            <div id="comprobarTipo">
                                <div class="row form-group comprobarIdentificacion"> 
                                    <div class="col-lg-offset-1 col-lg-2">
                                        <label for="txtNumColegiaturaDNI" id="lblDocComprobacion">Num. Colegiatura o DNI:</label>
                                    </div>
                                    <div class="col-lg-2">
                                         <input type="text" class="form-control" id="txtNumColegiaturaDNI" 
                                                placeholder = "num Coleg. o DNI" maxlength="10">
                                    </div>
                                    <div class="col-lg-2">
                                        <button type="button" class="btn btn-primary btn-block" id="btnComprobar">Comprobar</button>
                                    </div>
                                </div>
                                <div class="row form-group nombre"> 
                                    <div class="col-lg-offset-1 col-lg-2">
                                        <label>Nombres y Apellidos:</label>
                                    </div>
                                    <div class="col-lg-7">
                                         <label id="lblNombresApellidos"></label>
                                    </div>
                                </div>
                                <div class="row form-group estado"> 
                                    <div class="col-lg-offset-1 col-lg-1">
                                        <label>Estado:</label>
                                    </div>
                                    <div class="col-lg-2">
                                         <label id="lblEstado"></label>
                                    </div>
                                </div>
                            </div>
                            <div class="row form-group pagos"> 
                                <div class="col-lg-offset-1 col-lg-1">
                                    <label>Pagos :</label>
                                </div>
                                <div class="col-lg-5">
                                    <select class="form-control" id="cboPagos">
                                    </select>
                                </div>
                            </div>
                            <div class="row form-group periodosDeuda"> 
                                <div class="col-lg-offset-1 col-lg-1">
                                    <label>Periodo :</label>
                                </div>
                                <div class="col-lg-3">
                                     <input type="text" class="form-control" id="txtPeriodo" 
                                        value = "<%= sdf.format(fecha) %>" data-date-format="mmyyyy" readonly="true" maxlength="6">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel-footer">
                        <div class="row">
                            <div class="col-lg-offset-6 col-lg-3">
                                <button type="button" class="btn btn-primary btn-block" id="btnRegistrar">Registrar</button>
                            </div>
                            <div class="col-lg-3">
                                <button type="button" class="btn btn-primary btn-block" id="btnLimpiar">Limpiar</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <footer>
            <jsp:include page="../util/Footer.jsp"/>
        </footer>    
    </body>
</html>
