package org.openmrs.module.arielreports.reporting.library.datasets;

import java.util.List;
import org.openmrs.module.arielreports.reporting.library.queries.ReportQueries;
import org.openmrs.module.reporting.dataset.definition.DataSetDefinition;
import org.openmrs.module.reporting.dataset.definition.SqlDataSetDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;

public class ArielReportsDataSets {

  public static DataSetDefinition getGravidasSemDataPartoDataSet(List<Parameter> parameters) {

    SqlDataSetDefinition dsd = new SqlDataSetDefinition();

    dsd.setName("GRAVIDAS DE MAIS DE 9 MESES SEM DATA DE PARTO");
    dsd.setParameters(parameters);
    dsd.setSqlQuery(ReportQueries.GRAVIDAS);

    return dsd;
  }

  public static DataSetDefinition getPacientesCVNegativaDataSet(List<Parameter> parameters) {

    SqlDataSetDefinition dsd = new SqlDataSetDefinition();

    dsd.setName("PACIENTES COM CARGA VIRAL NEGATIVA");
    dsd.setParameters(parameters);
    dsd.setSqlQuery(ReportQueries.PACIENTES_CV_NEGATIVA);

    return dsd;
  }

  public static DataSetDefinition getPacientesInscritosDataSet(List<Parameter> parameters) {

    SqlDataSetDefinition dsd = new SqlDataSetDefinition();

    dsd.setName("INSCRITOS");
    dsd.setParameters(parameters);
    dsd.setSqlQuery(ReportQueries.INSCRITOS_SERVICO_TARV);

    return dsd;
  }

  public static DataSetDefinition getPacientesCoorteTARVDataSet(List<Parameter> parameters) {

    SqlDataSetDefinition dsd = new SqlDataSetDefinition();

    dsd.setName("PACIENTES NA COORTE TARV");
    dsd.setParameters(parameters);
    dsd.setSqlQuery(ReportQueries.PACIENTES_COORTE_TARV);

    return dsd;
  }

  public static DataSetDefinition getVisitasComRastreiosProfilaxiasDataSet(
      List<Parameter> parameters) {

    SqlDataSetDefinition dsd = new SqlDataSetDefinition();

    dsd.setName("VISITAS QUE TIVERAM RASTREIOS OU PROFILAXIAS");
    dsd.setParameters(parameters);
    dsd.setSqlQuery(ReportQueries.VISITAS_RASTREIOS_PROFILAXIAS);

    return dsd;
  }

  public static DataSetDefinition getSuspeitosFalenciaDataSet(List<Parameter> parameters) {

    SqlDataSetDefinition dsd = new SqlDataSetDefinition();

    dsd.setName("PACIENTES COM SUSPEITA DE FALENCIA");
    dsd.setParameters(parameters);
    dsd.setSqlQuery(ReportQueries.SUSPEITOS_FALENCIA);

    return dsd;
  }

  public static DataSetDefinition getPopChaveGruposApoioMDSDataSet(List<Parameter> parameters) {

    SqlDataSetDefinition dsd = new SqlDataSetDefinition();

    dsd.setName("POLULACAO CHAVE OU GRUPOS DE APOIO OU EM MODELOS DIFERENCIADOS ");
    dsd.setParameters(parameters);
    dsd.setSqlQuery(ReportQueries.POP_CHAVE_GRUPOS_APOIO_MDS);

    return dsd;
  }

  public static DataSetDefinition getElegiveisTPI(List<Parameter> parameters) {

    SqlDataSetDefinition dsd = new SqlDataSetDefinition();

    dsd.setName("PACIENTES ELEGIVEIS A TPI");
    dsd.setParameters(parameters);
    dsd.setSqlQuery(ReportQueries.ELEGIVEIS_TPI);

    return dsd;
  }

  public static DataSetDefinition getPacientesQueDeviamTerminarTPI(List<Parameter> parameters) {

    SqlDataSetDefinition dsd = new SqlDataSetDefinition();

    dsd.setName("PACIENTES QUE DEVIAM TERMINAR TPI");
    dsd.setParameters(parameters);
    dsd.setSqlQuery(ReportQueries.PACIENTES_DEVIAM_TERMINAR_TPI);

    return dsd;
  }

  public static DataSetDefinition getElegiveisCargaViral(List<Parameter> parameters) {

    SqlDataSetDefinition dsd = new SqlDataSetDefinition();

    dsd.setName("PACIENTES ELEGIVEIS A CARGA VIRAL");
    dsd.setParameters(parameters);
    dsd.setSqlQuery(ReportQueries.ELEGIVEIS_CARGA_VIRAL);

    return dsd;
  }

