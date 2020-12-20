package suanfa.tanxin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 假设存在下面需要付费的广播台，以及广播台信号可以覆盖的地区。 如何选择最少的广播台，让所有的地区都可以接收到信号
 * 广播台	覆盖地区
 * K1	"北京", "上海", "天津"
 * K2	"广州", "北京", "深圳"
 * K3	"成都", "上海", "杭州"
 * K4	"上海", "天津"
 * K5	"杭州", "大连"
 * <p>
 * 贪婪算法所得到的结果不一定是最优的结果(有时候会是最优解)，但是都是相对近似(接近)最优解的结果
 * 比如上题的算法选出的是K1, K2, K3, K5，符合覆盖了全部的地区
 * 但是我们发现 K2, K3,K4,K5 也可以覆盖全部地区，如果K2 的使用成本低于K1,那么我们上题的 K1, K2, K3, K5 虽然是满足条件，但是并不是最优的.
 * <p>
 * description: 贪心算法，贪婪算法，解决集合覆盖问题
 * author: Guo Yitao
 * create: 2020-08-06 10:45
 **/
public class GreedyAlgorithm {
    //创建广播电台,放入到Map
    static HashMap<String, HashSet<String>> broadcasts = new HashMap<String, HashSet<String>>();
    //allAreas 存放所有的地区
    static HashSet<String> allAreas = new HashSet<String>();
    //创建ArrayList, 存放选择的电台集合
    static ArrayList<String> selects = new ArrayList<String>();
    //定义一个临时的集合， 在遍历的过程中，存放遍历过程中的电台覆盖的地区和当前还没有覆盖的地区的交集
    static HashSet<String> tempSet = new HashSet<String>();

    static {
        //将各个电台放入到broadcasts
        HashSet<String> hashSet1 = new HashSet<String>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<String>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<String>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");

        HashSet<String> hashSet4 = new HashSet<String>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<String>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        //加入到map
        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);

        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");

    }

    public static void main(String[] args) {
        String maxKey = null;
        while (allAreas.size() != 0) { //如果还没有覆盖到全部地区
            //每进行一次while,需要
            maxKey = null;

            for (String key : broadcasts.keySet()) {
                tempSet.clear();
                //当前这个key能够覆盖的地区
                HashSet<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);
                tempSet.retainAll(allAreas); //求出tempSet 和   allAreas 集合的交集, 交集会赋给 tempSet
                // tempSet.size() >broadcasts.get(maxKey).size()) 体现出贪心算法的特点,每次都选择最优的
                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())) {
                    maxKey = key;
                }
            }
            //maxKey != null, 就应该将maxKey 加入selects
            if (maxKey != null) {
                selects.add(maxKey);
                //将maxKey指向的广播电台覆盖的地区，从 allAreas 去掉
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }

        System.out.println("得到的选择结果是" + selects);//[K1,K2,K3,K5]
    }
}
