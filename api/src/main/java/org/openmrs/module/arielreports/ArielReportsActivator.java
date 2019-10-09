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

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.BaseModuleActivator;

/**
 * This class contains the logic that is run every time this module is either started or shutdown
 */
public class ArielReportsActivator extends BaseModuleActivator {

  private Log log = LogFactory.getLog(this.getClass());

  public List<Initializer> getInitializers() {
    List<Initializer> l = new ArrayList<Initializer>();
    l.add(new ArielReportInitializer());
    return l;
  }

  /** @see #started() */
  public void started() {
    log.info("eSaude Reports module started - initializing...");
    for (Initializer initializer : getInitializers()) {
      initializer.started();
    }
  }

  @Override
  public void stopped() {
    for (int i = getInitializers().size() - 1; i >= 0; i--) {
      getInitializers().get(i).stopped();
    }
    log.info("eSaude Reports module stopped");
  }
}
