import magick.ImageInfo;
import magick.MagickImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class PhotoFile {
    private File file;
    private BufferedImage bimg;
    private int x, y, w, h;
    private String filename;

    public PhotoFile(String filename) {
        this.filename = filename;
        System.out.println(filename);
        this.file = new File(filename);
        try {
            this.bimg = ImageIO.read(this.file);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public File getFile() {
        return this.file;
    }

    public int getWidth() {
        return (int) this.bimg.getWidth();
    }

    public int getHeight() {
        return (int) this.bimg.getHeight();
    }

    public PhotoThumb getThumb(int x, int y, int w, int h) throws IOException {
        try {
            ImageInfo ii = new ImageInfo(this.filename);
            MagickImage mi = new MagickImage(ii);

            Dimension originalDimensions = mi.getDimension();
            double width = originalDimensions.getWidth();
            double height = originalDimensions.getHeight();
            double aspectRatio = width / height;

            int newWidth = w;
            int newHeight = (int) (newWidth / aspectRatio);

            MagickImage miSmall = mi.scaleImage(newWidth, newHeight);

            int offsetY = 0;
            if (newHeight > h) {
                offsetY = (int) ((newHeight - h) / 2.0);
            }

            Rectangle miSmallArea = new Rectangle(0, offsetY, newWidth, newHeight);
            MagickImage miThumb = miSmall.cropImage(miSmallArea);
            miThumb.setFileName(filename + "_thumb.jpg");
            ImageInfo iiThumb = new ImageInfo(filename + "_thumb.jpg");
            miThumb.writeImage(iiThumb);

            return new PhotoThumb(miThumb.imageToBlob(iiThumb), x, y, w, h);
        } catch (Exception e) {
        }

        return null;
    }

    public BufferedImage getImage() {
        return this.bimg;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getW() {
        return this.w;
    }

    public int getH() {
        return this.h;
    }
}