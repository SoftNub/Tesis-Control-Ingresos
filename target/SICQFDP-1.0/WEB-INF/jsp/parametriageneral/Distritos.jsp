<%-- 
    Document   : ParametriaGeneral
    Created on : 21-may-2016, 14:22:12
    Author     : wilson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <jsp:include page="../util/Header.jsp"/>
        <script src="<%=request.getContextPath()%>/js/parametriageneral/distrito.js"></script>
    </head>
    <body>
        <header>
            <nav>
                <jsp:include page="../util/Navbar.jsp"/>
                <h1 class="page-header">MANTENIMIENTO DE DISTRITOS</h1>
            </nav>
        </header>
        <section class="container">
            <div id = "panelConsultar">
                
            </div>
            <div id = "panelGrabarEditar">
                
            </div>
            <div id = "panelEliminar">
                
            </div>
        </section>
        <footer>
            <jsp:include page="../util/Footer.jsp"/>
        </footer>
    </body>
</html>
