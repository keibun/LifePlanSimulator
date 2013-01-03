package ch.qos.logback.classic;

import ch.qos.logback.classic.net.testObjectBuilders.LoggingEventBuilderInContext;
import ch.qos.logback.classic.net.testObjectBuilders.LoggingEventWithParametersBuilder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import ch.qos.logback.core.read.ListAppender;
import ch.qos.logback.core.status.OnConsoleStatusListener;
import ch.qos.logback.core.status.StatusChecker;
import ch.qos.logback.core.testUtil.RandomUtil;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.MDC;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Ceki G&uuml;lc&uuml;
 * @author Torsten Juergeleit
 */
public class AsyncAppenderTest {

  String thisClassName = this.getClass().getName();
  LoggerContext context = new LoggerContext();
  AsyncAppender asyncAppender = new AsyncAppender();
  ListAppender<ILoggingEvent> listAppender = new ListAppender<ILoggingEvent>();
  OnConsoleStatusListener onConsoleStatusListener = new OnConsoleStatusListener();
  StatusChecker statusChecker = new StatusChecker(context);
  LoggingEventBuilderInContext builder = new LoggingEventBuilderInContext(context, thisClassName, UnsynchronizedAppenderBase.class.getName());
  int diff = RandomUtil.getPositiveInt();

  @Before
  public void setUp() {
    onConsoleStatusListener.setContext(context);
    context.getStatusManager().add(onConsoleStatusListener);
    onConsoleStatusListener.start();

    asyncAppender.setContext(context);
    listAppender.setContext(context);
    listAppender.setName("list");
    listAppender.start();
  }

  @Test
  public void eventWasPreparedForDeferredProcessing() {
    asyncAppender.addAppender(listAppender);
    asyncAppender.start();

    String k = "k" + diff;
    MDC.put(k, "v");
    asyncAppender.doAppend(builder.build(diff));
    MDC.clear();

    asyncAppender.stop();
    assertFalse(asyncAppender.isStarted());

    // check the event
    assertEquals(1, listAppender.list.size());
    ILoggingEvent e = listAppender.list.get(0);

    // check that MDC values were correctly retained
    assertEquals("v", e.getMDCPropertyMap().get(k));
    assertFalse(e.hasCallerData());
  }


  @Test
  public void settingIncludeCallerDataPropertyCausedCallerDataToBeIncluded() {
    asyncAppender.addAppender(listAppender);
    asyncAppender.setIncludeCallerData(true);
    asyncAppender.start();


    asyncAppender.doAppend(builder.build(diff));
    asyncAppender.stop();

    // check the event
    assertEquals(1, listAppender.list.size());
    ILoggingEvent e = listAppender.list.get(0);
    assertTrue(e.hasCallerData());
    StackTraceElement ste = e.getCallerData()[0];
    assertEquals(thisClassName, ste.getClassName());
  }
}
