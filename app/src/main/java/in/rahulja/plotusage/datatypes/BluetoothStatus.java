package in.rahulja.plotusage.datatypes;

import in.rahulja.plotusage.enums.Status;
import in.rahulja.plotusage.interfaces.DataType;
import java.util.Collections;
import lombok.NonNull;
import lombok.ToString;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

@ToString
public class BluetoothStatus implements DataType {

  private Status value;

  @SuppressWarnings("RedundantIfStatement")
  private static boolean validate(String bluetoothStatusString) {
    if (StringUtils.isBlank(bluetoothStatusString)) {
      return false;
    }
    if (bluetoothStatusString.trim().equals("off") || bluetoothStatusString.trim().equals("on")) {
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
      if (data.trim().equals("off")) {
        value = Status.OFF;
      } else if (data.trim().equals("on")) {
        value = Status.ON;
      }
    }
  }
}
