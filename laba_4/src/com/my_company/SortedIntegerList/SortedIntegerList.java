package com.my_company.SortedIntegerList;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class SortedIntegerList {

    LinkedList<Integer> sortedList = new LinkedList<Integer>();
    int number = 0;
    boolean isRepeatElements = true;

    public SortedIntegerList(boolean isRepElemet) {
        isRepeatElements = isRepElemet;
    }

    SortedIntegerList(int isRepElemet) {
        try {
            if (isRepElemet != 1 || isRepElemet != 0) {
                throw new Exception("Only 1 or 0!");
            }
            if (isRepElemet == 1) {
                isRepeatElements = true;
            } else {
                isRepeatElements = false;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    int size() {
        return sortedList.size();
    }

    void add(int num) {
        if (size() == 0) {
            sortedList.add(num);
            return;
        }
        int index = 0;
        int indexSet = 0;
        for (ListIterator<Integer> i = sortedList.listIterator(); i.hasNext();) {
            Integer element = (Integer) i.next();
            if (element.intValue() == num) {
                if (!isRepeatElements) {
                    return;
                }
            }
            if (num >= element.intValue()) {
                indexSet = index + 1; // i+1
                //i.add(8);

            } else {
                indexSet = index;
                break;
            }
            index++;
        }
        sortedList.add(indexSet, num);
    }

    public void add1(int num) {
        if (size() == 0) {
            sortedList.add(num);
            return;
        }
        ListIterator<Integer> i = sortedList.listIterator();
        while (i.hasNext()) {
            Integer element = (Integer) i.next();
            if (element.intValue() == num) {
                if (!isRepeatElements) {
                    return;
                }
            }
            if (num <= element.intValue()) {
                i.previous();
                i.add(num);
                return;

            }
        }
        //i.previous();
        i.add( num);
    }

    public void remove(int num) {
        int index = 0;
        int indexSet = -1;
        for (Iterator<Integer> i = sortedList.iterator(); i.hasNext();) {
            Integer element = (Integer) i.next();
            if (element.intValue() == num) {
                indexSet = index;
                i.remove();
                return;
            }

            index++;
        }
        if (indexSet != -1) {
            sortedList.remove(indexSet);
        }

    }

    public void print() {
        for (Iterator<Integer> i = sortedList.iterator(); i.hasNext();) {
            Integer element = (Integer) i.next();
            System.out.print(element + "  ");
        }
        System.out.print("\n");
    }

    Iterator<Integer> iteratorIntegerList() {
        return sortedList.iterator();
    }

    public boolean equals(Object o) {
        SortedIntegerList a = (SortedIntegerList) o;
        if (this.hashCode() != o.hashCode()) {
            return false;
        }
        if (o instanceof SortedIntegerList) {
            if (size() != a.size()) {
                return false;
            } else {
                Iterator<Integer> i1 = sortedList.iterator();
                Iterator<Integer> i2 = a.iteratorIntegerList();

                while (i1.hasNext()) {
                    Integer element1 = (Integer) i1.next();
                    Integer element2 = (Integer) i2.next();
                    if (element1 != element2) {
                        return false;
                    }
                }
                return true;
            }
        } else {
            return false;
        }
    }
}
