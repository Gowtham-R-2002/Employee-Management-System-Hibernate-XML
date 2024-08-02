package com.util;

/** 
 * Contains methods for Validation for inputs given by user
 */
public class Validator {

    /** 
     * Returns boolean value after validation of any String
     *
     * @param name  The String that is to be validated
     * @return      The boolean true if it is a valid String 
     *              else false
     */
    public static boolean isAlphabeticName(String name) {
        return name.matches("^[a-zA-Z ]+$") ? true : false;
    }
}