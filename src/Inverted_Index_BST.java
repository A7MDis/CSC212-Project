
public class Inverted_Index_BST {  
    BST < String , Term> invertedindexBST;   
    int count = 0;  

    public Inverted_Index_BST() {  
        invertedindexBST = new BST< String , Term> ();  
    }  

    public boolean addNew (int docID, String word)  
    {  
       if (invertedindexBST.empty())  
       {  
           count ++;  
           Term t = new Term ();  
           t.setVocabulary(new Vocabulary (word));  
           t.add_docID(docID);  
           invertedindexBST.insert(word, t);  
           return true;  
       }  
       else  
       {  
            if (invertedindexBST.find(word))  
            {  
                Term t = this.invertedindexBST.retrieve();  
                t.add_docID(docID);  
                invertedindexBST.update(t);  
                return false;  
                  
            }  
              
           count ++;  
           Term t = new Term ();  
           t.setVocabulary(new Vocabulary (word));  
           t.add_docID(docID);  
           invertedindexBST.insert(word, t);  
            return true;  
   }  
}  

public boolean found(String word)  
{  
       return invertedindexBST.find(word);  
}  
  
public void printDocument()  
{  
    invertedindexBST.Traverse();  
}  
 
public boolean [] AND_OR_Function (String str )  
{  
    if (! str.contains(" OR ") && ! str.contains(" AND "))  
    {  
        boolean [] r1 = new boolean[50];  
        str = str.toLowerCase().trim();  
      
        if (this.found (str))  
            r1 =  this.invertedindexBST.retrieve().getDocs();  
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
        b1 = this.invertedindexBST.retrieve().getDocs();  

    for ( int i = 1 ; i< ANDs.length ; i++)  
    {  
        boolean [] b2 = new boolean [50];  
        if (this.found (ANDs[i].toLowerCase().trim()))  
            b2 = this.invertedindexBST.retrieve().getDocs();  
          
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
        b1 = this.invertedindexBST.retrieve().getDocs();  

    for ( int i = 1 ; i< ORs.length ; i++)  
    {  
        boolean [] b2 = new boolean [50];  
        if (this.found (ORs[i].toLowerCase().trim()))  
            b2 = this.invertedindexBST.retrieve().getDocs();  
          
        for ( int j = 0 ; j < 50 ; j++)  
            b1 [j] = b1[j] || b2[j];  
         
    }  
    return b1;  
}  


} 