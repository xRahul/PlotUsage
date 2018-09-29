package in.rahulja.plotusage.parsers;

import in.rahulja.plotusage.exceptions.ParserNotFoundException;
import in.rahulja.plotusage.interfaces.RowParser;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

public class RowParserFactory {

  public RowParser getRowParser(@NonNull String fileNameRegex) {
    if (StringUtils.isBlank(fileNameRegex)) {
      throw new ParserNotFoundException("Can not file a RowParser for: " + fileNameRegex);
    }
    if (fileNameRegex.equalsIgnoreCase("^Call_.*\\.csv$")) {
      return new CallRowParser();
    } else if (fileNameRegex.equalsIgnoreCase("^Display_.*\\.csv$")) {
      return new DisplayRowParser();
    } else if (fileNameRegex.equalsIgnoreCase("^Location_.*\\.csv$")) {
      return new LocationRowParser();
    }
    return null;
  }
}
