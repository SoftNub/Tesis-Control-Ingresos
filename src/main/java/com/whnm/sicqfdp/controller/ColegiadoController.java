/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.controller;

import com.whnm.sicqfdp.beans.Colegiado;
import com.whnm.sicqfdp.beans.CustomUser;
import com.whnm.sicqfdp.beans.ListColegiado;
import com.whnm.sicqfdp.beans.Persona;
import com.whnm.sicqfdp.birtreport.BirtReportInterface;
import com.whnm.sicqfdp.interfaces.ColegiadoDao;
import com.whnm.sicqfdp.interfaces.PersonaDao;
import com.whnm.sicqfdp.utils.ValidaEntrada;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author wilson
 */
@Controller
public class ColegiadoController {
    @Autowired
    @Qualifier("personaService")
    private PersonaDao personaService;
    @Autowired
    @Qualifier("colegiadoService")
    private ColegiadoDao colegiadoService;
    @Autowired
    @Qualifier("birtReportInterfaceImpl")
    private BirtReportInterface birtReport;
    
    
    // <editor-fold defaultstate="collapsed" desc="Pre Inscripcion">
    @RequestMapping(value="/colegiados/PreInscripcion.cqfdp", method = RequestMethod.GET)
    public ModelAndView mostrarVistaPreInscripcion(){
        String urlJs1;
        String urlJs2;
        String tituloParametria;
        String msjGrabar;
        String msjEditar;
        String msjCancelar;
        ModelAndView vista = new ModelAndView();
        tituloParametria = "PRE - INSCRIPCION";
        msjGrabar = "Grabar Pre Inscripcion";
        msjEditar = "Editar Pre Inscripcion";
        msjCancelar = "Cancelar Pre Inscripcion";
        vista.addObject("TituloModulo", tituloParametria);
        vista.addObject("MsjGrabar", msjGrabar);
        vista.addObject("MsjEditar", msjEditar);
        vista.addObject("MsjCancelar", msjCancelar);
        vista.setViewName("modulocolegiados/PreInscripcion");
        return vista;
    }
    
