package com.infotop.account.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.validation.Valid;

import net.infotop.util.OperationNoUtil;
import net.infotop.web.easyui.Message;
import net.infotop.web.easyui.Tree;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ch.qos.logback.classic.Logger;

import com.infotop.account.model.Parameter;
import com.infotop.account.model.User;
import com.infotop.account.service.AccountService;
import com.infotop.account.service.ParameterService;
import com.infotop.account.service.AuthenticationRealm.ShiroUser;
import com.infotop.common.BasicController;


@Controller
@RequestMapping(value = "/parameter")
public class ParameterController extends BasicController{
	@Autowired
	private ParameterService parameterService;
	@Resource
	private AccountService accountService;
	
	@RequestMapping(value = "",method = RequestMethod.GET)
	public String list(Model model){
		ShiroUser su = super.getLoginUser();
		User user = accountService.findUserByName(su.getLoginName());
		if (user != null) {
			
		}else {
			logger.log(this.getClass(),Logger.ERROR_INT,"登陆帐号无效!","",null);
			return "redirect:/login";
		}
		return "parameter/parameterList";
	}
	
	@RequestMapping(value = "treeList", method = RequestMethod.POST)
	@ResponseBody
	public List<Tree> treeList(@RequestParam(value = "id", defaultValue = "0") String parentId,
			ServletRequest request) {
		return parameterService.getParameterTreeList(parentId);
	}
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String createForm(Model model,
			@RequestParam(value = "pid", defaultValue = "0") Long pid) {
		Parameter parameter = new Parameter();
		if (0 != pid) {
			Parameter parent = parameterService.getParameterById(pid);
			parameter.setCategory(parent.getCategory());
			parameter.setParentId(pid);
		}
		model.addAttribute("parameter", parameter);
		model.addAttribute("action", "add");
		return "parameter/parameterForm";
	}
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public Message create(@Valid Parameter parameter,
			RedirectAttributes redirectAttributes) {
		parameter.setUuid(OperationNoUtil.getUUID());
		parameterService.save(parameter);
		msg.setSuccess(true);
		msg.setMessage("参数添加成功");
		msg.setData(parameter);
		return msg;
	}
	
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("parameter", parameterService.getParameterById(id));
		model.addAttribute("action", "update");
		return "parameter/parameterForm";
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(
			@Valid Parameter parameter) {
		parameterService.update(parameter);
		msg.setSuccess(true);
		msg.setMessage("参数更新成功");
		msg.setData(parameter);
		return msg;
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public Message delete(@RequestParam(value = "id") Long id,
			ServletRequest request) {
		try {
			parameterService.deleteParameter(id);
		}catch (Exception ex) {
			ex.printStackTrace();
			msg.setSuccess(false);
			msg.setMessage(ex.getMessage());
			msg.setData("");
		}
		return msg;
	}

}
