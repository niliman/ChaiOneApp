package com.chaione.model;


import com.chaione.model.User;

import io.realm.RealmObject;

/**
 * This model class will be used to provide Data objects data to list view after parsing the json.
 * Created by niliman on 1/16/2015.
 */
public class Data extends RealmObject {

    /**
     * User class object.
     */
    private User user;

    /**
     * String text represents the Description of Post.
     */
    private String text;


    /**
     * get Time Line post Detailed description.
     *
     * @return text Time line posts details.
     */
    public String getText() {
        return this.text;
    }

    /**
     * set Time Line post Detailed description.
     *
     * @param text Time line posts details.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * get User class object.
     *
     * @return User class object.
     */
    public User getUser() {
        return this.user;
    }

    /**
     * set User class object.
     *
     * @param user User class object.
     */
    public void setUser(User user) {
        this.user = user;
    }


}
