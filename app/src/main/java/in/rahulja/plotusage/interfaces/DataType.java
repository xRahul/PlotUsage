package in.rahulja.plotusage.interfaces;

import lombok.NonNull;

public interface DataType {
  public boolean isValid(@NonNull final String data);
  public Object getValue();
  public void setData(@NonNull final String data);
}
