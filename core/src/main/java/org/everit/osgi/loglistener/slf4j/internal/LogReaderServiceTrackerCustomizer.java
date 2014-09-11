/**
 * This file is part of Everit - OSGi LogListener implementation for SLF4J.
 *
 * Everit - OSGi LogListener implementation for SLF4J is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Everit - OSGi LogListener implementation for SLF4J is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Everit - OSGi LogListener implementation for SLF4J.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.everit.osgi.loglistener.slf4j.internal;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.log.LogReaderService;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/**
 * A {@link ServiceTrackerCustomizer} that adds the {@link Slf4jLogListener} to the tracked {@link LogReaderService}.
 */
public class LogReaderServiceTrackerCustomizer implements ServiceTrackerCustomizer<LogReaderService, LogReaderService> {

    /**
     * The {@link org.osgi.service.log.LogListener} instance that will be registered to the {@link LogReaderService}.
     */
    private final Slf4jLogListener slf4jLogListener = new Slf4jLogListener();

    @Override
    public LogReaderService addingService(final ServiceReference<LogReaderService> reference) {
        Bundle bundle = reference.getBundle();
        BundleContext bundleContext = bundle.getBundleContext();
        LogReaderService logReaderService = bundleContext.getService(reference);
        logReaderService.addLogListener(slf4jLogListener);
        return logReaderService;
    }

    @Override
    public void modifiedService(final ServiceReference<LogReaderService> reference,
            final LogReaderService logReaderService) {
        // Nothing to do here.
    }

    @Override
    public void removedService(final ServiceReference<LogReaderService> reference,
            final LogReaderService logReaderService) {
        logReaderService.removeLogListener(slf4jLogListener);
    }

}
