package com.vertafore.test.utilities.classgenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;

public abstract class ClassGenerator {

  static final String SWAGGER_API_URL =
      "https://api.dev.titan.v4af.com/%s/v2/api-docs?group=%s-service";
  static final String STRING_CONSTANT_TEMPLATE = "\tprivate static final String %s = \"%s\";\n";
  static final String IMPORT_STATEMENT_TEMPLATE = "import %s;\n";

  public static void generateBaseDirectory(String basePath) {
    String newPath = Paths.get(basePath).toAbsolutePath().normalize().toString();
    File file = new File(newPath); // initialize File object and passing new path as argument
    try {
      file.mkdir();
    } catch (Exception e) {
      e.printStackTrace(); // prints exception if any
    }
  }

  public static void generateBaseFile(String basePath) {
    String newPath = Paths.get(basePath).toAbsolutePath().normalize().toString();
    File file = new File(newPath); // initialize File object and passing path as argument
    try {
      file.createNewFile(); // makes a new file
    } catch (Exception e) {
      e.printStackTrace(); // prints exception if any
    }
  }

  public static void writeUsingBufferedWriter(String path, String fileContent) {

    File file = new File(path);
    FileWriter fr = null;
    BufferedWriter br = null;
    String dataWithNewLine = fileContent + System.getProperty("line.separator");
    try {
      fr = new FileWriter(file);
      br = new BufferedWriter(fr);
      br.write(dataWithNewLine);

    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        br.close();
        fr.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public static String generatePackageNameFromPath(String path) {
    return path.replaceAll("[^A-z]+", "").toLowerCase();
  }

  public static String capitalizeStringArrToString(String[] arr) {
    StringBuilder result = new StringBuilder();
    Arrays.stream(arr)
        .forEach(
            word -> {
              result.append(capitalizeString(word));
            });
    return result.toString();
  }

  public static String capitalizeString(String str) {
    if (str.isEmpty()) {
      return str;
    } else if (str.length() == 1) {
      return Character.toUpperCase(str.charAt(0)) + "";
    } else if (str.length() >= 2) {
      return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    } else {
      throw new IllegalArgumentException("could not capitalize string " + str);
    }
  }
}
