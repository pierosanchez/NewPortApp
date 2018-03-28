package com.newport.app.data.models.response;

/**
 * Created by tohure on 17/11/17.
 */

public class NewResponse {


    /**
     * id : 3
     * title : NOticia Leon
     * image : D226aM0wwAOSJAbCDUX6ZQVdZxng0rGPOSJMBjmw.jpeg
     * content : <p style="text-align: justify; "><font face="Open Sans, Arial, sans-serif"><br></font></p><p style="text-align: justify; "><font face="Open Sans, Arial, sans-serif">Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500, cuando un impresor (N. del T. persona que se dedica a la imprenta) desconocido usó una galería de textos y<u> los mezcló de tal manera que log</u>ró hacer un libro de textos especimen. No sólo sobrevivió 500 años, sino que tambien ingresó como texto de relleno en documentos electrónicos, quedando esencialmente igual al original. Fue popularizado en los 60s con la creación de las hojas "Letraset", las cuales contenian pasajes de Lorem Ipsum, y<b> más recientemente</b> con software de <span style="background-color: rgb(255, 0, 0);">autoedición</span>, como por ejemplo Aldus PageMaker, el cual incluye versiones de Lorem Ipsum.</font></p><p style="text-align: justify; "><font face="Open Sans, Arial, sans-serif"><br></font></p><p style="text-align: center;"><font face="Open Sans, Arial, sans-serif"><iframe width="560" height="315" src="https://www.youtube.com/embed/qu3r_olF8tk" frameborder="0" allowfullscreen=""></iframe><br></font></p>
     * date : 2017-12-01
     * category_id : 1
     * subcategory_id :
     * status : 1
     * has_gallery : 0
     * upload_photos : 0
     * created_at : 2017-11-06 11:35:20
     * updated_at : 2017-11-06 11:47:36
     * subcategory : {}
     * category_name : Mensaje Portada
     * subcategory_name :
     * status_name : Activo
     * image_url : http://208.68.36.216/storage/images/D226aM0wwAOSJAbCDUX6ZQVdZxng0rGPOSJMBjmw.jpeg
     * category : {"id":1,"name":"Mensaje Portada","status":1,"created_at":"2017-10-31 00:00:00","updated_at":"2017-10-31 00:00:00"}
     */

    private int id;
    private String title;
    private String image;
    private String content;
    private String date;
    private int status;
    private int has_gallery;
    private int upload_photos;
    private String created_at;
    private String updated_at;
    private String category_name;
    private String subcategory_name;
    private String status_name;
    private String image_url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getHas_gallery() {
        return has_gallery;
    }

    public void setHas_gallery(int has_gallery) {
        this.has_gallery = has_gallery;
    }

    public int getUpload_photos() {
        return upload_photos;
    }

    public void setUpload_photos(int upload_photos) {
        this.upload_photos = upload_photos;
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

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getSubcategory_name() {
        return subcategory_name;
    }

    public void setSubcategory_name(String subcategory_name) {
        this.subcategory_name = subcategory_name;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
