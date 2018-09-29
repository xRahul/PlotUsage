package in.rahulja.plotusage.bo;

import in.rahulja.plotusage.interfaces.DataType;
import in.rahulja.plotusage.interfaces.FileRow;
import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CsvRow implements FileRow {
  private List<DataType> columns;
}
