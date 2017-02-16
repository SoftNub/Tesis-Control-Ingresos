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
        <script src="<%=request.getContextPath()%>/js/usuarios/Usuarios.js"></script>
        <script src="<%=request.getContextPath()%>/js/usuarios/Perfiles.js"></script>
        <script src="<%=request.getContextPath()%>/js/usuarios/Menus.js"></script>
        <script src="<%=request.getContextPath()%>/js/usuarios/gestionPerfiles.js"></script>
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
            <div class="row form-group divPerfiles"> 
                <div class="row form-group"> 
                    <div class="col-lg-2">
                        <label for="txtDescripcionPerfil">Ingrese Descripción:</label>
                    </div>
                    <div class="col-lg-3">
                        <input type="text" class="form-control" id="txtDescripcionPerfil" 
                            name="txtDescripcionPerfil"   placeholder = "perfil" tabindex="1" maxlength="100">
                    </div>
                    <div class="col-lg-1">
                        <button type="button" class="btn btn-primary btn-block" id="btnCrearPerfil">
                            Crear <span class="glyphicon glyphicon-plus-sign"></span>
                        </button>
                    </div>
                </div>
            </div>
            <br/>
            <div class="row form-group divPerfiles">
                <div class="col-lg-offset-2 col-lg-8" id="divTabla">
                    
                </div>               
            </div>
            <br/>
            <div class="panel panel-primary divDetalle">
                <div class="panel-heading text-center"><strong id="tituloPanelMenu">MENUS DE PERFIL: SECRETARIA</strong></div>
                <div class="panel-body">
                    <div class="row form-group">
                        <div class="col-lg-offset-2 col-lg-8" id="divMenu">
                            
                        </div>
                    </div>
                </div>
                <div class="panel-footer">
                    <div class="row form-group">
                        <div class="col-lg-offset-4 col-lg-2">
                            <button type="button" class="btn btn-primary btn-block" id="btnGenerarMenu">
                                Generar Menu <span class="glyphicon glyphicon-cog"></span>
                            </button>
                        </div>
                        <div class="col-lg-offset-1 col-lg-2">
                            <button type="button" class="btn btn-primary btn-block" id="btnAtras">
                                <span class="glyphicon glyphicon-arrow-left"></span> Atras 
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div id="myModal" class="modal fade" role="dialog">
                <div class="modal-dialog">
                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">EDITAR PERFIL</h4>
                        </div>
                        <div class="modal-body">
                            <div id = "panelErrorInt">
                
                            </div>
                            <br/>
                            <div class="row form-group"> 
                                <div class="col-lg-2">
                                    <label for="txtDescripcion">Descripcion:</label>
                                </div>
                                <div class="col-lg-8">
                                    <input type="text" class="form-control" id="txtDescripcion" 
                                        name ="txtDescripcion"   placeholder = "perfil" tabindex="5" maxlength="100">
                                </div>
                            </div>
                            <div class="row form-group"> 
                                <div class="col-lg-2">
                                    <label>Habilitado: </label>
                                </div>
                                <div class="col-lg-1">
                                    <input type="checkbox"  id="chkEstado"/>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <div class="row form-group">
                                <div class="col-lg-offset-3 col-lg-2">
                                    <button type="button" class="btn btn-primary btn-block" id="btnEditar">
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
