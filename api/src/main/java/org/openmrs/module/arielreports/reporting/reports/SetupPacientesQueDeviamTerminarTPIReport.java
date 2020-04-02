package org.openmrs.module.arielreports.reporting.reports;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import org.openmrs.Location;
import org.openmrs.module.arielreports.ArielDataExportManager;
import org.openmrs.module.arielreports.reporting.library.datasets.ArielReportsDataSets;
import org.openmrs.module.reporting.evaluation.parameter.Mapped;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.openmrs.module.reporting.report.ReportDesign;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.springframework.stereotype.Component;

@Component
public class SetupPacientesQueDeviamTerminarTPIReport extends ArielDataExportManager {

  @Override
  public String getExcelDesignUuid() {
    return "4b1165cc-2236-11ea-9a51-e3f9b2c72a64";
  }

  @Override
  public List<ReportDesign> constructReportDesigns(ReportDefinition reportDefinition) {
    List<ReportDesign> reportDesigns = new ArrayList<ReportDesign>();
    reportDesigns.add(buildReportDesign(reportDefinition));
    return reportDesigns;
  }

  @Override
  public ReportDesign buildReportDesign(ReportDefinition reportDefinition) {
    ReportDesign reportDesign = null;
    try {
      reportDesign =
          createXlsReportDesign(
              reportDefinition,
              "ListaIniciosTPIQueDeviamTerminar.xls",
              "TERMINO TPI",
              getExcelDesignUuid(),
              null);
      Properties props = new Properties();
      props.put("repeatingSections", "sheet:1,row:8,dataset:TERMINOTPI");
      props.put("sortWeight", "5000");
      reportDesign.setProperties(props);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return reportDesign;
  }

  @Override
  public String getUuid() {
    return "524fda1c-2236-11ea-ad6a-1f6cd5884dc4";
  }

  @Override
  public String getName() {
    return "ARIEL - LISTA DE PACIENTES QUE INICIARAM TPI E DEVIAM TERMINAR";
  }

  @Override
  public String getDescription() {
    return "São pacientes que iniciaram o Tratamento Profiláctico com Isoniazida há 6 meses e deviam terminar no período de reporte";
  }

  @Override
  public ReportDefinition constructReportDefinition() {
    ReportDefinition rd = new ReportDefinition();
    rd.setUuid(getUuid());
    rd.setName(getName());
    rd.setDescription(getDescription());
    rd.setParameters(getParameters());
    rd.addDataSetDefinition(
        "TERMINOTPI",
        Mapped.mapStraightThrough(
            ArielReportsDataSets.getPacientesQueDeviamTerminarTPI(getParameters())));
    return rd;
  }

  @Override
  public String getVersion() {
    return "0.2";
  }

  @Override
  public List<Parameter> getParameters() {
    return Arrays.asList(
        new Parameter("startDate", "Data Inicial", Date.class),
        new Parameter("endDate", "Data Final", Date.class),
        new Parameter("location", "Unidade Sanitária", Location.class));
  }
}
