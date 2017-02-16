/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.service;

import com.whnm.sicqfdp.beans.CustomUser;
import com.whnm.sicqfdp.beans.ListUsuarios;
import com.whnm.sicqfdp.beans.Usuario;
import com.whnm.sicqfdp.interfaces.UsuarioDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author whnm
 */
@Service
@Qualifier(value = "usuarioService")
public class UsuarioService2 implements UsuarioDao{
    @Autowired
    @Qualifier(value="usuarioDao")
    private UsuarioDao usuarioDao;
    
    @Override
    public ListUsuarios listarUsuarios(Integer opc, Usuario usuario) {
        return usuarioDao.listarUsuarios(opc, usuario);
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
        return usuarioDao.mantUsuarios(opc, usuario, user);
    }
    
}
