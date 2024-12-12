import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CopyToUpperCase {

    /**
     * Точка входу програми.
     * Очікує два аргументи: шлях до вихідного файлу та шлях до цільового файлу.
     */
    public static void main(String[] args) {
        if (args == null || args.length != 2) {
            throw new IllegalArgumentException("Необхідно передати два аргументи: <source> <destination>");
        }

        String source = args[0];
        String destination = args[1];

        try {
            copyToUpperCase(source, destination);
            System.out.println("Файл успішно скопійовано у верхньому регістрі.");
        } catch (IllegalArgumentException | NullPointerException e) {
            System.err.println("Помилка аргументів: " + e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Помилка вводу/виводу: " + e.getMessage());
            System.exit(2);
        }
    }

    /**
     * Копіює текстовий файл, перетворюючи всі символи у верхній регістр.
     *
     * @param source      шлях до вихідного файлу
     * @param destination шлях до цільового файлу
     * @throws IllegalArgumentException якщо source або destination є null або порожні
     * @throws IOException              якщо виникла помилка при читанні або записі файлу
     */
    public static void copyToUpperCase(String source, String destination) throws IOException {
        if (source == null || destination == null) {
            throw new NullPointerException("Шляхи до файлів не можуть бути null.");
        }

        if (source.trim().isEmpty() || destination.trim().isEmpty()) {
            throw new IllegalArgumentException("Шляхи до файлів не можуть бути порожніми.");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(destination))) {

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line.toUpperCase());
                writer.newLine(); // Додаємо перенесення рядка
            }
        }
    }
}
