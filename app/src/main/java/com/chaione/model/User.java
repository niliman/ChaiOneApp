package com.chaione.model;

import io.realm.RealmObject;

/**
 * This model class will be used to provide User objects data to list view after parsing the json.
 * Created by niliman on 1/17/2015.
 */
public class User  extends RealmObject {

    /**
     * Avatar Image class object.
     */
    private AvatarImage avatar_image;

    /**
     * String name represents title of post.
     */
    private String name;

    /**
     * get AvatarImage class object.
     *
     * @return avatar_image AvatarImage class object.
     */
    public AvatarImage getAvatar_image() {
        return this.avatar_image;
    }

    /**
     * set AvatarImage class object.
     *
     * @param avatar_image AvatarImage class object.
     */
    public void setAvatar_image(AvatarImage avatar_image) {
        this.avatar_image = avatar_image;
    }

    /**
     * get Time Line Posts Title.
     *
     * @return name Time Line Posts Title.
     */
    public String getName() {
        return this.name;
    }

    /**
     * set Time Line Posts Title.
     *
     * @param name Time Line Posts Title.
     */
    public void setName(String name) {
        this.name = name;
    }


}
