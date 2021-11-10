import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BSTMap<K extends Comparable<K>, V > implements  Map<K, V>  {

    BinarySearchTree<K,V> bst;

    public BSTMap () {
        bst = new BinarySearchTree<>();
    }
    
    public long getGetLoopCount()
    {
        return bst.getFindLoopCount();
    }
    public long getPutLoopCount()
    {
        return bst.getInsertLoopCount();
    }

    public void resetGetLoops()
    {
        bst.resetFindLoops();
    }
    public void resetPutLoops()
    {
        bst.resetInsertLoops();
    }
    
    /*
     * Purpose:
     *     Check to see if key is stored in the map.
     *
     * Parameters:
     *     K key
     *
     * Pre-conditions:
     *    None.
     *
     * Returns:
     *     boolean - true if key is in the map, false otherwise.
     *
     */
    public boolean containsKey(K key) {

        try {
            if(bst != null)
            {
                if(bst.find(key) != null)
                {
                    return true;
                }
            }
            return false;
                
            
        } catch (KeyNotFoundException ex) {
            
            
            return false;
        }
        
        
    }
    /*
     * Purpose:
     *     Return the value stored at key in the map
     *
     * Parameters:
     *     K key
     *
     * Pre-conditions:
     *     None.
     *
     * Returns:
     *    V - the value stored at key.
     *
     * Throws:
     *    KeyNotFoundException if key is not in the map.
     *
     */
    public V get (K key) throws KeyNotFoundException {
        try
        {
            V value = bst.find(key);
            return value;
            
        }
        catch(KeyNotFoundException ex)
        {
            
            throw new KeyNotFoundException();
        }
        
    }
    /*
     * Purpose:
     *     Return a List of Entry types which contain the
     *    key/value pairs of every entry in the map.
     *
     * Parameters:
     *     none
     *
     * Pre-conditions:
     *     None.
      *
     * Returns:
     *    List<Entry<K,V> > - List with all the key/value pairs in the map.
     */
    public List<Entry<K,V> >    entryList() {
        // you are free to choose your traversal order here
        return bst.entryList();
    }
    /*
     * Purpose:
     *    Insert the key/value pair into the map.
     *    If the key already exists in the map, instead
     *    update the entry to include the new value.
     *
     * Parameters:
     *     K key, V value
     *
     * Pre-conditions:
     *    None.
     *
     * Returns:
     *    void
     *
     * Examples:
     *    if m is {("hello", 5)} and m.put("joe",8") is called
     *    then m is: {("hello", 5), ("joe",8)}
     *
     *    if m is {("hello", 5)} and m.put("hello", 99) is called
     *    then m is {("hello", 99)}
     *
     *    NOTE:     Maps do not provide ordering, so these examples
     *         have chosen an arbitrary ordering.  Your implementation
     *        may store items in a different order than the examples.
     */
    public void put (K key, V value) {
        
            bst.insert(key, value);
        
    }

    public int size() {
        int size = bst.size();
        return size;
    }

    public void clear() {
        bst.clear();
    }

}
