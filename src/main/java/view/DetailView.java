package view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.Justin.Yuan.ClinicalSkillApp.MainActivity;
import com.Justin.Yuan.ClinicalSkillApp.R;

import model.DetailModel;

/**
 * Created by RUI on 2017/5/28.
 */

public class DetailView extends Fragment {
    private  MainActivity mainActivity;
    DetailModel detailModel;
    private WebView webView;

    public DetailView(MainActivity mainActivity) {
        this.mainActivity=mainActivity;
    }

    public void refresh(DetailModel detailModel, MainActivity mainActivity) {
//        FragmentManager f=mainActivity.getSupportFragmentManager();
//        FragmentTransaction transaction =  f.beginTransaction();
//        transaction.replace(R.id.webview,this);
//        transaction.commit();
        this.detailModel=detailModel;
        // 装载web
        if(webView!=null){
            webView.loadUrl("file:///android_asset/"+detailModel.getHtml()+".htm");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayout recyclerView = (LinearLayout) inflater.inflate(
                R.layout.detail, container, false);
        webView = (WebView) recyclerView.findViewById(R.id.detailWebView);

// 设置可以支持缩放
        webView.getSettings().setSupportZoom(true);
// 设置出现缩放工具
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
//扩大比例的缩放
        webView.getSettings().setUseWideViewPort(true);
//自适应屏幕
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);

        webView.getSettings().setLoadWithOverviewMode(true);

        refresh(detailModel,mainActivity);
        return recyclerView;
    }
}
