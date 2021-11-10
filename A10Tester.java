//
// A10Tester.java
//
import java.util.*;

public class A10Tester {
    static boolean testLinked = false;
    static boolean testHash = false;
    static boolean testBST = false;


    private static int testPassCount = 0;
    private static int testCount = 0;

    static int hash1_to_add = 15;
    static String hash1_keys[] = { "blue","dog","icecream","hockey","house","car","cry","apple","baseball","apple","school","red","why","dog","street"};
    static int hash1_values[] = { 18,17,32,41,28,18,18,9,7,41,40,37,8,9,10};
    static int hash1_size = 13;
    static String    hash1_keys_order[] = {"blue","apple","baseball","car","cry","dog", "red", "why", "house", "icecream","hockey","school","street"};
    static int     hash1_values_order[] = {18,41,7,18,18,9,37,8,28,32,41,40,10};


    static int hash2_to_add = 14;
    static String hash2_keys[] = { "run","red","bird","bannana","blue","orange","street","icecream","yellow","hockey","football","bird","football","football"};
    static int hash2_values[] = { 29,11,6,28,13,31,6,6,40,25,1,30,5,0};
    static int hash2_size = 11;
    static String hash2_keys_order[] = {"bird","blue","red","run","yellow","icecream","football","orange","bannana","hockey","street"};
    static int hash2_values_order[] = {30,13,11,29,40,6,0,31,28,25,6};





    public static void displayResults (boolean passed, String testName) {
        /* There is some magic going on here getting the line number
         * Borrowed from:
         * http://blog.taragana.com/index.php/archive/core-java-how-to-get-java-source-code-line-number-file-name-in-code/
         */
        testCount++;
        if (passed)
        {
            System.out.println ("Passed test: " + testCount);
            testPassCount++;
        }
        else
        {
            System.out.println ("Failed test: " + testName + " at line "
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    public static void addKeyValues (HashMap<String, Integer> hm, String keys[], int[] values, int count){
        for (int i = 0; i < count; i++)
            hm.put(keys[i], values[i]);
    }

    public static void hashmapEntryTest1() {
        System.out.println("**** begin hashmapEntryTest1");
        HashMap<String, Integer> hm = new HashMap<String, Integer> ();
        addKeyValues(hm,hash1_keys, hash1_values, hash1_to_add);


        List <Entry<String,Integer> > l = hm.entryList();
        compareLists (hash1_keys_order, hash1_values_order, hash1_size, l);
        displayResults(hm.size() == hash1_size, "number of entries");

        // not passing: use these debug print statments to compare lists
        System.out.println("l entrylist: " + l);
        for(int i=0; i<hash1_size; i++) {
            System.out.println("(" + hash1_keys_order[i] + "," + hash1_values_order[i] + ")");
        }

        System.out.println("****** end hashmapEntryTest1\n");

    }

    public static void hashmapEntryTest2() {
        System.out.println("**** begin hashmapEntryTest2");
        HashMap<String, Integer> hm = new HashMap<String, Integer> ();
        addKeyValues(hm,hash2_keys, hash2_values, hash2_to_add);

        List <Entry<String,Integer> > l = hm.entryList();
        compareLists (hash2_keys_order, hash2_values_order, hash2_size, l);
        displayResults(hm.size() == hash2_size, "number of entries");

        // not passing: use these debug print statments to compare lists
        // System.out.println("l entrylist: " + l);
        // for(int i=0; i<hash1_size; i++) {
        //     System.out.println("(" + hash1_keys_order[i] + "," + hash1_values_order[i] + ")");
        // }

        System.out.println("****** end hashmapEntryTest2\n");

    }

    public static void compareLists (String[] key_expected, int[] value_expected, int count, List<Entry<String, Integer> > l) {
        displayResults( count == l.size(), "comparing lists, size");

        Iterator<Entry<String,Integer> > i = l.iterator();
        int pos = 0;
        boolean passed = true;

        while (passed && i.hasNext()) {
            Entry<String,Integer> e = i.next();

            if (!e.getKey().equals(key_expected[pos]) || !e.getValue().equals(value_expected[pos])) {
                // System.out.println(e + ":" + key_expected[pos] + "," + value_expected[pos]);
                passed = false;
            }
            pos++;
        }
        displayResults(passed, "end of comparing lists");
    }

    public static void map1Test() {
        System.out.println("**** begin test map size put get");
        Map<String, Integer> d = A10Tester.<String,Integer>createMap();

        displayResults(d.size() == 0 ,"size");
        d.put("abc", 10);

        displayResults(d.size() == 1,"put + size");
        try
        {
            int val = d.get("abc");

            displayResults(val == 10,"put + get");
        }
        catch (KeyNotFoundException e)
        {
            displayResults(false, "put + get");
        }

        d.put("abc", 15);

        displayResults(d.size() == 1,"put update + size");

        try
        {
            int val = d.get("abc");

            displayResults(val == 15,"put + get");
        }
        catch (KeyNotFoundException e)
        {
            displayResults(false, "put + get");
        }

        try
        {
            int val = d.get("def");
            displayResults(false, "put + get should not be there");
            val = val + 1;

        }
        catch (KeyNotFoundException e)
        {
            displayResults(true, "put + get should not be there");
        }
        System.out.println("****** end map size put get\n");

    }

    public static void map2Test() {
        System.out.println("**** begin map stress test");
        Map<Integer, Integer> d = A10Tester.<Integer,Integer>createMap();

        for (int i = 0; i < 10000; i++) {
            d.put(i,i);
        }
        displayResults(d.size() == 10000, "put + size");

        boolean passed = true;
        int i = 0;
        int testSize = 10000;
        while (i<testSize && passed) {
            try {
                d.get(i);
                i++;
            }
            catch (KeyNotFoundException e) {
                passed = false;
            }
        }
        displayResults(passed, "get");
        System.out.println("****** end map stress test\n");
    }


    public static <K extends Comparable<K>,V> Map<K,V> createMap() {
        if (testLinked) {
            return new LinkedMap<K,V>();
        } else if (testHash) {
            return new HashMap<K,V>();
        } else {
            return new BSTMap<K,V>();
        }
    }
    public static void main (String[] args)    {
        testCount = 0;
        testPassCount = 0;

        if (args.length != 0 && args[0].equals("linked")) {
            testLinked = true;

        } else if (args.length != 0 && args[0].equals("bst")) {
            testBST = true;
        }
        else {
            testHash = true;
        }
        System.out.println("Testing " +
                           (testLinked ? "LinkedList" :
                            (testHash ? "HashTable" : "BinarySearchTree"))
                           + " implementation.");

        try {
            map1Test  ();
            map2Test  ();
            hashmapEntryTest1();
            hashmapEntryTest2();
        }
        catch (Exception e) {
            System.out.println("Unhandled exception in tester: " + e);
            e.printStackTrace();
        }

        System.out.println("Passed " + testPassCount + "/" + testCount + " tests");
     }
}
