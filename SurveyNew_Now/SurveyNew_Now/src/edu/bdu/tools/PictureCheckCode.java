package edu.bdu.tools;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PictureCheckCode extends HttpServlet{
	   public PictureCheckCode()
	   {
		   super();//初始化servlet
	   }
	   
	   public void destroy()
	   {
		   super.destroy();//销毁servlet
	   }
	   
	   public void init() throws ServletException{
		   super.init();
	   }
	   
	   public Color getRandColor(int s,int e)
	   {
		   Random random = new Random();
		   
		   if(s > 255) s = 255;
		   
		   if(e > 255) e = 255;
		   
		   int r = s + random.nextInt(e - s);
		   
		   int g = s + random.nextInt(e - s);
		   
		   int b = s + random.nextInt(e - s);
		   
		   return new Color(r,g,b);//生成颜色对象
	   }
	   
	   public void service(HttpServletRequest request,HttpServletResponse response)
	     throws ServletException,IOException{
		   
		   //禁止缓存
		   response.setHeader("Pragma", "No-cache");
		   
		   response.setHeader("Cache-Control","No-cache");
		   
		   response.setDateHeader("Expires", 0);
		   
		   //指定生成的相应是图片
		   response.setContentType("image/jpeg");
		   
		   int width = 100;//宽度
		   
		   int height = 40;//高度
		   
		   BufferedImage image = new BufferedImage(width,height,
				   BufferedImage.TYPE_INT_BGR);//创建BufferedImage类的对象
		   
		   Graphics g = image.getGraphics();//创建Graphics类的对象
		   
		   Graphics2D g2d = (Graphics2D)g;//通过Graphics类的对象创建一个Graphics2D类对象
		   
		   Random random = new Random();//随机数类
		   
		   Font mFont = new Font("华文宋体",Font.BOLD,18);//通过Font构造字体
		   
		   g.setColor(getRandColor(200,250));//改变图形的当前颜色为随机生成的颜色
		   
		   g.fillRect(0,0,width,height);//画出一个方框
		   
		   //画一条折线
		   BasicStroke bs = new BasicStroke(2f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL);//创建一个供画笔选择线条粗细的对象
		   
		   g2d.setStroke(bs);//改变线条的粗细
		   
		   g.setColor(Color.DARK_GRAY);//设置当前颜色为预定义颜色中的深灰色
		   
		   int[] xPoints = new int[3];
		   
		   int[] yPoints = new int[3];
		   
		   for(int j = 0; j < 3; j++){
			   
			   xPoints[j] = random.nextInt(width - 1);
			   
			   yPoints[j] = random.nextInt(height - 1);
		   }
		   
		   g.drawPolyline(xPoints, yPoints, 3);
		   //生成并输出随机的验证文字
		   g.setFont(mFont);
		   
		   String sRand = "";
		   
		   int itmp = 0;
		   
		   for(int i = 0; i < 4; i++){
			   if(random.nextInt(2) == 1){
				  itmp = random.nextInt(26) + 65;
			   }
			   else
			   {
				   itmp = random.nextInt(10)+48;
			   }
			   
			   char ctmp = (char)itmp;
			   
			   sRand += String.valueOf(ctmp);
			   
			   Color color = new Color(20 + random.nextInt(110),20 + random.nextInt(110),20 + random.nextInt(110));
			   
			   g.setColor(color);
			   /****随机缩放文字并将文字旋转指定角度*/
			   //将文字旋转指定角度
			   Graphics2D g2d_word = (Graphics2D)g;
			   
			   AffineTransform trans = new AffineTransform();
			   
			   trans.rotate(random.nextInt(45)*3.14/180,15*i+10,7);
			   
			   //缩放文字
			   float scaleSize = random.nextFloat() + 0.8f;
			   
			   if(scaleSize > 1.1f)scaleSize=1f;
			   
			   trans.scale(scaleSize,scaleSize);
			   
			   g2d_word.setTransform(trans);
			   
			   /**********************/
			   g.drawString(String.valueOf(ctmp), 12*i+40, 8);
		   }
		   
		   //将生成的验证码保存到Session中
		   
		   HttpSession session = request.getSession(true);//获得访问的session对象
		   
		   session.setAttribute("randCheckCode", sRand);
		   
		   g.dispose();
		   
		   ImageIO.write(image, "JPEG", response.getOutputStream());//将图片输出
		   
	   }
}

