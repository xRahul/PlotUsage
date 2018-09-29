package in.rahulja.plotusage.pojos;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Location {
  private Double latitude;
  private Double longitude;
}
