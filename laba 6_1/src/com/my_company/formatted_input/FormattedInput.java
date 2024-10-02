package com.my_company.formatted_input;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FormattedInput {

    public static Object[] scanf(String format) {
        List<Object> list = new ArrayList<Object>();
        ArrayList<String> inputFormat = new ArrayList<String>();
        String strFormat;
        for (int i = 0; i < format.length(); i++) {
            if (format.charAt(i) == '%') {
                i++;
                strFormat = new String(new char[]{format.charAt(i)});
                inputFormat.add(strFormat);
            }

        }
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < inputFormat.size(); i++) {
            System.out.println(inputFormat.get(i));

        }

        int i = 0;
        char chArray;
        while (scanner.hasNext()) {
            chArray = inputFormat.get(i).charAt(0);

            if (chArray == 'd') { // dig
                if (scanner.hasNextInt()) {
                    System.out.println("viaf\n");
                    // Integer b = scanner.nextInt();
                    list.add(scanner.nextInt());
                } else {
                    while (scanner.hasNext()) {
                        if (!scanner.hasNextInt()) {
                            scanner.next();
                            System.out.println("You entered the data incorrectly!");
                        } else {
                            list.add(scanner.nextInt());
                            System.out.println("You entered the data correctly!");
                            // System.out.println(scanner.nextInt());
                            break;
                        }
                    }
                }
            }
            if (chArray == 'f') { // float
                if (scanner.hasNextFloat()) {
                    list.add(scanner.nextFloat());
                } else {
                    while (scanner.hasNext()) {
                        if (!scanner.hasNextFloat()) {
                            scanner.next();
                            System.out.println("You entered the data incorrectly!");
                        } else {
                            list.add(scanner.nextFloat());
                            System.out.println("You entered the data correctly!");
                            // System.out.println(scanner.nextInt());
                            break;
                        }
                    }
                }
            }
            if (chArray == 's') {// строка
                if (scanner.hasNext()) {
                    list.add(scanner.next());
                    System.out.println("-----sad-----\n");
                } else {
                    // ничего не надо,так как строкой могут быть и числа
                }
            }
            if (chArray == 'c') { // символ
                if (scanner.hasNext()) {
                    strFormat = scanner.next();
                    if (strFormat.length() == 1) {
                        // list.add(scanner.next());
                    } else {
                        while (strFormat.length() == 1) {
                            System.out.println("You entered the data incorrectly!");
                            scanner.close();
                            scanner = new Scanner(System.in);
                            if (scanner.hasNext()) {
                                strFormat = scanner.next();
                            }
                        }
                        System.out.println("You entered the data correctly!");
                        list.add(strFormat);
                    }

                }
            }

            i++;
            if (i > inputFormat.size() - 1) {
                break;
            }
        }
        scanner.close();
        for (int j = 0; j < list.size(); j++) {
            System.out.println("list =");
            System.out.println(list.get(j));
            System.out.println("\n");

        }
        return list.toArray();

    }

    public static Object[] sscanf(String format, String in) {
        List<Object> list = new ArrayList<Object>();
        ArrayList<String> inputFormat = new ArrayList<String>();
        String strFormat;
        for (int i = 0; i < format.length(); i++) {
            if (format.charAt(i) == '%') {
                i++;
                strFormat = new String(new char[]{format.charAt(i)});
                inputFormat.add(strFormat);
            }
        }
        Scanner scanner = new Scanner(in);
        int i = 0;
        char chArray;
        while (scanner.hasNext()) {

            chArray = inputFormat.get(i).charAt(0);
            if (chArray == 'd') { // dig

                if (scanner.hasNextInt()) {
                    System.out.println("viaf\n");
                    list.add(scanner.nextInt());
                } else {
                    while (scanner.hasNext()) {
                        System.out.println("You entered the data incorrectly!");
                        scanner.close();
                        scanner = new Scanner(System.in);
                        if (!scanner.hasNextInt()) {
                            scanner.next();

                        } else {
                            list.add(scanner.nextInt());
                            System.out.println("You entered the data correctly!");
                            // System.out.println(scanner.nextInt());
                            break;
                        }
                    }
                }
            }
            if (chArray == 'f') { // float
                if (scanner.hasNextFloat()) {
                    list.add(scanner.nextFloat());
                } else {
                    while (scanner.hasNext()) {
                        System.out.println("You entered the data incorrectly!");
                        scanner.close();
                        scanner = new Scanner(System.in);
                        if (!scanner.hasNextFloat()) {
                            scanner.next();
                        } else {
                            list.add(scanner.nextFloat());
                            System.out.println("You entered the data correctly!");
                            // System.out.println(scanner.nextInt());
                            break;
                        }
                    }
                }
            }
            if (chArray == 's') {// строка
                if (scanner.hasNext()) {
                    list.add(scanner.next());
                } else {
                    // ничего не надо,так как строкой могут быть и числа
                }
            }
            if (chArray == 'c') { // символ
                if (scanner.hasNext()) {
                    strFormat = scanner.next();
                    if (strFormat.length() == 1) {
                        list.add(scanner.next());
                    } else {
                        while (strFormat.length() == 1) {
                            System.out.println("You entered the data incorrectly!");
                            scanner.close();
                            scanner = new Scanner(System.in);
                            if (scanner.hasNext()) {
                                strFormat = scanner.next();
                            }
                        }
                        System.out.println("You entered the data correctly!");
                        list.add(strFormat);
                    }

                }
            }

            i++;
            if (i > inputFormat.size() - 1) {
                break;
            }
        }
        for (int j = 0; j < list.size(); j++) {
            System.out.println("list =");
            System.out.println(list.get(j));
            System.out.println("\n");

        }
        scanner.close();
        return list.toArray();
    }

}