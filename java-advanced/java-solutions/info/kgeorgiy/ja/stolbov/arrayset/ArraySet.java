package info.kgeorgiy.ja.stolbov.arrayset;

import java.util.*;

import static java.util.Collections.binarySearch;

public class ArraySet<E> extends AbstractSet<E> implements SortedSet<E> {

    private final List<E> array;
    private Comparator<? super E> comparator;
    private final Comparator<? super E> DEFAULT_COMPARATOR = Collections.reverseOrder().reversed();

    public ArraySet() {
        this.array = new ArrayList<>();
        this.comparator = null;
    }

    public ArraySet(Collection<? extends E> array, Comparator<? super E> comparator) {
        TreeSet<E> treeSet = new TreeSet<>(comparator);
        treeSet.addAll(array);
        this.array = new ArrayList<>(treeSet);
        this.comparator = comparator;
    }

    public ArraySet(Comparator<? super E> comparator) {
        this.array = new ArrayList<>();
        this.comparator = comparator;
    }

    public ArraySet(Collection<? extends E> array) {
        this.array = new ArrayList<>(new TreeSet<>(array));
        this.comparator = null;
    }

    @Override
    public int size() {
        return array.size();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean contains(Object o) {
        return binarySearch(array, (E) o, comparator) >= 0;
    }

    @Override
    public Iterator<E> iterator() {
        return array.iterator();
    }

    @Override
    public Comparator<? super E> comparator() {
        return comparator;
    }

    @Override
    public ArraySet<E> subSet(E fromElement, E toElement) {
        if (comparator == null) {
            comparator = DEFAULT_COMPARATOR;
        }
        if (comparator.compare(fromElement, toElement) > 0) {
            throw new IllegalArgumentException("Wrong elements");
        }
        return new ArraySet<>(array.subList(findIndex(fromElement), findIndex(toElement)), comparator);
    }


    private int findIndex(final E element) {
        int ind = binarySearch(array, element, comparator);
        if (ind >= 0) {
            return ind;
        } else {
            return -ind - 1;
        }
    }

    @Override
    public SortedSet<E> headSet(E toElement) {
        return new ArraySet<E>(array.subList(0, findIndex(toElement)), comparator);
    }

    @Override
    public SortedSet<E> tailSet(E fromElement) {
        return new ArraySet<E>(array.subList(findIndex(fromElement), array.size()), comparator);
    }

    @Override
    public E first() {
        return array.getFirst();
    }

    @Override
    public E last() {
        return array.getLast();
    }

}