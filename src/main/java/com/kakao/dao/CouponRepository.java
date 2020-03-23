package com.kakao.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kakao.vo.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long>{
	Coupon findByEmail(String email);
}
