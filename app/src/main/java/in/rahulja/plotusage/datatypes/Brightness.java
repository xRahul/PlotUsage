package in.rahulja.plotusage.datatypes;

import in.rahulja.plotusage.interfaces.DataType;
import lombok.NonNull;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

@ToString
public class Brightness implements DataType {

  private Integer value;

  @SuppressWarnings("RedundantIfStatement")
  private static boolean validate(String brightnessLevelString) {
    if (StringUtils.isBlank(brightnessLevelString)) {
      return false;
    }
    if (NumberUtils.isCreatable(brightnessLevelString.trim())) {
      return true;
    }
    return false;
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
