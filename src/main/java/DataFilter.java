import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataFilter {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Specify at least one file to process.");
            return;
        }

        List<String> integers = new ArrayList<>();
        List<String> floats = new ArrayList<>();
        List<String> strings = new ArrayList<>();

        for (String fileName : args) {
            processFile(fileName, integers, floats, strings);
        }

        writeToFile("integers.txt", integers);
        writeToFile("floats.txt", floats);
        writeToFile("strings.txt", strings);

        System.out.println("End processing.");
    }

    private static void processFile(String fileName, List<String> integers, List<String> floats, List<String> strings) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.matches("^-?\\d+$")) {
                    integers.add(line);
                } else if (line.matches("^-?\\d+\\.\\d+$")) {
                    floats.add(line);
                } else {
                    strings.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error read file: " + fileName);
        }
    }

    private static void writeToFile(String fileName, List<String> data) {
        if (data.isEmpty()) return;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : data) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error write in file: " + fileName);
        }
    }
}
