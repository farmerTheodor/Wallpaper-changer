/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcrawler;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 *
 * @author daniel
 */
public class ImageCrawler extends WebCrawler {

    public void getUrls() {
        System.out.println("finding images");
        //gets all the div elements that include a data-url with png or jpeg
        websiteElement = currentSite.select("div[data-url~=(?i)\\.(png|jpe?g)]");
        if (websiteElement.hasText()) {
            System.out.println("found image");
            getAttributes("data-url");
        } else {
            System.out.println("no image found");
        }

    }

    public boolean isWallpaper(String imageUrl, int xSize, int ySize, int marginError) throws IOException {
        int sizeToGuessY = 0;
        int sizeToGuessX = 0;
        System.out.println("checking aspect ratio");
        //gets the image from the url
        InputStream stream = new URL(imageUrl).openStream();
        BufferedImage img = ImageIO.read(stream);
        sizeToGuessY = img.getHeight();
        sizeToGuessX = img.getWidth();
        //if the image is within the size allotted then it replaces the current wallpaper
        if (sizeToGuessX >= (xSize - marginError) && sizeToGuessY >= (ySize - 300)) {
            File outputfile = new File("/home/daniel/Pictures/currentWallpaper.jpg");
            ImageIO.write(img, "jpg", outputfile);
            System.out.println("a candidate:" + sizeToGuessX + "," + sizeToGuessY);
            stream.close();
            return true;
        }        
        stream.close();
        System.out.println("not large enough");
        return false;

    }

}
