/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.dao;

import com.whnm.sicqfdp.beans.CustomUser;
import com.whnm.sicqfdp.beans.ListMenu;
import com.whnm.sicqfdp.beans.Menu;
import com.whnm.sicqfdp.beans.Parametros;
import com.whnm.sicqfdp.beans.Perfil;
import com.whnm.sicqfdp.beans.Usuario;
import com.whnm.sicqfdp.interfaces.MenuDao;
import com.whnm.sicqfdp.spbeans.SpConsultarMenu;
import com.whnm.sicqfdp.spbeans.SpGenerarMenu;
import com.whnm.sicqfdp.spbeans.SpMantMenu;
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
@Qualifier(value="menuDao")
public class MenuDaoImpl implements MenuDao{
    private DataSource dataSource;
    private SpConsultarMenu spConsultarMenu;
    private SpMantMenu spMantMenu;
    private SpGenerarMenu spGenerarMenu;
    
    @Autowired
    public MenuDaoImpl(@Qualifier("dataSource") DataSource dataSource) {
        this.dataSource = dataSource;
        this.spConsultarMenu = new SpConsultarMenu(dataSource);
        this.spMantMenu = new SpMantMenu(dataSource);
        this.spGenerarMenu = new SpGenerarMenu(dataSource);
    }
    
    @Override
    public ListMenu listaMenu(Integer tipoOpe, Perfil perfil, Menu menu, Usuario usuario) {
        ListMenu respuesta = new ListMenu();
        List<Menu> lista = new ArrayList<>();
        Map<String,Object> vars = new HashMap<String,Object>();
        vars.put(SpConsultarMenu.PARAM_IN_OPC, tipoOpe);
        vars.put(SpConsultarMenu.PARAM_IN_ID_MENU, menu.getIdMenu());
        vars.put(SpConsultarMenu.PARAM_IN_ID_PERFIL, perfil.getIdPerfil());
        vars.put(SpConsultarMenu.PARAM_IN_USERNAME, usuario.getUsername());
        try {
            Map<String, Object> result = (Map<String, Object>) spConsultarMenu.execute(vars);
            List<Map<String, Object>> listResult = (List<Map<String, Object>>) result.get(Parametros.RESULSET);
            respuesta.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            respuesta.setMsjError(result.get(Parametros.MSJ).toString());
            for (Map<String, Object> item : listResult) {
                Menu elem = new Menu();
                elem.setIdMenu(item.get("idMenu") != null ? Integer.parseInt(String.valueOf(item.get("idMenu"))) : -1);
                elem.setIdRol(item.get("idRol") != null ? Integer.parseInt(String.valueOf(item.get("idRol"))) : -1);
                elem.setDescripMenu(item.get("menu") != null ? String.valueOf(item.get("menu")) : "");
                elem.setDescripRol(item.get("rol") != null ? String.valueOf(item.get("rol")) : "");
                elem.setEstado(item.get("estado") != null ? Integer.parseInt(String.valueOf(item.get("estado"))) : -1);
                lista.add(elem);
            }        
        } catch(Exception ex){
            Logger.getLogger(MenuDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            respuesta.setIndError(1);
            respuesta.setMsjError("Error: ["+ex.getMessage()+"]");
        }
        respuesta.setListMenus(lista);
        return respuesta;
    }

    @Override
    public Menu grabar(Menu elemento, CustomUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Menu editar(Menu elemento, CustomUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Menu eliminar(Menu elemento, CustomUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Menu listar(Menu elemento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Menu mantMenu(Integer tipoOpe, Perfil perfil, Menu menu, Usuario usuario,
            CustomUser user) {
        Menu respuesta = new Menu();
        Map<String,Object> vars = new HashMap<String,Object>();
        vars.put(SpMantMenu.PARAM_IN_OPC, tipoOpe);
        vars.put(SpMantMenu.PARAM_IN_ID_PERFIL, perfil.getIdPerfil());
        vars.put(SpMantMenu.PARAM_IN_ID_MENU, menu.getIdMenu());
        vars.put(SpMantMenu.PARAM_IN_USUARIO_MENU, usuario.getUsername());
        vars.put(SpMantMenu.PARAM_IN_USUARIO, user.getUsername());
        try {
            Map<String, Object> result = (Map<String, Object>) spMantMenu.execute(vars);
            respuesta.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            respuesta.setMsjError(result.get(Parametros.MSJ).toString());      
        } catch(Exception ex){
            Logger.getLogger(PerfilDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            respuesta.setIndError(1);
            respuesta.setMsjError("Error: ["+ex.getMessage()+"]");
        }
        return respuesta;
    }

    @Override
    public Menu generarMenu(Perfil perfil, String usuario, CustomUser user) {
        Menu respuesta = new Menu();
        Map<String,Object> vars = new HashMap<String,Object>();
        vars.put(SpGenerarMenu.PARAM_IN_ID_PERFIL, perfil.getIdPerfil());
        vars.put(SpGenerarMenu.PARAM_IN_USUARIO_MENU, usuario);
        vars.put(SpGenerarMenu.PARAM_IN_USUARIO, user.getUsername());
        try {
            Map<String, Object> result = (Map<String, Object>) spGenerarMenu.execute(vars);
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
