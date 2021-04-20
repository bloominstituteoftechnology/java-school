package com.lambdaschool.schools.models;

public class SlipReturnData {
  private String message;
  private slip slip_position;
  private long timestamp;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public slip getSlip_position() {
    return slip_position;
  }

  public void setSlip_position(slip slip_position) {
    this.slip_position = slip_position;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }
}
