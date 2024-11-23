import java.util.Scanner;  

public class Main {  
    public static Scanner input = new Scanner(System.in);  
    public static Search_Engine SE = new Search_Engine();  

    public static int menu() {  
        System.out.println("1. Boolean Retrieval ");  
        System.out.println("2. Ranked Retrieval");  
        System.out.println("3. Indexed Documents");  
        System.out.println("4. Indexed Tokens");  
        System.out.println("5. Exit");  
        System.out.println("Enter your choice");  
        int choice = input.nextInt();  
        return choice;  
    }  

    public static void printBoolean(boolean[] result) {  
        Term t = new Term("", result);  
        System.out.println(t);  
    }  

    public static void printRankedResults(double[] scores) {
        System.out.print("Result doc scores: [ ");
        for (double score : scores) {
            System.out.print(score + " ");
        }
        System.out.println("]");
    }

    public static void Boolean_Retrieval_menu() { 
        System.out.println("################### Boolean Retrieval ####################"); 
        System.out.println("Please enter your Boolean query:"); 
        input.nextLine(); 
        String userQuery = input.nextLine(); 
        System.out.println("Your query: " + userQuery); 
        System.out.print("Result doc IDs: "); 
        printBoolean(SE.Boolean_Retrieval(userQuery, 2)); 
        System.out.println(); 
    } 

    public static void Ranked_Retrieval_menu() {
        System.out.println("################ Ranked Retrieval #################");
        System.out.println("Please enter your Ranked Retrieval query:");
        input.nextLine(); 
        String userQuery = input.nextLine(); 
        System.out.println("Your query: " + userQuery);
        SE.Ranked_Retrieval(userQuery);
        System.out.println(); 
    }

    public static void Indexed_Documents_menu() {  
        System.out.println("######## Indexed Documents ######## ");  
        System.out.println("tokens " + SE.tokens);  
    }  

    public static void Indexed_Tokens_menu() {  
        System.out.println("######## Indexed Tokens ######## ");  
        System.out.println("tokens " + SE.vocab);  
    }  

    public static void main(String[] args) {  
        SE.LoadData("C:\\Users\\alshm\\OneDrive\\Documents\\CSC212P\\stop.txt", "C:\\Users\\alshm\\OneDrive\\Documents\\CSC212P\\dataset.csv");

        int choice;  

        do {  
            choice = menu();  
            switch (choice) {  
                case 1:  
                    Boolean_Retrieval_menu();  
                    break;  
                case 2:  
                    Ranked_Retrieval_menu();  
                    break;  
                case 3:  
                    Indexed_Documents_menu();  
                    break;  
                case 4:  
                    Indexed_Tokens_menu();  
                    break;  
                case 5:  
                    System.out.println("Good bye!");
                    break;  
                default:         
                    System.out.println("Wrong input, try again!");  
            }  
        } while (choice != 5);  
    }  
}  
