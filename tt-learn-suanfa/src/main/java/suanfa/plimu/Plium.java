package suanfa.plimu;

import java.util.Arrays;

/**
 * 有胜利乡有7个村庄(A, B, C, D, E, F, G) ，现在需要修路把7个村庄连通
 * 各个村庄的距离用边线表示(权) ，比如 A – B 距离 5公里
 * 问：如何修路保证各个村庄都能连通，并且总的修建公路总里程最短?
 * 思路: 将10条边，连接即可，但是总的里程数不是最小.
 * 正确的思路，就是尽可能的选择少的路线，并且每条路线最小，保证总里程数最少.
 * <p>
 * description: 普利姆算法求最少生成树 修路问题
 * author: Guo Yitao
 * create: 2020-08-06 11:33
 **/
public class Plium {

	public static void main(String[] args) {
		char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
		int verxs = data.length;

		//10000表示不连通
		int[][] weight = new int[][]{
				{10000, 5, 7, 10000, 10000, 10000, 2},
				{5, 10000, 10000, 9, 10000, 10000, 3},
				{7, 10000, 10000, 10000, 8, 10000, 10000},
				{10000, 9, 10000, 10000, 10000, 4, 10000},
				{10000, 10000, 8, 10000, 10000, 5, 4},
				{10000, 10000, 10000, 4, 5, 10000, 6},
				{2, 3, 10000, 10000, 4, 6, 10000},};

		MGraph graph = new MGraph(verxs);
		MinTree minTree = new MinTree();
		minTree.createGraph(graph, verxs, data, weight);
		minTree.showGraph(graph);

		minTree.prim(graph,0);
	}
}

class MinTree {

	/**
	 * @param graph  图对象
	 * @param verxs  节点个数
	 * @param data   数据
	 * @param weight 图的邻接矩阵
	 * @return void
	 * @date 2020/8/6 12:33
	 * @author guoyitao
	 */
	public void createGraph(MGraph graph, int verxs, char[] data, int[][] weight) {
		int i, j;
		for (i = 0; i < verxs; i++) {
			graph.data[i] = data[i];
			for (j = 0; j < verxs; j++) {
				graph.weight[i][j] = weight[i][j];
			}
		}
	}

	public void showGraph(MGraph mGraph) {
		for (int[] ints : mGraph.weight) {
			System.out.println(Arrays.toString(ints));
		}
	}

	/**
	 *
	 * @date 2020/8/6 12:41
	 * @author guoyitao
	 * @param graph 图
	 * @param v data[] 的下表，表示哪个顶点  A->0   B->1
	 * @return void
	 */
	public void prim(MGraph graph,int v){
		int visited[] = new int[graph.verxs];
		//访问起始点
		visited[v] = 1;

		int h1 = -1;
		int h2 = -1;
		int minWeight = 10000; //初始化
		for(int k = 1; k < graph.verxs; k++) { //算法结束之后有 graph.verxs条边

			//确定每一次生成的子图，和那个节点最近
			for(int i = 0; i < graph.verxs; i++) {// i节点表示被访问过的节点
				for(int j = 0; j< graph.verxs;j++) {//j节点表示还没被访问过的节点
					if(visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
						//替换minWeight（寻找已经访问过的节点和未访问过的节点的最小的边）
						minWeight = graph.weight[i][j];
						h1 = i;
						h2 = j;
					}
				}
			}
			//找到一条最小的边
			System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + "> 值:" + minWeight);
			//将当前节点标识成 已经访问
			visited[h2] = 1;
			//minWeight 归位
			minWeight = 10000;
		}
	}
}


class MGraph {
	int verxs; //节点个数
	char[] data;  //节点
	int[][] weight; //存放边

	public MGraph(int verxs) {
		this.verxs = verxs;
		this.data = new char[verxs];
		this.weight = new int[verxs][verxs];
	}
}
