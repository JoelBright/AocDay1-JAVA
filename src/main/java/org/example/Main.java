package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    private static final String Path = "src/main/resources/";

    static Result readAndSortListsIn(String fileName) {

        List<Integer> firstColumn = new ArrayList<>();
        List<Integer> secondColumn = new ArrayList<>();

        String filePath = Path + fileName;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    break;
                }
                String[] values = line.split("\\s+");
                firstColumn.add(Integer.parseInt(values[0]));
                secondColumn.add(Integer.parseInt(values[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        firstColumn = firstColumn.stream().sorted().collect(Collectors.toList());
        secondColumn = secondColumn.stream().sorted().collect(Collectors.toList());
        System.out.println("First Column: " + firstColumn);
        System.out.println("Second Column: " + secondColumn);

        return new Result(firstColumn, secondColumn);
    }

    static int calculateTotalDistance(List<Integer> list1, List<Integer> list2) {
        int requiredTotal = 0;

        int index = 0;
        for (int x : list1) {
            int y = list2.get(index);

            int distance = x - y;
            System.out.println("x:" + x + ", y:" + y + " thus, distance at index " + index + " is =" + distance);

            if (distance < 0) {
                distance *= -1;
            }
            requiredTotal += distance;

            System.out.println("updated requiredTotal is =" + requiredTotal);

            index++;
        }

        return requiredTotal;
    }

    static long calculateSimilarityScore(List<Integer> list1, List<Integer> list2) {
        long requiredScore = 0;

        for (int x : list1) {

            long countOfX = list2.stream().filter(e -> e.equals(x)).count();

            requiredScore += countOfX * x;
            if (countOfX > 0)
                System.out.println("x:" + x + ", countOfX:" + countOfX + " thus, requiredScore is now =" + requiredScore);
        }

        return requiredScore;
    }

    public static void main(String[] args) {

        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.println("Hi, select the file to run:\n 1-ExampleIn.txt\n 2-Input.txt\n 3-TestIn.txt");
        Scanner scanner = new Scanner(System.in);
        String selection = scanner.nextLine();

        String fileName = switch (selection) {
            case "2" -> "Input.txt";
            case "3" -> "TestIn.txt";
            default -> "ExampleIn.txt";
        };

        Result inputValues = readAndSortListsIn(fileName);

        System.out.println("The required total distance answer for part 1: " + calculateTotalDistance(inputValues.value1(), inputValues.value2()));

        System.out.println("The required similarity score answer for part 2: " + calculateSimilarityScore(inputValues.value1(), inputValues.value2()));
    }

    public record Result(List<Integer> value1, List<Integer> value2) {
    }
}