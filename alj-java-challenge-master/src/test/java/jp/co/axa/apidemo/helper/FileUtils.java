package jp.co.axa.apidemo.helper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {

    public static String readJsonFile(final String filepath) throws URISyntaxException, IOException {

        return String.join("", Files.readAllLines(
                Paths.get(org.apache.tomcat.util.http.fileupload.FileUtils.class.getClassLoader().getResource(filepath)
                        .toURI()),
                StandardCharsets.UTF_8
        ));
    }
}
