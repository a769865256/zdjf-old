package com.zdjf.frame.idgenerator;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import org.springframework.data.redis.core.RedisTemplate;

import com.zdjf.frame.dataaccess_api.IdGenerator;

/**
 * <p>
 * redis 自增Id生成
 * </p>
 * <p>
 * 原子自增可用于分布式环境
 * </p>
 * 
 * @author liuxin
 *
 */
public class RedisIdGenerator implements IdGenerator {
	private RedisTemplate<String, Long> redisTemplate;

	public RedisIdGenerator(RedisTemplate<String, Long> redisTemplate) {
		super();
		this.redisTemplate = redisTemplate;
	}

	@Override
	public Serializable setId(Object entity, PropertyDescriptor idPropertyDescriptor) {
		String key = "sequence:" + getSeqName(entity.getClass());
		long val = redisTemplate.boundValueOps(key).increment(1);
		try {
			idPropertyDescriptor.getWriteMethod().invoke(entity, val);
		} catch (IllegalAccessException e) {
			throw new IllegalArgumentException(e);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e);
		} catch (InvocationTargetException e) {
			throw new IllegalArgumentException(e);
		}
		return val;
	}

	private String getSeqName(Class<?> cls) {
		while (cls.getSuperclass() != Object.class) {
			cls = cls.getSuperclass();
		}
		return cls.getName();
	}

}
