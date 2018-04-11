package com.zdjf.frame.chain.valuestack;

/**
 * 值栈容器
 * 
 * @author chenrg
 *
 */
public class ValueStackContext {

	private static ThreadLocal<ValueStack> THREAD_LOCAL = new ThreadLocal<ValueStack>();

	public static ValueStack getCurrent() {
		ValueStack valueStack = THREAD_LOCAL.get();
		if (valueStack == null) {
			valueStack = new ValueStack();
			THREAD_LOCAL.set(valueStack);
		}
		return valueStack;
	}

	public static void setNull() {
		THREAD_LOCAL.remove();
	}

}
