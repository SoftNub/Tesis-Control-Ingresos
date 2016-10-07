/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.service;

import com.whnm.sicqfdp.beans.Operadora;
import com.whnm.sicqfdp.beans.ListOperadora;
import com.whnm.sicqfdp.interfaces.OperadoraDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author wilson
 */
@Service("operadoraService")
public class OperadoraService implements OperadoraDao{
    @Autowired
    @Qualifier("operadoraDao")
    private OperadoraDao operadoraDao;
    @Override
    public Operadora mantOperadora(Integer opcCrud, Operadora ope) {
        return operadoraDao.mantOperadora(opcCrud, ope);
    }

    @Override
    public ListOperadora listarOperadoras(Integer opcListado, Operadora ope) {
        return operadoraDao.listarOperadoras(opcListado, ope);
    }
}
