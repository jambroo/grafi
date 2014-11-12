import magick.*;

import java.awt.Dimension;
import java.awt.Rectangle;

public class ResizeImage {
    public static void main(String[] args) throws Exception {
        int thumbWidth = 200;
        int thumbHeight = 150;

        String filename;
        if (args.length >= 1) {
            filename = args[0];

            try {
                if (args.length > 1) {
                    thumbWidth = Integer.parseInt(args[1]);
                }

                if (args.length > 2) {
                    thumbHeight = Integer.parseInt(args[2]);
                }
            } catch (Exception e) {
                throw new Exception("Invalid parameters. Run program with as: ResizeImage <image_filename> [<thumb_width>] [<thumb_height].");
            }
        } else {
            throw new Exception("No image filename provided.");
        }

        ImageInfo ii = new ImageInfo(filename);
        MagickImage mi = new MagickImage(ii);

        Dimension originalDimensions = mi.getDimension();
        double width = originalDimensions.getWidth();
        double height = originalDimensions.getHeight();
        double aspectRatio = width / height;

        int newWidth = thumbWidth;
        int newHeight = (int) (newWidth / aspectRatio);

        MagickImage miSmall = mi.scaleImage(newWidth, newHeight);
        miSmall.setFileName(filename + "_small.jpg");
        ImageInfo iiSmall = new ImageInfo(filename + "_small.jpg");
        miSmall.writeImage(iiSmall);
        //byte[] img = miSmall.imageToBlob(miSmall);

        int offsetY = 0;
        if (newHeight > thumbHeight) {
            offsetY = (int) ((newHeight - thumbHeight) / 2.0);
        }

        Rectangle miSmallArea = new Rectangle(0, offsetY, thumbWidth, thumbHeight);
        MagickImage miThumb = miSmall.cropImage(miSmallArea);
        miThumb.setFileName(filename + "_thumb.jpg");
        ImageInfo iiThumb = new ImageInfo(filename + "_thumb.jpg");
        miThumb.writeImage(iiThumb);
    }
}
