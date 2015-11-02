package nl.waarisdetrein.hudlauncher.apps;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import nl.waarisdetrein.hudlauncher.R;
import nl.waarisdetrein.hudlauncher.model.AppModel;

/**
 * Created by joelthuis on 31/10/15.
 */
public class AppsAdapter extends RecyclerView.Adapter<AppsAdapter.ViewHolder> {

    private final Context ctx;
    private ArrayList<AppModel> mApps = new ArrayList<>();

    public AppsAdapter(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public AppsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.cell_app, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(AppsAdapter.ViewHolder holder, int position) {
        final AppModel app = mApps.get(position);
        holder.mAppName.setText(app.getLabel());
        holder.mIcon.setImageDrawable(app.getIcon());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = ctx.getPackageManager().getLaunchIntentForPackage(app.getApplicationPackageName());

                if (intent != null) {
                    ctx.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mApps.size();
    }

    public void setData(ArrayList<AppModel> apps) {
        mApps.clear();
        mApps.addAll(apps);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        View mView;
        TextView mAppName;
        ImageView mIcon;

        public ViewHolder(View v) {
            super(v);
            mView = v;
            mAppName = (TextView) v.findViewById(R.id.label_app_name);
            mIcon = (ImageView) v.findViewById(R.id.icon_app);
            v.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle(v.getContext().getString(R.string.title_app_context));
            menu.add(0, v.getId(), 0, "Add to home");
            menu.add(0, v.getId(), 0, "Uninstall");
        }



    }
}
