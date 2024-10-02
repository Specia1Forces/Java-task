package com.my_company.main;


import com.my_company.settings.Settings;

import java.io.*;

public class Main {
	public static void main(String[] args) {
		Settings states = new Settings();
		states.put("Germany", 1);
		states.put("Spain", 2);
		states.put("France", 3);
		states.put("Italy", 4);
		System.out.println(states.toString());
		System.out.println("------------");
		System.out.println("Удалить элемент и получить ");
		System.out.println(states.get("Spain"));
		states.delete("Spain");
		System.out.println("------------");
		System.out.println(states.toString());
		System.out.println("------------");
		states.saveToBinaryFile("Country");
		System.out.println("------------");
		states.clearSetting();
		System.out.println("------------");
		System.out.println(states.toString());
		states.loadFromBinaryFile("Country");
		System.out.println("------------");
		System.out.println(states.toString());
		states.saveToTextFile("Countr");
		System.out.println("------------");
		states.clearSetting();
		states.loadFromTextFile("Countr");
		System.out.println("------------");
		System.out.println(states.toString());


	}
}