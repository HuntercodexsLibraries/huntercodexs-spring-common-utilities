package com.huntercodexs.common.util.handler;

import com.huntercodexs.common.util.model.CommonErrorModel;

import java.util.ArrayList;
import java.util.List;

import static com.huntercodexs.common.util.parser.CommonParserUtil.convertToJSON;
import static com.huntercodexs.common.util.parser.CommonPropertiesParserUtil.getErrorMessage;

public class CommonErrorMessagesUtil {

    private final List<CommonErrorModel> errors = new ArrayList<>();

    public static String messageError(String codeError, String filePath) {
        return getErrorMessage(codeError, filePath);
    }

    public static String messageErrors(String codeError, String filePath) {
        CommonErrorMessagesUtil commonErrorMessagesUtil = new CommonErrorMessagesUtil();
        commonErrorMessagesUtil.add(codeError, filePath);
        return commonErrorMessagesUtil.toString();
    }

    private void add(String codeError, String filePath) {
        CommonErrorModel errorModel = new CommonErrorModel();
        errorModel.setCode(codeError);
        errorModel.setMessage(getErrorMessage(codeError, filePath));
        this.errors.add(errorModel);
    }

    public int size() {
        return this.errors.size();
    }

    public void clear() {
        this.errors.clear();
    }

    public String toString() {
        return convertToJSON(this.errors);
    }

}
