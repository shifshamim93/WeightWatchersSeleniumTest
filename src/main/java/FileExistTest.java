import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FileExistTest {


  public static void main(String[] args) {

    Scanner scan = new Scanner(System.in);
    System.out.print("Enter relative path of resource eg: relative/test.txt : ");

    String path = scan.nextLine();

    boolean fileExists = doesFileExist(path);

    if (fileExists) {
      readFile(new File(
          new FileExistTest().getClass().getClassLoader().getResource(path).getFile()));
    }

  }


  static boolean doesFileExist(String path) {

    boolean exists = false;
    try {
      ClassLoader classLoader = new FileExistTest().getClass().getClassLoader();
      exists = new File(classLoader.getResource(path).getFile()).isFile();
      System.out.println("File exists : " + exists);
    } catch (Exception e) {
      System.out.println("File not found at path : " + path + e.getMessage());
      //e.printStackTrace();
    }
    return exists;
  }


  static void readFile(File file) {

    try (FileReader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader)) {

      String line;
      while ((line = br.readLine()) != null) {
        String[] lineContent = line.split("–");
        System.out.println(lineContent[0]);
        List<String> meaningList = Arrays.asList(lineContent[1].split(","));
        meaningList.forEach(t -> System.out.println(t));

      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
