/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.dao;

import com.whnm.sicqfdp.beans.Departamento;
import com.whnm.sicqfdp.beans.ListDepartamento;
import com.whnm.sicqfdp.beans.ListProvincia;
import com.whnm.sicqfdp.beans.Parametros;
import com.whnm.sicqfdp.beans.Provincia;
import com.whnm.sicqfdp.interfaces.DepartamentoDao;
import com.whnm.sicqfdp.spbeans.SpEditarDepartamento;
import com.whnm.sicqfdp.spbeans.SpEliminarDepartamento;
import com.whnm.sicqfdp.spbeans.SpEliminarProvinciaDepartamento;
import com.whnm.sicqfdp.spbeans.SpGrabarDepartamento;
import com.whnm.sicqfdp.spbeans.SpGrabarProvinciaDepartamento;
import com.whnm.sicqfdp.spbeans.SpListarDepartamento;
import com.whnm.sicqfdp.spbeans.SpListarDepartamentosHabilitados;
import com.whnm.sicqfdp.spbeans.SpListarProvinciasDepartamento;
import com.whnm.sicqfdp.spbeans.SpListarTodosDepartamentos;
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
@Service("departamentoDao")
public class DepartamentoDaoImpl implements DepartamentoDao{
    private DataSource dataSource;
    private SpListarDepartamento spListarDepartamento;
    private SpListarTodosDepartamentos spListarTodosDepartamentos;
    private SpGrabarDepartamento spGrabarDepartamento;
    private SpEditarDepartamento spEditarDepartamento;
    private SpEliminarDepartamento spEliminarDepartamento;
    private SpListarProvinciasDepartamento spListarProvinciasDepartamento;
    private SpGrabarProvinciaDepartamento spGrabarProvinciaDepartamento;
    private SpEliminarProvinciaDepartamento spEliminarProvinciaDepartamento;
    private SpListarDepartamentosHabilitados spListarDepartamentosHabilitados;

