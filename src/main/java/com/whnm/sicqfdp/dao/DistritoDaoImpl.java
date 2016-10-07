/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.dao;

import com.whnm.sicqfdp.beans.Distrito;
import com.whnm.sicqfdp.beans.DistritoList;
import com.whnm.sicqfdp.beans.Parametros;
import com.whnm.sicqfdp.interfaces.DistritoDao;
import com.whnm.sicqfdp.spbeans.SpEditarDistrito;
import com.whnm.sicqfdp.spbeans.SpEliminarDistrito;
import com.whnm.sicqfdp.spbeans.SpGrabarDistrito;
import com.whnm.sicqfdp.spbeans.SpListarDistritos;
import com.whnm.sicqfdp.spbeans.SpListarDistritosHabilitados;
import com.whnm.sicqfdp.spbeans.SpListarTodosDistritos;
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
@Service(value="distritoDao")
public class DistritoDaoImpl implements DistritoDao{
    private DataSource dataSource;
    private SpListarDistritos spListarDistrito;
    private SpListarTodosDistritos spListarTodosDistrito;
    private SpListarDistritosHabilitados spListarDistritosHabilitados;
    private SpGrabarDistrito spGrabarDistrito;
    private SpEditarDistrito spEditarDistrito;
    private SpEliminarDistrito spEliminarDistrito;

