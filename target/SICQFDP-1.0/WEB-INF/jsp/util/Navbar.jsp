<%-- 
    Document   : Navbar
    Created on : 21-may-2016, 14:50:25
    Author     : wilson
--%>
<div class="navbar navbar-default" role="navigation">
    <div class="navbar-header">
        <a class="navbar-brand" href="Index.cqfdp">SISCQFDP</a>
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#menuPrincipal">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
    </div>
    <div class="navbar-collapse collapse" id="menuPrincipal">
        <ul class="nav navbar-nav">
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown">Parametria General<span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                    <li><a href="Departamento.cqfdp">Departamentos</a></li>
                    <li><a href="Provincia.cqfdp">Provincias</a></li>
                    <li><a href="Distritos.cqfdp">Distritos</a></li>
                    <li class="divider"></li>
                    <li><a href="EstadoCivil.cqfdp">Estados Civiles</a></li>
                    <li><a href="CondicionCasa.cqfdp">Condicion Casa</a></li>
                    <li class="divider"></li>
                    <li><a href="Operadora.cqfdp">Empresas Telefonia</a></li>
                    <li><a href="TipoEquipo.cqfdp">Tipo Equipo</a></li>
                    <li class="divider"></li>
                    <li><a href="#">Cursos Capacitacion</a></li>
                    <li><a href="#">Especialidades</a></li>
                    <li><a href="#">Maestria</a></li>
                    <li><a href="#">Doctorado</a></li>
                    <li><a href="#">Instituciones Formativas</a></li>
                </ul>
            </li>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown">Colegiados<span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                    <li><a href="PreInscripcion.cqfdp">Pre - Inscripcion</a></li>
                    <li><a href="InscripcionIngresoColegiatura.cqfdp">Inscripción/Ingreso Colegiatura</a></li>
                    <li><a href="ActualizaDatosColegiatura.cqfdp">Actualización Datos Colegiado</a></li>
                    <li><a href="EgresoColegiatura.cqfdp">Egreso Colegiatura</a></li>
                    <li><a href="SolicitudEgresoColegiatura.cqfdp">Solicitud Egreso Colegiatura</a></li>
                </ul>
            </li>
            <li>
                <a href="j_spring_security_logout">Salir</a>
            </li>
        </ul>
    </div>
</div>