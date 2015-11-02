package nl.waarisdetrein.hudlauncher;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by joelthuis on 01/11/15.
 */
public class ToolbarAdapter extends RecyclerView.Adapter<ToolbarAdapter.ViewHolder> {

    private final Context ctx;
    private OnToolbarItemClickListener listener;
    private static ViewHolder current;

    public ToolbarAdapter(Context ctx, OnToolbarItemClickListener listener) {
        this.ctx = ctx;
        this.listener = listener;
    }

    @Override
    public ToolbarAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_tool, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ToolbarAdapter.ViewHolder holder, final int position) {
        if (position == 0) {
            holder.icon.setImageResource(R.drawable.ic_home_lg);
            if (current == null) {
                holder.indicator.setVisibility(View.VISIBLE);
                current = holder;
            }
        } else if (position == 1) {
            holder.icon.setImageResource(R.drawable.ic_apps_lg);
        } else if (position == 2) {
            holder.icon.setImageResource(R.drawable.ic_power_lg);
        } else if (position == 3) {
            holder.icon.setImageResource(R.drawable.ic_download_lg);
        } else if (position == 4) {
            holder.icon.setImageResource(R.drawable.ic_settings_lg);
        }
        holder.cell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current.indicator.setVisibility(View.GONE);
                holder.indicator.setVisibility(View.VISIBLE);
                current = holder;
                listener.onToolbarItemClicked(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        View indicator;
        View cell;
        ImageView icon;

        public ViewHolder(View v) {
            super(v);
            cell = v;
            icon = (ImageView) v.findViewById(R.id.icon_tool);
            indicator = v.findViewById(R.id.indicator_current);
        }
    }

    public interface OnToolbarItemClickListener {
        void onToolbarItemClicked(int position);
    }
}
