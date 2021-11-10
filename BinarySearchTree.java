import java.util.*;

//
// An implementation of a binary search tree.
//
// This tree stores both keys and values associated with those keys.
//
// More information about binary search trees can be found here:
//
// http://en.wikipedia.org/wiki/Binary_search_tree
//
// Note: Wikipedia is using a different definition of
//       depth and height than we are using.  Be sure
//       to read the comments in this file for the
//          height function.
//
public class BinarySearchTree <K extends Comparable<K>, V>  {

    public static final int BST_PREORDER  = 1;
    public static final int BST_POSTORDER = 2;
    public static final int BST_INORDER   = 3;

    // These are package friendly for the TreeView class
    BSTNode<K,V> root;
    int          count;

    long         findLoops;
    long         insertLoops;

    public BinarySearchTree () {
        root = null;
        count = 0;
        resetFindLoops();
        resetInsertLoops();
    }

    public long getFindLoopCount() {
        return findLoops;
    }

    public long getInsertLoopCount() {
        return insertLoops;
    }

    public void resetFindLoops() {
        findLoops = 0;
    }
    public void resetInsertLoops() {
        insertLoops = 0;
    }
/*
     * Purpose:
     *
     * Insert a new Key:Value Entry into the tree.  If the Key
     * already exists in the tree, update the value stored at
     * that node with the new value.
     *
     * Pre-Conditions:
     *     the tree is a valid binary search tree
    */
    public void insert (K k, V v) {
        if(root == null || count == 0)
        {
            root = new BSTNode<>(k,v);
            
            count++;
        }
        else
        {
            root = insertHelper(root, k, v);
        }

    }
    private BSTNode<K,V> insertHelper(BSTNode<K,V> temp, K mapNum, V v)
    {
        if(temp != null)
        {
            if(mapNum.compareTo(temp.key) == 0)
            {
                temp.setValue(v);
            }
            else if(mapNum.compareTo(temp.key) < 0)
            {
                temp.left = insertHelper(temp.left, mapNum, v);
            }
            else
            {
                temp.right = insertHelper(temp.right, mapNum, v);
            }
        }
        else
        {
            count++;
            
            return new BSTNode<>(mapNum, v);
            
        }
        
        return temp;
    }

    /*
     * Purpose:
     *
     * Return the value stored at key.  Throw a KeyNotFoundException
     * if the key isn't in the tree.
     *
     * Pre-conditions:
     *    the tree is a valid binary search tree and k is not null
     *
     * Returns:
     *    the value stored at key
     *
     * Throws:
     *    KeyNotFoundException if key isn't in the tree
     */
    public V find (K key) throws KeyNotFoundException {
        if(root == null)
        {
                    throw new KeyNotFoundException();

        }
        return findHelper(root, key);
        
    }
    private V findHelper(BSTNode<K, V> temp, K mapKey) throws KeyNotFoundException 
    {
        if(temp == null)
        {
            throw new KeyNotFoundException();
        }
        else
        {
            if(mapKey.compareTo(temp.key) == 0)
            {
                return temp.value;
            }
            else if(mapKey.compareTo(temp.key) >= 0)
            {
                return findHelper(temp.right, mapKey);
            }
            else
            {
                return findHelper(temp.left, mapKey);
            }
        }
        
    }

    /*
     * Purpose:
     *
     * Return the number of nodes in the tree.
     *
     * Returns:
     *    the number of nodes in the tree.
     */
    public int size() {
        return count;
    }

    /*
     * Purpose:
     *    Remove all nodes from the tree.
     */
    public void clear() {
        count = 0;
        root = null;
    }

    /*
     * Purpose:
     *
     * Return the height of the tree.  We define height
     * as being the number of nodes on the path from the root
     * to the deepest node.
     *
     * This means that a tree with one node has height 1.
     *
     * Examples:
     *    See the assignment PDF and the test program for
     *    examples of height.
     */
    public int height() {
        if(root == null)
        {
            return 0;
        }
        int heightLeft = 0;
        int heightRight =0;
        return heightHelper(root, heightLeft,heightRight);
    }
    public int heightHelper(BSTNode<K,V>temp, int heightL, int heightR)
    {
        if(temp == null)
        {
            return 0;
        }
        if(temp != null)
        {
            heightL = heightHelper(temp.left, heightL, heightR);
            heightR = heightHelper(temp.right, heightL, heightR);
        }
        if(heightL > heightR)return 1+heightL;
        else return 1+heightR;
    }


