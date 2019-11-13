/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gecdeveloper.scraper.gogoanime;

import com.gecdeveloper.scraper.AnimeFetch;
import com.gecdeveloper.scraper.model.AnimeModel;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.net.UnknownHostException;
/**
 *
 * @author Ankush
 */
public class SearchAnime implements AnimeFetch{
      
    
    public ArrayList<AnimeModel> list;
    private final String url="https://www4.gogoanime.se/search.html?keyword=";
    private String animeName;
    private JSONObject  json;

	
	
    public SearchAnime() {
		// TODO Auto-generated constructor stub
	   list= new ArrayList<>();
	   this.animeName="";
	   json= new JSONObject();
    }
   
    private void startScraping() {
		
		 try {
	            Document doc = Jsoup.connect(getUrl()).get();
	            Elements container = doc.select("div.last_episodes");
	            Elements container2 = container.select("ul.items");
	          //  Log.d("urlll",container.outerHtml());
	            Elements dataContainer = container2.select("li");
	            for (Element element : dataContainer) {
	                Elements Episode = element.select("p.released");
	                String episode = Episode.text();
	                Elements titles = element.select("p.name");
	                String title = titles.text();
	           //     Log.d("Fetchtitle", title);
	                Elements img = element.select("div.img");
	                Element links = img.select("a[href]").first(); // a with href
	                String nextPageLink = links.attr("href");
	                Element ImageLink = links.select("img").first();
	                String imgLink = ImageLink.attr("src");
	         //       Log.d("fetchsearched", nextPageLink);
	                list.add(new AnimeModel(episode, imgLink, title, nextPageLink+"-episode-1"));
	                
	                System.out.println("episode details" + episode);
	                System.out.println("title"+title);
	                System.out.println("image Link"+imgLink);
	                System.out.println("next page link"+ nextPageLink +"-episode-1");
	                
	            }


	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		
	}
	 
    private String getUrl() {
		
		return url+getProperAnimeName(animeName);
		
		
	}
	
    private String getProperAnimeName(String text) {
		    
		String[] s=text.split("\\s");   
		String tmp="";
	    for(String temp:s){	       
	    	if(tmp.length()>0)	        
	    		tmp=tmp + "+" +temp;	            
	    	else	        
	    		tmp=temp;
	        } 
	    return tmp;
	}
	
     private void createJSON() throws JSONException {
	    	 
	    	 JSONArray array = new JSONArray();
	    	 for(AnimeModel ls: list) {
	        	 JSONObject item= new JSONObject();
	       			 item.put("title", ls.getTitle());    		 
	    			 item.put("thumbnail", ls.getThumbnail());    		 
	    			 item.put("latest episode", ls.getEpisode());
	    			 item.put("url", ls.getNextPageUrl());
	    			 array.put(item);
	    	 }
	    	 
	    	 json.put("Searched Animes", array);
	     }

    @Override
    public JSONObject getAnimes() throws UnknownHostException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public JSONObject getEpisodes(String animeUrl) throws UnknownHostException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JSONObject getSearchedAnime(String animeName) {

        this.animeName=animeName;
    	startScraping();
    	
    	try {
            createJSON();
        }   catch (JSONException e) {
			// TODO Auto-generated catch block
            e.printStackTrace();
	}
    	
    	return json;
    }
    
    

}
