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
public class SetupMudancaParaDTGReport extends ArielDataExportManager {

  @Override
  public String getExcelDesignUuid() {
    return "30b09c68-6d9d-11ea-b94e-cb83d0431484";
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
              "ListaMudancaDTG.xls",
              "MUDANCA PARA DTG",
              getExcelDesignUuid(),
              null);
      Properties props = new Properties();
      props.put("repeatingSections", "sheet:1,row:9,dataset:MUDANCA");
      props.put("sortWeight", "5000");
      reportDesign.setProperties(props);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return reportDesign;
  }

  @Override
  public String getUuid() {
    return "3625957c-6d9d-11ea-9267-37dd560e00fd";
  }

  @Override
  public String getName() {
    return "ARIEL - LISTA DE PACIENTES QUE TIVERAM CONSULTA CLÍNICA - MUDANÇA PARA DTG";
  }

  @Override
  public String getDescription() {
    return "São que tiveram consulta clínica num período";
  }

  @Override
  public ReportDefinition constructReportDefinition() {
    ReportDefinition rd = new ReportDefinition();
    rd.setUuid(getUuid());
    rd.setName(getName());
    rd.setDescription(getDescription());
    rd.setParameters(getParameters());
    rd.addDataSetDefinition(
        "MUDANCA",
        Mapped.mapStraightThrough(ArielReportsDataSets.getMudancaParaDTGDataSet(getParameters())));
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
        new Parameter("dateCoorte", "Data Avaliação Coorte", Date.class),
        new Parameter("location", "Unidade Sanitária", Location.class));
  }
}
