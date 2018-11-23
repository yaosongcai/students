package com.wq.utils.util;

import java.math.BigDecimal;

/**
 * 项目名称：
 * 类描述
 *  <pre>
 *      double精确运算utils
 *  </pre>
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/9/19
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/9/19
 * 修改备注：
 */
public class Arith {  
	private static final int DEF_DIV_SCALE = 10;  

	/** 
	 * * 两个Double数相加 * 
	 *  
	 * @param v1 * 
	 * @param v2 * 
	 * @return Double 
	 */  
	public static double add(Double v1, Double v2) {
		BigDecimal b1 = new BigDecimal(v1.toString());  
		BigDecimal b2 = new BigDecimal(v2.toString());
		return new Double(b1.add(b2).setScale(2,BigDecimal.ROUND_UP).doubleValue());
	}  

	/** 
	 * * 两个Double数相减 * 
	 *  
	 * @param v1 * 
	 * @param v2 * 
	 * @return Double 
	 */  
	public static double sub(Double v1, Double v2) {
		BigDecimal b1 = new BigDecimal(v1.toString());  
		BigDecimal b2 = new BigDecimal(v2.toString());  
		return new Double(b1.subtract(b2).setScale(2,BigDecimal.ROUND_UP).doubleValue());
	}  

	/** 
	 * * 两个Double数相乘 * 
	 *  
	 * @param v1 * 
	 * @param v2 * 
	 * @return Double 
	 */  
	public static double mul(Double v1, Double v2) {
		BigDecimal b1 = new BigDecimal(v1.toString());  
		BigDecimal b2 = new BigDecimal(v2.toString());  
		return new Double(b1.multiply(b2).setScale(2,BigDecimal.ROUND_UP).doubleValue());
	}  

	/** 
	 * * 两个Double数相除 * 
	 *  
	 * @param v1 * 
	 * @param v2 * 
	 * @return Double 
	 */  
	public static double div(Double v1, Double v2) {
		BigDecimal b1 = new BigDecimal(v1.toString());  
		BigDecimal b2 = new BigDecimal(v2.toString());  
		return new Double(b1.divide(b2, DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP)
				.doubleValue());  
	}  

	/** 
	 * * 两个Double数相除，并保留scale位小数 * 
	 *  
	 * @param v1 * 
	 * @param v2 * 
	 * @param scale * 
	 * @return Double 
	 */  
	public static Double div(Double v1, Double v2, int scale) {
		if (scale < 0) {  
			throw new IllegalArgumentException(  
					"The scale must be a positive integer or zero");  
		}  
		BigDecimal b1 = new BigDecimal(v1.toString());  
		BigDecimal b2 = new BigDecimal(v2.toString());  
		return new Double(b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue());  
	}  
}  
