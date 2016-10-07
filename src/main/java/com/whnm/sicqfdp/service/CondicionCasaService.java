/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.service;

import com.whnm.sicqfdp.beans.CondicionCasa;
import com.whnm.sicqfdp.beans.ListCondicionCasa;
import com.whnm.sicqfdp.interfaces.CondicionCasaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author wilson
 */
@Service("condicionCasaService")
public class CondicionCasaService implements CondicionCasaDao{
    @Autowired
    @Qualifier("condicionCasaDao")
    private CondicionCasaDao condicionCasaDao;
    @Override
    public CondicionCasa mantCondicionCasa(Integer opcCrud, CondicionCasa condCasa) {
        return condicionCasaDao.mantCondicionCasa(opcCrud, condCasa);
    }

    @Override
    public ListCondicionCasa listarCondicionCasas(Integer opcListado, CondicionCasa condCasa) {
        return condicionCasaDao.listarCondicionCasas(opcListado, condCasa);
    }
}
