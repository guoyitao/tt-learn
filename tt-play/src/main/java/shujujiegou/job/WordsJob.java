package shujujiegou.job;

public interface WordsJob {
     void countWords();
     void outputToFile();
     void deleteLowWord(boolean isPrint);
     void printASL(boolean isPrint);

     void clear();
}
