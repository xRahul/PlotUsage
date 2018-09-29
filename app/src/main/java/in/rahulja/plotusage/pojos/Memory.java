package in.rahulja.plotusage.pojos;

import in.rahulja.plotusage.enums.MemoryType;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Memory {
  private Integer value;
  private MemoryType memoryType;
}
