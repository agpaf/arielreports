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
public class SetupElegiveisTPIReport extends ArielDataExportManager {

  @Override
  public String getExcelDesignUuid() {
    return "a231aa9a-2229-11ea-ac5d-d307adafac6f";
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
              "ListaElegiveisTPI.xls",
              "ELEGIVEIS TPI",
              getExcelDesignUuid(),
              null);
      Properties props = new Properties();
      props.put("repeatingSections", "sheet:1,row:8,dataset:ELEGIVEIS");
      props.put("sortWeight", "5000");
      reportDesign.setProperties(props);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return reportDesign;
  }

  @Override
  public String getUuid() {
    return "a8ee6206-2229-11ea-9ea0-9b1a42ff426c";
  }

  @Override
  public String getName() {
    return "ARIEL - LISTA DE PACIENTES ELEGIVEIS AO TPI";
  }

  @Override
  public String getDescription() {
    return "São pacientes elegíveis ao Tratamento Profiláctico com Isoniazida";
  }

  @Override
  public ReportDefinition constructReportDefinition() {
    ReportDefinition rd = new ReportDefinition();
    rd.setUuid(getUuid());
    rd.setName(getName());
    rd.setDescription(getDescription());
    rd.setParameters(getParameters());
    rd.addDataSetDefinition(
        "ELEGIVEIS",
        Mapped.mapStraightThrough(ArielReportsDataSets.getElegiveisTPI(getParameters())));
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
