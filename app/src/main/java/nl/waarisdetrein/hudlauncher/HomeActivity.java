package nl.waarisdetrein.hudlauncher;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Build;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.webkit.WebViewFragment;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import nl.waarisdetrein.hudlauncher.apps.AppsAdapter;
import nl.waarisdetrein.hudlauncher.apps.AppsLoader;
import nl.waarisdetrein.hudlauncher.model.AppModel;

public class HomeActivity extends AppCompatActivity implements ToolbarAdapter.OnToolbarItemClickListener {

    private RecyclerView mToolbarView;
    private ToolbarAdapter mToolbarAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        LinearLayout l = (LinearLayout) findViewById(R.id.root);
        SharedPreferences s = getSharedPreferences("pref", Context.MODE_PRIVATE);
        if (s.getBoolean("pref_background_show", false)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                l.setBackground(WallpaperManager.getInstance(this).getDrawable());
            }
        }

        mToolbarView = (RecyclerView) findViewById(R.id.recycler_toolbar);
        mToolbarView.setHasFixedSize(true);
        RecyclerView.LayoutManager toolbarManager = new LinearLayoutManager(this);
        mToolbarView.setLayoutManager(toolbarManager);
        mToolbarAdapter = new ToolbarAdapter(this, this);
        mToolbarView.setAdapter(mToolbarAdapter);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.fragment_main, new HomeFragment());
        transaction.commit();

    }

    @Override
    public void onToolbarItemClicked(int position) {
        switch(position) {
            case 0:
                replaceFragment(new HomeFragment());
                break;
            case 1:
                replaceFragment(new AppsFragment());
                break;
            case 2:
                replaceFragment(WebFragment.newInstance("http://192.168.1.90:8080/#/Floorplans"));
                break;
            case 3:
                replaceFragment(WebFragment.newInstance("http://192.168.1.100:9091/transmission/web/"));
                break;
            case 4:
                replaceFragment(new SettingsFragment());
                break;
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fragment_main, fragment);
        transaction.addToBackStack(null);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.commit();
    }
}
