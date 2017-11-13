package model;

import com.Justin.Yuan.ClinicalSkillApp.MainActivity;

import java.util.ArrayList;
import java.util.List;

import bean.Data;
import bean.DetailBean;
import bean.MethodsBean;
import bean.SystemsBean;

public class MethodListModel
{


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    String title;
    public List<CardListModel> getCardListModel() {
        return cardListModel;
    }

    List<CardListModel> cardListModel =new ArrayList();

    public void refresh(int SystemIndex, Data data, MainActivity mainActivity) {
        SystemsBean s =data.getSystems(SystemIndex);
        title=s.getName();
        this.getCardListModel().clear();
        //for(MethodsBean m:s.getMethods()){
        for(int mIndex=0;mIndex<=s.getMethods().size()-1;mIndex++){
            MethodsBean m=s.getMethods().get(mIndex);
                CardListModel cardListModel=new CardListModel();
                cardListModel.setTitle(m.getName());
                //for(DetailBean d:m.getDetails()){
            for(int dIndex=0;dIndex<=m.getDetails().size()-1;dIndex++){
                DetailBean d=m.getDetails().get(dIndex);
                    cardListModel.add(new CardModel(d.getDetail(),d.getTitle(),mainActivity,SystemIndex,mIndex,dIndex));
                }
                this.getCardListModel().add(cardListModel);
            }
        }

    }


//  MethodListModel(Data data, int sys){
  //Data find information
//  }

