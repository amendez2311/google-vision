package com.upc.ia.entity;

import javax.persistence.*;

@Entity
@Table(name = "google_vision")
public class GoogleVisionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "mid")
    public String mid;

    @Column(name = "description")
    public String description;

    @Column(name = "score")
    public String score;

    @Column(name = "topicality")
    public String topicality;

    @Column(name = "locale")
    public String locale;

    @Column(name = "keyf")
    public String key;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getTopicality() {
        return topicality;
    }

    public void setTopicality(String topicality) {
        this.topicality = topicality;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "GoogleVisionEntity{" +
                "id=" + id +
                ", mid='" + mid + '\'' +
                ", description='" + description + '\'' +
                ", score='" + score + '\'' +
                ", topicality='" + topicality + '\'' +
                ", locale='" + locale + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}
