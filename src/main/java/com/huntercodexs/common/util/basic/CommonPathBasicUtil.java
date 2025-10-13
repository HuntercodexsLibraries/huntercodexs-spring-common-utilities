package com.huntercodexs.common.util.basic;

public class CommonPathBasicUtil {

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">sanitizePath</h6>
     *
     * <p style="color: #CDCDCD">Sanitize any path as you need</p>
     *
     * @param path (String)
     * @return String (Path sanitized)
    * */
    public static String sanitizePath(String path) {
        if (path.matches(".*\\..*$")) {
            return path;
        }
        return path.replaceAll("/$", "");
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">fileExtension</h6>
     *
     * <p style="color: #CDCDCD">Get the file extension from one string</p>
     *
     * @param filepath (String)
     * @return String (Extension File)
    * */
    public static String fileExtension(String filepath) {
        if (!filepath.contains(".")) {
            return filepath;
        }

        String[] fileParts = filepath.split("\\.");
        String fileType = fileParts[fileParts.length-1];

        if (fileType.isEmpty()) {
            return filepath;
        }

        return fileType;
    }

}
