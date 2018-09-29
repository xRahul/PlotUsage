package in.rahulja.plotusage.parsers;

import in.rahulja.plotusage.bo.CsvRow;
import in.rahulja.plotusage.interfaces.DataType;
import in.rahulja.plotusage.interfaces.FileRow;
import in.rahulja.plotusage.interfaces.RowParser;
import in.rahulja.plotusage.utils.RowParserUtils;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NonNull;

class LocationRowParser implements RowParser {
  @Override
  public FileRow parseRow(@NonNull String fileName, @NonNull String rowString) {
    List<String> rowCells = Arrays.stream(rowString.split(","))
        .map(String::trim)
        .collect(Collectors.toList());
    if (rowString.contains("%LOC") && rowCells.size() == 11) {
      List<DataType> = RowParserUtils.getCommonColumns(fileName, rowCells);
    }
  }
}
