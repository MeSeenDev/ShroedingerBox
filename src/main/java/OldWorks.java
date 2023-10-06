import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Arrays.stream;

public class OldWorks {

    // Создание Comparator'а вынесено в отдельный метод, чтобы не загромождать метод kotlin.main.
    private static Comparator<Map.Entry<String, Integer>> descendingFrequencyOrder() {
        // Нам нужен Comparator, который сначала упорядочивает пары частоте (по убыванию),
        // а затем по слову (в алфавитном порядке). Так и напишем:
        return Comparator.<Map.Entry<String, Integer>>comparingInt(Map.Entry::getValue)
                .reversed()
                .thenComparing(Map.Entry::getKey);

    }

    public void StreamMy() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, UTF_8))) {
            Comparator<Map.Entry<String, Integer>> frst = Map.Entry.comparingByValue(Comparator.reverseOrder());
            Comparator<Map.Entry<String, Integer>> scnd = Map.Entry.comparingByKey();
            stream(reader.readLine().replaceAll("[\\p{Blank}\\p{Punct}]", " ").split(" "))  //Получаем Array.stream , меняем табуляцию и символы на пробелы и делим по пробелам
                    .map(String::toLowerCase) // переводим строки в нижний регистр
                    .collect(Collectors.groupingBy(x -> x, Collectors.summingInt(p -> 1)))
                    .entrySet()
                    .stream()
                    .sorted(frst.thenComparing(scnd))   // сортируем
                    .map(Map.Entry::getKey) // получаем сортированный сет ключей
                    .limit(11)
                    .forEach(System.out::println); // Выводим
        } catch (IOException ignored) {
        }
    }

    public void StreamEtalon() {
        // Для чтения входного потока используем Scanner.
        // Поскольку словами мы считаем последовательности символов,
        // состоящие из букв или цифр, то в качестве разделителя слов Scanner'у
        // указываем регулярное выражение, означающее
        // "один или более символ, не являющийся ни буквой, ни цифрой".
        Scanner scanner = new Scanner(System.in, UTF_8)
                .useDelimiter("[^\\p{L}\\p{Digit}]+");

        // Пройдем по всем словам входного потока и составим Map<String, Integer>,
        // где ключом является слово, преобразованное в нижний регистр,
        // а значением - частота этого слова.
        Map<String, Integer> freqMap = new HashMap<>();
        scanner.forEachRemaining(s -> freqMap.merge(s.toLowerCase(), 1, Integer::sum));

        freqMap.entrySet().stream()                 // получим стрим пар (слово, частота)
                .sorted(descendingFrequencyOrder()) // отсортируем
                .limit(10)                          // возьмем первые 10
                .map(Map.Entry::getKey)             // из каждой пары возьмем слово
                .forEach(System.out::println);      // выведем в консоль
    }
}
