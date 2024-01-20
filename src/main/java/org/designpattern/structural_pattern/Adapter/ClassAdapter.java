package org.designpattern.structural_pattern.Adapter;

public class ClassAdapter extends ExternalSystem implements InternalSystem{
  @Override
  public void performInternalAction() {
    performExternalAction();
  }
}
