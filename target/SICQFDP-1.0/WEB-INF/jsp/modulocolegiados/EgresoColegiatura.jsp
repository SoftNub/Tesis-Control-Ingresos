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
        <script src="<%=request.getContextPath()%>/js/modulocolegiado/AdmEgresoColegiatura.js"></script>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/estilosParametria.css">
<!--        <script>
            function iniciar(){
                $("#egresosColegiatura").DataTable();
            }
        </script>-->
    </head>
    <body>
        <header>
            <nav>
                <jsp:include page="../util/Navbar.jsp"/>
                <h3 class="page-header"><c:out value="${TituloModulo}" /></h3>
            </nav>
        </header>
        <section class="container">
            <br/>
            <div class="btn-group btn-group-justified" id="panelOpciones">
                <div class="btn-group" role="group">
                <button type="button" class="btn btn-primary" id="Opcgrabar" 
                        title="<c:out value="${MsjGrabar}" />" accesskey="g">
                    <span class="glyphicon glyphicon-floppy-disk"></span>
                </button>
                </div>
                <div class="btn-group" role="group">
                <button type="button" class="btn btn-primary" id="OpcCancelar" 
                        title="<c:out value="${MsjCancelar}" />" accesskey="c">
                    <span class="glyphicon glyphicon-apple"></span>
                </button>
                </div>    
            </div>
            <div id = "panelError">
                
            </div>
            <br/>        
            <div id = "panelConsultar">
                <table id="egresosColegiatura" class="table table-hover table-condensed" style = "text-align: center">
                    <thead>
                       <tr>
                            <th>Numero Colegiatura</th>
                            <th>Numero DNI</th>
                            <th>Nombre</th>
                            <th>Aprobar Egreso</th>
                        </tr> 
                    </thead>
                    <tbody id = "bodyNumColegiatura">
<!--                        <tr>
                            <td>12345</td>
                            <td>23434544</td>
                            <td>WILSON HERNANDE NEIRA MIJA</td>
                            <td>
                                <input type="checkbox" />
                            </td>
                        </tr>
                        <tr>
                            <td>12345</td>
                            <td>23434544</td>
                            <td>WILSON HERNANDE NEIRA MIJA</td>
                            <td>
                                <input type="checkbox" />
                            </td>
                        </tr>
                        <tr>
                            <td>12345</td>
                            <td>23434544</td>
                            <td>WILSON HERNANDE NEIRA MIJA</td>
                            <td>
                                <input type="checkbox" />
                            </td>
                        </tr>
                        <tr>
                            <td>12345</td>
                            <td>23434544</td>
                            <td>WILSON HERNANDE NEIRA MIJA</td>
                            <td>
                                <input type="checkbox" />
                            </td>
                        </tr>-->
                    </tbody>
                </table>
<!--                <br/>
                <div class="row">
                    <div class="col-lg-offset-9 col-lg-3">
                        <button type="button" class="btn btn-primary btn-block">Grabar</button>
                    </div>
                </div>-->
            </div>
        </section>
        <footer>
            <jsp:include page="../util/Footer.jsp"/>
        </footer>    
    </body>
</html>
