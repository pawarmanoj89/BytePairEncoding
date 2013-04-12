import java.io.*;

public class FileRead {
  public static void main(String[] args) {
    File file = new File("d:\\ipfile.txt");
    int ch;
    StringBuffer strContent = new StringBuffer("");
    FileInputStream fin = null;
    try {
      fin = new FileInputStream(file);
      while ((ch = fin.read()) != -1)
        strContent.append((char) ch);
      fin.close();
    } catch (Exception e) {
      System.out.println(e);
    }
    System.out.println(strContent.toString());
  }
}