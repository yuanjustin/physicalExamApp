package view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Justin.Yuan.ClinicalSkillApp.MainActivity;
import com.Justin.Yuan.ClinicalSkillApp.R;

import java.util.ArrayList;
import java.util.List;

import model.MethodListModel;

/**
 * Created by RUI on 2017/5/29.
 */

public class MethodListView  extends Fragment{
    private  MainActivity mainActivity;
    MethodListModel methodListModel;
    ViewPager viewPager ;
    TabLayout tabs ;
    boolean added=false;
    public MethodListView(MethodListModel m,MainActivity mainActivity) {
        this.methodListModel=m;
        this.mainActivity=mainActivity;
    }

    //====================tabview=============
    public void refresh(MethodListModel m ) {
        /*//替换layout
        CoordinatorLayout tab = (CoordinatorLayout) mainActivity.findViewById(R.id.tab);
        RecyclerView notab = (RecyclerView) mainActivity.findViewById(R.id.noTab);
        notab.setVisibility(View.GONE);
        tab.setVisibility(View.VISIBLE);*/
        // Setting ViewPager for each Tabs
       /* ViewPager viewPager = (ViewPager) mainActivity.findViewById(R.id.viewpager);
        TabLayout tabs = (TabLayout) mainActivity.findViewById(R.id.tabs);*/

      /* LayoutInflater inflater = (LayoutInflater)mainActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        CoordinatorLayout groupPollingAddress = (CoordinatorLayout)inflater.inflate(R.layout.content_main, null);
*/
        //  ViewPager viewPager = (ViewPager) mainActivity.findViewById(R.id.viewpager);
       /* Adapter adapter = new Adapter(mainActivity.getSupportFragmentManager());
        // Add Fragments to Tabs
        // mainActivity.getActionBar().setTitle(m.getTitle());
        for(CardListModel cl:m.getCardListModel()){
            adapter.addFragment( new CardListView(cl.getDetailModel(),mainActivity), cl.getTitle());
        }

        viewPager.setAdapter(adapter);
        // Set Tabs inside Toolbar
       // TabLayout tabs = (TabLayout) mainActivity.findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
       // mainActivity.setContentView(groupPollingAddress);*/

//        FragmentManager f=mainActivity.getSupportFragmentManager();
//        FragmentTransaction transaction =  f.beginTransaction();
//        transaction.replace(R.id.id_content,this);
//        transaction.commit();
        //  ViewPager viewPager = (ViewPager) mainActivity.findViewById(R.id.viewpager);
        this.methodListModel=m;
        if (viewPager==null){return;}
        Adapter adapter = new Adapter(mainActivity.getSupportFragmentManager());
        // Add Fragments to Tabs
        // mainActivity.getActionBar().setTitle(m.getTitle());
        /*for(int i=0;i<=methodListModel.getCardListModel().size()-1;i++){
            adapter.addFragment( new CardListView(methodListModel.getCardListModel().get(i).getDetailModel(),mainActivity,i), methodListModel.getCardListModel().get(i).getTitle());
        }*/

        if(added){
            ArrayList<String> mT = new ArrayList<>();
            for(int i=0;i<=methodListModel.getCardListModel().size()-1;i++) {
                mT.add(methodListModel.getCardListModel().get(i).getTitle());
            }
            adapter.setTitle(mT);
            ArrayList<Fragment> mF = new ArrayList<>();
            for(int i=0;i<=methodListModel.getCardListModel().size()-1;i++) {
                mF.add( new CardListView(methodListModel.getCardListModel().get(i).getDetailModel(), mainActivity, i));
            }
            adapter.setFragments( mF);
            viewPager.setAdapter(adapter);
            // Set Tabs inside Toolbar
            // TabLayout tabs = (TabLayout) mainActivity.findViewById(R.id.tabs);
            tabs.setupWithViewPager(viewPager);
            if(methodListModel.getCardListModel().size()>=5){
                tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
            }else{
                tabs.setTabMode(TabLayout.MODE_FIXED);
            }
            adapter.notifyDataSetChanged();
        }else{
             for(int i=0;i<=methodListModel.getCardListModel().size()-1;i++){
                 adapter.addFragment( new CardListView(methodListModel.getCardListModel().get(i).getDetailModel(),mainActivity,i), methodListModel.getCardListModel().get(i).getTitle());
            }

            viewPager.setAdapter(adapter);
            // Set Tabs inside Toolbar
            // TabLayout tabs = (TabLayout) mainActivity.findViewById(R.id.tabs);
            tabs.setupWithViewPager(viewPager);
            if(methodListModel.getCardListModel().size()>=5){
                tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
            }else{
                tabs.setTabMode(TabLayout.MODE_FIXED);
            }
            //tabs.setTabsFromPagerAdapter(adapter);
            added=true;
        }




    }
    static class Adapter extends FragmentStatePagerAdapter {
        private  List<Fragment> mFragmentList = new ArrayList<>();
        private  List<String> mFragmentTitleList = new ArrayList<>();
        private  FragmentManager fm;
        public void setFragments(ArrayList<Fragment> fragments){
            if(this.mFragmentList!=null){
                FragmentTransaction ft=fm.beginTransaction();
                for(Fragment f:this.mFragmentList){
                    
                    ft.remove(f);
                    ft.commit();
                    ft=null;
                    fm.executePendingTransactions();
                }
                this.mFragmentList=fragments;
                notifyDataSetChanged();
            }
        }
        public Adapter(FragmentManager manager) {
            super(manager);
            fm=manager;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

        public void setTitle(ArrayList<String> title) {
            this.mFragmentTitleList = title;
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        android.support.design.widget.CoordinatorLayout recyclerView = (android.support.design.widget.CoordinatorLayout) inflater.inflate(
                R.layout.content_main, container, false);
        viewPager = (ViewPager) recyclerView.findViewById(R.id.viewpager);
        tabs = (TabLayout) recyclerView.findViewById(R.id.tabs);
        refresh(methodListModel);
        return recyclerView;
    }

}
