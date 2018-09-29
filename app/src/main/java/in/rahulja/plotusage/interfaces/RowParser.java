package in.rahulja.plotusage.interfaces;

import lombok.NonNull;

public interface RowParser {
  public FileRow parseRow(@NonNull String fileName, @NonNull String rowString);
}
