package nl.waarisdetrein.hudlauncher;

import android.app.Fragment;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import nl.waarisdetrein.hudlauncher.apps.AppsAdapter;
import nl.waarisdetrein.hudlauncher.apps.AppsLoader;
import nl.waarisdetrein.hudlauncher.model.AppModel;

public class AppsFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<AppModel>>  {

    private RecyclerView mAppsView;
    private LinearLayoutManager mLayoutManager;
    private AppsAdapter mAdapter;
    private TextView mLoader;


    public AppsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_apps, container, false);

        mAppsView = (RecyclerView) v.findViewById(R.id.recycler_apps);
        mAppsView.setHasFixedSize(true);

        mLayoutManager = new GridLayoutManager(getActivity(), getNumberOfColumns());
        mAppsView.setLayoutManager(mLayoutManager);

        mAdapter = new AppsAdapter(getActivity());
        mAppsView.setAdapter(mAdapter);
        mAppsView.setVisibility(View.GONE);

        mLoader = (TextView) v.findViewById(R.id.label_loading);

        ((FragmentActivity) getActivity()).getSupportLoaderManager().initLoader(0, null, this);
        return v;
    }

    private int getNumberOfColumns() {
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        Log.i("App", "App has width of " + size.x);
        if (size.x <= 400) {
            return 4;
        } else {
            return 5;
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return super.onContextItemSelected(item);
    }

    @Override
    public Loader<ArrayList<AppModel>> onCreateLoader(int id, Bundle bundle) {
        Log.i("App", "Started app loader");
        return new AppsLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<AppModel>> loader, ArrayList<AppModel> apps) {
        Log.i("App", "Got " + apps.size() + " apps from loader");
        mAppsView.setVisibility(View.VISIBLE);
        mLoader.setVisibility(View.GONE);
        mAdapter.setData(apps);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<AppModel>> loader) {
        Log.i("App", "Got loader reset");
        mAdapter.setData(null);
    }

}
