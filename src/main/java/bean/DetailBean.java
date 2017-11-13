package bean;

import com.Justin.Yuan.ClinicalSkillApp.MainActivity;

import java.io.IOException;
import java.io.InputStream;

public class DetailBean
{
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    String title ;
    String detail ;

    public String getDetailText() {
        return detailText;
    }

    public void setDetailText(String detailText) {
        this.detailText = detailText;
    }

    String detailText ;
    DetailBean(String detail, String title, MainActivity mainActivity){

        this.detail=detail;
        this.title=title;
        InputStream in = null;
        try {
            in = mainActivity.getResources().getAssets().open(detail+".txt");
            int length = in.available();
            byte [] buffer = new byte[length];
            in.read(buffer);
             detailText = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

//    public String getBrief() {
//        return detail;
//    }
}