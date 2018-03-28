package com.newport.app.data.models.response;

import java.util.List;

/**
 * Created by tohure on 12/11/17.
 */

public class DirectoryResponse {

    /**
     * section : Jefatura
     * contacts : [{"id":7,"name":"Giuliana cavero","position":"Jefe de RRHH","telephone1":"2355","telephone2":"987663833","email":"gcavero@newport.com.pe","photo":"VZ4ggIzOmOsrYAt0wW69f0hTSkDkmMWrOOElcpPG.jpeg","created_at":"2017-11-11 19:12:16","updated_at":"2017-11-13 12:51:52","photo_url":"http://208.68.36.216/storage/directory/VZ4ggIzOmOsrYAt0wW69f0hTSkDkmMWrOOElcpPG.jpeg"},{"id":6,"name":"Jacqueline Villanueva","position":"Gerente de Recursos Humanos","telephone1":"2350","telephone2":"","email":"jvillanueva@newport.com.pe","photo":"8YUbfvIkitBX0SL9B2dUQC0CxuIEZf5mHZLdQT9S.jpeg","created_at":"2017-11-11 19:01:06","updated_at":"2017-11-13 12:51:22","photo_url":"http://208.68.36.216/storage/directory/8YUbfvIkitBX0SL9B2dUQC0CxuIEZf5mHZLdQT9S.jpeg"},{"id":8,"name":"Yanira Retamozo","position":"Coordinadora de RRHH","telephone1":"1208","telephone2":"989012028","email":"yretamozo@newport.com.pe","photo":"yc58kwNzDuvdJO6PM6LLwA1qUsV9Qk7YT3A2QyiS.jpeg","created_at":"2017-11-11 19:13:50","updated_at":"2017-11-13 12:52:18","photo_url":"http://208.68.36.216/storage/directory/yc58kwNzDuvdJO6PM6LLwA1qUsV9Qk7YT3A2QyiS.jpeg"}]
     */

    private String section;
    private List<ContactsBean> contacts;

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public List<ContactsBean> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactsBean> contacts) {
        this.contacts = contacts;
    }

    public static class ContactsBean {
        /**
         * id : 7
         * name : Giuliana cavero
         * position : Jefe de RRHH
         * telephone1 : 2355
         * telephone2 : 987663833
         * email : gcavero@newport.com.pe
         * photo : VZ4ggIzOmOsrYAt0wW69f0hTSkDkmMWrOOElcpPG.jpeg
         * created_at : 2017-11-11 19:12:16
         * updated_at : 2017-11-13 12:51:52
         * photo_url : http://208.68.36.216/storage/directory/VZ4ggIzOmOsrYAt0wW69f0hTSkDkmMWrOOElcpPG.jpeg
         */

        private int id;
        private String name;
        private String position;
        private String telephone1;
        private String telephone2;
        private String email;
        private String photo;
        private String created_at;
        private String updated_at;
        private String photo_url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getTelephone1() {
            return telephone1;
        }

        public void setTelephone1(String telephone1) {
            this.telephone1 = telephone1;
        }

        public String getTelephone2() {
            return telephone2;
        }

        public void setTelephone2(String telephone2) {
            this.telephone2 = telephone2;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
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

        public String getPhoto_url() {
            return photo_url;
        }

        public void setPhoto_url(String photo_url) {
            this.photo_url = photo_url;
        }
    }
}
