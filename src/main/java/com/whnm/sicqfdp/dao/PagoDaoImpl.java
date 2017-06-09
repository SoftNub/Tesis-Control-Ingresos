/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.dao;

import com.whnm.sicqfdp.beans.Colegiado;
import com.whnm.sicqfdp.beans.CustomUser;
import com.whnm.sicqfdp.beans.ListaGenerico;
import com.whnm.sicqfdp.beans.Pago;
import com.whnm.sicqfdp.beans.PagoDetalle;
import com.whnm.sicqfdp.beans.Parametros;
import com.whnm.sicqfdp.beans.Precio;
import com.whnm.sicqfdp.beans.TipoPago;
import com.whnm.sicqfdp.interfaces.PagoDao;
import com.whnm.sicqfdp.spbeans.SpListaPago;
import com.whnm.sicqfdp.spbeans.SpMantPago;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author whnm
 */
@Service("pagoDao")
public class PagoDaoImpl implements PagoDao{
    private DataSource dataSource;
    private SpMantPago spMantPago;
    private SpListaPago spListaPago;
    @Autowired
    public PagoDaoImpl(@Qualifier("dataSource1")DataSource dataSource) {
        this.dataSource = dataSource;
        this.spMantPago = new SpMantPago(dataSource);
        this.spListaPago = new SpListaPago(dataSource);
    }
    
    
    @Override
    public Pago mantPago(Integer tipoOperacion, Pago pago, CustomUser usuario) {
        Pago pagoRep = new Pago();
        String tramaPagos = "";
        Map<String,Object> vars = new HashMap<String,Object>();
        vars.put(SpMantPago.PARAM_IN_OPC_CRUD, tipoOperacion);
        vars.put(SpMantPago.PARAM_IN_USUARIO, usuario.getUsername());
        if(tipoOperacion == 1){
            vars.put(SpMantPago.PARAM_IN_NUM_COLEG, pago.getColegiado().getNumColegiatura());
            vars.put(SpMantPago.PARAM_IN_DNI, pago.getColegiado().getDni());
            vars.put(SpMantPago.PARAM_IN_NOMBRES, pago.getColegiado().getNombres());
            vars.put(SpMantPago.PARAM_IN_COD_PAGO, 0);
            
            if(pago.getListPagoDetalle().size() > 0){
                for(PagoDetalle pd : pago.getListPagoDetalle()){
                    tramaPagos += pd.getTipoPago().getId() + "|" + pd.getSecuencia() + "|" +
                            pd.getCantidad() + "|*";
                }

                vars.put(SpMantPago.PARAM_IN_TRAMA_PAGOS, tramaPagos);
            } else {
                 pagoRep.setIndError(1);
                 pagoRep.setMsjError("Error: [No se encontro pagos a realizar]");  
            }
        } else if(tipoOperacion == 2){
            vars.put(SpMantPago.PARAM_IN_COD_PAGO, pago.getCodigo());
            vars.put(SpMantPago.PARAM_IN_NUM_COLEG, "");
            vars.put(SpMantPago.PARAM_IN_DNI, "");
            vars.put(SpMantPago.PARAM_IN_NOMBRES, "");
            vars.put(SpMantPago.PARAM_IN_TRAMA_PAGOS, "");
        }    
        
        try {
            Map<String, Object> result = (Map<String, Object>) spMantPago.execute(vars);
            pagoRep.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            pagoRep.setMsjError(result.get(Parametros.MSJ).toString());        
        } catch(Exception ex){
            Logger.getLogger(PagoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            pagoRep.setIndError(1);
            pagoRep.setMsjError("Error: ["+ex.getMessage()+"]");        
        }
        
        return pagoRep;
    }

    @Override
    public Pago grabar(Pago elemento, CustomUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pago editar(Pago elemento, CustomUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pago eliminar(Pago elemento, CustomUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pago listar(Pago elemento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListaGenerico<Pago> listarPagos(Integer opc, Pago pago, TipoPago tipoPago, CustomUser user) {
        ListaGenerico<Pago>  resp = new ListaGenerico<> ();
        int i;
        int j;
        List<Pago> listaPagos = new ArrayList<>();
//        List<DetalleDeuda> lista = new ArrayList<>();
        Colegiado persona;
        Long codigoActual = -2L;
        Long codigoFor;
//        TipoPago tipoPago;
//        DetalleDeuda detalle;
        Map<String,Object> vars = new HashMap<String,Object>();
        vars.put(SpListaPago.PARAM_IN_OPC, opc);
        vars.put(SpListaPago.PARAM_IN_DNI, (pago.getColegiado() == null ? "0" : pago.getColegiado().getDni()));
        vars.put(SpListaPago.PARAM_IN_NUM_COLEG, (pago.getColegiado() == null ? 0 : pago.getColegiado().getNumColegiatura()));
        vars.put(SpListaPago.PARAM_IN_FECHA_INI, pago.getFechaPago());
        vars.put(SpListaPago.PARAM_IN_FECHA_FIN, pago.getFechaPagoFin());
        vars.put(SpListaPago.PARAM_IN_COD_TIPO_PAGO, (tipoPago == null ? 0: tipoPago.getId()));
        vars.put(SpListaPago.PARAM_IN_COD_PAGO, (pago.getCodigo() == null ? -1 : pago.getCodigo()));
        vars.put(SpListaPago.PARAM_IN_ESTADO_PAGO, (pago.getEstado() == null ? -1 : pago.getEstado()));
        try {
            Map<String, Object> result = (Map<String, Object>) spListaPago.execute(vars);
            List<Map<String, Object>> listResult = (List<Map<String, Object>>) result.get(Parametros.RESULSET);
            resp.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            resp.setMsjError(result.get(Parametros.MSJ).toString());
            if (resp.getIndError() == 0){
               i = 1;
               j = listResult.size(); 
               Pago p = null; 
               PagoDetalle d = null;
               List<PagoDetalle> listaPagoDetalle = null;
               for (Map<String, Object> item : listResult) {
                   codigoFor = item.get("codigo_pago") != null ? Long.parseLong(item.get("codigo_pago").toString()) : -1L;
                    if(codigoActual.longValue() == -2L){
                        p = new Pago();
                        p.setCodigo(codigoFor); 
                        codigoActual = p.getCodigo();
                        persona = new Colegiado(); 
                        persona.setNumColegiatura(item.get("num_colegiatura") != null ? item.get("num_colegiatura").toString() : "");
                        persona.setDni(item.get("DNI") != null ? item.get("DNI").toString() : "");
                        persona.setNombres(item.get("nombre") != null ? item.get("nombre").toString() : "");
                        p.setColegiado(persona);


                        p.setTotal(item.get("total") != null ? Double.parseDouble(item.get("total").toString()) : 0);
                        p.setFechaPago(item.get("fecha_pago") != null ? item.get("fecha_pago").toString() : "00/00/0000");
                        p.setEstado(item.get("estado") != null ? Integer.parseInt(item.get("estado").toString()) : 0);
                        listaPagoDetalle = new ArrayList<>();
                        d = new PagoDetalle();
                        d.setSecuencia(item.get("secuencia") != null ? Integer.parseInt(item.get("secuencia").toString()) : 0);
                        d.setCantidad(item.get("cantidad") != null ? Integer.parseInt(item.get("cantidad").toString()) : 0);
                        d.setImporte(item.get("importe") != null ? Double.parseDouble(item.get("importe").toString()) : 0);
                        TipoPago t = new TipoPago();
                        t.setId(item.get("id_tipo_pago") != null ? Long.parseLong(item.get("id_tipo_pago").toString()) : -1);
                        Precio pre = new Precio();
                        pre.setPrecio(item.get("precio_unitario") != null ? Double.parseDouble(item.get("precio_unitario").toString()) : 0);
                        t.setPrecioActual(pre);
                        d.setTipoPago(t);
                        listaPagoDetalle.add(d);
                    } else if (codigoFor.longValue() != codigoActual.longValue()){
                        p.setListPagoDetalle(listaPagoDetalle);
                        listaPagos.add(p);
                        p = new Pago();
                        p.setCodigo(codigoFor); 
                        codigoActual = p.getCodigo();
                        persona = new Colegiado(); 
                        persona.setNumColegiatura(item.get("num_colegiatura") != null ? item.get("num_colegiatura").toString() : "");
                        persona.setDni(item.get("DNI") != null ? item.get("DNI").toString() : "");
                        persona.setNombres(item.get("nombre") != null ? item.get("nombre").toString() : "");
                        p.setColegiado(persona);


                        p.setTotal(item.get("total") != null ? Double.parseDouble(item.get("total").toString()) : 0);
                        p.setFechaPago(item.get("fecha_pago") != null ? item.get("fecha_pago").toString() : "00/00/0000");
                        p.setEstado(item.get("estado") != null ? Integer.parseInt(item.get("estado").toString()) : 0);
                        listaPagoDetalle = new ArrayList<>();
                        d = new PagoDetalle();
                        d.setSecuencia(item.get("secuencia") != null ? Integer.parseInt(item.get("secuencia").toString()) : 0);
                        d.setCantidad(item.get("cantidad") != null ? Integer.parseInt(item.get("cantidad").toString()) : 0);
                        d.setImporte(item.get("importe") != null ? Double.parseDouble(item.get("importe").toString()) : 0);
                        TipoPago t = new TipoPago();
                        t.setId(item.get("id_tipo_pago") != null ? Long.parseLong(item.get("id_tipo_pago").toString()) : -1);
                        Precio pre = new Precio();
                        pre.setPrecio(item.get("precio_unitario") != null ? Double.parseDouble(item.get("precio_unitario").toString()) : 0);
                        t.setPrecioActual(pre);
                        d.setTipoPago(t);
                        listaPagoDetalle.add(d);
                    } else{
                        d = new PagoDetalle();
                        d.setSecuencia(item.get("secuencia") != null ? Integer.parseInt(item.get("secuencia").toString()) : 0);
                        d.setCantidad(item.get("cantidad") != null ? Integer.parseInt(item.get("cantidad").toString()) : 0);
                        d.setImporte(item.get("importe") != null ? Double.parseDouble(item.get("importe").toString()) : 0);
                        TipoPago t = new TipoPago();
                        t.setId(item.get("id_tipo_pago") != null ? Long.parseLong(item.get("id_tipo_pago").toString()) : -1);
                        Precio pre = new Precio();
                        pre.setPrecio(item.get("precio_unitario") != null ? Double.parseDouble(item.get("precio_unitario").toString()) : 0);
                        t.setPrecioActual(pre);
                        d.setTipoPago(t);
                        listaPagoDetalle.add(d);
                    }
                    
                    if(i == j){
                        p.setListPagoDetalle(listaPagoDetalle);
                        listaPagos.add(p);
                    }
                    i++;
                }
                resp.setListaGenerico(listaPagos);
            }
        } catch(Exception ex){
            Logger.getLogger(PagoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            resp.setIndError(1);
            resp.setMsjError("Error: ["+ex.getMessage()+"]");        
        }
        return resp;
    }
    
}
