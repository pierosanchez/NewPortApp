package com.newport.app.data.models.response;

/**
 * Created by tohure on 09/11/17.
 */

public class PhotoUploadedResponse {

    /**
     * news_id : 2
     * user_code : AP836S34
     * image : 1acdbd35ce2d14f93353d20c7330af92c4c3c201.png
     * status : 1
     * updated_at : 2017-11-05 21:37:20
     * created_at : 2017-11-05 21:37:20
     * id : 3
     * image_url : image_url
     * status_name : Activo
     */

    private String news_id;
    private String user_code;
    private String image;
    private int status;
    private String updated_at;
    private String created_at;
    private int id;
    private String image_url;
    private String status_name;

    public String getNews_id() {
        return news_id;
    }

    public void setNews_id(String news_id) {
        this.news_id = news_id;
    }

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
        this.image = image;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }
}
