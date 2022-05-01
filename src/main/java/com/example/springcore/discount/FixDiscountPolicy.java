package com.example.springcore.discount;

import com.example.springcore.member.Grade;
import com.example.springcore.member.Member;

public class FixDiscountPolicy implements DiscountPolicy {
    private int discountdFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return discountdFixAmount;
        }else{
            return 0;
        }
    }
}
