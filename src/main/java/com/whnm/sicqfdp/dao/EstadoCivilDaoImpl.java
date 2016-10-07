/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.dao;

import com.whnm.sicqfdp.beans.EstadoCivil;
import com.whnm.sicqfdp.beans.ListEstadoCivil;
import com.whnm.sicqfdp.beans.Parametros;
import com.whnm.sicqfdp.interfaces.EstadoCivilDao;
import com.whnm.sicqfdp.spbeans.SpListarEstadoCivil;
import com.whnm.sicqfdp.spbeans.SpMantEstadoCivil;
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
@Service(value="estadoCivilDao")
public class EstadoCivilDaoImpl implements EstadoCivilDao{
    private DataSource dataSource;
    private SpListarEstadoCivil spListarEstadoCivil;
    private SpMantEstadoCivil spMantEstadoCivil;
    
    @Autowired
    public EstadoCivilDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.spListarEstadoCivil = new SpListarEstadoCivil(dataSource);
        this.spMantEstadoCivil = new SpMantEstadoCivil(dataSource);
    }
    
    @Override
    public EstadoCivil mantEstadoCivil(Integer opcCrud, EstadoCivil estCivil) {
        EstadoCivil estadocivil = new EstadoCivil();
        Map<String,Object> vars = new HashMap<String,Object>();
        vars.put(SpMantEstadoCivil.PARAM_IN_OPCCRUD, opcCrud);
        vars.put(SpMantEstadoCivil.PARAM_IN_IDESTADOCIVIL, estCivil.getIdEstadoCivil());
        vars.put(SpMantEstadoCivil.PARAM_IN_DESCESTADOCIVIL, estCivil.getDenominacion());
        vars.put(SpMantEstadoCivil.PARAM_IN_ABBRESTADOCIVIL, estCivil.getAbbr());
        vars.put(SpMantEstadoCivil.PARAM_IN_HABILITADO, estCivil.getHabilitado());
        try {
            Map<String, Object> result = (Map<String, Object>) spMantEstadoCivil.execute(vars);
            estadocivil.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            estadocivil.setMsjError(result.get(Parametros.MSJ).toString());        
        } catch(Exception ex){
            Logger.getLogger(DistritoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            estadocivil.setIndError(1);
            estadocivil.setMsjError("Error: ["+ex.getMessage()+"]");        
        }
        return estadocivil;
    }

    @Override
    public ListEstadoCivil listarEstadoCivil(Integer opcListado, EstadoCivil estCivil) {
        ListEstadoCivil listaEstadoCivil = new ListEstadoCivil();
        List<EstadoCivil> estadosCiviles = new ArrayList<>();
        Map<String,Object> vars = new HashMap<String,Object>();
        vars.put(SpListarEstadoCivil.PARAM_IN_OPCLISTADO, opcListado);
        vars.put(SpListarEstadoCivil.PARAM_IN_IDESTADOCILVIL, estCivil.getIdEstadoCivil());
        vars.put(SpListarEstadoCivil.PARAM_IN_HABILITADO, estCivil.getHabilitado());
        try {
            Map<String, Object> result = (Map<String, Object>) spListarEstadoCivil.execute(vars);
            List<Map<String, Object>> listResult = (List<Map<String, Object>>) result.get(Parametros.RESULSET);
            listaEstadoCivil.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            listaEstadoCivil.setMsjError(result.get(Parametros.MSJ).toString());
            for (Map<String, Object> item : listResult) {
                EstadoCivil estC = new EstadoCivil();
                estC.setIdEstadoCivil(item.get("idEstadoCivil") != null ? Integer.parseInt(String.valueOf(item.get("idEstadoCivil"))) : -1);
                estC.setDenominacion(item.get("descripcion") != null ? String.valueOf(item.get("descripcion")) : "");
                estC.setAbbr(item.get("abbr") != null ? String.valueOf(item.get("abbr")) : "");
                estC.setHabilitado(item.get("habilitado") != null ? Integer.parseInt(String.valueOf(item.get("habilitado"))) : -1);
                estadosCiviles.add(estC);
            }        
        } catch(Exception ex){
            Logger.getLogger(DistritoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            listaEstadoCivil.setIndError(1);
            listaEstadoCivil.setMsjError("Error: ["+ex.getMessage()+"]");
        }
        listaEstadoCivil.setListaEstadosCiviles(estadosCiviles);
        return listaEstadoCivil;
    }
    
}
