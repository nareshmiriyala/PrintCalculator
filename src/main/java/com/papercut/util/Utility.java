package com.papercut.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Utility class.
 * Created by nareshm on 5/09/2015.
 */
public class Utility {
    /**
     * Round the input big decimal value to 2 decimal points.
     * @param value
     * @return
     */
    public static BigDecimal getRoundedValue(BigDecimal value) {
        return value.setScale(2, RoundingMode.CEILING);
    }
}
