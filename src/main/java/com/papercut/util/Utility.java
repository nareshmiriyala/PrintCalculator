package com.papercut.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by nareshm on 5/09/2015.
 */
public class Utility {
    public static BigDecimal getRoundedValue(BigDecimal value){
        return value.setScale(2, RoundingMode.CEILING);
    }
}
