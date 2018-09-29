package in.rahulja.plotusage.datatypes;

import in.rahulja.plotusage.enums.MemoryType;
import in.rahulja.plotusage.interfaces.DataType;
import in.rahulja.plotusage.pojos.Memory;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

public class FreeMemory implements DataType {

  private Memory value;

  @SuppressWarnings("RedundantIfStatement")
  private static boolean validate(String freeMemoryString) {
    if (StringUtils.isBlank(freeMemoryString)) {
      return false;
    }
    String memoryType = freeMemoryString.substring(freeMemoryString.length() - 2);
    if (!memoryType.equals("MB")) {
      return false;
    }
    try {
      Integer.parseInt(freeMemoryString.substring(0, freeMemoryString.length() - 2));
    } catch (Exception e) {
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
      value = Memory.builder().memoryType(MemoryType.MB)
          .value(Integer.parseInt(data.substring(0, data.length() - 2)))
          .build();
    }
  }
}
