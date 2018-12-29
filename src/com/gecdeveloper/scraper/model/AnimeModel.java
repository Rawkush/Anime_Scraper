/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gecdeveloper.scraper.model;

/**
 *
 * @author Ankush
 */
public class AnimeModel {
  private String episode;
    private String thumbnail;
    private String title;
    private String nextPageUrl;
    private String type;

    public AnimeModel(String title,String episode, String thumbnail , String nextPageUrl) {
        this.episode = episode;
        this.thumbnail = thumbnail;
        this.title = title;
        this.nextPageUrl = nextPageUrl;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

   


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public AnimeModel(String episode, String thumbnail, String title, String nextPageUrl, String type) {
        this.episode = episode;
        this.thumbnail = thumbnail;
        this.title = title;
        this.nextPageUrl = nextPageUrl;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
  

}
