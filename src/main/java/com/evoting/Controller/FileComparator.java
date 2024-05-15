package com.evoting.Controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class FileComparator {
    public static void main(String[] args) {
        String file1Path = "C:/Users/ujjwa/Desktop/MysqlPOM.txt";
        String file2Path = "C:/Users/ujjwa/Desktop/StagingPOM.txt";


        compareFiles(file1Path, file2Path);
    }

    private static void compareFiles(String file1Path, String file2Path) {
        try (BufferedReader reader1 = new BufferedReader(new FileReader(file1Path));
             BufferedReader reader2 = new BufferedReader(new FileReader(file2Path))) {

            Set<String> linesOfFile2 = new HashSet<>();
            String line2;
            while ((line2 = reader2.readLine()) != null) {
                linesOfFile2.add(line2);
            }

            String line1;
            int lineNum = 1;
            while ((line1 = reader1.readLine()) != null) {
                if (!linesOfFile2.contains(line1)) {
                    System.out.println("Line from " + getFileName(file1Path) + " not found in " + getFileName(file2Path) + ":");
                    System.out.println(line1);
                    System.out.println();
                }
                lineNum++;
            }

            System.out.println("Comparison complete.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getFileName(String filePath) {
        String[] parts = filePath.split("\\\\");
        return parts[parts.length - 1];
    }
}
