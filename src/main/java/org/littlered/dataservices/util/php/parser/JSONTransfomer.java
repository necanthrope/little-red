package org.littlered.dataservices.util.php.parser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Map;

public class JSONTransfomer {
	@SuppressWarnings("rawtypes")
	public static Object toJSON(Object o)
	{
		if (o instanceof Map)
		{
			return arrayToJSON((Map) o);
		}
		else if (o instanceof SerializedPhpParser.PhpObject)
		{
			return mapToJSON(((SerializedPhpParser.PhpObject) o).attributes);
		}
		else if (o == SerializedPhpParser.NULL)
		{
			return null;
		}
		return o;
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	private static JSONArray arrayToJSON(Map o)
	{
		JSONArray a = new JSONArray();
		for (Object obj : o.values())
		{
			a.put(toJSON(obj));
		}
		return a;
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	private static JSONObject mapToJSON(Map o)
	{
		JSONObject obj = new JSONObject();
		Map map = o;
		Iterator<Map.Entry> i = map.entrySet().iterator();
		while (i.hasNext())
		{
			Map.Entry next = i.next();
			obj.put((String)next.getKey(), toJSON(next.getValue()));
		}
		return obj;
	}
}
