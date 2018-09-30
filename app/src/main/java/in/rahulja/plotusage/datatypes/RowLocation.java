package in.rahulja.plotusage.datatypes;

import in.rahulja.plotusage.interfaces.DataType;
import in.rahulja.plotusage.pojos.Location;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.NonNull;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import static in.rahulja.plotusage.constants.CommonConstants.COMMA;

@ToString
public class RowLocation implements DataType {

  private Location value;

  @SuppressWarnings("RedundantIfStatement")
  private static boolean validate(String locationString) {
    if (StringUtils.isBlank(locationString)) {
      return false;
    }
    List<Double> latLongValues;
    try {
      latLongValues = Stream.of(locationString.split(COMMA))
          .map(String::trim)
          .map(Double::parseDouble)
          .collect(Collectors.toList());
    } catch (Exception e) {
      return false;
    }
    if (latLongValues.get(0) < -90 || latLongValues.get(0) > 90) {
      return false;
    }
    if (latLongValues.get(1) < -180 || latLongValues.get(1) > 180) {
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
      List<Double> latLongValues = Stream.of(data.split(COMMA))
          .map(Double::parseDouble)
          .collect(Collectors.toList());
      value = Location.builder()
          .latitude(latLongValues.get(0))
          .longitude(latLongValues.get(1))
          .build();
    }
  }
}
