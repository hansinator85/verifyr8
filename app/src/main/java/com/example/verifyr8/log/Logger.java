//==========================================================================
// Copyright (c) Fabasoft R&D GmbH, A-4020 Linz, 1988-2021.
//
// Alle Rechte vorbehalten. Alle verwendeten Hard- und Softwarenamen sind
// Handelsnamen und/oder Marken der jeweiligen Hersteller.
//
// Der Nutzer des Computerprogramms anerkennt, dass der oben stehende
// Copyright-Vermerk im Sinn des Welturheberrechtsabkommens an der vom
// Urheber festgelegten Stelle in der Funktion des Computerprogramms
// angebracht bleibt, um den Vorbehalt des Urheberrechtes genuegend zum
// Ausdruck zu bringen. Dieser Urheberrechtsvermerk darf weder vom Kunden,
// Nutzer und/oder von Dritten entfernt, veraendert oder disloziert werden.
//==========================================================================

package com.example.verifyr8.log;

import android.os.Bundle;
import android.text.TextUtils;

import java.text.MessageFormat;
import java.util.logging.Level;

import androidx.annotation.NonNull;

public abstract class Logger
{
  /**
   * <b>This boolean must never be checked in with the value true!</b><br/>
   * Set LOG_EXTENDED to true, when logging of extended information like
   * touch events should be activated.
   */
  private static final boolean LOG_EXTENDED = false;

  public static @NonNull Logger getLogger(Class<?> c)
  {
    return LoggerFactory.getInstance().getFactory().getLogger(c);
  }

  public static Logger getLogger(String name)
  {
    return LoggerFactory.getInstance().getFactory().getLogger(name);
  }

  private String name;
  private Level level;

  protected Logger(Level level, String name)
  {
    this.level = level;
    this.name = name;
  }

  public void warn(Object message, Throwable exc)
  {
    log(Level.WARNING, message, exc);
  }

  public void warn(Object message)
  {
    log(Level.WARNING, message);
  }

  public void error(Object message, Throwable exc)
  {
    log(Level.WARNING, message, exc);
  }

  public void error(Object message)
  {
    log(Level.WARNING, message);
  }

  public void fatal(Object message, Throwable exc)
  {
    log(Level.SEVERE, message, exc);
  }

  public void fatal(Object message)
  {
    log(Level.SEVERE, message);
  }

  public void info(Object message, Throwable exc)
  {
    log(Level.INFO, message, exc);
  }

  public void info(Object message)
  {
    log(Level.INFO, message);
  }

  public void trace(Object message, Throwable exc)
  {
    log(Level.INFO, message, exc);
  }

  public void trace(Object message)
  {
    log(Level.INFO, message);
  }

  public void debug(Object message, Throwable exc)
  {
    log(Level.INFO, message, exc);
  }

  public void debug(Object message)
  {
    log(Level.INFO, message);
  }

  public void extended(Object message, Throwable exc)
  {
    if (LOG_EXTENDED) {
      log(Level.INFO, message, exc);
    }
  }

  public void extended(Object message)
  {
    if (LOG_EXTENDED) {
      log(Level.INFO, message);
    }
  }

  /**
   * Bundles will only be logged when LOG_STACK_TRACES is active
   *
   * @param message the message to show with the bundle
   * @param bundle  the bundle to log
   */
  public void logBundle(Object message, Bundle bundle)
  {
    debug(message);
    if (bundle != null && !bundle.isEmpty()) {
      for (String key : bundle.keySet()) {
        debug(key + ": " + bundle.get(key));
      }
    }
  }

  /**
   * Stack Trace will only be logged when LOG_STACK_TRACES is active
   *
   * @param message the message to show with the stack trace
   */
  public void logStackTrace(Object message)
  {
    debug(message);
    StackTraceElement[] stacklist = Thread.currentThread().getStackTrace();
    for (int idx = 3, cnt = stacklist.length; idx < cnt; idx++) {
      debug(stacklist[idx].toString());
    }
  }

  public void logLongString(Object message, String longString)
  {
    TextUtils.SimpleStringSplitter strings = new TextUtils.SimpleStringSplitter('\n');
    strings.setString(longString);
    strings.forEach(stringPart -> debug(MessageFormat.format("{0}: {1}", message, stringPart)));
  }

  public boolean isLoggable(java.util.logging.Level level)
  {
    return this.level == null || level.intValue() > this.level.intValue();
  }

  private void log(Level level, Object stackTrace)
  {
    log(level, stackTrace, null);
  }

  public abstract void log(Level level, Object stackTrace, Throwable exc);

  public void setLevel(Level level)
  {
    this.level = level;
  }
}
