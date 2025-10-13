package com.huntercodexs.common.util;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

public class DataBuilder {

    public static final String PATH_TO_IMAGES_TEST = "./src/test/resources/images";
    public static final String PROPERTIES_MESSAGE_FILE_TEST = "test_messages";

    public static final String ERROR_CODE_TEST = "ERR001";
    public static final String ERROR_MESSAGE_TEST = "Sample error message";

    public static List<List<String>> imageToMatrix(byte[] image, int matrixSize) {
        if (matrixSize <= 1) {
            return null;
        }

        String encode = imageEncode(image);
        int encodeLength = encode.length();
        int bytesLength = (encodeLength+matrixSize) / matrixSize;
        String[] lines = encode.split("(?<=\\G.{" + bytesLength + "})");

        List<List<String>> imageMatrix = new ArrayList<>();

        for (String line : lines) {

            List<String> matrixColumns = new ArrayList<>();

            int lineLength = line.length();
            int columnsLength = (lineLength+matrixSize) / matrixSize;
            String[] columns = line.split("(?<=\\G.{" + columnsLength + "})");

            Collections.addAll(matrixColumns, columns);

            imageMatrix.add(matrixColumns);
        }

        return imageMatrix;
    }

    public static String imageEncode(byte[] image) {
        return new String(Base64.getEncoder().encode(image));
    }
}
