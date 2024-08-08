package cn.edu.zuel.servlet;

import cn.edu.zuel.entity.User;
import cn.edu.zuel.service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet("/userAjaxRegister")
public class UserAjaxRegister extends HttpServlet {
    private UserService userService=new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
//        req.getRequestDispatcher("ajaxRegister.jsp").forward(req,resp);
        doPost(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if(StringUtils.isEmpty(username)){
//            req.setAttribute("errorMsg","用户名为空");
//            req.getRequestDispatcher("register.jsp").forward(req,resp);
//            return;
            setResultError("用户名不能为空",writer);
            return;
        }
        if(StringUtils.isEmpty(password)){
//            req.setAttribute("errorMsg","密码为空");
//            req.getRequestDispatcher("register.jsp").forward(req,resp);
            setResultError("密码不能为空",writer);
            return;
        }
        if(userService.findByUsername(username)!=null){
//            req.setAttribute("errorMsg","该用户"+username+"已经被注册！");
//            req.getRequestDispatcher("register.jsp").forward(req,resp);
            setResultError("该用户"+username+"已被注册",writer);

            return;
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        int register = userService.register(user);
        if(register<1){
//            req.setAttribute("errorMsg","注册失败");
//            req.getRequestDispatcher("register.jsp").forward(req,resp);
            setResultError("注册失败",writer);

            return;
        }
        String userCode=req.getParameter("checkCode");
        HttpSession session=req.getSession();
        String checkCodeO =(String)session.getAttribute("checkCodeO");
        if(StringUtils.isEmpty(checkCodeO)){
            setResultError("请加载图像验证码",writer);
//            这一步相当重要，不然做接口测试注册时会出问题
            return;
        }
        if (!checkCodeO.equalsIgnoreCase(userCode)) {
//            req.setAttribute("errorMsg","验证码错误");
//            req.getRequestDispatcher("register.jsp").forward(req,resp);
            setResultError("验证码错误",writer);

            return;
        }
        setResultOk("注册成功",writer);
//        resp.sendRedirect("login.jsp");
    }
    public void setResult(Integer code,String msg,PrintWriter writer){
        HashMap<String, Object> result = new HashMap<>();
        result.put("code",code);
        result.put("msg",msg);
        String jsonString = JSONObject.toJSONString(result);
        writer.print(jsonString);
        writer.close();
    }
    public void setResultError(String msg,PrintWriter writer){
        setResult(500,msg,writer);
    }
    public void setResultOk(String msg,PrintWriter writer){
        setResult(200,msg,writer);
    }

}
