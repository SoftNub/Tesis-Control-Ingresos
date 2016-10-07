/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.interfaces;

import com.whnm.sicqfdp.beans.Operadora;
import com.whnm.sicqfdp.beans.ListOperadora;

/**
 *
 * @author wilson
 */
public interface OperadoraDao {
    public Operadora mantOperadora(Integer opcCrud, Operadora ope);
    public ListOperadora listarOperadoras(Integer opcListado, Operadora ope);
}
