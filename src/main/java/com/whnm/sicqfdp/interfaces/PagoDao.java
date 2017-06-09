/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.interfaces;

import com.whnm.sicqfdp.beans.CustomUser;
import com.whnm.sicqfdp.beans.ListaGenerico;
import com.whnm.sicqfdp.beans.Pago;
import com.whnm.sicqfdp.beans.TipoPago;

/**
 *
 * @author whnm
 */
public interface PagoDao extends Generica<Pago>{
    public Pago mantPago(Integer tipoOperacion, Pago pago, CustomUser usuario);
    public ListaGenerico<Pago> listarPagos(Integer opc, Pago pago, TipoPago tipoPago, CustomUser user);
}
