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
public class SetupRevelacaoDiagnosticoReport extends ArielDataExportManager {

  @Override
  public String getExcelDesignUuid() {
    return "f9499eaa-741d-11ea-acef-23bbf0dbaca2";
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
              "RevelacaoDiagnostico.xls",
              "REVELACAO DIAGNOSTICO",
              getExcelDesignUuid(),
              null);
      Properties props = new Properties();
      props.put("repeatingSections", "sheet:1,row:8,dataset:REVELACAO");
      props.put("sortWeight", "5000");
      reportDesign.setProperties(props);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return reportDesign;
  }

  @Override
  public String getUuid() {
    return "022d708c-741e-11ea-a599-bb0e7c0a18e8";
  }

  @Override
  public String getName() {
    return "ARIEL - LISTA DE PACIENTES COM REVELAÇÃO DE DIAGNÓSTICO";
  }

  @Override
  public String getDescription() {
    return "São pacientes com ou sem revelação de diagnóstico";
  }

  @Override
  public ReportDefinition constructReportDefinition() {
    ReportDefinition rd = new ReportDefinition();
    rd.setUuid(getUuid());
    rd.setName(getName());
    rd.setDescription(getDescription());
    rd.setParameters(getParameters());
    rd.addDataSetDefinition(
        "REVELACAO",
        Mapped.mapStraightThrough(
            ArielReportsDataSets.getRevelacaoDiagnosticoDataSet(getParameters())));
    return rd;
  }

  @Override
  public String getVersion() {
    return "0.1";
  }

  @Override
  public List<Parameter> getParameters() {
    return Arrays.asList(
        new Parameter("endDate", "Data Final", Date.class),
        new Parameter("location", "Unidade Sanitária", Location.class));
  }
}
