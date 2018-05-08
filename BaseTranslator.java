import java.util.*;
import java.util.regex.*;

class BaseTranslator{

    static final int BINARY = 0;
    static final int OCTAL = 1;
    static final int DECIMAL = 2;
    static final int HEX = 3;

    static boolean validateInput(String input, int base){
        Pattern p = Pattern.compile(getPattern(base));
        Matcher m = p.matcher(input);
        return m.matches();
    }

    static String getPattern(int base){
        String pattern = "";
        switch(base){
            case BINARY:
                pattern = "[0-1]+";
                break;
            case OCTAL:
                pattern = "[0-7]+";
                break;
            case DECIMAL:
                pattern = "[1-9][0-9]*";
                break;
            case HEX:
                pattern = "[0-9a-fA-F]+";
                break;
        }
        return pattern;
    }

    static String getResult(String input, int from, int to){
        int dVal;
        if(from == DECIMAL){
            dVal = Integer.parseInt(input);
        }
        else {
            dVal = getDecimalVal(input, from);
        }
        if(to == DECIMAL){
            return Integer.toString(dVal);
        }
        else {
            return intToResult(dVal, to);
        }
    }

    static int getDecimalVal(String input, int from){
        String bin;
        if(from == BINARY){
            return binToDec(input);
        }
        if (from == OCTAL){
            bin = octToBin(input);
        }
        else {
            bin = hexToBin(input);
        }
        return binToDec(bin);
    }

    static String intToResult(int decVal, int to){
        String bin = decToBin(decVal);
        if(to == BINARY){
            return bin;
        }
        bin = reformatBinString(bin, to);
        if(to == OCTAL){
            return binToOct(bin);
        }
        else {
            return binToHex(bin);
        }
    }

    static String reformatBinString(String b, int base){
        String bin = "";
        int blockSize = (base == OCTAL)? 3: 4;
        int numBlocks = b.length()/blockSize + 1;
        int leadingZeroes = b.length() % blockSize;
        if(leadingZeroes != 0){
            leadingZeroes = blockSize - leadingZeroes;
        }
        for(int i = 0; i < leadingZeroes; i++){
            bin += '0';
        }
        bin += b;
        System.out.println("Reformatted: " + bin);
        return bin;
    }

    static String decToBin(int d){
        String bin = "";
        if(d == 0){
            return "0";
        }
        if(d == 1){
            return "1";
        }
        while(d > 0){
            bin += (d % 2 == 1)? '1':'0';
            d/=2;
        }
        StringBuilder sb = new StringBuilder(bin);
        sb.reverse();
        return sb.toString();
    }

    static int binToDec(String bin){
        int d = 0;
        for(int i = bin.length()-1; i >= 0; i--){
            int power = bin.length()-i-1;
            if(bin.charAt(i) == '1'){
                d += Math.pow(2,power);
            }
        }
        return d;
    }

    static String hexToBin(String hex){
        String bin = "";
        for(int i = 0; i < hex.length(); i++){
            bin += hexCharToBin(hex.charAt(i));
        }
        return bin;
    }


    static String binToHex(String b){
        String hex = "";
        int blocks = b.length()/4;
        for(int i = 0; i < blocks; i++){
            hex += decToChar(binToDec(b.substring(4*i, 4*(i+1))));
        }
        return hex;
    }

    static String octToBin(String oct){
        String bin = "";
        for(int i = 0; i < oct.length(); i++){
            bin += octCharToBin(oct.charAt(i));
        }
        return bin;
    }

    static String binToOct(String b){
        String oct = "";
        int blocks = b.length()/3;
        for(int i = 0; i < blocks; i++){
            String block = b.substring(3*i, 3*i +3);
            oct += decToChar(binToDec(block));
        }
        return oct;
    }

    static String octCharToBin(char oc){
        if(oc == '0'){
            return "000";
        }
        if(oc == '1'){
            return "001";
        }
        if(oc == '2'){
            return "010";
        }
        if(oc == '3'){
            return "011";
        }
        if(oc == '4'){
            return "100";
        }
        if(oc == '5'){
            return "101";
        }
        if(oc == '6'){
            return "110";
        }
        if(oc == '7'){
            return "111";
        }
        return "err";
    }

    static String hexCharToBin(char h){
        if(h == '0'){
            return "0000";
        }
        if(h == '1'){
            return "0001";
        }
        if(h == '2'){
            return "0010";
        }
        if(h == '3'){
            return "0011";
        }
        if(h == '4'){
            return "0100";
        }
        if(h == '5'){
            return "0101";
        }
        if(h == '6'){
            return "0110";
        }
        if(h == '7'){
            return "0111";
        }
        if(h == '8'){
            return "1000";
        }
        if(h == '9'){
            return "1001";
        }
        if(h == 'a' || h == 'A'){
            return "1010";
        }
        if(h == 'b' || h == 'B'){
            return "1011";
        }
        if(h == 'c' || h == 'C'){
            return "1100";
        }
        if(h == 'd' || h == 'D'){
            return "1101";
        }
        if(h == 'e' || h == 'E'){
            return "1110";
        }
        if(h == 'f' || h == 'F'){
            return "1111";
        }
        return "err";
    }

    static char decToChar(int dec){
        if(dec < 10){
            return Character.forDigit(dec, 10);
        }
        else {
            return Character.forDigit(dec, 16);
        }
    }



}
