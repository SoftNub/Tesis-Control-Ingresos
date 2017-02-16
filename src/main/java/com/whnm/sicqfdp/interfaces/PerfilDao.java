/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.interfaces;

import com.whnm.sicqfdp.beans.CustomUser;
import com.whnm.sicqfdp.beans.ListPerfiles;
import com.whnm.sicqfdp.beans.Perfil;

/**
 *
 * @author whnm
 */
public interface PerfilDao extends Generica<Perfil>{
    public ListPerfiles listarPerfiles(Integer tipoOperacion, Perfil perfil);
    public Perfil mantPerfiles(Integer tipoOperacion, Perfil perfil, CustomUser user);
}
