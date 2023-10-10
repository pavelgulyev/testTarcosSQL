package com.example.demo1.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChangesCollection implements Aggregate{
    public Map<Integer, Changes> requirementsCollection = new HashMap<Integer, Changes>();
    public static int key=0;
    public ChangesCollection(List<Changes> changes){
        for(int i=1; i<=changes.size(); i++)
        {
            this.requirementsCollection.put(i, changes.get(i));
        }

    }
    public void AddRequariments(Changes requirements){
        requirementsCollection.put(key, requirements);
        key++;
    }
    private class RequirementsIterator implements Iterator {
        @Override
        public boolean hasNext() {
            return requirementsCollection.containsKey(key+=1);
        }

        @Override
        public Changes next() {
            if(this.hasNext()){
                return requirementsCollection.get(key);
            }
            else{
                key=0;
                return requirementsCollection.get(0);
            }
        }

        @Override
        public Object preview() {
            if(this.hasNext()){
                return requirementsCollection.get(key);
            }
            return null;
        }
    }
    @Override
    public Iterator getIterator() {
        return null;
    }
}
