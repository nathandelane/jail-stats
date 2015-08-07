package net.sf.nathandelane.test.app;


public final class Waiter {
  
  public static final void waitForSeconds(int seconds) {
    try {
      Thread.sleep(seconds * 1000);
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  
}
