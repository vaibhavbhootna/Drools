package com.drools.poc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DroolsController {

	@RequestMapping(method = RequestMethod.GET, value = { "/" })
	public String home()
	{
		return "index";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = { "/drools" })
	public ModelAndView getTestData(HttpServletRequest request) {
		String integerValue= request.getParameter("integerValue");
		String testRecordId= request.getParameter("testRecordId");
		TestRecord testRecord = new TestRecord();
		testRecord.setIntegerValue(Integer.valueOf(integerValue));
		testRecord.setTestRecordId(Integer.valueOf(testRecordId));
	
		RuleExecutor ruleExecutor = new RuleExecutor();
		TestResult testResult= ruleExecutor.processRules(testRecord);
		System.out.println(testResult);
		return new ModelAndView("result","testResult",testResult);
	}
	
}