    @Autowired
    public DepartamentoDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.spListarDepartamento = new SpListarDepartamento(dataSource);
        this.spListarTodosDepartamentos = new SpListarTodosDepartamentos(dataSource);
        this.spGrabarDepartamento = new SpGrabarDepartamento(dataSource);
        this.spEditarDepartamento = new SpEditarDepartamento(dataSource);
        this.spEliminarDepartamento = new SpEliminarDepartamento(dataSource);
        this.spListarProvinciasDepartamento = new SpListarProvinciasDepartamento(dataSource);
        this.spGrabarProvinciaDepartamento = new SpGrabarProvinciaDepartamento(dataSource);
        this.spEliminarProvinciaDepartamento = new SpEliminarProvinciaDepartamento(dataSource);
        this.spListarDepartamentosHabilitados = new SpListarDepartamentosHabilitados(dataSource);
    }
    @Override
    public ListDepartamento listarAllDepartamentos() {
        ListDepartamento listaDepartamentos = new ListDepartamento();
        List<Departamento> provincia = new ArrayList<>();
        try {
            Map<String, Object> result = (Map<String, Object>) spListarTodosDepartamentos.execute();
            List<Map<String, Object>> listResult = (List<Map<String, Object>>) result.get(Parametros.RESULSET);
            listaDepartamentos.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            listaDepartamentos.setMsjError(result.get(Parametros.MSJ).toString());
            for (Map<String, Object> item : listResult) {
                Departamento prov = new Departamento();
                prov.setIdDepartamento(item.get("idDepartamento") != null ? Integer.parseInt(String.valueOf(item.get("idDepartamento"))) : -1);
                prov.setNombreDepartamento(item.get("NombreDepartamento") != null ? String.valueOf(item.get("NombreDepartamento")) : "");
                prov.setHabilitado(item.get("Habilitado") != null ? Integer.parseInt(String.valueOf(item.get("Habilitado"))) : -1);
                provincia.add(prov);
            }        
        } catch(Exception ex){
            Logger.getLogger(DepartamentoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            listaDepartamentos.setIndError(1);
            listaDepartamentos.setMsjError("Error: ["+ex.getMessage()+"]");
        }
        listaDepartamentos.setListaDepartamentos(provincia);
        return listaDepartamentos;
    }

    @Override
    public Departamento grabar(Departamento elemento) {
         Departamento distrito = new Departamento();
        Map<String,Object> vars = new HashMap<String,Object>();
        vars.put(SpGrabarDepartamento.PARAM_IN_DESCDEPARTAMENTO, elemento.getNombreDepartamento().toUpperCase());
        vars.put(SpGrabarDepartamento.PARAM_IN_HABILITADO, elemento.getHabilitado());
        try {
            Map<String, Object> result = (Map<String, Object>) spGrabarDepartamento.execute(vars);
            distrito.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            distrito.setMsjError(result.get(Parametros.MSJ).toString());        
        } catch(Exception ex){
            Logger.getLogger(DepartamentoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            distrito.setIndError(1);
            distrito.setMsjError("Error: ["+ex.getMessage()+"]");        
        }
        return distrito;
    }

    @Override
    public Departamento editar(Departamento elemento) {
        Departamento distrito = new Departamento();
        Map<String,Object> vars = new HashMap<String,Object>();
        vars.put(SpEditarDepartamento.PARAM_IN_CODDEPARTAMENTO, elemento.getIdDepartamento());
        vars.put(SpEditarDepartamento.PARAM_IN_DESCDEPARTAMENTO, elemento.getNombreDepartamento().toUpperCase());
        vars.put(SpEditarDepartamento.PARAM_IN_HABILITADO, elemento.getHabilitado());
        try {
            Map<String, Object> result = (Map<String, Object>) spEditarDepartamento.execute(vars);
            distrito.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            distrito.setMsjError(result.get(Parametros.MSJ).toString());        
        } catch(Exception ex){
            Logger.getLogger(DepartamentoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            distrito.setIndError(1);
            distrito.setMsjError("Error: ["+ex.getMessage()+"]");        
        }
        return distrito;
    }

    @Override
    public Departamento eliminar(Departamento elemento) {
        Departamento distrito = new Departamento();
        Map<String,Object> vars = new HashMap<String,Object>();
        vars.put(SpEliminarDepartamento.PARAM_IN_CODDEPARTAMENTO, elemento.getIdDepartamento());
        try {
            Map<String, Object> result = (Map<String, Object>) spEliminarDepartamento.execute(vars);
           distrito.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            distrito.setMsjError(result.get(Parametros.MSJ).toString());        
        } catch(Exception ex){
            Logger.getLogger(DepartamentoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            distrito.setIndError(1);
            distrito.setMsjError("Error: ["+ex.getMessage()+"]");        
        }
        return distrito;
    }

    @Override
    public Departamento listar(Departamento elemento) {
        Departamento distrito = new Departamento();
        Map<String,Object> vars = new HashMap<String,Object>();
        vars.put(SpListarDepartamento.PARAM_IN_CODDEPARTAMENTO, elemento.getIdDepartamento());
        try {
            Map<String, Object> result = (Map<String, Object>) spListarDepartamento.execute(vars);
            List<Map<String, Object>> listResult = (List<Map<String, Object>>) result.get(Parametros.RESULSET);
            distrito.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            distrito.setMsjError(result.get(Parametros.MSJ).toString());
            for (Map<String, Object> item : listResult) {
                distrito.setIdDepartamento(item.get("idDepartamento") != null ? Integer.parseInt(String.valueOf(item.get("idDepartamento"))) : -1);
                distrito.setNombreDepartamento(item.get("NombreDepartamento") != null ? String.valueOf(item.get("NombreDepartamento")) : "");
                distrito.setHabilitado(item.get("Habilitado") != null ? Integer.parseInt(String.valueOf(item.get("Habilitado"))) : -1);
            }        
        } catch(Exception ex){
            Logger.getLogger(DepartamentoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            distrito.setIndError(1);
            distrito.setMsjError("Error: ["+ex.getMessage()+"]");        
        }
        return distrito;
    }

    @Override
    public ListProvincia listarProvinciaDeDepartamento(Departamento elemento) {
        ListProvincia listaProvincias = new ListProvincia();
        List<Provincia> provincias = new ArrayList<>();
        Map<String,Object> vars = new HashMap<String,Object>();
        vars.put(SpListarProvinciasDepartamento.PARAM_IN_CODDEPARTAMENTO, elemento.getIdDepartamento());
        try {
            Map<String, Object> result = (Map<String, Object>) spListarProvinciasDepartamento.execute(vars);
            List<Map<String, Object>> listResult = (List<Map<String, Object>>) result.get(Parametros.RESULSET);
            listaProvincias.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            listaProvincias.setMsjError(result.get(Parametros.MSJ).toString());
            for (Map<String, Object> item : listResult) {
                Provincia prov = new Provincia();
                prov.setIdProvincia(item.get("idProvincia") != null ? Integer.parseInt(String.valueOf(item.get("idProvincia"))) : -1);
                prov.setNombreProvincia(item.get("NombreProvincia") != null ? String.valueOf(item.get("NombreProvincia")) : "");
                provincias.add(prov);
            }        
        } catch(Exception ex){
            Logger.getLogger(DepartamentoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            listaProvincias.setIndError(1);
            listaProvincias.setMsjError("Error: ["+ex.getMessage()+"]");
        }
        listaProvincias.setListaProvincias(provincias);
        return listaProvincias;
    }

    @Override
    public Departamento grabarProvinciaDeDepartamento(Departamento elemento, Provincia elemento2) {
        Departamento departamento = new Departamento();
        Map<String,Object> vars = new HashMap<String,Object>();
        vars.put(SpGrabarProvinciaDepartamento.PARAM_IN_CODDEPARTAMENTO, elemento.getIdDepartamento());
        vars.put(SpGrabarProvinciaDepartamento.PARAM_IN_CODPROVINCIA, elemento2.getIdProvincia());
        try {
            Map<String, Object> result = (Map<String, Object>) spGrabarProvinciaDepartamento.execute(vars);
            departamento.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            departamento.setMsjError(result.get(Parametros.MSJ).toString());        
        } catch(Exception ex){
            Logger.getLogger(DepartamentoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            departamento.setIndError(1);
            departamento.setMsjError("Error: ["+ex.getMessage()+"]");        
        }
        return departamento;
    }

    @Override
    public Departamento eliminarProvinciaDeDepartamento(Departamento elemento, Provincia elemento2) {
        Departamento departamento = new Departamento();
        Map<String,Object> vars = new HashMap<String,Object>();
        vars.put(SpEliminarProvinciaDepartamento.PARAM_IN_CODDEPARTAMENTO, elemento.getIdDepartamento());
        vars.put(SpEliminarProvinciaDepartamento.PARAM_IN_CODPROVINCIA, elemento2.getIdProvincia());
        try {
            Map<String, Object> result = (Map<String, Object>) spEliminarProvinciaDepartamento.execute(vars);
            departamento.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            departamento.setMsjError(result.get(Parametros.MSJ).toString());        
        } catch(Exception ex){
            Logger.getLogger(DepartamentoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            departamento.setIndError(1);
            departamento.setMsjError("Error: ["+ex.getMessage()+"]");        
        }
        return departamento;
    }

    @Override
    public ListDepartamento listarDepartamentosHabilitadas(Departamento elemento) {
        ListDepartamento listaDepartamentos = new ListDepartamento();
        List<Departamento> departamento = new ArrayList<>();
        Map<String,Object> vars = new HashMap<String,Object>();
        vars.put(SpListarDepartamentosHabilitados.PARAM_IN_HABILITADO, elemento.getHabilitado());
        try {
            Map<String, Object> result = (Map<String, Object>) spListarDepartamentosHabilitados.execute(vars);
            List<Map<String, Object>> listResult = (List<Map<String, Object>>) result.get(Parametros.RESULSET);
            listaDepartamentos.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            listaDepartamentos.setMsjError(result.get(Parametros.MSJ).toString());
            for (Map<String, Object> item : listResult) {
                Departamento dep = new Departamento();
                dep.setIdDepartamento(item.get("idDepartamento") != null ? Integer.parseInt(String.valueOf(item.get("idDepartamento"))) : -1);
                dep.setNombreDepartamento(item.get("NombreDepartamento") != null ? String.valueOf(item.get("NombreDepartamento")) : "");
                departamento.add(dep);
            }        
        } catch(Exception ex){
            Logger.getLogger(DepartamentoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            listaDepartamentos.setIndError(1);
            listaDepartamentos.setMsjError("Error: ["+ex.getMessage()+"]");
        }
        listaDepartamentos.setListaDepartamentos(departamento);
        return listaDepartamentos;
    }
    
    
}
