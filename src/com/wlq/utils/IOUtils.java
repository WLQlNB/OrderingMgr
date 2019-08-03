package com.wlq.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import com.wlq.pojo.Dish;
import com.wlq.pojo.OrderingSet;

public class IOUtils {
	public static List<OrderingSet> list = new ArrayList<OrderingSet>();
	public static List<Dish> dishList = new ArrayList<Dish>();
	public static int count = 1;
	
	public static String readDishData() {
		File file = new File("src/com/wlq/utils/dish.txt");
		String str = null;
		String strs = "";
		Reader reader=null;
		BufferedReader bReader=null;
		try {
			reader = new FileReader(file);
			bReader = new BufferedReader(reader);
			while ((str = bReader.readLine()) != null) {
				strs += str + "\n";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				bReader.close();
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return strs;
	}

	public static List<OrderingSet> readOrderList() {
		String str = readOrderData();
		String[] strs = str.split("\\n");
		for (int i = 0; i < strs.length; i++) {
			String[] string = new String[10];
			string = strs[i].split("\\s+");
			OrderingSet oSet = new OrderingSet();
			oSet.setId(Integer.parseInt(string[0].trim()));
			oSet.setName(string[1]);
			oSet.setDate(string[2]);
			oSet.setAddress(string[3]);
			oSet.setPrices(Double.parseDouble(string[4]));
			oSet.setStatus(string[5]);
			list.add(oSet);
		}
		return list;
	}

	public static String readOrderData() {
		count = 1;
		File file = new File("src/com/wlq/utils/order.txt");
		String str = null;
		String strs = "";
		Reader reader=null;
		BufferedReader bReader=null;
		try {
			reader = new FileReader(file);
			bReader = new BufferedReader(reader);
			while ((str = bReader.readLine()) != null) {
				strs += str + "\n";
				count++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				bReader.close();
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return strs;
	}
	
	@SuppressWarnings("unused")
	public static List<Dish> readDishLish() {
		File file = new File("src/com/wlq/utils/dish.txt");
		String string = readDishData();
		String[] strs = string.split("\\n");
		for (int i = 0; i < strs.length; i++) {
			String[] str1 = new String[10];
			str1 = strs[i].split("\\s+");
			Dish dish = new Dish();
			dish.setId(Integer.parseInt(str1[0].trim()));
			dish.setName(str1[1].toString());
			dish.setPrices(str1[2].toString());
			dish.setLikeNumber(Integer.parseInt(str1[3].trim()));
			dishList.add(dish);
		}
		return dishList;
	}
	
	public static void writeOrderData(OrderingSet oSet) {
		File file = new File("src/com/wlq/utils/order.txt");
		OutputStream out = null;
		OutputStreamWriter writer = null;
		BufferedWriter bwriter = null;
		try {
			out = new FileOutputStream(file);
			writer = new OutputStreamWriter(out);
			bwriter= new BufferedWriter(writer);
			
			for (OrderingSet oSet2 : list) {
				bwriter.write(oSet2.toString());
				bwriter.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				out.close();
				writer.close();
				bwriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
	public static void writeDishData(Dish dish) {
		File file = new File("src/com/wlq/utils/dish.txt");
		OutputStream out = null;
		OutputStreamWriter writer = null;
		BufferedWriter bwriter = null;
		try {
			out = new FileOutputStream(file);
			writer = new OutputStreamWriter(out);
			bwriter= new BufferedWriter(writer);	
			for (Dish dish2 : dishList) {
				bwriter.write(dish2.toString());
				bwriter.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				out.close();
				writer.close();
				bwriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
