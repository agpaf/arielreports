/**
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of
 * the MPL was not distributed with this file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * OpenMRS is also distributed under the terms of the Healthcare Disclaimer located at
 * http://openmrs.org/license.
 *
 * <p>Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS graphic logo is a
 * trademark of OpenMRS Inc.
 */
package org.openmrs.module.arielreports;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.BaseModuleActivator;

/**
 * This class contains the logic that is run every time this module is either started or shutdown
 */
public class ArielReportsActivator extends BaseModuleActivator {

  private Log log = LogFactory.getLog(this.getClass());

  private ArielReportInitializer reportsInitializer = new ArielReportInitializer();

  @Override
  public void contextRefreshed() {
    log.debug("Ariel Reports Module refreshed");
  }

  @Override
  public void willRefreshContext() {
    log.debug("Refreshing Ariel Reports Module");
  }

  @Override
  public void willStart() {
    log.debug("Starting Ariel Reports Module");
  }

  @Override
  public void willStop() {
    log.debug("Stopping Ariel Reports Module");
    try {
      reportsInitializer.purgeReports();
      log.debug("Ariel Reports purged");
    } catch (Exception e) {
      log.error("An error occured trying to purge Ariel reports", e);
    }
  }

  /** @see #started() */
  public void started() {
    try {
      reportsInitializer.initializeReports();
      log.info("Started Ariel Reports Module");
    } catch (MetadataLookupException e) {
      Context.getAlertService().notifySuperUsers("eptsreports.startuperror.globalproperties", null);
      throw e;
    } catch (Exception e) {
      Context.getAlertService().notifySuperUsers("eptsreports.startuperror.general", null);
      throw e;
    }
  }

  /** @see #stopped() */
  public void stopped() {
    log.info("Stopped Ariel Reports Module");
  }
}
