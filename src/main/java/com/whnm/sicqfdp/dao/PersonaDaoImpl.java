/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.dao;

import com.whnm.sicqfdp.beans.CustomUser;
import com.whnm.sicqfdp.beans.Parametros;
import com.whnm.sicqfdp.beans.Persona;
import com.whnm.sicqfdp.interfaces.PersonaDao;
import com.whnm.sicqfdp.spbeans.SpActualizarPersonaPreInscripcion;
import com.whnm.sicqfdp.spbeans.SpGrabarPersonaPreInscripcion;
import com.whnm.sicqfdp.spbeans.SpListarPersona;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author wilson
 */
@Service(value = "personaDao")
public class PersonaDaoImpl implements PersonaDao{
    private DataSource dataSource;
    private SpListarPersona spListarPersona;
    private SpGrabarPersonaPreInscripcion spGrabarPersonaPreInscripcion;
    private SpActualizarPersonaPreInscripcion spActualizarPersonaPreInscripcion;
    
    @Autowired
    public PersonaDaoImpl(@Qualifier("dataSource1")DataSource dataSource) {
        this.dataSource = dataSource;
        this.spListarPersona = new SpListarPersona(dataSource);
        this.spGrabarPersonaPreInscripcion = new SpGrabarPersonaPreInscripcion(dataSource);
        this.spActualizarPersonaPreInscripcion = new SpActualizarPersonaPreInscripcion(dataSource);
    }
    
