/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.service;

import com.whnm.sicqfdp.beans.CustomUser;
import com.whnm.sicqfdp.beans.ListPerfiles;
import com.whnm.sicqfdp.beans.Perfil;
import com.whnm.sicqfdp.interfaces.PerfilDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author whnm
 */
@Service
@Qualifier(value="perfilService")
public class PerfilService implements PerfilDao{
    @Autowired
    @Qualifier(value="perfilDao")        
    PerfilDao perfilDao;
    
    @Override
    public ListPerfiles listarPerfiles(Integer tipoOperacion, Perfil perfil) {
        return perfilDao.listarPerfiles(tipoOperacion, perfil);
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
        return perfilDao.mantPerfiles(tipoOperacion, perfil, user);
    }

}
