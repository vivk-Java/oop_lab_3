package oop.lab.Text;

import oop.lab.ArrayUtils.ArrayUtils;

import java.util.*;

public class TextManager {
    private String text;

    private int sentencesCount;
    private int wordsCount;

    private int lettersCount;
    private int consonantsCount;
    private int vowelsCount;

    private String longestWord;

    private final Hashtable<String, Integer> words = new Hashtable<String, Integer>();

    private final Character[] consonantsArray = { 'B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N',
                                                  'P', 'Q', 'R', 'S', 'T', 'V', 'W', 'X', 'Z', 'Y',
                                                  'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n',
                                                  'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'z', 'y' };

    private final Character[] vowelsArray = { 'A', 'E', 'I', 'O', 'U',
                                              'a', 'e', 'i', 'o', 'u' };

    public TextManager(String text) throws Exception {
        this.text = text;
        textManagerCalc();
    }

    public void setText(String text) throws Exception {
        this.text = text;
        textManagerCalc();
    }

    public String getText() {
        return this.text;
    }

    public int getSentencesCount() {
        return this.sentencesCount;
    }

    public int getWordsCount() {
        return this.wordsCount;
    }


    public int getLettersCount() {
        return this.lettersCount;
    }

    public int getConsonantsCount() {
        return this.consonantsCount;
    }

    public int getVowelsCount() {
        return this.vowelsCount;
    }

    public String getLongestWord() {
        return this.longestWord;
    }

    public String[] getTopWords(int count) {
        return topWordsCalc(count);
    }

    private void textManagerCalc() throws Exception {
        sentencesCalc();
        wordsCalc();
        lettersCalc();
        consonantsCalc();
        vowelsCalc();
        longestWordCalc();
    }

    private void sentencesCalc() throws Exception {
        if (this.text == null)
            throw new Exception("Text is undefined.");

        String[] sentences = this.text.split("\\.");
        this.sentencesCount = sentences.length;
    }

    private void wordsCalc() throws Exception {
        if (this.text == null) {
            throw new Exception("Text is undefined.");
        }

        String lowerCase = this.text.toLowerCase();
        String[] words = lowerCase.split(" ");
        int wordsLength = 0;

        this.words.clear();

        for (String word : words) {
            if (word.isEmpty()) {
                continue;
            }

            String replacement = "";
            word = word.replaceAll("\\.", replacement);
            word = word.replaceAll(",", replacement);
            word = word.replaceAll(" ", replacement);

            if (word.isEmpty()) {
                continue;
            }

            if (this.words.containsKey(word)) {
                var number = this.words.get(word);
                this.words.put(word, number + 1);
            } else {
                this.words.put(word, 1);
            }

            wordsLength++;
        }

        this.wordsCount = wordsLength;
    }

    private void lettersCalc() throws Exception {
        boolean isConsonant = false;
        boolean isVowel = false;
        int letters = 0;

        char[] text = this.text.toCharArray();

        for (char letter : text) {
            isConsonant = ArrayUtils.contains(this.consonantsArray, letter);
            isVowel = ArrayUtils.contains(this.vowelsArray, letter);
            if (!isConsonant && !isVowel) {
                continue;
            }

            letters++;
        }

        this.lettersCount = letters;
    }

    private void consonantsCalc() throws Exception {
        boolean isConsonant = false;
        int consonants = 0;

        char[] text = this.text.toCharArray();

        for (char letter : text) {
            isConsonant = ArrayUtils.contains(this.consonantsArray, letter);
            if (!isConsonant) {
                continue;
            }

            consonants++;
        }

        this.consonantsCount = consonants;
    }

    private void vowelsCalc() throws Exception {
        boolean isVowel = false;
        int vowels = 0;

        char[] text = this.text.toCharArray();

        for (char letter : text) {
            isVowel = ArrayUtils.contains(this.vowelsArray, letter);
            if (!isVowel) {
                continue;
            }

            vowels++;
        }

        this.vowelsCount = vowels;
    }
    
    private String[] topWordsCalc(int count) {
        ArrayList<String> topWords = new ArrayList<>();

        ArrayList<Map.Entry<String, Integer>> list = new ArrayList(this.words.entrySet());
        list.sort(new Comparator<Map.Entry<?, Integer>>() {
            public int compare(Map.Entry<?, Integer> o1, Map.Entry<?, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        for (int i = 0; i < list.size(); i++) {
            if (i >= count) {
                break;
            }

            topWords.add(list.get(i).getKey());
        }

        return topWords.toArray(String[]::new);
    }

    private void longestWordCalc() {
        if (this.words.size() == 0) {
            this.longestWord = null;
        }

        final String[] longestWord = { null };
        final int[] longestWordLength = { -1 };
        words.forEach((key, value) -> {
            if (key.length() > longestWordLength[0]) {
                longestWordLength[0] = key.length();
                longestWord[0] = key;
            }
        });

        this.longestWord = longestWord[0];
    }
}
