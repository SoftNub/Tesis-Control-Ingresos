/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.dao;

import com.whnm.sicqfdp.beans.CustomUser;
import com.whnm.sicqfdp.beans.ListUsuarios;
import com.whnm.sicqfdp.beans.Parametros;
import com.whnm.sicqfdp.beans.Usuario;
import com.whnm.sicqfdp.interfaces.UsuarioDao;
import com.whnm.sicqfdp.spbeans.SpConsultarUsuario;
import com.whnm.sicqfdp.spbeans.SpMantUsuario;
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
@Qualifier(value="usuarioDao")
public class Usuario2DaoImpl implements UsuarioDao{
    private DataSource dataSource;
    private SpConsultarUsuario spConsultarUsuario;
    private SpMantUsuario spMantUsuario;

    
    @Autowired
    public Usuario2DaoImpl(@Qualifier("dataSource") DataSource dataSource) {
        this.dataSource = dataSource;
        this.spConsultarUsuario = new SpConsultarUsuario(dataSource);
        this.spMantUsuario = new SpMantUsuario(dataSource);
    }
    
    @Override
    public ListUsuarios listarUsuarios(Integer opc, Usuario usuario) {
        ListUsuarios respuesta = new ListUsuarios();
        List<Usuario> lista = new ArrayList<>();
        Map<String,Object> vars = new HashMap<String,Object>();
        vars.put(SpConsultarUsuario.PARAM_IN_OPC, opc);
        vars.put(SpConsultarUsuario.PARAM_IN_USERNAME, usuario.getUsername());
        try {
            Map<String, Object> result = (Map<String, Object>) spConsultarUsuario.execute(vars);
            List<Map<String, Object>> listResult = (List<Map<String, Object>>) result.get(Parametros.RESULSET);
            respuesta.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            respuesta.setMsjError(result.get(Parametros.MSJ).toString());
            if(respuesta.getIndError() == 0){
                for (Map<String, Object> item : listResult) {
                    Usuario elem = new Usuario();
                    elem.setUsername(item.get("username") != null ? String.valueOf(item.get("username")) : "");
                    elem.setNombre(item.get("Nombres") != null ? String.valueOf(item.get("Nombres")) : "");
                    if(opc == 1 || opc == 2){ 
                        elem.setApellidos(item.get("apellido") != null ? String.valueOf(item.get("apellido")) : "");
                        elem.setTipo(item.get("tipo") != null ? String.valueOf(item.get("tipo")) : "");
                        elem.setId_perfil(item.get("id_perfil") != null ? Integer.parseInt(String.valueOf(item.get("id_perfil"))) : 0);
                        elem.setHabilitado(item.get("habilitado") != null ? Integer.parseInt(String.valueOf(item.get("habilitado"))) : 0);
                        elem.setEs_usuario(item.get("es_usuario") != null ? Integer.parseInt(String.valueOf(item.get("es_usuario"))) : 0);
                    }
                    lista.add(elem);
                }
            }
        } catch(Exception ex){
            Logger.getLogger(Usuario2DaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            respuesta.setIndError(1);
            respuesta.setMsjError("Error: ["+ex.getMessage()+"]");
        }
        respuesta.setListUsuarios(lista);
        return respuesta;
    }

    @Override
    public Usuario grabar(Usuario elemento, CustomUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario editar(Usuario elemento, CustomUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario eliminar(Usuario elemento, CustomUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario listar(Usuario elemento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario mantUsuarios(Integer opc, Usuario usuario, CustomUser user) {
        Usuario respuesta = new Usuario();
        Map<String,Object> vars = new HashMap<String,Object>();
        vars.put(SpMantUsuario.PARAM_IN_OPC, opc);
        vars.put(SpMantUsuario.PARAM_IN_USERNAME, usuario.getUsername());
        vars.put(SpMantUsuario.PARAM_IN_PASSWORD, usuario.getPassword());
        vars.put(SpMantUsuario.PARAM_IN_ESTADO, usuario.getHabilitado());
        vars.put(SpMantUsuario.PARAM_IN_ID_PERFIL, usuario.getId_perfil());
        vars.put(SpMantUsuario.PARAM_IN_TIPO, usuario.getTipo());
        vars.put(SpMantUsuario.PARAM_IN_USUARIO, user.getUsername());
        try {
            Map<String, Object> result = (Map<String, Object>) spMantUsuario.execute(vars);
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
