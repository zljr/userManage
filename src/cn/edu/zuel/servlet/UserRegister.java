package cn.edu.zuel.servlet;

import cn.edu.zuel.entity.User;
import cn.edu.zuel.service.UserService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/register")
public class UserRegister extends HttpServlet {
    private UserService userService=new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.getRequestDispatcher("register.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if(StringUtils.isEmpty(username)){
            req.setAttribute("errorMsg","用户名为空");
            req.getRequestDispatcher("register.jsp").forward(req,resp);
            return;
        }
        if(StringUtils.isEmpty(password)){
            req.setAttribute("errorMsg","密码为空");
            req.getRequestDispatcher("register.jsp").forward(req,resp);
            return;
        }
        if(userService.findByUsername(username)!=null){
            req.setAttribute("errorMsg","该用户"+username+"已经被注册！");
            req.getRequestDispatcher("register.jsp").forward(req,resp);
            return;
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        int register = userService.register(user);
        if(register<1){
            req.setAttribute("errorMsg","注册失败");
            req.getRequestDispatcher("register.jsp").forward(req,resp);
            return;
        }
        String userCode=req.getParameter("checkCode");
        HttpSession session=req.getSession();
        String checkCodeO =(String)session.getAttribute("checkCodeO");
        if (!checkCodeO.equalsIgnoreCase(userCode)) {
            req.setAttribute("errorMsg","验证码错误");
            req.getRequestDispatcher("register.jsp").forward(req,resp);
            return;
        }
        resp.sendRedirect("login.jsp");
    }
}
