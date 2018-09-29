package in.rahulja.plotusage.datatypes;

import in.rahulja.plotusage.interfaces.DataType;
import in.rahulja.plotusage.utils.DateUtils;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

import static in.rahulja.plotusage.constants.CommonConstants.COMMA;
import static in.rahulja.plotusage.constants.CommonConstants.DEFAULT_TIMEZONE;

public class RowDateTime implements DataType {

  private ZonedDateTime value;

  @SuppressWarnings("RedundantIfStatement")
  private static boolean validate(String dateTimeString) {
    if (StringUtils.isBlank(dateTimeString)) {
      return false;
    }
    List<Integer> dateTimeValues;
    try {
      dateTimeValues = Stream.of(dateTimeString.split(COMMA))
          .map(String::trim)
          .map(Integer::parseInt)
          .collect(Collectors.toList());
    } catch (Exception e) {
      return false;
    }
    if (dateTimeValues.size() < 5) {
      return false;
    }
    if (dateTimeValues.get(0) < 0 || dateTimeValues.get(0) > 9999) {
      return false;
    }
    if (dateTimeValues.get(1) < 1 || dateTimeValues.get(1) > 12) {
      return false;
    }
    if (dateTimeValues.get(2) < 1 || dateTimeValues.get(2) > 31) {
      return false;
    }
    if (dateTimeValues.get(3) < 0 || dateTimeValues.get(3) > 24) {
      return false;
    }
    if (dateTimeValues.get(4) < 0 || dateTimeValues.get(0) > 60) {
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
      List<Integer> dateTimeValues = Stream.of(data.split(COMMA))
          .map(String::trim)
          .map(Integer::parseInt)
          .collect(Collectors.toList());
      int year = DateUtils.getFourDigitYear(dateTimeValues.get(0));
      int month = dateTimeValues.get(1);
      int day = dateTimeValues.get(2);
      int hour = dateTimeValues.get(3);
      int minute = dateTimeValues.get(4);
      LocalDateTime localDateTime = LocalDateTime.of(year, month, day, hour, minute);
      value = ZonedDateTime.of(localDateTime, ZoneId.of(DEFAULT_TIMEZONE));
    }
  }
}
