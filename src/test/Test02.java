package test;

import cn.edu.zuel.entity.Student;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

public class Test02 {
    public static void main(String[] args) {
        Student stu = new Student(1,"xhf","666");
        String s = JSONObject.toJSONString(stu);
//        把Java对象变成json
        System.out.println(s);
//        嵌套json........1
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",200);
        jsonObject.put("flag",true);
        ArrayList<Student> studentArrayList = new ArrayList<>();
        studentArrayList.add(stu);
        jsonObject.put("data",studentArrayList);
        System.out.println(jsonObject);
//        嵌套json........2
        ArrayList<JSONObject> jsonObjectArrayList=new ArrayList<>();
        JSONObject jsonObject1=new JSONObject();
        jsonObject1.put("name","yumazi");
        jsonObject1.put("code",404);
        jsonObjectArrayList.add(jsonObject1);
        jsonObjectArrayList.add(jsonObject);
        System.out.println("..................");
        for(JSONObject js:jsonObjectArrayList){
            System.out.println(js);
        }
//        System.out.println(jsonObjectArrayList);
    }
}
