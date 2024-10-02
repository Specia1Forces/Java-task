package com.my_company.laba_4;


import com.my_company.SortedIntegerList.SortedIntegerList;


public class Main {
    public static void main(String[] args) {
        /*
         * SortedIntegerList l1 = new SortedIntegerList(true); Scanner in = new
         * Scanner(System.in); int num = 0; System.out.println("Введите значения:\n");
         * for(int i=0;i<10;i++) { num = in.nextInt(); l1.add(num); }
         * System.out.println("list1: \n"); l1.print();
         */
        SortedIntegerList l1 = new SortedIntegerList(true);
        SortedIntegerList l2 = new SortedIntegerList(true);
        SortedIntegerList l3 = new SortedIntegerList(true);

		/*for (int i = 20; i >= 0; i--) {

			l1.add(i % 5);
			l2.add(i % 6);
			l3.add(i % 5);
		}*/
        for (int i = 10; i >= 0; i--) {
            l1.add1(i % 7);
            l2.add1(i % 6);
            l3.add1(i % 8);
        }
        System.out.println("list1: \n");
        l1.print();
        System.out.println("list2 :\n");
        l2.print();
        System.out.println("list3 :\n");
        l3.print();

        l1.remove(25);
        l2.remove(3);
        l3.remove(2);

        System.out.println("list1: \n");
        l1.print();
        System.out.println("list2 :\n");
        l2.print();
        System.out.println("list3 :\n");
        l3.print();

        System.out.println("l2.equals(l3): " + l2.equals(l3));
        System.out.println("l1.equals(l3): " + l1.equals(l3));

    }
}
