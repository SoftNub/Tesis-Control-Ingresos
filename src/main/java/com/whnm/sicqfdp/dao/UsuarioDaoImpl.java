/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.dao;

import com.whnm.sicqfdp.beans.Parametros;
import com.whnm.sicqfdp.beans.Role;
import com.whnm.sicqfdp.beans.User;
import com.whnm.sicqfdp.interfaces.UserDao;
import com.whnm.sicqfdp.spbeans.SpLoginUsuario;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author wilson
 */
@Service(value="usuarioDao")
public class UsuarioDaoImpl implements UserDao{
    private DataSource dataSource;
    private SpLoginUsuario spLoginUsuario;
    
    @Autowired
    public UsuarioDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.spLoginUsuario = new SpLoginUsuario(dataSource);
    }
    
    @Override
    public User login(User user) {
        User usuario = new User();
        Role role;
        Set<Role> myRoles = new HashSet<>();
        Map<String,Object> vars = new HashMap<>();
        vars.put(SpLoginUsuario.PARAM_IN_USUARIO, user.getNombreUsuario());
        vars.put(SpLoginUsuario.PARAM_IN_PASSWORD, user.getPassword());
        try {
            Map<String, Object> result = (Map<String, Object>) spLoginUsuario.execute(vars);
            List<Map<String, Object>> listResult = (List<Map<String, Object>>) result.get(Parametros.RESULSET);
            usuario.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            usuario.setMsjError(result.get(Parametros.MSJ).toString());
            if(usuario.getIndError() == 0){
                for (Map<String, Object> item : listResult) {
                    role = new Role();
                    role.setIdRol(item.get("id") != null ? Integer.parseInt(String.valueOf(item.get("id"))) : -1);
                    role.setDescripcion(item.get("descripcion") != null ? String.valueOf(item.get("descripcion")) : "");
                    role.setAutorisacion(item.get("autorizacion") != null ? String.valueOf(item.get("autorizacion")) : "");
                    myRoles.add(role);
                }
            }
        } catch(Exception ex){
            Logger.getLogger(UsuarioDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            usuario.setIndError(1);
            usuario.setMsjError("Error: ["+ex.getMessage()+"]");        
        }
        return usuario;
    }

    @Override
    public User grabar(User elemento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User editar(User elemento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User eliminar(User elemento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User listar(User elemento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
