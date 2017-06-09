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
        <script src="<%=request.getContextPath()%>/js/generales/ListaGenerica.js"></script>
        <script src="<%=request.getContextPath()%>/js/modulosPagos/TipoPago.js"></script>
        <script src="<%=request.getContextPath()%>/js/modulosPagos/Pagos.js"></script>
        <script src="<%=request.getContextPath()%>/js/modulosPagos/revertirPago.js"></script>
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
                        <div class="row form-group"> 
                            <div class="col-lg-2">
                                <label>Codigo Pago:</label>
                            </div>
                            <div class="col-lg-3">
                                 <input type="text" class="form-control" id="txtCodigoPago">
                            </div>
                            <div class="col-lg-2">
                                <button type="button" class="btn btn-primary btn-block" id="btnBuscar">Buscar</button>
                            </div>
                        </div>
                        <div class="row form-group"> 
                            <div class="col-lg-2">
                                <label>Cabecera:</label>
                            </div>
                        </div>
                        <div class="row form-group table-responsive" id="divTablaCabecera"> 
                               
                        </div> 
                        <div class="row form-group"> 
                            <div class="col-lg-2">
                                <label>Detalle:</label>
                            </div>
                        </div>
                        <div class="row form-group table-responsive" id="divTablaDetalle"> 
                            
                        </div> 
                    </div>
                    <div class="panel-footer">
                        <div class="row">
                            <div class="col-lg-offset-6 col-lg-3">
                                <button type="button" class="btn btn-primary btn-block" id="btnRevertir">Revertir</button>
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