    @Autowired
    public DistritoDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.spListarDistrito = new SpListarDistritos(dataSource);
        this.spListarTodosDistrito = new SpListarTodosDistritos(dataSource);
        this.spListarDistritosHabilitados = new SpListarDistritosHabilitados(dataSource);
        this.spGrabarDistrito = new SpGrabarDistrito(dataSource);
        this.spEditarDistrito = new SpEditarDistrito(dataSource);
        this.spEliminarDistrito = new SpEliminarDistrito(dataSource);
    }

    @Override
    public DistritoList listarTodosDistritos() {
        DistritoList listaDistritos = new DistritoList();
        List<Distrito> distritos = new ArrayList<>();
        try {
            Map<String, Object> result = (Map<String, Object>) spListarTodosDistrito.execute();
            List<Map<String, Object>> listResult = (List<Map<String, Object>>) result.get(Parametros.RESULSET);
            listaDistritos.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            listaDistritos.setMsjError(result.get(Parametros.MSJ).toString());
            for (Map<String, Object> item : listResult) {
                Distrito dis = new Distrito();
                dis.setIdDistrito(item.get("idDistrito") != null ? Integer.parseInt(String.valueOf(item.get("idDistrito"))) : -1);
                dis.setNombreDistrito(item.get("NombreDistrito") != null ? String.valueOf(item.get("NombreDistrito")) : "");
                dis.setHabilitado(item.get("Habilitado") != null ? Integer.parseInt(String.valueOf(item.get("Habilitado"))) : -1);
                distritos.add(dis);
            }        
        } catch(Exception ex){
            Logger.getLogger(DistritoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            listaDistritos.setIndError(1);
            listaDistritos.setMsjError("Error: ["+ex.getMessage()+"]");
        }
        listaDistritos.setListaDistritos(distritos);
        return listaDistritos;
    }

    @Override
    public Distrito grabar(Distrito elemento) {
        Distrito distrito = new Distrito();
        Map<String,Object> vars = new HashMap<String,Object>();
        vars.put(SpGrabarDistrito.PARAM_IN_DESCDISTRITO, elemento.getNombreDistrito().toUpperCase());
        vars.put(SpGrabarDistrito.PARAM_IN_HABILITADO, elemento.getHabilitado());
        try {
            Map<String, Object> result = (Map<String, Object>) spGrabarDistrito.execute(vars);
            distrito.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            distrito.setMsjError(result.get(Parametros.MSJ).toString());        
        } catch(Exception ex){
            Logger.getLogger(DistritoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            distrito.setIndError(1);
            distrito.setMsjError("Error: ["+ex.getMessage()+"]");        
        }
        return distrito;
    }

    @Override
    public Distrito editar(Distrito elemento) {
        Distrito distrito = new Distrito();
        Map<String,Object> vars = new HashMap<String,Object>();
        vars.put(SpEditarDistrito.PARAM_IN_CODDISTRITO, elemento.getIdDistrito());
        vars.put(SpEditarDistrito.PARAM_IN_DESCDISTRITO, elemento.getNombreDistrito().toUpperCase());
        vars.put(SpEditarDistrito.PARAM_IN_HABILITADO, elemento.getHabilitado());
        try {
            Map<String, Object> result = (Map<String, Object>) spEditarDistrito.execute(vars);
            distrito.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            distrito.setMsjError(result.get(Parametros.MSJ).toString());        
        } catch(Exception ex){
            Logger.getLogger(DistritoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            distrito.setIndError(1);
            distrito.setMsjError("Error: ["+ex.getMessage()+"]");        
        }
        return distrito;
    }

    @Override
    public Distrito eliminar(Distrito elemento) {
        Distrito distrito = new Distrito();
        Map<String,Object> vars = new HashMap<String,Object>();
        vars.put(SpEliminarDistrito.PARAM_IN_CODDISTRITO, elemento.getIdDistrito());
        try {
            Map<String, Object> result = (Map<String, Object>) spEliminarDistrito.execute(vars);
           distrito.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            distrito.setMsjError(result.get(Parametros.MSJ).toString());        
        } catch(Exception ex){
            Logger.getLogger(DistritoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            distrito.setIndError(1);
            distrito.setMsjError("Error: ["+ex.getMessage()+"]");        
        }
        return distrito;
    }

    @Override
    public Distrito listar(Distrito elemento) {
        Distrito distrito = new Distrito();
        Map<String,Object> vars = new HashMap<String,Object>();
        vars.put(SpListarDistritos.PARAM_IN_CODDISTRITO, elemento.getIdDistrito());
        try {
            Map<String, Object> result = (Map<String, Object>) spListarDistrito.execute(vars);
            List<Map<String, Object>> listResult = (List<Map<String, Object>>) result.get(Parametros.RESULSET);
            distrito.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            distrito.setMsjError(result.get(Parametros.MSJ).toString());
            for (Map<String, Object> item : listResult) {
                distrito.setIdDistrito(item.get("idDistrito") != null ? Integer.parseInt(String.valueOf(item.get("idDistrito"))) : -1);
                distrito.setNombreDistrito(item.get("NombreDistrito") != null ? String.valueOf(item.get("NombreDistrito")) : "");
                distrito.setHabilitado(item.get("Habilitado") != null ? Integer.parseInt(String.valueOf(item.get("Habilitado"))) : -1);
            }        
        } catch(Exception ex){
            Logger.getLogger(DistritoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            distrito.setIndError(1);
            distrito.setMsjError("Error: ["+ex.getMessage()+"]");        
        }
        return distrito;
    }

    @Override
    public DistritoList listarDistritosHabilitados(Distrito elemento) {
        DistritoList listaDistritos = new DistritoList();
        List<Distrito> distritos = new ArrayList<>();
        Map<String,Object> vars = new HashMap<String,Object>();
        vars.put(SpListarDistritosHabilitados.PARAM_IN_HABILITADO, elemento.getHabilitado());
        try {
            Map<String, Object> result = (Map<String, Object>) spListarDistritosHabilitados.execute(vars);
            List<Map<String, Object>> listResult = (List<Map<String, Object>>) result.get(Parametros.RESULSET);
            listaDistritos.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            listaDistritos.setMsjError(result.get(Parametros.MSJ).toString());
            for (Map<String, Object> item : listResult) {
                Distrito dis = new Distrito();
                dis.setIdDistrito(item.get("idDistrito") != null ? Integer.parseInt(String.valueOf(item.get("idDistrito"))) : -1);
                dis.setNombreDistrito(item.get("NombreDistrito") != null ? String.valueOf(item.get("NombreDistrito")) : "");
                distritos.add(dis);
            }        
        } catch(Exception ex){
            Logger.getLogger(DistritoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            listaDistritos.setIndError(1);
            listaDistritos.setMsjError("Error: ["+ex.getMessage()+"]");
        }
        listaDistritos.setListaDistritos(distritos);
        return listaDistritos;
    }
}
