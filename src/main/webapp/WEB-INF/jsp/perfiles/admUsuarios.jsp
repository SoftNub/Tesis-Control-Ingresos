<%-- 
    Document   : Administracion de Perfiles
    Created on : 15 oct 2016, 07:21:21
    Author     : wilson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="../util/Header.jsp"/>
        <script src="<%=request.getContextPath()%>/js/usuarios/Perfiles.js"></script>
        <script src="<%=request.getContextPath()%>/js/usuarios/Menus.js"></script>
        <script src="<%=request.getContextPath()%>/js/usuarios/Usuarios.js"></script>
        <script src="<%=request.getContextPath()%>/js/usuarios/gestionUsuarios.js"></script>
    </head>
    <body>
        <header>
            <nav>
                <jsp:include page="../util/Navbar.jsp"/>
                <h3 class="page-header"><c:out value="${TituloParametria}" /></h3>
            </nav>
        </header>
        <section class="container">
            <div id = "panelError">
                
            </div>        
            <br/>        
            <div class="panel panel-primary">
                <div class="panel-heading text-center"><strong id="tituloPanelMenu">GESTION USUARIO</strong></div>
                <div class="panel-body">
                    <div class="row form-group">
                        <div class="col-lg-offset-3 col-lg-2">
                            <label for="cboOpcionBusqueda">BUSCAR: </label>
                        </div>
                        <div class="col-lg-3">
                            <select class="form-control" id="cboOpcionBusqueda">
                                <option value="">--SELECCIONE--</option>
                                <option value="1">PRE-INSCRITO</option>
                                <option value="2">COLEGIADO</option>
                            </select>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-lg-offset-3 col-lg-2">
                            <label for="txtDNINumColeg">INGRESAR: </label>
                        </div>
                        <div class="col-lg-3">
                            <input type="text" class="form-control" id="txtDNINumColeg" 
                                name ="txtDNINumColeg"   placeholder = "DNI o Num. Colegiatura" maxlength="10">
                        </div>
                        <div class="col-lg-1">
                            <button type="button" class="btn btn-primary btn-block" id="btnBuscar">
                                <span class="glyphicon glyphicon-search"></span>
                            </button>
                        </div>
                    </div>
                    <br/>
                    <div class="panel panel-default divDetalle">
                        <div class="panel-body">
                            <div class="row form-group"> 
                                <div class="col-lg-2">
                                    <label for="txtNombre">Nombre: </label>
                                </div>
                                <div class="col-lg-8">
                                    <label id="txtNombre" name="txtNombre">Wilson Hernande</label>
                                </div>
                            </div>
                            <div class="row form-group"> 
                                <div class="col-lg-2">
                                    <label for="txtApellidos">Apellidos: </label>
                                </div>
                                <div class="col-lg-8">
                                    <label id="txtApellidos" name="txtApellidos">Neira Mija</label>
                                </div>
                            </div>
                            <div class="row form-group"> 
                                <div class="col-lg-2">
                                    <label for="txtTipoUsuario">Tipo Usuario: </label>
                                </div>
                                <div class="col-lg-8">
                                    <label id="txtTipoUsuario" name="txtTipoUsuario">Colegiado</label>
                                </div>
                            </div>
                            <div class="row form-group"> 
                                <div class="col-lg-2">
                                    <label for="txtPerfil">Perfil:</label>
                                </div>
                                <div class="col-lg-3">
                                    <select class="form-control" id="cboPerfil">
                                        
                                    </select>
                                </div>
                            </div>
                            <div class="row form-group"> 
                                <div class="col-lg-2">
                                    <label for="txtUsuario">Usuario: </label>
                                </div>
                                <div class="col-lg-8">
                                    <label id="txtUsuario" name="txtUsuario">12345</label>
                                </div>
                            </div>
                            <div class="row form-group"> 
                                <div class="col-lg-2">
                                    <label for="txtPassword">Password: </label>
                                </div>
                                <div class="col-lg-4">
                                    <input type="password" class="form-control" id="txtPassword" 
                                           name ="txtPassword"  maxlength="100">
                                </div>
                            </div>
                            <div class="row form-group"> 
                                <div class="col-lg-2">
                                    <label>Estado: </label>
                                </div>
                                <div class="col-lg-1">
                                    <input type="checkbox"  id="chkEstado"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel-footer divDetalle">
                    <div class="row form-group">
                        <div class="col-lg-offset-4 col-lg-2">
                            <button type="button" class="btn btn-primary btn-block" id="btnAceptar">
                                Aceptar <span class="glyphicon glyphicon-ok"></span>
                            </button>
                        </div>
                        <div class="col-lg-offset-1 col-lg-2">
                            <button type="button" class="btn btn-primary btn-block" id="btnAtras">
                                Limpiar <span class="glyphicon glyphicon-remove"></span> 
                            </button>
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
