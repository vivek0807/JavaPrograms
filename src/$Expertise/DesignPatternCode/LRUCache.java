package $Expertise.DesignPatternCode;


import java.util.*;

class LRUManager{

    LinkedHashMap<Integer,Integer> linkedHashMap;
    int size;
    LRUManager(int capacity){
        this.linkedHashMap=new LinkedHashMap<>();
        this.size=capacity;
    }

    void put(int key, int value){
        if (linkedHashMap.size()<=size){
            linkedHashMap.putFirst(key,value);
        }
        else
        {
            linkedHashMap.remove(linkedHashMap.lastEntry().getKey());
            linkedHashMap.putFirst(key, value);
        }
    }

    public int  get(int key){

        if (linkedHashMap.containsKey(key))
        { linkedHashMap.putFirst(key,linkedHashMap.get(key));
            return linkedHashMap.get(key);
        }
        else
            return -1;

    }

    void printLRU(){
        System.out.println(linkedHashMap);
    }
}

public class LRUCache {

    public static void main(String[] args) {

       LRUManager lruManager= new LRUManager(2);
        lruManager.put(1,1);
        lruManager.put(2,2);
        System.out.println(lruManager.get(1));
        lruManager.put(3,3);
        lruManager.get(2);
        lruManager.put(4,4);
        System.out.println(lruManager.get(1));
        System.out.println(lruManager.get(3));
        System.out.println(lruManager.get(4));
        lruManager.printLRU();

    }


}
