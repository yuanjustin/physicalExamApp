package model;

import com.Justin.Yuan.ClinicalSkillApp.MainActivity;

import bean.Data;

public class MainModel
{
  Data data;
  public static int MERHOD_LIST=1;
  public static int DETAIL_LIST=0;
  public static int SEARCH_LIST=-1;
  public static int ABOUT_LIST=-2;

  DetailModel detailModel;

  public Data getData() {
    return data;
  }

  public void setData(Data data) {
    this.data = data;
  }

  MethodListModel methodListModel;
SearchModel searchModel;


  public void setMethodListModel(MethodListModel methodListModel) {
    this.methodListModel = methodListModel;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  String title;

  public int getSystemIndex() {
    return SystemIndex;
  }

  public void setSystemIndex(int systemIndex) {
    SystemIndex = systemIndex;
    fragmentIndex=MERHOD_LIST;
  }

  private int SystemIndex;

  public int getFragmentIndex() {
    return fragmentIndex;
  }

  public void setFragmentIndex(int fragmentIndex) {
    this.fragmentIndex = fragmentIndex;
  }

  private int fragmentIndex=MERHOD_LIST;






  public MainModel(MainActivity mainActivity, Data data){
  //title index =1
    title="";
    SystemIndex=0;
    this.data=data;
  }

  public  MethodListModel getMethodListModel() {
    return methodListModel;
  }
}
