/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.interfaces;

import com.whnm.sicqfdp.beans.Colegiado;
import com.whnm.sicqfdp.beans.CustomUser;
import com.whnm.sicqfdp.beans.ListColegiado;

/**
 *
 * @author wilson
 */
public interface ColegiadoDao extends Generica<Colegiado> {
    public Colegiado listarColegiado(Integer opc, Colegiado per);
    public Colegiado grabarColegiado(Integer opc, Integer indicador, Colegiado per,
            CustomUser user);
    public Colegiado grabarSolicitudColegiatura(Colegiado col, CustomUser user);
    public ListColegiado buscarColegiadoEgreso(Integer opc, Colegiado col);
    public ListColegiado grabarEgresoColegiado(ListColegiado listaColegiados,
            CustomUser user);
}
