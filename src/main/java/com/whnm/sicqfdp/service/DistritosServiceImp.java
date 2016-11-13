/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.service;

import com.whnm.sicqfdp.beans.CustomUser;
import com.whnm.sicqfdp.beans.Distrito;
import com.whnm.sicqfdp.beans.DistritoList;
import com.whnm.sicqfdp.interfaces.DistritoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author wilson
 */
@Service(value = "distritoService")
public class DistritosServiceImp implements DistritoDao{
    @Autowired
    @Qualifier("distritoDao")
    private DistritoDao distritoDao;

    @Override
    public DistritoList listarTodosDistritos() {
        return distritoDao.listarTodosDistritos();
    }

    @Override
    public Distrito grabar(Distrito elemento, CustomUser user) {
        return distritoDao.grabar(elemento, user);
    }

    @Override
    public Distrito editar(Distrito elemento, CustomUser user) {
       return distritoDao.editar(elemento, user);
    }

    @Override
    public Distrito eliminar(Distrito elemento, CustomUser user) {
        return distritoDao.eliminar(elemento, user);
    }

    @Override
    public Distrito listar(Distrito elemento) {
        return distritoDao.listar(elemento);
    }

    @Override
    public DistritoList listarDistritosHabilitados(Distrito elemento) {
        return distritoDao.listarDistritosHabilitados(elemento);
    }
}
