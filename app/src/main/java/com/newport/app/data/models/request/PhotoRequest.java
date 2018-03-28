package com.newport.app.data.models.request;

import static com.newport.app.util.Constant.BASE64_HEADER;

/**
 * Created by tohure on 09/11/17.
 */

public class PhotoRequest {

    /**
     * user_code : AP836S34
     * image : base
     * news_id : 2
     */

    private String user_code;
    private String image;
    private String news_id;

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = BASE64_HEADER + image;
    }

    public String getNews_id() {
        return news_id;
    }

    public void setNews_id(String news_id) {
        this.news_id = news_id;
    }
}
