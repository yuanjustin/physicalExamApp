package model;

import com.Justin.Yuan.ClinicalSkillApp.MainActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bean.Data;
import bean.DetailBean;
import bean.MethodsBean;
import bean.SystemsBean;

public class SearchModel
{
    public List<CardModel> getCardList() {
        return cardList;
    }

    public void setCardList(List<CardModel> cardList) {
        this.cardList = cardList;
    }

    private List<CardModel> cardList = new ArrayList<CardModel>();
 void refrsh(List<DetailModel> cardlist){
  
 }
  void setSystemIndex(int x){}
   List<DetailModel> getList(){
    return null;
   }

	boolean isInside(String s, String searchWord){
        return true;
    };



    public void refesh(String query,Data data,MainActivity mainActivity) {
        this.getCardList().clear();
        String[] querySplit=query.trim().split(" ");
        //for(SystemsBean s:data.getSystems()){
        for(int sIndex=0;sIndex<=data.getSystems().size()-1;sIndex++){
            SystemsBean s=data.getSystems().get(sIndex);
            for(int mIndex=0;mIndex<=s.getMethods().size()-1;mIndex++){
                MethodsBean m=s.getMethods().get(mIndex);
                for(int dIndex=0;dIndex<=m.getDetails().size()-1;dIndex++){
                    DetailBean d=m.getDetails().get(dIndex);
                    String allDetail=s.getName()+" " +m.getName()+d.getTitle()+d.getDetailText();
                    if(allFit(allDetail,querySplit)==true){
                        this.getCardList().add(new CardModel(d.getDetail(),d.getTitle(),mainActivity,sIndex,mIndex,dIndex));
                    }
                }
            }
        }
//  搜索所有卡片更新model
        //if query="" {返回上一个视图}

    }

    private boolean allFit(String allDetail,String[] query  ) {
        String q = query[query.length - 1];
        query = Arrays.copyOf(query, query.length - 1);
        if (query.length == 0 ) {//因为上面已经删除了
            return allDetail.contains(q);
        } else {
            if (allDetail.contains(q)) {
                return allFit(allDetail, query);
            } else {
                return false;
            }
        }

    }
}
