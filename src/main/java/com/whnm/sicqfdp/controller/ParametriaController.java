/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.controller;

import com.whnm.sicqfdp.beans.CondicionCasa;
import com.whnm.sicqfdp.beans.Departamento;
import com.whnm.sicqfdp.beans.Distrito;
import com.whnm.sicqfdp.beans.DistritoList;
import com.whnm.sicqfdp.beans.EstadoCivil;
import com.whnm.sicqfdp.beans.ListCondicionCasa;
import com.whnm.sicqfdp.beans.ListDepartamento;
import com.whnm.sicqfdp.beans.ListEstadoCivil;
import com.whnm.sicqfdp.beans.ListOperadora;
import com.whnm.sicqfdp.beans.ListProvincia;
import com.whnm.sicqfdp.beans.ListTipoEquipo;
import com.whnm.sicqfdp.beans.Operadora;
import com.whnm.sicqfdp.beans.Provincia;
import com.whnm.sicqfdp.beans.TipoEquipo;
import com.whnm.sicqfdp.interfaces.CondicionCasaDao;
import com.whnm.sicqfdp.interfaces.DepartamentoDao;
import com.whnm.sicqfdp.interfaces.DistritoDao;
import com.whnm.sicqfdp.interfaces.EstadoCivilDao;
import com.whnm.sicqfdp.interfaces.OperadoraDao;
import com.whnm.sicqfdp.interfaces.ProvinciaDao;
import com.whnm.sicqfdp.interfaces.TipoEquipoDao;
import com.whnm.sicqfdp.utils.ValidaEntrada;
import java.io.IOException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *Clase que controlara toda la parametria general del
 * sistema
 * @author wilson
 * @version 1.0
 */
@Controller
public class ParametriaController {
    @Autowired
    @Qualifier("distritoService")
    private DistritoDao distritoServicio;
    
    @Autowired
    @Qualifier("provinciaService")
    private ProvinciaDao provinciaServicio;
    
    @Autowired
    @Qualifier("departamentoService")
    private DepartamentoDao departamentoServicio;
    
    @Autowired
    @Qualifier("estadoCivilService")
    private EstadoCivilDao estadoCivilService;
    
    @Autowired
    @Qualifier("condicionCasaService")
    private CondicionCasaDao condicionCasaService;
    
    @Autowired
    @Qualifier("operadoraService")
    private OperadoraDao operadoraService;
    
    @Autowired
    @Qualifier("tipoEquipoService")
    private TipoEquipoDao tipoEquipoService;


// <editor-fold defaultstate="collapsed" desc="parametria distritos">
    @RequestMapping(value="Distritos.cqfdp", method = RequestMethod.GET)
    public ModelAndView mostrarVistaDistrito(){
        String urlJs1;
        String urlJs2;
        String tituloParametria;
        String msjGrabar;
        String msjEditar;
        String msjEliminar;
        ModelAndView vista = new ModelAndView();
        urlJs1 = "/js/parametriageneral/Distrito.js";
        urlJs2 = "/js/parametriageneral/AdmDistrito.js";
        tituloParametria = "MANTENIMIENTO DE DISTRITOS";
        msjGrabar = "Grabar Distrito";
        msjEditar = "Editar Distrito";
        msjEliminar = "Eliminar Distrito";
        vista.addObject("UrlJs1", urlJs1);
        vista.addObject("UrlJs2", urlJs2);
        vista.addObject("TituloParametria", tituloParametria);
        vista.addObject("MsjGrabar", msjGrabar);
        vista.addObject("MsjEditar", msjEditar);
        vista.addObject("MsjEliminar", msjEliminar);
        vista.setViewName("parametriageneral/PlantillaParametria");
        return vista;
    }
    
    
    @RequestMapping(value="ListarAllDistritos.action", method = RequestMethod.POST)
    public @ResponseBody DistritoList listarAllDistritos(
    ){
        DistritoList listaDistritos;
        listaDistritos = distritoServicio.listarTodosDistritos();
        return listaDistritos;
    }
    
    @RequestMapping(value="GrabarDistrito.action", method = RequestMethod.POST)
    public @ResponseBody Distrito grabarDistrito(
          @RequestBody Distrito distrito  
    ){
        ValidaEntrada validaEntrada;
        Distrito distritoRep = new Distrito();
        validaEntrada = new ValidaEntrada();
        if(!distrito.getNombreDistrito().trim().isEmpty()) {
            if (validaEntrada.validarSoloLetras2(distrito.getNombreDistrito())){
                if (distrito.getHabilitado() == 0 || distrito.getHabilitado() == 1){
                    distritoRep = distritoServicio.grabar(distrito);
                } else {
                    distritoRep.setIndError(1);
                    distritoRep.setMsjError("Error:[Campo de habilitado contiene un valor incorrecto]");
                }             
            } else {
                distritoRep.setIndError(1);
                distritoRep.setMsjError("Error:[Solo se permiten letras y espacios]");
            }
        } else {
            distritoRep.setIndError(1);
            distritoRep.setMsjError("Error:[Campo Descripción no puede ser vacio]");
        }
        return distritoRep;
    }
    
    @RequestMapping(value="EditarDistrito.action", method = RequestMethod.POST)
    public @ResponseBody Distrito editarDistrito(
          @RequestBody Distrito distrito  
    ){
        ValidaEntrada validaEntrada;
        Distrito distritoRep = new Distrito();
        validaEntrada = new ValidaEntrada();
        if (distrito.getIdDistrito() > 0) {
            if(!distrito.getNombreDistrito().trim().isEmpty()){
                if (validaEntrada.validarSoloLetras2(distrito.getNombreDistrito())){
                    if (distrito.getHabilitado() == 0 || distrito.getHabilitado() == 1){
                         distritoRep = distritoServicio.editar(distrito);
                    } else {
                        distritoRep.setIndError(1);
                        distritoRep.setMsjError("Error:[Campo de habilitación contiene un valor incorrecto]");
                    }             
                } else {
                    distritoRep.setIndError(1);
                    distritoRep.setMsjError("Error:[Solo se permiten letras y espacios]");
                }
            } else {
                distritoRep.setIndError(1);
                distritoRep.setMsjError("Error:[Campo Descripción no puede ser vacio]");
            }
        } else {
            distritoRep.setIndError(1);
            distritoRep.setMsjError("Error:[Codigo de distrito Incorrecto]");
        }  
        return distritoRep;
    }
    
    @RequestMapping(value="EliminarDistrito.action", method = RequestMethod.POST)
    public @ResponseBody Distrito eliminarDistrito(
          @RequestBody Distrito distrito  
    ){
        Distrito distritoRep = new Distrito();
        if (distrito.getIdDistrito() > 0) {
            distritoRep = distritoServicio.eliminar(distrito);
        } else {
            distritoRep.setIndError(1);
            distritoRep.setMsjError("Error:[Codigo de distrito Incorrecto]");
        }
        return distritoRep;
    }
    
    @RequestMapping(value="ListarDistritoId.action", method = RequestMethod.POST)
    public @ResponseBody Distrito listarDistritoId(
          @RequestBody Distrito distrito  
    ){
        Distrito distritoRep = new Distrito();
        if (distrito.getIdDistrito() > 0) {
            distritoRep = distritoServicio.listar(distrito);
        } else {
            distritoRep.setIndError(1);
            distritoRep.setMsjError("Error:[Codigo de distrito Incorrecto]");
        }
        return distritoRep;
    }
    
