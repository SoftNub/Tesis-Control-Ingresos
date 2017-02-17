/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.service;

import com.whnm.sicqfdp.beans.CustomUser;
import com.whnm.sicqfdp.beans.ListTipoPago;
import com.whnm.sicqfdp.beans.TipoPago;
import com.whnm.sicqfdp.interfaces.TipoPagoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Clase que contiene los distintos servicios expuestos de la clase
 * tipo pago
 * @author wilson
 */
@Service("tipoPagoService")
public class TipoPagoService implements TipoPagoDao{
    /**
     * Obtencion del bean Dao par tipo de pago
     */
    @Autowired
    @Qualifier("tipoPagoDao")
    private TipoPagoDao tipoPagoDao;

    @Override
    public ListTipoPago listarTipoPago(Integer opc, TipoPago tipoPago) {
        return tipoPagoDao.listarTipoPago(opc, tipoPago);
    }

    @Override
    public TipoPago mantenimientoTipoPago(Integer opc, TipoPago tipoPago, CustomUser user) {
        return tipoPagoDao.mantenimientoTipoPago(opc, tipoPago, user);
    }

    @Override
    public TipoPago grabar(TipoPago elemento, CustomUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TipoPago editar(TipoPago elemento, CustomUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TipoPago eliminar(TipoPago elemento, CustomUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TipoPago listar(TipoPago elemento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }   

    @Override
    public TipoPago consultarPreciosTipoPago(TipoPago objs) {
        return tipoPagoDao.consultarPreciosTipoPago(objs);
    }
}
