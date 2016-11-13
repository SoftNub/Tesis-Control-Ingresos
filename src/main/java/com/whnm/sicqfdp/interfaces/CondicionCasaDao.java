/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.interfaces;

import com.whnm.sicqfdp.beans.CondicionCasa;
import com.whnm.sicqfdp.beans.CustomUser;
import com.whnm.sicqfdp.beans.ListCondicionCasa;

/**
 *
 * @author wilson
 */
public interface CondicionCasaDao {
    public CondicionCasa mantCondicionCasa(Integer opcCrud, CondicionCasa condCasa,
            CustomUser user);
    public ListCondicionCasa listarCondicionCasas(Integer opcListado, CondicionCasa condCasa);
}
