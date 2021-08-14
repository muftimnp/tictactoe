package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Props {
  private Props() {
  }

  private Properties loadProps() throws Exception {
    try {
      Properties props = new Properties();
      FileInputStream in;
      String workDir = System.getProperty("user.dir");
      in = new FileInputStream(workDir + File.separator + "src/main/resources/application.properties");
      props.load(in);
      return props;
    } catch (IOException e) {
      throw e;
    }
  }

  private static class PropsHolder {
    private static final Properties INSTANCE_PROPS() throws Exception {
      return new Props().loadProps();
    }
  }

  public static Properties getProperties() throws Exception {
    try {
      return PropsHolder.INSTANCE_PROPS();
    } catch (Exception e) {
      throw e;
    }
  }

  public static String readProp(String key) throws Exception {
    try {
      return Props.getProperties().getProperty(key);
    } catch (Exception e) {
      throw e;
    }
  }

  public static int readGridSize() throws Exception {
    try {
      int n = Integer.parseInt(Props.getProperties().getProperty(Fields.SIZE_OF_BOARD));
      if (n < 3 || n > 15) {
        throw new IndexOutOfBoundsException("Grid size is out of bounds");
      }
      return n;
    } catch (Exception e) {
      throw e;
    }
  }
}