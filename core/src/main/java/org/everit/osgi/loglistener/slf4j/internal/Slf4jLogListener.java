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
import org.osgi.framework.Version;
import org.osgi.service.log.LogEntry;
import org.osgi.service.log.LogListener;
import org.osgi.service.log.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A {@link LogListener} that forwards all the {@link LogEntry}s to a {@link Logger} assigned to the bundle that created
 * the {@link LogEntry}.
 */
public class Slf4jLogListener implements LogListener {

    private Logger getLogger(final Bundle bundle) {
        String name = bundle.getSymbolicName();
        Version version = bundle.getVersion();
        if (version == null) {
            version = Version.emptyVersion;
        }
        Logger logger = LoggerFactory.getLogger(name + '.' + version);
        return logger;
    }

    @Override
    public void logged(final LogEntry logEntry) {

        Bundle bundle = logEntry.getBundle();
        Logger logger = getLogger(bundle);

        int level = logEntry.getLevel();
        String message = logEntry.getMessage();
        Throwable exception = logEntry.getException();

        switch (level) {
        case LogService.LOG_DEBUG:
            logger.debug(message, exception);
            break;
        case LogService.LOG_INFO:
            logger.info(message, exception);
            break;
        case LogService.LOG_WARNING:
            logger.warn(message, exception);
            break;
        case LogService.LOG_ERROR:
            logger.error(message, exception);
            break;
        default:
            logger.warn("[unknown level (" + level + ")] " + message, exception);
            break;
        }

    }

}
