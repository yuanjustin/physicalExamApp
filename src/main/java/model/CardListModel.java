package model;

import java.util.ArrayList;
import java.util.List;

import bean.Data;

public class CardListModel
{
  public List<CardModel> getDetailModel() {
    return cardModel;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  String title;
  List<CardModel> cardModel =new ArrayList();

  public void add(CardModel d){
    cardModel.add(d);
  }

 /* CardListModel(Data data, int sys, int meth){
  //Data find information
  }*/
}
