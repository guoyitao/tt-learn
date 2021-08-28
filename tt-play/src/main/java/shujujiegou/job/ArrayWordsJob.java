package shujujiegou.job;

import cn.hutool.core.io.FileUtil;
import shujujiegou.util.Util;

import java.io.File;
import java.util.*;

public class ArrayWordsJob implements WordsJob {
    static class Count{
        String keyword;
        int count;

        @Override
        public String toString() {
            return keyword +"\t" + count;
        }
    }
    private List<Count> list = new ArrayList<>();
    private String context;
    private String output;
    private int lowCount;
    private int ASL;

    public int getASL() {
        return ASL;
    }

    private void increaseASL(){
        ASL++;
    }

    @Override
    public void clear(){
        list = new ArrayList<>();
        ASL = 0;
    }

    public ArrayWordsJob(String input, String output, int lowCount) {
        System.out.println("---------------线性表--------------------");
        this.output = output;
        this.lowCount = lowCount;
        this.context = Util.readFileAs(input);
    }

    private Count addWord(String s){
        boolean isContains = false;
        Count result = null;
        for (Count count : list) {
            increaseASL();
            if (count.keyword.equals(s)){
                isContains = true;
                count.count++;
                result = count;
                break;
            }
        }
        if (!isContains){
            increaseASL();
            Count count = new Count();
            count.keyword = s;
            count.count = 1;
            result = count;
            list.add(count);
        }
        return result;
    }

    @Override
    public void countWords(){
        StringTokenizer stringTokenizer = new StringTokenizer(context, ".,# ");
        while (stringTokenizer.hasMoreTokens()) {
            String s = stringTokenizer.nextToken();
            if (Util.isEnglish(s)) {
                addWord(s);
            }
        }
//        System.out.println(list);
    }

    @Override
    public void outputToFile(){
        FileUtil.del(new File(output));
        list.sort(new Comparator<Count>() {
            @Override
            public int compare(Count o1, Count o2) {
                return o1.count - o2.count;
            }
        });
        for (int i = 0; i < list.size(); i++) {
            increaseASL();
        }
        FileUtil.appendUtf8Lines(list,new File(output));
    }

    @Override
    public void deleteLowWord(boolean isPrint){
        Iterator<Count> iterator = list.iterator();
        while (iterator.hasNext()) {
            increaseASL();
            Count count = iterator.next();
            if (count.count< lowCount){
                if (isPrint){
                    System.out.printf("低于%d的单词：key=%s value=%d\n",lowCount,count.keyword,count.count );
                }
                iterator.remove();
            }
        }
    }

    @Override
    public void printASL(boolean isPrint) {
        if (isPrint){
            System.out.println("线性表的ASL:" + getASL());
        }

    }


    public static void main(String[] args) {
        String input = "C:\\mycode\\桌面快捷资料\\计量大学上课资料\\计量大学上课资料\\算法数据结构\\qimo8\\words.txt";
        String output = "C:\\mycode\\桌面快捷资料\\计量大学上课资料\\计量大学上课资料\\算法数据结构\\qimo8\\OutFile.txt";
        ArrayWordsJob arrayWordsJob = new ArrayWordsJob(input,output,5);

        arrayWordsJob.countWords();
        arrayWordsJob.deleteLowWord(true);
        arrayWordsJob.outputToFile();
        arrayWordsJob.printASL(true);
    }
}
