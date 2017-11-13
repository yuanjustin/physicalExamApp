package view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Justin.Yuan.ClinicalSkillApp.MainActivity;
import com.Justin.Yuan.ClinicalSkillApp.R;

import moe.feng.alipay.zerosdk.AlipayZeroSdk;

/**
 * A simple {@link Fragment} subclass.
 */
public class aboutFragment extends Fragment {

MainActivity mainActivity;
    public aboutFragment(MainActivity mainActivity) {
        this.mainActivity=mainActivity;
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_about, container, false);
        TextView t=(TextView)v.findViewById(R.id.textView2);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AlipayZeroSdk.hasInstalledAlipayClient(mainActivity)){
                    AlipayZeroSdk.startAlipayClient(mainActivity,"a6x07259dtyuujb9kg4mk09");
                }else{
                    Toast.makeText( mainActivity,"您未安装支付宝", Toast.LENGTH_LONG).show();
                }
            }
        });
        ImageView image=(ImageView)v.findViewById(R.id.imageView2);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AlipayZeroSdk.hasInstalledAlipayClient(mainActivity)){
                    AlipayZeroSdk.startAlipayClient(mainActivity,"a6x07259dtyuujb9kg4mk09");
                }
                else{
                    Toast.makeText( mainActivity,"您未安装支付宝", Toast.LENGTH_LONG).show();
                }
            }
        });
        return v;

    }
}
