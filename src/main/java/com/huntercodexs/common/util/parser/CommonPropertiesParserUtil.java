package com.huntercodexs.common.util.parser;

import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ResourceBundle;

import static com.huntercodexs.common.util.constants.CommonConstantsUtil.PROPERTIES_MESSAGE_FILE;
import static java.util.Objects.isNull;

public final class CommonPropertiesParserUtil {

    @Generated
    private static final Logger log = LoggerFactory.getLogger(CommonPropertiesParserUtil.class);

    private CommonPropertiesParserUtil() {}

    public static String getErrorMessage(String key, String filePath) {
        try {
            ResourceBundle bundle;
            if (isNull(filePath)) {
                bundle = ResourceBundle.getBundle(PROPERTIES_MESSAGE_FILE);
            } else {
                bundle = ResourceBundle.getBundle(filePath);
            }
            return bundle.getString(key);
        } catch (Exception e) {
            log.warn("Properties Message Error: {}", e.getMessage());
            return "Properties Message Error: " + e.getMessage();
        }
    }

}
