package com.lambdaschool.schools.models;

public class IssPositionReturnData {
  private String message;
  private IssPosition iss_position;
  private long timestamp;

  public String getMessage()
  {
    return message;
  }

  public void setMessage(String message)
  {
    this.message = message;
  }

  public IssPosition getIss_position()
  {
    return iss_position;
  }

  public void setIss_position(IssPosition iss_position)
  {
    this.iss_position = iss_position;
  }

  public long getTimestamp()
  {
    return timestamp;
  }

  public void setTimestamp(long timestamp)
  {
    this.timestamp = timestamp;
  }
}
