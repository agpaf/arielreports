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
public class SetupElegiveisCargaViralReport extends ArielDataExportManager {

  @Override
  public String getExcelDesignUuid() {
    return "e9cf5fc2-2243-11ea-b162-d336bbf947c8";
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
              "ListaElegiveisCargaViral.xls",
              "ELEGIVEIS CARGA VIRAL",
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
    return "ef59da12-2243-11ea-a8f4-ff0cacd1b3bb";
  }

  @Override
  public String getName() {
    return "ARIEL - LISTA DE PACIENTES ELEGIVEIS A CARGA VIRAL";
  }

  @Override
  public String getDescription() {
    return "São pacientes elegíveis ao exame de Carga Viral";
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
        Mapped.mapStraightThrough(ArielReportsDataSets.getElegiveisCargaViral(getParameters())));
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
