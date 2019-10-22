package org.openmrs.module.arielreports.reporting.library.datasets;

import java.util.List;
import org.openmrs.module.arielreports.reporting.library.queries.ReportQueries;
import org.openmrs.module.reporting.dataset.definition.DataSetDefinition;
import org.openmrs.module.reporting.dataset.definition.SqlDataSetDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;

public class ArielReportsDataSets {

  public static DataSetDefinition getGravidasSemDataPartoDataSet(List<Parameter> parameters) {

    SqlDataSetDefinition dsd = new SqlDataSetDefinition();

    dsd.setName("G");
    dsd.setParameters(parameters);
    dsd.setSqlQuery(ReportQueries.GRAVIDAS);

    return dsd;
  }

  public static DataSetDefinition getPacientesCVNegativaDataSet(List<Parameter> parameters) {

    SqlDataSetDefinition dsd = new SqlDataSetDefinition();

    dsd.setName("G");
    dsd.setParameters(parameters);
    dsd.setSqlQuery(ReportQueries.PACIENTES_CV_NEGATIVA);

    return dsd;
  }
}
