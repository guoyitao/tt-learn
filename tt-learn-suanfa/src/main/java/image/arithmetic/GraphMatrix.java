package image.arithmetic;


import image.bean.*;
import org.junit.Test;

import java.util.*;

/**
 * @Description: 图 数据结构 邻接矩阵 TODO 时间标签实现错误
 * i\j A B C
 *  A  X X X
 *  B  X X X
 *  C  X X X
 * ABC是顶点 X是边
 * @Author: guo
 * @CreateDate: 2019/5/4
 * @UpdateUser:
 */
public class GraphMatrix<Tv, Te> {

    private LinkedList<Vertex<Tv>> V; //顶点集
    private LinkedList<LinkedList<Edge<Te>>> E;//边集

    private int eSize = 0; //边的计数

    public GraphMatrix() {
        V = new LinkedList<>();
        E = new LinkedList<>();
    }


    //顶点数据
    public Tv vertex(int i) {
        return V.get(i).getData();
    }

    //顶点入度
    public int inDegree(int i) {
        return V.get(i).getInDegree();
    }

    //顶点出度
    public int outDegree(int i) {
        return V.get(i).getOutDegree();
    }

    //顶点状态
    public VStatus vstatus(int i) {
        return V.get(i).getStatus();
    }

    public void setVstatus(int i, VStatus vstatus){
        V.get(i).setStatus(vstatus);
    }

    //时间标签dTime
    public int dTime(int i) {
        return V.get(i).getdTime();
    }

    public int increaseVDTime(int i){
        int i1 = dTime(i) + 1;
        V.get(i).setdTime(i1);
        return  i1;
    }

    //时间标签fTime
    public int fTime(int i) {
        return V.get(i).getfTime();
    }

    //在遍历树中的父亲顶点
    public int parent(int i) {
        return V.get(i).getParent();
    }

    public int setParent(int i,int parnet) {
        V.get(i).setParent(parnet);
        return parnet;
    }

    //优先级数
    public int priority(int i) {
        return V.get(i).getPriority();
    }

    //对于任意顶点i，枚举出所有临接顶点
    public int nextNbr(int i, int j) { //逆向顺序查找，O（n）
        while ((-1 < j) && !exists(i, --j)) {

        }
        return j;
    }

    //第一个邻居顶点
    public int firstNbr(int i) {
        return nextNbr(i, V.size());
    }

    //判断边是否存在
    //因为矩阵是正方形所有用 E.size()或者V.size()都是一样的
    public boolean exists(int i, int j) {
        return ((i >= 0) && (i < V.size()) && (j >= 0) && (j < E.size()) && (E.get(i).get(j)) != null);
    }

    //边（i,j）的数据
    public Te edge(int i, int j) {
        return E.get(i).get(j).getData();
    }

    //边的状态
    public Estatus EStatus(int i, int j) {
        return E.get(i).get(j).getEstatus();
    }

    public Estatus setEStatus(int i, int j,Estatus estatus) {
        E.get(i).get(j).setEstatus(estatus);
        return estatus;
    }

    //边的权重
    public int weight(int i, int j) {
        return E.get(i).get(j).getWeight();
    }

    //边插入
    public boolean insertEdge(Te edge, int w, int i, int j) {
        if (exists(i, j)) {
            return false;
        } //如果已经存在就不添加
        E.get(i).set(j, new Edge<Te>(edge, w)); //创建新的边

        eSize++; //更新边的计数

        increaseVertexOutDegree(i);
        increaseVertexInDegree(j);

        return true;
    }

    //删除顶点i和j之间的连边
    public Te removeEdge(int i, int j) {
        Te edgeBak = edge(i, j);
        E.get(i).set(j, null); //删除边E（i,j）
        eSize--; //更新边的计数

        decreaseVertexOutDegree(i);
        decreaseVertexInDegree(j);

        return edgeBak;
    }

    //插入顶点，返回编号(插入list末端)
    public int insertVertex(Tv vertex){
        //插入顶点
        V.addLast(new Vertex<Tv>(vertex));

        //创建空边行，插入边集最后
        LinkedList<Edge<Te>> e = new LinkedList<Edge<Te>>();
        E.addLast(e);
        for (int i = 0; i < V.size(); i++) {
            e.addLast(null);

            //每行添加1个长度
            //因为这时候E[].size() 已经确定不变了，但是新添加的E[]已经等于V.size()所以不用在后面加个null
            //所以只要不是最后一行就再后面添null就好了
            if (i != V.size()){
                E.get(i).addLast(null);
            }

        }
        //返回行号
        return V.size() -1;
    }

    //删除顶点
    //还可优化  TODO
    public Tv removeVertex(int i){
        for (int j = 0; j < V.size(); j++) {
            if (exists(i,j)){ //删除所有出边
//                E.get(i).remove(j); //这步应该是多此一举，直接删行 == 把里面的每个边删掉

                eSize--;
                decreaseVertexInDegree(j);
            }
        }
        E.remove(i); //直接删除行

        //真正的删除顶点
        Vertex<Tv> removeV = V.remove(i);

        for (int j = 0; j < V.size(); j++) {
            if (exists(j,i)){ //删除所有入边及第i列
                E.get(j).remove(i);

                eSize--;
                decreaseVertexOutDegree(j);
            }
        }


        return removeV.getData();

    }


