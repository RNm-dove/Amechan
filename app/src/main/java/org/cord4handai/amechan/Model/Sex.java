package org.cord4handai.amechan.Model;

/**
 * Created by ryosuke on 2018/03/09.
 */

public enum Sex {
    Male,
    Female;

    public static Sex judgeSexFromInt(int i){
        if(i == 1){
            return Male;
        } else if ( i== 2){
            return Female;
        } else {
            return null;
        }
    }


}
