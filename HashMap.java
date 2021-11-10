import java.util.*;

public class HashMap<K extends Comparable<K>, V> implements Map<K,V> {

    private long			getLoops;
    private long			putLoops;
    private int       tableSize;

    private List< List<Entry<K,V>> > 	table;
    private int			count;

    public HashMap() {
        tableSize = 1000003; // prime number
        table = new ArrayList<List<Entry<K,V>>>(tableSize);
        // initializes table as a list of empty lists
        for (int i = 0; i < tableSize; i++)
            table.add(new LinkedList<Entry<K,V>>());
        count = 0;

        resetGetLoops();
        resetPutLoops();
    }

    public long getGetLoopCount() {
        return getLoops;
    }

    public long getPutLoopCount() {
        return putLoops;
    }

    public void resetGetLoops() {
        getLoops = 0;
    }
    public void resetPutLoops() {
        putLoops = 0;
    }

    /*
     * Purpose:
     *     Check to see if key is stored in the map.
     *
     * Pre-conditions:
     *    None.
     *
     * Returns:
     *     true if key is in the map, false otherwise.
     *
     */
    public boolean containsKey(K key) {
        // gets the index in the table this key should be in
        int index = Math.abs(key.hashCode()) % tableSize;
        // TODO... complete this method
        // Tip: use Java's List and Iterator operations to go through a chain at a specific index
        if(table.get(index)!= null)
        {
            List<Entry <K, V>> temp = table.get(index);
            Iterator<Entry<K, V>> search;
            search = temp.iterator();
            return containsKeyHelper(key, search);
            
        }else
        {
            return false;
        }

        
    }
    private boolean containsKeyHelper(K key, Iterator<Entry<K,V>> search)
    {
        if(search.hasNext())
        {
            
            if(search.next().getKey().equals(key))
            {
                return true;
            }
            else
            {
                
                return containsKeyHelper(key, search);
            }
        }
        return false;
    }

     /*
     * Purpose:
     *     Return the value stored at key in the map
     *
     * Pre-conditions:
     *     None.
     *
     * Returns:
     *    the value stored at key.
     *
     * Throws:
     *    KeyNotFoundException if key is not in the map.
     *
     */
    public V get (K key) throws KeyNotFoundException {
        // gets the index in the table this key should be in
        int index = Math.abs(key.hashCode()) % tableSize;
        // TODO... complete this method
        // Tip: use Java's List and Iterator operations to go through a chain at a specific index
        if(table.get(index) != null)
        {
            List<Entry<K, V>> sheet = table.get(index);
            Iterator<Entry<K, V>> search;
            search = sheet.iterator();
            return getHelper(key, search);
            
        }
        else
        {
            throw new KeyNotFoundException();
        }
        
    
        

    }
private V getHelper(K key, Iterator<Entry<K, V>> temp) throws KeyNotFoundException
{
    if(temp.hasNext())
    {
        Entry<K,V> value = temp.next();
        if(value.getKey().equals(key))
        {
            return value.getValue();
        }
    }
    throw new KeyNotFoundException();
}
/*
     * Purpose:
     *     Return a List of Entry types which contain the
     *    key/value pairs of every entry in the map.
     *
     * Pre-conditions:
     *     None.
     *
     * Returns:
     *    An instance of List with all the key/value pairs in
     *    the map.
     */
    public List<Entry<K,V> >	entryList() {
        List <Entry<K,V>> l = new LinkedList<>();
        // TODO... complete this method
        // Tip: use Java's List and Iterator operations to go through every index in the table
        //      use a second Iterator to go through each element in a chain at a specific index
        //      and add each element to l
        int counter = 0;
        while(counter < tableSize)
        {
            List<Entry<K, V>> Sheet = table.get(counter);
            Iterator<Entry<K, V>> temp = Sheet.iterator();
            
                while(temp.hasNext() == true)
                {
                    Entry<K, V> value = temp.next();
                    
                    l.add(value);
                }
            
            
            counter++;
        }

        return l;
    }

    /*
     * Purpose:
     *    Insert the key/value pair into the map.
     *    If the key already exists in the map, instead
     *    update the entry to include the new value.
     *
     * Pre-conditions:
     *    None.
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
    public void put (K key, V value){
        // gets the index in the table this key should be in
        int index = Math.abs(key.hashCode())%tableSize;
        // TODO... complete this method
        // Tip: use Java's List and Iterator operations to go through a chain at a specific index
        //  if key is found, update value.  if key is not found add a new Entry with key,value
        // The tester expects that you will add the newest Entry to the END of the list
        Entry<K,V> newVal = new Entry<> (key,value);
        int loop = 0;
        
        List<Entry<K, V>>sheet = table.get(index);
        Iterator<Entry<K,V>> temp = sheet.iterator();
        
        
        
            if(sheet.isEmpty() != true)
            {
                if(sheet.get(0).getKey().equals(key))
            {
                sheet.remove(0);
                sheet.add(0, newVal);
                count = count;
                return;
                
            }
                else
                {
                    while(temp.hasNext())
                {
                    
                    
                    if(!temp.next().getKey().equals(key))
                    {
                        loop++;
                    }
                    else
                    {
                        
                        sheet.remove(loop);
                        sheet.add(loop, newVal);
                        loop++;
                        count++;
                        return;
                    }
                    
                }
                }
                
                count = count + 1;
                sheet.add(newVal);
                return;
                
            }
            if(sheet.isEmpty() == true)
            {
                sheet.add(newVal);
                count++;
                return;
            }
            
            
            
            
                
            
            
        


    }

    public int size() {
        return count;
    }

    public void clear() {
        table.clear();
        count = 0;
    }

}
