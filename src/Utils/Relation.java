package Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Roi on 1/19/2015.
 */
public class Relation <I,J,K> {

    Map<I, Map<J, K>> map;

    public Relation(){
        this.map  = new HashMap<I, Map<J, K>>();
    }

    public void setValue(I key1, J key2, K value){
        Map<J, K> jkMap = this.map.get(key1);
        if (jkMap == null){
            jkMap = new HashMap<J, K>();
            this.map.put(key1, jkMap);
        }
        jkMap.put(key2, value);
    }

    public Set<I> getAllFirstKeys(){
        return map.keySet();
    }

    public K getValue(I key1, J key2){
        Map<J, K> jkMap = map.get(key1);
        if (jkMap == null){
            return null;
        }
        return jkMap.get(key2);
    }

    public Set<J> getSecondKeys(I key1){
        return map.get(key1).keySet();
    }

}
