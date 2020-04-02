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
public class SetupCoorteTARVReport extends ArielDataExportManager {

  @Override
  public String getExcelDesignUuid() {
    return "0bfecfee-007d-11ea-a9af-2ff361bc46d2";
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
              "ListaCoorteTARV.xls",
              "PACIENTES COORTE TARV",
              getExcelDesignUuid(),
              null);
      Properties props = new Properties();
      props.put("repeatingSections", "sheet:1,row:8,dataset:COORTE");
      props.put("sortWeight", "5000");
      reportDesign.setProperties(props);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return reportDesign;
  }

  @Override
  public String getUuid() {
    return "163d633a-007d-11ea-804e-c747238eff63";
  }

  @Override
  public String getName() {
    return "ARIEL - LISTA DE PACIENTES NA COORTE TARV (MASTER CARD)";
  }

  @Override
  public String getDescription() {
    return "São pacientes na Coorte TARV";
  }

  @Override
  public ReportDefinition constructReportDefinition() {
    ReportDefinition rd = new ReportDefinition();
    rd.setUuid(getUuid());
    rd.setName(getName());
    rd.setDescription(getDescription());
    rd.setParameters(getParameters());
    rd.addDataSetDefinition(
        "COORTE",
        Mapped.mapStraightThrough(
            ArielReportsDataSets.getPacientesCoorteTARVDataSet(getParameters())));
    return rd;
  }

  @Override
  public String getVersion() {
    return "0.3";
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
