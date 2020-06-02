package com.vertafore.test.actor.ems;

import net.serenitybdd.screenplay.Actor;

public class EMSActor extends Actor {
  private final String context;

  public EMSActor(String name, String context) {
    super(name);
    this.context = context;
  }

  //  public static String context() {
  //    return context;
  //  }
  //
  //  public static <T extends Actor> String GetContextForActor(T emsActor) {
  //    return context;
  //  }

  public String context() {
    return context;
  }
}
