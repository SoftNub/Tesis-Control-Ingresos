/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.spbeans;

import java.sql.Types;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

/**
 *
 * @author wilson
 */
public class SpGrabarPersonaPreInscripcion extends StoredProcedure {
    public static final String SPROC_NAME = "mc_sp_grabar_persona";
    public static final String PARAM_IN_DNI = "_dni";
    public static final String PARAM_IN_APEPATERNO = "appaterno";
    public static final String PARAM_IN_APEMATERNO = "apmaterno";
    public static final String PARAM_IN_NOMBRES = "nom";
    public static final String PARAM_IN_IDESTADOCIVIL = "idEstadoCivil";
    public static final String PARAM_IN_GSANGUINEO = "gsanguineo";
    public static final String PARAM_IN_TIPOSANGRE = "tiposang";
    public static final String PARAM_IN_FECHANAC = "fechanac";
    public static final String PARAM_IN_IDPAISORIGEN = "idPaisOrigen";
    public static final String PARAM_IN_IDDEPARTAMENTOORIGEN = "idDepartOrigen";
    public static final String PARAM_IN_IDPROVINCIAORIGEN = "idProvOrigen";
    public static final String PARAM_IN_IDDISTRITOORIGEN = "idDistOrigen";
    public static final String PARAM_IN_IDCONDCASA = "idCondCasa";
    public static final String PARAM_IN_LIBRETAMILITAR = "libretamil";
    public static final String PARAM_IN_CARNETEXT = "carnetext";
    public static final String PARAM_IN_FAPNP = "fa_pnp";
    public static final String PARAM_IN_CARNETSALUD = "carnetsalud";
    public static final String PARAM_IN_DIR_ACT = "direcion_domi";
    public static final String PARAM_IN_IDPROVACT = "idProvActual";
    public static final String PARAM_IN_IDDISTACT = "idDistActual";
    public static final String PARAM_IN_TELFACT = "telfDomicilio";
    public static final String PARAM_IN_EMAIL1 = "email1";
    public static final String PARAM_IN_EMAIL2 = "email2";
    public static final String PARAM_IN_CODPOSTAL = "codigoPostal";
    public static final String PARAM_IN_USUARIO = "_usuario";
    public static final String PARAM_OUT_IND = "ind";
    public static final String PARAM_OUT_MSJ = "msj";
    
    public SpGrabarPersonaPreInscripcion(DataSource dataSource) {
        super(dataSource, SPROC_NAME);
        declareParameter(new SqlParameter(PARAM_IN_DNI, Types.VARCHAR));
        declareParameter(new SqlParameter(PARAM_IN_APEPATERNO, Types.VARCHAR));
        declareParameter(new SqlParameter(PARAM_IN_APEMATERNO, Types.VARCHAR));
        declareParameter(new SqlParameter(PARAM_IN_NOMBRES, Types.VARCHAR));
        declareParameter(new SqlParameter(PARAM_IN_IDESTADOCIVIL, Types.INTEGER));
        declareParameter(new SqlParameter(PARAM_IN_GSANGUINEO, Types.VARCHAR));
        declareParameter(new SqlParameter(PARAM_IN_TIPOSANGRE, Types.VARCHAR));
        declareParameter(new SqlParameter(PARAM_IN_FECHANAC, Types.VARCHAR));
        declareParameter(new SqlParameter(PARAM_IN_IDPAISORIGEN, Types.INTEGER));
        declareParameter(new SqlParameter(PARAM_IN_IDDEPARTAMENTOORIGEN, Types.INTEGER));
        declareParameter(new SqlParameter(PARAM_IN_IDPROVINCIAORIGEN, Types.INTEGER));
        declareParameter(new SqlParameter(PARAM_IN_IDDISTRITOORIGEN, Types.INTEGER));
        declareParameter(new SqlParameter(PARAM_IN_IDCONDCASA, Types.INTEGER));
        declareParameter(new SqlParameter(PARAM_IN_LIBRETAMILITAR, Types.VARCHAR));
        declareParameter(new SqlParameter(PARAM_IN_CARNETEXT, Types.VARCHAR));
        declareParameter(new SqlParameter(PARAM_IN_FAPNP, Types.VARCHAR));
        declareParameter(new SqlParameter(PARAM_IN_CARNETSALUD, Types.VARCHAR));
        declareParameter(new SqlParameter(PARAM_IN_DIR_ACT, Types.VARCHAR));
        declareParameter(new SqlParameter(PARAM_IN_IDPROVACT, Types.INTEGER));
        declareParameter(new SqlParameter(PARAM_IN_IDDISTACT, Types.INTEGER));
        declareParameter(new SqlParameter(PARAM_IN_TELFACT, Types.VARCHAR));
        declareParameter(new SqlParameter(PARAM_IN_EMAIL1, Types.VARCHAR));
        declareParameter(new SqlParameter(PARAM_IN_EMAIL2, Types.VARCHAR));
        declareParameter(new SqlParameter(PARAM_IN_CODPOSTAL, Types.VARCHAR));
        declareParameter(new SqlParameter(PARAM_IN_USUARIO, Types.VARCHAR));
        declareParameter(new SqlOutParameter(PARAM_OUT_IND, Types.INTEGER));
        declareParameter(new SqlOutParameter(PARAM_OUT_MSJ, Types.VARCHAR));
        compile();
    }
    
    public Map<String, Object> execute(Map<String,?> campos) {
        Map<String, Object> result = super.execute(campos);  
        return result;
    }
}
