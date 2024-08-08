package cn.edu.zuel.servlet;

import cn.edu.zuel.entity.User;
import cn.edu.zuel.service.UserService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class UserLogin extends HttpServlet {
    private UserService userService=new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.getRequestDispatcher("login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        if(StringUtils.isEmpty(username)){
            req.setAttribute("errorMsg","用户名为空");
            req.getRequestDispatcher("error.jsp").forward(req,resp);
            return;
        }
        if(StringUtils.isEmpty(password)){
            req.setAttribute("errorMsg","密码为空");
            req.getRequestDispatcher("error.jsp").forward(req,resp);
            return;
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        User login = userService.login(user);
        if(login==null){
            req.setAttribute("errorMsg","账号或密码错误");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
            return;
        }
        String rememberPassword = req.getParameter("rememberPassword");
        if("on".equals(rememberPassword)){
            Cookie username1 = new Cookie("username",username);
            username1.setMaxAge(60*60);
            Cookie password1 = new Cookie("password",password);
            password1.setMaxAge(60*60);
            resp.addCookie(username1);
            resp.addCookie(password1);
        }
        HttpSession session = req.getSession();
        session.setAttribute("user",user);
//        req.getRequestDispatcher("success.jsp").forward(req,resp);
        resp.sendRedirect("index.jsp");
    }
}
