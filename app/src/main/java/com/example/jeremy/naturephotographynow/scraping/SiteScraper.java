package com.example.jeremy.naturephotographynow.scraping;

import android.content.Context;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import com.example.jeremy.naturephotographynow.MainActivity;
import com.example.jeremy.naturephotographynow.gallery.Album;
import com.example.jeremy.naturephotographynow.gallery.Gallery;
import com.example.jeremy.naturephotographynow.gallery.Picture;

import org.jsoup.Jsoup;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Mezzo on 7/18/2015.
 * Scrapes the Nature Photography Now Website
 */
public class SiteScraper {

    private Album album;
    private static SiteScraper singleton;
    int numPics = 0;
    int loadingPicNum = 0;
    String error = " ";

    private SiteScraper(){

    }

    /*
    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }//*/

    /** So that there's only one instance */
    public static SiteScraper getInstance(){
        if(SiteScraper.singleton == null){
            SiteScraper.singleton = new SiteScraper();
        }
        return singleton;
    }

    public int getProgress() {
        if(numPics != 0) {
            return loadingPicNum / numPics;
        }
        return 0;
    }

    /** Initialize scraping:
     * gets the Nature Photography Now site map xml file,
     * sets up the list of Galleries,
     * adds their names,
     * and starts adding the picture urls to a String list of images,
     * then loads the actual picture to the image -takes a while (10-15 minutes),
     * and adds the image to the gallery.
     * */
    public int init(String sitemapURL) throws IOException, ParserConfigurationException, SAXException {
        Document doc = getSiteMap(sitemapURL);
        Log.v("SiteScraper", "Document acquired");
        List<String> galleries = new ArrayList<String>();
        List<Pair<String, String>> images = new ArrayList<Pair<String, String>>();
        Log.v("SiteScraper", "Achieving URL obtain");
        NodeList nodeList = doc.getElementsByTagName("url");
        Log.v("SiteScraper", "Beginning iteration...");
        for(int i = 0; i < nodeList.getLength(); i++){
            Node n = nodeList.item(i);
            Element en = (Element) n;
            Node loc = en.getElementsByTagName("loc").item(0);
            Element eloc = (Element) loc;
            String location =  eloc.getTextContent();
            Pattern isGallery = Pattern.compile("^.*?com/gallery/.+?/");
            Pattern isPicture = Pattern.compile("^.*?com/gallery/.+?/.+");
            Matcher mGal = isGallery.matcher(location);
            Matcher mPic = isPicture.matcher(location);
            if(mGal.find()){
                if(mPic.find()){
                    Pair<String, String> locgal = new Pair<String, String>(location, mGal.group(0));
                    images.add(locgal);
                    Log.v("SiteScraper", "Adding Picture " + location);
                    numPics++;
                } else {
                    galleries.add(location);
                    Log.v("SiteScraper", "Adding Gallery " + location);
                }
            }
        }

        Log.v("SiteScraper", "Building Album");
        this.album = new Album();
        for(String gallery: galleries){
            Gallery gGallery = new Gallery();

            Pattern nameFinder = Pattern.compile("/([^/]+)/$");
            Matcher nameMatcher = nameFinder.matcher(gallery);
            nameMatcher.find();
            String galleryName = nameMatcher.group(1);
            galleryName = toTitleCase(galleryName.replace('-', ' '));
            gGallery.setName(galleryName);

            album.addGalleryToList(gGallery);
        }
        Log.v("SiteScraper", "Adding Pictures");
        for(Pair<String, String> locgal: images){
            Picture image = new Picture();
            String imageLocationURL = locgal.first;
            String galleryLocationURL = locgal.second;

            Pattern nameFinder = Pattern.compile("/([^/]+)/$");
            Matcher nameMatcher = nameFinder.matcher(imageLocationURL);
            nameMatcher.find();
            String pictureName = nameMatcher.group(1);
            pictureName = toTitleCase(pictureName.replace('-', ' '));
            image.setName(pictureName);

            nameMatcher = nameFinder.matcher(galleryLocationURL);
            nameMatcher.find();
            String galleryName = nameMatcher.group(1);
            galleryName = toTitleCase(galleryName.replace('-', ' '));
            Gallery g = album.getGalleryByName(galleryName);

            Log.v("SiteScraper", "Getting Picture for " + pictureName);
            image.setUrl(error = getPictureUrl(imageLocationURL));
            if (error.equals("error")){
                return -1;
            }
            /*
            Toast(getApplicationContext(), "Downloading:" +
                    " "  + "%",Toast.LENGTH_LONG).show();
            image.setPageUrl(imageLocationURL);//*/
            loadingPicNum++;

            g.addPicture(image);
        }
        Log.v("SiteScraper", "Album completed");
        return 1;
    }

    private String getPictureUrl(String pageURL) throws IOException {
            try {
                org.jsoup.nodes.Document doc = Jsoup.connect(pageURL).get();
                org.jsoup.nodes.Element node = doc.select("#gallery_big_img").first();
                return node.attr("src");
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    org.jsoup.nodes.Document doc = Jsoup.connect(pageURL).get();
                    org.jsoup.nodes.Element node = doc.select("#gallery_big_img").first();
                    return node.attr("src");
                } catch (Exception e1) {
                    e1.printStackTrace();
                    try {
                        org.jsoup.nodes.Document doc = Jsoup.connect(pageURL).get();
                        org.jsoup.nodes.Element node = doc.select("#gallery_big_img").first();
                        return node.attr("src");
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        try {
                            org.jsoup.nodes.Document doc = Jsoup.connect(pageURL).get();
                            org.jsoup.nodes.Element node = doc.select("#gallery_big_img").first();
                            return node.attr("src");
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            return "error";
                        }
                    }
                }
            }
    }

    private Document getSiteMap(String sitemapURL) throws ParserConfigurationException, IOException, SAXException {
        URL url = new URL(sitemapURL);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new InputSource(url.openStream()));
        doc.getDocumentElement().normalize();
        return doc;
    }

    public Album getAlbum(){
        return album;
    }


    private static String toTitleCase(String givenString) {
        String[] arr = givenString.split(" ");
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < arr.length; i++) {
            sb.append(Character.toUpperCase(arr[i].charAt(0)))
                    .append(arr[i].substring(1)).append(" ");
        }
        return sb.toString().trim();
    }

}
