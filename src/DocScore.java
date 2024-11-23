
public class DocScore {
    double score;
	int docID;
    public DocScore(int docID, double score) {
        this.docID = docID;
        this.score = score;
    }

    public double getScore() {
        return score;
    }

    public int getDocID() {
        return docID;
    }

    public boolean isGreaterThan(DocScore other) {
        return this.score > other.score;
    }

    @Override
    public String toString() {
        return "DocID: " + docID + ",Score: " + score;
    }
}