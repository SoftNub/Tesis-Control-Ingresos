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
        <script src="<%=request.getContextPath()%>/js/modulosPagos/Precio.js"></script>
        <script src="<%=request.getContextPath()%>/js/modulosPagos/TipoPago.js"></script>
        <script src="<%=request.getContextPath()%>/js/modulosPagos/AdmGestionPago.js"></script>
    </head>
    <body>
        <header>
            <nav>
                <jsp:include page="../util/Navbar.jsp"/>
                <h3 class="page-header"><c:out value="${TituloModulo}" /></h3>
            </nav>
        </header>
        <section class="container">
            <div class="btn-group" id="panelOpciones">
                <div class="btn-group" role="group">
                    <button type="button" class="btn btn-primary" id="Opcgrabar" 
                            title="<c:out value="${MsjGrabar}" />" accesskey="g"
                            value = "<c:out value="${MsjGrabar}" />">
                        <span class="glyphicon glyphicon-plus"></span>
                    </button>
                    <br>     
                </div>
            </div>
            <br>        
            <div id = "panelError">
                
            </div>
            <div id = "panelConsultar">
                
            </div>
            <div id = "panelGrabar">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title text-center titleTipoPago">Tipo Pago</h3>
                    </div>
                    <div class="panel-body">
                        <div id="divTipoPago">
                            <div class="row form-group"> 
                                <div class="col-lg-offset-1 col-lg-2">
                                    <label for="txtConcepto">Concepto (*):</label>
                                </div>
                                <div class="col-lg-7">
                                     <input type="text" class="form-control" id="txtConcepto" 
                                            placeholder = "concepto" tabindex="1" maxlength="50">
                                </div>
                            </div>
                            <div class="row form-group"> 
                                <div class="col-lg-offset-1 col-lg-2">
                                    <label>Para :</label>
                                </div>
                                <div class="col-lg-2">
                                     <input type="checkbox" id="chkConceptoCol" tabindex="2"/>Colegiado
                                </div>
                                <div class="col-lg-2">
                                     <input type="checkbox" id="chkConceptoNoCol" tabindex="3"/>Persona
                                </div>
                            </div>
                            <div class="row form-group"> 
                                <div class="col-lg-offset-1 col-lg-2">
                                     <input type="checkbox" id="chkConceptoInh" tabindex="4"/>Es inhabilitador
                                </div>
                            </div>
                            <div class="row form-group inhabilitador"> 
                                <div class="col-lg-offset-2 col-lg-3">
                                    <label>Numero Pagos Activos :</label>
                                </div>
                                <div class="col-lg-1">
                                     <input type="text" class="form-control" id="txtNumPagosActivos" 
                                             tabindex="5" maxlength="3">
                                </div>
                                <div class="col-lg-2">
                                    <label>Tipo Generacion :</label>
                                </div>
                                <div class="col-lg-2">
                                     <input type="checkbox" id="chkConceptoManual" tabindex="6"/> Manual
                                </div>
                                <div class="col-lg-2">
                                     <input type="checkbox" id="chkConceptoMensual" tabindex="7"/> Mensual
                                </div>
                            </div>
                            <div class="row form-group"> 
                                <div class="col-lg-offset-1 col-lg-1">
                                    <label>estado :</label>
                                </div>
                                <div class="col-lg-2">
                                      <select class="form-control" id="cboEstado" tabindex="8">
                                     </select>
                                </div>
                                <div class="col-lg-2">
                                    <label>Estados Colegiados :</label>
                                </div>
                                <div class="col-lg-1">
                                     <input type="checkbox" id="chkEstColHab" tabindex="9"/> Hab.
                                </div>
                                <div class="col-lg-1">
                                     <input type="checkbox" id="chkEstColInh" tabindex="10"/> Inh.
                                </div>
                                <div class="col-lg-1">
                                     <input type="checkbox" id="chkEstColExo" tabindex="11"/> Exo.
                                </div>
                                <div class="col-lg-1">
                                     <input type="checkbox" id="chkEstColEgre" tabindex="12"/> Egr.
                                </div>
                            </div>
                            <div class="row form-group divPrecio"> 
                                <div class="col-lg-offset-1 col-lg-1">
                                    <label>Precio :</label>
                                </div>
                                <div class="col-lg-1">
                                     <input type="text" class="form-control" id="txtPrecio" 
                                            placeholder = "Precio" tabindex="13" maxlength="10">
                                </div>
                                <div class="col-lg-3">
                                    <label for="nombres">Fecha Inicio Vigencia DD/MM/YYYY(*):</label>
                                </div>
                                <div class="col-lg-2">
    <!--                                <div class="input-group date" data-provide="datepicker">
                                        <input type="text" class="form-control" id="txtFechaNacimiento">
                                        <div class="input-group-addon">
                                            <span class="glyphicon glyphicon-th"></span>
                                        </div>
                                    </div>-->

                                    <input type="text" class="form-control" id="txtFechaNacimiento" 
                                           data-date-format="dd/mm/yyyy" readonly="true" tabindex="14" maxlength="10">
                                </div>
                            </div>    
                        </div>
                        <div id="divPrecioTipoPago">
                            <div class="row form-group precioVigente"> 
                                <div class="col-lg-offset-1 col-lg-2">
                                    <label>Precio Vigente:</label>
                                </div>
                                <div class="col-lg-2">
                                     <input type="text" class="form-control" id="txtPrecioVigente" 
                                            readonly="true" placeholder = "Precio" maxlength="10">
                                </div>
                                <div class="col-lg-offset-1 col-lg-2">
                                    <label for="nombres">Fecha Inicio Vigencia:</label>
                                </div>
                                <div class="col-lg-2">
                                    <input type="text" class="form-control" id="txtFechaInicioVigencia" 
                                        readonly="true" maxlength="10">
                                </div>
                            </div>
                            <div class="row form-group precioProximo"> 
                                <div class="col-lg-offset-1 col-lg-2">
                                    <label>Precio Prox. Vigente:</label>
                                </div>
                                <div class="col-lg-2">
                                     <input type="text" class="form-control" id="txtPrecioProxVigente" 
                                            readonly="true" placeholder = "Precio" maxlength="10">
                                </div>
                                <div class="col-lg-offset-1 col-lg-2">
                                    <label for="nombres">Fecha Inicio Prox. Vigente:</label>
                                </div>
                                <div class="col-lg-2">
                                    <input type="text" class="form-control" id="txtFechaInicioProxVigencia" 
                                       readonly="true" maxlength="10">
                                </div>
                            </div>
                            <div class="row form-group"> 
                                <div class="col-lg-offset-1 col-lg-2">
                                    <label>Precio Nuevo:</label>
                                </div>
                                <div class="col-lg-2">
                                     <input type="text" class="form-control" id="txtPrecioNuevo" 
                                            placeholder = "Nuevo Precio" maxlength="10">
                                </div>
                                <div class="col-lg-offset-1 col-lg-2">
                                    <label for="nombres">Fecha Inicio Vigencia Nuevo:</label>
                                </div>
                                <div class="col-lg-2">
                                    <input type="text" class="form-control" id="txtFechaInicioVigenciaNuevo" 
                                           data-date-format="dd/mm/yyyy" readonly="true" maxlength="10">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel-footer">
                        <div class="row">
                            <div class="col-lg-offset-6 col-lg-3">
                                <button type="button" class="btn btn-primary btn-block" id="btnAceptar">Grabar</button>
                            </div>
                            <div class="col-lg-3">
                                <button type="button" class="btn btn-primary btn-block" onclick="regresarPrincipal()">Cancelar</button>
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
