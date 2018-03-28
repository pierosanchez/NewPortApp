package com.newport.app.data.models.response;

/**
 * Created by tohure on 15/11/17.
 */

public class ScheduleResponse {

    /**
     * id : 1
     * logo : http://208.68.36.216/storage/working-hours/copacabana.jpg
     * file : ObpdgI1c6mrDDI1PZoHoOVGkLHfN75NITvkchkc8.pdf
     * created_at : 2017-10-30 08:47:27
     * updated_at : 2017-11-02 19:46:36
     * file_url : http://208.68.36.216/storage/files/ObpdgI1c6mrDDI1PZoHoOVGkLHfN75NITvkchkc8.pdf
     */

    private int id;
    private String logo;
    private String file;
    private String created_at;
    private String updated_at;
    private String file_url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
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

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }
}
