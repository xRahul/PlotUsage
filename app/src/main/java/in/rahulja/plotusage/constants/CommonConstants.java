package in.rahulja.plotusage.constants;

import android.icu.util.TimeZone;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonConstants {
  public static final String DEFAULT_TIMEZONE = TimeZone.getDefault().getID();
  public static final String COMMA = ",";
  public static final String PERCENT_LOC = "%LOC";
  public static final String CALL_FILE_NAME_REGEX = "^Call_.*\\.csv$";
  public static final String DISPLAY_FILE_NAME_REGEX = "^Display_.*\\.csv$";
  public static final String LOCATION_FILE_NAME_REGEX = "^Location_.*\\.csv$";
}
