package org.designpattern.structural_pattern.Proxy.Remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
//Remote : 이 인터페이스를 구현하는 클래스는 RMI를 통해 원격으로 호출될 수 있는 메서드를 제공해야 함.
 public interface Calculator extends Remote {
 // RemoteException은 RMI에서 발생할 수 있는 예외를 처리하기 위한 선언으로, 구현 메서드에서 해당 예외 처리를 강제하도록, interface에서 정의함
  int add(int a, int b) throws RemoteException;
  int subtract(int a, int b) throws RemoteException;
}
