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

import static in.rahulja.plotusage.constants.CommonConstants.COMMA;

class DisplayRowParser implements RowParser {
  @Override
  public FileRow parseRow(@NonNull String fileName, @NonNull String rowString) {
    List<String> rowCells = Arrays.stream(rowString.split(COMMA))
        .map(String::trim)
        .collect(Collectors.toList());
    List<DataType> commonColumns = RowParserUtils.getCommonColumns(fileName, rowCells);
    return CsvRow.builder().columns(commonColumns).build();
  }
}
