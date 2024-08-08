package cn.edu.zuel.servlet;

import cn.edu.zuel.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/existUserName")
//实现输入姓名后，判断该用户名是否存在
public class ExistUserName extends HttpServlet {
    private UserService userService=new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        String username=req.getParameter("username");
        PrintWriter writer = resp.getWriter();
        if(userService.findByUsername(username)!=null){
            writer.print("该用户"+username+"已经存在！");

        }
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req,resp);
    }
}
