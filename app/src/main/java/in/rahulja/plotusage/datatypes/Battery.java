package in.rahulja.plotusage.datatypes;

import in.rahulja.plotusage.interfaces.DataType;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

public class Battery implements DataType {

  private Integer value;

  @SuppressWarnings("RedundantIfStatement")
  private static boolean validate(String batteryLevelString) {
    if (StringUtils.isBlank(batteryLevelString)) {
      return false;
    }
    int batteryLevel = Integer.parseInt(batteryLevelString.trim());
    if (batteryLevel < 0 || batteryLevel > 100) {
      return false;
    }
    return true;
  }

  @Override public boolean isValid(@NonNull String data) {
    return validate(data);
  }

  @Override public Object getValue() {
    return value;
  }

  @Override public void setData(@NonNull String data) {
    if (isValid(data)) {
      value = Integer.parseInt(data.trim());
    }
  }
}
