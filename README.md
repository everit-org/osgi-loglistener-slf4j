osgi-loglistener-slf4j
=======================

An OSGi LogListener implementation that forwards the LogEntry to an SLF4J 
logger. This bundle contains a BundleActivator that opens a ServiceTracker for 
LogReaderServices to add the Slf4jLogListener the them.

A blog post is available on [Everit - Opensource][1] about this bundle.

[1]: http://everitorg.wordpress.com/2014/09/11/osgi-loglistener-for-slf4j/
