/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.dao;

import com.whnm.sicqfdp.beans.Colegiado;
import com.whnm.sicqfdp.beans.CustomUser;
import com.whnm.sicqfdp.beans.ExperienciaLaboral;
import com.whnm.sicqfdp.beans.Familiar;
import com.whnm.sicqfdp.beans.ListColegiado;
import com.whnm.sicqfdp.beans.OtrosGrados;
import com.whnm.sicqfdp.beans.Parametros;
import com.whnm.sicqfdp.beans.Titulacion;
import com.whnm.sicqfdp.interfaces.ColegiadoDao;
import com.whnm.sicqfdp.spbeans.SpGrabarColegiadoEgreso;
import com.whnm.sicqfdp.spbeans.SpGrabarColegiadoInscripcion;
import com.whnm.sicqfdp.spbeans.SpGrabarSolicitudEgreso;
import com.whnm.sicqfdp.spbeans.SpListarColegiado;
import com.whnm.sicqfdp.spbeans.SpListarColegiadoPago;
import java.util.ArrayList;
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
@Service(value = "colegiadoDao")
public class ColegiadoDaoImpl implements ColegiadoDao{
    private DataSource dataSource;
    private SpListarColegiado spListarColegiado;
    private SpGrabarColegiadoInscripcion spGrabarColegiadoInscripcion;
    private SpGrabarSolicitudEgreso spGrabarSolicitudEgreso;
    private SpGrabarColegiadoEgreso spGrabarColegiadoEgreso;
    private SpListarColegiadoPago spListarColegiadoPago;
    
    
    @Autowired
    public ColegiadoDaoImpl(@Qualifier("dataSource1")DataSource dataSource) {
        this.dataSource = dataSource;
        this.spListarColegiado = new SpListarColegiado(dataSource);
        this.spGrabarColegiadoInscripcion = new SpGrabarColegiadoInscripcion(dataSource);
        this.spGrabarSolicitudEgreso = new SpGrabarSolicitudEgreso(dataSource);
        this.spGrabarColegiadoEgreso = new SpGrabarColegiadoEgreso(dataSource);
        this.spListarColegiadoPago = new SpListarColegiadoPago(dataSource);
    }
   
