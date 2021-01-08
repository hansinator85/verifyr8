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

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Handler;
import java.util.logging.Level;

public class JavaLoggerFactory implements
  ILoggerFactory
{
  private static Level globalLevel = null;
  private static final Set<LoggerImpl> loggers = new HashSet<>();

  JavaLoggerFactory()
  {
    // empty constructor
  }

  @Override
  public Logger getLogger(Class<?> c)
  {
    return getLogger(c.getSimpleName());
  }

  @Override
  public Logger getLogger(String name)
  {
    LoggerImpl logger = new LoggerImpl(java.util.logging.Logger.getLogger(name));
    JavaLoggerFactory.loggers.add(logger);
    if (JavaLoggerFactory.globalLevel != null) {
      logger.setLevel(JavaLoggerFactory.globalLevel);
    }
    return logger;
  }

  static void setLogLevel(Level newLogLevel)
  {
    JavaLoggerFactory.globalLevel = newLogLevel;

    java.util.logging.Logger topLogger = java.util.logging.Logger.getLogger("");
    for (Handler handler : topLogger.getHandlers()) {
      handler.setLevel(newLogLevel);
    }
    for (Logger logger : JavaLoggerFactory.loggers) {
      if (logger != null) {
        logger.setLevel(newLogLevel);
      }
    }
  }
}
