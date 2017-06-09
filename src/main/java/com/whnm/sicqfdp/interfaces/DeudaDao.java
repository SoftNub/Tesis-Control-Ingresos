/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.interfaces;

import com.whnm.sicqfdp.beans.CustomUser;
import com.whnm.sicqfdp.beans.Deuda;

/**
 *
 * @author whnm
 */
public interface DeudaDao extends Generica<Deuda> {

    public Deuda listarDeuda(Integer opc, Deuda deuda);

    public Deuda mantDeuda(Integer opc, Deuda deuda, CustomUser user);
    
}
