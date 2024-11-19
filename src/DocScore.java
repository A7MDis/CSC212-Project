public class DocScore {
    int docID;  // رقم المستند
    double score;  // الدرجة أو النقاط

    // البناء (Constructor)
    public DocScore(int docID, double score) {
        this.docID = docID;
        this.score = score;
    }

    // دالة للحصول على درجة المستند
    public double getScore() {
        return score;
    }

    // دالة للحصول على رقم المستند
    public int getDocID() {
        return docID;
    }

    // دالة لمقارنة درجتين بين مستندين (يمكن استخدامها للترتيب)
    public boolean isGreaterThan(DocScore other) {
        return this.score > other.score;
    }

    // تمثيل الكائن كـ String لطباعة النتائج
    @Override
    public String toString() {
        return "DocID: " + docID + ", Score: " + score;
    }
}
