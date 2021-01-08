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

import java.util.logging.Level;

public class LoggerImpl extends Logger
{
  private final java.util.logging.Logger logger;

  LoggerImpl(java.util.logging.Logger logger)
  {
    super(java.util.logging.Level.ALL, logger.getName());
    this.logger = logger;
  }

  @Override
  public void setLevel(Level level)
  {
    super.setLevel(level);
    logger.setLevel(level);
  }

  @Override
  public void log(Level level, Object stackTrace, Throwable exc)
  {
    if (!isLoggable(level)) {
      return;
    }

    StackTraceElement elem = null;
    StackTraceElement[] elems = Thread.currentThread().getStackTrace();
    for (int idx = 2; idx < elems.length; idx++) {
      if (!elems[idx].getClassName().equals("com.example.verifyr8.log.Logger") &&
          !elems[idx].getClassName().equals("com.example.verifyr8.log.JavaLoggerFactory") &&
          !elems[idx].getClassName().equals("com.example.verifyr8.log.JavaLoggerFactory$LoggerImpl")) {
        elem = elems[idx];
        break;
      }
    }

    String msg = stackTrace != null ? stackTrace.toString() : "null";
    if (elem != null) {
      String method = elem.getMethodName();
      String source = elem.getClassName();
      if (exc != null) {
        logger.logp(level, source, method, msg, exc);
      }
      else {
        logger.logp(level, source, method, msg);
      }
    }
    else if (exc != null) {
      logger.log(level, msg, exc);
    }
    else {
      logger.log(level, msg);
    }
  }
}
