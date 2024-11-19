public class Inverted_Index_AVLRanked {  
    class frequency {  
        int docID = 0;  
        int f = 0;  
    }  

    AVLTree<Integer, AVLTree<String, Rank>> AVLrank;   
    frequency[] freqs = new frequency[50];  

    public Inverted_Index_AVLRanked() {  
        AVLrank = new AVLTree<Integer, AVLTree<String, Rank>>();  
    }  

    // إضافة كلمة جديدة إلى الفهرس
    public boolean addNew(int docID, String word) {  
        if (AVLrank.empty()) {  
            // إذا كان الفهرس فارغًا، أنشئ فهرس جديد لهذا المستند
            AVLTree<String, Rank> miniRank = new AVLTree<String, Rank>();  
            miniRank.insert(word, new Rank(word, 1));  
            AVLrank.insert(docID, miniRank);  
            return true;  
        } else {  
            // إذا كان هناك فهرس لهذا المستند، ابحث عن الكلمة داخله
            if (AVLrank.find(docID)) {  
                AVLTree<String, Rank> miniRank = AVLrank.retrieve();  
                if (miniRank.find(word)) {  
                    // إذا كانت الكلمة موجودة في الفهرس، أضف رتبة جديدة
                    Rank rank = miniRank.retrieve();  
                    rank.add_Rank();  // تأكد أن add_Rank() موجودة في كلاس Rank
                    miniRank.update(rank);  
                    AVLrank.update(miniRank);  
                    return false;  
                }  
                // إذا كانت الكلمة غير موجودة، قم بإضافتها مع رتبة جديدة
                miniRank.insert(word, new Rank(word, 1));  
                AVLrank.update(miniRank);  
                return true;  
            }  
            // إذا لم يكن هناك فهرس للمستند، قم بإنشاء فهرس جديد وإضافة الكلمة مع رتبتها
            AVLTree<String, Rank> miniRank = new AVLTree<String, Rank>();  
            miniRank.insert(word, new Rank(word, 1));  
            AVLrank.insert(docID, miniRank);  
            return true;  
        }  
    }  

    // التحقق من وجود الكلمة في المستند
    public boolean found(int docID, String word) {  
        if (AVLrank.find(docID))  
            if (AVLrank.retrieve().find(word))  
                return true;  
        return false;  
    }  

    // الحصول على رتبة الكلمة في المستند
    public int getrank(int docID, String word) {  
        int value = 0;  
        if (AVLrank.find(docID))  
            if (AVLrank.retrieve().find(word))  
                return AVLrank.retrieve().retrieve().getRank();  
        return value;  
    }  

    // طباعة المستندات في الفهرس
    public void printDocument() {  
        AVLrank.TraverseT();  
    }  

    // حساب التكرار لكل كلمة في النص
    public void TF(String str) {  
        str = str.toLowerCase().trim();  
        String[] words = str.split(" ");  

        int index = 0;  
        for (int docID = 0; docID < 50; docID++) {  
            int count = 0;  
            for (int j = 0; j < words.length; j++)  
                count += this.getrank(docID, words[j]);  
            if (count > 0) {  
                freqs[index] = new frequency();  
                freqs[index].docID = docID;  
                freqs[index].f = count;  
                index++;  
            }  
        }  

        if (index > 0) {  
            mergesort(freqs, 0, index - 1);  
        }  

        for (int x = 0; x < index; x++)  
            System.out.println(freqs[x].docID + "\t\t" + freqs[x].f);  
    }  

    // خوارزمية الفرز باستخدام خوارزمية الدمج (MergeSort)
    public static void mergesort(frequency[] A, int l, int r) {  
        if (l >= r)  
            return;  
        int m = (l + r) / 2;  
        mergesort(A, l, m);  
        mergesort(A, m + 1, r);  
        merge(A, l, m, r);  
    }  

    // دمج المصفوفات بعد الفرز
    private static void merge(frequency[] A, int l, int m, int r) {  
        frequency[] B = new frequency[r - l + 1];  
        int i = l, j = m + 1, k = 0;  

        while (i <= m && j <= r) {  
            if (A[i].f >= A[j].f)  
                B[k++] = A[i++];  
            else  
                B[k++] = A[j++];  
        }  

        if (i > m)  
            while (j <= r)  
                B[k++] = A[j++];  
        else  
            while (i <= m)  
                B[k++] = A[i++];  

        for (k = 0; k < B.length; k++)  
            A[k + l] = B[k];  
    }  
}
