package in.rahulja.plotusage.bo;

import in.rahulja.plotusage.interfaces.FileRow;
import in.rahulja.plotusage.interfaces.RowParser;
import in.rahulja.plotusage.parsers.RowParserFactory;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class CsvFile {
  private String path;
  private String fileNameRegex;

  public FileRow parseRow(@NonNull String fileName, @NonNull String rowString) {
    RowParser rowParser = new RowParserFactory().getRowParser(fileNameRegex);
    return rowParser.parseRow(fileName, rowString);
  }
}
