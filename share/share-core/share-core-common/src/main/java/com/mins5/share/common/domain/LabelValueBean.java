package com.mins5.share.common.domain;

public class LabelValueBean extends DomainObject
{
  private static final long serialVersionUID = -2890686165632325414L;
  private String label;
  private String value;

  public LabelValueBean()
  {
  }

  public LabelValueBean(String value, String label)
  {
    this.value = value;
    this.label = label;
  }

  public String getLabel() {
    return this.label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getValue() {
    return this.value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}