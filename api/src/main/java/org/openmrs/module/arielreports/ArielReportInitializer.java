package org.openmrs.module.arielreports;

/** Created by codehub on 06/10/19. */
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.AdministrationService;
import org.openmrs.api.context.Context;
import org.openmrs.module.reporting.ReportingConstants;
import org.openmrs.module.reporting.report.manager.ReportManagerUtil;
import org.openmrs.module.reporting.report.util.ReportUtil;

/** Initializes reports */
public class ArielReportInitializer implements Initializer {

  protected static final Log log = LogFactory.getLog(ArielReportInitializer.class);

  /** @see Initializer#started() */
  @Override
  public synchronized void started() {
    removeOldReports();

    ReportManagerUtil.setupAllReports(ArielReportManager.class);
    ReportUtil.updateGlobalProperty(
        ReportingConstants.GLOBAL_PROPERTY_DATA_EVALUATION_BATCH_SIZE, "-1");
  }

  /** @see Initializer#stopped() */
  @Override
  public void stopped() {}

  private void removeOldReports() {
    AdministrationService as = Context.getAdministrationService();
    // the purpose of this snipet is to allow rapid development other than going to
    // change the
    // report version all the time for change
    log.warn("Removing all reports");
    // getting id of the loaded report designs
    String report_resource_quality_improvement_id =
        "select id from reporting_report_design where uuid='b69d36ae-e9d8-11e9-aba8-7f11132c9956'";
    // deleting the resource already loaded
    as.executeSQL(
        "delete from reporting_report_design_resource where report_design_id =("
            + report_resource_quality_improvement_id
            + ");",
        false);
    // deleting the actual designs now
    as.executeSQL(
        "delete from reporting_report_design where uuid='b69d36ae-e9d8-11e9-aba8-7f11132c9956';",
        false);

    // deleting all report requests and managers
    as.executeSQL("delete from reporting_report_request;", false);
    as.executeSQL(
        "delete from global_property WHERE property LIKE 'reporting.reportManager%';", false);

    // deleting the actual report definitions from the db
    as.executeSQL(
        "delete from serialized_object WHERE uuid = 'dc98f7c6-e9d8-11e9-a398-dfe515d7157b';",
        false);
  }
}
