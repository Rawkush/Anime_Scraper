/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gecdeveloper.scraper.gogoanime;

import com.gecdeveloper.scraper.AnimeFetch;
import com.gecdeveloper.scraper.model.AnimeModel;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Ankush
 */
public class RecentlyUpdated implements AnimeFetch{
    
	
    private ArrayList<AnimeModel> list;
    private final String mainPageUrl="https://www4.gogoanime.se";
    private JSONObject  json;
     
     public RecentlyUpdated(){
        list= new ArrayList<>();  
        json= new JSONObject();
     }
    
     // gets recently updated animes
    @Override
     public JSONObject getAnimes() throws UnknownHostException { // this exception is throws when no internet connection
        
    	 
        try{
    	     startScraping();
	    }catch (JSONException e) {
		
                e.printStackTrace();
	    }
        return json;
     }
     
     private void startScraping() throws JSONException{
         JSONArray array = new JSONArray();

    	 try {
             Document doc = Jsoup.connect(mainPageUrl).get();
             Elements elements = doc.select("div.last_episodes.loaddub").select("ul.items").select("li");
             for (Element element : elements) {
                 Elements Episode = element.select("p.episode");
                 String episode = Episode.text();
                 
                 Elements titles = element.select("p.name");
                 String title = titles.text();
                 
                 //                 Log.d("title", title);
                 Elements img = element.select("div.img");
                 Element links = img.select("a[href]").first(); // a with href
                 String nextPageLink = links.attr("href");
                 nextPageLink = mainPageUrl + nextPageLink;
                 Element ImageLink = links.select("img").first();
                 String imgLink = ImageLink.attr("src");               
               //  Log.d("links", imgLink);
                 System.out.println("title of anime" + title);
                 System.out.println("image Link" + imgLink);
                 System.out.println("episode details "+episode);
                 System.out.println("next page Link" + nextPageLink);
                 
                 // adding item into arrary
                 JSONObject item= new JSONObject();
       			 item.put("title", title);    		 
    			 item.put("thumbnail", imgLink);    		 
    			 item.put("latest episode", episode);
    			 item.put("url", nextPageLink);
    	         array.put(item);

             }

             
         } catch (IOException e) {
             e.printStackTrace();
         }
        finally{
             json.put("Recent Animes", array);
        }

    }

    @Override
    public JSONObject getEpisodes(String anime) throws UnknownHostException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JSONObject getSearchedAnime(String animeName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
                             
    
    
}
