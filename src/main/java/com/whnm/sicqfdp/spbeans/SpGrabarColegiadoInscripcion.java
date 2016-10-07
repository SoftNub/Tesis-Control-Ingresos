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
public class SpGrabarColegiadoInscripcion extends StoredProcedure {
    public static final String SPROC_NAME = "mc_sp_mant_colegiado";
    public static final String PARAM_IN_OPC = "opc";
    public static final String PARAM_IN_DNI = "dni";
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
    public static final String PARAM_IN_NUMCOLEGIATURA = "numcolegiatura";
    public static final String PARAM_IN_FECHACOLEGIATURA = "fechaColegiatura";
    public static final String PARAM_IN_INDINGRESO = "ind_ingreso";
    public static final String PARAM_IN_TRAMA_EXPLAB = "trama_exp_lab";
    public static final String PARAM_IN_TRAMA_DATOSFAM = "trama_datos_fam";
    public static final String PARAM_IN_IDTITULO = "id_titulo";
    public static final String PARAM_IN_UNITITULO = "univ_titulo";
    public static final String PARAM_IN_ANNOTITULO = "anno_titulo";
    public static final String PARAM_IN_NUMTITULO = "num_titulo";
    public static final String PARAM_IN_NUMREGISTRO = "num_registro";
    public static final String PARAM_IN_TRAMA_GRADOS = "trama_grados";
    public static final String PARAM_IN_IND_EXISTE = "ind_existe";
    public static final String PARAM_IN_ES_COLEGIADO = "ind_es_coleg";
    public static final String PARAM_OUT_IND = "ind";
    public static final String PARAM_OUT_MSJ = "msj";
    
    public SpGrabarColegiadoInscripcion(DataSource dataSource) {
        super(dataSource, SPROC_NAME);
        declareParameter(new SqlParameter(PARAM_IN_OPC, Types.INTEGER));
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
        declareParameter(new SqlParameter(PARAM_IN_NUMCOLEGIATURA, Types.VARCHAR));
        declareParameter(new SqlParameter(PARAM_IN_FECHACOLEGIATURA, Types.VARCHAR));
        declareParameter(new SqlParameter(PARAM_IN_INDINGRESO, Types.INTEGER));
        declareParameter(new SqlParameter(PARAM_IN_TRAMA_EXPLAB, Types.VARCHAR));
        declareParameter(new SqlParameter(PARAM_IN_TRAMA_DATOSFAM, Types.VARCHAR));
        declareParameter(new SqlParameter(PARAM_IN_IDTITULO, Types.INTEGER));
        declareParameter(new SqlParameter(PARAM_IN_UNITITULO, Types.VARCHAR));
        declareParameter(new SqlParameter(PARAM_IN_ANNOTITULO, Types.VARCHAR));
        declareParameter(new SqlParameter(PARAM_IN_NUMTITULO, Types.VARCHAR));
        declareParameter(new SqlParameter(PARAM_IN_NUMREGISTRO, Types.VARCHAR));
        declareParameter(new SqlParameter(PARAM_IN_TRAMA_GRADOS, Types.VARCHAR));
        declareParameter(new SqlParameter(PARAM_IN_IND_EXISTE, Types.INTEGER));
        declareParameter(new SqlParameter(PARAM_IN_ES_COLEGIADO, Types.INTEGER));
        declareParameter(new SqlOutParameter(PARAM_OUT_IND, Types.INTEGER));
        declareParameter(new SqlOutParameter(PARAM_OUT_MSJ, Types.VARCHAR));
        compile();
    }
    
    public Map<String, Object> execute(Map<String,?> campos) {
        Map<String, Object> result = super.execute(campos);  
        return result;
    }
}
