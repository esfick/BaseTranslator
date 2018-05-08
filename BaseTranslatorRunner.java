import java.util.*;


public class BaseTranslatorRunner{

    public static void main(String[]args){
        Scanner input = new Scanner(System.in);
        //int from, to;
        int from = 1;
        int to = 3;
        String num;
        /*do {
            System.out.println("Select Number of Base to Translate FROM: ");
            System.out.println("0: Base 2 (Binary)");
            System.out.println("1: Base 8 (Octal)");
            System.out.println("2: Base 10 (Decimal)");
            System.out.println("3: Base 16 (Hexadecimal)");
            from = input.nextInt();
        } while(from < 0 || from > 3);
        do {
            System.out.println("Select Number of Base to Translate TO: ");
            System.out.println("0: Base 2 (Binary)");
            System.out.println("1: Base 8 (Octal)");
            System.out.println("2: Base 10 (Decimal)");
            System.out.println("3: Base 16 (Hexadecimal)");
            to = input.nextInt();
        } while(to < 0 || to > 3);
        */
        //input.nextLine();
        System.out.println("Enter number in " + typeToString(from) + " that you want to convert to " + typeToString(to) + ": ");
        num = input.nextLine();
        if(BaseTranslator.validateInput(num, from)){
            String result = num;
            if(from != to){
                result = BaseTranslator.getResult(num, from, to);
            }
            System.out.println("Result is " + result);
        }
    }

    public static String typeToString(int type){
        String typ = "";
        switch(type){
            case 0:
                typ = "binary";
                break;
            case 1:
                typ = "octal";
                break;
            case 2:
                typ = "decimal";
                break;
            case 3:
                typ = "hexadecimal";
                break;
        }
        return typ;
    }

}
