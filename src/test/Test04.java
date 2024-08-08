package test;

import cn.edu.zuel.entity.Result;
import cn.edu.zuel.entity.Student;
import com.alibaba.fastjson.JSONObject;

public class Test04 {
//    法2
    public static void main(String[] args) {
        String str="{\"code\":200,\"flag\":true,\"data\":[{\"id\":1,\"name\":\"xhf\",\"password\":\"666\"}]}";
//        JSONObject jsonObject = JSONObject.parseObject(str);
        Result result = JSONObject.parseObject(str, Result.class);
        System.out.println(result);
    }
}
