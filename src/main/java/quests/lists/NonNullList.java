package quests.lists;

/**
 * @author Vyacheslav Doroshenko
 */

import java.util.*;

/**
 * @author Vyacheslav Doroshenko
 */
public class NonNullList<Element> implements List<Element> {

    private final ArrayList<Element> list;

    private NonNullList() {
        list = new ArrayList<Element>();
    }

    private NonNullList(int initialCapacity) {
        list = new ArrayList<Element>(initialCapacity);
    }

    private NonNullList(Collection<? extends Element> collection) {
        list = new ArrayList<Element>(collection);
    }

    public static <Element> NonNullList<Element> getInstance() {
        return new NonNullList<Element>();
    }

    public static <Element> NonNullList<Element> getInstance(int initialCapacity) {
        return new NonNullList<Element>(initialCapacity);
    }

    /**
     * @param collection Элементы которые будут добавлены в список
     * @return NonNullList с nonNull списком
     * @throws NullPointerException если коллекция содержит null элемент
     */
    public static <Element> NonNullList<Element> createFrom(Collection<? extends Element> collection) {
        if (collection.contains(null)) throw new NullPointerException("Список не поддерживает значения null");
        return new NonNullList<Element>(collection);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    @Override
    public Iterator<Element> iterator() {
        return list.iterator();
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public <T> T[] toArray( T[] a) {
        return list.toArray(a);
    }

    @Override
    public boolean add( Element element) {
        if (element == null) throw new NullPointerException("Список не поддерживает значения null");
        return list.add(element);
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) throw new NullPointerException("Список не поддерживает значения null");
        return list.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return list.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Element> c) {
        if (c.contains(null)) throw new NullPointerException("Список не поддерживает значения null");
        return list.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends Element> c) {
        if (c.contains(null)) throw new NullPointerException("Список не поддерживает значения null");
        return list.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return list.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return list.removeAll(c);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public Element get(int index) {
        return list.get(index);
    }

    @Override
    public Element set(int index, Element element) {
        if (element == null) throw new NullPointerException("Список не поддерживает значения null");
        return list.set(index, element);
    }

    @Override
    public void add(int index,  Element element) {
        if (element == null) throw new NullPointerException("Список не поддерживает значения null");
        list.add(index, element);
    }

    @Override
    public Element remove(int index) {
        return list.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) throw new NullPointerException("Список не поддерживает значения null");
        return list.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) throw new NullPointerException("Список не поддерживает значения null");
        return list.lastIndexOf(o);
    }

    @Override
    public ListIterator<Element> listIterator() {
        return list.listIterator();
    }

    @Override
    public ListIterator<Element> listIterator(int index) {
        return list.listIterator(index);
    }

    @Override
    public List<Element> subList(int fromIndex, int toIndex) {
        return list.subList(fromIndex, toIndex);
    }

    @Override
    public String toString() {
        return "NonNullList{" +
                "list=" + list +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NonNullList)) return false;
        NonNullList<?> that = (NonNullList<?>) o;
        return list.equals(that.list);
    }

    @Override
    public int hashCode() {
        return 31 * list.hashCode();
    }


}
