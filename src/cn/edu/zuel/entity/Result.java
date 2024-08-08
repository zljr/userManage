package cn.edu.zuel.entity;


import java.util.List;

public class Result {
    private Integer code;
    private Boolean flag;
    private List<Student> data;

    public Result() {
    }

    public Result(Integer code, Boolean flag, List<Student> data) {
        this.code = code;
        this.flag = flag;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public List<Student> getData() {
        return data;
    }

    public void setData(List<Student> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", flag=" + flag +
                ", data=" + data +
                '}';
    }
}
