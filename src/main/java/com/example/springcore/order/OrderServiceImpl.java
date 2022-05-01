package com.example.springcore.order;

import com.example.springcore.discount.DiscountPolicy;
import com.example.springcore.discount.FixDiscountPolicy;
import com.example.springcore.discount.RateDiscountPolicy;
import com.example.springcore.member.Member;
import com.example.springcore.member.MemberRepository;
import com.example.springcore.member.MemoryMemeberRepository;

public class OrderServiceImpl implements OrderService {
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
