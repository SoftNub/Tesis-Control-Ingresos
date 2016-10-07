/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.service;

import com.whnm.sicqfdp.beans.EstadoCivil;
import com.whnm.sicqfdp.beans.ListEstadoCivil;
import com.whnm.sicqfdp.interfaces.EstadoCivilDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author wilson
 */
@Service("estadoCivilService")
public class EstadoCivilService implements EstadoCivilDao{
    @Autowired
    @Qualifier("estadoCivilDao")
    private EstadoCivilDao estadoCivilDao;
    @Override
    public EstadoCivil mantEstadoCivil(Integer opcCrud, EstadoCivil estCivil) {
        return estadoCivilDao.mantEstadoCivil(opcCrud, estCivil);
    }

    @Override
    public ListEstadoCivil listarEstadoCivil(Integer opcListado, EstadoCivil estCivil) {
        return estadoCivilDao.listarEstadoCivil(opcListado, estCivil);
    }
    
}
