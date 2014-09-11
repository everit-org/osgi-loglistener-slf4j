osgi-loglistener-slf4j
=======================

An OSGi LogListener implementation that forwards the LogEntry to an SLF4J 
logger. This bundle contains a BundleActivator that opens a ServiceTracker for 
LogReaderServices to add the Slf4jLogListener the them.
