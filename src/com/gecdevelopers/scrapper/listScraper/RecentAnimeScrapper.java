package com.gecdevelopers.scrapper.listScraper;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gecdevelopers.scrapper.Models.AnimeModel;
public class RecentAnimeScrapper {
	
	
	 private ArrayList<AnimeModel> list;
     private final String mainPageUrl="https://www4.gogoanime.se";

	 
	 public RecentAnimeScrapper(){
	        list= new ArrayList<>();  
	 }
	 
	 
    
     public void startScraping() {

         try {
             Document doc = Jsoup.connect(mainPageUrl).get();
             Elements container = doc.select("div.last_episodes.loaddub");
             Elements container2 = container.select("ul.items");
             Elements dataContainer = container2.select("li");
             for (Element element : dataContainer) {
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
                 
                 list.add(new AnimeModel(episode, imgLink, title, nextPageLink));
             }


         } catch (IOException e) {
             e.printStackTrace();
         }
         
         
     }


     public ArrayList<AnimeModel> getList() {
         return list;
     }

	

}
