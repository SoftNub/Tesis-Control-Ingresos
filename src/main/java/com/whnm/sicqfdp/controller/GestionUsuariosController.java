/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.controller;

import com.whnm.sicqfdp.beans.CustomUser;
import com.whnm.sicqfdp.beans.ListMenu;
import com.whnm.sicqfdp.beans.ListPerfiles;
import com.whnm.sicqfdp.beans.ListUsuarios;
import com.whnm.sicqfdp.beans.Menu;
import com.whnm.sicqfdp.beans.Perfil;
import com.whnm.sicqfdp.beans.Usuario;
import com.whnm.sicqfdp.interfaces.MenuDao;
import com.whnm.sicqfdp.interfaces.PerfilDao;
import com.whnm.sicqfdp.interfaces.UsuarioDao;
import com.whnm.sicqfdp.utils.ValidaEntrada;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author whnm
 */
@Controller
public class GestionUsuariosController {
    @Autowired
    @Qualifier(value = "perfilService")        
    PerfilDao perfilService;
    
    @Autowired
    @Qualifier(value = "menuService")        
    MenuDao menuService;
    
    @Autowired
    @Qualifier(value = "usuarioService")        
    UsuarioDao usuarioService;
    
    // <editor-fold defaultstate="collapsed" desc="gestion perfiles">
     @RequestMapping(value="usuarios/GestionPerfiles.cqfdp", method = RequestMethod.GET)
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
    
    @RequestMapping(value="usuarios/listarPerfil.action", method = RequestMethod.POST)
    public @ResponseBody ListPerfiles listarPerfil(
          @RequestBody String objs  
    ){
        ValidaEntrada validaEntrada;
        ListPerfiles perfilRep = new ListPerfiles();
        validaEntrada = new ValidaEntrada();
        ObjectMapper mapper = new ObjectMapper();
        Integer opc;
        Perfil perfil;
        try{
            JsonNode node = mapper.readTree(objs);
            opc = mapper.convertValue(node.get("tipoOperacion"), Integer.class);
            perfil = mapper.convertValue(node.get("perfil"), Perfil.class);     
            perfilRep = perfilService.listarPerfiles(opc, perfil);

        }catch(Exception ex){
            perfilRep.setIndError(1);
            perfilRep.setMsjError("Error:["+ex.getMessage()+"]");
        } 
        return perfilRep;
    }
    
    @RequestMapping(value="usuarios/mantPerfiles.action", method = RequestMethod.POST)
    public @ResponseBody Perfil mantPerfiles(
          @RequestBody String objs  
    ){
        ValidaEntrada validaEntrada;
        Perfil perfilRep = new Perfil();
        validaEntrada = new ValidaEntrada();
        CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ObjectMapper mapper = new ObjectMapper();
        Integer opc;
        Perfil perfil;
        try{
            JsonNode node = mapper.readTree(objs);
            opc = mapper.convertValue(node.get("tipoOperacion"), Integer.class);
            perfil = mapper.convertValue(node.get("perfil"), Perfil.class);     
            perfilRep = perfilService.mantPerfiles(opc, perfil, user);

        }catch(Exception ex){
            perfilRep.setIndError(1);
            perfilRep.setMsjError("Error:["+ex.getMessage()+"]");
        } 
        return perfilRep;
    }
    
    @RequestMapping(value="usuarios/listarMenus.action", method = RequestMethod.POST)
    public @ResponseBody ListMenu listarMenus(
          @RequestBody String objs  
    ){
        ValidaEntrada validaEntrada;
        ListMenu menuRep = new ListMenu();
        validaEntrada = new ValidaEntrada();
        ObjectMapper mapper = new ObjectMapper();
        Integer opc;
        Perfil perfil;
        Menu menu;
        Usuario usuario;
        try{
            JsonNode node = mapper.readTree(objs);
            opc = mapper.convertValue(node.get("tipoOperacion"), Integer.class);
            perfil = mapper.convertValue(node.get("perfil"), Perfil.class);     
            menu = mapper.convertValue(node.get("menu"), Menu.class);     
            usuario = mapper.convertValue(node.get("usuario"), Usuario.class);     
            menuRep =menuService.listaMenu(opc, perfil, menu, usuario);
        }catch(Exception ex){
            menuRep.setIndError(1);
            menuRep.setMsjError("Error:["+ex.getMessage()+"]");
        } 
        return menuRep;
    }
    
