package in.rahulja.plotusage.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import in.rahulja.plotusage.R;
import in.rahulja.plotusage.interfaces.FileRow;
import in.rahulja.plotusage.viewHolders.LineHolder;
import java.util.List;

public class LineAdapter extends RecyclerView.Adapter<LineHolder> {

  private final List<FileRow> rows;

  public LineAdapter(List<FileRow> rows) {
    this.rows = rows;
  }

  @NonNull @Override
  public LineHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new LineHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.main_line_view, parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull LineHolder holder, int position) {
    holder.title.setText(rows.get(position).toString());
  }

  @Override
  public int getItemCount() {
    return rows != null ? rows.size() : 0;
  }
}