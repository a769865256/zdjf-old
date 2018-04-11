package com.zdjf.util;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 验证码类
 * @author chenrg
 *
 */
public class VerifyCode {
	static Random r = new Random();

	static String ssource = "0123456789";

	static char[] src = ssource.toCharArray();

	private int codeLength = 4;

	private HttpServletRequest request = null;

	private HttpServletResponse response = null;

	public VerifyCode(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		String code = getCode(this.codeLength);
		this.createImage(code);
	}

	public VerifyCode(HttpServletRequest request, HttpServletResponse response,
			Integer codeLength) {
		this.codeLength = codeLength.intValue();
		this.request = request;
		this.response = response;
		String code = getCode(this.codeLength);
		this.createImage(code);
	}

	private String getCode(int length) {
		char[] buf = new char[length];
		int rnd;
		for (int i = 0; i < length; i++) {
			rnd = Math.abs(r.nextInt()) % src.length;

			buf[i] = src[rnd];
		}
		return new String(buf);
	}

	private Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	private void createImage(String sCode) {
		this.response.setContentType("image/jpeg");

		this.response.setHeader("Pragma", "No-cache");
		this.response.setHeader("Cache-Control", "no-cache");
		this.response.setDateHeader("Expires", 0);

		int width = 15 * this.codeLength;
		int height = 20;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		Graphics g = image.getGraphics();

		Random random = new Random();

		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);

		g.setFont(new Font("Times New Roman", Font.PLAIN, 18));

		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}

		for (int i = 0; i < this.codeLength; i++) {
			String rand = sCode.substring(i, i + 1);

			g.setColor(new Color(20 + random.nextInt(60), 20 + random
					.nextInt(120), 20 + random.nextInt(180)));
			g.drawString(rand, 13 * i + 6, 16);
		}

		g.dispose();

		this.request.getSession().setAttribute("randomCode", sCode);

		ServletOutputStream outStream;
		/*try {
			outStream = response.getOutputStream();
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(outStream);

			encoder.encode(image);

			outStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}
	
	public static boolean checkCode(String code,HttpServletRequest request) {
		String randomCode=request.getSession().getAttribute("randomCode").toString();		
		return randomCode.equalsIgnoreCase(code);
	}
}