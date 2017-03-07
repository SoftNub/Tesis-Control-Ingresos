/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.controller;

import com.whnm.sicqfdp.beans.CustomUser;
import com.whnm.sicqfdp.beans.ListLogTipoPago;
import com.whnm.sicqfdp.beans.ListTipoPago;
import com.whnm.sicqfdp.beans.TipoPago;
import com.whnm.sicqfdp.interfaces.TipoPagoDao;
import com.whnm.sicqfdp.utils.ValidaEntrada;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author whnm
 */
@Controller
public class PagosController {
    @Autowired
    @Qualifier(value = "tipoPagoService")        
    TipoPagoDao tipoPagoService;
    
    // <editor-fold defaultstate="collapsed" desc="gestion tipo pago - precios">
     @RequestMapping(value="pagos/gestionTipoPago.cqfdp", method = RequestMethod.GET)
    public ModelAndView mostrarGestionPerfiles(){
        String tituloParametria;
        String btnGrabar;
        ModelAndView vista = new ModelAndView();
        tituloParametria = "GESTIÓN DE TIPO PAGO";
        btnGrabar = "Nuevo Tipo Pago";
        vista.addObject("TituloModulo", tituloParametria);
        vista.addObject("MsjGrabar", btnGrabar);
        vista.setViewName("moduloPagos/GestionTipoPago");
        return vista;
    }
    
    @RequestMapping(value="pagos/listarTipoPago.action", method = RequestMethod.POST)
    public @ResponseBody ListTipoPago listarTipoPago(
          @RequestBody String objs  
    ){
        ValidaEntrada validaEntrada;
        ListTipoPago lista = new ListTipoPago();
        validaEntrada = new ValidaEntrada();
        ObjectMapper mapper = new ObjectMapper();
        Integer opc;
        TipoPago tipoPago;
        try{
            JsonNode node = mapper.readTree(objs);
            opc = mapper.convertValue(node.get("tipoOperacion"), Integer.class);
            tipoPago = mapper.convertValue(node.get("tipoPago"), TipoPago.class);     
            lista = tipoPagoService.listarTipoPago(opc, tipoPago);
        }catch(Exception ex){
            lista.setIndError(1);
            lista.setMsjError("Error:["+ex.getMessage()+"]");
        } 
        return lista;
    }
    
    @RequestMapping(value="pagos/mantTipoPago.action", method = RequestMethod.POST)
    public @ResponseBody TipoPago mantTipoPago(
          @RequestBody String objs  
    ){
        ValidaEntrada validaEntrada;
        TipoPago tipoPagoRep = new TipoPago();
        validaEntrada = new ValidaEntrada();
        ObjectMapper mapper = new ObjectMapper();
        Integer opc;
        TipoPago tipoPago;
        CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try{
            JsonNode node = mapper.readTree(objs);
            opc = mapper.convertValue(node.get("tipoOperacion"), Integer.class);
            tipoPago = mapper.convertValue(node.get("tipoPago"), TipoPago.class);     
            tipoPagoRep = tipoPagoService.mantenimientoTipoPago(opc, tipoPago, user);
        }catch(Exception ex){
            tipoPagoRep.setIndError(1);
            tipoPagoRep.setMsjError("Error:["+ex.getMessage()+"]");
        } 
        return tipoPagoRep;
    }
    
    @RequestMapping(value="pagos/listarTipoPagoPrecio.action", method = RequestMethod.POST)
    public @ResponseBody TipoPago consultarPreciosTipoPago(
          @RequestBody TipoPago objs  
    ){
        ValidaEntrada validaEntrada;
        TipoPago tipoPagoRep = new TipoPago();
        validaEntrada = new ValidaEntrada();
        try{  
            tipoPagoRep = tipoPagoService.consultarPreciosTipoPago(objs);
        }catch(Exception ex){
            tipoPagoRep.setIndError(1);
            tipoPagoRep.setMsjError("Error:["+ex.getMessage()+"]");
        } 
        return tipoPagoRep;
    }
    
     @RequestMapping(value="pagos/actualizaTipoPagoPrecio.action", method = RequestMethod.POST)
    public @ResponseBody TipoPago actualizaTipoPagoPrecio(
          @RequestBody TipoPago objs  
    ){
        TipoPago tipoPagoRep = new TipoPago();
        CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try{
            tipoPagoRep = tipoPagoService.actualizaTipoPagoPrecio(objs, user);
        }catch(Exception ex){
            tipoPagoRep.setIndError(1);
            tipoPagoRep.setMsjError("Error:["+ex.getMessage()+"]");
        } 
        return tipoPagoRep;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="generacion manual -procesos automaticos">
     @RequestMapping(value="pagos/generarCoutasManuales.cqfdp", method = RequestMethod.GET)
    public ModelAndView generarCoutasManuales(){
        String tituloParametria;
        String btnGrabar;
        ModelAndView vista = new ModelAndView();
        tituloParametria = "GENERACION DE COUTAS MENSUALES";
        vista.addObject("TituloModulo", tituloParametria);
        vista.setViewName("moduloPagos/GenerarCuotaManuales");
        return vista;
    }
    
    @RequestMapping(value="pagos/generarCoutasMensuales.action", method = RequestMethod.POST)
    public @ResponseBody TipoPago generarCoutasMensuales(
          @RequestBody String objs  
    ){
        ValidaEntrada validaEntrada;
        TipoPago resp = new TipoPago();
        validaEntrada = new ValidaEntrada();
        ObjectMapper mapper = new ObjectMapper();
        String fecha;
        CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try{
            JsonNode node = mapper.readTree(objs);
            fecha = mapper.convertValue(node.get("fechaEjecucion"), String.class);   
            resp = tipoPagoService.crearCuotasInhabilitadoras(fecha, user);
        }catch(Exception ex){
            resp.setIndError(1);
            resp.setMsjError("Error:["+ex.getMessage()+"]");
        } 
        return resp;
    }
    
    @RequestMapping(value="pagos/verLogCoutasMensuales.action", method = RequestMethod.POST)
    public @ResponseBody ListLogTipoPago verLogCoutasMensuales(
          @RequestBody String objs  
    ){
        ValidaEntrada validaEntrada;
        ListLogTipoPago lista = new ListLogTipoPago();
        validaEntrada = new ValidaEntrada();
        ObjectMapper mapper = new ObjectMapper();
        String fechaIni, fechaFin;
        Integer tipoOperacion;
        try{
            JsonNode node = mapper.readTree(objs);
            tipoOperacion = mapper.convertValue(node.get("tipoOperacion"), Integer.class);
            fechaIni = mapper.convertValue(node.get("fechaInicio"), String.class);
            fechaFin = mapper.convertValue(node.get("fechaFin"), String.class);   
            lista = tipoPagoService.verLogCoutasMensuales(tipoOperacion,fechaIni, fechaFin);
        }catch(Exception ex){
            lista.setIndError(1);
            lista.setMsjError("Error:["+ex.getMessage()+"]");
        } 
        return lista;
    }
    // </editor-fold>
}
