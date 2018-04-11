package com.zdjf.frame.chain.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.zdjf.frame.chain.api.Chain;
import com.zdjf.frame.chain.api.Node;
import com.zdjf.frame.chain.valuestack.ValueStack;

/**
 *
 * @author chenrg
 *
 */
public class DefaultChain implements Chain {
	private Collection<Node> nodes;
	private String first;
	private Map<String, Node> nodesMap;

	@Override
	public void doChain(ValueStack valueStack) throws Exception {
		if (nodesMap == null) {
			initNodesMap();
		}
		Node node = nodesMap.get(getFirst());
		if (node == null) {
			throw new IllegalArgumentException("首节点(first)未找到");
		}
		while (node != null) {
			String r = node.doNode(valueStack);
			if (r == null) {
				return;
			}
			Map<String, String> fwds = node.getForwards();
			if (fwds == null) {
				break;
			}
			String fwd = fwds.get(r);
			if (fwd == null) {
				throw new IllegalArgumentException("流向(" + r + ")未找到");
			}
			node = nodesMap.get(fwd);
		}
	}

	private void initNodesMap() {
		nodesMap = new HashMap<String, Node>();
		for (Node node : nodes) {
			if (nodesMap.containsKey(node.getName())) {
				throw new IllegalArgumentException("节点(" + node.getName() + ")重复");
			}
			nodesMap.put(node.getName(), node);
		}
	}

	@Override
	public Collection<Node> getNodes() {
		return nodes;
	}

	@Override
	public void setNodes(Collection<Node> nodes) {
		this.nodes = nodes;
	}

	@Override
	public String getFirst() {
		return first;
	}

	@Override
	public void setFirst(String first) {
		this.first = first;
	}

}
