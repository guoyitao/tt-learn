package image.arithmetic;

import lombok.AllArgsConstructor;

/**
 * 二叉搜索树
 * 描述：{
 *     顺序性：任意1节点的  左后代都比这个节点小，右后代都比这个节点大
 *     单调性：BST的中序遍历序列，必然单调非降
 *
 * }
 *
 *
 * @Description:
 * @Author: guo
 * @CreateDate: 2019/5/5
 * @UpdateUser:
 */
public class MyBST {

    @AllArgsConstructor
    class Entry<V>{
        Integer key;
        V value;

        @Override
        protected Entry<V> clone() throws CloneNotSupportedException {
            return new Entry(this.key,this.value);
        }

        public boolean isBiggerThan(Entry e){
            return this.key > e.key;
        }

        public boolean isSmallerThan(Entry e){
            return this.key < e.key;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Entry){
                return this.key.equals(((Entry) obj).key);
            }
            return super.equals(obj);
        }


        public boolean isNotequals(Object obj) {
            if (obj instanceof Entry){
                return !this.key.equals(((Entry) obj).key);
            }
            return super.equals(obj);
        }
    }


}
