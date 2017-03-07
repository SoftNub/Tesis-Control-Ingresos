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
        <script src="<%=request.getContextPath()%>/js/modulosPagos/TipoPago.js"></script>
        <script src="<%=request.getContextPath()%>/js/modulosPagos/LogTipoPago.js"></script>
        <script src="<%=request.getContextPath()%>/js/modulosPagos/genCuotaManuales.js"></script>
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
                        <h3 class="panel-title text-center titleTipoPago"><c:out value="${TituloModulo}" /></h3>
                    </div>
                    <div class="panel-body">
                        <div>
                            <div class="row form-group"> 
                                <div class="col-lg-offset-4 col-lg-1">
                                    <label for="nombres">Fecha:</label>
                                </div>
                                <div class="col-lg-2">
                                    <input type="text" class="form-control" id="txtEjecucion" 
                                           data-date-format="dd/mm/yyyy" readonly="true" maxlength="10">
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-lg-offset-5 col-lg-2">
                                    <button type="button" class="btn btn-primary btn-block" id="btnEjecutar">Ejecutar</button>
                                </div>
                            </div>
                            <hr>
                            <div class="row form-group">
                                <div class="col-lg-1">
                                    <label for="txtFechaInicioLog">F. Inicio:</label>
                                </div>
                                <div class="col-lg-2">
                                    <input type="text" class="form-control" id="txtFechaInicioLog" 
                                           data-date-format="dd/mm/yyyy" readonly="true" maxlength="10">
                                </div>
                                <div class="col-lg-1">
                                    <label for="txtFechaFinLog">F. Fin:</label>
                                </div>
                                <div class="col-lg-2">
                                    <input type="text" class="form-control" id="txtFechaFinLog" 
                                           data-date-format="dd/mm/yyyy" readonly="true" maxlength="10">
                                </div>
                                <div class="col-lg-2">
                                    <button type="button" class="btn btn-primary btn-block" id="btnVerLog">Ver Log</button>
                                </div>
                            </div>
                            <div class="row form-group divLog"> 
                                
                            </div> 
                        </div>
                    </div>
                    <div class="panel-footer">
                        
                    </div>
                </div>
            </div>            
        </section>
        <footer>
            <jsp:include page="../util/Footer.jsp"/>
        </footer>    
    </body>
</html>
