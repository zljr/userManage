package test;

import cn.edu.zuel.entity.Student;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

public class Test03 {
    public static void main(String[] args) {
        String str="{\"code\":200,\"flag\":true,\"data\":[{\"id\":1,\"name\":\"xhf\",\"password\":\"666\"}]}";
        JSONObject jsonObject = JSONObject.parseObject(str);
//        ArrayList<Student> arrayList = (ArrayList<Student>) jsonObject.get("data");
//        for(Student stu:arrayList){
//            System.out.println(stu);
//        }
        Integer code = jsonObject.getInteger("code");
        System.out.println(code);
        JSONArray data = jsonObject.getJSONArray("data");
        for(Object js:data){
            JSONObject jsonObject1 = (JSONObject) js;
            System.out.println(jsonObject1.get("password"));
        }


    }
}
