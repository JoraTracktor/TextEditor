package converter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class IconControl {
    public static BufferedImage resize(Object img, int percent) {
        BufferedImage buff = (BufferedImage) img;
        return resize(buff, buff.getWidth() * percent / 100, buff.getWidth() * percent / 100);
    }

    private static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return dimg;
    }
}
