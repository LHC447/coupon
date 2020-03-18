package com.kakao.web;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kakao.domain.Coupon;
import com.kakao.domain.CouponRepository;

@RestController
@RequestMapping("/api/coupons")
public class apiCouponController {
	@Autowired
	private CouponRepository couponRepository;
	
	@PostMapping("")
	private Coupon create(String email) {
		Coupon checkEmail = couponRepository.findByEmail(email);
		
		if(checkEmail == null) {
			String code = coupnum();
			Coupon coupon = new Coupon(email, code);
			return couponRepository.save(coupon); 
		} else {
			return null;
		}
	}
	
	public static String generate(String data, int length) {
		SecureRandom random = new SecureRandom();
		
        if (length < 1) throw new IllegalArgumentException("길이는 1이상이어야 합니다.");
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append( data.charAt(random.nextInt(data.length())
            		));
        }
        return sb.toString();
    }
	
	public String coupnum() {
    	String English_Lower = "abcdefghijklmnopqrstuvwxyz";
        String English_Upper = English_Lower.toUpperCase();
        String Number = "0123456789";
        
        /** 랜덤을 생성할 대상 문자열 **/
        String Random_String = English_Lower + English_Upper + Number;
        
        String CouponNumber = generate(Random_String, 4)+"-"+generate(Random_String, 4)
        +"-"+generate(Random_String, 4)+"-"+generate(Random_String, 4);
        
        return CouponNumber;
	}
	
}