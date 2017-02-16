/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author whnm
 */
@Controller
public class GestionUsuariosController {
    // <editor-fold defaultstate="collapsed" desc="gestion perfiles">
     @RequestMapping(value="usuarios/perfiles.cqfdp", method = RequestMethod.GET)
    public ModelAndView mostrarGestionPerfiles(){
        String tituloParametria;
        String msjGrabar;
        String msjEditar;
        String msjEliminar;
        ModelAndView vista = new ModelAndView();
        tituloParametria = "GESTIÓN DE PERFILES";
        vista.addObject("TituloParametria", tituloParametria);
        vista.setViewName("perfiles/admPerfiles");
        return vista;
    }
    // </editor-fold>
}
