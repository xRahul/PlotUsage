package in.rahulja.plotusage.datatypes;

import in.rahulja.plotusage.enums.Status;
import in.rahulja.plotusage.interfaces.DataType;
import lombok.NonNull;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

@ToString
public class DisplayStatus implements DataType {

  private Status value;

  @SuppressWarnings("RedundantIfStatement")
  private static boolean validate(String displayStatusString) {
    if (StringUtils.isBlank(displayStatusString)) {
      return false;
    }
    if (displayStatusString.trim().equals("DisplayOn")
        || displayStatusString.trim().equals("DisplayOff")
        || displayStatusString.trim().equals("DisplayUnlocked")) {
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
      switch (data.trim()) {
        case "DisplayOff":
          value = Status.OFF;
          break;
        case "DisplayOn":
          value = Status.ON;
          break;
        case "DisplayUnlocked":
          value = Status.UNLOCKED;
          break;
        default:
      }
    }
  }
}
