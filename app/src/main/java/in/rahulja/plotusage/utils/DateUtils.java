package in.rahulja.plotusage.utils;

import in.rahulja.plotusage.exceptions.InvalidDataException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import static in.rahulja.plotusage.constants.CommonConstants.DEFAULT_TIMEZONE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtils {

  public static int getFourDigitYear(@NonNull int year) {
    ZonedDateTime now = ZonedDateTime.now(ZoneId.of(DEFAULT_TIMEZONE));
    int lastCentury = (now.getYear() - (now.getYear() % 100)) - 100;
    int currentCentury = now.getYear() - (now.getYear() % 100);
    if (year >= 0 && year <= 99) {
      if (year + currentCentury > now.getYear()) {
        return year + lastCentury;
      } else {
        return year + currentCentury;
      }
    } else if (year > now.getYear()-100 && year < now.getYear()+100) {
      return year;
    }
    throw new InvalidDataException("Year is invalid and not in the last 100 years: " + year);
  }
}
