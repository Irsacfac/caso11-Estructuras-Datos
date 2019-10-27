public class Palabra {

    private String word;
    private int repetitions;

    public Palabra(String pWord) {
        word = pWord;
        repetitions = 1;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public void addRepetition(){
        repetitions++;
    }

}
