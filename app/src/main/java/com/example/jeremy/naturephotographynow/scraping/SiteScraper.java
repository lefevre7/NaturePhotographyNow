package com.example.jeremy.naturephotographynow.scraping;

import android.util.Log;
import android.util.Pair;

import com.example.jeremy.naturephotographynow.gallery.Album;
import com.example.jeremy.naturephotographynow.gallery.Gallery;
import com.example.jeremy.naturephotographynow.gallery.Picture;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
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
 */
public class SiteScraper {

    private Album album;
    private static SiteScraper singleton;

    private SiteScraper(){

    }

    public static SiteScraper getInstance(){
        if(SiteScraper.singleton == null){
            SiteScraper.singleton = new SiteScraper();
        }
        return singleton;
    }

    public void init(String sitemapURL) throws IOException, ParserConfigurationException, SAXException {
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

            image.setUrl(locgal.first);

            g.addPicture(image);
        }
        Log.v("SiteScraper", "Album completed");
    }

    private String getPictureUrl(String pageURL){

        return null;
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
