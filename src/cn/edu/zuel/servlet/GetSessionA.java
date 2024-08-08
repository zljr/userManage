package cn.edu.zuel.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/getSessionA")
public class GetSessionA extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        System.out.println(session.getId());
        session.setAttribute("xhf","666");
        Cookie cookie = new Cookie("JSESSIONID", session.getId());
        cookie.setMaxAge(60*60);//有效期一小时
        resp.addCookie(cookie);
        PrintWriter writer = resp.getWriter();
        writer.print("ok");
        writer.close();
    }
}
