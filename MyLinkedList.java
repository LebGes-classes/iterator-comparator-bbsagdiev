import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<E> implements MyList<E> {
    private Node<E> head;
    private int size;

    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
            this.next = null;
        }
    }

    public MyLinkedList() {
        head = null;
        size = 0;
    }

    @Override
    public void add(E element) {
        Node<E> newNode = new Node<>(element);
        if (head == null) {
            head = newNode;
        } else {
            Node<E> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> current = head;
        if (index == 0) {
            E data = head.data;
            head = head.next;
            size--;
            return data;
        }
        Node<E> prev = null;
        for (int i = 0; i < index; i++) {
            prev = current;
            current = current.next;
        }
        prev.next = current.next;
        size--;
        return current.data;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // Реализация итератора
    private class LinkedListIterator implements Iterator<E> {
        private Node<E> current = head;
        private Node<E> lastReturned = null;
        private Node<E> previous = null;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            previous = lastReturned; // Запоминаем предыдущий узел
            lastReturned = current;
            current = current.next;
            return lastReturned.data;
        }

        @Override
        public void remove() {
            if (lastReturned == null) {
                throw new IllegalStateException();
            }
            if (lastReturned == head) { // Удаление головы
                head = current;
            } else {
                previous.next = current;
            }
            size--;
            lastReturned = null;
        }
    }

    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }
}