    @RequestMapping(value="colegiados/buscarPersona.action", method = RequestMethod.POST)
    public @ResponseBody Persona buscarPersona(
          @RequestBody String objs  
    ){
        ValidaEntrada validaEntrada;
        Persona personaRep = new Persona();
        validaEntrada = new ValidaEntrada();
        ObjectMapper mapper = new ObjectMapper();
        Integer opc;
        Persona persona;
        try{
            JsonNode node = mapper.readTree(objs);
            opc = mapper.convertValue(node.get("opcion"), Integer.class);
            persona = mapper.convertValue(node.get("persona"), Persona.class);
            if(persona.getDni() != null){
                if (!persona.getDni().isEmpty()) {
                    if(persona.getDni().length() == 8){
                        if(validaEntrada.validarSoloNumerosDNI(persona.getDni())){
                            personaRep = personaService.listarPersona(opc,persona);
                        } else {
                            personaRep.setIndError(1);
                            personaRep.setMsjError("[Error: El DNI debe tener 8"
                                    +" numeros]");
                        }
                    } else {
                        personaRep.setIndError(1);
                        personaRep.setMsjError("[Error: El DNI solo debe tener 8"
                                +" caracteres]");
                    }
                } else {
                    personaRep.setIndError(1);
                    personaRep.setMsjError("[Error: El DNI no puede ser vacio]");
                }
            } else {
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El DNI no puede ser vacio]");
            }
        }catch(Exception ex){
            personaRep.setIndError(1);
            personaRep.setMsjError("Error:["+ex.getMessage()+"]");
        } 
        return personaRep;
    }
    
    @RequestMapping(value="/colegiados/mantPersonaPreInscripcion.action", method = RequestMethod.POST)
    public @ResponseBody Persona mantPersonaPreInscripcion(
          @RequestBody String objs  
    ){
        ValidaEntrada validaEntrada;
        Persona personaRep = new Persona();
        validaEntrada = new ValidaEntrada();
        ObjectMapper mapper = new ObjectMapper();
        CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer opc;
        Persona persona;
        try{
            JsonNode node = mapper.readTree(objs);
            opc = mapper.convertValue(node.get("opcion"), Integer.class);
            persona = mapper.convertValue(node.get("persona"), Persona.class);
            
            /*validacion de información*/
            if(persona.getDni() == null){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El DNI no puede ser vacio]");
                personaRep.setIdCampoError("txtDni");
                return personaRep;
            }
            if(persona.getDni().isEmpty()){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El DNI no puede ser vacio]");
                personaRep.setIdCampoError("txtDni");
                return personaRep;
            }
            if(persona.getDni().length() < 8 || persona.getDni().length() > 9){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El DNI solo debe tener 8 caracteres]'");
                personaRep.setIdCampoError("txtDni");
                return personaRep;
            }
            if(!validaEntrada.validarSoloNumerosDNI(persona.getDni())){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El DNI debe tener 8 numeros]'");
                personaRep.setIdCampoError("txtDni");
                return personaRep;
            }
            
            
            if(persona.getApePaterno() == null){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El campo Apellido Paterno no puede ser vacio]");
                personaRep.setIdCampoError("txtApellidoPat");
                return personaRep;
            }
            if(persona.getApePaterno().isEmpty()){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El campo Apellido Paterno no puede ser vacio]");
                personaRep.setIdCampoError("txtApellidoPat");
                return personaRep;
            }
            if(persona.getApePaterno().length()>50){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El campo Apellido Paterno solo acepta hasta 50 caracteres]");
                personaRep.setIdCampoError("txtApellidoPat");
                return personaRep;
            }
            if(!validaEntrada.validarSoloLetras2(persona.getApePaterno())){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El campo Apellido Paterno solo acepta letras y espacios]");
                personaRep.setIdCampoError("txtApellidoPat");
                return personaRep;
            }
            
            
            if(persona.getApeMaterno()== null){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El campo Apellido Materno no puede ser vacio]");
                personaRep.setIdCampoError("txtApellidoMat");
                return personaRep;
            }
            if(persona.getApeMaterno().isEmpty()){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El campo Apellido Materno no puede ser vacio]");
                personaRep.setIdCampoError("txtApellidoMat");
                return personaRep;
            }
            if(persona.getApeMaterno().length()>50){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El campo Apellido Materno solo acepta hasta 50 caracteres]");
                personaRep.setIdCampoError("txtApellidoMat");
                return personaRep;
            }
            if(!validaEntrada.validarSoloLetras2(persona.getApeMaterno())){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El campo Apellido Materno solo acepta letras y espacios]");
                personaRep.setIdCampoError("txtApellidoMat");
                return personaRep;
            }
            
            
            if(persona.getNombres()== null){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El campo Nombres no puede ser vacio]");
                personaRep.setIdCampoError("txtNombres");
                return personaRep;
            }
            if(persona.getNombres().isEmpty()){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El campo Nombres no puede ser vacio]");
                personaRep.setIdCampoError("txtNombres");
                return personaRep;
            }
            if(persona.getNombres().length()>50){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El campo Nombres solo acepta hasta 100 caracteres]");
                personaRep.setIdCampoError("txtNombres");
                return personaRep;
            }
            if(!validaEntrada.validarSoloLetras2(persona.getNombres())){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El campo Nombres solo acepta letras y espacios]");
                personaRep.setIdCampoError("txtNombres");
                return personaRep;
            }
            
            
            if(persona.getIdEstadoCivil()== null){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: Debe seleccionar un Estado Civil]");
                personaRep.setIdCampoError("cboEstadoCivil");
                return personaRep;
            }
            if(persona.getIdEstadoCivil() == 0){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: Debe seleccionar un Estado Civil]");
                personaRep.setIdCampoError("cboEstadoCivil");
                return personaRep;
            }
            
            if(persona.getGrupoSanguineo()!= null){
               if(persona.getGrupoSanguineo().length() > 20){
                    personaRep.setIndError(1);
                    personaRep.setMsjError("[Error: El campo Grupo Sanguineo solo acepta hasta 20 caracteres]");
                    personaRep.setIdCampoError("txtGrupoSan");
                    return personaRep;
               }
            }
            
            
            if(persona.getRh()!= null){
               if(persona.getRh().length() > 10){
                    personaRep.setIndError(1);
                    personaRep.setMsjError("[Error: El campo Rh solo acepta hasta 10 caracteres]");
                    personaRep.setIdCampoError("txtRh");
                    return personaRep;
               }
            }
            
            
            if(persona.getFechaNacimiento() == null){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El campo Fecha Nacimiento no puede ser vacio]");
                personaRep.setIdCampoError("txtFechaNacimiento");
                return personaRep;
            }
            
            if(!validaEntrada.validaFecha(persona.getFechaNacimiento())){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El campo Fecha Nacimiento tiene "
                        + "formato incorrecto o no es real. Recuerde DD/MM/YYYY]");
                personaRep.setIdCampoError("txtFechaNacimiento");
                return personaRep;
            }
            
            if(persona.getIdPaisOri() == null || persona.getIdPaisOri() == 0){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: Debe seleccionar un Pais]");
                personaRep.setIdCampoError("cboPaisOrigen");
                return personaRep;
            }
            
            if(persona.getIdDepartamentoOri()== null || persona.getIdDepartamentoOri() == 0){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: Debe seleccionar un Departamento]");
                personaRep.setIdCampoError("cboDepartamentoOrigen");
                return personaRep;
            }
            
            if(persona.getIdProvinciaOri()== null || persona.getIdProvinciaOri() == 0){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: Debe seleccionar una Provincia]");
                personaRep.setIdCampoError("cboProvinciaOrigen");
                return personaRep;
            }
            
            if(persona.getIdDistritoOri()== null || persona.getIdDistritoOri()== 0){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: Debe seleccionar un Distrito]");
                personaRep.setIdCampoError("cboDistritoOrigen");
                return personaRep;
            }
            
            if(persona.getLibretaMilitar() != null){
                if(persona.getLibretaMilitar().length() > 20){
                    personaRep.setIndError(1);
                    personaRep.setMsjError("[Error: El campo Libreta Militar solo acepta hasta 20 caracteres]");
                    personaRep.setIdCampoError("txtLibretaMilitar");
                    return personaRep;
                }
            }
            
            
            if(persona.getCarnetExt()!= null){
                if(persona.getCarnetExt().length() > 20){
                    personaRep.setIndError(1);
                    personaRep.setMsjError("[Error: El campo Carnet Extranjeria solo acepta hasta 20 caracteres]");
                    personaRep.setIdCampoError("txtCarnetExtranjeria");
                    return personaRep;
                }
            }
            
            if(persona.getFfaa_pnp()!= null){
                if(persona.getFfaa_pnp().length() > 20){
                    personaRep.setIndError(1);
                    personaRep.setMsjError("[Error: El campo Tarjeta FF.AA. y PNP solo acepta hasta 20 caracteres]");
                    personaRep.setIdCampoError("txtTarjetaIdentidad");
                    return personaRep;
                }
            }
            
            if(persona.getCarnetEssalud()!= null){
                if(persona.getCarnetEssalud().length() > 20){
                    personaRep.setIndError(1);
                    personaRep.setMsjError("[Error: El campo Carnet Essalud solo acepta hasta 20 caracteres]");
                    personaRep.setIdCampoError("txtCarnetEssalud");
                    return personaRep;
                }
            }
            
            if(persona.getDomicilio()== null){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El campo Domicilio no puede ser vacio]");
                personaRep.setIdCampoError("txtDomicilioActual");
                return personaRep;
            }
            if(persona.getDomicilio().length() > 200){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El campo Domicilio solo acepta hasta 200 caracteres]");
                personaRep.setIdCampoError("txtDomicilioActual");
                return personaRep;
            }
            if(!validaEntrada.validarCaracteresDomicilio(persona.getDomicilio())){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El campo Domicilio contiene caracteres no permitidos]");
                personaRep.setIdCampoError("txtDomicilioActual");
                return personaRep;
            }
            
            if(persona.getCodigoPostal()!= null){
                if(persona.getCodigoPostal().length() > 10){
                    personaRep.setIndError(1);
                    personaRep.setMsjError("[Error: El campo Codigo postal solo acepta hasta 10 caracteres]");
                    personaRep.setIdCampoError("txtCodigoPostal");
                    return personaRep;
                }
            }
            
            if(persona.getTelfDomicilio()== null){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El campo Telf. Domicilio no puede ser vacio]");
                personaRep.setIdCampoError("txtTelfDomicilio");
                return personaRep;
            }
            if(persona.getTelfDomicilio().length() > 15){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El campo Telf. Domicilio solo acepta hasta 15 caracteres]");
                personaRep.setIdCampoError("txtTelfDomicilio");
                return personaRep;
            }
            if(!validaEntrada.validarTextoTelefono(persona.getTelfDomicilio())){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El campo Telf. Domicilio solo puede contener numeros y #]");
                personaRep.setIdCampoError("txtTelfDomicilio");
                return personaRep;
            }
            
            if(persona.getEmailPrincipal()== null){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El campo Email 1 no puede ser vacio]");
                personaRep.setIdCampoError("txtEmail1");
                return personaRep;
            }
            if(persona.getEmailPrincipal().length() > 100){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El campo Email 1 solo acepta hasta 100 caracteres]");
                personaRep.setIdCampoError("txtEmail1");
                return personaRep;
            }
            if(!validaEntrada.validarEmail(persona.getEmailPrincipal())){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El campo Email 1. Formato de email incorrecto]");
                personaRep.setIdCampoError("txtEmail1");
                return personaRep;
            }
                        
            if(persona.getEmailSecundario()!= null){
                if(persona.getEmailSecundario().length() > 100){
                    personaRep.setIndError(1);
                    personaRep.setMsjError("[Error: El campo Email 2 solo acepta hasta 100 caracteres]");
                    personaRep.setIdCampoError("txtEmail2");
                    return personaRep;
                }
                if(!validaEntrada.validarEmail(persona.getEmailSecundario())){
                    personaRep.setIndError(1);
                    personaRep.setMsjError("[Error: El campo Email 2. Formato de email incorrecto]");
                    personaRep.setIdCampoError("txtEmail2");
                    return personaRep;
                }
            }
           
            if (opc == 1){
                personaRep = personaService.grabar(persona, user);
            } else {
                personaRep = personaService.editar(persona, user);
            }
        }catch(Exception ex){
            personaRep.setIndError(1);
            personaRep.setMsjError("Error:["+ex.getMessage()+"]");
        } 
        return personaRep;
    }
// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Inscripcion/Ingreso Colegiatura">
    @RequestMapping(value="colegiados/InscripcionIngresoColegiatura.cqfdp", method = RequestMethod.GET)
    public ModelAndView mostrarVistaInscripcionIngresoColegiatura(){
        String tituloParametria;
        String msjGrabar;
        String msjEditar;
        String msjCancelar;
        ModelAndView vista = new ModelAndView();
        msjGrabar = "Grabar Inscripcion/Ingreso";
        msjEditar = "Editar Inscripcion/Ingreso";
        msjCancelar = "Cancelar Inscripcion/Ingreso";
        tituloParametria = "INSCRIPCION/INGRESO COLEGIATURA";
        vista.addObject("TituloModulo", tituloParametria);
        vista.addObject("MsjGrabar", msjGrabar);
        vista.addObject("MsjCancelar", msjCancelar);
        vista.setViewName("modulocolegiados/InscripcionIngresoColegiatura");
        return vista;
    }
    
    @RequestMapping(value="colegiados/buscarColegiado.action", method = RequestMethod.POST)
    public @ResponseBody Colegiado buscarColegiado(
          @RequestBody String objs  
    ){
        ValidaEntrada validaEntrada;
        Colegiado colegiadoRep = new Colegiado();
        validaEntrada = new ValidaEntrada();
        ObjectMapper mapper = new ObjectMapper();
        Integer opc;
        Colegiado colegiado;
        try{
            JsonNode node = mapper.readTree(objs);
            opc = mapper.convertValue(node.get("opcion"), Integer.class);
            colegiado = mapper.convertValue(node.get("colegiado"), Colegiado.class);
            if(opc == 1 || opc == 2){
                if(colegiado.getDni() != null){
                    if (!colegiado.getDni().isEmpty()) {
                        if(colegiado.getDni().length() == 8){
                            if(validaEntrada.validarSoloNumerosDNI(colegiado.getDni())){
                                colegiadoRep = colegiadoService.listarColegiado(opc,colegiado);
                            } else {
                                colegiadoRep.setIndError(1);
                                colegiadoRep.setMsjError("[Error: El DNI debe tener 8"
                                        +" numeros]");
                            }
                        } else {
                            colegiadoRep.setIndError(1);
                            colegiadoRep.setMsjError("[Error: El DNI solo debe tener 8"
                                    +" caracteres]");
                        }
                    } else {
                        colegiadoRep.setIndError(1);
                        colegiadoRep.setMsjError("[Error: El DNI no puede ser vacio]");
                    }
                } else {
                    colegiadoRep.setIndError(1);
                    colegiadoRep.setMsjError("[Error: El DNI no puede ser vacio]");
                }
            } else if (opc == 3 || opc == 4) {
                if(colegiado.getDni() != null){
                    if(!colegiado.getDni().isEmpty()){
                        if(colegiado.getDni().length() == 8){
                            if(validaEntrada.validarSoloNumerosDNI(colegiado.getDni())){
                                colegiadoRep = colegiadoService.listarColegiado(opc,colegiado);
                            } else {
                                colegiadoRep.setIndError(1);
                                colegiadoRep.setMsjError("[Error: El DNI debe tener 8"
                                        +" numeros]");
                            }
                        } else {
                            colegiadoRep.setIndError(1);
                            colegiadoRep.setMsjError("[Error: El DNI solo debe tener 8"
                                    +" caracteres]");
                        }
                    } else {
                        if(colegiado.getNumColegiatura().length() <= 10){
                            if(validaEntrada.validarSoloNumerosColegiatura(colegiado.getNumColegiatura())){
                                colegiadoRep = colegiadoService.listarColegiado(opc,colegiado);
                            } else {
                                colegiadoRep.setIndError(1);
                                colegiadoRep.setMsjError("[Error: El Numero Colegiatura debe tener solo"
                                        +" numeros]");
                            }
                        } else {
                            colegiadoRep.setIndError(1);
                            colegiadoRep.setMsjError("[Error: El Numero Colegiatura acepta como"
                                    +" maximo 10 numeros]");
                        }
                    }
                } else if (colegiado.getNumColegiatura() != null){
                    if(!colegiado.getNumColegiatura().isEmpty()){
                        if(colegiado.getNumColegiatura().length() <= 10){
                            if(validaEntrada.validarSoloNumerosColegiatura(colegiado.getNumColegiatura())){
                                colegiadoRep = colegiadoService.listarColegiado(opc,colegiado);
                            } else {
                                colegiadoRep.setIndError(1);
                                colegiadoRep.setMsjError("[Error: El Numero Colegiatura debe tener solo"
                                        +" numeros]");
                            }
                        } else {
                            colegiadoRep.setIndError(1);
                            colegiadoRep.setMsjError("[Error: El Numero Colegiatura acepta como"
                                    +" maximo 10 numeros]");
                        }
                    } else {
                        colegiadoRep.setIndError(1);
                        colegiadoRep.setMsjError("[Error: El DNI o El Numero Colegiatura no pueden ser vacios]");
                    }
                } else {
                    colegiadoRep.setIndError(1);
                    colegiadoRep.setMsjError("[Error: El DNI o El Numero Colegiatura no pueden ser vacios]");
                }
            } else {
                colegiadoRep = colegiadoService.listarColegiado(opc, colegiado);
            }            
        }catch(Exception ex){
            colegiadoRep.setIndError(1);
            colegiadoRep.setMsjError("Error:["+ex.getMessage()+"]");
        } 
        return colegiadoRep;
    }
    
    @RequestMapping(value="colegiados/mantColegiadoInscripcion.action", method = RequestMethod.POST)
    public @ResponseBody Colegiado mantColegiadoInscripcion(
          @RequestBody String objs  
    ){
        ValidaEntrada validaEntrada;
        Colegiado personaRep = new Colegiado();
        validaEntrada = new ValidaEntrada();
        ObjectMapper mapper = new ObjectMapper();
        CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer opc;
        Integer indIncripIngre;
        Colegiado persona;
        try{
            JsonNode node = mapper.readTree(objs);
            opc = mapper.convertValue(node.get("opcion"), Integer.class);
            indIncripIngre = mapper.convertValue(node.get("indicador"), Integer.class);
            persona = mapper.convertValue(node.get("colegiado"), Colegiado.class);
            
            /*validacion de información*/
            if (indIncripIngre == null){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: Debe seleccionar si es una Inscripcion o un Ingreso de colegiatura]");
                personaRep.setIdCampoError("cboIngresoInscripcion");
                return personaRep;
            }
            
            if(persona.getDni() == null){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El DNI no puede ser vacio]");
                personaRep.setIdCampoError("txtDni");
                return personaRep;
            }
            if(persona.getDni().isEmpty()){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El DNI no puede ser vacio]");
                personaRep.setIdCampoError("txtDni");
                return personaRep;
            }
            if(persona.getDni().length() < 8 || persona.getDni().length() > 9){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El DNI solo debe tener 8 caracteres]'");
                personaRep.setIdCampoError("txtDni");
                return personaRep;
            }
            if(!validaEntrada.validarSoloNumerosDNI(persona.getDni())){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El DNI debe tener 8 numeros]'");
                personaRep.setIdCampoError("txtDni");
                return personaRep;
            }
            
            if(persona.getNumColegiatura() == null){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El Numero Colegiatura no puede ser vacio]");
                personaRep.setIdCampoError("txtNumColegiatura");
                return personaRep;
            }
            
            if(persona.getNumColegiatura().isEmpty()){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El Numero Colegiatura no puede ser vacio]");
                personaRep.setIdCampoError("txtNumColegiatura");
                return personaRep;
            }
            
            if(persona.getNumColegiatura().length()< 1 || persona.getNumColegiatura().length() > 10){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El Numero Colegiatura acepta maximo 10 caracteres]");
                personaRep.setIdCampoError("txtNumColegiatura");
                return personaRep;
            }
            
            if(!validaEntrada.validarSoloNumerosColegiatura(persona.getNumColegiatura())){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El Numero Colegiatura acepta maximo 10 numeros]'");
                personaRep.setIdCampoError("txtNumColegiatura");
                return personaRep;
            }
            
            if(opc == 1){
                if(indIncripIngre == 0) {
                    if(persona.getFechaInscripcion() == null){
                        personaRep.setIndError(1);
                        personaRep.setMsjError("[Error: El campo Fecha Inscripcion/Ingreso no puede ser vacio]");
                        personaRep.setIdCampoError("txtFechaIngresoInscripcion");
                        return personaRep;
                    }


                    if(!validaEntrada.validaFecha(persona.getFechaInscripcion())){
                        personaRep.setIndError(1);
                        personaRep.setMsjError("[Error: El campo Fecha Inscripcion/Ingreso tiene "
                                + "formato incorrecto o no es real. Recuerde DD/MM/YYYY]");
                        personaRep.setIdCampoError("txtFechaIngresoInscripcion");
                        return personaRep;
                    }
                } else {
                    if(persona.getFechaIngreso()== null){
                        personaRep.setIndError(1);
                        personaRep.setMsjError("[Error: El campo Fecha Inscripcion/Ingreso no puede ser vacio]");
                        personaRep.setIdCampoError("txtFechaIngresoInscripcion");
                        return personaRep;
                    }

                    if(!validaEntrada.validaFecha(persona.getFechaIngreso())){
                        personaRep.setIndError(1);
                        personaRep.setMsjError("[Error: El campo Fecha Inscripcion/Ingreso tiene "
                                + "formato incorrecto o no es real. Recuerde DD/MM/YYYY]");
                        personaRep.setIdCampoError("txtFechaIngresoInscripcion");
                        return personaRep;
                    }
                }
            } else {
                if(persona.getFechaInscripcion() != null){
                    if(!persona.getFechaInscripcion().isEmpty()){
                        if(!validaEntrada.validaFecha(persona.getFechaInscripcion())){
                            personaRep.setIndError(1);
                            personaRep.setMsjError("[Error: El campo Fecha Inscripcion tiene "
                                    + "formato incorrecto o no es real. Recuerde DD/MM/YYYY]");
                            personaRep.setIdCampoError("txtFechaInscripcion");
                            return personaRep;
                        }
                    }
                }
                
//                if(persona.getFechaIngreso() != null){
//                    if(!persona.getFechaIngreso().isEmpty()){
//                        if(!validaEntrada.validaFecha(persona.getFechaIngreso())){
//                            personaRep.setIndError(1);
//                            personaRep.setMsjError("[Error: El campo Fecha Ingreso tiene "
//                                    + "formato incorrecto o no es real. Recuerde DD/MM/YYYY]");
//                            personaRep.setIdCampoError("txtFechaIngreso");
//                            return personaRep;
//                        }
//                    }
//                }
                
            } 
            if(persona.getApePaterno() == null){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El campo Apellido Paterno no puede ser vacio]");
                personaRep.setIdCampoError("txtApellidoPat");
                return personaRep;
            }
            if(persona.getApePaterno().isEmpty()){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El campo Apellido Paterno no puede ser vacio]");
                personaRep.setIdCampoError("txtApellidoPat");
                return personaRep;
            }
            if(persona.getApePaterno().length()>50){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El campo Apellido Paterno solo acepta hasta 50 caracteres]");
                personaRep.setIdCampoError("txtApellidoPat");
                return personaRep;
            }
            if(!validaEntrada.validarSoloLetras2(persona.getApePaterno())){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El campo Apellido Paterno solo acepta letras y espacios]");
                personaRep.setIdCampoError("txtApellidoPat");
                return personaRep;
            }
            
            
            if(persona.getApeMaterno()== null){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El campo Apellido Materno no puede ser vacio]");
                personaRep.setIdCampoError("txtApellidoMat");
                return personaRep;
            }
            if(persona.getApeMaterno().isEmpty()){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El campo Apellido Materno no puede ser vacio]");
                personaRep.setIdCampoError("txtApellidoMat");
                return personaRep;
            }
            if(persona.getApeMaterno().length()>50){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El campo Apellido Materno solo acepta hasta 50 caracteres]");
                personaRep.setIdCampoError("txtApellidoMat");
                return personaRep;
            }
            if(!validaEntrada.validarSoloLetras2(persona.getApeMaterno())){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El campo Apellido Materno solo acepta letras y espacios]");
                personaRep.setIdCampoError("txtApellidoMat");
                return personaRep;
            }
            
            
            if(persona.getNombres()== null){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El campo Nombres no puede ser vacio]");
                personaRep.setIdCampoError("txtNombres");
                return personaRep;
            }
            if(persona.getNombres().isEmpty()){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El campo Nombres no puede ser vacio]");
                personaRep.setIdCampoError("txtNombres");
                return personaRep;
            }
            if(persona.getNombres().length()>50){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El campo Nombres solo acepta hasta 100 caracteres]");
                personaRep.setIdCampoError("txtNombres");
                return personaRep;
            }
            if(!validaEntrada.validarSoloLetras2(persona.getNombres())){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El campo Nombres solo acepta letras y espacios]");
                personaRep.setIdCampoError("txtNombres");
                return personaRep;
            }
            
            
            if(persona.getIdEstadoCivil()== null){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: Debe seleccionar un Estado Civil]");
                personaRep.setIdCampoError("cboEstadoCivil");
                return personaRep;
            }
            if(persona.getIdEstadoCivil() == 0){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: Debe seleccionar un Estado Civil]");
                personaRep.setIdCampoError("cboEstadoCivil");
                return personaRep;
            }
            
            if(persona.getGrupoSanguineo()!= null){
               if(persona.getGrupoSanguineo().length() > 20){
                    personaRep.setIndError(1);
                    personaRep.setMsjError("[Error: El campo Grupo Sanguineo solo acepta hasta 20 caracteres]");
                    personaRep.setIdCampoError("txtGrupoSan");
                    return personaRep;
               }
            }
            
            
            if(persona.getRh()!= null){
               if(persona.getRh().length() > 10){
                    personaRep.setIndError(1);
                    personaRep.setMsjError("[Error: El campo Rh solo acepta hasta 10 caracteres]");
                    personaRep.setIdCampoError("txtRh");
                    return personaRep;
               }
            }
            
            
            if(persona.getFechaNacimiento() == null){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El campo Fecha Nacimiento no puede ser vacio]");
                personaRep.setIdCampoError("txtFechaNacimiento");
                return personaRep;
            }
            
            if(!validaEntrada.validaFecha(persona.getFechaNacimiento())){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El campo Fecha Nacimiento tiene "
                        + "formato incorrecto o no es real. Recuerde DD/MM/YYYY]");
                personaRep.setIdCampoError("txtFechaNacimiento");
                return personaRep;
            }
            
            if(persona.getIdPaisOri() == null || persona.getIdPaisOri() == 0){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: Debe seleccionar un Pais]");
                personaRep.setIdCampoError("cboPaisOrigen");
                return personaRep;
            }
            
            if(persona.getIdDepartamentoOri()== null || persona.getIdDepartamentoOri() == 0){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: Debe seleccionar un Departamento]");
                personaRep.setIdCampoError("cboDepartamentoOrigen");
                return personaRep;
            }
            
            if(persona.getIdProvinciaOri()== null || persona.getIdProvinciaOri() == 0){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: Debe seleccionar una Provincia]");
                personaRep.setIdCampoError("cboProvinciaOrigen");
                return personaRep;
            }
            
            if(persona.getIdDistritoOri()== null || persona.getIdDistritoOri()== 0){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: Debe seleccionar un Distrito]");
                personaRep.setIdCampoError("cboDistritoOrigen");
                return personaRep;
            }
            
            if(persona.getLibretaMilitar() != null){
                if(persona.getLibretaMilitar().length() > 20){
                    personaRep.setIndError(1);
                    personaRep.setMsjError("[Error: El campo Libreta Militar solo acepta hasta 20 caracteres]");
                    personaRep.setIdCampoError("txtLibretaMilitar");
                    return personaRep;
                }
            }
            
            
            if(persona.getCarnetExt()!= null){
                if(persona.getCarnetExt().length() > 20){
                    personaRep.setIndError(1);
                    personaRep.setMsjError("[Error: El campo Carnet Extranjeria solo acepta hasta 20 caracteres]");
                    personaRep.setIdCampoError("txtCarnetExtranjeria");
                    return personaRep;
                }
            }
            
            if(persona.getFfaa_pnp()!= null){
                if(persona.getFfaa_pnp().length() > 20){
                    personaRep.setIndError(1);
                    personaRep.setMsjError("[Error: El campo Tarjeta FF.AA. y PNP solo acepta hasta 20 caracteres]");
                    personaRep.setIdCampoError("txtTarjetaIdentidad");
                    return personaRep;
                }
            }
            
            if(persona.getCarnetEssalud()!= null){
                if(persona.getCarnetEssalud().length() > 20){
                    personaRep.setIndError(1);
                    personaRep.setMsjError("[Error: El campo Carnet Essalud solo acepta hasta 20 caracteres]");
                    personaRep.setIdCampoError("txtCarnetEssalud");
                    return personaRep;
                }
            }
            
            if(persona.getDomicilio()== null){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El campo Domicilio no puede ser vacio]");
                personaRep.setIdCampoError("txtDomicilioActual");
                return personaRep;
            }
            if(persona.getDomicilio().length() > 200){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El campo Domicilio solo acepta hasta 200 caracteres]");
                personaRep.setIdCampoError("txtDomicilioActual");
                return personaRep;
            }
            if(!validaEntrada.validarCaracteresDomicilio(persona.getDomicilio())){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El campo Domicilio contiene caracteres no permitidos]");
                personaRep.setIdCampoError("txtDomicilioActual");
                return personaRep;
            }
            
            if(persona.getCodigoPostal()!= null){
                if(persona.getCodigoPostal().length() > 10){
                    personaRep.setIndError(1);
                    personaRep.setMsjError("[Error: El campo Codigo postal solo acepta hasta 10 caracteres]");
                    personaRep.setIdCampoError("txtCodigoPostal");
                    return personaRep;
                }
            }
            
            if(persona.getTelfDomicilio()== null){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El campo Telf. Domicilio no puede ser vacio]");
                personaRep.setIdCampoError("txtTelfDomicilio");
                return personaRep;
            }
            if(persona.getTelfDomicilio().length() > 15){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El campo Telf. Domicilio solo acepta hasta 15 caracteres]");
                personaRep.setIdCampoError("txtTelfDomicilio");
                return personaRep;
            }
            if(!validaEntrada.validarTextoTelefono(persona.getTelfDomicilio())){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El campo Telf. Domicilio solo puede contener numeros y #]");
                personaRep.setIdCampoError("txtTelfDomicilio");
                return personaRep;
            }
            
            if(persona.getEmailPrincipal()== null){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El campo Email 1 no puede ser vacio]");
                personaRep.setIdCampoError("txtEmail1");
                return personaRep;
            }
            if(persona.getEmailPrincipal().length() > 100){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El campo Email 1 solo acepta hasta 100 caracteres]");
                personaRep.setIdCampoError("txtEmail1");
                return personaRep;
            }
            if(!validaEntrada.validarEmail(persona.getEmailPrincipal())){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El campo Email 1. Formato de email incorrecto]");
                personaRep.setIdCampoError("txtEmail1");
                return personaRep;
            }
                        
            if(persona.getEmailSecundario()!= null){
                if(persona.getEmailSecundario().length() > 100){
                    personaRep.setIndError(1);
                    personaRep.setMsjError("[Error: El campo Email 2 solo acepta hasta 100 caracteres]");
                    personaRep.setIdCampoError("txtEmail2");
                    return personaRep;
                }
                if(!validaEntrada.validarEmail(persona.getEmailSecundario())){
                    personaRep.setIndError(1);
                    personaRep.setMsjError("[Error: El campo Email 2. Formato de email incorrecto]");
                    personaRep.setIdCampoError("txtEmail2");
                    return personaRep;
                }
            }
            personaRep = colegiadoService.grabarColegiado(opc, indIncripIngre, persona,
                    user);
        }catch(Exception ex){
            personaRep.setIndError(1);
            personaRep.setMsjError("Error:["+ex.getMessage()+"]");
        } 
        return personaRep;
    }
// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="ActualizarDatosColegiatura">
    @RequestMapping(value="colegiados/ActualizaDatosColegiatura.cqfdp", method = RequestMethod.GET)
    public ModelAndView mostrarVistaActualizaDatosColegiatura(){
        String tituloParametria;
        String msjGrabar;
        String msjCancelar;
        ModelAndView vista = new ModelAndView();
        tituloParametria = "ACTUALIZACION DE DATOS DE COLEGIATURA";
        msjGrabar = "Grabar Actualizacion Datos";
        msjCancelar = "Cancelar Actualizacion datos";
        vista.addObject("TituloModulo", tituloParametria);
        vista.addObject("MsjGrabar", msjGrabar);
        vista.addObject("MsjCancelar", msjCancelar);
        vista.setViewName("modulocolegiados/ActualizarDatosColegiatura");
        return vista;
    }
// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="EgresoColegiatura">
    @RequestMapping(value="colegiados/EgresoColegiatura.cqfdp", method = RequestMethod.GET)
    public ModelAndView mostrarVistaEgresoColegiatura(){
        String tituloParametria;
        String msjGrabar;
        String msjCancelar;
        ModelAndView vista = new ModelAndView();
        tituloParametria = "EGRESO DE COLEGIATURA";
        msjGrabar = "Grabar Egreso Colegiatura";
        msjCancelar = "Cancelar Egreso Colegiatura";
        vista.addObject("TituloModulo", tituloParametria);
        vista.addObject("MsjGrabar", msjGrabar);
        vista.addObject("MsjCancelar", msjCancelar);
        vista.setViewName("modulocolegiados/EgresoColegiatura");
        return vista;
    }
    
    @RequestMapping(value="colegiados/listarColegiadosEgreso.action", method = RequestMethod.POST)
    public @ResponseBody ListColegiado listarColegiadosEgreso(
    ){
        ListColegiado resp = new ListColegiado();
        Colegiado col = new Colegiado();
        try{
            col.setDni("");
            col.setNumColegiatura("");
            resp = colegiadoService.buscarColegiadoEgreso(5, col);
        } catch(Exception ex){
            resp.setIndError(1);
            resp.setMsjError("[Error: "+ex.getMessage()+" ]");
        }
        return  resp;
    }
    
    @RequestMapping(value="colegiados/grabarEgresoColegiatura.action", method = RequestMethod.POST)
    public @ResponseBody ListColegiado grabarEgresoColegiatura(
            @RequestBody ListColegiado listaColegiados 
    ){
        ListColegiado resp = new ListColegiado();
        CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean band = false;
        try{
            if(listaColegiados.getListaColegiados() != null){
                if(!listaColegiados.getListaColegiados().isEmpty()){
                    for(Colegiado item :listaColegiados.getListaColegiados()){
                        if(item.getIndSolicitud() != null){
                            if(item.getIndSolicitud() == 2) {
                                band = true;
                                break;
                            }
                        }
                    }
                    if(band){
                        resp = colegiadoService.grabarEgresoColegiado(listaColegiados, user);
                    } else {
                        resp.setIndError(1);
                        resp.setMsjError("[Error: No hay ningun colegiado seleccionado para egreso]");
                    }
                } else {
                    resp.setIndError(1);
                    resp.setMsjError("[Error: No hay informacion para egresos]");
                }
            } else {
                resp.setIndError(1);
                resp.setMsjError("[Error: No hay informacion para egresos]");
            }
            
        } catch(Exception ex){
            resp.setIndError(1);
            resp.setMsjError("[Error: "+ex.getMessage()+" ]");
        }
        return  resp;
    }
// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Solicitud Egreso Colegiatura">
    @RequestMapping(value="colegiados/SolicitudEgresoColegiatura.cqfdp", method = RequestMethod.GET)
    public ModelAndView mostrarVistaSolicitudEgresoColegiatura(){
        String tituloParametria;
        String msjGrabar;
        String msjCancelar;
        ModelAndView vista = new ModelAndView();
        tituloParametria = "SOLICITUD EGRESO DE COLEGIATURA";
        msjGrabar = "Grabar Solicitud Egreso";
        msjCancelar = "Cancelar Solicitud Egreso";
        vista.addObject("TituloModulo", tituloParametria);
        vista.addObject("MsjGrabar", msjGrabar);
        vista.addObject("MsjCancelar", msjCancelar);
        vista.setViewName("modulocolegiados/SolicitudEgresoColegiatura");
        return vista;
    }
    
    @RequestMapping(value="colegiados/grabarSolicitudEgreso.action", method = RequestMethod.POST)
    public @ResponseBody Colegiado grabarSolicitudEgreso(
          @RequestBody Colegiado colegiado  
    ){
        ValidaEntrada validaEntrada;
        Colegiado personaRep = new Colegiado();
        validaEntrada = new ValidaEntrada();
        CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try{
            if(colegiado.getDni() == null){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El DNI no puede ser vacio]");
                return personaRep;
            }
            if(colegiado.getDni().isEmpty()){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El DNI no puede ser vacio]");
                return personaRep;
            }
            if(colegiado.getDni().length() < 8 || colegiado.getDni().length() > 9){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El DNI solo debe tener 8 caracteres]'");
                return personaRep;
            }
            if(!validaEntrada.validarSoloNumerosDNI(colegiado.getDni())){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El DNI debe tener 8 numeros]'");
                return personaRep;
            }
            
            if(colegiado.getNumColegiatura() == null){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El Numero Colegiatura no puede ser vacio]");
                return personaRep;
            }
            
            if(colegiado.getNumColegiatura().isEmpty()){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El Numero Colegiatura no puede ser vacio]");
                return personaRep;
            }
            
            if(colegiado.getNumColegiatura().length()< 1 || colegiado.getNumColegiatura().length() > 10){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El Numero Colegiatura acepta maximo 10 caracteres]");
                return personaRep;
            }
            
            if(!validaEntrada.validarSoloNumerosColegiatura(colegiado.getNumColegiatura())){
                personaRep.setIndError(1);
                personaRep.setMsjError("[Error: El Numero Colegiatura acepta maximo 10 numeros]'");
                return personaRep;
            }
            personaRep = colegiadoService.grabarSolicitudColegiatura(colegiado, user);
        }catch(Exception ex){
            personaRep.setIndError(1);
            personaRep.setMsjError("Error:["+ex.getMessage()+"]");
        } 
        return personaRep;
    }
// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Buscar Colegiado Pagos">
    @RequestMapping(value="colegiados/buscarColegiadosPagos.action", method = RequestMethod.POST)
    public @ResponseBody ListColegiado buscarColegiadosPagos(
        @RequestBody String objs      
    ){
        ListColegiado resp = new ListColegiado();
        Colegiado col = new Colegiado();
        ObjectMapper mapper = new ObjectMapper();
        ValidaEntrada valida = new ValidaEntrada();
        String tipoOperacion;
        Integer tipoDocumento;
        String numeroDocumento;
        try{
            JsonNode node = mapper.readTree(objs);
            tipoOperacion = mapper.convertValue(node.get("tipoOperacion"), String.class);
            tipoDocumento = mapper.convertValue(node.get("tipoDocumento"), Integer.class);
            col = mapper.convertValue(node.get("colegiado"), Colegiado.class);   
            if(!tipoOperacion.equals("C") && !tipoOperacion.equals("N")){
                resp.setIndError(1);
                resp.setMsjError("[Error: valor incorrecto en el Tipo]");
                return resp;
            }
            
            if(tipoDocumento != 2){
                resp.setIndError(1);
                resp.setMsjError("[Error: valor incorrecto en el documento]");
                return resp;
            }
            
            if(tipoOperacion.equals("C")){
                if(!valida.validarSoloNumerosColegiatura(col.getNumColegiatura())){
                    resp.setIndError(1);
                    resp.setMsjError("[Error: valor incorrecto en el documento]");
                    return resp;
                }
            } else {
                if(!valida.validarSoloNumerosDNI(col.getDni())){
                    resp.setIndError(1);
                    resp.setMsjError("[Error: valor incorrecto en el documento]");
                    return resp;
                }
            }
            
            resp = colegiadoService.buscarColegiadosPagos(tipoOperacion, tipoDocumento, col);
        } catch(Exception ex){
            resp.setIndError(1);
            resp.setMsjError("[Error: "+ex.getMessage()+" ]");
        }
        return  resp;
    }
    // </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="Reporte Colegiado">
    @RequestMapping(value="reportes/reporteColegiados.cqfdp", method = RequestMethod.GET)
    public ModelAndView viewReporteColegiados(){
        String tituloParametria;
        String msjGrabar;
        String msjCancelar;
        ModelAndView vista = new ModelAndView();
        tituloParametria = "REPORTES DE COLEGIADOS";
        vista.addObject("TituloModulo", tituloParametria);
        vista.setViewName("moduloReportes/reporteColegiados");
        return vista;
    }
    
    
    @RequestMapping(value = "reportes/reporteColegiados.action", method = RequestMethod.GET)
    public void reporteColegiados(
    @RequestParam Integer reporte,
    @RequestParam Integer opc,
    @RequestParam String dni,
    @RequestParam String numeroColegiatura,
    @RequestParam Integer estado,
    @RequestParam String formato,
    HttpServletRequest request,
    HttpServletResponse response) {
        byte varRespuesta[];
        String reportName;
        String reportFormat=formato;
        ServletOutputStream outputStream;
        try {
            Map reportParameters = new HashMap(); 
            CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(reporte == 1){
                reportParameters.put("parOpc", opc);            
                reportParameters.put("parDNI", dni);            
                reportParameters.put("parNumColeg", numeroColegiatura);                       
                reportParameters.put("parEstadoColeg", estado);                       
                reportParameters.put("parOpcExpLab", 1);                       
                reportParameters.put("parOpcFamColeg", 1);                       
                reportParameters.put("parUser", user.getUsername()); 
                reportName = "repCarnetizacionRecarnetizacionColegiado";
            } else {
                reportParameters.put("parOpc", opc);            
                reportParameters.put("parDNI", dni);            
                reportParameters.put("parNumColeg", ("ALL".equals(numeroColegiatura)? "%" : numeroColegiatura));                       
                reportParameters.put("parEstado", estado);                       
                reportParameters.put("parUser", user.getUsername());
                reportName = "repColegiadoEstados";
            }                   
            response.setContentType("application/"+formato);
            response.setHeader("Content-Disposition", "attachment;filename="+reportName+"."+formato);
            if(formato.equalsIgnoreCase("PDF")){
                varRespuesta=birtReport.getReport(reportName+".rptdesign", reportFormat, reportParameters,user.getUsername());
            } else {
                varRespuesta=birtReport.getReportXLS(reportName+".rptdesign", reportParameters,user.getUsername());
            }
            response.setContentLength(varRespuesta.length);
            outputStream = response.getOutputStream();
            outputStream.write(varRespuesta, 0, varRespuesta.length);
            outputStream.flush();
            outputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    // </editor-fold>
}
