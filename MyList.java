public interface MyList<E> {
    void add(E element);
    E get(int index);
    E remove(int index);
    int size();
    boolean isEmpty();
}
