/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.interfaces;

import com.whnm.sicqfdp.beans.Departamento;
import com.whnm.sicqfdp.beans.ListDepartamento;
import com.whnm.sicqfdp.beans.ListProvincia;
import com.whnm.sicqfdp.beans.Provincia;

/**
 *
 * @author wilson
 */
public interface DepartamentoDao extends Generica<Departamento>{
    public ListDepartamento listarAllDepartamentos();
    public ListDepartamento listarDepartamentosHabilitadas(Departamento elemento);
    public ListProvincia listarProvinciaDeDepartamento(Departamento elemento);
    public Departamento grabarProvinciaDeDepartamento(Departamento elemento, Provincia elemento2);
    public Departamento eliminarProvinciaDeDepartamento(Departamento elemento, Provincia elemento2);
}
