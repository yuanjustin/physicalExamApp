package bean;

import java.util.ArrayList;
import java.util.List;

public class MethodsBean
{
    List<DetailBean> details = new ArrayList<>();

    public List<DetailBean> getDetails() {
        return details;
    }

    public String getName() {
        return name;

    }

    public void setName(String name) {
        this.name = name;
    }

    String name ;
    void add(DetailBean d){
        details.add(d);
    }

}