package com.vertafore.test.tasks.utilities;

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
