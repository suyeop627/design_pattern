package org.designpattern.structural_pattern.Proxy.Remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculatorImpl extends UnicastRemoteObject implements Calculator {
  //UnicastRemoteObject
  //UnicastRemoteObject 클래스를 상속받은 클래스의 객체는 RMI에서 원격으로 사용 가능한 객체로 만들어짐.
  // 이를 통해 해당 객체의 메서드를 원격에서 호출할 수 있게 됩니다.
  //원격객체는 RMI Registry에 등록되어, 클라이언트에서 찾게됨. 클라이언트는 RMI Registry를 통해 서버에서 실행 중인 원격 객체를 검색하여 호출할 수 있음.
  protected CalculatorImpl() throws RemoteException {
    super();
  }

  @Override
  public int add(int a, int b) throws RemoteException {
    return a + b;
  }

  @Override
  public int subtract(int a, int b) throws RemoteException {
    return a - b;
  }
}
