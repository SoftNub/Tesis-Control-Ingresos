/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.dao;

import com.whnm.sicqfdp.beans.CustomUser;
import com.whnm.sicqfdp.beans.Menu;
import com.whnm.sicqfdp.beans.Parametros;
import com.whnm.sicqfdp.beans.Role;
import com.whnm.sicqfdp.interfaces.UserDao;
import com.whnm.sicqfdp.spbeans.SpLoginUsuario;
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
@Service(value="usuarioDao")
public class UsuarioDaoImpl implements UserDao{
    private DataSource dataSource;
    private SpLoginUsuario spLoginUsuario;
    //private SpTraePassword spTraePassword;
    
    @Autowired
    public UsuarioDaoImpl(@Qualifier("dataSource") DataSource dataSource) {
        this.dataSource = dataSource;
        this.spLoginUsuario = new SpLoginUsuario(dataSource);
        //this.spTraePassword = new SpTraePassword(dataSource);
    }
    
    @Override
    public CustomUser login(String username) {
        CustomUser usuario = new CustomUser();
        Role role;
        Menu menu;
        List<Role> myRoles = new ArrayList<>();
        List<Menu> myMenus = new ArrayList<>();
        Map<String,Object> vars = new HashMap<>();
        vars.put(SpLoginUsuario.PARAM_IN_USUARIO, username);
        vars.put(SpLoginUsuario.PARAM_IN_PASSWORD, "");
        try {
            Map<String, Object> result = (Map<String, Object>) spLoginUsuario.execute(vars);
            List<Map<String, Object>> listResult = (List<Map<String, Object>>) result.get(Parametros.RESULSET);
            List<Map<String, Object>> listResult2 = (List<Map<String, Object>>) result.get(Parametros.RESULSET_2);
            usuario.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            usuario.setMsjError(result.get(Parametros.MSJ).toString());
            usuario.setUsername(result.get(Parametros.USERNAME).toString());
            usuario.setPassword(result.get(Parametros.PASSWORD).toString());
            if(usuario.getIndError() == 0){
                for (Map<String, Object> item : listResult) {
                    role = new Role();
                    role.setIdRol(item.get("id") != null ? Integer.parseInt(String.valueOf(item.get("id"))) : -1);
                    role.setDescripcion(item.get("descripcion") != null ? String.valueOf(item.get("descripcion")) : "");
                    role.setAutorisacion(item.get("autorizacion") != null ? String.valueOf(item.get("autorizacion")) : "");
                    myRoles.add(role);
                }
                usuario.setAuthorities(myRoles);
                
                for (Map<String, Object> item : listResult2) {
                    menu = new Menu();
                    menu.setDescripMenu(item.get("opcionHtml") != null ? String.valueOf(item.get("opcionHtml")) : "");
                    myMenus.add(menu);
                }
                usuario.setMenus(myMenus);
            }
        } catch(Exception ex){
            Logger.getLogger(UsuarioDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            usuario.setIndError(1);
            usuario.setMsjError("Error: ["+ex.getMessage()+"]");        
        }
        return usuario;
    }

//    @Override
//    public User traer_password(User user) {
//        User usuario = new User();
//        Map<String,Object> vars = new HashMap<>();
//        vars.put(SpTraePassword.PARAM_IN_USUARIO, user.getNombreUsuario());
//        try {
//            Map<String, Object> result = (Map<String, Object>) spTraePassword.execute(vars);
//            List<Map<String, Object>> listResult = (List<Map<String, Object>>) result.get(Parametros.RESULSET);
//            usuario.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
//            usuario.setMsjError(result.get(Parametros.MSJ).toString());
//            if(usuario.getIndError() == 0){
//                for (Map<String, Object> item : listResult) {
//                    usuario.setPassword(item.get("password") != null ? String.valueOf(item.get("password")) : "");
//                }
//            }
//        } catch(Exception ex){
//            Logger.getLogger(UsuarioDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//            usuario.setIndError(1);
//            usuario.setMsjError("Error: ["+ex.getMessage()+"]");        
//        }
//        return usuario;
//    }


    @Override
    public CustomUser listar(CustomUser elemento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CustomUser grabar(CustomUser elemento, CustomUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CustomUser editar(CustomUser elemento, CustomUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CustomUser eliminar(CustomUser elemento, CustomUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
