package com.chaione.model;

import io.realm.RealmObject;

/**
 * Created by niliman on 1/17/2015.
 * Avatar Image data model class.
 */
public class AvatarImage extends RealmObject{

    /**
     * String url for holding image url
     */
    private String url;


    /**
     * get Image Url
     *
     * @return url Image url.
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * set Image Url
     *
     * @param url Image url.
     */
    public void setUrl(String url) {
        this.url = url;
    }

}
