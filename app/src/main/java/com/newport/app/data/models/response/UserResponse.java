package com.newport.app.data.models.response;

import java.util.List;

/**
 * Created by tohure on 05/12/17.
 */

public class UserResponse {

    /**
     * dni : 44035653
     * name : Jose Pascual Angeles
     * sap_code : 20000128
     * area_code : G01ADSIS01
     * area : Sistemas - Newport
     * position_code : 60004773
     * position : Asistente de Programación
     * telephone : 993685539
     * email : kbarrientosa@gmail.com
     * date_entry : 08/10/2005
     * tardiness : {"entry":[{"day":"26/10","time":"00:06:00"},{"day":"27/10","time":"00:04:00"},{"day":"28/10","time":"00:04:00"},{"day":"29/10","time":"00:01:00"}],"lunch":[{"day":"26/10","time":"00:05:00"},{"day":"27/10","time":"00:02:00"},{"day":"28/10","time":"00:03:00"},{"day":"29/10","time":"00:08:00"},{"day":"30/10","time":"00:02:00"}]}
     */

    private String dni;
    private String name;
    private int sap_code;
    private String area_code;
    private String area;
    private String position_code;
    private String position;
    private String telephone;
    private String email;
    private String date_entry;
    private TardinessBean tardiness;

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSap_code() {
        return sap_code;
    }

    public void setSap_code(int sap_code) {
        this.sap_code = sap_code;
    }

    public String getArea_code() {
        return area_code;
    }

    public void setArea_code(String area_code) {
        this.area_code = area_code;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPosition_code() {
        return position_code;
    }

    public void setPosition_code(String position_code) {
        this.position_code = position_code;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate_entry() {
        return date_entry;
    }

    public void setDate_entry(String date_entry) {
        this.date_entry = date_entry;
    }

    public TardinessBean getTardiness() {
        return tardiness;
    }

    public void setTardiness(TardinessBean tardiness) {
        this.tardiness = tardiness;
    }

    public static class TardinessBean {
        private List<EntryBean> entry;
        private List<LunchBean> lunch;

        public List<EntryBean> getEntry() {
            return entry;
        }

        public void setEntry(List<EntryBean> entry) {
            this.entry = entry;
        }

        public List<LunchBean> getLunch() {
            return lunch;
        }

        public void setLunch(List<LunchBean> lunch) {
            this.lunch = lunch;
        }

        public static class EntryBean {
            /**
             * day : 26/10
             * time : 00:06:00
             */

            private String day;
            private String time;

            public String getDay() {
                return day;
            }

            public void setDay(String day) {
                this.day = day;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }
        }

        public static class LunchBean {
            /**
             * day : 26/10
             * time : 00:05:00
             */

            private String day;
            private String time;

            public String getDay() {
                return day;
            }

            public void setDay(String day) {
                this.day = day;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }
        }
    }
}
