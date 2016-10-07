<%-- 
    Document   : login
    Created on : 23-sep-2016, 12:00:18
    Author     : wilson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/libs/bootstrap-3.3.6/css/bootstrap.min.css"/>
        <title>Login</title>
    </head>
    <body>
        <div class="container">
            
            <form class="form-signin"  action="<c:url value='j_spring_security_check' />" method='POST'>
                <c:if test="${not empty error}">
			<div class="alert alert-danger">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="alert alert-info">${msg}</div>
		</c:if>
                <h2 class="form-signin-heading">LOGIN</h2>
                <label for="txtNumColegiatura" class="sr-only">NUM. COLEGIATURA</label>
                <input type="text" id="txtNumColegiatura" name="txtNumColegiatura" class="form-control" placeholder="Num colegiatura" required="" autofocus="">
                <label for="txtContrasenna" class="sr-only">Contraseña</label>
                <input type="password" id="txtContrasenna" name="txtContrasenna" class="form-control" placeholder="Contraseña" required="">
<!--                <div class="checkbox">
                    <label>
                        <input type="checkbox" value="remember-me"> Remember me
                    </label>
                </div>-->
                <button class="btn btn-lg btn-primary btn-block" type="submit">Ingresar</button>
            </form>

        </div> <!-- /container -->
    </body>
</html>
