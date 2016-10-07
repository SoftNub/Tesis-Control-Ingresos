/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.interfaces;

import com.whnm.sicqfdp.beans.Distrito;
import com.whnm.sicqfdp.beans.DistritoList;

/**
 *
 * @author wilson
 */
public interface DistritoDao extends Generica<Distrito>{
    /**
     * Lista todos los distritos
     * @return DistritoList
     */
    public DistritoList listarTodosDistritos();
    public DistritoList listarDistritosHabilitados(Distrito elemento);
}
