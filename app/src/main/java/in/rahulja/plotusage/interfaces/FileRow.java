package in.rahulja.plotusage.interfaces;

import androidx.annotation.NonNull;
import java.util.List;

public interface FileRow {
  public List<DataType> getColumns();

  @NonNull
  public String toString();
}
