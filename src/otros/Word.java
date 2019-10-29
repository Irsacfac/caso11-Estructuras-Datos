package otros;

public class Word {

    public String word;
    public int repetitions;

    public Word(String pWord) {
        word = pWord;
        repetitions = 1;
    }

    public String getWord() {
        return word;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void incrementRepetitions(){
        repetitions++;
    }
}
