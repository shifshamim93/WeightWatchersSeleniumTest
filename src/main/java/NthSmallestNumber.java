import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class NthSmallestNumber {

  public static void main(String[] args) {

    Scanner scan = new Scanner(System.in);
    System.out.print("Enter any number between 1 to 500 : ");

    int num;
    while (true) {
      String line = scan.nextLine();
      try {
        num = Integer.parseInt(line);
        if (num > 0 && num <= 500) {
          break;
        }
      } catch (NumberFormatException e) {
        // retry
      }
      System.out.print("Error: input must be a integer between 1 and 500.\n");
    }

    scan.close();

    Integer arr[] = generateArrayWithRandomNumber(500);

    System.out.print("N'th smallest number is " +
        NthSmallestNumber(arr, num));

  }

  public static int NthSmallestNumber(Integer[] arr,
      int k) {
    // Sort the given array
    Arrays.sort(arr);

    // Return N'th element in
    // the sorted array
    return arr[k - 1];
  }

  public static Integer[] generateArrayWithRandomNumber(int arraySize) {

    Random rd = new Random(); // creating Random object
    Integer[] arr = new Integer[arraySize];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = rd.nextInt(); // storing random integers in an array
      System.out.println(arr[i]); // printing each array element
    }
    return arr;
  }


}