    /*
     * Purpose:
     *
     * Return a list of all the key/value Entrys stored in the tree
     * The list will be constructed by performing a level-order
     * traversal of the tree.  If the tree is empty, the returned list is empty.
     *
     * A level order traversal of a tree cannot be done without the help
     *  of a secondary data structure
     *
     * It is commonly implemented using a queue of nodes as the secondary
     *  data structure.
     *  You will still be adding the "visited" elements to l as you do in the other
     *  traversal methods but you will create an additional q to hold nodes still to visit
     *
     *  From wikipedia (they call it breadth-first), the algorithm for level order is:
     *
     *    levelorder()
     *        q = empty queue
     *        q.enqueue(root)
     *        while not q.empty do
     *            node := q.dequeue()
     *            visit(node)
     *            if node.left != null then
     *                  q.enqueue(node.left)
     *            if node.right != null then
     *                  q.enqueue(node.right)
     *
     * Note that you can use the Java LinkedList as a Queue
     *  and then use only the removeFirst() and addLast() methods on q
     *
     * Parameters: None
     *
     * Returns: List< Entry<K,V> > - list of all key/value Entrys in the tree
     *  in a levelorder traversal
     */
    public List< Entry<K,V> >  entryList() {
        List< Entry<K,V>> list = new LinkedList< Entry<K,V>>();  // hold traversal elements
        
        LinkedList<BSTNode<K,V>> temp = new LinkedList<BSTNode<K, V>>();
        
        if(root != null)
        {
            temp.add(root);
            return entryListHelper(temp, root, list);
        }
        
        return list;
    }
    private List<Entry<K,V> > entryListHelper(LinkedList<BSTNode<K,V>> temp, BSTNode<K, V> root,  List< Entry<K,V>> list)
    {
        if(temp.isEmpty() != true)
        {
            BSTNode<K, V> mem = temp.removeFirst();
            list.add(new Entry<>(mem.key, mem.value));
            
            if(mem.left != null)
            {
                temp.add(mem.left);
                
            }
            if(mem.right != null)
            {
                temp.add(mem.right);
            }
            return entryListHelper(temp, root, list);
        }
        return list;        
    }

    /*
     * Purpose:
     *
     * Return a list of all the key/value Entrys stored in the tree
     * The list will be constructed by performing a traversal
     * specified by the parameter which.
     *
     * If which is:
     *    BST_PREORDER    perform a pre-order traversal
     *    BST_POSTORDER    perform a post-order traversal
     *    BST_INORDER    perform an in-order traversal
     * Parameters: int which
     *
     * Precondition: which is one of BST_PREORDER, BST_POSTORDER or BST_INORDER
     *
     * Returns: List< Entry<K,V> > - list of all key/value Entrys in the tree
     */
    public List< Entry<K,V> >    entryList (int which) {
        List< Entry<K,V>> l = new LinkedList<  >();
        
        switch (which) {
            case 3 -> doInOrder(root, l);
            case 2 -> doPostOrder(root, l);
            case 1 -> doPreOrder(root, l);
            default -> {
            }
        }

        return l;
    }

    /*
     * Your instructor had the following private methods in their solution:
     * private void doInOrder (BSTNode<K,V> n, List <Entry<K,V> > l);
     * private void doPreOrder (BSTNode<K,V> n, List <Entry<K,V> > l);
     * private void doPostOrder (BSTNode<K,V> n, List <Entry<K,V> > l);
     * private int doHeight (BSTNode<K,V> t)
     */
    private void doInOrder (BSTNode<K,V> n, List <Entry<K,V> > l)
    {
        if(n != null)
        {
            doInOrder(n.left, l);
            l.add(new Entry<>(n.key, n.value));
            doInOrder(n.right, l);
        }
        else
        {
            return;
        }
    }
    private void doPreOrder (BSTNode<K,V> n, List <Entry<K,V> > l)
    {
        if(n != null)
        {
            l.add(new Entry<>(n.key, n.value));
            doPreOrder(n.left, l);
            
            doPreOrder(n.right, l);
        }
        else
        {
            return;
        }
    }
    private void doPostOrder (BSTNode<K,V> n, List <Entry<K,V> > l)
    {
        if(n != null)
        {
            doPostOrder(n.left, l);
            doPostOrder(n.right, l);
            l.add(new Entry<>(n.key, n.value));
            
        }
        else
        {
            return;
        }
    }
}
