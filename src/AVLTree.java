
public class AVLTree<K extends Comparable<K>, T> {

    class AVLNode<K extends Comparable<K>, T> {
        public K key;
        public T data;
        AVLNode<K, T> parent;
        AVLNode<K, T> left;
        AVLNode<K, T> right;
        int bf;

        public AVLNode() {
            this.key = null;
            this.data = null;
            this.parent = this.left = this.right = null;
            this.bf = 0;
        }

        public AVLNode(K key, T data) {
            this.key = key;
            this.data = data;
            this.parent = this.left = this.right = null;
            this.bf = 0;
        }

        public AVLNode<K, T> getLeft() {
            return left;
        }

        public AVLNode<K, T> getRight() {
            return right;
        }

        public T getData() {
            return data;
        }

        @Override
        public String toString() {
            return "AVL Node{" + "key=" + key + ", data=" + data + '}';
        }
    }

    private AVLNode<K, T> root;
    private AVLNode<K, T> curr;
    private int count;

    public AVLTree() {
        root = curr = null;
        count = 0;
    }

    public boolean empty() {
        return root == null;
    }

    public int size() {
        return count;
    }

    public void clear() {
        root = curr = null;
        count = 0;
    }

    public T retrieve() {
        if (curr != null) {
            return curr.data;
        }
        return null;
    }

    public void update(T e) {
        if (curr != null) {
            curr.data = e;
        }
    }

    private T searchTreeHelper(AVLNode<K, T> node, K key) {
        if (node == null) {
            return null;
        } else if (node.key.compareTo(key) == 0) {
            curr = node;
            return node.data;
        } else if (node.key.compareTo(key) > 0) {
            return searchTreeHelper(node.left, key);
        } else {
            return searchTreeHelper(node.right, key);
        }
    }

    private void updateBalance(AVLNode<K, T> node) {
        if (node.bf < -1 || node.bf > 1) {
            rebalance(node);
            return;
        }

        if (node.parent != null) {
            if (node == node.parent.left) {
                node.parent.bf -= 1;
            }

            if (node == node.parent.right) {
                node.parent.bf += 1;
            }

            if (node.parent.bf != 0) {
                updateBalance(node.parent);
            }
        }
    }

    void rebalance(AVLNode<K, T> node) {
        if (node.bf > 0) {
            if (node.right.bf < 0) {
                rightRotate(node.right);
                leftRotate(node);
            } else {
                leftRotate(node);
            }
        } else if (node.bf < 0) {
            if (node.left.bf > 0) {
                leftRotate(node.left);
                rightRotate(node);
            } else {
                rightRotate(node);
            }
        }
    }

    public boolean find(K key) {
        return searchTreeHelper(this.root, key) != null;
    }

    void leftRotate(AVLNode<K, T> x) {
        AVLNode<K, T> y = x.right;
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }

        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;

        x.bf = x.bf - 1 - Math.max(0, y.bf);
        y.bf = y.bf - 1 + Math.min(0, x.bf);
    }

    void rightRotate(AVLNode<K, T> x) {
        AVLNode<K, T> y = x.left;
        x.left = y.right;
        if (y.right != null) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;

        x.bf = x.bf + 1 - Math.min(0, y.bf);
        y.bf = y.bf + 1 + Math.max(0, x.bf);
    }

    public boolean insert(K key, T data) {
        AVLNode<K, T> node = new AVLNode<>(key, data);

        AVLNode<K, T> p = null;
        AVLNode<K, T> current = this.root;

        while (current != null) {
            p = current;
            if (node.key.compareTo(current.key) == 0) {
                return false;
            } else if (node.key.compareTo(current.key) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        node.parent = p;
        if (p == null) {
            root = node;
            curr = node;
        } else if (node.key.compareTo(p.key) < 0) {
            p.left = node;
        } else {
            p.right = node;
        }
        count++;

        updateBalance(node);
        return true;
    }

    public boolean remove(K key) {
        K k1 = key;
        AVLNode<K, T> p = root;
        AVLNode<K, T> q = null;

        while (p != null) {
            if (k1.compareTo(p.key) < 0) {
                q = p;
                p = p.left;
            } else if (k1.compareTo(p.key) > 0) {
                q = p;
                p = p.right;
            } else {
                if ((p.left != null) && (p.right != null)) {
                    AVLNode<K, T> min = p.right;
                    q = p;
                    while (min.left != null) {
                        q = min;
                        min = min.left;
                    }
                    p.key = min.key;
                    p.data = min.data;
                    k1 = min.key;
                    p = min;
                }

                if (p.left != null) {
                    p = p.left;
                } else {
                    p = p.right;
                }

                if (q == null) {
                    root = p;
                    this.updateBalance(p);
                } else {
                    if (k1.compareTo(q.key) < 0) {
                        q.left = p;
                    } else {
                        q.right = p;
                    }
                    this.updateBalance(q);
                }
                count--;
                curr = root;
                return true;
            }
        }
        return false;
    }

    public T remove_maximum() {
        if (root == null) {
            return null;
        }
        AVLNode<K, T> n = this.recAVL_Search_maximum(root);
        T v = n.data;
        this.remove(n.key);
        return v;
    }

    public T Search_maximum() {
        if (root == null) {
            return null;
        }
        return recAVL_Search_maximum(root).data;
    }

    private AVLNode<K, T> recAVL_Search_maximum(AVLNode<K, T> x) {
        if (x.right == null) {
            return x;
        } else {
            return recAVL_Search_maximum(x.right);
        }
    }

    public void Traverse() {
        if (root != null) {
            traverseTree(root);
        }
    }

    private void traverseTree(AVLNode<K, T> node) {
        if (node == null) {
            return;
        }
        traverseTree(node.left);
        System.out.println(node.data);
        traverseTree(node.right);
    }
}
 