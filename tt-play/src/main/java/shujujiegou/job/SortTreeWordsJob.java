package shujujiegou.job;

import cn.hutool.core.io.FileUtil;
import shujujiegou.util.Util;

import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class SortTreeWordsJob implements WordsJob {
    static class Count implements Comparable<Count> {
        String keyword;
        int count;

        @Override
        public String toString() {
            return keyword + "\t" + count;
        }

        @Override
        public int compareTo(Count o) {
            if (o.keyword.equals(this.keyword)){
                return 0;
            }
            return this.keyword.compareTo(o.keyword);
        }
    }

    private BinarySearchTree<Count> sortedBinTree = new BinarySearchTree<>();
    private String context;
    private String output;
    private int lowCount;

    public SortTreeWordsJob(String input, String output, int lowCount) {
        System.out.println("---------------二叉排序树--------------------");
        this.output = output;
        this.lowCount = lowCount;
        this.context = Util.readFileAs(input);
    }

    @Override
    public void countWords() {
        StringTokenizer stringTokenizer = new StringTokenizer(context, ".,# ");
        while (stringTokenizer.hasMoreTokens()) {
            String s = stringTokenizer.nextToken();
            if (Util.isEnglish(s)) {
                addWord(s);
            }
        }
//        System.out.println();
    }

    private void addWord(String s) {
        Count count = new Count();
        count.keyword = s;
        count.count = 1;
        TreeNode<Count> node = sortedBinTree.get(count);
        if (node == null) {
            sortedBinTree.insert(count);
        } else {
            Count c = node.getData();
            c.count++;
        }
    }

    @Override
    public void outputToFile() {
        FileUtil.del(new File(output));
        List<Count> collect = sortedBinTree.inorderTraversal().stream().sorted(new Comparator<Count>() {
            @Override
            public int compare(Count o1, Count o2) {
                return o1.count - o2.count;
            }
        }).collect(Collectors.toList());
        FileUtil.appendUtf8Lines(collect, new File(output));
    }

    @Override
    public void deleteLowWord(boolean isPrint) {
        List<Count> counts = sortedBinTree.inorderTraversal();
        for (Count count : counts) {
            if (count.count< lowCount){
                if (isPrint){
                    System.out.printf("低于%d的单词：key=%s value=%d\n",lowCount,count.keyword,count.count );
                }
                boolean delete = sortedBinTree.delete(count);
                if (!delete){
                    System.out.printf("低于%d的单词：key=%s value=%d\n",lowCount,count.keyword,count.count );
                }
            }
        }
    }

    @Override
    public void printASL(boolean isPrint) {
        if (isPrint) {
            System.out.println("二叉搜索树ASL:" + sortedBinTree.getASL());
        }
    }

    @Override
    public void clear() {
        sortedBinTree = new BinarySearchTree<>();
    }

    public static void main(String[] args) {
        String input = "C:\\mycode\\桌面快捷资料\\计量大学上课资料\\计量大学上课资料\\算法数据结构\\qimo8\\words.txt";
        String output = "C:\\mycode\\桌面快捷资料\\计量大学上课资料\\计量大学上课资料\\算法数据结构\\qimo8\\OutFile.txt";
        SortTreeWordsJob sortTreeWordsJob = new SortTreeWordsJob(input, output, 5);
        sortTreeWordsJob.countWords();
        sortTreeWordsJob.deleteLowWord(true);
        sortTreeWordsJob.outputToFile();
        sortTreeWordsJob.printASL(true);
    }
}
