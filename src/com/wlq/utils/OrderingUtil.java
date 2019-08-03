package com.wlq.utils;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.wlq.pojo.Dish;
import com.wlq.pojo.OrderingSet;

public class OrderingUtil {
	
	@SuppressWarnings("resource")
	public static void startMenu() {
		System.out.println("欢迎使用吃货联盟订餐系统");
		System.out.println("**************************");
		System.out.println("1、我要订餐");
		System.out.println("2、查看餐袋");
		System.out.println("3、签收订单");
		System.out.println("4、删除订单");
		System.out.println("5、我要点赞");
		System.out.println("6、退出系统");
		System.out.println("**************************");
		System.out.println("请选择：");
		Scanner scanner = new Scanner(System.in);
		String key = scanner.next();
		switch (key) {
		case "1":
			add();
			break;
		case "2":
			display();
			break;
		case "3":
			sign();
			break;
		case "4":
			delete();
			break;
		case "5":
			praise();
			break;
		case "6":
			break;
		default:
			System.out.println("输入错误，请重新输入！");
			startMenu();
			break;
		}

	}

	public static void initial() {
		System.out.println("序号" + "\t\t菜名" + "\t\t单价" + "\t\t点赞数");
		String data = IOUtils.readDishData();
		IOUtils.readOrderData();
		System.out.println(data);
	}

	@SuppressWarnings("resource")
	public static void add() {
		System.out.println("****我要订餐****");
		System.out.println("请输入订餐人姓名：");
		Scanner scanner = new Scanner(System.in);
		String name = scanner.next();
		initial();
		try {
			System.out.println("请选择您要点的菜品编号：");
			int chooseDish = scanner.nextInt();
			System.out.println("请选择您要点的菜品份数：");
			int number = scanner.nextInt();
			System.out.println("请输入送餐时间(送餐时间是10点至20点之间整点送餐)：");
			String time = scanner.next();
			System.out.println("请输入送餐地址：");
			String address = scanner.next();
			String[] dishNames = { "红烧带鱼", "鱼香肉丝", "时令鲜疏" };
			double[] prices = { 38.0, 20.0, 10.0 };
			String dishMeg = dishNames[chooseDish - 1] + " " + number + "份";
			double sumPrice = prices[chooseDish - 1] * number;
			double deliCharge = (sumPrice >= 50) ? 0 : 5;
			System.out.println("您订的是：" + dishMeg);
			System.out.println("送餐时间：" + time + "点");
			System.out.println("餐费：" + sumPrice + "元，送餐费" + deliCharge + "元，总计：" + (sumPrice + deliCharge) + "元。");
			System.out.println("订餐成功！");
			OrderingSet oSet = new OrderingSet();
			oSet.setName(name);
			oSet.setId(IOUtils.count);
			oSet.setAddress(address);
			oSet.setDate(time);
			oSet.setPrices(sumPrice + deliCharge);
			oSet.setStatus("已预订");
			File file = new File("src/com/wlq/utils/order.txt");
			FileWriter fWriter = new FileWriter(file, true);
			fWriter.write(oSet.toString());
			fWriter.close();
			System.out.println("输入0返回：");
			int input = scanner.nextInt();
			if (input == 0)
				startMenu();
		} catch (Exception e) {
			System.out.println("输入错误，请重新输入！");
			startMenu();
		}
	}

	@SuppressWarnings("resource")
	public static void display() {
		System.out.println("****查看餐袋*****");
		System.out.println("序号" + "\t\t订餐人" + "\t送餐日期" + "\t送餐地址" + "\t总金额" + "\t订单状态");
		try {
			String data =IOUtils.readOrderData();
			System.out.println(data);
			System.out.println("输入0返回：");
			Scanner scanner = new Scanner(System.in);
			int input = scanner.nextInt();
			if (input == 0)
				startMenu();
		} catch (Exception e) {
			System.out.println("输入错误，请重新输入！");
			display();
		}
	}

	// 点赞
	@SuppressWarnings({ "resource" })
	public static void praise() {
		System.out.println("****我要点赞****");
		System.out.println("序号" + "\t\t菜名" + "\t\t单价" + "\t\t点赞");
		String string=IOUtils.readDishData();
		System.out.println(string);	
		List<Dish>dishs=new ArrayList<Dish>();
		dishs=IOUtils.readDishLish();
		System.out.println("请您选择要点赞的菜品序号：");
		try {
			Scanner scanner=new Scanner(System.in);
			int num=scanner.nextInt();	
			for (int i = 0; i < dishs.size(); i++) {
				Dish dish=new Dish();
				if (dishs.get(i).getId()==num) {
					dish.setId(dishs.get(i).getId());
					dish.setName(dishs.get(i).getName());
					dish.setPrices(dishs.get(i).getPrices());
					dish.setLikeNumber(dishs.get(i).getLikeNumber()+1);
					dishs.remove(dishs.get(i));
					dishs.add(dish);
					System.out.println("点赞成功！");
					IOUtils.writeDishData(dish);
					break;
				}
			}
				System.out.println("输入0返回：");
				String input = scanner.next();
				if (input.equals("0"))
					startMenu();
		} catch (Exception e) {
			System.out.println("输入错误，请重新输入！");
			praise();
		}
	}

	// 接收要签收的订单号
	@SuppressWarnings({ "resource" })
	public static void sign() {
		System.out.println("****签收订单****");
		String str = IOUtils.readOrderData();
		System.out.println(str);
		System.out.println("****请选择要签收的订单序号：");
		List<OrderingSet> list = new ArrayList<OrderingSet>();
		try {
			Scanner scanner = new Scanner(System.in);
			int num = scanner.nextInt();
			list = IOUtils.readOrderList();
			for (int i = 0; i <list.size(); i++) {
				OrderingSet oSet=new OrderingSet();
				if(list.get(i).getId()==num) {
					oSet.setId(list.get(i).getId());
					oSet.setAddress(list.get(i).getAddress());
					oSet.setDate(list.get(i).getDate());
					oSet.setName(list.get(i).getName());
					oSet.setPrices(list.get(i).getPrices());
					oSet.setStatus("已完成");
					list.remove(list.get(i));
					list.add(oSet);
					System.out.println("签收成功");
				}
				IOUtils.writeOrderData(oSet);
			}
			System.out.println("输入0返回：");
			int input = scanner.nextInt();
			if (input == 0)
				startMenu();
		} catch (Exception e) {
			System.out.println("输入错误，请重新输入！");
			sign();
		}
	}


	@SuppressWarnings("resource")
	private static void delete() {
		List<OrderingSet> list = new ArrayList<OrderingSet>();
		System.out.println("****删除订单****");
		String string = IOUtils.readOrderData();
		System.out.println(string);
		System.out.println("****请输入要删除的订单序号：");
		try {
			Scanner scanner = new Scanner(System.in);
			int input = scanner.nextInt();
			list = IOUtils.readOrderList();
			for (int i = 0; i < list.size(); i++) {
				OrderingSet oSet=new OrderingSet();
				if(list.get(i).getId()==input) {
					if ("已完成".equals(list.get(i).getStatus())) {
						list.remove(list.get(i));
						System.out.println("删除成功！");
					}else {
						System.out.println("您选择的订单未签收，不能删除");
					}
					IOUtils.writeOrderData(oSet);
				}
			}
			System.out.println("输入0返回：");
			String  num = scanner.next();
			if (num.equals("0"))
				startMenu();
		} catch (Exception e) {
			System.out.println("输入错误，请重新输入！");
			delete();
		}
	}

	
}