    @RequestMapping(value="ListarDistritosHabilitados.action", method = RequestMethod.POST)
    public @ResponseBody DistritoList listarDistritosHabilitados(
            @RequestBody Distrito distrito      
    ){
        DistritoList listaDistritos = new DistritoList();
        if (distrito.getHabilitado() == 0 || distrito.getHabilitado() == 1){
            listaDistritos = distritoServicio.listarTodosDistritos();
        } else {
            listaDistritos.setIndError(1);
            listaDistritos.setMsjError("Error:[Campo de estado habilitado contiene un valor incorrecto]");
        }        
        return listaDistritos;
    }
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="parametria provincia">
    @RequestMapping(value="Provincia.cqfdp", method = RequestMethod.GET)
    public ModelAndView mostrarVistaProvincia(){
        String urlJs1;
        String urlJs2;
        String urlJs3;
        String tituloParametria;
        String msjGrabar;
        String msjEditar;
        String msjEliminar;
        String msjAsociar;
        ModelAndView vista = new ModelAndView();
        urlJs1 = "/js/parametriageneral/Distrito.js";
        urlJs2 = "/js/parametriageneral/Provincia.js";
        urlJs3 = "/js/parametriageneral/AdmProvincia.js";
        tituloParametria = "MANTENIMIENTO DE PROVINCIAS";
        msjGrabar = "Grabar Provincia";
        msjEditar = "Editar Provincia";
        msjEliminar = "Eliminar Provincia";
        msjAsociar = "Asociar Distrito a Provincia";
        vista.addObject("UrlJs1", urlJs1);
        vista.addObject("UrlJs2", urlJs2);
        vista.addObject("UrlJs3", urlJs3);
        vista.addObject("TituloParametria", tituloParametria);
        vista.addObject("MsjGrabar", msjGrabar);
        vista.addObject("MsjEditar", msjEditar);
        vista.addObject("MsjEliminar", msjEliminar);
        vista.addObject("MsjAsociar", msjAsociar);
        vista.setViewName("parametriageneral/PlantillaParametria2");
        return vista;
    }
    
    
    @RequestMapping(value="ListarAllProvincias.action", method = RequestMethod.POST)
    public @ResponseBody ListProvincia listarAllProvincias(
    ){
        ListProvincia listaProvincias;
        listaProvincias = provinciaServicio.listarAllProvincias();
        return listaProvincias;
    }
    
    @RequestMapping(value="GrabarProvincia.action", method = RequestMethod.POST)
    public @ResponseBody Provincia grabarProvincia(
          @RequestBody Provincia provincia 
    ){
        ValidaEntrada validaEntrada;
        Provincia provinciaRep = new Provincia();
        validaEntrada = new ValidaEntrada();
        if (provincia.getNombreProvincia() != null) {
            if(!provincia.getNombreProvincia().trim().isEmpty()) {
                if (validaEntrada.validarSoloLetras2(provincia.getNombreProvincia())){
                    if (provincia.getHabilitado() == 0 || provincia.getHabilitado() == 1){
                        provinciaRep = provinciaServicio.grabar(provincia);
                    } else {
                        provinciaRep.setIndError(1);
                        provinciaRep.setMsjError("Error:[Campo de habilitado contiene un valor incorrecto]");
                    }             
                } else {
                    provinciaRep.setIndError(1);
                    provinciaRep.setMsjError("Error:[Solo se permiten letras y espacios]");
                }
            } else {
                provinciaRep.setIndError(1);
                provinciaRep.setMsjError("Error:[Campo Descripción no puede ser vacio]");
            }
        } else {
            provinciaRep.setIndError(1);
            provinciaRep.setMsjError("Error:[Campo Descripción no puede ser vacio]");
        }
        
        return provinciaRep;
    }
    
    @RequestMapping(value="EditarProvincia.action", method = RequestMethod.POST)
    public @ResponseBody Provincia editarProvincia(
          @RequestBody Provincia provincia  
    ){
        ValidaEntrada validaEntrada;
        Provincia provinciaRep = new Provincia();
        validaEntrada = new ValidaEntrada();
        if (provincia.getIdProvincia()> 0) {
            if(provincia.getNombreProvincia() != null){
                if(!provincia.getNombreProvincia().trim().isEmpty()){
                    if (validaEntrada.validarSoloLetras2(provincia.getNombreProvincia())){
                        if (provincia.getHabilitado() == 0 || provincia.getHabilitado() == 1){
                             provinciaRep = provinciaServicio.editar(provincia);
                        } else {
                            provinciaRep.setIndError(1);
                            provinciaRep.setMsjError("Error:[Campo de habilitación contiene un valor incorrecto]");
                        }             
                    } else {
                        provinciaRep.setIndError(1);
                        provinciaRep.setMsjError("Error:[Solo se permiten letras y espacios]");
                    }
                } else {
                    provinciaRep.setIndError(1);
                    provinciaRep.setMsjError("Error:[Campo Descripción no puede ser vacio]");
                }
            } else {
                provinciaRep.setIndError(1);
                provinciaRep.setMsjError("Error:[Campo Descripción no puede ser vacio]");
            }
        } else {
            provinciaRep.setIndError(1);
            provinciaRep.setMsjError("Error:[Codigo de provincia Incorrecto]");
        }  
        return provinciaRep;
    }
    
    @RequestMapping(value="EliminarProvincia.action", method = RequestMethod.POST)
    public @ResponseBody Provincia eliminarProvincia(
          @RequestBody Provincia provincia
    ){
        Provincia provinciaRep = new Provincia();
        if (provincia.getIdProvincia() > 0) {
            provinciaRep = provinciaServicio.eliminar(provincia);
        } else {
            provinciaRep.setIndError(1);
            provinciaRep.setMsjError("Error:[Codigo de provincia Incorrecto]");
        }
        return provinciaRep;
    }
    
    @RequestMapping(value="ListarProvinciaId.action", method = RequestMethod.POST)
    public @ResponseBody Provincia listarProvinciaId(
          @RequestBody Provincia provincia  
    ){
        Provincia provinciaRep = new Provincia();
        if (provincia.getIdProvincia()> 0) {
            provinciaRep = provinciaServicio.listar(provincia);
        } else {
            provinciaRep.setIndError(1);
            provinciaRep.setMsjError("Error:[Codigo de provincia Incorrecto]");
        }
        return provinciaRep;
    }    
    
    @RequestMapping(value="ListarDistritosProvincia.action", method = RequestMethod.POST)
    public @ResponseBody DistritoList ListarDistritosProvincia(
          @RequestBody Provincia provincia  
    ){
        DistritoList listaDistritos = new DistritoList();
        if (provincia.getIdProvincia()> 0) {
            listaDistritos = provinciaServicio.listarDistritosDeProvincia(provincia);
        } else {
            listaDistritos.setIndError(1);
            listaDistritos.setMsjError("Error:[Codigo de provincia Incorrecto]");
        }
        return listaDistritos;
    }   
    
