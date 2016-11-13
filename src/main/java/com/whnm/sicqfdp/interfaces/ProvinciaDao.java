/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.interfaces;

import com.whnm.sicqfdp.beans.CustomUser;
import com.whnm.sicqfdp.beans.Distrito;
import com.whnm.sicqfdp.beans.DistritoList;
import com.whnm.sicqfdp.beans.ListProvincia;
import com.whnm.sicqfdp.beans.Provincia;

/**
 *
 * @author wilson
 */
public interface ProvinciaDao extends Generica<Provincia>{
    public ListProvincia listarAllProvincias();
    public ListProvincia listarProvinciasHabilitadas(Provincia elemento);
    public DistritoList listarDistritosDeProvincia(Provincia elemento);
    public Provincia grabarDistritoDeProvincia(Provincia elemento, Distrito elemento2,
            CustomUser user);
    public Provincia eliminarDistritoDeProvincia(Provincia elemento, Distrito elemento2);
}
