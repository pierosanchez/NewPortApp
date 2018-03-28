package com.newport.app.data.models.response;

import java.io.Serializable;

/**
 * Created by tohure on 08/11/17.
 */

public class PhotoGalleryEventResponse implements Serializable {


    /**
     * id : 3
     * news_id : 2
     * user_code : AP836S34
     * image : 1acdbd35ce2d14f93353d20c7330af92c4c3c201.png
     * status : 1
     * created_at : 2017-11-05 21:37:20
     * updated_at : 2017-11-05 21:37:20
     * image_url : http://208.68.36.216/storage/galleries/1acdbd35ce2d14f93353d20c7330af92c4c3c201.png
     * news_title : Cancion Criolla
     * status_name : Activo
     */

    private int id;
    private int news_id;
    private String user_code;
    private String image;
    private int status;
    private String created_at;
    private String updated_at;
    private String image_url;
    private String news_title;
    private String status_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNews_id() {
        return news_id;
    }

    public void setNews_id(int news_id) {
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

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getNews_title() {
        return news_title;
    }

    public void setNews_title(String news_title) {
        this.news_title = news_title;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }
}
