/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.service;

import com.whnm.sicqfdp.beans.CustomUser;
import com.whnm.sicqfdp.beans.Deuda;
import com.whnm.sicqfdp.interfaces.DeudaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author whnm
 */
@Service("deudaService")
public class DeudaService implements DeudaDao{
    @Autowired
    @Qualifier(value ="deudaDao")
    DeudaDao deudaDao;
    
    @Override
    public Deuda grabar(Deuda elemento, CustomUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Deuda editar(Deuda elemento, CustomUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Deuda eliminar(Deuda elemento, CustomUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Deuda listar(Deuda elemento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Deuda listarDeuda(Integer opc, Deuda deuda) {
        return deudaDao.listarDeuda(opc,deuda);
    }

    @Override
    public Deuda mantDeuda(Integer opc, Deuda deuda, CustomUser user) {
        return deudaDao.mantDeuda(opc, deuda, user);
    }
    
}