    @RequestMapping(value="GrabarDistritosProvincia.action", method = RequestMethod.POST)
    public @ResponseBody Provincia GrabarDistritosProvincia(
          @RequestBody String objs  
    ){
        Provincia provinciaRep = new Provincia();
        ObjectMapper mapper = new ObjectMapper();
        try{
            JsonNode node = mapper.readTree(objs);
            Provincia provincia =  mapper.convertValue(node.get("provincia"), Provincia.class);
            Distrito distrito =  mapper.convertValue(node.get("distrito"), Distrito.class);
            if (provincia.getIdProvincia() > 0) {
                if(distrito.getIdDistrito() > 0){
                     provinciaRep = provinciaServicio.grabarDistritoDeProvincia(provincia, distrito);
                } else {
                    provinciaRep.setIndError(1);
                    provinciaRep.setMsjError("Error:[Codigo de distrito Incorrecto]");
                }
            } else {
                provinciaRep.setIndError(1);
                provinciaRep.setMsjError("Error:[Codigo de provincia Incorrecto]");
            }
        } catch(Exception ex){
            provinciaRep.setIndError(1);
            provinciaRep.setMsjError("Error:["+ex.getMessage()+"]");
        }
        return provinciaRep;
    }  
    
    @RequestMapping(value="EliminarDistritosProvincia.action", method = RequestMethod.POST)
    public @ResponseBody Provincia EliminarDistritosProvincia(
          @RequestBody String objs  
    ){
        Provincia provinciaRep = new Provincia();
        ObjectMapper mapper = new ObjectMapper();
        try{
            JsonNode node = mapper.readTree(objs);
            Provincia provincia =  mapper.convertValue(node.get("provincia"), Provincia.class);
            Distrito distrito =  mapper.convertValue(node.get("distrito"), Distrito.class);
            if (provincia.getIdProvincia() > 0) {
                if(distrito.getIdDistrito() > 0){
                     provinciaRep = provinciaServicio.eliminarDistritoDeProvincia(provincia, distrito);
                } else {
                    provinciaRep.setIndError(1);
                    provinciaRep.setMsjError("Error:[Codigo de distrito Incorrecto]");
                }
            } else {
                provinciaRep.setIndError(1);
                provinciaRep.setMsjError("Error:[Codigo de provincia Incorrecto]");
            }
        } catch(Exception ex){
            provinciaRep.setIndError(1);
            provinciaRep.setMsjError("Error:["+ex.getMessage()+"]");
        }
        return provinciaRep;
    }
    
    @RequestMapping(value="ListarProvinciasHabilitadas.action", method = RequestMethod.POST)
    public @ResponseBody ListProvincia listarProvinciasHabilitadas(
            @RequestBody Provincia provincia     
    ){
        ListProvincia listaProvincias = new ListProvincia();
        if (provincia.getHabilitado() == 0 || provincia.getHabilitado() == 1){
            listaProvincias = provinciaServicio.listarProvinciasHabilitadas(provincia);
        } else {
            listaProvincias.setIndError(1);
            listaProvincias.setMsjError("Error:[Campo de estado habilitado contiene un valor incorrecto]");
        }        
        return listaProvincias;
    }
// </editor-fold>    

// <editor-fold defaultstate="collapsed" desc="parametria departamentos">
    @RequestMapping(value="Departamento.cqfdp", method = RequestMethod.GET)
    public ModelAndView mostrarVistaDepartamento(){
        String urlJs1;
        String urlJs2;
        String urlJs3;
        String tituloParametria;
        String msjGrabar;
        String msjEditar;
        String msjEliminar;
        String msjAsociar;
        ModelAndView vista = new ModelAndView();
        urlJs1 = "/js/parametriageneral/Provincia.js";
        urlJs2 = "/js/parametriageneral/Departamento.js";
        urlJs3 = "/js/parametriageneral/AdmDepartamento.js";
        tituloParametria = "MANTENIMIENTO DE DEPARTAMENTOS";
        msjGrabar = "Grabar Departamento";
        msjEditar = "Editar Departamento";
        msjEliminar = "Eliminar Departamento";
        msjAsociar = "Asociar Provincia a Departamento";
        vista.addObject("UrlJs1", urlJs1);
        vista.addObject("UrlJs2", urlJs2);
        vista.addObject("UrlJs3", urlJs3);
        vista.addObject("TituloParametria", tituloParametria);
        vista.addObject("MsjGrabar", msjGrabar);
        vista.addObject("MsjEditar", msjEditar);
        vista.addObject("MsjEliminar", msjEliminar);
        vista.addObject("MsjAsociar", msjAsociar);
        vista.setViewName("parametriageneral/PlantillaParametria2");
        return vista;
    }
    
    
    @RequestMapping(value="ListarAllDepartamentos.action", method = RequestMethod.POST)
    public @ResponseBody ListDepartamento ListarAllDepartamentos(
    ){
        ListDepartamento listaDepartamentos;
        listaDepartamentos = departamentoServicio.listarAllDepartamentos();
        return listaDepartamentos;
    }
    
    @RequestMapping(value="GrabarDepartamento.action", method = RequestMethod.POST)
    public @ResponseBody Departamento GrabarDepartamento(
          @RequestBody Departamento departamento 
    ){
        ValidaEntrada validaEntrada;
        Departamento departamentoRep = new Departamento();
        validaEntrada = new ValidaEntrada();
        if (departamento.getNombreDepartamento()!= null) {
            if(!departamento.getNombreDepartamento().trim().isEmpty()) {
                if (validaEntrada.validarSoloLetras2(departamento.getNombreDepartamento())){
                    if (departamento.getHabilitado() == 0 || departamento.getHabilitado() == 1){
                        departamentoRep = departamentoServicio.grabar(departamento);
                    } else {
                        departamentoRep.setIndError(1);
                        departamentoRep.setMsjError("Error:[Campo de habilitado contiene un valor incorrecto]");
                    }             
                } else {
                    departamentoRep.setIndError(1);
                    departamentoRep.setMsjError("Error:[Solo se permiten letras y espacios]");
                }
            } else {
                departamentoRep.setIndError(1);
                departamentoRep.setMsjError("Error:[Campo Descripción no puede ser vacio]");
            }
        } else {
            departamentoRep.setIndError(1);
            departamentoRep.setMsjError("Error:[Campo Descripción no puede ser vacio]");
        }
        
        return departamentoRep;
    }
    
    @RequestMapping(value="EditarDepartamento.action", method = RequestMethod.POST)
    public @ResponseBody Departamento EditarDepartamento(
          @RequestBody Departamento departamento  
    ){
        ValidaEntrada validaEntrada;
        Departamento departamentoRep = new Departamento();
        validaEntrada = new ValidaEntrada();
        if (departamento.getIdDepartamento()> 0) {
            if(departamento.getNombreDepartamento()!= null){
                if(!departamento.getNombreDepartamento().trim().isEmpty()){
                    if (validaEntrada.validarSoloLetras2(departamento.getNombreDepartamento())){
                        if (departamento.getHabilitado() == 0 || departamento.getHabilitado() == 1){
                             departamentoRep = departamentoServicio.editar(departamento);
                        } else {
                            departamentoRep.setIndError(1);
                            departamentoRep.setMsjError("Error:[Campo de habilitación contiene un valor incorrecto]");
                        }             
                    } else {
                        departamentoRep.setIndError(1);
                        departamentoRep.setMsjError("Error:[Solo se permiten letras y espacios]");
                    }
                } else {
                    departamentoRep.setIndError(1);
                    departamentoRep.setMsjError("Error:[Campo Descripción no puede ser vacio]");
                }
            } else {
                departamentoRep.setIndError(1);
                departamentoRep.setMsjError("Error:[Campo Descripción no puede ser vacio]");
            }
        } else {
            departamentoRep.setIndError(1);
            departamentoRep.setMsjError("Error:[Codigo de Departamento Incorrecto]");
        }  
        return departamentoRep;
    }
    
