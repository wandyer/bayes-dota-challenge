package gg.bayes.challenge.util;

import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ResourceReader {
    public static String readFileToString(String path) throws IOException {
        return FileUtils.readFileToString(ResourceUtils.getFile(path), String.valueOf(StandardCharsets.UTF_8));
    }
}
