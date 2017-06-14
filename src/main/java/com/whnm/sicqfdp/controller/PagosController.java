/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.controller;

import com.whnm.sicqfdp.beans.CustomUser;
import com.whnm.sicqfdp.beans.Deuda;
import com.whnm.sicqfdp.beans.ListLogTipoPago;
import com.whnm.sicqfdp.beans.ListTipoPago;
import com.whnm.sicqfdp.beans.ListaGenerico;
import com.whnm.sicqfdp.beans.Pago;
import com.whnm.sicqfdp.beans.TipoPago;
import com.whnm.sicqfdp.birtreport.BirtReportInterface;
import com.whnm.sicqfdp.interfaces.DeudaDao;
import com.whnm.sicqfdp.interfaces.PagoDao;
import com.whnm.sicqfdp.interfaces.TipoPagoDao;
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
 * @author whnm
 */
@Controller
public class PagosController {
    @Autowired
    @Qualifier(value = "tipoPagoService")        
    TipoPagoDao tipoPagoService;
    
    @Autowired
    @Qualifier(value = "deudaService")        
    DeudaDao deudaService;
    
    @Autowired
    @Qualifier(value = "pagoService")        
    PagoDao pagoService;
    
//    @Autowired
//    @Qualifier("birtReportInterfaceImpl")
    private BirtReportInterface birtReport;
    
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
    
