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
public class EpisodeData {
    
	public EpisodeData(String episode, String episodeUrl) {
            this.episode = episode;
            this.episodeUrl = episodeUrl;
	    
        }

	String episode;
	String episodeUrl;


	public String getEpisode() {
            return episode;
	}

	public String getEpisodeUrl() {
             return episodeUrl;
	}
	
}
