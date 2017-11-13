package model;

import bean.Data;

public class DetailModel
{
  void getDetail(Data data, int sys, int meth, int card){}
  void getBrift(Data data, int sys,int meth, int card){}

  public String getHtml() {
    return html;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  String title;
  String html;//文件名称
    public void setHtml(int system, int methodIndex, int cardIndex, Data data) {
      html=data.getSystems(system).getMethods().get(methodIndex).getDetails().get(cardIndex).getDetail();
      title=data.getSystems(system).getMethods().get(methodIndex).getDetails().get(cardIndex).getTitle();
    }

    // https://stackoverflow.com/questions/2116162/how-to-display-html-in-textview
}
