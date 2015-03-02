package com.obturecode.vallahackathon.data.parser;

import com.obturecode.vallahackathon.domain.entity.Photo;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by husky on 02/03/15.
 */
public class InterestingnessPhotosParser {
    public static ArrayList<Photo> parse(String toParse) throws XmlPullParserException, IOException {

        ArrayList<Photo> photos = new ArrayList<Photo>();

        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xpp = factory.newPullParser();
        xpp.setInput(new StringReader(toParse));
        int eventType = xpp.getEventType();
        Photo photo = null;
        while (eventType != XmlPullParser.END_DOCUMENT){
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    if(xpp.getName().equalsIgnoreCase("photo")){
                        photo = new Photo();
                        photo.setTitle(xpp.getAttributeValue(null,"title"));
                        photo.setId(xpp.getAttributeValue(null,"id"));
                        photo.setSecret(xpp.getAttributeValue(null,"secret"));
                        photo.setServer(xpp.getAttributeValue(null,"server"));
                        photo.setFarm(xpp.getAttributeValue(null,"farm"));
                        photo.setIsPublic(xpp.getAttributeValue(null,"ispublic"));
                        photo.setIsFamily(xpp.getAttributeValue(null,"isfamily"));
                        photo.setIsFriend(xpp.getAttributeValue(null,"isfriend"));
                    }
                    break;

                case XmlPullParser.END_TAG:
                    if(photo != null)
                        photos.add(photo);
                    photo = null;
                    break;

                default:
                    break;
            }
            eventType = xpp.next();
        }
        return photos;
    }
}
