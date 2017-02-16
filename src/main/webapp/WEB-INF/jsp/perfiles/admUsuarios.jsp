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
                        <label>Ingrese Descripción:</label>
                    </div>
                    <div class="col-lg-3">
                        <input type="text" class="form-control" id="txtDescripcionPerfil" 
                               placeholder = "perfil" tabindex="1" maxlength="100">
                    </div>
                    <div class="col-lg-1">
                        <button type="button" class="btn btn-primary btn-block">
                            Crear <span class="glyphicon glyphicon-plus-sign"></span>
                        </button>
                    </div>
                </div>
            </div>
            <br/>
            <div class="row form-group divPerfiles">
                <div class="col-lg-offset-2 col-lg-8">
                    <table id="tablaPerfiles" class="table table-bordered table-hover">
                        <thead>
                            <th class="text-center">Cod. Perfil</th>
                            <th class="text-center">Descripcion</th>
                            <th class="text-center">Estado</th>
                            <th class="text-center">Editar</th>
                            <th class="text-center">Menu</th>
                        </thead>
                        <tbody id="bodytablaPerfiles">
                            <tr>
                                <td class="text-center">1</td>
                                <td class="text-center">SECRETARIA</td>
                                <td class="text-center"><span class="label label-success">Activo</span></td>
                                <td class="text-center">
                                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
                                        <span class="glyphicon glyphicon-edit"></span>
                                    </button>
                                </td>
                                <td class="text-center">
                                    <button type="button" class="btn btn-primary" onclick="mostrarMenuPerfil(1)">
                                        <span class="glyphicon glyphicon-list-alt"></span>
                                    </button>
                                </td>
                            </tr>
                            
                        </tbody>
                    </table>
                </div>               
            </div>
            <br/>
            <div class="panel panel-primary divDetalle">
                <div class="panel-heading text-center"><strong>MENUS DE PERFIL: SECRETARIA</strong></div>
                <div class="panel-body">
                    <div class="row form-group">
                        <div class="col-lg-offset-2 col-lg-8">
                            <table id="tablaPerfilesMenu" class="table table-bordered table-hover">
                                <thead>
                                    <th class="text-center">Cod. Menu</th>
                                    <th class="text-center">Descripcion</th>
                                    <th class="text-center">Rol</th>
                                    <th class="text-center">Seleccionar</th>
                                </thead>
                                <tbody id="bodytablaPerfilesMenu">
                                    <tr>
                                        <td class="text-center">1</td>
                                        <td class="text-center">Perfiles</td>
                                        <td class="text-center">ROLE-GESTION-PERFILES</td>
                                        <td class="text-center">
                                        <input type="checkbox" id="1"/>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="panel-footer">
                    <div class="row form-group">
                        <div class="col-lg-offset-4 col-lg-2">
                            <button type="button" class="btn btn-primary btn-block">
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
                            <div class="row form-group"> 
                                <div class="col-lg-2">
                                    <label for="apellidos">Descripcion:</label>
                                </div>
                                <div class="col-lg-8">
                                    <input type="text" class="form-control" id="txtDescripcion" 
                                           placeholder = "perfil" tabindex="5" maxlength="100">
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
                                    <button type="button" class="btn btn-primary btn-block">
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
