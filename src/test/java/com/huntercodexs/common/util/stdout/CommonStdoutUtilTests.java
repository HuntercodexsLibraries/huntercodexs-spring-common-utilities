package com.huntercodexs.common.util.stdout;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.huntercodexs.common.util.DataBuilder.PATH_TO_IMAGES_TEST;
import static com.huntercodexs.common.util.DataBuilder.imageToMatrix;
import static com.huntercodexs.common.util.data.structure.CommonDataStructureUtil.objectMatrix;
import static com.huntercodexs.common.util.file.CommonFileHandlerUtil.byteFile;
import static com.huntercodexs.common.util.stdout.CommonStdoutUtil.matrixPrinter;
import static com.huntercodexs.common.util.stdout.CommonStdoutUtil.objectMatrixPrinter;

class CommonStdoutUtilTests {

    @Test
    public void cliTableTest() {

        CommonStdoutUtil stdout = new CommonStdoutUtil();
        stdout.addWidth(101);

        stdout.drawHeader("CODEXS CLI TABLE - huntercodexs.com");

        stdout.drawItemHeader("Resource", "System");
        stdout.drawItemId("1", "osVersion", "Ubuntu", "");
        stdout.drawItem("osVendor", "Linux", "");
        stdout.drawItem("osPath", "Debian", "");
        stdout.drawItem("osTest", "Linux", "");
        stdout.drawItem("osDistro", "Linux", "");

        stdout.nextItem();
        stdout.nextItemHeader();

        stdout.drawItemHeader("Resource", "Audio");
        stdout.drawItemId("1", "Board", "REALTEK", "test");
        stdout.drawItem( "Board", "REALTEK", "test");

        stdout.drawItemId("2", "Board", "REALTEK", "test");
        stdout.drawItem("Board", "REALTEK", "test");
        stdout.drawItem("Board", "REALTEK", "test");

        stdout.drawItemId("3", "Board", "REALTEK", "test");
        stdout.drawItem( "Board", "REALTEK", "Intel64 Family 6 Model 158 Stepping 13 GenuineIntel ~2400 Mhz :: Intel64 Family 6 Model 158 Stepping 13 GenuineIntel ~2400 Mhz");

        stdout.nextItem();
    }

    @Test
    public void toMatrixTest() {
        List<List<Object>> objectList = new ArrayList<>();

        List<Object> objectList1 = new ArrayList<>();
        Collections.addAll(objectList1, "XXX", "WWW", "AAA", "EEE", 444, 654);
        objectList.add(objectList1);

        List<Object> objectList2 = new ArrayList<>();
        Collections.addAll(objectList2, "R12", "RQE", 901, "NIO", "NOT", "QQQ");
        objectList.add(objectList2);

        Object[][] result = objectMatrix(objectList);

        objectMatrixPrinter(result, 6);
    }

    @Test
    public void imageToMatrixTest() throws IOException {
        List<List<String>> matrixImage;
        matrixImage = imageToMatrix(byteFile(PATH_TO_IMAGES_TEST + "/1-bmp/file.bmp"), 10);
        matrixPrinter(matrixImage, 3);
        matrixImage = imageToMatrix(byteFile(PATH_TO_IMAGES_TEST + "/2-gif/file.gif"), 5);
        matrixPrinter(matrixImage, 3);
        matrixImage = imageToMatrix(byteFile(PATH_TO_IMAGES_TEST + "/3-png/file.png"), 5);
        matrixPrinter(matrixImage, 3);
        matrixImage = imageToMatrix(byteFile(PATH_TO_IMAGES_TEST + "/3-png/file-sample-1.png"), 20);
        matrixPrinter(matrixImage, 3);
        matrixImage = imageToMatrix(byteFile(PATH_TO_IMAGES_TEST + "/5-jpg/file1.jpg"), 5);
        matrixPrinter(matrixImage, 3);
        matrixImage = imageToMatrix(byteFile(PATH_TO_IMAGES_TEST + "/5-jpg/file-sample-1.jpg"), 10);
        matrixPrinter(matrixImage, 3);
    }
}






