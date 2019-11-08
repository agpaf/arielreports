package org.openmrs.module.arielreports.reporting.library.datasets;

import java.util.List;
import org.openmrs.module.arielreports.reporting.library.queries.ReportQueries;
import org.openmrs.module.reporting.dataset.definition.DataSetDefinition;
import org.openmrs.module.reporting.dataset.definition.SqlDataSetDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;

public class ArielReportsDataSets {

  public static DataSetDefinition getGravidasSemDataPartoDataSet(List<Parameter> parameters) {

    SqlDataSetDefinition dsd = new SqlDataSetDefinition();

    dsd.setName("GRAVIDAS");
    dsd.setParameters(parameters);
    dsd.setSqlQuery(ReportQueries.GRAVIDAS);

    return dsd;
  }

  public static DataSetDefinition getPacientesCVNegativaDataSet(List<Parameter> parameters) {

    SqlDataSetDefinition dsd = new SqlDataSetDefinition();

    dsd.setName("CARGA VIRAL");
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

    dsd.setName("COORTE");
    dsd.setParameters(parameters);
    dsd.setSqlQuery(ReportQueries.PACIENTES_COORTE_TARV);

    return dsd;
  }

  public static DataSetDefinition getVisitasComRastreiosProfilaxiasDataSet(
      List<Parameter> parameters) {

    SqlDataSetDefinition dsd = new SqlDataSetDefinition();

    dsd.setName("VISITAS");
    dsd.setParameters(parameters);
    dsd.setSqlQuery(ReportQueries.VISITAS_RASTREIOS_PROFILAXIAS);

    return dsd;
  }
}
