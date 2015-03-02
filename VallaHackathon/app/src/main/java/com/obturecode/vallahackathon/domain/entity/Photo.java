package com.obturecode.vallahackathon.domain.entity;

/**
 * Created by husky on 02/03/15.
 */
public class Photo {
    private String id;
    private String ownerId;
    private String secret;
    private String server;
    private String farm;
    private String title;
    private String isFriend;
    private String isPublic;
    private String isFamily;
    private Owner owner;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getFarm() {
        return farm;
    }

    public void setFarm(String farm) {
        this.farm = farm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsFriend() {
        return isFriend;
    }

    public void setIsFriend(String isFriend) {
        this.isFriend = isFriend;
    }

    public String getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(String isPublic) {
        this.isPublic = isPublic;
    }

    public String getIsFamily() {
        return isFamily;
    }

    public void setIsFamily(String isFamily) {
        this.isFamily = isFamily;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl(){
        return "https://farm"+this.farm+".staticflickr.com/"+this.server+"/"+this.id+"_"+this.secret+".jpg";
    }

}
