package nl.waarisdetrein.hudlauncher;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by joelthuis on 01/11/15.
 */
public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private final Context ctx;

    public NotificationAdapter(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public NotificationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_notification, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(NotificationAdapter.ViewHolder holder, final int position) {
        if (position == 0) {
            holder.time.setText("15:42");
            holder.message.setText("Alarm disarmed");
            holder.sender.setText("Security");
        } else {
            holder.time.setText("11:56");
            holder.message.setText("Download Complete");
            holder.sender.setText("Transmission");
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        View view;
        TextView time;
        TextView message;
        TextView sender;

        public ViewHolder(View v) {
            super(v);
            view = v;
            time = (TextView) v.findViewById(R.id.label_time);
            message = (TextView) v.findViewById(R.id.label_message);
            sender = (TextView) v.findViewById(R.id.label_sender);


        }
    }

}
