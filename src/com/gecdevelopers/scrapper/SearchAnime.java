package com.gecdevelopers.scrapper;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;


public class SearchAnime {

	public ArrayList<AnimeModel> list;
    private final String url="https://www4.gogoanime.se/search.html?keyword=";
    private String animeName;

	
	
    public SearchAnime(String animeName) {
		// TODO Auto-generated constructor stub
	   list= new ArrayList<>();
	   this.animeName=animeName;
    }

	
	public void startScraping() {
		
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
	
	public ArrayList<AnimeModel> getList() {
			return list;
		
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
	
	
}
