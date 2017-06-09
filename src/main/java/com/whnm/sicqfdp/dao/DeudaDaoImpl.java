/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.dao;

import com.whnm.sicqfdp.beans.Colegiado;
import com.whnm.sicqfdp.beans.CustomUser;
import com.whnm.sicqfdp.beans.DetalleDeuda;
import com.whnm.sicqfdp.beans.Deuda;
import com.whnm.sicqfdp.beans.Parametros;
import com.whnm.sicqfdp.beans.TipoPago;
import com.whnm.sicqfdp.interfaces.DeudaDao;
import com.whnm.sicqfdp.spbeans.SpListarDeuda;
import com.whnm.sicqfdp.spbeans.SpMantDeuda;
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
@Service("deudaDao")
public class DeudaDaoImpl implements DeudaDao{
    private DataSource dataSource;
    private SpListarDeuda spListarDeuda;
    private SpMantDeuda spMantDeuda;
    
    @Autowired
    public DeudaDaoImpl(@Qualifier("dataSource1") DataSource dataSource) {
        this.dataSource = dataSource;
        this.spListarDeuda = new SpListarDeuda(dataSource);
        this.spMantDeuda = new SpMantDeuda(dataSource);
    }
    
    @Override
    public Deuda grabar(Deuda elemento, CustomUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Deuda editar(Deuda elemento, CustomUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Deuda eliminar(Deuda elemento, CustomUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Deuda listar(Deuda elemento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Deuda listarDeuda(Integer opc, Deuda deuda) {
        Deuda deudaRep = new Deuda();
        int i;
        List<DetalleDeuda> lista = new ArrayList<>();
        Colegiado persona;
        TipoPago tipoPago;
        DetalleDeuda detalle;
        Map<String,Object> vars = new HashMap<String,Object>();
        vars.put(SpListarDeuda.PARAM_IN_OPC, opc);
        vars.put(SpListarDeuda.PARAM_IN_DOC, deuda.getColegiad().getNumColegiatura());
        vars.put(SpListarDeuda.PARAM_IN_COD_TIPO_PAGO, deuda.getTipoPago().getId());
        vars.put(SpListarDeuda.PARAM_IN_PERIODO, (deuda.getPeriodo() == null ? ' ' : deuda.getPeriodo()));
        try {
            Map<String, Object> result = (Map<String, Object>) spListarDeuda.execute(vars);
            List<Map<String, Object>> listResult = (List<Map<String, Object>>) result.get(Parametros.RESULSET);
            deudaRep.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            deudaRep.setMsjError(result.get(Parametros.MSJ).toString());
            if (deudaRep.getIndError() == 0){
               i = 0; 
               for (Map<String, Object> item : listResult) {
                    if(i == 0){
                        persona = new Colegiado(); 
                        persona.setNumColegiatura(item.get("num_colegiatura") != null ? item.get("num_colegiatura").toString() : "");
                        persona.setNombres(item.get("nombres") != null ? item.get("nombres").toString() : "");
                        deudaRep.setColegiad(persona);
                    }
                    tipoPago = new TipoPago();
                    tipoPago.setId(item.get("id_tipo_pago") != null ? Long.parseLong(item.get("id_tipo_pago").toString()) : -1);

                    detalle = new DetalleDeuda();
                    detalle.setTipoPago(tipoPago);
                    detalle.setPeriodo(item.get("periodo") != null ? item.get("periodo").toString() : "");
                    lista.add(detalle);
                    i++;
                } 
                deudaRep.setDetalleDeuda(lista);
            }
               
        } catch(Exception ex){
            Logger.getLogger(DeudaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            deudaRep.setIndError(1);
            deudaRep.setMsjError("Error: ["+ex.getMessage()+"]");        
        }
        return deudaRep;
    }

    @Override
    public Deuda mantDeuda(Integer opc, Deuda deuda, CustomUser user) {
        Deuda resp = new Deuda();
        Map<String,Object> vars = new HashMap<String,Object>();
        vars.put(SpMantDeuda.PARAM_IN_OPC_CRUD, opc);
        vars.put(SpMantDeuda.PARAM_IN_NUM_COLEG, deuda.getColegiad().getNumColegiatura());
        vars.put(SpMantDeuda.PARAM_IN_COD_TIPO_PAGO, deuda.getTipoPago().getId());
        vars.put(SpMantDeuda.PARAM_IN_PERIODO, deuda.getPeriodo());
        vars.put(SpMantDeuda.PARAM_IN_USUARIO, user.getUsername());
        try {
            Map<String, Object> result = (Map<String, Object>) spMantDeuda.execute(vars);
            resp.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            resp.setMsjError(result.get(Parametros.MSJ).toString());        
        } catch(Exception ex){
            Logger.getLogger(TipoPagoDaoImp.class.getName()).log(Level.SEVERE, null, ex);
            resp.setIndError(1);
            resp.setMsjError("Error: ["+ex.getMessage()+"]");        
        }
        return resp;
    }
    
}
