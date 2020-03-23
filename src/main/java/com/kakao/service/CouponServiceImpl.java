package com.kakao.service;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kakao.dao.CouponRepository;
import com.kakao.vo.Coupon;

@Service
public class CouponServiceImpl implements CouponService{
	@Autowired
	private CouponRepository couponRepository;

	@Override
	public String generate(String data, int length) {
		SecureRandom random = new SecureRandom();
		
        if (length < 1) throw new IllegalArgumentException("길이는 1이상이어야 합니다.");
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append( data.charAt(random.nextInt(data.length())
            		));
        }
        return sb.toString();
	}

	@Override
	public String coupnum() {
    	String English_Lower = "abcdefghijklmnopqrstuvwxyz";
        String English_Upper = English_Lower.toUpperCase();
        String Number = "0123456789";
        
        String Random_String = English_Lower + English_Upper + Number;
        
        String CouponNumber = generate(Random_String, 4)+"-"+generate(Random_String, 4)
        +"-"+generate(Random_String, 4)+"-"+generate(Random_String, 4);
        
        return CouponNumber;
	}
	
	@Override
	public Coupon checkEmail(String email) {
		
		return couponRepository.findByEmail(email);
	}
	

	
}
