/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.service;

import com.whnm.sicqfdp.beans.Distrito;
import com.whnm.sicqfdp.beans.DistritoList;
import com.whnm.sicqfdp.beans.ListProvincia;
import com.whnm.sicqfdp.beans.Provincia;
import com.whnm.sicqfdp.interfaces.ProvinciaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author wilson
 */
@Service("provinciaService")
public class ProvinciaServiceImp implements ProvinciaDao{
    @Autowired
    @Qualifier("provinciaDao")
    private ProvinciaDao provinciaDao;

    @Override
    public ListProvincia listarAllProvincias() {
        return provinciaDao.listarAllProvincias();
    }

    @Override
    public Provincia grabar(Provincia elemento) {
        return provinciaDao.grabar(elemento);
    }

    @Override
    public Provincia editar(Provincia elemento) {
        return provinciaDao.editar(elemento);
    }

    @Override
    public Provincia eliminar(Provincia elemento) {
        return provinciaDao.eliminar(elemento);
    }

    @Override
    public Provincia listar(Provincia elemento) {
        return provinciaDao.listar(elemento);
    }

    @Override
    public DistritoList listarDistritosDeProvincia(Provincia elemento) {
        return provinciaDao.listarDistritosDeProvincia(elemento);
    }

    @Override
    public Provincia grabarDistritoDeProvincia(Provincia elemento, Distrito elemento2) {
        return provinciaDao.grabarDistritoDeProvincia(elemento, elemento2);
    }

    @Override
    public Provincia eliminarDistritoDeProvincia(Provincia elemento, Distrito elemento2) {
        return provinciaDao.eliminarDistritoDeProvincia(elemento, elemento2);
    }

    @Override
    public ListProvincia listarProvinciasHabilitadas(Provincia elemento) {
        return provinciaDao.listarProvinciasHabilitadas(elemento);
    }
}
