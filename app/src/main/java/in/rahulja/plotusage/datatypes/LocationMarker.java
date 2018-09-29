package in.rahulja.plotusage.datatypes;

import in.rahulja.plotusage.enums.LocationAt;
import in.rahulja.plotusage.interfaces.DataType;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

public class LocationMarker implements DataType {

  private LocationAt value;

  @SuppressWarnings("RedundantIfStatement")
  private static boolean validate(String locationMarkerString) {
    if (StringUtils.isBlank(locationMarkerString)) {
      return false;
    }
    if (locationMarkerString.trim().equals("ReachedHome")
        || locationMarkerString.trim().equals("LeftHome")
        || locationMarkerString.trim().equals("ReachedOffice")
        || locationMarkerString.trim().equals("LeftOffice")) {
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
        case "ReachedHome":
          value = LocationAt.HOME_REACHED;
          break;
        case "LeftHome":
          value = LocationAt.HOME_LEFT;
          break;
        case "ReachedOffice":
          value = LocationAt.OFFICE_REACHED;
          break;
        case "LeftOffice":
          value = LocationAt.OFFICE_LEFT;
          break;
        default:
      }
    }
  }
}
