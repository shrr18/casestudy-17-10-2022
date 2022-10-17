package com.gl.caseStudy4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.gl.caseStudy4.bean.Bill;
import com.gl.caseStudy4.bean.Offer;
import com.gl.caseStudy4.service.RechargeService;

@RestController
public class RechargeController {
	
	@Autowired
	private RechargeService service;
	
	@GetMapping(value="/index")
	 public ModelAndView showWelcomePage() {
		ModelAndView mv = new ModelAndView("index");
		List<Offer> offerList = service.findAllOffers();
		mv.addObject("offerList",offerList);
		return mv;
	}
	
	@GetMapping("/performRecharge")
	public ModelAndView saveNewBill(@RequestParam("mobileNumber") String num,
									  @RequestParam("offerId") long offerId) {
		long id = service.generateId();
		Bill bill = new Bill();
		bill.setMobileNumber(num);
		bill.setOfferId(offerId);
		bill.setTransactionId(offerId);
		service.billSave(bill);
		Offer offer = service.findAOffer(offerId);
		ModelAndView mv = new ModelAndView("transactionReport");
		mv.addObject("offer",offer);
		mv.addObject("bill",bill);
		return mv;
	}
}
