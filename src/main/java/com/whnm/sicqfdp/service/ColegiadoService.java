/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.service;

import com.whnm.sicqfdp.beans.Colegiado;
import com.whnm.sicqfdp.beans.CustomUser;
import com.whnm.sicqfdp.beans.ListColegiado;
import com.whnm.sicqfdp.interfaces.ColegiadoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author wilson
 */
@Service(value = "colegiadoService")
public class ColegiadoService implements ColegiadoDao{
    @Autowired
    @Qualifier("colegiadoDao")
    private ColegiadoDao colegiadoDao;
    
    @Override
    public Colegiado grabar(Colegiado elemento, CustomUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Colegiado editar(Colegiado elemento, CustomUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Colegiado eliminar(Colegiado elemento, CustomUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Colegiado listar(Colegiado elemento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Colegiado listarColegiado(Integer opc, Colegiado col) {
        return colegiadoDao.listarColegiado(opc, col);
    }

    @Override
    public Colegiado grabarColegiado(Integer opc, Integer indicador, Colegiado per,
            CustomUser user) {
        return colegiadoDao.grabarColegiado(opc, indicador, per, user);
    }

    @Override
    public Colegiado grabarSolicitudColegiatura(Colegiado col, CustomUser user) {
        return colegiadoDao.grabarSolicitudColegiatura(col, user);
    }

    @Override
    public ListColegiado buscarColegiadoEgreso(Integer opc , Colegiado col) {
        return colegiadoDao.buscarColegiadoEgreso(opc, col);
    }

    @Override
    public ListColegiado grabarEgresoColegiado(ListColegiado listaColegiados, CustomUser user) {
        return colegiadoDao.grabarEgresoColegiado(listaColegiados, user);
    }
    
}
