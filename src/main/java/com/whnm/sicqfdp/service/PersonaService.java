/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.service;

import com.whnm.sicqfdp.beans.Persona;
import com.whnm.sicqfdp.interfaces.PersonaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author wilson
 */
@Service(value = "personaService")
public class PersonaService implements PersonaDao{
    @Autowired
    @Qualifier("personaDao")
    private PersonaDao personaDao;
    
    @Override
    public Persona grabar(Persona elemento) {
        return personaDao.grabar(elemento);
    }

    @Override
    public Persona editar(Persona elemento) {
        return personaDao.editar(elemento);
    }

    @Override
    public Persona eliminar(Persona elemento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Persona listar(Persona elemento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Persona listarPersona(Integer opc, Persona per) {
        return personaDao.listarPersona(opc, per);
    }
    
}
