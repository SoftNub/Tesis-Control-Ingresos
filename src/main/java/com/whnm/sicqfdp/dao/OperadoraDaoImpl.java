/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.dao;

import com.whnm.sicqfdp.beans.ListOperadora;
import com.whnm.sicqfdp.beans.Operadora;
import com.whnm.sicqfdp.beans.Parametros;
import com.whnm.sicqfdp.interfaces.OperadoraDao;
import com.whnm.sicqfdp.spbeans.SpListarOperadora;
import com.whnm.sicqfdp.spbeans.SpMantOperadora;
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
@Service(value="operadoraDao")
public class OperadoraDaoImpl implements OperadoraDao{
    private DataSource dataSource;
    private SpListarOperadora spListarOperadora;
    private SpMantOperadora spMantOperadora;
    
    @Autowired
    public OperadoraDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.spListarOperadora = new SpListarOperadora(dataSource);
        this.spMantOperadora = new SpMantOperadora(dataSource);
    }
    @Override
    public Operadora mantOperadora(Integer opcCrud, Operadora ope) {
        Operadora operadora = new Operadora();
        Map<String,Object> vars = new HashMap<String,Object>();
        vars.put(SpMantOperadora.PARAM_IN_OPCCRUD, opcCrud);
        vars.put(SpMantOperadora.PARAM_IN_IDOPERADORA, ope.getIdOperadora());
        vars.put(SpMantOperadora.PARAM_IN_DESCOPERADORA, ope.getDenominacion());
        vars.put(SpMantOperadora.PARAM_IN_HABILITADO, ope.getHabilitado());
        try {
            Map<String, Object> result = (Map<String, Object>) spMantOperadora.execute(vars);
            operadora.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            operadora.setMsjError(result.get(Parametros.MSJ).toString());        
        } catch(Exception ex){
            Logger.getLogger(OperadoraDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            operadora.setIndError(1);
            operadora.setMsjError("Error: ["+ex.getMessage()+"]");        
        }
        return operadora;
    }

    @Override
    public ListOperadora listarOperadoras(Integer opcListado, Operadora ope) {
         ListOperadora listaOperadoras = new ListOperadora();
        List<Operadora> operadoras = new ArrayList<>();
        Map<String,Object> vars = new HashMap<String,Object>();
        vars.put(SpListarOperadora.PARAM_IN_OPCLISTADO, opcListado);
        vars.put(SpListarOperadora.PARAM_IN_IDOPERADORA, ope.getIdOperadora());
        vars.put(SpListarOperadora.PARAM_IN_HABILITADO, ope.getHabilitado());
        try {
            Map<String, Object> result = (Map<String, Object>) spListarOperadora.execute(vars);
            List<Map<String, Object>> listResult = (List<Map<String, Object>>) result.get(Parametros.RESULSET);
            listaOperadoras.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            listaOperadoras.setMsjError(result.get(Parametros.MSJ).toString());
            for (Map<String, Object> item : listResult) {
                Operadora oper = new Operadora();
                oper.setIdOperadora(item.get("idOperadora") != null ? Integer.parseInt(String.valueOf(item.get("idOperadora"))) : -1);
                oper.setDenominacion(item.get("NombreOperadora") != null ? String.valueOf(item.get("NombreOperadora")) : "");
                oper.setHabilitado(item.get("habilitado") != null ? Integer.parseInt(String.valueOf(item.get("habilitado"))) : -1);
                operadoras.add(oper);
            }        
        } catch(Exception ex){
            Logger.getLogger(DistritoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            listaOperadoras.setIndError(1);
            listaOperadoras.setMsjError("Error: ["+ex.getMessage()+"]");
        }
        listaOperadoras.setListaOperadoras(operadoras);
        return listaOperadoras;
    }
    
}
