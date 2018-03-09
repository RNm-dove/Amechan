package org.cord4handai.amechan.Model;

/**
 * Created by ryosuke on 2018/03/09.
 */

public enum Where {
    Osaka_Suita,
    Osaka_Toyonaka,
    Osaka_Umeda;

    public static Where judgeWhereFromInt(int i){
        if(i == 1){
            return Osaka_Suita;
        } else if ( i== 2){
            return Osaka_Toyonaka;
        } else {
            return Osaka_Umeda;
        }
    }

}
