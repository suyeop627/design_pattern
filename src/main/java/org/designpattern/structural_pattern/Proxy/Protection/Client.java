package org.designpattern.structural_pattern.Proxy.Protection;

public class Client {
  public static void main(String[] args) {
    //보호 프록시
    Subject protectionProxy = new ProtectionProxy("password");
    protectionProxy.response();

    Subject protectionProxy2 = new ProtectionProxy("wrong_password");
    protectionProxy2.response();

  }
}
