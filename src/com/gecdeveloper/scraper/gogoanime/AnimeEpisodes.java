/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gecdeveloper.scraper.gogoanime;

import com.gecdeveloper.scraper.AnimeFetch;
import com.gecdeveloper.scraper.model.EpisodeData;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;

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
public class AnimeEpisodes implements AnimeFetch{
    
    
    final String baseUrl="https://www4.gogoanime.se";
    private ArrayList<EpisodeData> episodeData;
    private String animeUrl;
    int numberOfEpisodes;
    private JSONObject  json;

    public AnimeEpisodes() {
    	this.animeUrl="";
    	episodeData=new ArrayList<>();
    	numberOfEpisodes=0;
    	json= new JSONObject();
    }
    
	private void startScraping() {
	
		String url= urlCorrector(animeUrl);
		  try {
              Document doc = Jsoup.connect(url).get();

              Elements epRange= doc.select("div.anime_video_body").select("ul#episode_page").select("li");
              String lastEpisodeNumber="0";
              for(Element rng:epRange){
                  lastEpisodeNumber=rng.text();
              }
              if(lastEpisodeNumber.contains("-")){
                  String[] temp=lastEpisodeNumber.split("-");
                  lastEpisodeNumber=temp[1];
              }

              numberOfEpisodes= Integer.parseInt(lastEpisodeNumber);

          } catch (IOException e) {
              e.printStackTrace();
          }
		  
		  
		 animeUrl=getProperUrl(animeUrl);
          for(int i=1;i<=numberOfEpisodes;i++){
              if(animeUrl.contains("episode")){
                  episodeData.add(new EpisodeData("episode "+i,animeUrl+"-"+i));

              }else
              episodeData.add(new EpisodeData("episode "+i,animeUrl+"-episode-"+i));

          }

        //  Log.d("ashdb",episodesData.get(0).getEpisodeUrl());
          Collections.reverse(episodeData);
		
	}
	
	
	private String urlCorrector(String url) {
		String correctedUrl;
		if(url.contains("https"))
	        {
	            animeUrl=getGeneralUrl(url); // getting general url of the page
	            //Log.d("new", episodeUrl);
	            //task.execute(url);
	            correctedUrl=url;
	        }
	        else {
	            animeUrl = baseUrl + getProperUrl(url);
	          // Log.d("new", episodeUrl);
	          //  task.execute(episodeUrl);
	            correctedUrl=animeUrl;
	            animeUrl=getGeneralUrl(animeUrl);
	        
	        }
		 
		 return correctedUrl;
		 
	}

	private String getGeneralUrl(String murl){
		String[] temp=murl.split("-");     
		String URL=null;     
		for(int i=0;i<temp.length;i++){        
			if(i==0){            
				URL=temp[i];          
			}else            
				if(i!=temp.length-1){              
					URL=URL+"-"+temp[i];             
				}      
		}

	      if(!parseStrToInt(temp[temp.length-1])){
	          URL=URL+"-"+temp[temp.length-1];
	      }

	return URL;
	}	
	
	private  boolean parseStrToInt(String str) {
	        if (str.matches("\\d+")) {
	            return true;
	        } else {
	            return false;
	        }
	    }
 
	private  String getProperUrl(String url){
		 return url.replaceFirst("category/",""); 
	 }
	 
	private void createJSON() throws JSONException {
	    	 
	    	 JSONArray array = new JSONArray();
	    	 for(EpisodeData ls: episodeData) {
	        	 JSONObject item= new JSONObject();
	       			 item.put("episode", ls.getEpisode());    		 
	    			 item.put("url", ls.getEpisodeUrl());    		 
	    			 array.put(item);
	    	 }
	    	 
	    	 json.put("Episodes", array);
	     }

    @Override
    public JSONObject getAnimes() throws UnknownHostException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JSONObject getEpisodes(String animeUrl) throws UnknownHostException {

		this.animeUrl= animeUrl;
		
		startScraping();
		
		try {
			createJSON();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
    
    }

    @Override
    public JSONObject getSearchedAnime(String animeName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