    @RequestMapping(value="EliminarDepartamento.action", method = RequestMethod.POST)
    public @ResponseBody Departamento EliminarDepartamento(
          @RequestBody Departamento departamento
    ){
        Departamento departamentoRep = new Departamento();
        if (departamento.getIdDepartamento()> 0) {
            departamentoRep = departamentoServicio.eliminar(departamento);
        } else {
            departamentoRep.setIndError(1);
            departamentoRep.setMsjError("Error:[Codigo de departamento Incorrecto]");
        }
        return departamentoRep;
    }
    
    @RequestMapping(value="ListarDepartamentoId.action", method = RequestMethod.POST)
    public @ResponseBody Departamento ListarDepartamentoId(
          @RequestBody Departamento departamento  
    ){
        Departamento departamentoRep = new Departamento();
        if (departamento.getIdDepartamento()> 0) {
            departamentoRep = departamentoServicio.listar(departamento);
        } else {
            departamentoRep.setIndError(1);
            departamentoRep.setMsjError("Error:[Codigo de departamento Incorrecto]");
        }
        return departamentoRep;
    }    
    
    @RequestMapping(value="ListarProvinciaDepartamento.action", method = RequestMethod.POST)
    public @ResponseBody ListProvincia ListarProvinciaDepartamento(
          @RequestBody Departamento departamento  
    ){
        ListProvincia listaProvincia = new ListProvincia();
        if (departamento.getIdDepartamento()> 0) {
            listaProvincia = departamentoServicio.listarProvinciaDeDepartamento(departamento);
        } else {
            listaProvincia.setIndError(1);
            listaProvincia.setMsjError("Error:[Codigo de departamento Incorrecto]");
        }
        return listaProvincia;
    }   
    
    @RequestMapping(value="GrabarProvinciaDepartamento.action", method = RequestMethod.POST)
    public @ResponseBody Departamento GrabarProvinciaDepartamento(
          @RequestBody String objs  
    ){
        Departamento departamentoRep = new Departamento();
        ObjectMapper mapper = new ObjectMapper();
        try{
            JsonNode node = mapper.readTree(objs);
            Departamento departamento = mapper.convertValue(node.get("departamento"), Departamento.class);
            Provincia provincia =  mapper.convertValue(node.get("provincia"), Provincia.class);
            if (departamento.getIdDepartamento()> 0) {
                if(provincia.getIdProvincia()> 0){
                     departamentoRep = departamentoServicio.grabarProvinciaDeDepartamento(departamento, provincia);
                } else {
                    departamentoRep.setIndError(1);
                    departamentoRep.setMsjError("Error:[Codigo de provincia Incorrecto]");
                }
            } else {
                departamentoRep.setIndError(1);
                departamentoRep.setMsjError("Error:[Codigo de departamento Incorrecto]");
            }
        } catch(Exception ex){
            departamentoRep.setIndError(1);
            departamentoRep.setMsjError("Error:["+ex.getMessage()+"]");
        }
        return departamentoRep;
    }  
    
    @RequestMapping(value="EliminarProvinciaDepartamento.action", method = RequestMethod.POST)
    public @ResponseBody Departamento EliminarProvinciaDepartamento(
          @RequestBody String objs  
    ){
        Departamento departamentoRep = new Departamento();
        ObjectMapper mapper = new ObjectMapper();
        try{
            JsonNode node = mapper.readTree(objs);
            Departamento departamento =  mapper.convertValue(node.get("departamento"), Departamento.class);
            Provincia provincia =  mapper.convertValue(node.get("provincia"), Provincia.class);
            if (departamento.getIdDepartamento()> 0) {
                if(provincia.getIdProvincia()> 0){
                     departamentoRep = departamentoServicio.eliminarProvinciaDeDepartamento(departamento, provincia);
                } else {
                    departamentoRep.setIndError(1);
                    departamentoRep.setMsjError("Error:[Codigo de provincia Incorrecto]");
                }
            } else {
                departamentoRep.setIndError(1);
                departamentoRep.setMsjError("Error:[Codigo de departamento Incorrecto]");
            }
        } catch(Exception ex){
            departamentoRep.setIndError(1);
            departamentoRep.setMsjError("Error:["+ex.getMessage()+"]");
        }
        return departamentoRep;
    }
    
    @RequestMapping(value="ListarDepartamentosHabilitados.action", method = RequestMethod.POST)
    public @ResponseBody ListDepartamento ListarDepartamentosHabilitados(
            @RequestBody Departamento departamento     
    ){
        ListDepartamento listaDepartamentos = new ListDepartamento();
        if (departamento.getHabilitado() == 0 || departamento.getHabilitado() == 1){
            listaDepartamentos = departamentoServicio.listarDepartamentosHabilitadas(departamento);
        } else {
            listaDepartamentos.setIndError(1);
            listaDepartamentos.setMsjError("Error:[Campo de estado habilitado contiene un valor incorrecto]");
        }        
        return listaDepartamentos;
    }
// </editor-fold>   

// <editor-fold defaultstate="collapsed" desc="parametria Estado Civil">
    @RequestMapping(value="EstadoCivil.cqfdp", method = RequestMethod.GET)
    public ModelAndView mostrarVistaEstadoCivil(){
        String urlJs1;
        String urlJs2;
        String tituloParametria;
        String msjGrabar;
        String msjEditar;
        String msjEliminar;
        ModelAndView vista = new ModelAndView();
        urlJs1 = "/js/parametriageneral/EstadoCivil.js";
        urlJs2 = "/js/parametriageneral/AdmEstadoCivil.js";
        tituloParametria = "MANTENIMIENTO DE ESTADO CIVIL";
        msjGrabar = "Grabar Estado Civil";
        msjEditar = "Editar Estado Civil";
        msjEliminar = "Eliminar Estado Civil";
        vista.addObject("UrlJs1", urlJs1);
        vista.addObject("UrlJs2", urlJs2);
        vista.addObject("TituloParametria", tituloParametria);
        vista.addObject("MsjGrabar", msjGrabar);
        vista.addObject("MsjEditar", msjEditar);
        vista.addObject("MsjEliminar", msjEliminar);
        vista.setViewName("parametriageneral/PlantillaParametria");
        return vista;
    }
    
    
    @RequestMapping(value="listEstadosCiviles.action", method = RequestMethod.POST)
    public @ResponseBody ListEstadoCivil listEstadosCiviles(
            @RequestBody String objs
    ){
        Integer opcListado;
        EstadoCivil estadoCivil;
        ListEstadoCivil listaEstadosCiviles = new ListEstadoCivil();
        ObjectMapper mapper = new ObjectMapper();
        try{
            JsonNode node = mapper.readTree(objs);
            opcListado =  mapper.convertValue(node.get("opc"), Integer.class);
            estadoCivil =  mapper.convertValue(node.get("estadoCivil"), EstadoCivil.class);
            switch(opcListado){
                case 1:
                    listaEstadosCiviles = estadoCivilService.listarEstadoCivil(opcListado, estadoCivil);
                    break;
                case 2:
                    if (estadoCivil.getIdEstadoCivil() > 0) {
                        listaEstadosCiviles = estadoCivilService.listarEstadoCivil(opcListado, estadoCivil);
                    } else {
                        listaEstadosCiviles.setIndError(1);
                        listaEstadosCiviles.setMsjError("Error : [Id Estado Civil incorrecto]");
                    }
                    break;
                case 3:
                    if (estadoCivil.getHabilitado() == 0 || estadoCivil.getHabilitado() == 1){
                        listaEstadosCiviles = estadoCivilService.listarEstadoCivil(opcListado, estadoCivil);
                    } else {
                        listaEstadosCiviles.setIndError(1);
                        listaEstadosCiviles.setMsjError("Error : [Campo habilitado incorrecto]"); 
                    } 
                    break;
                default :
                    listaEstadosCiviles.setIndError(1);
                    listaEstadosCiviles.setMsjError("Error : [Opcion no contemplada]"); 
                    break;
            }
            
        } catch(IOException ex){
            listaEstadosCiviles.setIndError(1);
            listaEstadosCiviles.setMsjError("Error : ["+ex.getMessage()+"]");
        }   
        return listaEstadosCiviles;
    }
    
