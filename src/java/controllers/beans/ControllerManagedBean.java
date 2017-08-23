/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.beans;

import java.util.ArrayList;

/**
 *
 * @author jean-gustave
 */
public abstract class ControllerManagedBean<T> {
    private T itemBean;
    private ArrayList<T> itemsBeans;

    public T getItemBean() {
        return itemBean;
    }

    public void setItemBean(T itemBean) {
        this.itemBean = itemBean;
    }

    public ArrayList<T> getItemsBeans() {
        return itemsBeans;
    }

    public void setItemsBeans(ArrayList<T> itemsBeans) {
        this.itemsBeans = itemsBeans;
    }
    
    public abstract ArrayList<T> getAll() throws Exception;
    
    public abstract boolean add(T item) throws Exception;
    public abstract boolean delete(T item);
    public abstract T get(int id);
    public abstract void edit(T item);
    public abstract String redirectToCreatePage();
    public abstract String redirectToListPage();
    
}
