/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gecdeveloper.scraper.nineanime;

import com.gecdeveloper.scraper.AnimeFetch;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class AnimeEpisode implements AnimeFetch {
    private JSONObject  json;

    public AnimeEpisode() {
        
        json= new JSONObject();

        try {
            startScraping();
        } catch (JSONException ex) {
            Logger.getLogger(AnimeEpisode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    private final String mainPageUrl="https://www2.9anime.to/watch/black-clover.v2k6";
    
    private void startScraping() throws JSONException{
     
        try {
            Document doc = Jsoup.connect(mainPageUrl).get();
            Element e= doc.getElementById("main");

            extractInfo(e.select("div.widget.info").first());
            
            System.out.print(""+doc);
            
        
        
        } catch (IOException ex) {
            Logger.getLogger(AnimeEpisode.class.getName()).log(Level.SEVERE, null, ex);
        }
            
     
    }
    
    //info about the anime
    private void extractInfo(Element element) throws JSONException{
        String desc=element.select("div.desc").text();
        Elements s=element.select("dl.meta.col-sm-12"); 
              
        for(Element e:s){
            
            Elements key= e.select("dt");
            Elements value= e.select("dd");
            try{
            for(int i=0;i<key.size();i++){
                
                json.put(key.get(i).text(), value.get(i).text());
            }
            }catch(ArrayIndexOutOfBoundsException ex){
                //error  catched
            }
        
        }
            
        //String type=element.select()
        json.put("desc", desc);
    
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
