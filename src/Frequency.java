public class Frequency {  
    Vocabulary word;  
    int Freq;  
  
    public Frequency() {  
        this.word = new Vocabulary("");  
        this.Freq = 0;  
    }  
      
    public Frequency(String word, int Freq) {  
        this.word = new Vocabulary(word);  
        this.Freq = Freq;  
    }  
      
        public void setVocabulary(Vocabulary word)  
    {  
        this. word = word;   
    }  
      
    public Vocabulary getVocabulary()  
    {  
         return word;  
    }  
  
    public int getFreq()  
    {  
        return Freq;  
    }  
      
    public int Icreament_Freq ()  
    {  
        ++this.Freq;  
        return this.Freq;  
    }  
  
    @Override  
    public String toString() {  
        return "Frequency{ " + word + ", " + Freq + '}';  
    }  
  
      
      
}  