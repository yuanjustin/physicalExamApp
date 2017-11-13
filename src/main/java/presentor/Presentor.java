package presentor;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.Justin.Yuan.ClinicalSkillApp.MainActivity;
import com.Justin.Yuan.ClinicalSkillApp.R;

import java.util.ArrayList;
import java.util.List;

import bean.Data;
import bean.SystemsBean;
import model.*;
import view.*;
public class Presentor
{
	private  MainActivity mainActivity;
	Data data;

	MainModel mainModel;
	SearchModel searchModel;
	DetailModel detailModel;
    MethodListModel methodListModel;

	MainView mainView;
	SearchView searchView;
	MethodListView methodListView;
	DetailView detailView;
    view.aboutFragment aboutFragment;
    private List<Fragment> fragments = new ArrayList<>();
    private Fragment currentFragment;
    int currentIndex;
    public Presentor(MainActivity mainActivity) {
		this.mainActivity=mainActivity;
    }

    public void initial() {
        //initial
        data=new Data(mainActivity);
        mainModel=new MainModel(mainActivity,data);//initial index
        mainView=new MainView(mainActivity);

        //ini search
        searchModel=new SearchModel();
//        searchModel.add(new CardModel("123"));
//        searchModel.add(new CardModel("123"));
        searchView=new SearchView(mainActivity);
        fragments.add(searchView);
        //ini detail
        detailModel= new DetailModel();
        detailView=new DetailView(mainActivity);
        fragments.add(detailView);

        //ini about
        aboutFragment=new aboutFragment(mainActivity);
        fragments.add(aboutFragment);

        //ini cardlist
        methodListModel= new MethodListModel();
        methodListView=new MethodListView(methodListModel,mainActivity);
        fragments.add(methodListView);

		refresh();
	}

    public void refresh() {
        FragmentManager f=mainActivity.getSupportFragmentManager();
        FragmentTransaction transaction =  f.beginTransaction();
        Toolbar toolbar = (Toolbar) mainActivity.findViewById(R.id.toolbar);
        if (mainModel.getFragmentIndex()==MainModel.SEARCH_LIST){//search
            currentIndex=0;
            toolbar.setTitle("搜索结果："+searchModel.getCardList().size()+"条");
            showFragment();
            searchView.refresh(searchModel);
        }else if (mainModel.getFragmentIndex()==MainModel.DETAIL_LIST){//detail
            currentIndex=1;
            toolbar.setTitle(detailModel.getTitle());
            showFragment();
            detailView.refresh(detailModel,mainActivity);
        }else if (mainModel.getFragmentIndex()==MainModel.ABOUT_LIST){//about
            toolbar.setTitle("关于本APP");
            currentIndex=2;
            showFragment();
        }else{
            currentIndex=3;
            showFragment();
            methodListModel.refresh(mainModel.getSystemIndex(),data,mainActivity);
            methodListView.refresh(methodListModel);
            toolbar.setTitle(methodListModel.getTitle());
        }
		//mainView.refreshCardList(mainModel.getMethodListModel());
		//mainView.refreshSearch(searchModel);
	}

	public void selectDraw(int id, CharSequence title) {
		if(id== R.id.nav_share){
				mainModel.setFragmentIndex(MainModel.ABOUT_LIST);
		}else{
            for( int i=0;i<=data.getSystems().size()-1;i++){
                SystemsBean systemsBean=data.getSystems(i);
                if(title.equals(systemsBean.getName())){
                    mainModel.setSystemIndex(i);
                    break;
                }
            }
        }
		refresh();
		}


	public void search(String query) {
		searchModel.refesh(query,data,mainActivity);
        mainModel.setFragmentIndex(MainModel.SEARCH_LIST);
		refresh();
	}

	public void click(View v, int methodIndex, int cardIndex) {
		//Toast.makeText(mainActivity, "This is a card view!"+methodIndex+"-"+cardIndex, Toast.LENGTH_LONG).show();
       int systemIndex;
        if(mainModel.getFragmentIndex()==MainModel.SEARCH_LIST){//如果搜索
            CardModel cardModel=searchModel.getCardList().get(cardIndex);
            systemIndex=cardModel.getSystemIndex();
            methodIndex=cardModel.getMethodIndex();
            cardIndex=cardModel.getCardIndex();
        }else{
            systemIndex=mainModel.getSystemIndex();
        }
        detailModel.setHtml(systemIndex,methodIndex,cardIndex,data);
        mainModel.setFragmentIndex(MainModel.DETAIL_LIST);
        refresh();
	}

    private void showFragment(){

        FragmentManager f=mainActivity.getSupportFragmentManager();
        FragmentTransaction transaction =  f.beginTransaction();
        //如果之前没有添加过
        if(!fragments.get(currentIndex).isAdded()){
            if(currentFragment!=null){ transaction
                    .hide(currentFragment);} transaction
                    .add(R.id.id_content,fragments.get(currentIndex),""+currentIndex);  //第三个参数为添加当前的fragment时绑定一个tag

        }else{
            if(currentFragment!=null){ transaction
                    .hide(currentFragment);} transaction

                    .show(fragments.get(currentIndex));

        }

        currentFragment = fragments.get(currentIndex);

        transaction.commit();

    }

    public void restore(Bundle savedInstanceState) {
          final String CURRENT_FRAGMENT = "STATE_FRAGMENT_SHOW";

        //获取“内存重启”时保存的索引下标
        currentIndex = savedInstanceState.getInt(CURRENT_FRAGMENT,0);
        FragmentManager f=mainActivity.getSupportFragmentManager();

        //注意，添加顺序要跟下面添加的顺序一样！！！！
        fragments.removeAll(fragments);
        fragments.add(f.findFragmentByTag(0+""));
        fragments.add(f.findFragmentByTag(1+""));
        fragments.add(f.findFragmentByTag(2+""));

        //恢复fragment页面
        FragmentTransaction mBeginTreansaction = f.beginTransaction();

        for (int i = 0; i < fragments.size(); i++) {
            if(i == currentIndex){
                mBeginTreansaction.show(fragments.get(i));
            }else{
                mBeginTreansaction.hide(fragments.get(i));
            }

        }

        mBeginTreansaction.commit();

        //把当前显示的fragment记录下来
        currentFragment = fragments.get(currentIndex);
    }
   public boolean backToMethodList(){
       if(currentIndex!=3){
           Toolbar toolbar = (Toolbar) mainActivity.findViewById(R.id.toolbar);
           toolbar.setTitle(methodListModel.getTitle());
           currentIndex=3;
        showFragment();
           return false;
       }
       return true;
    }
}


	//===========================cardlist================

	//====================================cardlist
//


//
//	void showDetail(int sIndex, int mInde, int cIndex){
//		mainModel.setSystemIndex(-1);
//		model.getDetail(sIndex,mInde,cIndex);
//	}
//	void showList(int index){
//		mainModel.setSystemIndex(index);
//		mainView.refreshView(mainModel);
//	}
//	void showSearch(String str){
//		mainModel.setSystemIndex(0);
//		mainView.refreshView(mainModel);
//	}
//	void showAbout(){}
//

//	void SearchChange( String str){
//	model.setSearch(str);
//	model.refrshSearch(search(model.getSearch));
//	}

