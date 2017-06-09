/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.service;

import com.whnm.sicqfdp.beans.CustomUser;
import com.whnm.sicqfdp.beans.ListaGenerico;
import com.whnm.sicqfdp.beans.Pago;
import com.whnm.sicqfdp.beans.TipoPago;
import com.whnm.sicqfdp.interfaces.PagoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author whnm
 */
@Service("pagoService")
public class PagoService implements PagoDao{
    @Autowired
    @Qualifier(value = "pagoDao")        
    PagoDao pagoDao;
    
    @Override
    public Pago mantPago(Integer tipoOperacion, Pago pago, CustomUser usuario) {
        return pagoDao.mantPago(tipoOperacion, pago, usuario);
    }

    @Override
    public Pago grabar(Pago elemento, CustomUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pago editar(Pago elemento, CustomUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pago eliminar(Pago elemento, CustomUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pago listar(Pago elemento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListaGenerico<Pago> listarPagos(Integer opc, Pago pago, TipoPago tipoPago, CustomUser user) {
        return pagoDao.listarPagos(opc, pago, tipoPago, user);
    }
    
}
