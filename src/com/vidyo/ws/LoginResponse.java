package com.vidyo.ws;

public class LoginResponse
{
  String status;
  String result;

  public String getStatus()
  {
    return this.status;
  }
  public void setStatus(String status) {
    this.status = status;
  }
  public String getResult() {
    return this.result;
  }
  public void setResult(String result) {
    this.result = result;
  }
}