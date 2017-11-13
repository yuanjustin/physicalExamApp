package bean;

import com.Justin.Yuan.ClinicalSkillApp.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Data
{
    public SystemsBean getSystems(int i) {
        return systems.get(i);
    }


    public List<SystemsBean> getSystems() {
        return systems;
    }

    List<SystemsBean> systems = new ArrayList<>();
    public Data(MainActivity mainActivity){
        // 文件夹读取 卡片标题与卡片文件一致
        String[] files;
        try {
            // 获得Assets一共有几多文件
            files = mainActivity.getResources().getAssets().list("");
            //一级mulu
            for(String system:files){
                if (!system.contains("s") && !system.contains("e") ) {
                    systems.add(new SystemsBean());
                    systems.get(systems.size() - 1).setName(system);
                    //二级目录
                    //  for(int s=0;s<=systems.size()-1;s++){
                    String[] MFiles = mainActivity.getResources().getAssets().list(system);
                    for (String methods : MFiles) {
                        systems.get(systems.size() - 1).add(new MethodsBean());
                        systems.get(systems.size() - 1).getMethods().get(systems.get(systems.size() - 1).getMethods().size() - 1).setName(methods);
                        //for(int m=0;m<=systems.get(s).getMethods().size()-1;m++) {
                        String[] DFiles = mainActivity.getResources().getAssets().list(system + "/" + methods);
                        //三级文件
                        for (String card : DFiles) {
                            if (card.contains(".htm")) {
                                card = card.substring(0, card.indexOf("."));
                                systems.get(systems.size() - 1).getMethods().get(systems.get(systems.size() - 1).getMethods().size() - 1).add(new DetailBean(system + "/" + methods + "/" + card, card, mainActivity));
                            }
                        }
                        //}

                    }
                }
               // }
            }

        } catch (IOException e1) {
            return;
        }

/*
      //test
        systems.add(new SystemsBean());
        systems.get(0).setName("系统1");
        systems.get(0).add(new MethodsBean());
        systems.get(0).getMethods().get(0).setName("方法1");
        systems.get(0).getMethods().get(0).add(new DetailBean("卡片1","11111"));
        systems.get(0).getMethods().get(0).add(new DetailBean("卡片2","11111"));
        systems.get(0).add(new MethodsBean());
        systems.get(0).getMethods().get(1).setName("方法2");
        systems.get(0).getMethods().get(1).add(new DetailBean("卡片1","1"));
        systems.get(0).getMethods().get(1).add(new DetailBean("卡片2","1"));


        systems.add(new SystemsBean());
        systems.get(1).setName("系统2");
        systems.get(1).add(new MethodsBean());
        systems.get(1).getMethods().get(0).setName("方法1");
        systems.get(1).getMethods().get(0).add(new DetailBean("卡片1","1"));
        systems.get(1).getMethods().get(0).add(new DetailBean("卡片2","1"));
        systems.get(1).add(new MethodsBean());
        systems.get(1).getMethods().get(1).setName("方法2");
        systems.get(1).getMethods().get(1).add(new DetailBean("卡片1","1"));
        systems.get(1).getMethods().get(1).add(new DetailBean("卡片2","1"));*/

    }
  String getjson(){
  //github
      return "[{title:'系统1',methodLists:[{title:'方法1',detailList:[{title:'卡片1',file:'1-1'},{title:'卡片2',file:'1-2'}]},{title:'方法2',detailList:[{title:'卡片1',file:'1-1'},{title:'卡片2',file:'1-2'}]}]},{title:'系统2',methodLists:[{title:'方法1',detailList:[{title:'卡片1',file:'1-1'},{title:'卡片2',file:'1-2'}]},{title:'方法2',detailList:[{title:'卡片1',file:'1-1'},{title:'卡片2',file:'1-2'}]}]}  ]";
  }
  void json2obj(){
      String json=getjson();
      try {
          JSONObject j=new JSONObject(json);
          JSONArray systemList= null;
          if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
              systemList = new JSONArray(j);
          }

      } catch (JSONException e) {
          e.printStackTrace();
      }

  }
  
  
  
}
