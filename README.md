# GogoScraper:-
   
GoGoScraper is a java library that provides unofficial api to scrape the gogoanime website. This library scrapes the gogoanime Website and returns the data as JSON Object.

# Features Provided

 - List of Recently updated Animes
 - List of Popular Ongoing Animes
 - Searching the Animes
 - Every Episode link 
 - Different video servers for streaming
 
 # How to Use
 - Import this library to your project 

#### To  Get list of recently updated animes
```
Scraper animes= new Scraper();
animes.getRecentlyUpdated(); // returns JSON Object
  ``` 
   
  #### Popular Animes
  ```
animes.getPopularOngoing(); // returns JSON Object
```

#### Search Animes
```
animes.searchAnime(animeName); // animeName is a String
```

### Episodes

```
animes.getAllEpisodes(animeUrl); // animeUrl is a String
```

### Video link from different Servers

```
animes..getServers(url); // url is a String
```

##### For  complete code example checkout the demo.java file in the repository

# Screenshot of JSON
![enter image description here](https://user-images.githubusercontent.com/25636146/48275506-a83cf180-e46b-11e8-9263-52fcba01b560.png)

# Library Used
- Jsoup
- java-JSON 

# Projects Using GogoScraper
- [RawAnime](https://github.com/Rawkush/RawAnime)

# Contribution 

Want to contribute? Great!

Fork your own copy of this repository, make changes, update, fix bugs, add additional features or just prettify the code and create a pull request explaining what have you added fixed or improved so that we can merge it to our branch.


#  Note

This library is being developed to be used in [RawAnime ](https://github.com/Rawkush/RawAnime)  Andriod application, but feel free to use it in your project. 

This Library is created for education purpose only and we shall not be responsible for its misuse.

#### If you are using our library please do tell us so we can add your project link.

##### Note: If broken create the new issue I will try to fix it ASAP

## Arigatou Gozaimasu
