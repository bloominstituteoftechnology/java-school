package com.lambdaschool.schools.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TranslationContents
{
  private String translated;

  public String getTranslated()
  {
    return translated;
  }

  public void setTranslated(String translated)
  {
    this.translated = translated;
  }
}