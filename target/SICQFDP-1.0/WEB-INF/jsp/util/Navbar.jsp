<%-- 
    Document   : Navbar
    Created on : 21-may-2016, 14:50:25
    Author     : wilson
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="navbar navbar-default" role="navigation">
    <div class="navbar-header">
        <a class="navbar-brand" href="/SICQFDP/Index.cqfdp">SISCQFDP</a>
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#menuPrincipal">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
    </div>
    <div class="navbar-collapse collapse" id="menuPrincipal">
        <ul class="nav navbar-nav">
<!--            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown">Parametria General<span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                    <li><a href="/SICQFDP/parametria/Departamento.cqfdp">Departamentos</a></li>
                    <li><a href="/SICQFDP/parametria/Provincia.cqfdp">Provincias</a></li>
                    <li><a href="/SICQFDP/parametria/Distritos.cqfdp">Distritos</a></li>
                    <li><a href="/SICQFDP/parametria/EstadoCivil.cqfdp">Estados Civiles</a></li>
                    <li><a href="/SICQFDP/parametria/CondicionCasa.cqfdp">Condicion Casa</a></li>
                    <li><a href="Operadora.cqfdp">Empresas Telefonia</a></li>
                    <li><a href="TipoEquipo.cqfdp">Tipo Equipo</a></li>
                </ul>
            </li>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown">Colegiados<span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                    <li><a href="/SICQFDP/colegiados/PreInscripcion.cqfdp">Pre - Inscripcion</a></li>
                    <li><a href="/SICQFDP/colegiados/InscripcionIngresoColegiatura.cqfdp">Inscripción/Ingreso Colegiatura</a></li>
                    <li><a href="/SICQFDP/colegiados/ActualizaDatosColegiatura.cqfdp">Actualización Datos Colegiado</a></li>
                    <li><a href="/SICQFDP/colegiados/EgresoColegiatura.cqfdp">Egreso Colegiatura</a></li>
                    <li><a href="/SICQFDP/colegiados/SolicitudEgresoColegiatura.cqfdp">Solicitud Egreso Colegiatura</a></li>
                </ul>
            </li>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown">Usuarios<span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                    <li><a href="/SICQFDP/usuarios/perfiles.cqfdp">Gestion de Perfiles</a></li>
                    <li><a href="/SICQFDP/usuarios/menusIndividuales.cqfdp">Gestion de Menus Individuales</a></li>
                    <li><a href="/SICQFDP/usuarios/gestionUsuarios.cqfdp">Gestion de Usuarios</a></li>
                    <li><a href="InscripcionIngresoColegiatura.cqfdp">Inscripción/Ingreso Colegiatura</a></li>
                    <li><a href="ActualizaDatosColegiatura.cqfdp">Actualización Datos Colegiado</a></li>
                    <li><a href="EgresoColegiatura.cqfdp">Egreso Colegiatura</a></li>
                    <li><a href="SolicitudEgresoColegiatura.cqfdp">Solicitud Egreso Colegiatura</a></li>
                </ul>
            </li>-->
            <c:forEach items="${menusUsuario}" var="item">
                ${item.descripMenu}
             </c:forEach>
            <li>
                <a href="/SICQFDP/j_spring_security_logout">Salir</a>
            </li>
        </ul>
    </div>
</div>