package com.huntercodexs.common.util.file;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static com.huntercodexs.common.util.file.CommonFileHandlerUtil.fileWriter;
import static com.huntercodexs.common.util.file.CommonFileReaderUtil.awaitFileContent;
import static com.huntercodexs.common.util.generator.CommonDataGeneratorUtil.randomNumber;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CommonFileReaderUtilTests {

    @Test
    public void awaitFileContentTest() throws Exception {

        fileWriter("".getBytes(), "./src/test/resources/file.txt");

        Thread.sleep(2000);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(() -> {
            try {
                return awaitFileContent(
                        "./src/test/resources/file.txt",
                        "[0-9]{6}",
                        60000
                );
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        Thread.sleep(5000);

        String randomValue = String.valueOf(randomNumber(6));
        byte[] bytes = randomValue.getBytes();
        fileWriter(bytes, "./src/test/resources/file.txt");

        String code = future.get(20, TimeUnit.SECONDS);
        assertEquals(randomValue, code);

        executor.shutdown();
    }

}
