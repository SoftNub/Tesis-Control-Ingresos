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
    
    /**
     * Busca Colegiado para realizar pagos de coutas y otros conceptos.
     * @param tipoOperacion : puede ser "C" para Colegiado y "N" para Persona
     * @param tipoDocumento : puede ser 2: solo DNI, en el caso del colegiado es indiferente
     * @param col : contiene información del documento del colegiado(NumColegiatura) o persona (DNI)
     * @return : Debe retornar array pero conteniendo un solo colegiado
     */
    public ListColegiado buscarColegiadosPagos(String tipoOperacion, Integer tipoDocumento, Colegiado col);
}