    @RequestMapping(value="usuarios/mantMenu.action", method = RequestMethod.POST)
    public @ResponseBody Menu mantMenu(
          @RequestBody String objs  
    ){
        ValidaEntrada validaEntrada;
        Menu menuRep = new Menu();
        validaEntrada = new ValidaEntrada();
        ObjectMapper mapper = new ObjectMapper();
        CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer opc;
        Usuario usuario;
        Perfil perfil;
        Menu menu;
        try{
            JsonNode node = mapper.readTree(objs);
            opc = mapper.convertValue(node.get("tipoOperacion"), Integer.class);
            perfil = mapper.convertValue(node.get("perfil"), Perfil.class);     
            menu = mapper.convertValue(node.get("menu"), Menu.class);     
            usuario = mapper.convertValue(node.get("usuarioMenu"), Usuario.class);     
            menuRep =menuService.mantMenu(opc, perfil, menu, usuario, user);
        }catch(Exception ex){
            menuRep.setIndError(1);
            menuRep.setMsjError("Error:["+ex.getMessage()+"]");
        } 
        return menuRep;
    }
    
    @RequestMapping(value="usuarios/generarMenu.action", method = RequestMethod.POST)
    public @ResponseBody Menu generarMenu(
          @RequestBody String objs  
    ){
        ValidaEntrada validaEntrada;
        Menu menuRep = new Menu();
        validaEntrada = new ValidaEntrada();
        ObjectMapper mapper = new ObjectMapper();
        CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer opc;
        String usuario;
        Perfil perfil;
        Menu menu;
        try{
            JsonNode node = mapper.readTree(objs);
            perfil = mapper.convertValue(node.get("perfil"), Perfil.class);     
            usuario = mapper.convertValue(node.get("usuarioMenu"), String.class);     
            menuRep =menuService.generarMenu(perfil, usuario, user);
        }catch(Exception ex){
            menuRep.setIndError(1);
            menuRep.setMsjError("Error:["+ex.getMessage()+"]");
        } 
        return menuRep;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="gestion menus individuales">
     @RequestMapping(value="usuarios/menusIndividuales.cqfdp", method = RequestMethod.GET)
    public ModelAndView mostrarGestionMenusIndividuales(){
        String tituloParametria;
        ModelAndView vista = new ModelAndView();
        tituloParametria = "GESTIÓN DE MENUS INDIVIDUALES USUARIOS";
        vista.addObject("TituloParametria", tituloParametria);
        vista.setViewName("perfiles/admMenuIndividual");
        return vista;
    }
    
    @RequestMapping(value="usuarios/listarUsuarios.action", method = RequestMethod.POST)
    public @ResponseBody ListUsuarios listarUsuarios(
          @RequestBody String objs  
    ){
        ValidaEntrada validaEntrada;
        ListUsuarios usuarioRep = new ListUsuarios();
        validaEntrada = new ValidaEntrada();
        ObjectMapper mapper = new ObjectMapper();
        Integer opc;
        Usuario usuario;
        try{
            JsonNode node = mapper.readTree(objs);
            opc = mapper.convertValue(node.get("tipoOperacion"), Integer.class);
            usuario = mapper.convertValue(node.get("usuario"), Usuario.class);     
            usuarioRep = usuarioService.listarUsuarios(opc, usuario);
        }catch(Exception ex){
            usuarioRep.setIndError(1);
            usuarioRep.setMsjError("Error:["+ex.getMessage()+"]");
        } 
        return usuarioRep;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="gestion usuarios">
    @RequestMapping(value="usuarios/gestionUsuarios.cqfdp", method = RequestMethod.GET)
    public ModelAndView mostrarGestionUsuarios(){
        String tituloParametria;
        ModelAndView vista = new ModelAndView();
        tituloParametria = "GESTIÓN DE USUARIOS";
        vista.addObject("TituloParametria", tituloParametria);
        vista.setViewName("perfiles/admUsuarios");
        return vista;
    }
    
    
    @RequestMapping(value="usuarios/mantUsuarios.action", method = RequestMethod.POST)
    public @ResponseBody Usuario mantUsuarios(
          @RequestBody String objs  
    ){
        ValidaEntrada validaEntrada;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        Usuario usuarioRep = new Usuario();
        validaEntrada = new ValidaEntrada();
        CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ObjectMapper mapper = new ObjectMapper();
        Integer opc;
        Usuario usuario;
        try{
            JsonNode node = mapper.readTree(objs);
            opc = mapper.convertValue(node.get("tipoOperacion"), Integer.class);
            usuario = mapper.convertValue(node.get("usuario"), Usuario.class);     
            if(opc == 1){
                usuario.setPassword(encoder.encode(usuario.getPassword()));
            } else{
                if(!usuario.getPassword().isEmpty()){
                    usuario.setPassword(encoder.encode(usuario.getPassword()));
                }
            }
            usuarioRep = usuarioService.mantUsuarios(opc, usuario, user);

        }catch(Exception ex){
            usuarioRep.setIndError(1);
            usuarioRep.setMsjError("Error:["+ex.getMessage()+"]");
        } 
        return usuarioRep;
    }
    // </editor-fold>
}
