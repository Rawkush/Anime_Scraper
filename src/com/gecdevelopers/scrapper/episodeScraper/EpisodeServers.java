package com.gecdevelopers.scrapper.episodeScraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gecdevelopers.scrapper.Models.EpisodeModel;

import java.io.IOException;
import java.util.ArrayList;
public class EpisodeServers {

    ArrayList <EpisodeModel> servers;

    public EpisodeServers() {
    	servers= new ArrayList<>();
    }
	
	public void startScraping(String url) {
		   try {
               Document doc = Jsoup.connect(url).get();

               Elements container= doc.select("div.anime_muti_link");
               Elements container1= container.select("ul");
               Elements link = container1.select("li"); // a with href
               for(Element li:link) {
                   String embeded= li.select("a").attr("data-video");
                   String serverName= li.select("a").text();
                   servers.add(new EpisodeModel(serverName,embeded));
                  // Log.d("sjdnckkd", li.select("a").text());
                  // Log.d("sjdnckkd", "aj");
                   
               }
           } catch (IOException e) {
               e.printStackTrace();
           }
	
	}

	
	
	public ArrayList<EpisodeModel> getServersList(){
		return servers;
	}
	
	
	
}
