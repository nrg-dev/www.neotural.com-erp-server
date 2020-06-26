package com.mynrg.util;

import java.util.HashMap;
import java.util.Map;

public class Test {

	public static void main(String args[]) {
		/*
		 * String name = "John Stanley"; String[] split = name.split(" ");
		 * 
		 * System.out.println("Fist Value ----:"+split[0]);
		 * System.out.println("Second value--->"+split[1]);
		 */
		double x;
		double y;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("x", 1112222222);
		map.put("y", "1112222222");
		x = Double.valueOf(map.get("x").toString());// second way.
		y = Double.valueOf(map.get("y").toString());// second way.
		System.out.println(x);
		System.out.println(y);


		/*
		 * Double d = (Double) map.get("x"); System.out.println(d);
		 * 
		 * if (map.get("x") instanceof Object) { x = ((Double)
		 * map.get("x")).doubleValue(); System.out.println(x);
		 * 
		 * }
		 * 
		 * if (map.get("y") instanceof Object) { y = ((Double)
		 * map.get("y")).doubleValue(); System.out.println(y);
		 * 
		 * }
		 */

	}
}
