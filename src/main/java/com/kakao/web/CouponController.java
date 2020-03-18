package com.kakao.web;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kakao.domain.Coupon;
import com.kakao.domain.CouponRepository;

@Controller
public class CouponController {
	@Autowired
	private CouponRepository couponRepository;
	
	@GetMapping("/coupons/list")
	public String coupon(Model model, @PageableDefault(sort = { "id" }, direction = Sort.Direction.ASC, size = 5) Pageable pageable) {
		
		Page<Coupon> couponPage = couponRepository.findAll(pageable);
		
		model.addAttribute("couponPage", couponPage);
		
		System.out.println("총 element 수 :"+ couponPage.getTotalElements()+", 전체 page 수 :"+ couponPage.getTotalPages()
		+", 페이지에 표시할 element 수 :" +couponPage.getSize() +", 현재 페이지 index :" +couponPage.getNumber()
		+", 현재 페이지의 element 수 :" +couponPage.getNumberOfElements());
		
		return "coupon/list";
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
        
        String Random_String = English_Lower + English_Upper + Number;
        
        String CouponNumber = generate(Random_String, 4)+"-"+generate(Random_String, 4)
        +"-"+generate(Random_String, 4)+"-"+generate(Random_String, 4);
        
        return CouponNumber;
	}
	
}
