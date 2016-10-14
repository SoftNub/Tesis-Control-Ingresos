/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Esta clase servida como controladora del index del sistema
 * @author wilson
 * @version 1.0
 */
@Controller
public class BienvenidaController {
    
    
     @RequestMapping(value = "login.cqfdp", method = RequestMethod.GET)
    public ModelAndView login(
        @RequestParam(value = "error", required = false) String error,
        @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        try{
            if (error != null) {
                model.addObject("error", "Usuario o contraseña incorrecta");
            }

            if (logout != null) {
                model.addObject("msg", "Usted ha salido del sistema satisfactoriamente.");
            }
            model.setViewName("login");
        } catch(Exception ex){
             model.addObject("error", ex.getMessage());
        }
        return model;
    }
    
    /**
     * Metodo que mostrar la vista del index del sistema 
     * @return ModelAndView
     */
    @RequestMapping(value="Index.cqfdp", method=RequestMethod.GET)
    public ModelAndView mostrarIndex(){
        ModelAndView vista = new ModelAndView();
        vista.setViewName("Index");
        return vista;
    }
}
