package com.my_company.settings;

import java.util.*;
import java.util.Map.Entry;
//import java.util.HashMap;
import java.io.*;

public class Settings {
    HashMap<String, Integer> settings = new HashMap<String, Integer>();

    public Settings() {

    }

    public void put(String str, int num) {
        System.out.println("the file was read!");
        settings.put(str, num);
    }

    public int get(String str) {
        return settings.get(str);
    }

    public void delete(String str) {
        settings.remove(str);
    }

    public void loadFromBinaryFile(String filename) {
        // https://metanit.com/java/tutorial/6.3.php
        settings.clear();
        String str = filename + ".bin";
        try (DataInputStream dos = new DataInputStream(new FileInputStream(str))) {
            int size = dos.readInt();
            for (int i = 1; i <= size; i++) {
                put(dos.readUTF(), dos.readInt());
            }
            System.out.println("the file was read!");
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }

    }

    public void saveToBinaryFile(String filename) {
        String str = filename + ".bin";
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(str))) {
            Iterator<Entry<String, Integer>> it = settings.entrySet().iterator();
            dos.writeInt(settings.size());
            while (it.hasNext()) {
                Entry<String, Integer> pair = it.next();
                dos.writeUTF(pair.getKey());
                dos.writeInt(pair.getValue());
            }
            System.out.println("File has been written!");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void loadFromTextFile(String filename) {
        settings.clear();
        String str = filename + ".txt";
        StringBuilder strBuffer = new StringBuilder();
        try (FileReader reader = new FileReader((str))) {
            int c = 0;
            char[] buf = new char[256];
            while ((c = reader.read(buf)) > 0) {
                if (c < 256) {
                    buf = Arrays.copyOf(buf, c);
                }
                strBuffer.append(buf);
            }
            System.out.println("the file was read!");
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
        Scanner scanner = new Scanner(strBuffer.toString());
        while (scanner.hasNext()) {
            put(scanner.next(), scanner.nextInt());
        }

    }

    public void saveToTextFile(String filename) { // вроде сделал
        String str = filename + ".txt";
        try (FileWriter writer = new FileWriter(str, false)) {
            Iterator<Entry<String, Integer>> it = settings.entrySet().iterator();
            while (it.hasNext()) {
                Entry<String, Integer> pair = it.next();
                writer.write(pair.getKey());
                writer.append(' ');
                writer.append(Integer.toString(pair.getValue()));
                writer.append('\n');
            }
            writer.flush();
            System.out.println("File has been written");
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }

    public void clearSetting() { // вроде сделал
        settings.clear();
    }

    public String toString() {
        StringBuilder strBuffer = new StringBuilder();
        Iterator<Entry<String, Integer>> it = settings.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, Integer> pair = it.next();
            strBuffer.append("Key " + pair.getKey() + " " + pair.getValue() + "\n");
        }
        return strBuffer.toString();
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (!(o instanceof Settings)) {
            return false;
        }
        Settings a = (Settings) o;
        return a.settings.equals(this.settings);

    }
}
