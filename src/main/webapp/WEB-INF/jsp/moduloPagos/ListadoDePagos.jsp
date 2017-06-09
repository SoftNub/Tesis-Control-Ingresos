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
        <script src="<%=request.getContextPath()%>/js/generales/ListaGenerica.js"></script>
        <script src="<%=request.getContextPath()%>/js/modulocolegiado/Colegiado.js"></script>
        <script src="<%=request.getContextPath()%>/js/modulosPagos/TipoPago.js"></script>
        <script src="<%=request.getContextPath()%>/js/modulosPagos/Pagos.js"></script>
        <script src="<%=request.getContextPath()%>/js/modulosPagos/listadoPagos.js"></script>
        <%
            Date fecha = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
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
                        <div class="row form-group">
                            <div class="col-lg-4">
                                <div class="row form-group">
                                    <div class="col-lg-4">
                                         <input type="checkbox" name="chkFechas" id="chkFechas"/><label>F. Inicio:</label>
                                    </div>
                                    <div class="col-lg-8">
                                         <input type="text" class="form-control" id="txtFechaInicio" 
                                                value = "<%= sdf.format(fecha) %>" data-date-format="dd/mm/yyyy" readonly="true" maxlength="10">
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-lg-4">
                                        <label>F. Fin:</label>
                                    </div>
                                    <div class="col-lg-8">
                                         <input type="text" class="form-control" id="txtFechaFin" 
                                            value = "<%= sdf.format(fecha) %>" data-date-format="dd/mm/yyyy" readonly="true" maxlength="10">
                                    </div>
                                </div>    
                            </div>
                            <div class="col-lg-3">
                                <div class="row form-group">
                                    <div class="col-lg-4">
                                        <input type="checkbox" name="chkDNI" id="chkDNI"/><label>DNI:</label>
                                    </div>
                                    <div class="col-lg-6">
                                         <input type="text" class="form-control" id="txtDNI" disabled="disabled" placeholder = "DNI" maxlength="8">
                                    </div>
                                </div>    
                                <div class="row form-group">
                                    <div class="col-lg-4">
                                        <input type="checkbox" name="chkNumColeg" id="chkNumColeg"/><label>N. Col:</label>
                                    </div>
                                    <div class="col-lg-7">
                                         <input type="text" class="form-control" id="txtNumColegiatura" disabled="disabled" placeholder = "Num. Coleg" maxlength="10">
                                    </div>
                                </div>    
                            </div> 
                            <div class="col-lg-5">
                                <div class="row form-group">
                                    <div class="col-lg-4">
                                         <input type="checkbox" name="chkTipo" id="chkTipo"/><label>Tipo Pago:</label>
                                    </div>
                                    <div class="col-lg-8">
                                         <select class="form-control" id="cboTipo" disabled="disabled">
                                        </select>
                                    </div>
                                 </div>
                                <div class="row form-group">
                                    <div class="col-lg-5">
                                        <button type="button" class="btn btn-primary btn-block" id="btnBuscar">Buscar</button>
                                    </div>
                                </div>
                            </div>    
                        </div>
                        <div class="row form-group table-responsive" id="divTablaPagos"> 
                           
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
