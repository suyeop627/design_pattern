package org.designpattern.structural_pattern.Proxy.Protection;

public class ProtectionProxy implements Subject {
  RealSubject realSubject = new RealSubject();
  private final String password;

  public ProtectionProxy(String password){
    this.password = password;
  }

  @Override
  public void response() {
    if(authenticate()){
      System.out.println("ProtectionProxy : do something before request");
      realSubject.response();
      System.out.println("ProtectionProxy : do something after request");
    }else{
      System.out.println("ProtectionProxy : forbidden");
    }
  }

  private boolean authenticate() {
    return "password".equals(password);
  }
}
