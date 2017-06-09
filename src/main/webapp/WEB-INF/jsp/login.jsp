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
        <script src="<%=request.getContextPath()%>/libs/jquery-1.12.4/jquery-1.12.4.min.js"></script>
        <script src="<%=request.getContextPath()%>/libs/bootstrap-3.3.6/js/bootstrap.min.js"></script>
        <title>Login</title>
        <style>
            .form-signin.col-lg-offset-3.col-lg-6 {
                margin-top: 10%;
            }
            
            .form-signin-heading {
                text-align: center;
            }
            
            img {
                margin-left: 35%;
                height: 150px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <form  class="form-signin col-lg-offset-3 col-lg-6 col-md-offset-3 col-md-6" action="<c:url value='j_spring_security_check' />" method='POST'>
                <c:if test="${not empty error}">
                        <div class="alert alert-danger">
                            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                            <strong>${error}</strong>
                        </div>
                </c:if>
                <c:if test="${not empty msg}">
                        <div class="alert alert-info">
                            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                            <strong>${msg}</strong>
                        </div>
                </c:if>
                <!--<h2 class="form-signin-heading">COLEGIO QUIMICO FARMACEUTICO DEPARTAMENTAL DE PIURA</h2>-->
                <h2 class="form-signin-heading">COLEGIO DE LICENCIADOS EN ADMINISTRACION DE PIURA</h2>
<!--                <img src="<%=request.getContextPath()%>/img/logoCQFDP.png" alt="CQFDP">-->
                <div class="form-group">
                    <label for="txtNumColegiatura" class="sr-only">NUM. COLEGIATURA</label>
                    <input type="text" id="txtNumColegiatura" name="txtNumColegiatura" class="form-control" placeholder="Num colegiatura" required="" autofocus="">
                </div>
                <div class="form-group">
                    <label for="txtContrasenna" class="sr-only">Contraseña</label>
                    <input type="password" id="txtContrasenna" name="txtContrasenna" class="form-control" placeholder="Contraseña" required="">
                </div>
                <!--                
<div class="checkbox">
                    <label>
                        <input type="checkbox" value="remember-me"> Remember me
                    </label>
                </div>-->
                <button class="btn btn-lg btn-primary btn-block" type="submit">Ingresar</button>
            </form>  
        </div> <!-- /container -->
    </body>
</html>