    @RequestMapping(value="pagos/registroCuotasManual.cqfdp", method = RequestMethod.GET)
    public ModelAndView registroCuotasManual(){
        String tituloParametria;
        ModelAndView vista = new ModelAndView();
        tituloParametria = "REGISTRO CUOTAS INHABILITADORAS MANUAL";
        vista.addObject("TituloModulo", tituloParametria);
        vista.setViewName("moduloPagos/RegistroCuotasManual");
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
    
    // <editor-fold defaultstate="collapsed" desc="Registro de Ingresos o Pagos de Colegiados y personas">
     @RequestMapping(value="pagos/registroIngresos.cqfdp", method = RequestMethod.GET)
    public ModelAndView registroIngresos(){
        String tituloParametria;
        String btnGrabar;
        ModelAndView vista = new ModelAndView();
        tituloParametria = "REGISTRO DE INGRESOS";
        vista.addObject("TituloModulo", tituloParametria);
        vista.setViewName("moduloPagos/RegistroDeIngresos");
        return vista;
    }
    
     @RequestMapping(value="pagos/listadoPago.cqfdp", method = RequestMethod.GET)
    public ModelAndView listadoPago(){
        String tituloParametria;
        ModelAndView vista = new ModelAndView();
        tituloParametria = "LISTADO DE PAGOS";
        vista.addObject("TituloModulo", tituloParametria);
        vista.setViewName("moduloPagos/ListadoDePagos");
        return vista;
    }
    
    @RequestMapping(value="pagos/revertirPago.cqfdp", method = RequestMethod.GET)
    public ModelAndView revertirPago(){
        String tituloParametria;
        ModelAndView vista = new ModelAndView();
        tituloParametria = "REVERTIR DE PAGO";
        vista.addObject("TituloModulo", tituloParametria);
        vista.setViewName("moduloPagos/RevertirPagos");
        return vista;
    }
    
    @RequestMapping(value="pagos/mantPagos.action", method = RequestMethod.POST)
    public @ResponseBody Pago mantPagos(
          @RequestBody String objs  
    ){
        ValidaEntrada validaEntrada;
        Pago pagoRep = new Pago();
        validaEntrada = new ValidaEntrada();
        ObjectMapper mapper = new ObjectMapper();
        Integer opc;
        Pago pago;
        CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try{
            JsonNode node = mapper.readTree(objs);
            opc = mapper.convertValue(node.get("tipoOperacion"), Integer.class);
            pago = mapper.convertValue(node.get("pago"), Pago.class);     
            pagoRep = pagoService.mantPago(opc, pago, user);
        }catch(Exception ex){
            pagoRep.setIndError(1);
            pagoRep.setMsjError("Error:["+ex.getMessage()+"]");
        } 
        return pagoRep;
    }
    
    @RequestMapping(value="pagos/listarPagos.action", method = RequestMethod.POST)
    public @ResponseBody ListaGenerico<Pago> listarPagos(
          @RequestBody String objs  
    ){
        ValidaEntrada validaEntrada;
        ListaGenerico<Pago> pagoRep = new ListaGenerico<>();
        validaEntrada = new ValidaEntrada();
        ObjectMapper mapper = new ObjectMapper();
        Integer opc;
        Pago pago;
        TipoPago tipoPago;
        CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try{
            JsonNode node = mapper.readTree(objs);
            opc = mapper.convertValue(node.get("tipoOperacion"), Integer.class);
            pago = mapper.convertValue(node.get("pago"), Pago.class);     
            tipoPago = mapper.convertValue(node.get("tpago"), TipoPago.class);     
            pagoRep = pagoService.listarPagos(opc, pago, tipoPago, user);
        }catch(Exception ex){
            pagoRep.setIndError(1);
            pagoRep.setMsjError("Error:["+ex.getMessage()+"]");
        } 
        return pagoRep;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Administracion de Deuda">
     @RequestMapping(value="pagos/listarDeuda.action", method = RequestMethod.POST)
    public @ResponseBody Deuda listarDeuda(
          @RequestBody String objs  
    ){
        ValidaEntrada validaEntrada;
        Deuda rep = new Deuda();
        validaEntrada = new ValidaEntrada();
        ObjectMapper mapper = new ObjectMapper();
        Integer opc;
        Deuda deuda;
        try{
            JsonNode node = mapper.readTree(objs);
            opc = mapper.convertValue(node.get("tipoOperacion"), Integer.class);
            deuda = mapper.convertValue(node.get("deuda"), Deuda.class);     
            rep = deudaService.listarDeuda(opc, deuda);
        }catch(Exception ex){
            rep.setIndError(1);
            rep.setMsjError("Error:["+ex.getMessage()+"]");
        } 
        return rep;
    }
    
     @RequestMapping(value="pagos/mantDeuda.action", method = RequestMethod.POST)
    public @ResponseBody Deuda mantDeuda(
          @RequestBody String objs  
    ){
        ValidaEntrada validaEntrada;
        Deuda rep = new Deuda();
        validaEntrada = new ValidaEntrada();
        ObjectMapper mapper = new ObjectMapper();
        Integer opc;
        Deuda deuda;
        CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try{
            JsonNode node = mapper.readTree(objs);
            opc = mapper.convertValue(node.get("tipoOperacion"), Integer.class);
            deuda = mapper.convertValue(node.get("deuda"), Deuda.class);     
            rep = deudaService.mantDeuda(opc, deuda, user);
        }catch(Exception ex){
            rep.setIndError(1);
            rep.setMsjError("Error:["+ex.getMessage()+"]");
        } 
        return rep;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Reporte Pagos">
    @RequestMapping(value="reportes/reportePagos.cqfdp", method = RequestMethod.GET)
    public ModelAndView viewReportePagos(){
        String tituloParametria;
        ModelAndView vista = new ModelAndView();
        tituloParametria = "REPORTES DE PAGOS";
        vista.addObject("TituloModulo", tituloParametria);
        vista.setViewName("moduloReportes/reportePagos");
        return vista;
    }
    
    
    @RequestMapping(value = "reportes/reportePagos.action", method = RequestMethod.GET)
    public void reportePagos(
    @RequestParam Integer opc,
    @RequestParam String dni,
    @RequestParam String numeroColegiatura,
    @RequestParam String periodo,
    @RequestParam Integer codTipoPago,
    @RequestParam Integer estadoTipoPago,
    @RequestParam String fechaFin,
    @RequestParam Integer codPago,
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
            reportParameters.put("repOpc", opc);            
            reportParameters.put("repDni", (dni.equalsIgnoreCase("ALL")? "%": dni));            
            reportParameters.put("repNumColeg", (numeroColegiatura.equalsIgnoreCase("ALL")? "%": numeroColegiatura));                       
            reportParameters.put("repPeriodo", periodo);                       
            reportParameters.put("repCodTipoPago", codTipoPago);                       
            reportParameters.put("repEstadoPago", estadoTipoPago);                       
            reportParameters.put("repFechaFin", fechaFin);                       
            reportParameters.put("repCodPago", codPago);                       
            reportParameters.put("parUser", user.getUsername());                       
            if(formato.equalsIgnoreCase("PDF")){
                reportName = "repPagosDetallePdf";
            } else {
                reportName = "repPagosDetalle";
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
    
    // <editor-fold defaultstate="collapsed" desc="Reporte Deudas">
    @RequestMapping(value="reportes/reporteDeudas.cqfdp", method = RequestMethod.GET)
    public ModelAndView viewReporteDeudas(){
        String tituloParametria;
        ModelAndView vista = new ModelAndView();
        tituloParametria = "REPORTES DE DEUDAS";
        vista.addObject("TituloModulo", tituloParametria);
        vista.setViewName("moduloReportes/reporteDeudas");
        return vista;
    }
    
    
    @RequestMapping(value = "reportes/reporteDeudas.action", method = RequestMethod.GET)
    public void reporteDeudas(
    @RequestParam Integer opc,
    @RequestParam String numeroColegiatura,
    @RequestParam Integer codTipoPago,
    @RequestParam String periodo,
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
            reportParameters.put("repOpc", opc);                      
            reportParameters.put("repNumColeg", (numeroColegiatura.equalsIgnoreCase("ALL")? "%": numeroColegiatura));                       
            reportParameters.put("repCodTipoPago", codTipoPago);                       
            reportParameters.put("repPeriodo", periodo);                       
            reportParameters.put("parUser", user.getUsername());                       
            if(formato.equalsIgnoreCase("PDF")){
                reportName = "repDeudaDetallePdf";
            } else {
                reportName = "repDeudasDetalleXls";
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
