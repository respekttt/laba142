import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LongestZeroSequenceLength {

    /**
     * Точка входу програми.
     * Очікує один аргумент: шлях до бінарного файлу.
     */
    public static void main(String[] args) {
        try {
            if (args == null || args.length != 1) {
                throw new IllegalArgumentException("Необхідно передати один аргумент: <filename>");
            }

            String filename = args[0];
            long maxSequence = longestZeroSequenceLength(filename);
            System.out.println("Найдовша послідовність нулів має довжину: " + maxSequence);
        } catch (IllegalArgumentException | NullPointerException e) {
            System.err.println("Помилка аргументів: " + e.getMessage());
            System.exit(1);
        } catch (FileNotFoundException e) {
            System.err.println("Файл не знайдено: " + e.getMessage());
            System.exit(2);
        } catch (IOException e) {
            System.err.println("Помилка вводу/виводу: " + e.getMessage());
            System.exit(3);
        }
    }

    /**
     * Знаходить довжину найдовшої послідовності байтів зі значенням 0 у файлі.
     *
     * @param filename шлях до бінарного файлу
     * @return довжина найдовшої послідовності нулів
     * @throws IllegalArgumentException якщо filename є null або порожнім
     * @throws IOException              якщо виникла помилка при читанні файлу
     */
    public static long longestZeroSequenceLength(String filename) throws IOException {
        if (filename == null) {
            throw new NullPointerException("Ім'я файлу не може бути null.");
        }

        if (filename.trim().isEmpty()) {
            throw new IllegalArgumentException("Ім'я файлу не може бути порожнім.");
        }

        long maxSequence = 0;
        long currentSequence = 0;

        // Використовуємо буфер розміру 4096 байт для ефективного читання
        byte[] buffer = new byte[4096];

        try (FileInputStream fis = new FileInputStream(filename)) {
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                for (int i = 0; i < bytesRead; i++) {
                    if (buffer[i] == 0) {
                        currentSequence++;
                        if (currentSequence > maxSequence) {
                            maxSequence = currentSequence;
                        }
                    } else {
                        currentSequence = 0;
                    }
                }
            }
        }

        return maxSequence;
    }
}
