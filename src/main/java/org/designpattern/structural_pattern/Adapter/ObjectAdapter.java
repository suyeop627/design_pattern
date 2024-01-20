package org.designpattern.structural_pattern.Adapter;

public class ObjectAdapter implements InternalSystem{
  private final ExternalSystem externalSystem;

  public ObjectAdapter(ExternalSystem externalSystem) {
    this.externalSystem = externalSystem;
  }

  @Override
  public void performInternalAction() {
    externalSystem.performExternalAction();
  }
}