    /**
     * BFS 广度优先搜索
     * 定义一个先进先出的queue
     * 把初始化起始点先入队
     * 把点从队列从一个顶点出发枚举与他有关联的点，并把有关联的点入队
     * 在发现点的同时设置状态和判断  已经被发现（正在队列中），或者甚至已经访问完毕，或者为跨边的
     *
     * 在这里是有向图可能会有不可达点，所以在这个方法外面包一层循环枚举所有边就可以全部访问
     *
     * @author guoyitao
     * @date 2019/5/5 14:21
     * @params
     * @return
     */
    public void BFS(int startV, VoidFun voidFun){
        LinkedList<Integer> queue = new LinkedList<Integer>(); //遵循现进先出
        setVstatus(startV,VStatus.DISOVERED);
        queue.addFirst(startV); //队列初始化，入队

        while (!queue.isEmpty()){
            Integer v = queue.removeLast(); //从队尾取出
            increaseVDTime(v);
            for (int u = firstNbr(v); u > -1 ; u = nextNbr(v,u)) { //发现邻居
                if (VStatus.UNDISCOVERED == vstatus(u)){ //if not found
                    setVstatus(u,VStatus.DISOVERED);
                    queue.addFirst(u); //发现，入队
                    setEStatus(v,u,Estatus.TREE); //将边状态设置为连通
                    setParent(u,v); //引入数边
                }else{ //如果 已经被发现（正在队列中），或者甚至已经访问完毕
                    setEStatus(v,u,Estatus.CROSS); //归列于跨边
                }
            }
            voidFun.each(V.get(v)); //访问回调
            setVstatus(v,VStatus.VISITED);//访问完毕
        }
    }

    /*
    * DFS 深度优先搜索
    *
    * */
    public void DFS(int startV, VoidFun voidFun){
        setVstatus(startV,VStatus.DISOVERED);
        increaseVDTime(startV);

        for (int u = firstNbr(startV); u > -1 ; u = nextNbr(startV,u)) { //发现邻居
            switch (vstatus(u)){
                case UNDISCOVERED: //u尚未被发现
                    setEStatus(startV,u,Estatus.TREE);
                    setParent(u,startV);
                    DFS(startV, voidFun); //递归
                    break;
                case DISOVERED: //u已经被发现但是尚未被访问，应属于被后代指向的祖先
                    setEStatus(startV,u,Estatus.BACKWARD);
                    break;
                default: //u已经访问完毕，就视为前向边(遍历树的祖先节点，向前指向后代)，或者是跨边（嫡系（表兄弟）边）
                    setEStatus(startV,u,dTime(startV)<dTime(u) ? Estatus.FORWARD : Estatus.CROSS);
                    break;
            }
        }

        voidFun.each(V.get(startV)); //访问回调
        setVstatus(startV,VStatus.VISITED);//访问完毕
    }

    //递增关联i的入度
    private void increaseVertexInDegree(int i) {
        Vertex<Tv> jVertex = V.get(i);
        jVertex.setInDegree(jVertex.getInDegree() + 1);
    }

    //递增关联i的出度
    private void increaseVertexOutDegree(int i) {
        Vertex<Tv> iVertex = V.get(i);
        iVertex.setOutDegree(iVertex.getOutDegree() + 1);
    }

    //递减关联i的入度
    private void decreaseVertexInDegree(int i) {
        Vertex<Tv> jVertex = V.get(i);
        jVertex.setInDegree(jVertex.getInDegree() - 1);
    }

    //递减关联i的出度
    private void decreaseVertexOutDegree(int i) {
        Vertex<Tv> iVertex = V.get(i);
        iVertex.setOutDegree(iVertex.getOutDegree() - 1);
    }

    /**
     *
     * 创建图 6个顶点（0~6）
     *      0-1{
     *        1-2,
     *        1-3,
     *        1-4{
     *          4-5,
     *          5-6
     *        }
     *     }
     *
     * @author guoyitao
     * @date 2019/5/5 14:18
     * @params
     * @return
     */
    public static GraphMatrix<Integer,Integer> buildGraph(){
        GraphMatrix<Integer,Integer> graphMatrix = new GraphMatrix<>();
        for (int i = 0; i < 7; i++) {
            graphMatrix.insertVertex(i);
        }
        graphMatrix.insertEdge(50,2,0,1);
        graphMatrix.insertEdge(100,3,1,2);
        graphMatrix.insertEdge(150,4,1,3);
        graphMatrix.insertEdge(200,5,1,4);
        graphMatrix.insertEdge(250,6,4,5);
        graphMatrix.insertEdge(300,7,4,6);
        return graphMatrix;
    }

    public void printGraph(){
        System.out.print("\t");
        for (int i = 0; i < V.size(); i++) {
            System.out.print(V.get(i).getData()+ "\t");

        }
        System.out.println();

        for (int i = 0; i < V.size(); i++) {
            System.out.print(V.get(i).getData()+ "\t");
            for (Edge<Te> integerEdge : E.get(i)) {
                if (integerEdge != null){
                    System.out.print(integerEdge.getData() +"\t");
                }else{
                    System.out.print("-1" +"\t");
                }
            }
            System.out.println();
        }
    }

    @Test
    public void testBFS(){
        GraphMatrix<Integer, Integer> graphMatrix = buildGraph();
        graphMatrix.BFS(1, new VoidFun() {
            @Override
            public void each(Vertex i) {
                System.out.println(i.getData());
            }
        });
    }

    @Test
    public void testDFS(){
        GraphMatrix<Integer, Integer> graphMatrix = buildGraph();
        graphMatrix.DFS(1, new VoidFun() {
            @Override
            public void each(Vertex i) {
                System.out.println(i.getData());
            }
        });
    }

    @Test
    public void testPrint(){
        GraphMatrix<Integer, Integer> graphMatrix = buildGraph();
        graphMatrix.printGraph();
    }




}
