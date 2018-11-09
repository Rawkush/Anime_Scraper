package com.gecdevelopers.scrapper;

import java.util.ArrayList;

import com.gecdevelopers.scrapper.Models.AnimeModel;
import com.gecdevelopers.scrapper.Models.EpisodeModel;
import com.gecdevelopers.scrapper.episodeScraper.EpisodeServers;
import com.gecdevelopers.scrapper.listScraper.EpisodeList;
import com.gecdevelopers.scrapper.listScraper.PopularOnGoing;
import com.gecdevelopers.scrapper.listScraper.RecentAnimeScrapper;
import com.gecdevelopers.scrapper.listScraper.SearchAnime;



public class Scraper {

	
    private ArrayList<AnimeModel> recentAnimeList;
    private ArrayList<EpisodeModel> episodeList;
    private ArrayList<AnimeModel> popularOngoingAnimeList; // ongoing and popular
    private ArrayList<AnimeModel> searchedAnimeList;
    private ArrayList<EpisodeModel> serverList;
    public Scraper() {
    	recentAnimeList= new ArrayList<>();
    	popularOngoingAnimeList= new ArrayList<>();
    	searchedAnimeList= new ArrayList<>();
    	episodeList=new ArrayList<>();
    	serverList= new ArrayList<>();
    }
	
    
    
    public void startScrapingServers(String url) {
    	
    	EpisodeServers var= new EpisodeServers();
    	var.startScraping(url);
    	serverList.addAll(var.getServersList());
    			
    }
    
    void startSearching(String animeName) {
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
	    
	public void scrapeEpisodeList(String animeUrl) {
		EpisodeList eplist= new EpisodeList(animeUrl);
		eplist.startScraping();
		episodeList.addAll(eplist.getEpisodeList());
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
	
	public ArrayList<EpisodeModel> getEpisodeList(){
		return episodeList;
	}
	
	
	public ArrayList<EpisodeModel> getServerList(){
		return serverList;
	}
	
	
}
