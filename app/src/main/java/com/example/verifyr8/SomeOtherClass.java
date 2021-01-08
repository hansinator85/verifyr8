package com.example.verifyr8;

import android.os.Bundle;
import android.util.Log;

import com.example.verifyr8.log.Logger;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SomeOtherClass
{
  private static final Logger logger = Logger.getLogger(SomeOtherClass.class);
  private final String mStr;

  public SomeOtherClass(Bundle param)
  {
    // create some code that will not be stripped away due to unused code or always same parameter conditions...
    byte[] bytes = new byte[20];
    try {
      SecureRandom.getInstanceStrong().nextBytes(bytes);
    }
    catch (NoSuchAlgorithmException exc) {
      exc.printStackTrace();
      SecureRandom random = new SecureRandom();
      random.nextBytes(bytes);
    }
    mStr = new String(bytes);

    logger.debug("OWNLOGGER::SomeOtherClass::Constructor:: will be removed");
    logger.debug("OWNLOGGER::SomeOtherClass::Constructor:: will stay due to StringBuilder with variable: " + param);
    Log.d("SomeOtherClass::Constructor::", "will be removed");
    Log.d("SomeOtherClass::Constructor::", "will stay due to StringBuilder with variable: " + param);
  }
}
