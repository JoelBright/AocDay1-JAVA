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

    static int calculateTotalDistance(String fileName){
        String filePath = Path + fileName;
        int requiredTotal = 0;

        List<Integer> firstColumn = new ArrayList<>();
        List<Integer> secondColumn = new ArrayList<>();

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

        int index = 0;
        for(int x: firstColumn){
            int y = secondColumn.get(index);

            int distance = x-y;
            System.out.println("x:"+x+", y:"+y+" thus, distance at index "+index+" is ="+distance);

            if (distance < 0) { distance *= -1; }
            requiredTotal += distance;

            System.out.println("updated requiredTotal is ="+requiredTotal);

            index++;
        }

        return requiredTotal;
    }

    public static void main(String[] args) {

        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.print("Hi, select the file to run:\n 1-ExampleIn.txt\n 2-Input.txt\n 3-TestIn.txt\n");
        Scanner scanner = new Scanner(System.in);
        String selection = scanner.nextLine();

        String fileName = "ExampleIn.txt";

        switch (selection){
            case "2" :  fileName = "Input.txt";
                break;
            case "3" :  fileName = "TestIn.txt";
                break;
            default:    fileName = "ExampleIn.txt";
        }

        System.out.print("The required total distance is: "+ calculateTotalDistance(fileName));


    }
}