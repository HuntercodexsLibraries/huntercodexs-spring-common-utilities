package com.huntercodexs.common.util.file;

import org.junit.jupiter.api.Test;

import javax.mail.util.ByteArrayDataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

import static com.huntercodexs.common.util.DataBuilder.PATH_TO_IMAGES_TEST;
import static com.huntercodexs.common.util.basic.CommonsToolsBasicUtil.stdout;
import static com.huntercodexs.common.util.file.CommonFileHandlerUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommonFileHandlerUtilTests {

    @Test
    public void bytesExtractorShipmentFileTest() throws IOException {
        InputStream result = bytesFileExtractor("./src/test/resources/", "application-tests.properties");
        stdout("RESULT IS: " + result);
    }

    @Test
    public void fileToByteArrayTest() throws IOException {
        InputStream result = fileToByteArray("./src/test/resources/", "application-tests.properties");
        stdout("RESULT IS: " + result);
    }

    @Test
    public void fileToDataSourceTest() throws IOException {
        ByteArrayDataSource result = fileToDataSource("./src/test/resources/", "application-tests.properties");
        stdout("RESULT");
        stdout(String.valueOf(result));
    }

    @Test
    public void loadPropsTest() {
        Properties props = loadProps("classpath:application.properties");
        assertEquals("huntercodexs-common-utilities", props.getProperty("spring.application.name"));
    }

    @Test
    public void fileToStringTest() throws IOException {
        String result = fileToString("./src/test/resources/", "application-tests.properties");
        stdout("RESULT");
        stdout(result);
    }

    @Test
    public void fileToStringConfTest() throws IOException {
        String result = fileToString("./src/test/resources/files/others/", "attach.conf");
        stdout("RESULT");
        stdout(result);
    }

    @Test
    public void fileToStringCsvTest() throws IOException {
        String result = fileToString("./src/test/resources/files/others/", "attach.csv");
        stdout("RESULT");
        stdout(result);
    }

    @Test
    public void fileToStringDocTest() throws IOException {
        String result = fileToString("./src/test/resources/files/others/", "attach.doc");
        stdout("RESULT");
        stdout(result);
    }

    @Test
    public void fileToStringJpgTest() throws IOException {
        String result = fileToString("./src/test/resources/files/others/", "attach.jpg");
        stdout("RESULT");
        stdout(result);
    }

    @Test
    public void fileToStringPngTest() throws IOException {
        String result = fileToString("./src/test/resources/files/others/", "attach.png");
        stdout("RESULT");
        stdout(result);
    }

    @Test
    public void fileToStringPdfTest() throws IOException {
        String result = fileToString("./src/test/resources/files/others/", "attach.pdf");
        stdout("RESULT");
        stdout(result);
    }

    @Test
    public void fileToStringTxtTest() throws IOException {
        String result = fileToString("./src/test/resources/files/others/", "attach.txt");
        stdout("RESULT");
        stdout(result);
    }

    @Test
    public void fileToStringXlsTest() throws IOException {
        String result = fileToString("./src/test/resources/files/others/", "attach.xls");
        stdout("RESULT");
        stdout(result);
    }

    @Test
    public void fileToArrayTest() throws IOException {
        ArrayList<String> result = fileToArray("./src/test/resources/", "application-tests.properties");
        stdout("RESULT IS: " + result);
    }

    @Test
    public void fileInputStreamTest() throws IOException {
        String stream = fileInputStream("src/test/resources/file.txt");
        stdout(stream);
    }

    @Test
    public void byteFileTest() throws IOException {
        stdout(Arrays.toString(byteFile("src/test/resources/images/5-jpg/file2.jpg")));
    }

    @Test
    public void ioFileTest() throws IOException {
        stdout(ioFile(PATH_TO_IMAGES_TEST + "/5-jpg/file1.jpg"));
    }

    @Test
    public void binFileTest() throws IOException {
        stdout(binFile("src/test/resources/images/11-pdf/file.pdf"));
    }

    @Test
    public void existsTest() {
        if (exists("/home/jereelton/logs/.lock")) {
            stdout("LOG NOT ALLOWED");
        } else {
            stdout("LOG ALLOWED");
        }
    }

    @Test
    public void folderCreateTest() {
        assertTrue(folderCreate("./src/test/resources/tmp/folder-created-from-java-tests"));
    }

    @Test
    public void fileListTest() {
        stdout((Object) fileList("./src/test/resources/tmp", ".*.txt"));
    }

    @Test
    public void fileDeleteTest() {
        folderCreate("./src/test/resources/tmp/java-tests/delete-java-tests");
        assertTrue(fileDelete("./src/test/resources/tmp/java-tests/delete-java-tests"));
    }

    @Test
    public void fileMoveTest() {
        assertTrue(fileMove(
                "./src/test/resources/tmp/folder-created-from-java-tests",
                "./src/test/resources/tmp/folder-moved-from-java-tests"));
    }

    @Test
    public void fileWriterTest() {
        String textTest = "This is only a test!\n";
        byte[] bytes = textTest.getBytes();
        assertTrue(fileWriter(bytes,"./src/test/resources/tmp/file-tests-2.txt"));
    }

}





