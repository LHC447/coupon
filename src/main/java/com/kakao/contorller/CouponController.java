package com.kakao.contorller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kakao.dao.CouponRepository;
import com.kakao.service.CouponService;
import com.kakao.vo.Coupon;

@Controller
public class CouponController {
	@Autowired
	private CouponRepository couponRepository;
	
	@Autowired
	private CouponService couponService;
	
	
	@GetMapping("/coupon/list")
	public String coupon(Model model, @PageableDefault(sort = { "id" }, direction = Sort.Direction.ASC, size = 5) Pageable pageable) {
		
		Page<Coupon> couponPage = couponRepository.findAll(pageable);
		
		model.addAttribute("couponPage", couponPage);
		
		System.out.println("총 element 수 :"+ couponPage.getTotalElements()+", 전체 page 수 :"+ couponPage.getTotalPages()
		+", 페이지에 표시할 element 수 :" +couponPage.getSize() +", 현재 페이지 index :" +couponPage.getNumber()
		+", 현재 페이지의 element 수 :" +couponPage.getNumberOfElements());
		
		return "coupon/list";
	}
	
	
	@ResponseBody
	@PostMapping(value = "/coupon/checkEmail")
	public int postEmailCheck(HttpServletRequest req) throws Exception {
		
		String email = req.getParameter("email");
		Coupon checkEmail =  couponService.checkEmail(email);
	 
		int result = 0;
	 
		if(checkEmail != null) {
			result = 1;
		} 
	 
	 return result;
	}
	
	
	@ResponseBody
	@PostMapping(value= "/coupon/pageble")
	public Page<Coupon> listAll(@PageableDefault(sort = { "id" }, direction = Sort.Direction.ASC, size = 5) Pageable pageable) {
	
		return couponRepository.findAll(pageable);
	
	}
	
}
