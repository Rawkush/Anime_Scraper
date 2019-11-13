/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gecdeveloper.scraper;

import java.net.UnknownHostException;
import org.json.JSONObject;

/**
 *
 * @author Ankush
 */
public interface AnimeFetch {
    
    JSONObject getAnimes() throws UnknownHostException;
    JSONObject getEpisodes(String animeUrl) throws UnknownHostException;
    JSONObject getSearchedAnime(String animeName);

    
}
