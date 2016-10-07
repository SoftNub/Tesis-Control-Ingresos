<%-- 
    Document   : ParametriaGeneral
    Created on : 21-may-2016, 14:22:12
    Author     : wilson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <jsp:include page="../util/Header.jsp"/>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/estilosParametria.css">
        <script src="<%=request.getContextPath()%><c:out value="${UrlJs1}" />"></script>
        <script src="<%=request.getContextPath()%><c:out value="${UrlJs2}" />"></script>
        <script src="<%=request.getContextPath()%><c:out value="${UrlJs3}" />"></script>
    </head>
    <body>
        <header>
            <nav>
                <jsp:include page="../util/Navbar.jsp"/>
                <h3 class="page-header"><c:out value="${TituloParametria}" /></h3>
            </nav>
        </header>
        <section class="container">
            <div class="btn-group btn-group-justified" id="panelOpciones">
                <div class="btn-group" role="group">
                <button type="button" class="btn btn-primary" id="Opcgrabar" title="<c:out value="${MsjGrabar}" />">
                    <span class="glyphicon glyphicon-floppy-disk"></span>
                </button>
                </div>
                <div class="btn-group" role="group">
                <button type="button" class="btn btn-primary" id="Opceditar" title="<c:out value="${MsjEditar}" />">
                    <span class="glyphicon glyphicon-edit"></span>
                </button>
                </div>
                <div class="btn-group" role="group">
                    <button type="button" class="btn btn-primary" id = "Opceliminar" title="<c:out value="${MsjEliminar}" />">
                    <span class="glyphicon glyphicon-floppy-remove"></span>
                </button>
                </div>
                <div class="btn-group" role="group">
                    <button type="button" class="btn btn-primary" id = "OpcAdd" title="<c:out value="${MsjAsociar}" />">
                    <span class="glyphicon glyphicon-check"></span>
                </button>
                </div>    
            </div>
            <div id = "panelError">
                
            </div>
            <div id = "panelConsultar">
                
            </div>
            <div id = "panelGrabarEditar">
                
            </div>
            <div id = "panelEliminar">
                
            </div>
            <div id = "panelAdd">
                <div class="btn-group btn-group-justified" id="panelOpcionesAdd">

                </div>
                <div id = "panelErrorAdd">
                    
                </div>
                <div id = "panelFormularioAdd">
                    
                </div>        
                <div id = "panelTablaAdd">
                    
                </div>        
            </div>        
        </section>
        <footer>
            <jsp:include page="../util/Footer.jsp"/>
        </footer>
    </body>
</html>
