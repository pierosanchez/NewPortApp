package com.newport.app.ui.internalpolicies;

/**
 * Created by tohure on 11/11/17.
 */

public class InternalPolice {

    private String question;
    private String link;

    public InternalPolice(String question, String link) {
        this.question = question;
        this.link = link;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
