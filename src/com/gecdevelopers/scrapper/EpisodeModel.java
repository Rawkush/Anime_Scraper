package com.gecdevelopers.scrapper;

public class EpisodeModel {

	
	
	
	 public EpisodeModel(String episode, String episodeUrl) {
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
