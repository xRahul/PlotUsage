package in.rahulja.plotusage.utils;

import in.rahulja.plotusage.datatypes.RowDateTime;
import in.rahulja.plotusage.interfaces.DataType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RowParserUtils {

  public static List<DataType> getCommonColumns(@NonNull String fileName,
      @NonNull List<String> rowCells) {
    ArrayList<DataType> commonColumns = new ArrayList<>();
    DataType rowDateTime = getRowDateTime(fileName, rowCells);
    commonColumns.add(rowDateTime)

  }

  private static void getRowDateTime(@NonNull String fileName, @NonNull List<String> rowCells) {
    List<Integer> dateDdMmYy = Arrays.stream(
        fileName.substring(fileName.indexOf('_') + 1, fileName.indexOf('.')).split("-"))
        .map(String::trim)
        .map(Integer::parseInt)
        .collect(Collectors.toList());
    List<Integer> timeHhMm = Arrays.stream(
        rowCells.get(0).split("\\."))
        .map(String::trim)
        .map(Integer::parseInt)
        .collect(Collectors.toList());

  }
}
