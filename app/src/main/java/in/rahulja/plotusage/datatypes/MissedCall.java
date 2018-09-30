package in.rahulja.plotusage.datatypes;

import in.rahulja.plotusage.interfaces.DataType;
import in.rahulja.plotusage.pojos.Call;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.NonNull;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import static in.rahulja.plotusage.constants.CommonConstants.COMMA;

@ToString
public class MissedCall implements DataType {

  private Call value;

  @SuppressWarnings("RedundantIfStatement")
  private static boolean validate(String missedCallString) {
    if (StringUtils.isBlank(missedCallString)) {
      return false;
    }
    List<String> callValues = Stream.of(missedCallString.split(COMMA))
        .map(String::trim)
        .collect(Collectors.toList());
    try {
      Integer.parseInt(callValues.get(2));
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
      List<String> callValues = Stream.of(data.split(COMMA))
          .map(String::trim)
          .collect(Collectors.toList());
      value = Call.builder()
          .name(callValues.get(0))
          .number(callValues.get(1))
          .duration(Integer.parseInt(callValues.get(2)))
          .build();
    }
  }
}
