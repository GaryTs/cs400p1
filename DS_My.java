
public class DS_My implements DataStructureADT {

    // TODO may wish to define an inner class 
    // for storing key and value as a pair
    // such a class and its members should be "private"
    private class Node{
        private Comparable key;
        private Object value;
        private Node left_child;
        private Node right_child;
        private Node par;
        private Node(Comparable key, Object value, Node par) {
            this.key = key;
            this.value = value;
            this.par = par;
        }
    }

    // Private Fields of the class
    // TODO create field(s) here to store data pairs
    private int size;
    private Node root;
    
    public DS_My() {
        
        size = 0;
        root = null;
    }

    @Override
    public void insert(Comparable k, Object v) {
        insertHelper(k, v, root, null);
    }

    private void insertHelper(Comparable k, Object v, Node n, Node par) {
        if (k == null) {
            throw new IllegalArgumentException("null key");
        }
        if (n == null) {
            
            n = new Node(k, v, par);
            if(root == null) {
                root = n;
            }
            size ++;
        }
        else if (n.key.compareTo(k) > 0) {
            insertHelper(k, v, n.left_child, n);
        }
        else if (n.key.compareTo(k) < 0) {
            insertHelper(k, v, n.right_child, n);
        }
        else {
            throw new RuntimeException("duplicate key");
        }
    }
    
    @Override
    public boolean remove(Comparable k) {
        if (k == null){
            throw new IllegalArgumentException("null key");
        }
        return removeHelper(k, root);
        
    }
    
    private boolean removeHelper(Comparable k, Node n) {
        if(n == null) {
            return false;
        }
        
        else if(n.key.compareTo(k) == 0) {
            if(n.left_child == null && n.right_child == null) {
                if(n.par == null) {
                    root = null;
                    size = 0;
                    return true;
                }
                if(n.par.key.compareTo(n.key) < 0)
                    
                    n.par.right_child = null;
                else
                    n.par.left_child = null;
                
                this.size --;
                return true;
            }
            else if (n.right_child == null) {
                n.left_child.par = n.par;
                if(n.par.key.compareTo(n.key) < 0)
                    n.par.right_child = n.left_child;
                else
                    n.par.left_child = n.left_child;
                
                this.size --;
                return true;
            }
            else if (n.left_child == null) {
                n.right_child.par = n.par;
                if(n.par.key.compareTo(n.key) < 0)
                    n.par.right_child = n.right_child;
                else
                    n.par.left_child = n.right_child;
                
                this.size --;
                return true;
            }
            
            else {
                Node pred = n.left_child;
                for(; pred.right_child != null ; pred = pred.right_child) {
                }
                if(n.par.key.compareTo(n.key) < 0)
                    n.par.right_child = pred;
                else
                    n.par.left_child = pred;
                    pred.right_child = n.right_child;
                    pred.left_child = n.left_child;
                    pred.par.right_child = null;
                
                this.size --;
                return true;
            }
            
        }
        else if(n.key.compareTo(k) < 0)
            return removeHelper(k, n.right_child);
        else
            return removeHelper(k, n.left_child);
    }

    @Override
    public boolean contains(Comparable k) {
        return containsHelper(k, root);
    }
    
    private boolean containsHelper(Comparable k, Node n) {
        if(n==null) {
            return false;
        }
        else if(n.key.compareTo(k)==0) {
            return true;
        }
        else if(n.key.compareTo(k)<0) {
            return containsHelper(k,n.right_child);
        }
        else {
            return containsHelper(k,n.left_child);
        }
    }

    @Override
    public Object get(Comparable k) {
        if (k == null){
            throw new IllegalArgumentException("null key");
        }
        return getHelper(k, root);
    }
    
    private Object getHelper(Comparable k, Node n) {
        if(n == null) {
            return null;
        }
        else if(n.key.compareTo(k)==0) {
            return n.value;
        }
        else if(n.key.compareTo(k)<0) {
            return getHelper(k, n.right_child);
        }
        else {
            return getHelper(k, n.left_child);
        }
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return size;
    }
    
}
