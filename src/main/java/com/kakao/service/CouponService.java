package com.kakao.service;

import com.kakao.vo.Coupon;

public interface CouponService {
	public String generate(String data, int length);
	
	public String coupnum();
	
	public Coupon checkEmail(String email);
}
