package TAL.Languages;

public class Word {
    private Alphabet alphabet;
    private String word;

    public Word(String word, Alphabet alphabet) throws Exception{
        alphabet.check(word);
        this.word = word;
        this.alphabet = alphabet;
    }

    public String[] getPrefixes() {
        String[] prefixes = new String[word.length() + 1];
        for(int i = 0; i < prefixes.length; i++) {
            prefixes[i] = word.substring(0, i);
        }
        return prefixes;
    }

    public String[] getSuffixes() {
        String[] suffixes = new String[word.length() + 1];
        for(int i = 0; i < suffixes.length; i++) {
            suffixes[i] = word.substring(word.length() - i);
        }
        return suffixes;
    }

    public String toString() {
        return word;
    }
}
