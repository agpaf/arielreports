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
public class SetupPacientesCVNegativaReport extends ArielDataExportManager {

  @Override
  public String getExcelDesignUuid() {
    return "51ca24ca-f4ce-11e9-b0c2-a72117e556d1";
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
              "ListaPacientesCVNegativa.xls",
              "PACIENTES COM CV NEGATIVA",
              getExcelDesignUuid(),
              null);
      Properties props = new Properties();
      props.put("repeatingSections", "sheet:1,row:8,dataset:PACIENTES");
      props.put("sortWeight", "5000");
      reportDesign.setProperties(props);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return reportDesign;
  }

  @Override
  public String getUuid() {
    return "5b2dfa78-f4ce-11e9-b5df-133059d1eedd";
  }

  @Override
  public String getName() {
    return "ARIEL - LISTA DE PACIENTES COM CARGA VIRAL NEGATIVA";
  }

  @Override
  public String getDescription() {
    return "São pacientes que têm carga viral abaixo de ZERO";
  }

  @Override
  public ReportDefinition constructReportDefinition() {
    ReportDefinition rd = new ReportDefinition();
    rd.setUuid(getUuid());
    rd.setName(getName());
    rd.setDescription(getDescription());
    rd.setParameters(getParameters());
    rd.addDataSetDefinition(
        "PACIENTES",
        Mapped.mapStraightThrough(
            ArielReportsDataSets.getPacientesCVNegativaDataSet(getParameters())));
    return rd;
  }

  @Override
  public String getVersion() {
    return "0.3";
  }

  @Override
  public List<Parameter> getParameters() {
    return Arrays.asList(
        new Parameter("endDate", "Data Final", Date.class),
        new Parameter("location", "Unidade Sanitária", Location.class));
  }
}
