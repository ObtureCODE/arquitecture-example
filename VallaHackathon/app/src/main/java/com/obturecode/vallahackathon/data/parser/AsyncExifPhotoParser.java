package com.obturecode.vallahackathon.data.parser;

import android.os.AsyncTask;

import com.obturecode.vallahackathon.data.error.ParserError;
import com.obturecode.vallahackathon.domain.entity.Exif;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by husky on 02/03/15.
 */
public class AsyncExifPhotoParser extends AsyncTask<String, Void, Exif> {

    public interface AsyncExifPhotoParserDelegate{
        public void AsyncExifPhotoParserResult(Exif exif);
        public void AsyncExifPhotoParserError(Error e);
    }

    private AsyncExifPhotoParserDelegate delegate;

    public AsyncExifPhotoParser(AsyncExifPhotoParserDelegate delegate){
        super();
        this.delegate = delegate;
    }
    protected Exif doInBackground(String... params) {
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

    protected void onPostExecute(Exif exif) {
        if(!this.isCancelled()) {
            if (exif == null)
                this.delegate.AsyncExifPhotoParserError(new ParserError());
            else
                this.delegate.AsyncExifPhotoParserResult(exif);
        }
    }

    private Exif parse(String toParse) throws XmlPullParserException, IOException {


        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xpp = factory.newPullParser();
        xpp.setInput(new StringReader(toParse));
        int eventType = xpp.getEventType();
        Exif exif = null;
        String parent = null;
        while (eventType != XmlPullParser.END_DOCUMENT && !this.isCancelled()){
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    if(xpp.getName().equalsIgnoreCase("photo")){
                        exif = new Exif();
                        exif.setCamera(xpp.getAttributeValue(null, "camera"));
                    }
                    else if(xpp.getName().equalsIgnoreCase("exif")){
                        parent = xpp.getAttributeValue(null, "tag");
                    }
                    else if(xpp.getName().equalsIgnoreCase("raw") && parent!=null && parent.equalsIgnoreCase("ExposureTime")){
                        exif.setExposure(xpp.nextText());
                    }
                    else if(xpp.getName().equalsIgnoreCase("raw") && parent!=null && parent.equalsIgnoreCase("FNumber")){
                        exif.setAperture(xpp.nextText());
                    }
                    else if(xpp.getName().equalsIgnoreCase("raw") && parent!=null && parent.equalsIgnoreCase("ISO")){
                        exif.setIso(xpp.nextText());
                    }
                    else if(xpp.getName().equalsIgnoreCase("raw") && parent!=null && parent.equalsIgnoreCase("Lens")){
                        exif.setLens(xpp.nextText());
                    }
                    else if(xpp.getName().equalsIgnoreCase("raw") && parent!=null && parent.equalsIgnoreCase("FocalLength")){
                        exif.setFocalLength(xpp.nextText());
                    }
                    break;

                case XmlPullParser.END_TAG:
                    if(xpp.getName().equalsIgnoreCase("photo"))
                        return exif;
                    else if(xpp.getName().equalsIgnoreCase("exif"))
                        parent = null;
                    break;

                default:
                    break;
            }
            eventType = xpp.next();
        }
        return null;
    }
}
