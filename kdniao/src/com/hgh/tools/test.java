package com.hgh.tools;

import java.util.ArrayList;
import java.util.List;

public class test {

	public static void main(String[] args) {
		 List<String> lists = new ArrayList<String>();
		 for (int i = 0; i < 5; i++) {
			lists.add(""+i);
		}
		 List<String> lists1=null;
		 List<String> lists2=null;
		 List<String> lists3=null;
		 List<String> lists4=null;
		 List<String> lists5=null;
		 List<String> lists6=null;
		int index = lists.size()/5;
		if(index>=1){
			lists1=lists.subList(0, index);
			lists2=lists.subList(index, index*2);
			lists3=lists.subList(index*2, index*3);
			lists4=lists.subList(index*3, index*4);
			lists5=lists.subList(index*4, index*5);
		}
		lists6=lists.subList(index*5, lists.size());
		System.out.println(lists);
		System.out.println(lists1);
		System.out.println(lists2);
		System.out.println(lists3);
		System.out.println(lists4);
		System.out.println(lists5);
		System.out.println(lists6);
	}
}
