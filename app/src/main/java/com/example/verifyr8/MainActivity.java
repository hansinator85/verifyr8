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

package com.example.verifyr8;

import android.os.Bundle;
import android.util.Log;

import com.example.verifyr8.log.Logger;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{
  private static final Logger logger = Logger.getLogger(MainActivity.class);

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    logger.debug("OWNLOGGER::MainActivity::onCreate:: will be removed");
    logger.debug("OWNLOGGER::MainActivity::onCreate:: will stay due to StringBuilder with variable: " + savedInstanceState);
    Log.d("MainActivity::onCreate::", "will be removed");
    Log.d("MainActivity::onCreate::", "will stay due to StringBuilder with variable: " + savedInstanceState);
    // include the following line to inspect the behavior with an additional class where no auto-generated proguard rules should be applied.
    // new SomeOtherClass(savedInstanceState);
  }
}
