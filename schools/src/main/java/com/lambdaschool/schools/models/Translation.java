package com.lambdaschool.schools.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Translation
{
  private TranslationContents contents;

  public TranslationContents getContents()
  {
    return contents;
  }

  public void setContents(TranslationContents contents)
  {
    this.contents = contents;
  }
}