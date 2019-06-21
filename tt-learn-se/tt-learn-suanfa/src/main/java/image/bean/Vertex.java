package image.bean;

import lombok.Data;

//顶点
/*
* dfTime的作用:
*     可以算出任意两个顶点直接是否存在是否存在血缘关系
* 定义 活动期 activit[u] = dTime(u) to fTime(u)
*
* u是v的后代 ？ activit[v] 包含 activit[u]
* u是v的祖先 ？  activit[u] 包含 activit[v]
* u和v 无关 ？ activit[u] 交 activit[v] == 空集
*
* */
@Data
public class Vertex<T> {
    T data;
    int inDegree,outDegree; //出入度

    VStatus status;
    int dTime,fTime; //时间标签
    int parent; //在遍历树中的父节点
    int priority; // 在遍历树中的优先级（最短通路、极短跨边等）


    public Vertex(T data) {
        this.data = data;
        this.inDegree = 0;
        this.outDegree = 0;
        this.dTime = -1;
        this.fTime = -1;
        this.priority = Integer.MAX_VALUE;
        this.status = VStatus.UNDISCOVERED;
        this.parent = -1;
    }
}
