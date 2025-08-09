package $Expertise.DesignPatternCode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeMap;

class LFUCacheNode{
    int val;
    int key;
    int count;

    public LFUCacheNode(int val, int key, int count) {
        this.val = val;
        this.key = key;
        this.count = count;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
class LFUCacheManager{
    HashMap<Integer, LFUCacheNode> keyValueMap;
    TreeMap<Integer, LinkedList<LFUCacheNode>> frequencyTreeMap;
    int capacity;
    int currentcapacity;
    LFUCacheManager(int capacity){
        this.capacity= capacity;
        keyValueMap= new HashMap<>();
        frequencyTreeMap= new TreeMap<>();
        currentcapacity=0;
    }

    public void setValue(int key,int value){
        if (!keyValueMap.containsKey(key)){
            LFUCacheNode cacheNode = new LFUCacheNode(value,key,1);
            if (frequencyTreeMap.get(1)==null){
                LinkedList<LFUCacheNode> lfuCacheNodes= new LinkedList<>();
                lfuCacheNodes.add(cacheNode);
                frequencyTreeMap.put(1, lfuCacheNodes);
                capacity++;
            }
            else
            {
                LinkedList<LFUCacheNode> currentList=frequencyTreeMap.get(1);
                if (capacity==currentcapacity){
                    currentList.removeFirst();
                    currentList.addLast(new LFUCacheNode(value,key,1));
                }
                if (capacity<currentcapacity){
                    currentList.addLast(new LFUCacheNode(value,key,1));
                }
                frequencyTreeMap.put(1,currentList);
            }
        }
        else {
            int currentFrequency=keyValueMap.get(key).getCount();
            LinkedList<LFUCacheNode> lfuCacheNodeLinkedList = frequencyTreeMap.get(currentFrequency);
            for (int i = 0; i < lfuCacheNodeLinkedList.size(); i++) {
                    LFUCacheNode lfuCacheNode=lfuCacheNodeLinkedList.get(i);
                    lfuCacheNodeLinkedList.remove(i);
                    lfuCacheNode.setCount(lfuCacheNode.getCount()+1);
                    if (frequencyTreeMap.containsKey(lfuCacheNode.getCount())){
                        LinkedList<LFUCacheNode> newFrequencyNode= frequencyTreeMap.get(lfuCacheNode.getCount());
                        newFrequencyNode.addLast(lfuCacheNode);
                        frequencyTreeMap.put(lfuCacheNode.getCount(), newFrequencyNode);
                        break;
                    }
                    else {
                        LinkedList<LFUCacheNode> lfuCacheNodes= new LinkedList<>();
                        lfuCacheNodes.add(lfuCacheNode);
                          frequencyTreeMap.put(lfuCacheNode.getCount(),lfuCacheNodes);
                    }

            }
        }
    }
}

/**
 * <h1> Design LFU Cache</h1>
 * <h3> This is an enhancement over LRU cache where the eviction takes place on basis of count and then least recently used</h3>
 * <h3>Approach</h3>
 * <li>We will have a node that will keep KEY,VALUE AND CURRENT COUNT OF USAGE[FREQUENCY]</li>
 * <LI>We will have two Maps 1. Map <"key,Node>, Map\<"Frequency,Node"></LI>
 * <li>Each time we add an element
 * <ul>We first check if the key is existing using the map</ul>
 * <ul>If yes we find the node in the LL with respect to the frequency from the map, we then update the frequency of the node and set a new frequency key in the second map</ul>
 * <ul>If the new frequency key is already present then we aad this new node to last</ul>
 * <ul>we remove the node the from the older frequency Key Map, if the LL size gets 0, we remove the entire key from the freqMap</ul>
 * </li>
 * <li>While getting the Node
 * <ul>We check in the map first, if not present just return -1</ul></li>
 * <ul>if present we find the frequency, find the node, update the frequency there and map it with a new frequency</ul>
 *
 */
public class LFUCache {
    public static void main(String[] args) {

    }
}
