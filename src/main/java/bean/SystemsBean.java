package bean;

import java.util.ArrayList;
import java.util.List;

public class SystemsBean
{
    public List<MethodsBean> getMethods() {
        return methods;
    }

    List<MethodsBean> methods = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;
void add(MethodsBean m){
    methods.add(m);
}

}
