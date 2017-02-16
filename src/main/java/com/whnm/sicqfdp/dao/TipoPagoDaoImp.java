/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.dao;

import com.whnm.sicqfdp.beans.ListTipoEquipo;
import com.whnm.sicqfdp.beans.Parametros;
import com.whnm.sicqfdp.beans.TipoEquipo;
import com.whnm.sicqfdp.interfaces.TipoEquipoDao;
import com.whnm.sicqfdp.spbeans.SpListarTipoEquipo;
import com.whnm.sicqfdp.spbeans.SpMantTipoEquipo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author wilson
 */
@Service(value="tipoEquipoDao")
public class TipoEquipoDaoImp implements TipoEquipoDao{
    private DataSource dataSource;
    private SpListarTipoEquipo spListarTipoEquipo;
    private SpMantTipoEquipo spMantTipoEquipo;
    
    @Autowired
    public TipoEquipoDaoImp(DataSource dataSource) {
        this.dataSource = dataSource;
        this.spListarTipoEquipo = new SpListarTipoEquipo(dataSource);
        this.spMantTipoEquipo = new SpMantTipoEquipo(dataSource);
    }
    
    @Override
    public TipoEquipo mantTipoEquipo(Integer opcCrud, TipoEquipo tipoEq) {
        TipoEquipo tipoEquipo = new TipoEquipo();
        Map<String,Object> vars = new HashMap<String,Object>();
        vars.put(SpMantTipoEquipo.PARAM_IN_OPCCRUD, opcCrud);
        vars.put(SpMantTipoEquipo.PARAM_IN_IDTIPOEQUIPO, tipoEq.getIdTipoEquipo());
        vars.put(SpMantTipoEquipo.PARAM_IN_DESCTIPOEQUIPO, tipoEq.getDenominacion());
        vars.put(SpMantTipoEquipo.PARAM_IN_HABILITADO, tipoEq.getHabilitado());
        try {
            Map<String, Object> result = (Map<String, Object>) spMantTipoEquipo.execute(vars);
            tipoEquipo.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            tipoEquipo.setMsjError(result.get(Parametros.MSJ).toString());        
        } catch(Exception ex){
            Logger.getLogger(OperadoraDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            tipoEquipo.setIndError(1);
            tipoEquipo.setMsjError("Error: ["+ex.getMessage()+"]");        
        }
        return tipoEquipo;
    }

    @Override
    public ListTipoEquipo listarTipoEquipo(Integer opcListado, TipoEquipo tipoEq) {
        ListTipoEquipo listaTiposEquipos = new ListTipoEquipo();
        List<TipoEquipo> tiposEquipos = new ArrayList<>();
        Map<String,Object> vars = new HashMap<String,Object>();
        vars.put(SpListarTipoEquipo.PARAM_IN_OPCLISTADO, opcListado);
        vars.put(SpListarTipoEquipo.PARAM_IN_IDTIPOEQUIPO, tipoEq.getIdTipoEquipo());
        vars.put(SpListarTipoEquipo.PARAM_IN_HABILITADO, tipoEq.getHabilitado());
        try {
            Map<String, Object> result = (Map<String, Object>) spListarTipoEquipo.execute(vars);
            List<Map<String, Object>> listResult = (List<Map<String, Object>>) result.get(Parametros.RESULSET);
            listaTiposEquipos.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            listaTiposEquipos.setMsjError(result.get(Parametros.MSJ).toString());
            for (Map<String, Object> item : listResult) {
                TipoEquipo tEquipo = new TipoEquipo();
                tEquipo.setIdTipoEquipo(item.get("id") != null ? Integer.parseInt(String.valueOf(item.get("id"))) : -1);
                tEquipo.setDenominacion(item.get("denominacion") != null ? String.valueOf(item.get("denominacion")) : "");
                tEquipo.setHabilitado(item.get("habilitado") != null ? Integer.parseInt(String.valueOf(item.get("habilitado"))) : -1);
                tiposEquipos.add(tEquipo);
            }        
        } catch(Exception ex){
            Logger.getLogger(TipoEquipoDaoImp.class.getName()).log(Level.SEVERE, null, ex);
            listaTiposEquipos.setIndError(1);
            listaTiposEquipos.setMsjError("Error: ["+ex.getMessage()+"]");
        }
        listaTiposEquipos.setListaTiposEquipos(tiposEquipos);
        return listaTiposEquipos;
    }
    
}
