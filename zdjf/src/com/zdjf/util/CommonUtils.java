package com.zdjf.util;

import java.util.HashMap;
import java.util.Map;

public class CommonUtils {
	public static Map<String, Object> packageResult(Boolean flag,Object object){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("status", flag?"100":"101");
		map.put("returnCase", flag);
		map.put("content", object);
		return map;
	}
}
