package org.greenfred.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ScaleFilter {
    private static final Logger logger = LoggerFactory.getLogger(ScaleFilter.class);

    public static Boolean createThumbnail(File file, int thumbnailWidth, int thumbnailHeight, File targetFile) {
        try {
            BufferedImage src = ImageIO.read(file);
            int sorceW = src.getWidth();
            int sorceH = src.getHeight();
            if (sorceW <= thumbnailWidth) {
                return false;
            }
            int height;
            if (sorceW > thumbnailWidth) {
                height = thumbnailWidth * sorceH / sorceW;
            } else {
                thumbnailWidth = sorceW;
                height = sorceH;
            }
            BufferedImage dst = new BufferedImage(thumbnailWidth, height, BufferedImage.TYPE_INT_RGB);
            java.awt.Image scalerImage = src.getScaledInstance(thumbnailWidth, height, java.awt.Image.SCALE_SMOOTH);
            java.awt.Graphics2D g = dst.createGraphics();
            g.drawImage(scalerImage, 0, 0, thumbnailWidth, height, null);
            g.dispose();
            int resultH = dst.getHeight();
            if (resultH > thumbnailHeight) {
                resultH = thumbnailHeight;
                dst = dst.getSubimage(0, 0, thumbnailWidth, resultH);
            }
            ImageIO.write(dst, "JPEG", targetFile);
            return true;
        } catch (Exception e) {
            logger.error("生成缩略图失败");
        }
        return false;
    }

    public static void main(String[] args) {
        createThumbnail(new File("C:\\Users\\Administrator\\Pictures\\20180316193912_VJWJG11.jpeg"), 400, 200,
                new File("C:\\Users\\Administrator\\Pictures\\20180316193912_VJWJG12.jpeg"));
    }
}