    @RequestMapping(value="mantEstadoCivil.action", method = RequestMethod.POST)
    public @ResponseBody EstadoCivil mantEstadoCivil(
          @RequestBody String objs  
    ){
        Integer opcCrud;
        EstadoCivil estadoCivil;
        EstadoCivil estadoCivilRep = new EstadoCivil();
        ObjectMapper mapper = new ObjectMapper();
        ValidaEntrada valida = new ValidaEntrada();
        try{
            JsonNode node = mapper.readTree(objs);
            opcCrud =  mapper.convertValue(node.get("opc"), Integer.class);
            estadoCivil =  mapper.convertValue(node.get("estadoCivil"), EstadoCivil.class);
            switch(opcCrud){
                case 1:
                    if(estadoCivil.getDenominacion() != null){
                        if(!estadoCivil.getDenominacion().trim().isEmpty()){
                            if(estadoCivil.getDenominacion().length() <= 20) {
                                if(valida.validarSoloLetras2(estadoCivil.getDenominacion())){
                                    if(estadoCivil.getAbbr()!= null){
                                        if(!estadoCivil.getAbbr().trim().isEmpty()){
                                            if(estadoCivil.getAbbr().length() == 1){
                                                if(valida.validarSoloLetras2(estadoCivil.getAbbr())){
                                                    if(estadoCivil.getHabilitado() == 0 || estadoCivil.getHabilitado() == 1){
                                                        estadoCivilRep = estadoCivilService.mantEstadoCivil(opcCrud, estadoCivil);
                                                    } else {
                                                        estadoCivilRep.setIndError(1);
                                                        estadoCivilRep.setMsjError("Error : [Campo habilitado incorrecto]");
                                                    }
                                                } else {
                                                    estadoCivilRep.setIndError(1);
                                                    estadoCivilRep.setMsjError("Error : [Campo abreviatura solo permite letras y espacio]");
                                                }
                                            } else {
                                                estadoCivilRep.setIndError(1);
                                                estadoCivilRep.setMsjError("Error : [Campo abreviatura debe ser una letra]");
                                            }    
                                        }
                                    }
                                } else {
                                    estadoCivilRep.setIndError(1);
                                    estadoCivilRep.setMsjError("Error : [Campo Descripcion solo permite letras y espacio]");
                                }
                            } else {
                                estadoCivilRep.setIndError(1);
                                estadoCivilRep.setMsjError("Error : [Campo Descripcion solo puede contener 20 letras y/o espacios]");
                            }    
                        } else {
                            estadoCivilRep.setIndError(1);
                            estadoCivilRep.setMsjError("Error : [Campo Descripcion no puede estar vacio]");
                        }
                    } else {
                        estadoCivilRep.setIndError(1);
                        estadoCivilRep.setMsjError("Error : [Campo Descripcion no puede estar vacio]");
                    }
                    break;
                case 2:
                    if(estadoCivil.getDenominacion() != null){
                        if(!estadoCivil.getDenominacion().trim().isEmpty()){
                            if(estadoCivil.getDenominacion().length() <= 20) {
                                if(valida.validarSoloLetras2(estadoCivil.getDenominacion())){
                                    if(estadoCivil.getAbbr()!= null){
                                        if(!estadoCivil.getAbbr().trim().isEmpty()){
                                            if(estadoCivil.getAbbr().length() == 1) {
                                                if(valida.validarSoloLetras2(estadoCivil.getAbbr())){
                                                    if(estadoCivil.getHabilitado() == 0 || estadoCivil.getHabilitado() == 1){
                                                        if(estadoCivil.getIdEstadoCivil()>0) {
                                                            estadoCivilRep = estadoCivilService.mantEstadoCivil(opcCrud, estadoCivil);
                                                        } else {
                                                            estadoCivilRep.setIndError(1);
                                                            estadoCivilRep.setMsjError("Error : [Id Estado Civil incorrecto]");
                                                        }
                                                    } else {
                                                        estadoCivilRep.setIndError(1);
                                                        estadoCivilRep.setMsjError("Error : [Campo habilitado incorrecto]");
                                                    }
                                                } else {
                                                    estadoCivilRep.setIndError(1);
                                                    estadoCivilRep.setMsjError("Error : [Campo abreviatura solo permite letras y espacio]");
                                                }
                                            } else {
                                                estadoCivilRep.setIndError(1);
                                                estadoCivilRep.setMsjError("Error : [Campo abreviatura debe ser una letra]");
                                            }    
                                        }
                                    }
                                } else {
                                    estadoCivilRep.setIndError(1);
                                    estadoCivilRep.setMsjError("Error : [Campo Descripcion solo permite letras y espacio]");
                                }
                            } else {
                                estadoCivilRep.setIndError(1);
                                estadoCivilRep.setMsjError("Error : [Campo Descripcion solo puede contener 20 letras y/o espacios]");
                            }    
                        } else {
                            estadoCivilRep.setIndError(1);
                            estadoCivilRep.setMsjError("Error : [Campo Descripcion no puede estar vacio]");
                        }
                    } else {
                        estadoCivilRep.setIndError(1);
                        estadoCivilRep.setMsjError("Error : [Campo Descripcion no puede estar vacio]");
                    }
                    break;
                case 3:
                    if(estadoCivil.getIdEstadoCivil()>0) {
                        estadoCivilRep = estadoCivilService.mantEstadoCivil(opcCrud, estadoCivil);
                    } else {
                        estadoCivilRep.setIndError(1);
                        estadoCivilRep.setMsjError("Error : [Id Estado Civil incorrecto]");
                    }
                    break;
                default:
                    estadoCivilRep.setIndError(1);
                    estadoCivilRep.setMsjError("Error : [Opcion no contemplada]");
                    break;
            }
        } catch(IOException ex){
            estadoCivilRep.setIndError(1);
            estadoCivilRep.setMsjError("Error : ["+ex.getMessage()+"]");
        }   
        return estadoCivilRep;
    }
// </editor-fold>    
    
// <editor-fold defaultstate="collapsed" desc="parametria Condicion Casa">
    @RequestMapping(value="CondicionCasa.cqfdp", method = RequestMethod.GET)
    public ModelAndView mostrarVistaCondicionCasa(){
        String urlJs1;
        String urlJs2;
        String tituloParametria;
        String msjGrabar;
        String msjEditar;
        String msjEliminar;
        ModelAndView vista = new ModelAndView();
        urlJs1 = "/js/parametriageneral/CondicionCasa.js";
        urlJs2 = "/js/parametriageneral/AdmCondicionCasa.js";
        tituloParametria = "MANTENIMIENTO DE CONDICIONES DE CASA";
        msjGrabar = "Grabar Condicion Casa";
        msjEditar = "Editar Condicion Casa";
        msjEliminar = "Eliminar Condicion Casa";
        vista.addObject("UrlJs1", urlJs1);
        vista.addObject("UrlJs2", urlJs2);
        vista.addObject("TituloParametria", tituloParametria);
        vista.addObject("MsjGrabar", msjGrabar);
        vista.addObject("MsjEditar", msjEditar);
        vista.addObject("MsjEliminar", msjEliminar);
        vista.setViewName("parametriageneral/PlantillaParametria");
        return vista;
    }
    
    
    @RequestMapping(value="listCondicionCasa.action", method = RequestMethod.POST)
    public @ResponseBody ListCondicionCasa listCondicionCasa(
            @RequestBody String objs
    ){
        Integer opcListado;
        CondicionCasa condicionCasa;
        ListCondicionCasa listaCondicionesCasas = new ListCondicionCasa();
        ObjectMapper mapper = new ObjectMapper();
        try{
            JsonNode node = mapper.readTree(objs);
            opcListado =  mapper.convertValue(node.get("opc"), Integer.class);
            condicionCasa =  mapper.convertValue(node.get("condCasa"), CondicionCasa.class);
            switch(opcListado){
                case 1:
                    listaCondicionesCasas = condicionCasaService.listarCondicionCasas(opcListado, condicionCasa);
                    break;
                case 2:
                    if (condicionCasa.getIdCondicion()> 0) {
                        listaCondicionesCasas = condicionCasaService.listarCondicionCasas(opcListado, condicionCasa);
                    } else {
                        listaCondicionesCasas.setIndError(1);
                        listaCondicionesCasas.setMsjError("Error : [Id Condicion Casa incorrecto]");
                    }
                    break;
                case 3:
                    if (condicionCasa.getHabilitado() == 0 || condicionCasa.getHabilitado() == 1){
                        listaCondicionesCasas = condicionCasaService.listarCondicionCasas(opcListado, condicionCasa);
                    } else {
                        listaCondicionesCasas.setIndError(1);
                        listaCondicionesCasas.setMsjError("Error : [Campo habilitado incorrecto]"); 
                    } 
                    break;
                default :
                    listaCondicionesCasas.setIndError(1);
                    listaCondicionesCasas.setMsjError("Error : [Opcion no contemplada]"); 
                    break;
            }
            
        } catch(IOException ex){
            listaCondicionesCasas.setIndError(1);
            listaCondicionesCasas.setMsjError("Error : ["+ex.getMessage()+"]");
        }   
        return listaCondicionesCasas;
    }
    
