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
        <script src="<%=request.getContextPath()%>/js/moduloreportes/listadoColegiados.js"></script>
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
                            <div class="col-lg-offset-3 col-lg-1">
                                <label>Reporte:</label>
                            </div>
                            <div class="col-lg-4">
                                <select class="form-control" id="cboReporte">
                                    <option value="1">Carnetizacion - Recarnetizacion</option>
                                    <option value="2">Estados de Colegiados</option>
                                </select>
                            </div>
                        </div>
                        <div class="row form-group reporte1">
                            <div class="col-lg-offset-3 col-lg-1">
                                <label>Colegiado:</label>
                            </div>
                            <div class="col-lg-4">
                                <div class="input-group">
                                    <input type="text" id = "colegRep1" class="form-control" aria-label="..." VALUE="SELECCIONE COLEGIADO" readonly="true">
                                    <div class="input-group-btn">
                                        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">SELECCIONAR <span class="caret"></span></button>
                                        <ul class="dropdown-menu dropdown-menu-right">
                                            <li><a onClick="seleccionaColegiado(1)">NUMERO COLEGIATURA</a></li>
                                            <li><a onClick="seleccionaColegiado(2)">DNI</a></li>
                                            <li><a onClick="seleccionaColegiado(3)">SELECCIONE COLEGIADO</a></li>
                                        </ul>
                                    </div><!-- /btn-group -->
                                </div><!-- /input-group -->
                            </div>
                        </div>
                        <div class="row form-group reporte2">
                            <div class="col-lg-offset-3 col-lg-1">
                                <label>Colegiado:</label>
                            </div>
                            <div class="col-lg-4">
                                <div class="input-group">
                                    <input type="text" id="colegRep2" class="form-control" aria-label="..." value="TODOS LOS COLEGIADOS" readonly="true">
                                    <div class="input-group-btn">
                                        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">SELECCIONAR <span class="caret"></span></button>
                                        <ul class="dropdown-menu dropdown-menu-right">
                                            <li><a onClick="seleccionaColegiado(1)">NUMERO COLEGIATURA</a></li>
                                            <li><a onClick="seleccionaColegiado(2)">DNI</a></li>
                                            <li><a onClick="seleccionaColegiado(3)">TODOS</a></li>
                                        </ul>
                                    </div><!-- /btn-group -->
                                </div><!-- /input-group -->
                            </div>    
                        </div>
                        <div class="row form-group reporte2">
                            <div class="col-lg-offset-3 col-lg-1">
                                <label>Estados:</label>
                            </div>
                            <div class="col-lg-2">
                                <select class="form-control" id="cboEstado">
                                    <option value="0">Todos</option>
                                    <option value="1">Habilitado</option>
                                    <option value="2">Inhabilitado</option>
                                    <option value="3">Exonerado</option>
                                    <option value="4">Trasladado</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="panel-footer">
                        <div class="row form-group reporte1">
                            <div class=" col-lg-offset-5 col-lg-2" >
                                <button type="button" class="btn btn-primary btn-block" id="btnExportaPDF1">Exportar PDF</button>
                            </div>
                        </div>     
                        <div class="row form-group reporte2">
                            <div class=" col-lg-offset-3 col-lg-2" >
                                <button type="button" class="btn btn-primary btn-block" id="btnExportaExcel">Exportar XLS</button>
                            </div>
                            <div class="col-lg-offset-2 col-lg-2">
                                <button type="button" class="btn btn-primary btn-block" id="btnExportarPDF">Exportar PDF</button>
                            </div>
                        </div>     
                    </div>
                </div>
            </div>
            <div id="myModalColegiado" class="modal fade" role="dialog">
                <div class="modal-dialog">
                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">SELECCIONAR COLEGIADO</h4>
                        </div>
                        <div class="modal-body">
                            <div id = "panelErrorInt">
                
                            </div>
                            <br/>
                            <div class="row form-group"> 
                                <div class="col-lg-3">
                                    <label for="txtNum" id="labelNum"></label>
                                </div>
                                <div class="col-lg-3">
                                    <input type="text" class="form-control" id="txtNum" 
                                           name ="txtNum" maxlength="10">
                                </div>
                                <div class="col-lg-2">
                                    <button type="button" class="btn btn-primary btn-block" id="btnBuscarColegiado">
                                        Buscar
                                    </button>
                                </div>
                            </div>
                            <div class="row form-group" id="divTabla"> 
                                
                            </div>
                        </div>
                        <div class="modal-footer">
                            <div class="row form-group">
                                <div class="col-lg-offset-3 col-lg-2">
                                    <button type="button" class="btn btn-primary btn-block" id="btnAceptarColegiado">
                                        Aceptar
                                    </button>
                                </div>
                                <div class="col-lg-offset-1 col-lg-2">
                                    <button type="button" class="btn btn-primary btn-block" data-dismiss="modal">
                                        Cerrar
                                    </button>
                                </div>
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
