package com.newport.app.data.models.response;

import java.util.List;

/**
 * Created by tohure on 23/11/17.
 */

public class HomeResponse {


    /**
     * section1 : [{"id":12,"title":"Newport Restaurante ¡Un nuevo reto!","image":"DWX78QmsMDElQMbh0xcDllzgbzszlD7cQO53bYso.jpeg","date":"2017-12-01","status":1,"category_id":2,"subcategory_id":"","has_gallery":0,"upload_photos":0,"created_at":"2017-12-01 16:02:41","updated_at":"2017-12-01 16:03:19","category_name":"Noticias","subcategory_name":"","status_name":"Activo","image_url":"http://208.68.36.216/storage/images/DWX78QmsMDElQMbh0xcDllzgbzszlD7cQO53bYso.jpeg","category":{"id":2,"name":"Noticias","status":1,"created_at":"2017-10-31 00:00:00","updated_at":"2017-10-31 00:00:00"}},{"id":1,"title":"Fiestas Patrias2222","image":"XX5QIONIR4vE94YSkmS1HIaNz0o3KGzzCM7XoSWe.jpeg","date":"2018-03-23","status":1,"category_id":2,"subcategory_id":"","has_gallery":0,"upload_photos":0,"created_at":"2017-10-25 02:50:36","updated_at":"2017-11-30 16:52:48","category_name":"Noticias","subcategory_name":"","status_name":"Activo","image_url":"http://208.68.36.216/storage/images/XX5QIONIR4vE94YSkmS1HIaNz0o3KGzzCM7XoSWe.jpeg","category":{"id":2,"name":"Noticias","status":1,"created_at":"2017-10-31 00:00:00","updated_at":"2017-10-31 00:00:00"}}]
     * section2 : {"dni":"44035653","days":5,"time":"00:35:00"}
     * section3 : {"text":"Sé el reportero de la fiesta ¡SUBE TUS FOTOS!","news_id":7,"upload_photos":1}
     * section4 : {"id":3,"title":"NOticia Leon","image":"D226aM0wwAOSJAbCDUX6ZQVdZxng0rGPOSJMBjmw.jpeg","date":"2017-12-01","category_id":1,"subcategory_id":"","has_gallery":0,"upload_photos":0,"created_at":"2017-11-06 11:35:20","updated_at":"2017-11-06 11:47:36","category_name":"Mensaje Portada","subcategory_name":"","status_name":"Inactivo","image_url":"http://208.68.36.216/storage/images/D226aM0wwAOSJAbCDUX6ZQVdZxng0rGPOSJMBjmw.jpeg","category":{"id":1,"name":"Mensaje Portada","status":1,"created_at":"2017-10-31 00:00:00","updated_at":"2017-10-31 00:00:00"}}
     */

    private List<Section1Bean> section1;
    private Section2Bean section2;
    private Section3Bean section3;
    private Section4Bean section4;

    public Section2Bean getSection2() {
        return section2;
    }

    public void setSection2(Section2Bean section2) {
        this.section2 = section2;
    }

    public Section3Bean getSection3() {
        return section3;
    }

    public void setSection3(Section3Bean section3) {
        this.section3 = section3;
    }

    public Section4Bean getSection4() {
        return section4;
    }

    public void setSection4(Section4Bean section4) {
        this.section4 = section4;
    }

    public List<Section1Bean> getSection1() {
        return section1;
    }

    public void setSection1(List<Section1Bean> section1) {
        this.section1 = section1;
    }

    public static class Section2Bean {
        /**
         * dni : 44035653
         * days : 5
         * time : 00:35:00
         */

        private String dni;
        private int days;
        private String time;

        public String getDni() {
            return dni;
        }

        public void setDni(String dni) {
            this.dni = dni;
        }

        public int getDays() {
            return days;
        }

        public void setDays(int days) {
            this.days = days;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }

    public static class Section3Bean {
        /**
         * text : Sé el reportero de la fiesta ¡SUBE TUS FOTOS!
         * news_id : 7
         * upload_photos : 1
         */

        private String text;
        private int news_id;
        private int upload_photos;
        private String image_url;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public int getNews_id() {
            return news_id;
        }

        public void setNews_id(int news_id) {
            this.news_id = news_id;
        }

        public int getUpload_photos() {
            return upload_photos;
        }

        public void setUpload_photos(int upload_photos) {
            this.upload_photos = upload_photos;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }
    }

    public static class Section4Bean {
        /**
         * id : 3
         * title : NOticia Leon
         * image : D226aM0wwAOSJAbCDUX6ZQVdZxng0rGPOSJMBjmw.jpeg
         * date : 2017-12-01
         * category_id : 1
         * subcategory_id :
         * has_gallery : 0
         * upload_photos : 0
         * created_at : 2017-11-06 11:35:20
         * updated_at : 2017-11-06 11:47:36
         * category_name : Mensaje Portada
         * subcategory_name :
         * status_name : Inactivo
         * image_url : http://208.68.36.216/storage/images/D226aM0wwAOSJAbCDUX6ZQVdZxng0rGPOSJMBjmw.jpeg
         * category : {"id":1,"name":"Mensaje Portada","status":1,"created_at":"2017-10-31 00:00:00","updated_at":"2017-10-31 00:00:00"}
         */

        private int id;
        private String title;
        private String image;
        private String date;
        private int category_id;
        private String subcategory_id;
        private int has_gallery;
        private int upload_photos;
        private String created_at;
        private String updated_at;
        private String category_name;
        private String subcategory_name;
        private String status_name;
        private String image_url;
        private String image_url_icon;
        private CategoryBean category;

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

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getCategory_id() {
            return category_id;
        }

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
        }

        public String getSubcategory_id() {
            return subcategory_id;
        }

        public void setSubcategory_id(String subcategory_id) {
            this.subcategory_id = subcategory_id;
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

        public String getImage_url_icon() {
            return image_url_icon;
        }

        public void setImage_url_icon(String image_url_icon) {
            this.image_url_icon = image_url_icon;
        }

        public CategoryBean getCategory() {
            return category;
        }

        public void setCategory(CategoryBean category) {
            this.category = category;
        }

        public static class CategoryBean {
            /**
             * id : 1
             * name : Mensaje Portada
             * status : 1
             * created_at : 2017-10-31 00:00:00
             * updated_at : 2017-10-31 00:00:00
             */

            private int id;
            private String name;
            private int status;
            private String created_at;
            private String updated_at;

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
        }
    }

    public static class Section1Bean {
        /**
         * id : 12
         * title : Newport Restaurante ¡Un nuevo reto!
         * image : DWX78QmsMDElQMbh0xcDllzgbzszlD7cQO53bYso.jpeg
         * date : 2017-12-01
         * status : 1
         * category_id : 2
         * subcategory_id :
         * has_gallery : 0
         * upload_photos : 0
         * created_at : 2017-12-01 16:02:41
         * updated_at : 2017-12-01 16:03:19
         * category_name : Noticias
         * subcategory_name :
         * status_name : Activo
         * image_url : http://208.68.36.216/storage/images/DWX78QmsMDElQMbh0xcDllzgbzszlD7cQO53bYso.jpeg
         * category : {"id":2,"name":"Noticias","status":1,"created_at":"2017-10-31 00:00:00","updated_at":"2017-10-31 00:00:00"}
         */

        private int id;
        private String title;
        private String image;
        private String date;
        private int status;
        private int category_id;
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

        public int getCategory_id() {
            return category_id;
        }

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
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
}