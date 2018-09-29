package in.rahulja.plotusage.pojos;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Call {
  private String name;
  private String number;
  private Integer duration;
}
