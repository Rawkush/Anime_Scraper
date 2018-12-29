/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gecdeveloper.scraper.nineanime;
import com.gecdeveloper.scraper.AnimeFetch;
import com.gecdeveloper.scraper.model.AnimeModel;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private final String mainPageUrl="https://www2.9anime.to/";
    private JSONObject  json;
    public RecentlyUpdated(){ 
        json= new JSONObject();
     }

    private void startScraping() throws JSONException{
        
        try {
            Document doc = Jsoup.connect(mainPageUrl).get();
            Elements element= doc.select("div.widget-body");
            Elements all= element.select("div[data-name=\"all\"]").select("div.item");
            Elements sub= element.select("div[data-name=\"sub\"]").select("div.item");
            Elements dub= element.select("div[data-name=\"dub\"]").select("div.item");
            Elements trending= element.select("div[data-name=\"dub\"]").select("div.item");
            
            createJson(all,"all");
            createJson(sub,"sub");
            createJson(dub,"dub");
            createJson(trending,"trending");
        
        } catch (IOException ex) {
            Logger.getLogger(RecentlyUpdated.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
    } 

    public void createJson(Elements elements,String type) throws JSONException{
        
        JSONArray array = new JSONArray();

        for(Element element:elements){
            Element e=element.select("a[href]").first();
            String link=e.attr("href");
            String thumb=e.select("img").first().attr("src");
            String episode=e.select("div.ep").text();
            String typ= findType(e.select("div.status").text());
            String title=e.select("img").attr("alt");
            System.out.print("\n\nhahah");
             JSONObject item= new JSONObject();
       			 item.put("title", title);    		 
    			 item.put("type", typ);
                         item.put("thumbnail", thumb);    		 
    			 item.put("latest episode", episode);
    			 item.put("url", link);
            array.put(item);
        }
        json.put(type, array);
        
        
    }
    
    
    public String findType(String s){
        if(s.startsWith("Ep")){
            return "sub";
        }else
        {
            String tmp[]=s.split(" ");
            return tmp[0];
            
        }
    }
    
    @Override
    public JSONObject getAnimes() throws UnknownHostException {
        try {
            startScraping();
        } catch (JSONException ex) {
            Logger.getLogger(RecentlyUpdated.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;    
    }

    @Override
    public JSONObject getEpisodes(String animeUrl) throws UnknownHostException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JSONObject getSearchedAnime(String animeName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
    
    
}
