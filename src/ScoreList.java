class ScoreList {  
    private class Node {  
        DocScore data;  
        Node next;  
  
        Node(DocScore data) {  
            this.data = data;  
            this.next = null;  
        }  
    }  
  
    private Node head;  
    private int size;  
  
    public ScoreList() {  
        head = null;  
        size = 0;  
    }  
  
    public void add(DocScore score) {  
        Node newNode = new Node(score);  
        if (head == null) {  
            head = newNode;  
        } else {  
            Node current = head;  
            while (current.next != null) {  
                current = current.next;  
            }  
            current.next = newNode;  
        }  
        size++;  
    }  
  
    public void sort() {  
        if (size < 2) return;  
  
        boolean swapped;  
        Node ptr1;  
        Node lptr = null;  
  
        do {  
            swapped = false;  
            ptr1 = head;  
  
            while (ptr1.next != lptr) {  
                if (!ptr1.data.isGreaterThan(ptr1.next.data)) {  
                    // Swap data  
                    DocScore temp = ptr1.data;  
                    ptr1.data = ptr1.next.data;  
                    ptr1.next.data = temp;  
                    swapped = true;  
                }  
                ptr1 = ptr1.next;  
            }  
            lptr = ptr1;  
        } while (swapped);  
    }  
  
    public void traverse() {  
        Node current = head;  
        while (current != null) {  
            if (current.data.score > 0) {  
                System.out.println("Document " + current.data.docID + ": Score " + current.data.score);  
            }  
            current = current.next;  
        }  
    }  
}  