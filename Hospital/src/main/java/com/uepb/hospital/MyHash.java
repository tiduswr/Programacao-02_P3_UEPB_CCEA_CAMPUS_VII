package com.uepb.hospital;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class MyHash<T, E> implements Serializable{
    HashMap<T, E> db;
    
    public MyHash(){
        db = new HashMap<>();
    }
    
    public boolean add(T key, E arg){
        if(!db.containsKey(key)){
            db.put(key, arg);
            return true;
        }else{
            return false;
        }
    }
    public boolean remove(T arg){
        if(db.containsKey(arg)){
            db.remove(arg);
            return true;
        }else{
            return false;
        }
    }
    public E get(T arg){
        if(db.containsKey(arg)){
            return db.get(arg);
        }else{
            return null;
        }
    }
    public boolean contains(T arg){
        return db.containsKey(arg);
    }
    public ArrayList<E> getArrayList(){
        return new ArrayList<>(db.values());
    }
    public int size(){
        return db.size();
    }
}
