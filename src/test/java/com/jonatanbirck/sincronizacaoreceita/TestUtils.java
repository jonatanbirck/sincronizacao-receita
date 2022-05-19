package com.jonatanbirck.sincronizacaoreceita;

import java.net.URI;
import java.nio.file.Path;

public class TestUtils {

    public static final Path BASE_PATH = Path.of("src","test","resources");

    public static URI withBasePath(String path) {
        return BASE_PATH.resolve(path).toUri();
    }

}
