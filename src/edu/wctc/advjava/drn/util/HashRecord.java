/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.advjava.drn.util;

import java.util.HashMap;

/**
 *
 * @author Dan
 */
public class HashRecord extends HashMap<String, String> implements Record {

    private String title;
    
    public HashRecord() {}

    public HashRecord(String title) {
        // No validation - see setTitle() below
        this.title = title;
    }
    
    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        // No validation - allow null and empty titles
        this.title = title;
    }
    
}