    @RequestMapping(value="mantCondicionCasa.action", method = RequestMethod.POST)
    public @ResponseBody CondicionCasa mantCondicionCasa(
          @RequestBody String objs  
    ){
        Integer opcCrud;
        CondicionCasa condiconCasa;
        CondicionCasa condicionCasaRep = new CondicionCasa();
        ObjectMapper mapper = new ObjectMapper();
        ValidaEntrada valida = new ValidaEntrada();
        try{
            JsonNode node = mapper.readTree(objs);
            opcCrud =  mapper.convertValue(node.get("opc"), Integer.class);
            condiconCasa =  mapper.convertValue(node.get("condCasa"), CondicionCasa.class);
            switch(opcCrud){
                case 1:
                    if(condiconCasa.getDenominacion() != null){
                        if(!condiconCasa.getDenominacion().trim().isEmpty()){
                            if(condiconCasa.getDenominacion().length() <= 30) {
                                if(valida.validarSoloLetras2(condiconCasa.getDenominacion())){
                                    if(condiconCasa.getHabilitado() == 0 || condiconCasa.getHabilitado() == 1){
                                        condicionCasaRep = condicionCasaService.mantCondicionCasa(opcCrud, condiconCasa);
                                    } else {
                                        condicionCasaRep.setIndError(1);
                                        condicionCasaRep.setMsjError("Error : [Campo habilitado incorrecto]");
                                    }
                                }
                            } else {
                                condicionCasaRep.setIndError(1);
                                condicionCasaRep.setMsjError("Error : [Campo Descripcion solo puede contener 30 letras y/o espacios]");
                            }    
                        } else {
                            condicionCasaRep.setIndError(1);
                            condicionCasaRep.setMsjError("Error : [Campo Descripcion no puede estar vacio]");
                        }
                    } else {
                        condicionCasaRep.setIndError(1);
                        condicionCasaRep.setMsjError("Error : [Campo Descripcion no puede estar vacio]");
                    }
                    break;
                case 2:
                    if(condiconCasa.getDenominacion() != null){
                        if(!condiconCasa.getDenominacion().trim().isEmpty()){
                            if(condiconCasa.getDenominacion().length() <= 30) {
                                if(valida.validarSoloLetras2(condiconCasa.getDenominacion())){
                                    if(condiconCasa.getHabilitado() == 0 || condiconCasa.getHabilitado() == 1){
                                        if(condiconCasa.getIdCondicion()>0) {
                                            condicionCasaRep = condicionCasaService.mantCondicionCasa(opcCrud, condiconCasa);
                                        } else {
                                            condicionCasaRep.setIndError(1);
                                            condicionCasaRep.setMsjError("Error : [Id Condicion Casa incorrecto]");
                                        }
                                    } else {
                                        condicionCasaRep.setIndError(1);
                                        condicionCasaRep.setMsjError("Error : [Campo habilitado incorrecto]");
                                    }
                                } else {
                                    condicionCasaRep.setIndError(1);
                                    condicionCasaRep.setMsjError("Error : [Campo Descripcion solo permite letras y espacio]");
                                }
                            } else {
                                condicionCasaRep.setIndError(1);
                                condicionCasaRep.setMsjError("Error : [Campo Descripcion solo puede contener 30 letras y/o espacios]");
                            }    
                        } else {
                            condicionCasaRep.setIndError(1);
                            condicionCasaRep.setMsjError("Error : [Campo Descripcion no puede estar vacio]");
                        }
                    } else {
                        condicionCasaRep.setIndError(1);
                        condicionCasaRep.setMsjError("Error : [Campo Descripcion no puede estar vacio]");
                    }
                    break;
                case 3:
                    if(condiconCasa.getIdCondicion()>0) {
                        condicionCasaRep = condicionCasaService.mantCondicionCasa(opcCrud, condiconCasa);
                    } else {
                        condicionCasaRep.setIndError(1);
                        condicionCasaRep.setMsjError("Error : [Id Condicion Casa incorrecto]");
                    }
                    break;
                default:
                    condicionCasaRep.setIndError(1);
                    condicionCasaRep.setMsjError("Error : [Opcion no contemplada]");
                    break;
            }
        } catch(IOException ex){
            condicionCasaRep.setIndError(1);
            condicionCasaRep.setMsjError("Error : ["+ex.getMessage()+"]");
        }   
        return condicionCasaRep;
    }
// </editor-fold>   
    
// <editor-fold defaultstate="collapsed" desc="parametria Operadoras">
    @RequestMapping(value="Operadora.cqfdp", method = RequestMethod.GET)
    public ModelAndView mostrarVistaOperadora(){
        String urlJs1;
        String urlJs2;
        String tituloParametria;
        String msjGrabar;
        String msjEditar;
        String msjEliminar;
        ModelAndView vista = new ModelAndView();
        urlJs1 = "/js/parametriageneral/Operadora.js";
        urlJs2 = "/js/parametriageneral/AdmOperadoras.js";
        tituloParametria = "MANTENIMIENTO DE OPERADORAS";
        msjGrabar = "Grabar Operadora";
        msjEditar = "Editar Operadora";
        msjEliminar = "Eliminar Operadora";
        vista.addObject("UrlJs1", urlJs1);
        vista.addObject("UrlJs2", urlJs2);
        vista.addObject("TituloParametria", tituloParametria);
        vista.addObject("MsjGrabar", msjGrabar);
        vista.addObject("MsjEditar", msjEditar);
        vista.addObject("MsjEliminar", msjEliminar);
        vista.setViewName("parametriageneral/PlantillaParametria");
        return vista;
    }
    
    
    @RequestMapping(value="listOperadoras.action", method = RequestMethod.POST)
    public @ResponseBody ListOperadora listOperadoras(
            @RequestBody String objs
    ){
        Integer opcListado;
        Operadora operadora;
        ListOperadora listaOperadoras = new ListOperadora();
        ObjectMapper mapper = new ObjectMapper();
        try{
            JsonNode node = mapper.readTree(objs);
            opcListado =  mapper.convertValue(node.get("opc"), Integer.class);
            operadora =  mapper.convertValue(node.get("operadora"), Operadora.class);
            switch(opcListado){
                case 1:
                    listaOperadoras = operadoraService.listarOperadoras(opcListado, operadora);
                    break;
                case 2:
                    if (operadora.getIdOperadora()> 0) {
                        listaOperadoras = operadoraService.listarOperadoras(opcListado, operadora);
                    } else {
                        listaOperadoras.setIndError(1);
                        listaOperadoras.setMsjError("Error : [Id Operadora incorrecto]");
                    }
                    break;
                case 3:
                    if (operadora.getHabilitado() == 0 || operadora.getHabilitado() == 1){
                        listaOperadoras = operadoraService.listarOperadoras(opcListado, operadora);
                    } else {
                        listaOperadoras.setIndError(1);
                        listaOperadoras.setMsjError("Error : [Campo habilitado incorrecto]"); 
                    } 
                    break;
                default :
                    listaOperadoras.setIndError(1);
                    listaOperadoras.setMsjError("Error : [Opcion no contemplada]"); 
                    break;
            }
            
        } catch(IOException ex){
            listaOperadoras.setIndError(1);
            listaOperadoras.setMsjError("Error : ["+ex.getMessage()+"]");
        }   
        return listaOperadoras;
    }
    
