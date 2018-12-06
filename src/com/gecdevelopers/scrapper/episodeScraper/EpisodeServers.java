package com.gecdevelopers.scrapper.episodeScraper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gecdevelopers.scrapper.Models.EpisodeModel;

import java.io.IOException;
import java.util.ArrayList;
public class EpisodeServers {

	private ArrayList <EpisodeModel> servers;
    private JSONObject  json;

    public EpisodeServers() {
    	servers= new ArrayList<>();
    	json = new JSONObject();
    }
	
	private void startScraping(String url) {
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

	public JSONObject getServerList(String url) {
		
		startScraping(url);
		 try {
			createJSON();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return json;
	}

	private void createJSON() throws JSONException {
	    	 
	    	 JSONArray array = new JSONArray();
	    	 for(EpisodeModel ls: servers) {
	        	 JSONObject item= new JSONObject();
	       			 item.put("Server Name", ls.getEpisode());    		 
	    			 item.put("url", ls.getEpisodeUrl());    		 
	    			 array.put(item);
	    	 }
	    	 
	    	 json.put("Servers", array);
	     }

	 
	
}
