/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.dao;

import com.whnm.sicqfdp.beans.CondicionCasa;
import com.whnm.sicqfdp.beans.CustomUser;
import com.whnm.sicqfdp.beans.ListCondicionCasa;
import com.whnm.sicqfdp.beans.Parametros;
import com.whnm.sicqfdp.interfaces.CondicionCasaDao;
import com.whnm.sicqfdp.spbeans.SpListarCondicionCasa;
import com.whnm.sicqfdp.spbeans.SpMantCondicionCasa;
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
 * @author wilson
 */
@Service(value="condicionCasaDao")
public class CondicionCasaDaoImpl implements CondicionCasaDao{
    private DataSource dataSource;
    private SpListarCondicionCasa spListarCondicionCasa;
    private SpMantCondicionCasa spMantCondicionCasa;
    
    @Autowired
    public CondicionCasaDaoImpl(@Qualifier("dataSource1")DataSource dataSource) {
        this.dataSource = dataSource;
        this.spListarCondicionCasa = new SpListarCondicionCasa(dataSource);
        this.spMantCondicionCasa = new SpMantCondicionCasa(dataSource);
    }
    
    @Override
    public CondicionCasa mantCondicionCasa(Integer opcCrud, CondicionCasa condCasa,
            CustomUser user) {
        CondicionCasa condicionCasa = new CondicionCasa();
        Map<String,Object> vars = new HashMap<String,Object>();
        vars.put(SpMantCondicionCasa.PARAM_IN_OPCCRUD, opcCrud);
        vars.put(SpMantCondicionCasa.PARAM_IN_IDCONDICIONCASA, condCasa.getIdCondicion());
        vars.put(SpMantCondicionCasa.PARAM_IN_DESCCONDICIONCASA, condCasa.getDenominacion());
        vars.put(SpMantCondicionCasa.PARAM_IN_HABILITADO, condCasa.getHabilitado());
        vars.put(SpMantCondicionCasa.PARAM_IN_USUARIO, user.getUsername());
        try {
            Map<String, Object> result = (Map<String, Object>) spMantCondicionCasa.execute(vars);
            condicionCasa.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            condicionCasa.setMsjError(result.get(Parametros.MSJ).toString());        
        } catch(Exception ex){
            Logger.getLogger(DistritoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            condicionCasa.setIndError(1);
            condicionCasa.setMsjError("Error: ["+ex.getMessage()+"]");        
        }
        return condicionCasa;
    }

    @Override
    public ListCondicionCasa listarCondicionCasas(Integer opcListado, CondicionCasa condCasa) {
        ListCondicionCasa listaCondicionCasa = new ListCondicionCasa();
        List<CondicionCasa> condicionesCasas = new ArrayList<>();
        Map<String,Object> vars = new HashMap<String,Object>();
        vars.put(SpListarCondicionCasa.PARAM_IN_OPCLISTADO, opcListado);
        vars.put(SpListarCondicionCasa.PARAM_IN_IDCONDICIONCASA, condCasa.getIdCondicion());
        vars.put(SpListarCondicionCasa.PARAM_IN_HABILITADO, condCasa.getHabilitado());
        try {
            Map<String, Object> result = (Map<String, Object>) spListarCondicionCasa.execute(vars);
            List<Map<String, Object>> listResult = (List<Map<String, Object>>) result.get(Parametros.RESULSET);
            listaCondicionCasa.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            listaCondicionCasa.setMsjError(result.get(Parametros.MSJ).toString());
            for (Map<String, Object> item : listResult) {
                CondicionCasa condC = new CondicionCasa();
                condC.setIdCondicion(item.get("idcondCasa") != null ? Integer.parseInt(String.valueOf(item.get("idcondCasa"))) : -1);
                condC.setDenominacion(item.get("denominacion") != null ? String.valueOf(item.get("denominacion")) : "");
                condC.setHabilitado(item.get("habilitado") != null ? Integer.parseInt(String.valueOf(item.get("habilitado"))) : -1);
                condicionesCasas.add(condC);
            }        
        } catch(Exception ex){
            Logger.getLogger(DistritoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            listaCondicionCasa.setIndError(1);
            listaCondicionCasa.setMsjError("Error: ["+ex.getMessage()+"]");
        }
        listaCondicionCasa.setListaCondicionesCasas(condicionesCasas);
        return listaCondicionCasa;
    }
    
}