    @RequestMapping(value="mantOperadora.action", method = RequestMethod.POST)
    public @ResponseBody Operadora mantOperadora(
          @RequestBody String objs  
    ){
        Integer opcCrud;
        Operadora operadora;
        Operadora operadoraRep = new Operadora();
        ObjectMapper mapper = new ObjectMapper();
        ValidaEntrada valida = new ValidaEntrada();
        try{
            JsonNode node = mapper.readTree(objs);
            opcCrud =  mapper.convertValue(node.get("opc"), Integer.class);
            operadora =  mapper.convertValue(node.get("operadora"), Operadora.class);
            switch(opcCrud){
                case 1:
                    if(operadora.getDenominacion() != null){
                        if(!operadora.getDenominacion().trim().isEmpty()){
                            if(operadora.getDenominacion().length() <= 30) {
                                if(valida.validarSoloLetras2(operadora.getDenominacion())){
                                    if(operadora.getHabilitado() == 0 || operadora.getHabilitado() == 1){
                                        operadoraRep = operadoraService.mantOperadora(opcCrud, operadora);
                                    } else {
                                        operadoraRep.setIndError(1);
                                        operadoraRep.setMsjError("Error : [Campo habilitado incorrecto]");
                                    }
                                }
                            } else {
                                operadoraRep.setIndError(1);
                                operadoraRep.setMsjError("Error : [Campo Descripcion solo puede contener 30 letras y/o espacios]");
                            }    
                        } else {
                            operadoraRep.setIndError(1);
                            operadoraRep.setMsjError("Error : [Campo Descripcion no puede estar vacio]");
                        }
                    } else {
                        operadoraRep.setIndError(1);
                        operadoraRep.setMsjError("Error : [Campo Descripcion no puede estar vacio]");
                    }
                    break;
                case 2:
                    if(operadora.getDenominacion() != null){
                        if(!operadora.getDenominacion().trim().isEmpty()){
                            if(operadora.getDenominacion().length() <= 30) {
                                if(valida.validarSoloLetras2(operadora.getDenominacion())){
                                    if(operadora.getHabilitado() == 0 || operadora.getHabilitado() == 1){
                                        if(operadora.getIdOperadora()>0) {
                                            operadoraRep = operadoraService.mantOperadora(opcCrud, operadora);
                                        } else {
                                            operadoraRep.setIndError(1);
                                            operadoraRep.setMsjError("Error : [Id Operadora incorrecto]");
                                        }
                                    } else {
                                        operadoraRep.setIndError(1);
                                        operadoraRep.setMsjError("Error : [Campo habilitado incorrecto]");
                                    }
                                } else {
                                    operadoraRep.setIndError(1);
                                    operadoraRep.setMsjError("Error : [Campo Descripcion solo permite letras y espacio]");
                                }
                            } else {
                                operadoraRep.setIndError(1);
                                operadoraRep.setMsjError("Error : [Campo Descripcion solo puede contener 30 letras y/o espacios]");
                            }    
                        } else {
                            operadoraRep.setIndError(1);
                            operadoraRep.setMsjError("Error : [Campo Descripcion no puede estar vacio]");
                        }
                    } else {
                        operadoraRep.setIndError(1);
                        operadoraRep.setMsjError("Error : [Campo Descripcion no puede estar vacio]");
                    }
                    break;
                case 3:
                    if(operadora.getIdOperadora()>0) {
                        operadoraRep = operadoraService.mantOperadora(opcCrud, operadora);
                    } else {
                        operadoraRep.setIndError(1);
                        operadoraRep.setMsjError("Error : [Id Operadora incorrecto]");
                    }
                    break;
                default:
                    operadoraRep.setIndError(1);
                    operadoraRep.setMsjError("Error : [Opcion no contemplada]");
                    break;
            }
        } catch(IOException ex){
            operadoraRep.setIndError(1);
            operadoraRep.setMsjError("Error : ["+ex.getMessage()+"]");
        }   
        return operadoraRep;
    }
// </editor-fold>  
    
// <editor-fold defaultstate="collapsed" desc="parametria Tipo Equipo">
    @RequestMapping(value="TipoEquipo.cqfdp", method = RequestMethod.GET)
    public ModelAndView mostrarVistaTipoEquipo(){
        String urlJs1;
        String urlJs2;
        String tituloParametria;
        String msjGrabar;
        String msjEditar;
        String msjEliminar;
        ModelAndView vista = new ModelAndView();
        urlJs1 = "/js/parametriageneral/TipoEquipo.js";
        urlJs2 = "/js/parametriageneral/AdmTipoEquipo.js";
        tituloParametria = "MANTENIMIENTO DE TIPOS DE EQUIPOS";
        msjGrabar = "Grabar Tipo Equipo";
        msjEditar = "Editar Tipo Equipo";
        msjEliminar = "Eliminar Tipo Equipo";
        vista.addObject("UrlJs1", urlJs1);
        vista.addObject("UrlJs2", urlJs2);
        vista.addObject("TituloParametria", tituloParametria);
        vista.addObject("MsjGrabar", msjGrabar);
        vista.addObject("MsjEditar", msjEditar);
        vista.addObject("MsjEliminar", msjEliminar);
        vista.setViewName("parametriageneral/PlantillaParametria");
        return vista;
    }
    
    
    @RequestMapping(value="listTipoEquipo.action", method = RequestMethod.POST)
    public @ResponseBody ListTipoEquipo listTipoEquipo(
            @RequestBody String objs
    ){
        Integer opcListado;
        TipoEquipo tipoEquipo;
        ListTipoEquipo listaTiposEquipos = new ListTipoEquipo();
        ObjectMapper mapper = new ObjectMapper();
        try{
            JsonNode node = mapper.readTree(objs);
            opcListado =  mapper.convertValue(node.get("opc"), Integer.class);
            tipoEquipo =  mapper.convertValue(node.get("tipoEquipo"), TipoEquipo.class);
            switch(opcListado){
                case 1:
                    listaTiposEquipos = tipoEquipoService.listarTipoEquipo(opcListado, tipoEquipo);
                    break;
                case 2:
                    if (tipoEquipo.getIdTipoEquipo()> 0) {
                        listaTiposEquipos = tipoEquipoService.listarTipoEquipo(opcListado, tipoEquipo);
                    } else {
                        listaTiposEquipos.setIndError(1);
                        listaTiposEquipos.setMsjError("Error : [Id Operadora incorrecto]");
                    }
                    break;
                case 3:
                    if (tipoEquipo.getHabilitado() == 0 || tipoEquipo.getHabilitado() == 1){
                        listaTiposEquipos = tipoEquipoService.listarTipoEquipo(opcListado, tipoEquipo);
                    } else {
                        listaTiposEquipos.setIndError(1);
                        listaTiposEquipos.setMsjError("Error : [Campo habilitado incorrecto]"); 
                    } 
                    break;
                default :
                    listaTiposEquipos.setIndError(1);
                    listaTiposEquipos.setMsjError("Error : [Opcion no contemplada]"); 
                    break;
            }
            
        } catch(IOException ex){
            listaTiposEquipos.setIndError(1);
            listaTiposEquipos.setMsjError("Error : ["+ex.getMessage()+"]");
        }   
        return listaTiposEquipos;
    }
    
