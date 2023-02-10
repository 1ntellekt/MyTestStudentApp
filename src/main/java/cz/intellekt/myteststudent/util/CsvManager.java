package cz.intellekt.myteststudent.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvManager {

    public static List<List<String>> getStringsFromCsvFile(String fileName) {
        List<List<String>> records = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(fileName))){
            while (scanner.hasNextLine()){
                records.add(readLineInCsvFile(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return records;
    }

    private static List<String> readLineInCsvFile(String line) {
        List<String> values = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(line)){
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()){
                values.add(rowScanner.next());
            }
        }
        return values;
    }

}
