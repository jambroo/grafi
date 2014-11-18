import java.awt.image.BufferedImage;

class GrafiThumb {
    public BufferedImage image;
    public int x, y, w, h;

    public GrafiThumb(BufferedImage image, int x, int y, int w, int h) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
}