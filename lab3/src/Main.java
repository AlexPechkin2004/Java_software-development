/*
    Номер залікової книги: 1124
    C3 = 2 (String)
    С17 = 2 (Знайти таке слово в першому реченні заданого тексту, якого немає в жодному з наступних.)
*/

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Будь ласка, введіть текст:");
        String text = scanner.nextLine();
        try {
            String uniqueWord = findUniqueWord(text);
            if (uniqueWord != null) {
                System.out.println("Унікальне слово в першому реченні: " + uniqueWord);
            } else {
                System.out.println("Унікального слова в першому реченні не знайдено.");
            }
        } catch (Exception e) {
            System.out.println("Сталася помилка при обробці тексту: " + e.getMessage());
        }
    }

    private static String findUniqueWord(String text) throws Exception {
        text = cleanText(text);
        String[] sentences = text.split("[.!?]");
        if (sentences.length < 2) {
            throw new Exception("Текст повинен містити принаймні два речення.");
        }

        Map<String, String> firstSentenceWords = new LinkedHashMap<>();
        for (String word : sentences[0].split("\\s+")) {
            firstSentenceWords.put(word.toLowerCase(), word);
        }

        Set<String> otherSentencesWords = new HashSet<>();
        for (int i = 1; i < sentences.length; i++) {
            otherSentencesWords.addAll(Arrays.asList(sentences[i].toLowerCase().split("\\s+")));
        }

        for (String word : firstSentenceWords.keySet()) {
            if (!otherSentencesWords.contains(word)) {
                return firstSentenceWords.get(word);
            }
        }
        return null;
    }

    private static String cleanText(String text) {
        return text.replaceAll("[^\\p{L}\\p{M}\\p{N}\\s.?!']", " ");
    }
}
