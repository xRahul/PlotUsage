package in.rahulja.plotusage.constants;

import android.icu.util.TimeZone;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonConstants {
  public static final String DEFAULT_TIMEZONE = TimeZone.getDefault().getID();
  public static final String COMMA = ",";
}
