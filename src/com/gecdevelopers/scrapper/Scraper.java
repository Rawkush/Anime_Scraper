package com.gecdevelopers.scrapper;

import org.json.JSONObject;
import com.gecdevelopers.scrapper.episodeScraper.EpisodeServers;
import com.gecdevelopers.scrapper.listScraper.EpisodeList;
import com.gecdevelopers.scrapper.listScraper.PopularOnGoing;
import com.gecdevelopers.scrapper.listScraper.RecentAnimeScrapper;
import com.gecdevelopers.scrapper.listScraper.SearchAnime;



public class Scraper {

	
    private JSONObject recentAnimeList;
    private JSONObject episodeList;
    private JSONObject popularOngoingAnimeList; // ongoing and popular
    private JSONObject searchedAnimeList;
    private JSONObject serverList;
    public Scraper() {
    	recentAnimeList= new JSONObject();
    	popularOngoingAnimeList= new JSONObject();
    	searchedAnimeList= new JSONObject();
    	episodeList=new JSONObject();
    	serverList= new JSONObject();
    }
	
    
    
    public JSONObject getServers(String url) {
    	EpisodeServers var= new EpisodeServers();
    	serverList=var.getServerList(url);
    	return serverList;	
    }
    
    public JSONObject searchAnime(String animeName) {
    	SearchAnime var= new SearchAnime();
    	searchedAnimeList= var.getSearchedItem(animeName);
    	return searchedAnimeList;
    }
	
	public JSONObject getPopularOngoing() {
		PopularOnGoing var= new PopularOnGoing();
		popularOngoingAnimeList=var.getPopularOngoingAnimes();	
		return popularOngoingAnimeList;
		
		
	}
	
	public JSONObject getRecentlyUpdated() {
		RecentAnimeScrapper var= new RecentAnimeScrapper();
		recentAnimeList=var.getRecentAnimes();
		return recentAnimeList;
	}
	
	public JSONObject getAllEpisodes(String animeUrl) {
		EpisodeList var= new EpisodeList();
		episodeList= var.getEpisodesList(animeUrl);
		return episodeList;
	}
	
	
	    
		
}
