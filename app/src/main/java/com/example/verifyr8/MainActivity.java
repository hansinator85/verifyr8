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

import java.util.Objects;

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

    // error log should keep intact
    logger.error("OWNLOGGER::MainActivity::onCreate::error:: will stay as error log should keep with variable: " + savedInstanceState);

    // try if "real"-usage of StringBuilder-constructor and Bundle.toString() will be detected
    Bundle anotherBundle = new Bundle();
    anotherBundle.putString("someKey", "someValue");
    String bundleString = anotherBundle.toString();
    String usedBundleString = bundleString.replace("some", "useful");
    anotherBundle.putString("replacedValue", usedBundleString);
    anotherBundle.putString("stringbuilderValue", "original value: " + bundleString + " with replaced value: " + usedBundleString);
    onSaveInstanceState(anotherBundle);
    logger.error("OWNLOGGER::MainActivity::onCreate::error:: show another bundle: " + anotherBundle);
    logger.error(Objects.toString(anotherBundle));

    // include the following line to inspect the behavior with an additional class where no auto-generated proguard rules should be applied.
    // new SomeOtherClass(savedInstanceState);
  }
}
