/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.interfaces;

import com.whnm.sicqfdp.beans.CustomUser;
import com.whnm.sicqfdp.beans.EstadoCivil;
import com.whnm.sicqfdp.beans.ListEstadoCivil;

/**
 *
 * @author wilson
 */
public interface EstadoCivilDao {
    public EstadoCivil mantEstadoCivil(Integer opcCrud, EstadoCivil estCivil, CustomUser user);
    public ListEstadoCivil listarEstadoCivil(Integer opcListado, EstadoCivil estCivil);
}
