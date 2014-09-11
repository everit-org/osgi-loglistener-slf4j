/**
 * This file is part of Everit - OSGi LogListener for SLF4J tests.
 *
 * Everit - OSGi LogListener for SLF4J tests is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Everit - OSGi LogListener for SLF4J tests is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Everit - OSGi LogListener for SLF4J tests.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.everit.osgi.loglistener.slf4j.tests;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.ConfigurationPolicy;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.everit.osgi.dev.testrunner.TestRunnerConstants;
import org.junit.Test;
import org.osgi.service.log.LogService;

@Component(name = "Slf4jLogListenerTest", policy = ConfigurationPolicy.OPTIONAL, immediate = true)
@Properties({
        @Property(name = TestRunnerConstants.SERVICE_PROPERTY_TESTRUNNER_ENGINE_TYPE, value = "junit4"),
        @Property(name = TestRunnerConstants.SERVICE_PROPERTY_TEST_ID, value = "Slf4jLogListenerTest"),
        @Property(name = "logService.target")
})
@Service(value = Slf4jLogListenerTestComponent.class)
public class Slf4jLogListenerTestComponent {

    private static final int USER_DEFINED_LOG_LEVEL = 100;

    @Reference(bind = "setLogService")
    private LogService logService;

    public void setLogService(final LogService logService) {
        this.logService = logService;
    }

    @Test
    public void testLog() {
        logService.log(LogService.LOG_DEBUG, "debug");
        logService.log(LogService.LOG_INFO, "info");
        logService.log(LogService.LOG_WARNING, "warning");
        logService.log(LogService.LOG_ERROR, "error");
        logService.log(USER_DEFINED_LOG_LEVEL, "custom");
    }

}
