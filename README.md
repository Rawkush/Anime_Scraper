# Anime Scraper:-
   
Anime is a library that provides unofficial api to scrape the gogoanime, nine anime website. This library scrapes the gogoanime  and nine anime Website and returns the data as JSON Object.

#### java library is can be used by downloading and importing it into your application, however you can use our api made from python also

# Features Provided by Python api
 - List of REcently updated Animes
 - Searching the animes

# Features Provided by java Lib

 - List of Recently updated Animes
 - List of Popular Ongoing Animes
 - Searching the Animes
 
 # How to Use java lib
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
animes.getServers(url); // url is a String
```

##### For  complete code example checkout the demo.java file in the repository

## Screenshot of JSON
![enter image description here](https://user-images.githubusercontent.com/25636146/48275506-a83cf180-e46b-11e8-9263-52fcba01b560.png)

## Library Used
- Jsoup
- java-JSON 

# For Python API

Make the post request on the given url, the data post should be in JSON

  ```url = 'https://cbpgeca.herokuapp.com/' ```
and in that JSON intent key whould be present which specifies what api to call, example of json given below

### For recently updated anime
```
   {
      "intent":"new"
   }
```   
### For searching anime
```
   {
      "intent":"search"
      "anime": "naruto shippuden"
   }

```

### Get details of specific anime

```
   {
      "intent":"getAnime"
      "url":"http//:gogoanime.com/naruto"
   }
  
```

### Get video link
```
   {
      "intent":"episode"
      "url": "http//:www.gogoanime.com/naruto/"
      "episode_number":"13"
   }

```
# Note:

The above key values are required for the api to work, and all requests are handled by the same base url, for different functionality just the data posted will be changed as suggested above


# Projects Using GogoScraper
- [RawAnime](https://github.com/Rawkush/RawAnime)

# Contribution 

Want to contribute? Great!

Fork your own copy of this repository, make changes, update, fix bugs, add additional features or just prettify the code and create a pull request explaining what have you added fixed or improved so that we can merge it to our branch.




This library is being developed to be used in [RawAnime ](https://github.com/Rawkush/RawAnime)  Andriod application, but feel free to use it in your project. 

This Library is created for education purpose only and we shall not be responsible for its misuse.

#### If you are using our library please do tell us so we can add your project link.


####  Note(INCOMPLETE)

Note due to some reason Api for RawAnime is made from new and private, this repo does not scrapes video link right now, meanwhile this repo is left incomplete, althogh if you want to extend it you are well come to do so.

## Arigatou Gozaimasu
