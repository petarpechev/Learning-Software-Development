package Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.stream.Collectors;

public class TheMatrix {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

        int rows = Arrays
                .stream(reader.readLine().trim().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .findFirst().orElse(0);

        char[][] matrix = reader.lines()
                .limit(rows)
                .map(line -> line.replaceAll("\\s+", "").toCharArray())
                .toArray(char[][]::new);

        char fillChar = reader.readLine().trim().charAt(0);

        int[] startPosition = Arrays
                .stream(reader.readLine().trim().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int startRow = startPosition[0];
        int startCol = startPosition[1];

        char startChar = matrix[startRow][startCol];

        fill(matrix, startRow, startCol, fillChar, startChar);
        printMatrix(matrix);
    }

    private static void printMatrix(char[][] matrix) {
        System.out.println(Arrays.stream(matrix)
                .map(String::new)
                .collect(Collectors.joining(System.lineSeparator())));
    }
    private static void fill(char[][] matrix, int row, int col, char fillChar, char startChar) {
        if (matrix[row][col] != startChar) {
            return;
        }

        matrix[row][col] = fillChar;

        if (row + 1 < matrix.length) {
            fill(matrix, row + 1, col, fillChar, startChar);
        }

        if (row - 1 >= 0) {
            fill(matrix, row - 1, col, fillChar, startChar);
        }

        if (col + 1 < matrix[row].length) {
            fill(matrix, row, col + 1, fillChar, startChar);
        }

        if (col - 1 >= 0) {
            fill(matrix, row, col - 1, fillChar, startChar);
        }
    }
}
