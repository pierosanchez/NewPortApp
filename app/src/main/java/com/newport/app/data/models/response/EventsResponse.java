package com.newport.app.data.models.response;

import java.util.List;

/**
 * Created by tohure on 21/11/17.
 */

public class EventsResponse {


    /**
     * id : 1
     * subcategory : Así lo vivimos
     * news : [{"id":4,"category_id":3,"subcategory_id":1,"title":"Evento Pasado","date":"2017-11-02","has_gallery":1,"status":1,"created_at":"2017-11-13 12:22:04","category_name":"Eventos","subcategory_name":"Así lo vivimos","status_name":"Activo","image_url":"http://208.68.36.216/storage/images/","category":{"id":3,"name":"Eventos","status":1,"created_at":"2017-11-07 15:50:00","updated_at":"2017-11-07 15:50:00"},"subcategory":{"id":1,"name":"Así lo vivimos","category_id":3,"status":1,"created_at":"2017-10-31 00:00:00","updated_at":"2017-10-31 00:00:00"}}]
     */

    private int id;
    private String subcategory;
    private List<NewsBean> news;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public List<NewsBean> getNews() {
        return news;
    }

    public void setNews(List<NewsBean> news) {
        this.news = news;
    }

    public static class NewsBean {
        /**
         * id : 4
         * category_id : 3
         * subcategory_id : 1
         * title : Evento Pasado
         * date : 2017-11-02
         * has_gallery : 1
         * status : 1
         * created_at : 2017-11-13 12:22:04
         * category_name : Eventos
         * subcategory_name : Así lo vivimos
         * status_name : Activo
         * image_url : http://208.68.36.216/storage/images/
         * category : {"id":3,"name":"Eventos","status":1,"created_at":"2017-11-07 15:50:00","updated_at":"2017-11-07 15:50:00"}
         * subcategory : {"id":1,"name":"Así lo vivimos","category_id":3,"status":1,"created_at":"2017-10-31 00:00:00","updated_at":"2017-10-31 00:00:00"}
         */

        private int id;
        private int category_id;
        private int subcategory_id;
        private String title;
        private String date;
        private int has_gallery;
        private int status;
        private String created_at;
        private String category_name;
        private String subcategory_name;
        private String status_name;
        private String image_url;
        private CategoryBean category;
        private SubcategoryBean subcategory;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCategory_id() {
            return category_id;
        }

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
        }

        public int getSubcategory_id() {
            return subcategory_id;
        }

        public void setSubcategory_id(int subcategory_id) {
            this.subcategory_id = subcategory_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getHas_gallery() {
            return has_gallery;
        }

        public void setHas_gallery(int has_gallery) {
            this.has_gallery = has_gallery;
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

        public CategoryBean getCategory() {
            return category;
        }

        public void setCategory(CategoryBean category) {
            this.category = category;
        }

        public SubcategoryBean getSubcategory() {
            return subcategory;
        }

        public void setSubcategory(SubcategoryBean subcategory) {
            this.subcategory = subcategory;
        }

        public static class CategoryBean {
            /**
             * id : 3
             * name : Eventos
             * status : 1
             * created_at : 2017-11-07 15:50:00
             * updated_at : 2017-11-07 15:50:00
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

        public static class SubcategoryBean {
            /**
             * id : 1
             * name : Así lo vivimos
             * category_id : 3
             * status : 1
             * created_at : 2017-10-31 00:00:00
             * updated_at : 2017-10-31 00:00:00
             */

            private int id;
            private String name;
            private int category_id;
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

            public int getCategory_id() {
                return category_id;
            }

            public void setCategory_id(int category_id) {
                this.category_id = category_id;
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
}
