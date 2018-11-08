package com.gecdevelopers.scrapper;

import java.util.ArrayList;
import java.util.List;

public class Scrapper {

	
    ArrayList<AnimeModel> recentAnimeList;
    ArrayList<AnimeModel> popularOngoingAnimeList; // ongoing and popular
    
    public Scrapper() {
    	recentAnimeList= new ArrayList<>();
    	popularOngoingAnimeList= new ArrayList<>();
    }
	
	
	public ArrayList<AnimeModel> getRecentAnimeList(){
		return recentAnimeList;
	}
	
	public ArrayList<AnimeModel> getPopularOngoindAnimeList(){
		return popularOngoingAnimeList;
	}
	
	
	
}
