package main.java.com.jambroo.grafi;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by jamie on 01/04/14.
 */
public class PhotoThumb {
    private int x, y, w, h;
    private byte[] thumb;

    public PhotoThumb(byte[] thumb, int x, int y, int w, int h) {
        this.thumb = thumb;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.w;
    }

    public int getHeight() {
        return this.h;
    }

    public BufferedImage getImage() throws IOException {
        return ImageIO.read(new ByteArrayInputStream(this.thumb));
    }
}
