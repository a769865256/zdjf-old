package com.zdjf.frame.chain.valuestack;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.zdjf.frame.chain.exceptions.ValueExistsException;
import com.zdjf.frame.chain.exceptions.ValueNotExistsException;

public class ValueStack implements Serializable {

	private static final long serialVersionUID = 6050299171843288821L;
	
	private Map<String, Object> context;

	public ValueStack() {
		this.context = new HashMap<String, Object>();
	}

	public void setValue(String expr, Object value) {
		context.put(expr, value);
	}

	public void setValueIfNotExists(String expr, Object value) throws ValueExistsException {
		if (existsValue(expr)) {
			throw new ValueExistsException(expr + "值已经存在!");
		}
		setValue(expr, value);
	}

	public boolean existsValue(String expr) {
		return !(context.get(expr) == null);
	}

	public String getString(String expr) {
		return this.getValue(expr, String.class);
	}

	public String getStringIfExists(String expr) throws ValueNotExistsException {
		return this.getValueIfExists(expr, String.class);
	}

	public Object getValue(String expr) {
		return context.get(expr);
	}

	public Object getValueIfExists(String expr) throws ValueNotExistsException {
		if (!existsValue(expr)) {
			throw new ValueNotExistsException(expr + "值不存在!");
		}
		return getValue(expr);
	}

	@SuppressWarnings("unchecked")
	public <T> T getValue(String expr, Class<T> asType) {
		Object v = this.getValue(expr);
		if (v == null || (v.getClass() != asType && v.getClass().isAssignableFrom(asType))) {
			return null;
		}
		return (T) v;
	}

	@SuppressWarnings("unchecked")
	public <T> T getValueIfExists(String expr, Class<T> asType) throws ValueNotExistsException {
		Object v = this.getValueIfExists(expr);
		if (v == null || (v.getClass() != asType && v.getClass().isAssignableFrom(asType))) {
			throw new ValueNotExistsException("类型为 " + asType.getName() + " 的 " + expr + " 值不存在!");
		}
		return (T) v;
	}

	public void setAll(Map<String, ?> map) {
		if (map != null) {
			context.putAll(map);
		}
	}

	public void clear() {
		context.clear();
	}

	public Map<String, Object> getContext() {
		return context;
	}

}
