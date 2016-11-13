/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.service;

import com.whnm.sicqfdp.beans.CustomUser;
import com.whnm.sicqfdp.beans.Departamento;
import com.whnm.sicqfdp.beans.ListDepartamento;
import com.whnm.sicqfdp.beans.ListProvincia;
import com.whnm.sicqfdp.beans.Provincia;
import com.whnm.sicqfdp.interfaces.DepartamentoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author wilson
 */
@Service("departamentoService")
public class DepartamentoServiceImp implements DepartamentoDao{
    @Autowired
    @Qualifier("departamentoDao")
    private DepartamentoDao departamentoDao;

    @Override
    public ListDepartamento listarAllDepartamentos() {
        return departamentoDao.listarAllDepartamentos();
    }

    @Override
    public ListDepartamento listarDepartamentosHabilitadas(Departamento elemento) {
        return departamentoDao.listarDepartamentosHabilitadas(elemento);
    }

    @Override
    public ListProvincia listarProvinciaDeDepartamento(Departamento elemento) {
        return departamentoDao.listarProvinciaDeDepartamento(elemento);
    }

    @Override
    public Departamento grabarProvinciaDeDepartamento(Departamento elemento,
            Provincia elemento2, CustomUser user) {
        return departamentoDao.grabarProvinciaDeDepartamento(elemento, elemento2,
                user);
    }

    @Override
    public Departamento eliminarProvinciaDeDepartamento(Departamento elemento,
            Provincia elemento2) {
        return departamentoDao.eliminarProvinciaDeDepartamento(elemento, elemento2);
    }

    @Override
    public Departamento grabar(Departamento elemento, CustomUser user) {
        return departamentoDao.grabar(elemento, user);
    }

    @Override
    public Departamento editar(Departamento elemento, CustomUser user) {
        return departamentoDao.editar(elemento, user);
    }

    @Override
    public Departamento eliminar(Departamento elemento, CustomUser user) {
        return departamentoDao.eliminar(elemento, user);
    }

    @Override
    public Departamento listar(Departamento elemento) {
        return departamentoDao.listar(elemento);
    }
}
