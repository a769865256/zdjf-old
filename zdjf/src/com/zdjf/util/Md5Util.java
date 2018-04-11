package com.zdjf.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {
	/**
	 * 签名加密
	 * 
	 * @param sourceStr
	 * @return
	 */
	public static String md5to32(String sourceStr) {
		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(sourceStr.getBytes("utf-8"));
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0) {
					i += 256;
				}
				if (i < 16) {
					buf.append("0");
				}
				buf.append(Integer.toHexString(i));
			}
			result = buf.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
     * 先MD5加密，再Base64编码
     * 
     * @param str
     * @return
     * @throws NoSuchAlgorithmException
     */
    /*public static String getMd5BASE64(String plainText)
            throws NoSuchAlgorithmException {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(plainText.getBytes("utf-8"));
            return new sun.misc.BASE64Encoder().encode(bytes);
        } catch (UnsupportedEncodingException uee) {
            uee.printStackTrace();
        }
        return null;
    }*/


}
