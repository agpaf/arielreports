/*
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */

package org.openmrs.module.arielreports;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.arielreports.reporting.utils.ReportUtils;
import org.openmrs.module.reporting.ReportingConstants;
import org.openmrs.module.reporting.report.manager.ReportManager;
import org.openmrs.module.reporting.report.util.ReportUtil;

public class ArielReportInitializer {

  private Log log = LogFactory.getLog(this.getClass());

  /** Initializes all Ariel reports and remove deprecated reports from database. */
  public void initializeReports() {
    for (ReportManager reportManager : Context.getRegisteredComponents(ArielReportManager.class)) {
      if (reportManager.getClass().getAnnotation(Deprecated.class) != null) {
        // remove deprecated reports
        ReportUtils.purgeReportDefinition(reportManager);
        log.info(
            "Report " + reportManager.getName() + " is deprecated.  Removing it from database.");
      } else {
        // setup Ariel active reports
        ReportUtils.setupReportDefinition(reportManager);
        log.info("Setting up report " + reportManager.getName() + "...");
      }
    }
    ReportUtil.updateGlobalProperty(
        ReportingConstants.GLOBAL_PROPERTY_DATA_EVALUATION_BATCH_SIZE, "-1");
  }

  /** Purges all Ariel reports from database. */
  public void purgeReports() {
    for (ReportManager reportManager : Context.getRegisteredComponents(ArielReportManager.class)) {
      ReportUtils.purgeReportDefinition(reportManager);
      log.info("Report " + reportManager.getName() + " removed from database.");
    }
  }
}
