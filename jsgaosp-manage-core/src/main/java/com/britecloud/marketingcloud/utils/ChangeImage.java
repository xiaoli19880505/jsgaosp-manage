/** 
 * 项目名称:91营销云
 * 文件名：ChangeImage.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.utils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ChangeImage {
	 
    public static BufferedImage makeRoundedCorner(BufferedImage image,
            int cornerRadius) {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage output = new BufferedImage(w, h,
                BufferedImage.TYPE_INT_ARGB);
 
        Graphics2D g2 = output.createGraphics();
 
        // This is what we want, but it only does hard-clipping, i.e. aliasing
        // g2.setClip(new RoundRectangle2D ...)
 
        // so instead fake soft-clipping by first drawing the desired clip shape
        // in fully opaque white with antialiasing enabled...
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius,
                cornerRadius));
 
        // ... then compositing the image on top,
        // using the white shape from above as alpha source
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);
 
        g2.dispose();
 
        return output;
    }
 
    public static BufferedImage createResizedCopy(Image originalImage, int scaledWidth,
            int scaledHeight, boolean preserveAlpha) {
        int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB
                : BufferedImage.TYPE_INT_ARGB;
        BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight,
                imageType);
        Graphics2D g = scaledBI.createGraphics();
        if (preserveAlpha) {
            g.setComposite(AlphaComposite.Src);
        }
        g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
        g.dispose();
        return scaledBI;
    }
//    public static void main(String[] args) throws IOException {
//        BufferedImage icon = ImageIO.read(new File("F:/Desktop/getheadimg1.jpg"));
//        BufferedImage rounded = makeRoundedCorner(icon, 30);
//        ImageIO.write(rounded, "png", new File("F:/Desktop/getheadimg2.jpg"));
//         
//        BufferedImage pic = ImageIO.read(new File("/home/june/桌面/zhang.jpg"));
//        BufferedImage resized = createResizedCopy(pic, 250, 250*768/1024, true);
//        ImageIO.write(resized, "jpg", new File("/home/june/桌面/zhang_small.jpg"));
 //   }
 
}
