package com.gecdevelopers.scrapper.Models;

public class AnimeModel {

	
	
	
		private String episode;
	    private String imgUrl;
	    private String title;
	    private String nextPageUrl;

	    public AnimeModel(String episode,String imgUrl,String title,String nextPageUrl){
	        this.episode=episode;
	        this.imgUrl= imgUrl;
	        this.title=title;
	        this.nextPageUrl=nextPageUrl;
	    }

	    public String getEpisode() {
	        return episode;
	    }

	    public String getImgUrl() {
	        return imgUrl;
	    }

	    public String getTitle() {
	        return title;
	    }

	    public String getNextPageUrl() {
	        return nextPageUrl;
	    }

	
	
	
	
}
