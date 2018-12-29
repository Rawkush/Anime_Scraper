/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gecdeveloper.scraper;

import com.gecdeveloper.scraper.nineanime.RecentlyUpdated;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ankush
 */
public class Testing {
    
    public static void main(String[] args){
        RecentlyUpdated s= new RecentlyUpdated();
        try {
            System.out.print(""+s.getAnimes());
        } catch (UnknownHostException ex) {
            Logger.getLogger(Testing.class.getName()).log(Level.SEVERE, null, ex);
            //cannot access the site checkc your internet connection 
        }
        catch(UnsupportedOperationException e){
            
        }
        
        
    }
    
}
