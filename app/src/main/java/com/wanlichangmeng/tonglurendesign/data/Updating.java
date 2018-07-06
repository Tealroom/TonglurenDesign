package com.wanlichangmeng.tonglurendesign.data;

public class Updating {
    private String username;
    private String avatar;
    private String image;

    public Updating(String username, String avatar, String image) {
        this.username = username;
        this.avatar = avatar;
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
