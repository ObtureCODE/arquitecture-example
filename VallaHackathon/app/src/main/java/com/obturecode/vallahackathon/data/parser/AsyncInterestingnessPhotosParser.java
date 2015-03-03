package com.obturecode.vallahackathon.data.parser;

import android.os.AsyncTask;

import com.obturecode.vallahackathon.data.error.ParserError;
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
public class AsyncInterestingnessPhotosParser extends AsyncTask<String, Void, ArrayList<Photo>> {

    public interface AsyncInterestingnessPhotosParserDelegate{
        public void AsyncInterestingnessPhotosParserResult(ArrayList<Photo> photos);
        public void AsyncInterestingnessPhotosParserError(Error e);
    }

    private AsyncInterestingnessPhotosParserDelegate delegate;

    public AsyncInterestingnessPhotosParser(AsyncInterestingnessPhotosParserDelegate delegate){
        super();
        this.delegate = delegate;
    }
    protected ArrayList<Photo> doInBackground(String... params) {
        try {
            return this.parse(params[0]);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected void onPostExecute(ArrayList<Photo> photos) {
        if(!this.isCancelled()) {
            if (photos == null)
                this.delegate.AsyncInterestingnessPhotosParserError(new ParserError());
            else
                this.delegate.AsyncInterestingnessPhotosParserResult(photos);
        }
    }

    private ArrayList<Photo> parse(String toParse) throws XmlPullParserException, IOException {

        ArrayList<Photo> photos = new ArrayList<Photo>();

        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xpp = factory.newPullParser();
        xpp.setInput(new StringReader(toParse));
        int eventType = xpp.getEventType();
        Photo photo = null;
        while (eventType != XmlPullParser.END_DOCUMENT && !this.isCancelled()){
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
                        photo.setOwnerName(xpp.getAttributeValue(null, "ownername"));
                    }else if(xpp.getName().equalsIgnoreCase("description")){
                        if(photo!=null){
                            photo.setDescription(xpp.nextText());
                        }
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
