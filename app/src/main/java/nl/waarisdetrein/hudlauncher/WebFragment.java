package nl.waarisdetrein.hudlauncher;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class WebFragment extends Fragment {

    private WebView mWeb;
    private String startUrl;

    public static WebFragment newInstance(String url) {
        WebFragment w = new WebFragment();
        Bundle args = new Bundle();
        args.putString("url", url);
        w.setArguments(args);
        return w;

    }

    public WebFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            startUrl = getArguments().getString("url");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_web, container, false);

        mWeb = (WebView) v.findViewById(R.id.view_web);
        mWeb.getSettings().setJavaScriptEnabled(true);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        mWeb.loadUrl(startUrl);
    }
}
