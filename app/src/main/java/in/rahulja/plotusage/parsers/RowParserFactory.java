package in.rahulja.plotusage.parsers;

import in.rahulja.plotusage.exceptions.ParserNotFoundException;
import in.rahulja.plotusage.interfaces.RowParser;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

import static in.rahulja.plotusage.constants.CommonConstants.CALL_FILE_NAME_REGEX;
import static in.rahulja.plotusage.constants.CommonConstants.DISPLAY_FILE_NAME_REGEX;
import static in.rahulja.plotusage.constants.CommonConstants.LOCATION_FILE_NAME_REGEX;

public class RowParserFactory {

  public RowParser getRowParser(@NonNull String fileNameRegex) {
    if (StringUtils.isBlank(fileNameRegex)) {
      throw new ParserNotFoundException("Can not file a RowParser for: " + fileNameRegex);
    }
    if (fileNameRegex.equalsIgnoreCase(CALL_FILE_NAME_REGEX)) {
      return new CallRowParser();
    } else if (fileNameRegex.equalsIgnoreCase(DISPLAY_FILE_NAME_REGEX)) {
      return new DisplayRowParser();
    } else if (fileNameRegex.equalsIgnoreCase(LOCATION_FILE_NAME_REGEX)) {
      return new LocationRowParser();
    }
    return null;
  }
}
