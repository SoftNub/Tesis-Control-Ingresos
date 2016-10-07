/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.dao;

import com.whnm.sicqfdp.beans.Distrito;
import com.whnm.sicqfdp.beans.DistritoList;
import com.whnm.sicqfdp.beans.Provincia;
import com.whnm.sicqfdp.beans.ListProvincia;
import com.whnm.sicqfdp.beans.Parametros;
import com.whnm.sicqfdp.interfaces.ProvinciaDao;
import com.whnm.sicqfdp.spbeans.SpEditarProvincia;
import com.whnm.sicqfdp.spbeans.SpEliminarDistritoProvincia;
import com.whnm.sicqfdp.spbeans.SpEliminarProvincia;
import com.whnm.sicqfdp.spbeans.SpGrabarDistritoProvincia;
import com.whnm.sicqfdp.spbeans.SpGrabarProvincia;
import com.whnm.sicqfdp.spbeans.SpListarDistritosProvincia;
import com.whnm.sicqfdp.spbeans.SpListarProvincia;
import com.whnm.sicqfdp.spbeans.SpListarProvinciasHabilitados;
import com.whnm.sicqfdp.spbeans.SpListarTodasProvincias;
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
@Service("provinciaDao")
public class ProvinciaDaoImpl implements ProvinciaDao{
    private DataSource dataSource;
    private SpListarProvincia spListarProvincia;
    private SpListarTodasProvincias spListarTodosProvincia;
    private SpGrabarProvincia spGrabarProvincia;
    private SpEditarProvincia spEditarProvincia;
    private SpEliminarProvincia spEliminarProvincia;
    private SpListarDistritosProvincia spListarDistritoProvincia;
    private SpGrabarDistritoProvincia spGrabarDistritoProvincia;
    private SpEliminarDistritoProvincia spEliminarDistritoProvincia;
    private SpListarProvinciasHabilitados spListarProvinciasHabilitados;