    @Override
    public Colegiado listarColegiado(Integer opc, Colegiado per) {
        Colegiado persona = new Colegiado();
        String tramaExpLaboral;
        String tramaFamilia;
        String tramaGrados;
        ExperienciaLaboral expLab;
        Familiar familia;
        Titulacion tituloQF;
        OtrosGrados otrosEstudios;
        List<ExperienciaLaboral> expeLaborales;
        List<Familiar> familias;
        List<OtrosGrados> estudios;
        Map<String,Object> vars = new HashMap<String,Object>();
        vars.put(SpListarColegiado.PARAM_IN_OPC, opc);
        vars.put(SpListarColegiado.PARAM_IN_DNI, per.getDni());
        vars.put(SpListarColegiado.PARAM_IN_NUMCOLEGIATURA, per.getNumColegiatura());
        vars.put(SpListarColegiado.PARAM_IN_ESTADO, (per.getEstado() == null ? 0 : per.getEstado()));
        try {
            Map<String, Object> result = (Map<String, Object>) spListarColegiado.execute(vars);
            List<Map<String, Object>> listResult = (List<Map<String, Object>>) result.get(Parametros.RESULSET);
            persona.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            persona.setMsjError(result.get(Parametros.MSJ).toString());
            if (persona.getIndError() == 0){
               for (Map<String, Object> item : listResult) {
                    persona.setDni(item.get("DNI") != null ? item.get("DNI").toString() : "");
                    persona.setNumColegiatura(item.get("num_colegiatura") != null ? item.get("num_colegiatura").toString() : "");
                    persona.setFechaIngreso(item.get("fecha_ingreso") != null ? item.get("fecha_ingreso").toString() : "");
                    persona.setFechaInscripcion(item.get("fecha_inscripcion") != null ? item.get("fecha_inscripcion").toString() : "");
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
                    persona.setEsColegiado(item.get("es_colegiado") != null ? Integer.parseInt(String.valueOf(item.get("es_colegiado"))) : 0);
                    persona.setIndExiste(item.get("ind_existe") != null ? Integer.parseInt(String.valueOf(item.get("ind_existe"))) : 0);
                    
                    tituloQF = new Titulacion();
                    tituloQF.setId(item.get("idTituloQf") != null ? Integer.parseInt(String.valueOf(item.get("idTituloQf"))) : null);
                    tituloQF.setUnivTitulacion(item.get("uniTituloQf") != null ? String.valueOf(item.get("uniTituloQf")) : "");
                    tituloQF.setAnnoTitulacion(item.get("annoTitulo") != null ? String.valueOf(item.get("annoTitulo")) : "");
                    tituloQF.setNumTitulo(item.get("numTitulo") != null ? String.valueOf(item.get("numTitulo")) : "");
                    tituloQF.setNumRegistro(item.get("numRegistro") != null ? String.valueOf(item.get("numRegistro")) : "");
                    
                    persona.setTitulacion(tituloQF);
                    
                    tramaExpLaboral = item.get("trama_exp") != null ? String.valueOf(item.get("trama_exp")) : "";
                    tramaFamilia = item.get("trama_fam") != null ? String.valueOf(item.get("trama_fam")) : "";
                    tramaGrados = item.get("trama_grados") != null ? String.valueOf(item.get("trama_grados")) : "";
                    
                    expeLaborales = new ArrayList<>();
                    if (!tramaExpLaboral.isEmpty()){
                        String Split1 [] = tramaExpLaboral.split(",");
                        for(String datos1 : Split1){
                            String Split2[] = datos1.split("\\|");
                            expLab = new ExperienciaLaboral();
                            expLab.setId(Integer.parseInt(Split2[0].isEmpty()? null:Split2[0]));
                            expLab.setNumColegiatura(Split2[1]);
                            expLab.setCentroTrabajo(Split2[2]);
                            expLab.setRegimenLaboral(Split2[3]);
                            expLab.setTelefono(Split2[4]);
                            expLab.setFax(Split2[5]);
                            expeLaborales.add(expLab);
                        }
                    } 
                    persona.setExpeLaborales(expeLaborales);
                    
                    familias = new ArrayList<>();
                    if (!tramaFamilia.isEmpty()){
                        String Split3 [] = tramaFamilia.split(",");
                        for(String datos1 : Split3){
                            String Split4[] = datos1.split("\\|");
                            familia = new Familiar();
                            familia.setId(Integer.parseInt(Split4[0].isEmpty()? null:Split4[0]));
                            familia.setNumColegiatura(Split4[1]);
                            familia.setNombre(Split4[2]);
                            familia.setParentesco(Split4[3]);
                            familia.setEdad(Split4[4]);
                            familias.add(familia);
                        }
                    }
                    persona.setFamiliares(familias);
                    
                    estudios = new ArrayList<>();
                     if (!tramaGrados.isEmpty()){
                        String Split5 [] = tramaGrados.split(",");
                        for(String datos1 : Split5){
                            String Split6[] = datos1.split("\\|");
                            otrosEstudios = new OtrosGrados();
                            otrosEstudios.setId(Integer.parseInt(Split6[0].isEmpty()? null:Split6[0]));
                            otrosEstudios.setNumColegiatura(Split6[1]);
                            otrosEstudios.setTipo(Integer.parseInt(Split6[2].isEmpty()? null:Split6[2]));
                            otrosEstudios.setDenominacion(Split6[3]);
                            estudios.add(otrosEstudios);
                        }
                    }
                    persona.setOtrosEstudios(estudios); 
               }     
            } 
        } catch(Exception ex){
            Logger.getLogger(ColegiadoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            persona.setIndError(1);
            persona.setMsjError("Error: ["+ex.getMessage()+"]");        
        }
        return persona;
    }

    @Override
    public Colegiado grabarColegiado(Integer opc, Integer indicador, Colegiado elemento,
            CustomUser user) {
        Colegiado persona = new Colegiado();
        Map<String,Object> vars = new HashMap<String,Object>();
        vars.put(SpGrabarColegiadoInscripcion.PARAM_IN_OPC, opc);
        vars.put(SpGrabarColegiadoInscripcion.PARAM_IN_DNI, elemento.getDni());
        vars.put(SpGrabarColegiadoInscripcion.PARAM_IN_APEPATERNO, elemento.getApePaterno());
        vars.put(SpGrabarColegiadoInscripcion.PARAM_IN_APEMATERNO, elemento.getApeMaterno());
        vars.put(SpGrabarColegiadoInscripcion.PARAM_IN_NOMBRES, elemento.getNombres());
        vars.put(SpGrabarColegiadoInscripcion.PARAM_IN_IDESTADOCIVIL, elemento.getIdEstadoCivil());
        vars.put(SpGrabarColegiadoInscripcion.PARAM_IN_GSANGUINEO, elemento.getGrupoSanguineo());
        vars.put(SpGrabarColegiadoInscripcion.PARAM_IN_TIPOSANGRE, elemento.getRh());
        vars.put(SpGrabarColegiadoInscripcion.PARAM_IN_FECHANAC, elemento.getFechaNacimiento());
        vars.put(SpGrabarColegiadoInscripcion.PARAM_IN_IDPAISORIGEN, elemento.getIdPaisOri());
        vars.put(SpGrabarColegiadoInscripcion.PARAM_IN_IDDEPARTAMENTOORIGEN, elemento.getIdDepartamentoOri());
        vars.put(SpGrabarColegiadoInscripcion.PARAM_IN_IDPROVINCIAORIGEN, elemento.getIdProvinciaOri());
        vars.put(SpGrabarColegiadoInscripcion.PARAM_IN_IDDISTRITOORIGEN, elemento.getIdDistritoOri());
        vars.put(SpGrabarColegiadoInscripcion.PARAM_IN_IDCONDCASA, elemento.getIdCondicionCasa());
        vars.put(SpGrabarColegiadoInscripcion.PARAM_IN_LIBRETAMILITAR, elemento.getLibretaMilitar());
        vars.put(SpGrabarColegiadoInscripcion.PARAM_IN_CARNETEXT, elemento.getCarnetExt());
        vars.put(SpGrabarColegiadoInscripcion.PARAM_IN_FAPNP, elemento.getFfaa_pnp());
        vars.put(SpGrabarColegiadoInscripcion.PARAM_IN_CARNETSALUD, elemento.getCarnetEssalud());
        vars.put(SpGrabarColegiadoInscripcion.PARAM_IN_DIR_ACT, elemento.getDomicilio());
        vars.put(SpGrabarColegiadoInscripcion.PARAM_IN_IDPROVACT, elemento.getIdProvinciaAct());
        vars.put(SpGrabarColegiadoInscripcion.PARAM_IN_IDDISTACT, elemento.getIdDistritoAct());
        vars.put(SpGrabarColegiadoInscripcion.PARAM_IN_TELFACT, elemento.getTelfDomicilio());
        vars.put(SpGrabarColegiadoInscripcion.PARAM_IN_EMAIL1, elemento.getEmailPrincipal());
        vars.put(SpGrabarColegiadoInscripcion.PARAM_IN_EMAIL2, elemento.getEmailSecundario());
        vars.put(SpGrabarColegiadoInscripcion.PARAM_IN_CODPOSTAL, elemento.getCodigoPostal());
        vars.put(SpGrabarColegiadoInscripcion.PARAM_IN_NUMCOLEGIATURA, elemento.getNumColegiatura());
        if(opc == 1){
            if(indicador == 0) {
                vars.put(SpGrabarColegiadoInscripcion.PARAM_IN_FECHACOLEGIATURA, elemento.getFechaInscripcion());
            } else {
                vars.put(SpGrabarColegiadoInscripcion.PARAM_IN_FECHACOLEGIATURA, elemento.getFechaIngreso());
            }
        } else {
            vars.put(SpGrabarColegiadoInscripcion.PARAM_IN_FECHACOLEGIATURA, elemento.getFechaInscripcion());
        }    
        vars.put(SpGrabarColegiadoInscripcion.PARAM_IN_INDINGRESO, indicador);
        
        /*Elaborando tramas*/
        String tramaExpLab = "";
        if(elemento.getExpeLaborales() != null){
            for(ExperienciaLaboral item : elemento.getExpeLaborales()){
                tramaExpLab += (item.getId() != null ? item.getId() : "0")+"|"
                        + (item.getNumColegiatura() != null ? item.getNumColegiatura() : "")+"|"
                        + (item.getCentroTrabajo()!= null ? item.getCentroTrabajo() : " ")+"|"
                        + (item.getRegimenLaboral()!= null ? item.getRegimenLaboral() : " ")+"|"
                        + (item.getTelefono()!= null ? item.getTelefono() : " ")+"|"
                        + (item.getFax()!= null ? item.getFax(): " ")+"|"
                        + (item.getIndEliminacion()!= null ? item.getIndEliminacion(): "0")+"|*";
            }
        }
        String tramaFamilia = "";
        if(elemento.getFamiliares() != null){
            for(Familiar item : elemento.getFamiliares()){
                tramaFamilia += (item.getId() != null ? item.getId() : "0")+"|"
                        + (item.getNumColegiatura() != null ? item.getNumColegiatura() : "")+"|"
                        + (item.getNombre()!= null ? item.getNombre() : " ")+"|"
                        + (item.getParentesco()!= null ? item.getParentesco(): " ")+"|"
                        + (item.getEdad()!= null ? item.getEdad() : " ")+"|"
                        + (item.getIndEliminacion()!= null ? item.getIndEliminacion(): "0")+"|*";
            }
        }
        String tramaGrados = "";
        if(elemento.getOtrosEstudios()!= null){
            for(OtrosGrados item : elemento.getOtrosEstudios()){
                tramaGrados += (item.getId() != null ? item.getId() : "0")+"|"
                        + (item.getNumColegiatura() != null ? item.getNumColegiatura() : "")+"|"
                        + (item.getDenominacion()!= null ? item.getDenominacion() : " ")+"|"
                        + (item.getTipo()!= null ? item.getTipo() : "")+"|"
                        + (item.getIndEliminacion()!= null ? item.getIndEliminacion(): "0")+"|*";
            }
        }
        vars.put(SpGrabarColegiadoInscripcion.PARAM_IN_TRAMA_EXPLAB, tramaExpLab);
        vars.put(SpGrabarColegiadoInscripcion.PARAM_IN_TRAMA_DATOSFAM, tramaFamilia);
        vars.put(SpGrabarColegiadoInscripcion.PARAM_IN_IDTITULO, elemento.getTitulacion().getId());
        vars.put(SpGrabarColegiadoInscripcion.PARAM_IN_UNITITULO, elemento.getTitulacion().getUnivTitulacion());
        vars.put(SpGrabarColegiadoInscripcion.PARAM_IN_ANNOTITULO, elemento.getTitulacion().getAnnoTitulacion());
        vars.put(SpGrabarColegiadoInscripcion.PARAM_IN_NUMTITULO, elemento.getTitulacion().getNumTitulo());
        vars.put(SpGrabarColegiadoInscripcion.PARAM_IN_NUMREGISTRO, elemento.getTitulacion().getNumRegistro());
        vars.put(SpGrabarColegiadoInscripcion.PARAM_IN_TRAMA_GRADOS, tramaGrados);
        vars.put(SpGrabarColegiadoInscripcion.PARAM_IN_IND_EXISTE, elemento.getIndExiste());
        vars.put(SpGrabarColegiadoInscripcion.PARAM_IN_ES_COLEGIADO, elemento.getEsColegiado());
        vars.put(SpGrabarColegiadoInscripcion.PARAM_IN_USUARIO, user.getUsername());
        try {
            Map<String, Object> result = (Map<String, Object>) spGrabarColegiadoInscripcion.execute(vars);
            persona.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            persona.setMsjError(result.get(Parametros.MSJ).toString());
        } catch(Exception ex){
            Logger.getLogger(ColegiadoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            persona.setIndError(1);
            persona.setMsjError("Error: ["+ex.getMessage()+"]");        
        }
        return persona;
    }

    @Override
    public Colegiado grabar(Colegiado elemento, CustomUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Colegiado editar(Colegiado elemento, CustomUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Colegiado eliminar(Colegiado elemento, CustomUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Colegiado listar(Colegiado elemento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Colegiado grabarSolicitudColegiatura(Colegiado col, CustomUser user) {
        Colegiado persona = new Colegiado();
        Map<String,Object> vars = new HashMap<String,Object>();
        vars.put(SpGrabarSolicitudEgreso.PARAM_IN_DNI, col.getDni());
        vars.put(SpGrabarSolicitudEgreso.PARAM_IN_NUMCOLEGIATURA, col.getNumColegiatura());
        vars.put(SpGrabarSolicitudEgreso.PARAM_IN_USUARIO, user.getUsername());
        try {
            Map<String, Object> result = (Map<String, Object>) spGrabarSolicitudEgreso.execute(vars);
            persona.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            persona.setMsjError(result.get(Parametros.MSJ).toString());
        } catch(Exception ex){
            Logger.getLogger(ColegiadoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            persona.setIndError(1);
            persona.setMsjError("Error: ["+ex.getMessage()+"]");        
        }
        return persona;
    }

    @Override
    public ListColegiado buscarColegiadoEgreso(Integer opc, Colegiado col) {
        ListColegiado listaColegiados = new ListColegiado();
        List<Colegiado> lista = new ArrayList<>();
        Colegiado persona;
        String tramaExpLaboral;
        String tramaFamilia;
        String tramaGrados;
        ExperienciaLaboral expLab;
        Familiar familia;
        Titulacion tituloQF;
        OtrosGrados otrosEstudios;
        List<ExperienciaLaboral> expeLaborales;
        List<Familiar> familias;
        List<OtrosGrados> estudios;
        Map<String,Object> vars = new HashMap<String,Object>();
        vars.put(SpListarColegiado.PARAM_IN_OPC, opc);
        vars.put(SpListarColegiado.PARAM_IN_DNI, col.getDni());
        vars.put(SpListarColegiado.PARAM_IN_NUMCOLEGIATURA, col.getNumColegiatura());
        try {
            Map<String, Object> result = (Map<String, Object>) spListarColegiado.execute(vars);
            List<Map<String, Object>> listResult = (List<Map<String, Object>>) result.get(Parametros.RESULSET);
            listaColegiados.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            listaColegiados.setMsjError(result.get(Parametros.MSJ).toString());
            if (listaColegiados.getIndError() == 0){
               for (Map<String, Object> item : listResult) {
                    persona = new Colegiado(); 
                    persona.setDni(item.get("DNI") != null ? item.get("DNI").toString() : "");
                    persona.setNumColegiatura(item.get("num_colegiatura") != null ? item.get("num_colegiatura").toString() : "");
                    persona.setFechaIngreso(item.get("fecha_ingreso") != null ? item.get("fecha_ingreso").toString() : "");
                    persona.setFechaInscripcion(item.get("fecha_inscripcion") != null ? item.get("fecha_inscripcion").toString() : "");
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
                    persona.setEsColegiado(item.get("es_colegiado") != null ? Integer.parseInt(String.valueOf(item.get("es_colegiado"))) : 0);
                    persona.setIndExiste(item.get("ind_existe") != null ? Integer.parseInt(String.valueOf(item.get("ind_existe"))) : 0);
                    
                    tituloQF = new Titulacion();
                    tituloQF.setId(item.get("idTituloQf") != null ? Integer.parseInt(String.valueOf(item.get("idTituloQf"))) : null);
                    tituloQF.setUnivTitulacion(item.get("uniTituloQf") != null ? String.valueOf(item.get("uniTituloQf")) : "");
                    tituloQF.setAnnoTitulacion(item.get("annoTitulo") != null ? String.valueOf(item.get("annoTitulo")) : "");
                    tituloQF.setNumTitulo(item.get("numTitulo") != null ? String.valueOf(item.get("numTitulo")) : "");
                    tituloQF.setNumRegistro(item.get("numRegistro") != null ? String.valueOf(item.get("numRegistro")) : "");
                    
                    persona.setTitulacion(tituloQF);
                    
                    tramaExpLaboral = item.get("trama_exp") != null ? String.valueOf(item.get("trama_exp")) : "";
                    tramaFamilia = item.get("trama_fam") != null ? String.valueOf(item.get("trama_fam")) : "";
                    tramaGrados = item.get("trama_grados") != null ? String.valueOf(item.get("trama_grados")) : "";
                    
                    expeLaborales = new ArrayList<>();
                    if (!tramaExpLaboral.isEmpty()){
                        String Split1 [] = tramaExpLaboral.split(",");
                        for(String datos1 : Split1){
                            String Split2[] = datos1.split("\\|");
                            expLab = new ExperienciaLaboral();
                            expLab.setId(Integer.parseInt(Split2[0].isEmpty()? null:Split2[0]));
                            expLab.setNumColegiatura(Split2[1]);
                            expLab.setCentroTrabajo(Split2[2]);
                            expLab.setRegimenLaboral(Split2[3]);
                            expLab.setTelefono(Split2[4]);
                            expLab.setFax(Split2[5]);
                            expeLaborales.add(expLab);
                        }
                    } 
                    persona.setExpeLaborales(expeLaborales);
                    
                    familias = new ArrayList<>();
                    if (!tramaFamilia.isEmpty()){
                        String Split3 [] = tramaFamilia.split(",");
                        for(String datos1 : Split3){
                            String Split4[] = datos1.split("\\|");
                            familia = new Familiar();
                            familia.setId(Integer.parseInt(Split4[0].isEmpty()? null:Split4[0]));
                            familia.setNumColegiatura(Split4[1]);
                            familia.setNombre(Split4[2]);
                            familia.setParentesco(Split4[3]);
                            familia.setEdad(Split4[4]);
                            familias.add(familia);
                        }
                    }
                    persona.setFamiliares(familias);
                    
                    estudios = new ArrayList<>();
                     if (!tramaGrados.isEmpty()){
                        String Split5 [] = tramaGrados.split(",");
                        for(String datos1 : Split5){
                            String Split6[] = datos1.split("\\|");
                            otrosEstudios = new OtrosGrados();
                            otrosEstudios.setId(Integer.parseInt(Split6[0].isEmpty()? null:Split6[0]));
                            otrosEstudios.setNumColegiatura(Split6[1]);
                            otrosEstudios.setTipo(Integer.parseInt(Split6[2].isEmpty()? null:Split6[2]));
                            otrosEstudios.setDenominacion(Split6[3]);
                            estudios.add(otrosEstudios);
                        }
                    }
                    persona.setOtrosEstudios(estudios); 
                    lista.add(persona);
               }
               listaColegiados.setListaColegiados(lista);
            } 
        } catch(Exception ex){
            Logger.getLogger(ColegiadoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            listaColegiados.setIndError(1);
            listaColegiados.setMsjError("Error: ["+ex.getMessage()+"]");        
        }
        return listaColegiados;
    }

    @Override
    public ListColegiado grabarEgresoColegiado(ListColegiado listaColegiados, CustomUser user) {
        ListColegiado persona = new ListColegiado();
        Map<String,Object> vars = new HashMap<String,Object>();
        /*Elaborando trama*/
        String trama = "";
        if(listaColegiados.getListaColegiados()!= null){
            for(Colegiado item : listaColegiados.getListaColegiados()){
                if(item.getIndSolicitud() != null){
                    if ( item.getIndSolicitud() == 2){
                        trama   += (item.getDni()!= null ? item.getDni() : "")+"|"
                                + (item.getNumColegiatura() != null ? item.getNumColegiatura() : "")+"|*";
                    }
                }
            }
        }
       
        vars.put(SpGrabarColegiadoEgreso.PARAM_IN_TRAMA, trama);
        vars.put(SpGrabarColegiadoEgreso.PARAM_IN_USUARIO, user.getUsername());
        try {
            Map<String, Object> result = (Map<String, Object>) spGrabarColegiadoEgreso.execute(vars);
            persona.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            persona.setMsjError(result.get(Parametros.MSJ).toString());
        } catch(Exception ex){
            Logger.getLogger(ColegiadoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            persona.setIndError(1);
            persona.setMsjError("Error: ["+ex.getMessage()+"]");        
        }
        return persona;
    }

    @Override
    public ListColegiado buscarColegiadosPagos(String tipoOperacion, Integer tipoDocumento, Colegiado col) {
        ListColegiado listaColegiados = new ListColegiado();
        List<Colegiado> lista = new ArrayList<>();
        Colegiado persona;
        Map<String,Object> vars = new HashMap<String,Object>();
        vars.put(SpListarColegiadoPago.PARAM_IN_OPC, tipoOperacion);
        vars.put(SpListarColegiadoPago.PARAM_IN_TIPO_DOC, tipoDocumento);
        if(tipoOperacion.equals("C")){
            vars.put(SpListarColegiadoPago.PARAM_IN_DOC, col.getNumColegiatura());
        } else {
            vars.put(SpListarColegiadoPago.PARAM_IN_DOC, col.getDni());
        }
        try {
            Map<String, Object> result = (Map<String, Object>) spListarColegiadoPago.execute(vars);
            List<Map<String, Object>> listResult = (List<Map<String, Object>>) result.get(Parametros.RESULSET);
            listaColegiados.setIndError(Integer.parseInt(String.valueOf(result.get(Parametros.IND))));
            listaColegiados.setMsjError(result.get(Parametros.MSJ).toString());
            if (listaColegiados.getIndError() == 0){
               for (Map<String, Object> item : listResult) {
                    persona = new Colegiado(); 
                    persona.setNombres(item.get("Nombre") != null ? item.get("Nombre").toString() : "");
                    persona.setEstado(item.get("estado") != null ? Integer.parseInt(item.get("estado").toString()) : -1);
                    persona.setDni(item.get("DNI") != null ? item.get("DNI").toString() : "");
                    persona.setNumColegiatura(item.get("num_colegiatura") != null ? item.get("num_colegiatura").toString() : "");
                    lista.add(persona);
               }
               listaColegiados.setListaColegiados(lista);
            } 
        } catch(Exception ex){
            Logger.getLogger(ColegiadoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            listaColegiados.setIndError(1);
            listaColegiados.setMsjError("Error: ["+ex.getMessage()+"]");        
        }
        return listaColegiados;
    }
}
