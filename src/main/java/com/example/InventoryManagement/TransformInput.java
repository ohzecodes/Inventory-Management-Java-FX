package com.example.InventoryManagement;

/**
 * this class to helper class to convert  lower Case words to uppercase.
 * SentenceCase is defined as First letter Capitalized And the rest are lowercase
 * @author  Ohzecodes
 */
public class TransformInput {
    /**
     * Check if the string is lowercase
     * @param str the String you want to check
     * @return true if lowercase, else false
     */
    private static boolean isStringLowerCase(String str){
        char[] charArray = str.toCharArray();
        for(int i=0; i < charArray.length; i++){
            if( !Character.isLowerCase( charArray[i] ))
                return false;
        }

        return true;

    }

    /**
     * This method changes the tring to SentenceCase, in all cases.
     * @param s the string that you want to change
     * @return A SentenceCased version of the string
     */
    public static String SudoSentenceCase(String s ){
        return String.valueOf(s.trim().charAt(0)).toUpperCase() + s.substring(1).toLowerCase();
    }

    /**
     * check if the string is lowercase, then changes it to uppercase. otherwise return the strign as it is
     * @param s  the string that you want to change
     * @return if the string is lowercase, then changes it to uppercase. otherwise return the strign as it is
     */
    public static String SentenceCase(String s){
    if(isStringLowerCase(s)) {
        return SudoSentenceCase(s);
    }else{
        return s;
    }
}
}