    @Override
    public Persona grabar(Persona elemento, CustomUser user) {
        Persona persona = new Persona();
        Map<String,Object> vars = new HashMap<String,Object>();
        vars.put(SpGrabarPersonaPreInscripcion.PARAM_IN_DNI, elemento.getDni());
        vars.put(SpGrabarPersonaPreInscripcion.PARAM_IN_APEPATERNO, elemento.getApePaterno());
        vars.put(SpGrabarPersonaPreInscripcion.PARAM_IN_APEMATERNO, elemento.getApeMaterno());
        vars.put(SpGrabarPersonaPreInscripcion.PARAM_IN_NOMBRES, elemento.getNombres());
        vars.put(SpGrabarPersonaPreInscripcion.PARAM_IN_IDESTADOCIVIL, elemento.getIdEstadoCivil());
        vars.put(SpGrabarPersonaPreInscripcion.PARAM_IN_GSANGUINEO, elemento.getGrupoSanguineo());
        vars.put(SpGrabarPersonaPreInscripcion.PARAM_IN_TIPOSANGRE, elemento.getRh());
        vars.put(SpGrabarPersonaPreInscripcion.PARAM_IN_FECHANAC, elemento.getFechaNacimiento());
        vars.put(SpGrabarPersonaPreInscripcion.PARAM_IN_IDPAISORIGEN, elemento.getIdPaisOri());
        vars.put(SpGrabarPersonaPreInscripcion.PARAM_IN_IDDEPARTAMENTOORIGEN, elemento.getIdDepartamentoOri());
        vars.put(SpGrabarPersonaPreInscripcion.PARAM_IN_IDPROVINCIAORIGEN, elemento.getIdProvinciaOri());
        vars.put(SpGrabarPersonaPreInscripcion.PARAM_IN_IDDISTRITOORIGEN, elemento.getIdDistritoOri());
        vars.put(SpGrabarPersonaPreInscripcion.PARAM_IN_IDCONDCASA, elemento.getIdCondicionCasa());
        vars.put(SpGrabarPersonaPreInscripcion.PARAM_IN_LIBRETAMILITAR, elemento.getLibretaMilitar());
        vars.put(SpGrabarPersonaPreInscripcion.PARAM_IN_CARNETEXT, elemento.getCarnetExt());
        vars.put(SpGrabarPersonaPreInscripcion.PARAM_IN_FAPNP, elemento.getFfaa_pnp());
        vars.put(SpGrabarPersonaPreInscripcion.PARAM_IN_CARNETSALUD, elemento.getCarnetEssalud());
        vars.put(SpGrabarPersonaPreInscripcion.PARAM_IN_DIR_ACT, elemento.getDomicilio());
        vars.put(SpGrabarPersonaPreInscripcion.PARAM_IN_IDPROVACT, elemento.getIdProvinciaAct());
        vars.put(SpGrabarPersonaPreInscripcion.PARAM_IN_IDDISTACT, elemento.getIdDistritoAct());
        vars.put(SpGrabarPersonaPreInscripcion.PARAM_IN_TELFACT, elemento.getTelfDomicilio());
        vars.put(SpGrabarPersonaPreInscripcion.PARAM_IN_EMAIL1, elemento.getEmailPrincipal());
        vars.put(SpGrabarPersonaPreInscripcion.PARAM_IN_EMAIL2, elemento.getEmailSecundario());
        vars.put(SpGrabarPersonaPreInscripcion.PARAM_IN_CODPOSTAL, elemento.getCodigoPostal());
        vars.put(SpGrabarPersonaPreInscripcion.PARAM_IN_USUARIO, user.getUsername());
        try {
            Map<String, Object> result = (Map<String, Object>) spGrabarPersonaPreInscripcion.execute(vars);
            persona.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            persona.setMsjError(result.get(Parametros.MSJ).toString());
        } catch(Exception ex){
            Logger.getLogger(DistritoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            persona.setIndError(1);
            persona.setMsjError("Error: ["+ex.getMessage()+"]");        
        }
        return persona;
    }

    @Override
    public Persona editar(Persona elemento, CustomUser user) {
        Persona persona = new Persona();
        Map<String,Object> vars = new HashMap<String,Object>();
        vars.put(SpActualizarPersonaPreInscripcion.PARAM_IN_DNI, elemento.getDni());
        vars.put(SpActualizarPersonaPreInscripcion.PARAM_IN_APEPATERNO, elemento.getApePaterno());
        vars.put(SpActualizarPersonaPreInscripcion.PARAM_IN_APEMATERNO, elemento.getApeMaterno());
        vars.put(SpActualizarPersonaPreInscripcion.PARAM_IN_NOMBRES, elemento.getNombres());
        vars.put(SpActualizarPersonaPreInscripcion.PARAM_IN_IDESTADOCIVIL, elemento.getIdEstadoCivil());
        vars.put(SpActualizarPersonaPreInscripcion.PARAM_IN_GSANGUINEO, elemento.getGrupoSanguineo());
        vars.put(SpActualizarPersonaPreInscripcion.PARAM_IN_TIPOSANGRE, elemento.getRh());
        vars.put(SpActualizarPersonaPreInscripcion.PARAM_IN_FECHANAC, elemento.getFechaNacimiento());
        vars.put(SpActualizarPersonaPreInscripcion.PARAM_IN_IDPAISORIGEN, elemento.getIdPaisOri());
        vars.put(SpActualizarPersonaPreInscripcion.PARAM_IN_IDDEPARTAMENTOORIGEN, elemento.getIdDepartamentoOri());
        vars.put(SpActualizarPersonaPreInscripcion.PARAM_IN_IDPROVINCIAORIGEN, elemento.getIdProvinciaOri());
        vars.put(SpActualizarPersonaPreInscripcion.PARAM_IN_IDDISTRITOORIGEN, elemento.getIdDistritoOri());
        vars.put(SpActualizarPersonaPreInscripcion.PARAM_IN_IDCONDCASA, elemento.getIdCondicionCasa());
        vars.put(SpActualizarPersonaPreInscripcion.PARAM_IN_LIBRETAMILITAR, elemento.getLibretaMilitar());
        vars.put(SpActualizarPersonaPreInscripcion.PARAM_IN_CARNETEXT, elemento.getCarnetExt());
        vars.put(SpActualizarPersonaPreInscripcion.PARAM_IN_FAPNP, elemento.getFfaa_pnp());
        vars.put(SpActualizarPersonaPreInscripcion.PARAM_IN_CARNETSALUD, elemento.getCarnetEssalud());
        vars.put(SpActualizarPersonaPreInscripcion.PARAM_IN_DIR_ACT, elemento.getDomicilio());
        vars.put(SpActualizarPersonaPreInscripcion.PARAM_IN_IDPROVACT, elemento.getIdProvinciaAct());
        vars.put(SpActualizarPersonaPreInscripcion.PARAM_IN_IDDISTACT, elemento.getIdDistritoAct());
        vars.put(SpActualizarPersonaPreInscripcion.PARAM_IN_TELFACT, elemento.getTelfDomicilio());
        vars.put(SpActualizarPersonaPreInscripcion.PARAM_IN_EMAIL1, elemento.getEmailPrincipal());
        vars.put(SpActualizarPersonaPreInscripcion.PARAM_IN_EMAIL2, elemento.getEmailSecundario());
        vars.put(SpActualizarPersonaPreInscripcion.PARAM_IN_CODPOSTAL, elemento.getCodigoPostal());
        vars.put(SpActualizarPersonaPreInscripcion.PARAM_IN_USUARIO, user.getUsername());
        try {
            Map<String, Object> result = (Map<String, Object>) spActualizarPersonaPreInscripcion.execute(vars);
            persona.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            persona.setMsjError(result.get(Parametros.MSJ).toString());
        } catch(Exception ex){
            Logger.getLogger(DistritoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            persona.setIndError(1);
            persona.setMsjError("Error: ["+ex.getMessage()+"]");        
        }
        return persona;
    }

    @Override
    public Persona eliminar(Persona elemento, CustomUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Persona listar(Persona elemento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Persona listarPersona(Integer opc, Persona per) {
        Persona persona = new Persona();
        Map<String,Object> vars = new HashMap<String,Object>();
        vars.put(SpListarPersona.PARAM_IN_OPC, opc);
        vars.put(SpListarPersona.PARAM_IN_DNI, per.getDni());
        try {
            Map<String, Object> result = (Map<String, Object>) spListarPersona.execute(vars);
            List<Map<String, Object>> listResult = (List<Map<String, Object>>) result.get(Parametros.RESULSET);
            persona.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            persona.setMsjError(result.get(Parametros.MSJ).toString());
            if (persona.getIndError() == 0){
               for (Map<String, Object> item : listResult) {
                    persona.setDni(item.get("DNI") != null ? item.get("DNI").toString() : "");
                    persona.setApePaterno(item.get("ApePaterno") != null ? item.get("ApePaterno").toString() : "");
                    persona.setApeMaterno(item.get("ApeMaterno") != null ? item.get("ApeMaterno").toString() : "");
                    persona.setNombres(item.get("Nombres") != null ? item.get("Nombres").toString() : "");
                    persona.setGrupoSanguineo(item.get("GrupoSanguineo") != null ? item.get("GrupoSanguineo").toString() : "");
                    persona.setRh(item.get("Rh") != null ? item.get("Rh").toString() : "");
                    persona.setFechaNacimiento(item.get("FechaNacimiento") != null ? item.get("FechaNacimiento").toString() : "");
                    persona.setLibretaMilitar(item.get("LibretaMilitar") != null ? item.get("LibretaMilitar").toString() : "");
                    persona.setCarnetExt(item.get("CarnetExt") != null ? item.get("CarnetExt").toString() : "");
                    persona.setFfaa_pnp(item.get("ffaa_pnp") != null ? item.get("ffaa_pnp").toString() : "");
                    persona.setCarnetEssalud(item.get("CarnetEssalud") != null ? item.get("CarnetEssalud").toString() : "");
                    persona.setDomicilio(item.get("Domicilio") != null ? item.get("Domicilio").toString() : "");
                    persona.setIdEstadoCivil(item.get("idEstadoCivil") != null ? Integer.parseInt(String.valueOf(item.get("idEstadoCivil"))) : 0);
                    persona.setIdPaisOri(item.get("idPaisOri") != null ? Integer.parseInt(String.valueOf(item.get("idPaisOri"))) : 0);
                    persona.setIdDepartamentoOri(item.get("idDepartamentoOri") != null ? Integer.parseInt(String.valueOf(item.get("idDepartamentoOri"))) : 0);
                    persona.setIdProvinciaOri(item.get("idProvinciaOri") != null ? Integer.parseInt(String.valueOf(item.get("idProvinciaOri"))) : 0);
                    persona.setIdDistritoOri(item.get("idDistritoOri") != null ? Integer.parseInt(String.valueOf(item.get("idDistritoOri"))) : 0);
                    persona.setIdCondicionCasa(item.get("idCondicionCasa") != null ? Integer.parseInt(String.valueOf(item.get("idCondicionCasa"))) : 0);
                    persona.setIdDistritoAct(item.get("idDistritoAct") != null ? Integer.parseInt(String.valueOf(item.get("idDistritoAct"))) : 0);
                    persona.setIdProvinciaAct(item.get("idProvinciaAct") != null ? Integer.parseInt(String.valueOf(item.get("idProvinciaAct"))) : 0);
                    persona.setTelfDomicilio(item.get("telfDomicilio") != null ? String.valueOf(item.get("telfDomicilio")) : "");
                    persona.setEmailPrincipal(item.get("emailPrincipal") != null ? String.valueOf(item.get("emailPrincipal")) : "");
                    persona.setEmailSecundario(item.get("emailSecundario") != null ? String.valueOf(item.get("emailSecundario")) : "");
                    persona.setCodigoPostal(item.get("codigo_postal") != null ? String.valueOf(item.get("codigo_postal")) : "");
                    persona.setIndExiste(item.get("ind_existe") != null ? Integer.parseInt(String.valueOf(item.get("ind_existe"))) : 0);
                }     
            } 
        } catch(Exception ex){
            Logger.getLogger(DistritoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            persona.setIndError(1);
            persona.setMsjError("Error: ["+ex.getMessage()+"]");        
        }
        return persona;
    }
    
}
