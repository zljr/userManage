package test;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import cn.edu.zuel.utils.VerifyCode;

import javax.imageio.ImageIO;

public  class Test01 {
    public static void main(String[] args) throws IOException {
        VerifyCode verifyCode=new VerifyCode();
        BufferedImage image =verifyCode.createImage();
        ImageIO.write(image,"jpg",new File("E:/JavaWeb/web/src/pic/image.jpg"));
        System.out.println(verifyCode.getText());
    }


}
