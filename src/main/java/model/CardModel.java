package model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.Justin.Yuan.ClinicalSkillApp.MainActivity;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CardModel{
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    String content;//略缩文字
    String title;
    Bitmap pic;

    public int getSystemIndex() {
        return systemIndex;
    }

    public void setSystemIndex(int systemIndex) {
        this.systemIndex = systemIndex;
    }

    public int getMethodIndex() {
        return methodIndex;
    }

    public void setMethodIndex(int methodIndex) {
        this.methodIndex = methodIndex;
    }

    public int getCardIndex() {
        return cardIndex;
    }

    public void setCardIndex(int cardIndex) {
        this.cardIndex = cardIndex;
    }

    int systemIndex;
    int methodIndex;
    int cardIndex;
    public CardModel(String htmlName, String title, MainActivity mainActivity,int s,int m,int c) {
        this.title=title;
        this.systemIndex=s;
        this.methodIndex=m;
        this.cardIndex=c;
        //pic=Drawable.createFromPath(android.R.drawable.ic_delete);
         // 网页获取图片
        InputStream fs = null;
        try {
            String[] files = mainActivity.getResources().getAssets().list(htmlName+".files");
            if(files.length!=0){
                String file="";
                for(String f:files){
                    if(f.contains(".jpg")&& file==""){file=f;}
                }
                fs = mainActivity.getResources().getAssets().open(htmlName+".files/"+file);
                BufferedInputStream bs = new BufferedInputStream(fs);
                pic = BitmapFactory.decodeStream(bs);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // 参数2个
        //  content通过网页产生
        try{

            InputStream in = mainActivity.getResources().getAssets().open(htmlName+".txt");
            int length = in.available();
            byte [] buffer = new byte[length];
            in.read(buffer);
            String str = new String(buffer, "gb2312");
//            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
//            Matcher m = p.matcher(str);
//            str=m.replaceAll("");
//            Pattern p2 = Pattern.compile("<[^>]*>");
//            Matcher m2 = p2.matcher(str);
//            str=m2.replaceAll("");
            content=str.substring(0,100);
        }catch(Exception e){

            e.printStackTrace();

        }
    }

    public String getTitle() {
        return title;
    }

    public Bitmap getPic() {
        return pic;
    }
}
