package TAL.Languages;

public class Alphabet {
    private char[] symbols;

    public Alphabet (char[] symbols) {
        this.symbols = symbols;
        //TODO: sort the symbols according to UTF-8
    }

    public void check(String word) throws Exception{
        for (int i = 0; i < word.length(); i++) {
            //TODO: binary search the sorted symbols
            for (char c : symbols) {
                if (c != word.charAt(i)) {
                    throw new Exception("This word does not belong to the alphabet");
                }
            }
        }
    }

    public Language languageOf(int length) {
        Language l = new Language(this);
        String[] words = wordsOf(length);
        for (String word : words) l.addWord(word);
        return l;
    }

    public String[] wordsOf(int length) {
        return null; //TODO: complete
    }
}