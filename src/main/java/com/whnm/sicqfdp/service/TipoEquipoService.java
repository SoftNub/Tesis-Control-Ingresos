/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.service;

import com.whnm.sicqfdp.beans.ListTipoEquipo;
import com.whnm.sicqfdp.beans.TipoEquipo;
import com.whnm.sicqfdp.interfaces.TipoEquipoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author wilson
 */
@Service("tipoEquipoService")
public class TipoEquipoService implements TipoEquipoDao{
    @Autowired
    @Qualifier("tipoEquipoDao")
    private TipoEquipoDao tipoEquipoDao;
    
    @Override
    public TipoEquipo mantTipoEquipo(Integer opcCrud, TipoEquipo tipoEq) {
        return tipoEquipoDao.mantTipoEquipo(opcCrud, tipoEq);
    }

    @Override
    public ListTipoEquipo listarTipoEquipo(Integer opcListado, TipoEquipo tipoEq) {
        return tipoEquipoDao.listarTipoEquipo(opcListado, tipoEq);
    }
}
