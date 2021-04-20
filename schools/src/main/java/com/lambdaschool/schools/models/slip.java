package com.lambdaschool.schools.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class slip {

  private int id;
  private String advice;

  public long getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getAdvice() {
    return advice;
  }

  public void setAdvice(String advice) {
    this.advice = advice;
  }

  @Override
  public String toString() {
    return System.class.getName().toLowerCase() +"{\n" +
        "id':" + this.id +",\n"+
        "advice':" + this.getAdvice() + "\n" +
        '}'+ "\n";
  }
}
