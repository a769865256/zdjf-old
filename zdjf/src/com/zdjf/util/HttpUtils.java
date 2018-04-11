package com.zdjf.util;

import org.apache.commons.lang3.StringUtils;

public class HttpUtils {
	/**
	 * 替换转义符
	 * 
	 * @param message
	 * @return
	 */
	public static String decodeMessage(String message) {
		String string = StringUtils.replace(message, "&#x", "\\u");
		string = decodeUnicode(string);
		string = StringUtils.replace(string, "&lt", "<");
		string = StringUtils.replace(string, "&gt", ">");
		string = StringUtils.replace(string, "&quot;", "\"");
		string = StringUtils.replace(string, ";", "");
		string = StringUtils.replace(string, "\t", "");
		return string;
	}
	
	/**
	 * 获取有效的xml字符串
	 * @param message -- 需要截取的字符串
	 * @param startFlag -- 开始字符
	 * @param endFlag -- 结束字符
	 * @return
	 */
	public static String getUsableMessage(String message,String startFlag,String endFlag) {
		return StringUtils.substringBetween(message, startFlag,endFlag);
	}

	/**
	 * decode 转义
	 * 
	 * @param theString
	 * @return
	 */
	public static String decodeUnicode(String theString) {

		char aChar;

		int len = theString.length();

		StringBuffer outBuffer = new StringBuffer(len);

		for (int x = 0; x < len;) {

			aChar = theString.charAt(x++);

			if (aChar == '\\') {

				aChar = theString.charAt(x++);

				if (aChar == 'u') {

					// Read the xxxx;

					int value = 0;

					for (int i = 0; i < 4; i++) {

						aChar = theString.charAt(x++);

						switch (aChar) {

						case '0':

						case '1':

						case '2':

						case '3':

						case '4':

						case '5':

						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException(
									"Malformed   \\uxxxx   encoding.");
						}

					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't')
						aChar = '\t';
					else if (aChar == 'r')
						aChar = '\r';

					else if (aChar == 'n')

						aChar = '\n';

					else if (aChar == 'f')

						aChar = '\f';

					outBuffer.append(aChar);

				}

			} else

				outBuffer.append(aChar);

		}

		return outBuffer.toString();

	}
}
