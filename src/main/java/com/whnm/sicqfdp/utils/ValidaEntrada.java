/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wilson
 */
public class ValidaEntrada {
    private String caracterTextoValido = " abcdefghijklmnñopqrstuvwxyz0123456789.-áéíóú|";
    private String letrasValidas = " abcdefghijklmnñopqrstuvwxyzáéíóú";
    private String caracteresDni = "0123456789";
    private String formatoFecha = "[0-9]{2}/[0-9]{2}/[0-9]{4}";
    private String soloNumerosDni = "[0-9]{8}";
    private String soloNumerosColegiatura = "[0-9]{1,10}";
    private String soloLetras = "[a-z[áéíóúñ]]";
    private String caracteresDomicilio = " abcdefghijklmnñopqrstuvwxyz0123456789áéíóú#°-";
    private String caracteresTelefono = "0123456789#";
    private String expRegEmail = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*(.[a-z]{2,4})$";
    /**
     * Validara que la entrada solo contenga estos caracteres
     * " abcdefghijklmnñopqrstuvwxyz0123456789.-|"
     * @param entrada
     * @return 
     */
    public boolean validarTexto(String entrada){
        int i;
        boolean resultado = true;
        entrada = entrada.toLowerCase();
        entrada = entrada.replaceAll("\n","|");
        for (i = 0;  i < entrada.length(); i++){
            if(caracterTextoValido.indexOf(entrada.charAt(i))== -1){
                resultado = false;
                break;
            }
        }
        return resultado;
    }
    /**
     * Valida que el campo solo contenga numeros para dni.
     * @param entrada
     * @return 
     */
    public boolean validarSoloNumerosDNI(String entrada){
        return entrada.matches(soloNumerosDni);
    }
    
    /**
     * Valida que el campo solo contenga numeros para
     * el numero de colegiatura.
     * @param entrada
     * @return 
     */
    public boolean validarSoloNumerosColegiatura(String entrada){
        return entrada.matches(soloNumerosColegiatura);
    }
    
    /**
     * Valida que el campo solo contenga letras
     * @param entrada
     * @return true si solo contiene letras.
     * false si no contiene solo letras.
     */
    public boolean validarSoloLetras(String entrada){
        entrada  = entrada.toLowerCase();
        return entrada.matches(soloLetras);
    }
    
    /**
     * Valida que una fecha tenga el formato dd/MM/yyyy
     * y que ademas sea valido
     * @param entrada
     * @return 
     */
    public boolean validaFecha(String entrada){
        boolean resultado = true;
        SimpleDateFormat formato;
        formato = new SimpleDateFormat("dd/MM/yyyy");
        formato.setLenient(false);
        try {
            formato.parse(entrada);
        } catch (ParseException ex) {
            Logger.getLogger(ValidaEntrada.class.getName()).log(Level.SEVERE, null, ex);
            resultado = false;
        }
        return resultado;
    }
    
    /**
     * Valida letras recorriendo una cadena que define las
     * letras que se permiten
     * @param entrada
     * @return 
     */
    public boolean validarSoloLetras2(String entrada){
        int i;
        boolean resultado = true;
        entrada = entrada.toLowerCase();
        for (i = 0;  i < entrada.length(); i++){
            if(letrasValidas.indexOf(entrada.charAt(i))== -1){
                resultado = false;
                break;
            }
        }
        return resultado;
    }
    
    /**
     * Validara los caracteres permitidos en un domicilio
     * @param entrada
     * @return 
     */
    public boolean validarCaracteresDomicilio(String entrada){
        int i;
        boolean resultado = true;
        entrada = entrada.toLowerCase();
        entrada = entrada.replaceAll("\n","|");
        for (i = 0;  i < entrada.length(); i++){
            if(caracteresDomicilio.indexOf(entrada.charAt(i))== -1){
                resultado = false;
                break;
            }
        }
        return resultado;
    }
    
    /**
     * Valida que una la cadena recibida como parametro contenga
     * los caracteres permitidos para un telefono
     * @param entrada
     * @return 
     */
    public boolean validarTextoTelefono(String entrada){
        int i;
        boolean resultado = true;
        entrada = entrada.toLowerCase();
        entrada = entrada.replaceAll("\n","|");
        for (i = 0;  i < entrada.length(); i++){
            if(caracteresTelefono.indexOf(entrada.charAt(i))== -1){
                resultado = false;
                break;
            }
        }
        return resultado;
    }
    
    public boolean validarEmail(String entrada){
        entrada  = entrada.toLowerCase();
        return entrada.matches(expRegEmail);
    }
}
