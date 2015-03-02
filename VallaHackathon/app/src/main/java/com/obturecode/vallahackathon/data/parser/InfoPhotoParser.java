package com.obturecode.vallahackathon.data.parser;

import com.obturecode.vallahackathon.domain.entity.Photo;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by husky on 02/03/15.
 */
public class InfoPhotoParser {
    public static Photo parse(String toParse) throws XmlPullParserException, IOException {


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
                        photo.setTitle(xpp.getAttributeValue(null, "title"));
                        photo.setId(xpp.getAttributeValue(null, "id"));
                        photo.setSecret(xpp.getAttributeValue(null, "secret"));
                        photo.setServer(xpp.getAttributeValue(null, "server"));
                        photo.setFarm(xpp.getAttributeValue(null, "farm"));
                    }else  if(xpp.getName().equalsIgnoreCase("description")){
                        photo.setDescription(xpp.nextText());
                    }else  if(xpp.getName().equalsIgnoreCase("title")){
                        photo.setTitle(xpp.nextText());
                    }else  if(xpp.getName().equalsIgnoreCase("comments")){
                        photo.setNumComments(Integer.getInteger(xpp.nextText()));
                    }else  if(xpp.getName().equalsIgnoreCase("visibility")){
                        photo.setIsPublic(xpp.getAttributeValue(null,"ispublic"));
                        photo.setIsFamily(xpp.getAttributeValue(null,"isfamily"));
                        photo.setIsFriend(xpp.getAttributeValue(null,"isfriend"));
                    }
                    break;

                case XmlPullParser.END_TAG:
                    if(xpp.getName().equalsIgnoreCase("photo"))
                        return photo;

                default:
                    break;
            }
            eventType = xpp.next();
        }
        return null;
    }
}
