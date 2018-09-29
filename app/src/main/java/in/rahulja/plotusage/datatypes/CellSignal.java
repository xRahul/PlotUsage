package in.rahulja.plotusage.datatypes;

import in.rahulja.plotusage.interfaces.DataType;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class CellSignal implements DataType {

  private Integer value;

  @SuppressWarnings("RedundantIfStatement")
  private static boolean validate(String cellSignalString) {
    if (StringUtils.isBlank(cellSignalString)) {
      return false;
    }
    if (NumberUtils.isCreatable(cellSignalString.trim())) {
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
