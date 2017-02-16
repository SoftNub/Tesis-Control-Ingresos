/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.service;

import com.whnm.sicqfdp.beans.CustomUser;
import com.whnm.sicqfdp.beans.ListMenu;
import com.whnm.sicqfdp.beans.Menu;
import com.whnm.sicqfdp.beans.Perfil;
import com.whnm.sicqfdp.beans.Usuario;
import com.whnm.sicqfdp.interfaces.MenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author whnm
 */
@Service
@Qualifier(value = "menuService")
public class MenuService implements MenuDao{
    @Autowired
    @Qualifier(value = "menuDao")
    MenuDao menuDao;
    
    @Override
    public ListMenu listaMenu(Integer tipoOpe, Perfil perfil, Menu menu, Usuario usuario) {
        return menuDao.listaMenu(tipoOpe, perfil, menu, usuario);
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
        return menuDao.mantMenu(tipoOpe, perfil, menu, usuario, user);
    }

    @Override
    public Menu generarMenu(Perfil perfil, String usuario, CustomUser user) {
        return menuDao.generarMenu(perfil, usuario, user);
    }
    
}
