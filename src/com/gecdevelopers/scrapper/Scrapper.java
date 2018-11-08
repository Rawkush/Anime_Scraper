package com.gecdevelopers.scrapper;

import java.util.ArrayList;



public class Scrapper {

	
    private ArrayList<AnimeModel> recentAnimeList;
    private ArrayList<AnimeModel> popularOngoingAnimeList; // ongoing and popular
    private ArrayList<AnimeModel> searchedAnimeList;
    public Scrapper() {
    	recentAnimeList= new ArrayList<>();
    	popularOngoingAnimeList= new ArrayList<>();
    	searchedAnimeList= new ArrayList<>();
    }
	
    
    
    
    public void startSearching(String animeName) {
    	SearchAnime var= new SearchAnime(animeName);
    	var.startScraping();
    	searchedAnimeList.addAll(var.getList());
    }
	
	public void scrapeForPopularOngoingAnimeList() {
		PopularOnGoing var= new PopularOnGoing();
		var.startScraping();
		popularOngoingAnimeList.addAll(var.getList());
	}
	
	public void scrapeForRecentAnimeList() {
		RecentAnimeScrapper var= new RecentAnimeScrapper();
		var.startScraping();
		recentAnimeList.addAll(var.getList());
	}
	    
	public ArrayList<AnimeModel> getRecentAnimeList(){
		return recentAnimeList;
	}
	
	public ArrayList<AnimeModel> getPopularOngoindAnimeList(){
		return popularOngoingAnimeList;
	}
	
	public ArrayList<AnimeModel> getSearchedAnimeList() {
		return searchedAnimeList;
	}
	
	
	
	
}
