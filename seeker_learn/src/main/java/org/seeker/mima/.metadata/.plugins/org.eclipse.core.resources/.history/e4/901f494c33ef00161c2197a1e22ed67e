package org.vegetto.common.util.json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

public class JsonUtil {

	public static String toJSONStringByFastjson(List list) {
		// TODO 未完成
		return JSONArray.toJSONString(list);
	}
	/**
	 * 获取对象json串
	 * @param param
	 * @return
	 */
	public static String toJSONStringByFastjson(Object param) {
		// TODO 未完成
		return JSONArray.toJSONString(param);
	}

	/**
	 * 获取分页json串
	 * @param total
	 * @param rows
	 * @return
	 */
	public static String toJSONStringByFastjson(int total, List rows) {
		Map jsonMap = new HashMap();
		jsonMap.put("total", Integer.valueOf(total));
		jsonMap.put("rows", rows);
		// TODO 未完成
		return JSON.toJSONString(jsonMap);
	}


}
