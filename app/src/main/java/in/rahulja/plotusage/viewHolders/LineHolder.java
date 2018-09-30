package in.rahulja.plotusage.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import in.rahulja.plotusage.R;

public class LineHolder extends RecyclerView.ViewHolder {

  public TextView title;
  public ImageButton moreButton;
  public ImageButton deleteButton;

  public LineHolder(View itemView) {
    super(itemView);
    title = (TextView) itemView.findViewById(R.id.main_line_title);
  }
}