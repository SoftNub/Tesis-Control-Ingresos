/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.interfaces;

import com.whnm.sicqfdp.beans.CustomUser;
import com.whnm.sicqfdp.beans.ListUsuarios;
import com.whnm.sicqfdp.beans.Usuario;

/**
 *
 * @author whnm
 */
public interface UsuarioDao extends Generica<Usuario>{
    public ListUsuarios listarUsuarios(Integer opc, Usuario usuario);
    public Usuario mantUsuarios(Integer opc, Usuario usuario, CustomUser user);
}
