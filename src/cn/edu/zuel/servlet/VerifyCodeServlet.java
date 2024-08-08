package cn.edu.zuel.servlet;

import cn.edu.zuel.utils.VerifyCode;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.IOException;

@WebServlet("/verifyCodeServlet")
public class VerifyCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream outputStream = resp.getOutputStream();
        //通过resp的outputStream获取字节输出流
        VerifyCode code = new VerifyCode();
        BufferedImage image=code.createImage();
        //创建验证码图像
        ImageIO.write(image,"jpg",outputStream);
        //将图像保存到字节输出流中
        String checkCode=code.getText();
        //获取验证码文本
        HttpSession session=req.getSession();
        session.setAttribute("checkCodeO",checkCode);

    }
}
