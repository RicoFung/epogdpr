package com.common;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import chok.devwork.BaseController;

@Scope("prototype")
@Controller
@RequestMapping("/dict")
public class DictAction extends BaseController<Object>
{
	@Autowired
	Dict dict;
	
	@RequestMapping("/getCountrys")
	public void getCountrys()
	{
		List<Map<String, String>> list = dict.getCountrys();
		printJson(list);
	}
}
