package com.obturecode.vallahackathon.domain.entity;

/**
 * Created by husky on 02/03/15.
 */
public class Photo {
    private String id;
    private String owner;
    private String secret;
    private String server;
    private String farm;
    private String title;
    private String isfriend;
    private String ispublic;
    private String isfamily;


    private String description;


    public String getUrl(){
        return "https://farm"+this.farm+".staticflickr.com/"+this.server+"/"+this.id+"_"+this.secret+".jpg";
    }

}
