package image.bean;

import lombok.Data;


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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Estatus getEstatus() {
        return estatus;
    }

    public void setEstatus(Estatus estatus) {
        this.estatus = estatus;
    }
}
