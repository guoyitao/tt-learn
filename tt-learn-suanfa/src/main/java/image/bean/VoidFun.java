package image.bean;

@FunctionalInterface
public interface VoidFun<T> {

    void each(Vertex<T> i);
}
