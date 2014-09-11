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

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogReaderService;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The {@link BundleActivator} of this bundle to open the {@link ServiceTracker} for the {@link LogReaderService}s with
 * a {@link LogReaderServiceTrackerCustomizer}.
 */
public class Activator implements BundleActivator {

    /**
     * The {@link ServiceTracker} of the {@link LogReaderService}s.
     */
    private ServiceTracker<LogReaderService, LogReaderService> tracker = null;

    @Override
    public void start(final BundleContext context) throws Exception {
        tracker = new ServiceTracker<LogReaderService, LogReaderService>(
                context, LogReaderService.class, new LogReaderServiceTrackerCustomizer());
        tracker.open();
    }

    @Override
    public void stop(final BundleContext context) throws Exception {
        tracker.close();
    }

}
