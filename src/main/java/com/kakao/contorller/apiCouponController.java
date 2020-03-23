package com.kakao.contorller;


import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kakao.dao.CouponRepository;
import com.kakao.service.CouponService;
import com.kakao.vo.Coupon;

@RestController
@RequestMapping("/api/coupons")
public class apiCouponController {
	@Autowired
	private CouponRepository couponRepository;
	
	@Autowired
	private CouponService couponService;
	
	@PostMapping("")
	private Coupon create(String email) {
		
		if(couponService.checkEmail(email) == null) {
			String code = couponService.coupnum();
			Coupon coupon = new Coupon(email, code);
			
			return couponRepository.save(coupon); 	
		} else {
			return null;
		}


	}
	
}
