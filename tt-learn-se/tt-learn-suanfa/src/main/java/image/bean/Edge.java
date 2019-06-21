package image.bean;

import lombok.Data;

@Data
public class Edge<T> {
    T data; //数据
    int weight; //权重
    Estatus estatus;

    //构造新边
    public Edge(T data, int weight) {
        this.data = data;
        this.weight = weight;
        this.estatus = Estatus.UNDETERMINED; //待定的
    }
}
