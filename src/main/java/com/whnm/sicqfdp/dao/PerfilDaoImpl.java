/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.dao;

import com.whnm.sicqfdp.beans.CustomUser;
import com.whnm.sicqfdp.beans.ListPerfiles;
import com.whnm.sicqfdp.beans.Parametros;
import com.whnm.sicqfdp.beans.Perfil;
import com.whnm.sicqfdp.interfaces.PerfilDao;
import com.whnm.sicqfdp.spbeans.SpConsultarPerfil;
import com.whnm.sicqfdp.spbeans.SpMantPerfil;
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
@Service
@Qualifier(value="perfilDao")
public class PerfilDaoImpl implements PerfilDao{
    private DataSource dataSource;
    private SpConsultarPerfil spConsultarPerfil;
    private SpMantPerfil spMantPerfil;
    
    @Autowired
    public PerfilDaoImpl(@Qualifier("dataSource") DataSource dataSource) {
        this.dataSource = dataSource;
        this.spConsultarPerfil = new SpConsultarPerfil(dataSource);
        this.spMantPerfil = new SpMantPerfil(dataSource);
        //this.spTraePassword = new SpTraePassword(dataSource);
    }
    
    @Override
    public ListPerfiles listarPerfiles(Integer tipoOperacion, Perfil perfil) {
        ListPerfiles respuesta = new ListPerfiles();
        List<Perfil> lista = new ArrayList<>();
        Map<String,Object> vars = new HashMap<String,Object>();
        vars.put(SpConsultarPerfil.PARAM_IN_OPC, tipoOperacion);
        vars.put(SpConsultarPerfil.PARAM_IN_ID_PERFIL, perfil.getIdPerfil());
        vars.put(SpConsultarPerfil.PARAM_IN_ESTADO, perfil.getEstado());
        try {
            Map<String, Object> result = (Map<String, Object>) spConsultarPerfil.execute(vars);
            List<Map<String, Object>> listResult = (List<Map<String, Object>>) result.get(Parametros.RESULSET);
            respuesta.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            respuesta.setMsjError(result.get(Parametros.MSJ).toString());
            for (Map<String, Object> item : listResult) {
                Perfil elem = new Perfil();
                elem.setIdPerfil(item.get("idPerfil") != null ? Integer.parseInt(String.valueOf(item.get("idPerfil"))) : -1);
                elem.setDescripcion(item.get("descripcion") != null ? String.valueOf(item.get("descripcion")) : "");
                elem.setEstado(item.get("habilitado") != null ? Integer.parseInt(String.valueOf(item.get("habilitado"))) : -1);
                lista.add(elem);
            }        
        } catch(Exception ex){
            Logger.getLogger(PerfilDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            respuesta.setIndError(1);
            respuesta.setMsjError("Error: ["+ex.getMessage()+"]");
        }
        respuesta.setListPerfil(lista);
        return respuesta;
    }

    @Override
    public Perfil grabar(Perfil elemento, CustomUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Perfil editar(Perfil elemento, CustomUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Perfil eliminar(Perfil elemento, CustomUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Perfil listar(Perfil elemento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Perfil mantPerfiles(Integer tipoOperacion, Perfil perfil, CustomUser user) {
        Perfil respuesta = new Perfil();
        Map<String,Object> vars = new HashMap<String,Object>();
        vars.put(SpMantPerfil.PARAM_IN_OPC, tipoOperacion);
        vars.put(SpMantPerfil.PARAM_IN_ID_PERFIL, perfil.getIdPerfil());
        vars.put(SpMantPerfil.PARAM_IN_DESCRIPCION, perfil.getDescripcion());
        vars.put(SpMantPerfil.PARAM_IN_ESTADO, perfil.getEstado());
        vars.put(SpMantPerfil.PARAM_IN_USUARIO, user.getUsername());
        try {
            Map<String, Object> result = (Map<String, Object>) spMantPerfil.execute(vars);
            respuesta.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            respuesta.setMsjError(result.get(Parametros.MSJ).toString());      
        } catch(Exception ex){
            Logger.getLogger(PerfilDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            respuesta.setIndError(1);
            respuesta.setMsjError("Error: ["+ex.getMessage()+"]");
        }
        return respuesta;
    }
    
}
