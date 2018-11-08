package com.gecdevelopers.scrapper;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class PopularOnGoing {

    private final String  mainPageUrl="https://www4.gogoanime.se/";
    private final String pagedetails="page-recent-release-ongoing.html?page=";
    private ArrayList<AnimeModel> list;

	
	public PopularOnGoing() {
		list= new ArrayList<>();
	}
	
	
	
	public void startScraping() {
		
		getAllPages();
		
		
	}
	
	
	 private String getURL(String murl){

	        if(murl.contains("url")){
	            String[] temp=murl.split("url");
	           String[] strings= temp[1].split("'");
	           return strings[1];
	        }

	    return null;
	    }

	 public ArrayList<AnimeModel> getList() {
		return list;
	}
	 
	 
	private void getAllPages() {
        int pageNumber=0;
        try {
            Document doc = Jsoup.connect(mainPageUrl).get();
            Elements container = doc.select("div.pagination.recent");
            Elements pagesContainer= container.select("li");
            for(Element pages:pagesContainer){
                pageNumber++;
                extractPage(mainPageUrl+pagedetails+pageNumber);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

	}
	
	
	private void extractPage(String url) {

        try {
            Document doc = Jsoup.connect(url).get();
            Elements container = doc.select("div.added_series_body.popular");
            Elements dataContainer = container.select("li");

            for (Element element : dataContainer) {
                Elements dataCont=element.select("a[href]");
                Element data=dataCont.get(0);
                String title=dataCont.get(1).text();   // name of the anime
                String nextPageLink = data.attr("href");     
               // Log.d("RecentLinkk",data.attr("href"));
                Element img=data.select("div.thumbnail-popular").first();
                String temp=img.after("url").toString();
                String imgUrl=getURL(temp); // url of the thumbmail
                Elements episode = element.select("p");
                String latestEpisode=episode.get(1).text();  // episode number
               // Log.d("data3",""+episode.get(1).text());

                System.out.println("episode Details" + latestEpisode);
                System.out.println("image url "+ imgUrl);
                System.out.println("title "+title);
                System.out.println("next Page Link" +  nextPageLink+"-episode-1" );
                list.add(new AnimeModel(latestEpisode, imgUrl, title, nextPageLink+"-episode-1"));
/*
                Elements Episode = element.select("p.episode");
                String episode = Episode.text();
                Elements titles = element.select("p.name");
                String title = titles.text();
                Log.d("title", title);
                Elements img = element.select("div.img");
                Element links = img.select("a[href]").first(); // a with href
                String nextPageLink = links.attr("href");
                nextPageLink = url + nextPageLink;
                Element ImageLink = links.select("img").first();
                String imgLink = ImageLink.attr("src");
                Log.d("links", imgLink);
               // list.add(new AnimeModel(episode, imgLink, title, nextPageLink));
  */                
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

	}
	
	 
}
