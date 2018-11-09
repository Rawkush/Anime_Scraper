import com.gecdevelopers.scrapper.Scraper;

public class demo {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hello");
		Scraper var= new Scraper();
		//var.scrapeForRecentAnimeList();
		var.scrapeForPopularOngoingAnimeList();
		String link= var.getPopularOngoindAnimeList().get(0).getNextPageUrl();
		System.out.println("\n\n\n\n\n "+link);
		var.scrapeEpisodeList(link);
		String lin=var.getEpisodeList().get(0).getEpisodeUrl();
		System.out.println("\n\n\n\n\n "+lin);
		var.startScrapingServers(lin);
		System.out.println(var.getServerList().get(2).getEpisodeUrl());

		
	}
}