    @Autowired
    public ProvinciaDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.spListarProvincia = new SpListarProvincia(dataSource);
        this.spListarTodosProvincia = new SpListarTodasProvincias(dataSource);
        this.spGrabarProvincia = new SpGrabarProvincia(dataSource);
        this.spEditarProvincia = new SpEditarProvincia(dataSource);
        this.spEliminarProvincia = new SpEliminarProvincia(dataSource);
        this.spListarDistritoProvincia = new SpListarDistritosProvincia(dataSource);
        this.spGrabarDistritoProvincia = new SpGrabarDistritoProvincia(dataSource);
        this.spEliminarDistritoProvincia = new SpEliminarDistritoProvincia(dataSource);
        this.spListarProvinciasHabilitados = new SpListarProvinciasHabilitados(dataSource);
    }
    @Override
    public ListProvincia listarAllProvincias() {
        ListProvincia listaProvincias = new ListProvincia();
        List<Provincia> provincia = new ArrayList<>();
        try {
            Map<String, Object> result = (Map<String, Object>) spListarTodosProvincia.execute();
            List<Map<String, Object>> listResult = (List<Map<String, Object>>) result.get(Parametros.RESULSET);
            listaProvincias.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            listaProvincias.setMsjError(result.get(Parametros.MSJ).toString());
            for (Map<String, Object> item : listResult) {
                Provincia prov = new Provincia();
                prov.setIdProvincia(item.get("idProvincia") != null ? Integer.parseInt(String.valueOf(item.get("idProvincia"))) : -1);
                prov.setNombreProvincia(item.get("NombreProvincia") != null ? String.valueOf(item.get("NombreProvincia")) : "");
                prov.setHabilitado(item.get("Habilitado") != null ? Integer.parseInt(String.valueOf(item.get("Habilitado"))) : -1);
                provincia.add(prov);
            }        
        } catch(Exception ex){
            Logger.getLogger(ProvinciaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            listaProvincias.setIndError(1);
            listaProvincias.setMsjError("Error: ["+ex.getMessage()+"]");
        }
        listaProvincias.setListaProvincias(provincia);
        return listaProvincias;
    }

    @Override
    public Provincia grabar(Provincia elemento) {
         Provincia distrito = new Provincia();
        Map<String,Object> vars = new HashMap<String,Object>();
        vars.put(SpGrabarProvincia.PARAM_IN_DESCPROVINCIA, elemento.getNombreProvincia().toUpperCase());
        vars.put(SpGrabarProvincia.PARAM_IN_HABILITADO, elemento.getHabilitado());
        try {
            Map<String, Object> result = (Map<String, Object>) spGrabarProvincia.execute(vars);
            distrito.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            distrito.setMsjError(result.get(Parametros.MSJ).toString());        
        } catch(Exception ex){
            Logger.getLogger(ProvinciaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            distrito.setIndError(1);
            distrito.setMsjError("Error: ["+ex.getMessage()+"]");        
        }
        return distrito;
    }

    @Override
    public Provincia editar(Provincia elemento) {
        Provincia distrito = new Provincia();
        Map<String,Object> vars = new HashMap<String,Object>();
        vars.put(SpEditarProvincia.PARAM_IN_CODPROVINCIA, elemento.getIdProvincia());
        vars.put(SpEditarProvincia.PARAM_IN_DESCPROVINCIA, elemento.getNombreProvincia().toUpperCase());
        vars.put(SpEditarProvincia.PARAM_IN_HABILITADO, elemento.getHabilitado());
        try {
            Map<String, Object> result = (Map<String, Object>) spEditarProvincia.execute(vars);
            distrito.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            distrito.setMsjError(result.get(Parametros.MSJ).toString());        
        } catch(Exception ex){
            Logger.getLogger(ProvinciaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            distrito.setIndError(1);
            distrito.setMsjError("Error: ["+ex.getMessage()+"]");        
        }
        return distrito;
    }

    @Override
    public Provincia eliminar(Provincia elemento) {
        Provincia distrito = new Provincia();
        Map<String,Object> vars = new HashMap<String,Object>();
        vars.put(SpEliminarProvincia.PARAM_IN_CODPROVINCIA, elemento.getIdProvincia());
        try {
            Map<String, Object> result = (Map<String, Object>) spEliminarProvincia.execute(vars);
           distrito.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            distrito.setMsjError(result.get(Parametros.MSJ).toString());        
        } catch(Exception ex){
            Logger.getLogger(ProvinciaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            distrito.setIndError(1);
            distrito.setMsjError("Error: ["+ex.getMessage()+"]");        
        }
        return distrito;
    }

    @Override
    public Provincia listar(Provincia elemento) {
        Provincia distrito = new Provincia();
        Map<String,Object> vars = new HashMap<String,Object>();
        vars.put(SpListarProvincia.PARAM_IN_CODPROVINCIA, elemento.getIdProvincia());
        try {
            Map<String, Object> result = (Map<String, Object>) spListarProvincia.execute(vars);
            List<Map<String, Object>> listResult = (List<Map<String, Object>>) result.get(Parametros.RESULSET);
            distrito.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            distrito.setMsjError(result.get(Parametros.MSJ).toString());
            for (Map<String, Object> item : listResult) {
                distrito.setIdProvincia(item.get("idProvincia") != null ? Integer.parseInt(String.valueOf(item.get("idProvincia"))) : -1);
                distrito.setNombreProvincia(item.get("NombreProvincia") != null ? String.valueOf(item.get("NombreProvincia")) : "");
                distrito.setHabilitado(item.get("Habilitado") != null ? Integer.parseInt(String.valueOf(item.get("Habilitado"))) : -1);
            }        
        } catch(Exception ex){
            Logger.getLogger(ProvinciaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            distrito.setIndError(1);
            distrito.setMsjError("Error: ["+ex.getMessage()+"]");        
        }
        return distrito;
    }

    @Override
    public DistritoList listarDistritosDeProvincia(Provincia elemento) {
        DistritoList listaDistritos = new DistritoList();
        List<Distrito> distritos = new ArrayList<>();
        Map<String,Object> vars = new HashMap<String,Object>();
        vars.put(SpListarDistritosProvincia.PARAM_IN_CODPROVINCIA, elemento.getIdProvincia());
        try {
            Map<String, Object> result = (Map<String, Object>) spListarDistritoProvincia.execute(vars);
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
            Logger.getLogger(ProvinciaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            listaDistritos.setIndError(1);
            listaDistritos.setMsjError("Error: ["+ex.getMessage()+"]");
        }
        listaDistritos.setListaDistritos(distritos);
        return listaDistritos;
    }

    @Override
    public Provincia grabarDistritoDeProvincia(Provincia elemento, Distrito elemento2) {
        Provincia distrito = new Provincia();
        Map<String,Object> vars = new HashMap<String,Object>();
        vars.put(SpGrabarDistritoProvincia.PARAM_IN_CODPROVINCIA, elemento.getIdProvincia());
        vars.put(SpGrabarDistritoProvincia.PARAM_IN_CODDISTRITO, elemento2.getIdDistrito());
        try {
            Map<String, Object> result = (Map<String, Object>) spGrabarDistritoProvincia.execute(vars);
            distrito.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            distrito.setMsjError(result.get(Parametros.MSJ).toString());        
        } catch(Exception ex){
            Logger.getLogger(ProvinciaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            distrito.setIndError(1);
            distrito.setMsjError("Error: ["+ex.getMessage()+"]");        
        }
        return distrito;
    }

    @Override
    public Provincia eliminarDistritoDeProvincia(Provincia elemento, Distrito elemento2) {
        Provincia distrito = new Provincia();
        Map<String,Object> vars = new HashMap<String,Object>();
        vars.put(SpEliminarDistritoProvincia.PARAM_IN_CODPROVINCIA, elemento.getIdProvincia());
        vars.put(SpEliminarDistritoProvincia.PARAM_IN_CODDISTRITO, elemento2.getIdDistrito());
        try {
            Map<String, Object> result = (Map<String, Object>) spEliminarDistritoProvincia.execute(vars);
            distrito.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            distrito.setMsjError(result.get(Parametros.MSJ).toString());        
        } catch(Exception ex){
            Logger.getLogger(ProvinciaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            distrito.setIndError(1);
            distrito.setMsjError("Error: ["+ex.getMessage()+"]");        
        }
        return distrito;
    }

    @Override
    public ListProvincia listarProvinciasHabilitadas(Provincia elemento) {
        ListProvincia listaProvincias = new ListProvincia();
        List<Provincia> provincia = new ArrayList<>();
        Map<String,Object> vars = new HashMap<String,Object>();
        vars.put(SpListarProvinciasHabilitados.PARAM_IN_HABILITADO, elemento.getHabilitado());
        try {
            Map<String, Object> result = (Map<String, Object>) spListarProvinciasHabilitados.execute(vars);
            List<Map<String, Object>> listResult = (List<Map<String, Object>>) result.get(Parametros.RESULSET);
            listaProvincias.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            listaProvincias.setMsjError(result.get(Parametros.MSJ).toString());
            for (Map<String, Object> item : listResult) {
                Provincia prov = new Provincia();
                prov.setIdProvincia(item.get("idProvincia") != null ? Integer.parseInt(String.valueOf(item.get("idProvincia"))) : -1);
                prov.setNombreProvincia(item.get("NombreProvincia") != null ? String.valueOf(item.get("NombreProvincia")) : "");
                provincia.add(prov);
            }        
        } catch(Exception ex){
            Logger.getLogger(ProvinciaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            listaProvincias.setIndError(1);
            listaProvincias.setMsjError("Error: ["+ex.getMessage()+"]");
        }
        listaProvincias.setListaProvincias(provincia);
        return listaProvincias;
    }
    
    
}