    @RequestMapping(value="mantTipoEquipo.action", method = RequestMethod.POST)
    public @ResponseBody TipoEquipo mantTipoEquipo(
          @RequestBody String objs  
    ){
        Integer opcCrud;
        TipoEquipo tipoEquipo;
        TipoEquipo tipoEquipoRep = new TipoEquipo();
        ObjectMapper mapper = new ObjectMapper();
        ValidaEntrada valida = new ValidaEntrada();
        try{
            JsonNode node = mapper.readTree(objs);
            opcCrud =  mapper.convertValue(node.get("opc"), Integer.class);
            tipoEquipo =  mapper.convertValue(node.get("tipoEquipo"), TipoEquipo.class);
            switch(opcCrud){
                case 1:
                    if(tipoEquipo.getDenominacion() != null){
                        if(!tipoEquipo.getDenominacion().trim().isEmpty()){
                            if(tipoEquipo.getDenominacion().length() <= 30) {
                                if(valida.validarSoloLetras2(tipoEquipo.getDenominacion())){
                                    if(tipoEquipo.getHabilitado() == 0 || tipoEquipo.getHabilitado() == 1){
                                        tipoEquipoRep = tipoEquipoService.mantTipoEquipo(opcCrud, tipoEquipo);
                                    } else {
                                        tipoEquipoRep.setIndError(1);
                                        tipoEquipoRep.setMsjError("Error : [Campo habilitado incorrecto]");
                                    }
                                }
                            } else {
                                tipoEquipoRep.setIndError(1);
                                tipoEquipoRep.setMsjError("Error : [Campo Descripcion solo puede contener 30 letras y/o espacios]");
                            }    
                        } else {
                            tipoEquipoRep.setIndError(1);
                            tipoEquipoRep.setMsjError("Error : [Campo Descripcion no puede estar vacio]");
                        }
                    } else {
                        tipoEquipoRep.setIndError(1);
                        tipoEquipoRep.setMsjError("Error : [Campo Descripcion no puede estar vacio]");
                    }
                    break;
                case 2:
                    if(tipoEquipo.getDenominacion() != null){
                        if(!tipoEquipo.getDenominacion().trim().isEmpty()){
                            if(tipoEquipo.getDenominacion().length() <= 30) {
                                if(valida.validarSoloLetras2(tipoEquipo.getDenominacion())){
                                    if(tipoEquipo.getHabilitado() == 0 || tipoEquipo.getHabilitado() == 1){
                                        if(tipoEquipo.getIdTipoEquipo()>0) {
                                            tipoEquipoRep = tipoEquipoService.mantTipoEquipo(opcCrud, tipoEquipo);
                                        } else {
                                            tipoEquipoRep.setIndError(1);
                                            tipoEquipoRep.setMsjError("Error : [Id Tipo Equipo incorrecto]");
                                        }
                                    } else {
                                        tipoEquipoRep.setIndError(1);
                                        tipoEquipoRep.setMsjError("Error : [Campo habilitado incorrecto]");
                                    }
                                } else {
                                    tipoEquipoRep.setIndError(1);
                                    tipoEquipoRep.setMsjError("Error : [Campo Descripcion solo permite letras y espacio]");
                                }
                            } else {
                                tipoEquipoRep.setIndError(1);
                                tipoEquipoRep.setMsjError("Error : [Campo Descripcion solo puede contener 30 letras y/o espacios]");
                            }    
                        } else {
                            tipoEquipoRep.setIndError(1);
                            tipoEquipoRep.setMsjError("Error : [Campo Descripcion no puede estar vacio]");
                        }
                    } else {
                        tipoEquipoRep.setIndError(1);
                        tipoEquipoRep.setMsjError("Error : [Campo Descripcion no puede estar vacio]");
                    }
                    break;
                case 3:
                    if(tipoEquipo.getIdTipoEquipo()>0) {
                        tipoEquipoRep = tipoEquipoService.mantTipoEquipo(opcCrud, tipoEquipo);
                    } else {
                        tipoEquipoRep.setIndError(1);
                        tipoEquipoRep.setMsjError("Error : [Id Tipo Equipo incorrecto]");
                    }
                    break;
                default:
                    tipoEquipoRep.setIndError(1);
                    tipoEquipoRep.setMsjError("Error : [Opcion no contemplada]");
                    break;
            }
        } catch(IOException ex){
            tipoEquipoRep.setIndError(1);
            tipoEquipoRep.setMsjError("Error : ["+ex.getMessage()+"]");
        }   
        return tipoEquipoRep;
    }
// </editor-fold>  
}
