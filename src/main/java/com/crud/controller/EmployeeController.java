package com.crud.controller;
/**
 * 处理员工请求
 * @author Administrator
 *
 */

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crud.bean.Employee;
import com.crud.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Controller
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	
	/**
	 * 查询员工数据
	 * @return
	 */
	@RequestMapping(value="/emps")
	public String getEmps(@RequestParam(value="pn",defaultValue="1")Integer pn,Model model){
		//分页
		//引入PageHelper插件
		//查询之前调用,传入页码
		PageHelper.startPage(pn,5);
		//startPage后面紧跟着分页查询
		List<Employee> emps=employeeService.getAll();
		for (Iterator iterator = emps.iterator(); iterator.hasNext();) {
			Employee employee = (Employee) iterator.next();
			System.out.println(employee.getdId());
		}
		//使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了
		//封装详细的分页信息，包括我们查询数据,连续显示5页。
		PageInfo pageInfo=new PageInfo(emps,5);
		model.addAttribute("pn",pageInfo);
		return "list";
	}
}	
