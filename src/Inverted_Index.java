public class Inverted_Index {  
  
            LinkedList <Term> invertedindex;   
  
            public Inverted_Index() {  
                invertedindex = new LinkedList <Term>();  
            }  
  
            public boolean addNew (int docID, String word)  
            {  
                if (invertedindex.empty())  
               {  
                   Term t = new Term ();  
                    t.setVocabulary(new Vocabulary (word));  
                    t.add_docID(docID);  
                    invertedindex.insert(t);  
                    return true;  
               }  
               else  
               {  
                    invertedindex.findFirst();  
                    while ( ! invertedindex.last())  
                    {  
                        if ( invertedindex.retrieve().word.word.compareTo(word) == 0)  
                        {  
                            Term t = invertedindex.retrieve();  
                            t.add_docID(docID);  
                            invertedindex.update(t);  
                            return false;  
                        }  
                       invertedindex.findNext();  
                    }  
                    if ( invertedindex.retrieve().word.word.compareTo(word) == 0)  
                    {  
                        Term t = invertedindex.retrieve();  
                        t.add_docID(docID);  
                        invertedindex.update(t);  
                        return false;  
                    }  
                    else  
                    {  
                        Term t = new Term ();  
                        t.setVocabulary(new Vocabulary (word));  
                        t.add_docID(docID);  
                        invertedindex.insert(t);  
                    }  
                    return true;  
           }  
        }  
  
        public boolean found(String word)  
        {  
               if (invertedindex.empty())  
                   return false;  
  
               invertedindex.findFirst();  
               for ( int i = 0 ; i < invertedindex.size ; i++)  
               {  
                   if ( invertedindex.retrieve().word.word.compareTo(word) == 0)  
                       return true;  
                  invertedindex.findNext();  
               }  
               return false;  
        }  
        //=====================================================================  
        public boolean [] AND_OR_Function (String str )  
        {  
            if (! str.contains(" OR ") && ! str.contains(" AND "))  
            {  
                boolean [] r1 = new boolean[50];  
                str = str.toLowerCase().trim();  
              
                if (this.found (str))  
                    r1 =  this.invertedindex.retrieve().getDocs();  
                return r1;  
            }  
              
            else if (str.contains(" OR ") && str.contains(" AND "))  
            {  
                String [] AND_ORs = str.split(" OR ");  
                boolean []  r1 = AND_Function (AND_ORs[0]);  
                 
                for ( int i = 1 ; i < AND_ORs.length ; i++  )  
                {     
                    boolean [] r2 =AND_Function (AND_ORs[i]);  
                      
                    for ( int j = 0 ; j < 50 ; j++ )  
                        r1 [j] = r1[j] || r2[j];  
                }  
                return r1;  
            }  
              
            else  if (str.contains(" AND "))  
                return AND_Function (str);  
              
            return OR_Function (str);  
        }  
          
        public boolean [] AND_Function (String str)  
        {  
            String [] ANDs = str.split(" AND ");  
            boolean [] b1 = new boolean [50];  
              
            if (this.found (ANDs[0].toLowerCase().trim()))  
                b1 = this.invertedindex.retrieve().getDocs();  
  
            for ( int i = 1 ; i< ANDs.length ; i++)  
            {  
                boolean [] b2 = new boolean [50];  
                if (this.found (ANDs[i].toLowerCase().trim()))  
                    b2 = this.invertedindex.retrieve().getDocs();  
                  
                for ( int j = 0 ; j < 50 ; j++)  
                    b1 [j] = b1[j] && b2[j];  
            }                  
  
            return b1;  
        }  
          
        public boolean [] OR_Function (String str)  
        {  
            String [] ORs = str.split(" OR ");  
            boolean [] b1 = new boolean [50];  
              
            if (this.found (ORs[0].toLowerCase().trim()))  
                b1 = this.invertedindex.retrieve().getDocs();  
  
            for ( int i = 1 ; i< ORs.length ; i++)  
            {  
                boolean [] b2 = new boolean [50];  
                if (this.found (ORs[i].toLowerCase().trim()))  
                    b2 = this.invertedindex.retrieve().getDocs();  
                  
                for ( int j = 0 ; j < 50 ; j++)  
                    b1 [j] = b1[j] || b2[j];  
                 
            }  
            return b1;  
        }  
  
        public void printDocment()  
        {  
            if (this.invertedindex.empty())  
                System.out.println("Empty Inverted Index");  
            else  
            {  
                this.invertedindex.findFirst();  
                while ( ! this.invertedindex.last())  
                {  
                    System.out.println(invertedindex.retrieve());  
                    this.invertedindex.findNext();  
                }  
                System.out.println(invertedindex.retrieve());  
            }  
        }  
}  