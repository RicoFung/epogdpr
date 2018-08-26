package com.epo.admin.action;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.epo.admin.entity.VipMemInfo;
import com.epo.admin.service.VipMemInfoService;

import chok.devwork.BaseController;
import chok.util.CollectionUtil;

@Scope("prototype")
@Controller
@RequestMapping("/admin/vipmeminfo")
public class VipMemInfoAction extends BaseController<VipMemInfo>
{
	static Logger log = LoggerFactory.getLogger(VipMemInfoAction.class);
	
	@Autowired
	private VipMemInfoService service;

	@RequestMapping("/add")
	public String add()
	{
		put("queryParams", req.getParameterValueMap(false, true));
		return "/admin/vipmeminfo/add.jsp";
	}

	@RequestMapping("/add2")
	public void add2(VipMemInfo po)
	{
		try
		{
			service.add(po);
			print("1");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			print("0:" + e.getMessage());
		}
	}

	@RequestMapping("/imp")
	public String imp()
	{
		put("queryParams", req.getParameterValueMap(false, true));
		return "/admin/vipmeminfo/imp.jsp";
	}

	@RequestMapping("/imp2")
	public void imp2(@RequestParam("myFile") CommonsMultipartFile files[])
	{
		try
		{
			Map<String, List<VipMemInfo>> resultMap = service.imp2(files);
			if (resultMap.get("sucRows").size() > 0)
				result.setSuccess(true);
			else
				result.setSuccess(false);
				
			result.setMsg("(success:"+resultMap.get("sucRows").size()+")");
			result.setMsg(result.getMsg()+"(error:"+resultMap.get("errRows").size()+")");
			
			if (resultMap.get("errRows").size() > 0)
			{
				System.out.println("----------------------------");
				for(VipMemInfo po: resultMap.get("errRows"))
				{
					System.out.println(po.toString());
				}
				System.out.println("----------------------------");
			}
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg(e.getMessage());
		}
		printJson(result);
	}

	@RequestMapping("/del")
	public void del()
	{
		try
		{
			service.del(CollectionUtil.toLongArray(req.getLongArray("id[]", 0l)));
			result.setSuccess(true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg(e.getMessage());
		}
		printJson(result);
	}

	@RequestMapping("/upd")
	public String upd()
	{
		put("po", service.get(req.getLong("id")));
		put("queryParams", req.getParameterValueMap(false, true));
		return "/admin/vipmeminfo/upd.jsp";
	}

	@RequestMapping("/upd2")
	public void upd2(VipMemInfo po)
	{
		try
		{
			service.upd(po);
			print("1");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			print("0:" + e.getMessage());
		}
	}

	@RequestMapping("/get")
	public String get()
	{
		put("po", service.get(req.getLong("id")));
		put("queryParams", req.getParameterValueMap(false, true));
		return "/admin/vipmeminfo/get.jsp";
	}

	@RequestMapping("/query")
	public String query()
	{
		put("queryParams", req.getParameterValueMap(false, true));
		return "/admin/vipmeminfo/query.jsp";
	}

	@RequestMapping("/query2")
	public void query2()
	{
		Map<String, Object> m = req.getParameterValueMap(false, true);
		result.put("total", service.getCount(m));
		result.put("rows", service.query(req.getDynamicSortParameterValueMap(m)));
		printJson(result.getData());
	}

	@RequestMapping("/exp")
	public void exp()
	{
		Map<String, Object> m = req.getParameterValueMap(false, true);
		List<VipMemInfo> list = service.query(m);
		exp(list, "xlsx");
	}

	public static void main(String[] args)
	{
		// 设置默认语言环境
		// Locale.setDefault(Locale.FRANCE);
		// Locale l = Locale.getDefault();
		// System.out.println("默认语言地区代码: " + l.toString());

//		Locale chinaLocale;
//		Locale newLocale, testLocale;
//		Locale locales[] = Locale.getAvailableLocales();
//		for (Locale locale : locales)
//		{
//			// 获取系统支持的语言和国家
//			System.out.println("Language：" + locale.getLanguage() + "   Country:" + locale.getCountry());
//			// 预览该国家语言
//			newLocale = new Locale(locale.getLanguage(), locale.getCountry());
//			Locale.setDefault(newLocale);
//			testLocale = Locale.getDefault();
//			System.out.println(
//					"     Display:" + testLocale.getDisplayLanguage() + "   " + testLocale.getDisplayCountry());
//			// 用中文表示该国家语言
//			Locale.setDefault(new Locale("zh", "CN"));
//			chinaLocale = Locale.getDefault();
//			System.out.println(
//					"     Display:" + testLocale.getDisplayLanguage() + "   " + testLocale.getDisplayCountry());
//		}
	}
}
