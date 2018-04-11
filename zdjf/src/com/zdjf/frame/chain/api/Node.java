package com.zdjf.frame.chain.api;

import java.util.Map;

import com.zdjf.frame.chain.valuestack.ValueStack;

/**
 * 责任链节点
 * 
 * @author chenrg
 *
 */
public interface Node {
	public String getName(); // 名称

	public void setName(String name);

	public String doNode(ValueStack valueStack) throws Exception;

	public Map<String, String> getForwards(); // 流向

	public void setForwards(Map<String, String> forwards);
}
