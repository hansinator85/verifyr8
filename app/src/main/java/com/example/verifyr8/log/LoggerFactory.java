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

import java.io.Serializable;
import java.util.logging.Level;

public class LoggerFactory implements Serializable
{
  private static LoggerFactory instance;

  private transient ILoggerFactory factory;

  private LoggerFactory()
  {
    if (instance != null) {
      throw new IllegalStateException("Use getInstance() method to get the instance of this class.");
    }
    factory = new JavaLoggerFactory();
  }

  public static synchronized LoggerFactory getInstance()
  {
    if (instance == null) {
      instance = new LoggerFactory();
    }
    return instance;
  }

  public ILoggerFactory getFactory()
  {
    return factory;
  }

  public static void setLogLevel(Level newLogLevel)
  {
    JavaLoggerFactory.setLogLevel(newLogLevel);
  }
}
