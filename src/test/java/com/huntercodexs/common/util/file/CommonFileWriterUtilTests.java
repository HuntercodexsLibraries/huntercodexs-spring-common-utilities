package com.huntercodexs.common.util.file;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(MockitoExtension.class)
class CommonFileWriterUtilTests {

    private CommonFileWriterUtil commonFileWriterUtil;

    @BeforeEach
    void setUp() {
        openMocks(this);
        commonFileWriterUtil = new CommonFileWriterUtil();
    }

    @Test
    public void fileCreateTest() throws IOException {
        String textTest = "This is only a test!\n";

        commonFileWriterUtil.fileDelete("./src/test/resources/tmp/java-tests/moved-file-tests.txt");
        commonFileWriterUtil.folderCreate("./src/test/resources/tmp/java-tests");
        commonFileWriterUtil.fileCreate("./src/test/resources/tmp/java-tests/buffered-file-tests.txt");
        commonFileWriterUtil.fileWrite("HEADER: "+textTest);
        commonFileWriterUtil.fileNewLine();
        commonFileWriterUtil.fileWrite("1."+textTest);
        commonFileWriterUtil.fileWrite("2."+textTest);
        commonFileWriterUtil.fileWrite("3."+textTest);
        commonFileWriterUtil.fileWrite("4."+textTest);
        commonFileWriterUtil.fileNewLine();
        commonFileWriterUtil.fileClose();
        commonFileWriterUtil.fileMove(
                "./src/test/resources/tmp/java-tests/buffered-file-tests.txt",
                "./src/test/resources/tmp/java-tests/moved-file-tests.txt");
    }

}
