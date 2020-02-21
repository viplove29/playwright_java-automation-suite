package com.vertafore.test.utilities.misc;

import java.io.File;
import java.util.Objects;

public class HelperUtils {
  // generic method to find resources to upload
  public File getFileByFileName(String fileName, String fileExtension) {

    return new File(
        Objects.requireNonNull(
                getClass().getClassLoader().getResource("files/" + fileName + fileExtension))
            .getFile());
  }
}
