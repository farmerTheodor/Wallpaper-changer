/*
 this program runs by replaceing your existing wallpaper image with an image from a subreddit 
 I havent tried it on any other website but im sure it could easily be modified 
 might improve it by checking the aspect ratio so that there isnt any stretching
 */
package webcrawler;

import java.io.*;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author daniel deen
 */
public class WebCrawler {

    public static void main(String[] args) {
        ImageCrawler imageFinder = new ImageCrawler();
        try {
            //gets the html code for the image finder
            imageFinder.connectToSite("https://www.reddit.com/r/ImaginaryLandscapes/top/?sort=top&t=day");

        } catch (IOException ed) {
            System.out.println(ed.getMessage());
        }
        try {
            imageFinder.getUrls();
            //iterates through the image list until it finds a candidate for your image size
            for (int i = 0; i < imageFinder.attributeList.size(); i++) {
                try {
                    if (imageFinder.isWallpaper(imageFinder.attributeList.get(i), 1600, 900, 500)) {
                        break;
                    }
                } catch (IOException t) {
                    System.out.println(t.getMessage());
                    System.out.println(t.toString());
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    public Document currentSite;
    public Elements websiteElement;
    public ArrayList<String> attributeList = new ArrayList<>();
    public String imageURL;

    public void connectToSite(String websiteURL) throws IOException {
        System.out.println("finding " + websiteURL + "......");
        currentSite = Jsoup.connect(websiteURL).userAgent("Chrome").get();
        System.out.println("found " + websiteURL);
    }

    public void getAttributes(String attribute) {
        System.out.println("extracting urls");
        for (int i = 0; i < websiteElement.size(); i++) {
            attributeList.add(websiteElement.get(i).attr(attribute));
        }
        System.out.println("url extracting complete");

    }

}
