package com.vidyo.common;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.awt.*;
import java.util.*;
import java.awt.font.TextAttribute;
import java.awt.geom.AffineTransform;

public class MyCaptcha extends HttpServlet {

    private int height = 0;
    private int width = 0;
    public static final String CAPTCHA_KEY = "captcha_key_name";

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        height = 30;
        width = 120;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException, ServletException {
        //Expire response
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Max-Age", 0);

        
        Random r = new Random();
        String token = Long.toString(Math.abs(r.nextLong()), 36);
        String ch = token.substring(0, 5);

        BufferedImage image1 = skewImage(ch);
        HttpSession session = req.getSession(true);
        session.setAttribute(CAPTCHA_KEY, ch);

        OutputStream outputStream = response.getOutputStream();
        ImageIO.write(image1, "jpeg", outputStream);
        outputStream.close();
    }
    
    private static final int MAX_LETTER_COUNT = 5;
    private static final int LETTER_WIDTH = 43;
    private static final int IMAGE_HEIGHT =30;
    private static final double SKEW =20;
    private static final int DRAW_LINES = 20;
    private static final int DRAW_BOXES =1;
    private static final int MAX_X = LETTER_WIDTH * MAX_LETTER_COUNT;
    private static final int MAX_Y = IMAGE_HEIGHT;

    private static final Color[] RANDOM_BG_COLORS = { Color.WHITE };

    //private static final Color[] RANDOM_FG_COLORS = { Color.BLUE };
    
    public BufferedImage skewImage(String securityChars)
    {
      BufferedImage outImage = new BufferedImage(MAX_X, MAX_Y, 1);

      Graphics2D g2d = outImage.createGraphics();
     
      g2d.fillRect(0, 0, MAX_X, MAX_Y);
      for (int i = 0; i < DRAW_BOXES; i++) {
        paindBoxes(g2d);
      }

      Font font = new Font("dialog", Font.BOLD , 27);
      g2d.setFont(font);

      g2d.drawRect(0, 0, MAX_X, MAX_Y);
      g2d.setColor(new Color(223,225,225));
      g2d.fillRect(0,0, MAX_X , MAX_Y);      
      g2d.drawRect(1, 1, MAX_X - 2, MAX_Y - 2);
      g2d.setColor(Color.GRAY);
      g2d.fillRect(1,1, MAX_X-2 , MAX_Y - 2);      
      //g2d.setBackground(Color.GRAY);
      
      AffineTransform affineTransform = new AffineTransform();
      for (int i = 0; i < MAX_LETTER_COUNT; i++) {
        double angle = 0.0D;
        if (Math.random() * 2.0D > 1.0D)
          angle = Math.random() * SKEW;
        else {
          angle = Math.random() * -SKEW;
        }
        
        int colorId = (int)(Math.random() * RANDOM_BG_COLORS.length);
        g2d.setColor(RANDOM_BG_COLORS[colorId]);

        affineTransform.rotate(angle, LETTER_WIDTH * i + LETTER_WIDTH / 2, MAX_Y / 2*i,MAX_Y / 2*i);

        g2d.drawString(securityChars.substring(i, i + 1), i * LETTER_WIDTH + 3, 20 + (int)(Math.random() * 6.0D));

        affineTransform.rotate(-angle, LETTER_WIDTH * i + LETTER_WIDTH / 2, MAX_Y / 2*i,MAX_Y / 2*i);
      }

      g2d.setXORMode(RANDOM_BG_COLORS[(int)(Math.random() * RANDOM_BG_COLORS.length)]);
      g2d.setStroke(new BasicStroke(20.0F));
      g2d.drawLine(0, MAX_Y / 2, MAX_X, MAX_Y / 2);
      g2d.setXORMode(RANDOM_BG_COLORS[(int)(Math.random() * RANDOM_BG_COLORS.length)]);
      //g2d.drawLine(0, MAX_Y / 2 - 10, MAX_X, MAX_Y / 2 - 10);
      g2d.setXORMode(RANDOM_BG_COLORS[(int)(Math.random() * RANDOM_BG_COLORS.length)]);
      //g2d.drawLine(0, MAX_Y / 2 + 10, MAX_X, MAX_Y / 2 + 10);

      return outImage;
    }
    
    private void paindBoxes(Graphics2D g2d) {
        int colorId = (int)(Math.random() * RANDOM_BG_COLORS.length);
        g2d.setColor(RANDOM_BG_COLORS[colorId]);
        g2d.fillRect(getRandomX(), getRandomY(), getRandomX(), getRandomY());
      }
    
    private int getRandomX()
    {
      return (int)(Math.random() * MAX_X);
    }

    private int getRandomY() {
      return (int)(Math.random() * MAX_Y);
    }
    
}