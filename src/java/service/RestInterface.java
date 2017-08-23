/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.ArrayList;

/**
 *
 * @author jean-gustave
 */
public interface RestInterface<T> {
    public T get(String url);
    public ArrayList<T> getAll(String url) throws Exception;
    public boolean add(T item, String url) throws Exception;
    public boolean delete(T item, String url);
    
    
}
