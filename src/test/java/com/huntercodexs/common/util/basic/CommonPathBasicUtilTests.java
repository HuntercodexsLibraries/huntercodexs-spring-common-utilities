package com.huntercodexs.common.util.basic;

import org.junit.jupiter.api.Test;

import static com.huntercodexs.common.util.basic.CommonPathBasicUtil.fileExtension;
import static com.huntercodexs.common.util.basic.CommonPathBasicUtil.sanitizePath;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CommonPathBasicUtilTests {

    @Test
    public void sanitizePathTest() {
        String result = sanitizePath("/home/user/test/");
        assertEquals("/home/user/test", result);

        result = sanitizePath("/home/user/test");
        assertEquals("/home/user/test", result);

        result = sanitizePath("/home/user/test/text.txt");
        assertEquals("/home/user/test/text.txt", result);

        result = sanitizePath("text.txt");
        assertEquals("text.txt", result);

        result = sanitizePath("text");
        assertEquals("text", result);
    }

    @Test
    public void fileExtensionTest() {
        assertEquals("txt", fileExtension("file.txt"));
        assertEquals("txt", fileExtension("/home/user/file.txt"));
        assertEquals("txt", fileExtension("/home/user.name/file.txt"));
        assertEquals("txt", fileExtension("/home/user.name/file.name.txt"));
        assertEquals("txt", fileExtension("/home/user.name/file.name.txt"));
        assertEquals("txt", fileExtension("~/file.name.txt"));
    }
}
