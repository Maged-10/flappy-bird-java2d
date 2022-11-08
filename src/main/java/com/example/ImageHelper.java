package com.example;

import java.io.IOException;
import java.awt.Image;
import javax.imageio.ImageIO;

/** Helper Class to load Images */
public class ImageHelper {
    private static Image PILLAR, BACKGROUND, BIRD;

    // Private constructor
    public ImageHelper() {
    }

    public static Image getBird() {
        try {
            BIRD = ImageIO.read(ImageHelper.class.getClassLoader()
                .getResource("assets/redbird-midflap.png"));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return BIRD;
    }

    public static Image getPillar() {
        try {
        PILLAR = ImageIO.read(ImageHelper.class.getClassLoader()
            .getResource("assets/pipe-red.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
        return PILLAR;
    }

    public static Image getBackground() {
        try {
            BACKGROUND = ImageIO.read(ImageHelper.class.getClassLoader()
                .getResource("assets/background-night.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
        return BACKGROUND;
    }
}
