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
        <script src="<%=request.getContextPath()%>/js/modulosPagos/TipoPago.js"></script>
        <script src="<%=request.getContextPath()%>/js/modulosPagos/Deuda.js"></script>
        <script src="<%=request.getContextPath()%>/js/modulosPagos/Pagos.js"></script>
        <script src="<%=request.getContextPath()%>/js/modulosPagos/RegistroIngresos.js"></script>
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
                        <h3 class="panel-title text-center">Registro de Ingresos</h3>
                    </div>
                    <div class="panel-body">
                        <div>
                            <div class="row form-group tipo"> 
                                <div class="col-lg-offset-1 col-lg-2">
                                    <label>Tipo :</label>
                                </div>
                                <div class="col-lg-2">
                                     <input type="radio" name="tipoConcepto" id="chkConceptoCol" checked="checked"/>Colegiado
                                </div>
                                <div class="col-lg-2">
                                     <input type="radio" name="tipoConcepto" id="chkConceptoNoCol"/>Persona
                                </div>
                            </div>
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
                                <div class="col-lg-1">
                                    <button type="button" class="btn btn-primary btn-block" id="btnAnnadir">
                                        <span class="glyphicon glyphicon-plus"></span>
                                    </button>
                                </div>
                            </div>
                            <div class="row form-group periodosDeuda"> 
                                <div class="col-lg-offset-1 col-lg-10 alert alert-info periodosDeuda2">
                                     <label id="lblPeridosDeuda" ></label>
                                </div>
                                <!--class="label label-info"-->
                            </div>
                            <div class="row form-group table-responsive"> 
                                <table class="table table-bordered table-striped">
                                    <thead>
                                        <tr>
                                            <th>Cantidad</th><th>Concepto</th><th>Precio Unitario</th><th>Importe</th><th>Quitar</th>
                                        </tr>
                                    </thead>
                                    <tbody id="bodyConceptosDetalle">
                                      
                                    </tbody>
                                    <tfoot>
                                        <tr>
                                            <th colspan="3">TOTAL</th><th id="totalImporte">0</th><th></th>
                                        </tr>
                                    </tfoot>
                                </table>
                            </div> 
                        </div>
                    </div>
                    <div class="panel-footer">
                        <div class="row">
                            <div class="col-lg-offset-6 col-lg-3">
                                <button type="button" class="btn btn-primary btn-block" id="btnPagar">Pagar</button>
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
