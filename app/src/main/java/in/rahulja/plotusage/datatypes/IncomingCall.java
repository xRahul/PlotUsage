package in.rahulja.plotusage.datatypes;

import in.rahulja.plotusage.interfaces.DataType;
import in.rahulja.plotusage.pojos.Call;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

import static in.rahulja.plotusage.constants.CommonConstants.COMMA;

public class IncomingCall implements DataType {

  private Call value;

  @SuppressWarnings("RedundantIfStatement")
  private static boolean validate(String incomingCallString) {
    if (StringUtils.isBlank(incomingCallString)) {
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
          .build();
    }
  }
}
