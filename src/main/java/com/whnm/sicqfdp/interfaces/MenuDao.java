/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.interfaces;

import com.whnm.sicqfdp.beans.CustomUser;
import com.whnm.sicqfdp.beans.ListMenu;
import com.whnm.sicqfdp.beans.Menu;
import com.whnm.sicqfdp.beans.Perfil;
import com.whnm.sicqfdp.beans.Usuario;

/**
 *
 * @author whnm
 */
public interface MenuDao extends Generica<Menu>{
    public ListMenu listaMenu(Integer tipoOpe, Perfil perfil, Menu menu, Usuario usuario);
    public Menu mantMenu(Integer tipoOpe, Perfil perfil, Menu menu, Usuario usuario,
            CustomUser user);
    public Menu generarMenu(Perfil perfil, String usuario, CustomUser user);
}
