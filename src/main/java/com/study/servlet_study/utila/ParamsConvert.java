package com.study.servlet_study.utila;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ParamsConvert {
	
	public static Map<String, String> convertParamsMapToMap(Map<String, String[]> paramsMap) {
		
		Map<String, String> map = new HashMap<>();
		
		paramsMap.forEach((k, v) -> {  // type = string 
			StringBuilder builder = new StringBuilder(); // 비어있는 문자열의 공간을 만들어줌
			
			Arrays.asList(v).forEach(value -> builder.append(value)); // 배열을 리스트 자료형으로 바꿔줌, 리스트에서 value를 하나씩 담아 합친다
			
//			System.out.println(k + ":" + builder.toString()); // 문자열로 바꿔줌 toString을 하지않으면 주소값이 나옴
			
			map.put(k, builder.toString());
		});
		
		
		return map;
		
	}
}
