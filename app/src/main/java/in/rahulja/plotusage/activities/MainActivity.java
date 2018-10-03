package in.rahulja.plotusage.activities;

import android.os.Bundle;
import android.os.Environment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import in.rahulja.plotusage.R;
import in.rahulja.plotusage.adapters.LineAdapter;
import in.rahulja.plotusage.bo.CsvFile;
import in.rahulja.plotusage.interfaces.FileRow;
import in.rahulja.plotusage.utils.StorageUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

import static in.rahulja.plotusage.constants.CommonConstants.CALL_FILE_NAME_REGEX;
import static in.rahulja.plotusage.constants.CommonConstants.DISPLAY_FILE_NAME_REGEX;
import static in.rahulja.plotusage.constants.CommonConstants.LOCATION_FILE_NAME_REGEX;

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity {

  private RecyclerView mRecyclerView;
  private LineAdapter mAdapter;
  private RecyclerView.LayoutManager mLayoutManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mRecyclerView = findViewById(R.id.my_recycler_view);

    // use this setting to improve performance if you know that changes
    // in content do not change the layout size of the RecyclerView
    mRecyclerView.setHasFixedSize(true);

    // use a linear layout manager
    mLayoutManager = new LinearLayoutManager(this);
    mRecyclerView.setLayoutManager(mLayoutManager);

    List<FileRow> rows = new ArrayList<>();
    try {
      rows = getAllRows();
    } catch (IOException e) {
      Log.e(MainActivity.class.getName(), "Error occurred in Row Processing");
    }
    // specify an adapter (see also next example)
    mAdapter = new LineAdapter(rows);
    mRecyclerView.setAdapter(mAdapter);

    mRecyclerView.addItemDecoration(
        new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
  }

  private List<FileRow> getAllRows() throws IOException {
    List<FileRow> rows = new ArrayList<>();
    if (!StorageUtils.isExternalStorageReadable()) {
      return rows;
    }
    File taskerDir = Environment.getExternalStoragePublicDirectory("TaskerData");
    if (taskerDir.exists()) {
      File[] files = taskerDir.listFiles();
      for (File file : files) {
        if (file.isFile() && file.getName().contains("csv")) {
          try (BufferedReader reader = new BufferedReader(
              new InputStreamReader(new FileInputStream(file)))) {
            CsvFile csvFile = getCsvFile(file);
            String line = reader.readLine();
            while (line != null) {
              line = reader.readLine();
              if (StringUtils.isNotBlank(line)) {
                rows.add(csvFile.parseRow(file.getName(), line));
              }
            }
          }
        }
      }
    }
    return rows;
  }

  private CsvFile getCsvFile(@NonNull File file) {
    String fileNameRegex = StringUtils.EMPTY;
    if (file.getName().matches(CALL_FILE_NAME_REGEX)) {
      fileNameRegex = CALL_FILE_NAME_REGEX;
    }
    if (file.getName().matches(DISPLAY_FILE_NAME_REGEX)) {
      fileNameRegex = DISPLAY_FILE_NAME_REGEX;
    }
    if (file.getName().matches(LOCATION_FILE_NAME_REGEX)) {
      fileNameRegex = LOCATION_FILE_NAME_REGEX;
    }
    return CsvFile.builder().fileNameRegex(fileNameRegex).build();
  }
}
