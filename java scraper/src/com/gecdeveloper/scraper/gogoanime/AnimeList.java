/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gecdeveloper.scraper.gogoanime;

import com.gecdeveloper.scraper.AnimeFetch;
import java.net.UnknownHostException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.print.Doc;
import java.util.ArrayList;
/**
 *
 * @author Ankush
 */
public class AnimeList implements AnimeFetch{
    
    final String baseurl="https://www05.gogoanimes.tv/anime-list.html?page=";
   private  ArrayList<AnimeListModel>  listofanime;
   private JSONObject jsonObject;
   public AnimeList()
   {
       listofanime=new ArrayList<>();
       jsonObject=new JSONObject();

   }
   private  void startScraping()
   {
       try
       {
           int count=1;
           do {
               Document page= Jsoup.connect(baseurl+count).get();
               Elements animenames=page.select("div[class=anime_list_body]").select("ul[class=listing]").select("li");
               if(animenames.size()==0||animenames==null)
                   break;
               for(Element currentanime :animenames)
               {
                    AnimeListModel currentanimemodel=new AnimeListModel();
                    Document animepage=Jsoup.connect(currentanime.select("a").attr("abs:href")).get();
                    Element anime_info_body=animepage.selectFirst("div[class=anime_info_body_bg]");
                    currentanimemodel.setName(anime_info_body.select("h1").html());
                    Elements paragraph=anime_info_body.select("p[class=type]");
                    currentanimemodel.setType(paragraph.eq(0).select("a").html());
                    String incorrectplotsummary=paragraph.eq(1).html();

                    int spamendindex=incorrectplotsummary.lastIndexOf(">");
                    currentanimemodel.setPlotSummary(incorrectplotsummary.substring(spamendindex+1,incorrectplotsummary.length()));
                    Elements gettingGenre=paragraph.eq(2).select("a");
                    String genre="";
                    for(Element individualGenre :gettingGenre)
                    {
                        genre=genre+individualGenre.html();
                    }
                    currentanimemodel.setGenre(genre);
                    String incorrectReleased=paragraph.eq(3).html();
                    spamendindex=incorrectReleased.lastIndexOf(">");
                    currentanimemodel.setReleased(incorrectReleased.substring(spamendindex+1));
                    String incorrectStatus=paragraph.eq(4).html();
                    spamendindex=incorrectStatus.lastIndexOf(">");
                    currentanimemodel.setStatus(incorrectStatus.substring(spamendindex+1));
                    listofanime.add(currentanimemodel);

               }

        count++;
           }while (true);

       }
       catch (Exception e)
       {
           e.printStackTrace();
       }
   }
 
    private void createJSON() throws JSONException {

        JSONArray array = new JSONArray();
        for(AnimeListModel ls: listofanime) {
            JSONObject item= new JSONObject();
            item.put("Name", ls.getName());
            item.put("Type", ls.getType());
            item.put("Plot Summary",ls.getPlotSummary());
            item.put("Genre",ls.getGenre());
            item.put("Released",ls.getReleased());
            item.put("Status",ls.getStatus());
            array.put(item);
        }

        jsonObject.put("Anime", array);
    }

    @Override
    public JSONObject getAnimes() throws UnknownHostException{

        startScraping();

        try {
            createJSON();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject;
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
