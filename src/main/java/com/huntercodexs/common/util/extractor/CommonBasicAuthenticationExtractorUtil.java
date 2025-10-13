package com.huntercodexs.common.util.extractor;

import java.util.Base64;

import static java.util.Objects.nonNull;

public class CommonBasicAuthenticationExtractorUtil {

    public static String[] getUserBasicAuthentication(String authorizationHeader) {

        var returnSplit = new String[2];
        returnSplit[0] = "";
        returnSplit[1] = "";

        if (nonNull(authorizationHeader) && authorizationHeader.startsWith("Basic")) {

            String[] split64 = authorizationHeader.split("\\s");
            byte[] decoded64Array;

            try {
                decoded64Array = Base64.getDecoder().decode(split64[1].getBytes());
            } catch (Exception e) {
                return null;
            }

            var decoded64String = new String(decoded64Array);
            String[] split = decoded64String.split(":");

            if (split.length == 2)
                return split;
        }

        return returnSplit;
    }

    public static boolean verifyIfAuthorization(String authorization) {
        return nonNull(authorization) && authorization.contains("Basic ");
    }
}
