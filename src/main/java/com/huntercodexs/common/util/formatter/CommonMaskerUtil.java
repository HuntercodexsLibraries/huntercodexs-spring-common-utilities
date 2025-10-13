package com.huntercodexs.common.util.formatter;

import static com.huntercodexs.common.util.basic.CommonStringBasicUtil.repeat;

public class CommonMaskerUtil {

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">cardNumberMasked</h6>
     *
     * <p style="color: #CDCDCD">Mask a card number with a specific mask</p>
     *
     * @param cardNumber (String)
     * @param mask (String)
     * @return String (Masked Card Number)
    * */
    public static String cardNumberMasked(String cardNumber, String mask) {
        if (mask.isEmpty()) mask = "*";
        mask = repeat(mask, 4);
        String regexCard = "([0-9]{4})([- ]?)([0-9]{4})([- ]?)([0-9]{4})([- ]?)([0-9]{4})";
        return cardNumber.replaceAll(regexCard, "$1$2"+mask+"$4"+mask+"$6$7");
    }

}