  public static DataSetDefinition getPacientesQueVisitaramUS(List<Parameter> parameters) {

    SqlDataSetDefinition dsd = new SqlDataSetDefinition();

    dsd.setName("PACIENTES QUE VISITARAM A US");
    dsd.setParameters(parameters);
    dsd.setSqlQuery(ReportQueries.PACIENTES_QUE_VISITARAM_US);

    return dsd;
  }

  public static DataSetDefinition getMarcadosSeguimentoOuLevantamento(List<Parameter> parameters) {

    SqlDataSetDefinition dsd = new SqlDataSetDefinition();

    dsd.setName("PACIENTES MARCADOS PARA SEGUIMENTO OU LEVANTAMENTO");
    dsd.setParameters(parameters);
    dsd.setSqlQuery(ReportQueries.MARCADOS_SEGUIMENTO_LEVANTAMENTO);

    return dsd;
  }

  public static DataSetDefinition getReiniciosTARVDataSet(List<Parameter> parameters) {

    SqlDataSetDefinition dsd = new SqlDataSetDefinition();

    dsd.setName("PACIENTES QUE REINICIARAM O TARV");
    dsd.setParameters(parameters);
    dsd.setSqlQuery(ReportQueries.REINICIOS_TARV);

    return dsd;
  }

  public static DataSetDefinition getMudancaParaDTGDataSet(List<Parameter> parameters) {

    SqlDataSetDefinition dsd = new SqlDataSetDefinition();

    dsd.setName("PACIENTES QUE MUDARAM DE REGIME PARA DTG");
    dsd.setParameters(parameters);
    dsd.setSqlQuery(ReportQueries.MUDARANCA_PARA_DTG);

    return dsd;
  }

  public static DataSetDefinition getAvaliacaoCestaBasicaDataSet(List<Parameter> parameters) {

    SqlDataSetDefinition dsd = new SqlDataSetDefinition();

    dsd.setName("PACIENTES NA CESTA BASICA");
    dsd.setParameters(parameters);
    dsd.setSqlQuery(ReportQueries.AVALIACAO_CESTA_BASICA);

    return dsd;
  }

  public static DataSetDefinition getRastreiosIniciosTBDataSet(List<Parameter> parameters) {

    SqlDataSetDefinition dsd = new SqlDataSetDefinition();

    dsd.setName("PACIENTES RASTREIADOS INICIADOS TB");
    dsd.setParameters(parameters);
    dsd.setSqlQuery(ReportQueries.RASTREIOS_INICIO_TB);

    return dsd;
  }

  public static DataSetDefinition getFaltosParaBuscaDataSet(List<Parameter> parameters) {

    SqlDataSetDefinition dsd = new SqlDataSetDefinition();

    dsd.setName("PACIENTES FALTOSOS PARA BUSCA");
    dsd.setParameters(parameters);
    dsd.setSqlQuery(ReportQueries.FALTOSOS_PARA_BUSCA);

    return dsd;
  }

  public static DataSetDefinition getAtendidosConsultaDataSet(List<Parameter> parameters) {

    SqlDataSetDefinition dsd = new SqlDataSetDefinition();

    dsd.setName("PACIENTES ATENDIDOS NA CONSULTA");
    dsd.setParameters(parameters);
    dsd.setSqlQuery(ReportQueries.ATENDIDOS_CONSULTAS);

    return dsd;
  }

  public static DataSetDefinition getCargaViralAcima1000DataSet(List<Parameter> parameters) {

    SqlDataSetDefinition dsd = new SqlDataSetDefinition();

    dsd.setName("PACIENTES COM CARGA VIRAL ACIMA DE 1000 CÓPIAS");
    dsd.setParameters(parameters);
    dsd.setSqlQuery(ReportQueries.CARGA_VIRAL_ACIMA_1000);

    return dsd;
  }

  public static DataSetDefinition getRevelacaoDiagnosticoDataSet(List<Parameter> parameters) {

    SqlDataSetDefinition dsd = new SqlDataSetDefinition();

    dsd.setName("PACIENTES COM REVELAÇÃO DE DIAGNÓSTICO");
    dsd.setParameters(parameters);
    dsd.setSqlQuery(ReportQueries.REVELACAO_DIAGNOSTICO);

    return dsd;
  }

  public static DataSetDefinition getMulheresViaDataSet(List<Parameter> parameters) {

    SqlDataSetDefinition dsd = new SqlDataSetDefinition();

    dsd.setName("MULHERES COM E SEM VIA");
    dsd.setParameters(parameters);
    dsd.setSqlQuery(ReportQueries.MULHERES_VIA);

    return dsd;
  }
}
