package com.example.controljornada.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Esta clase es la encargada de comprobar si la contraseña es valida
 * @author pablo
 *
 */
public final class CommonUtils {


    public static boolean isPasswordValid(String password){
        String PASSWORDPATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";
        Pattern pattern = Pattern.compile(PASSWORDPATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

}
