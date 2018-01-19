package com.crud.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.crud.bean.Employee;
import com.github.pagehelper.PageInfo;

/**
 *使用Spring测试模块
 * @author Administrator
 *
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations= {"classpath:applicationContext.xml",
		"file:src/main/webapp/WEB-INF/dispatcher-servlet.xml"})

public class MvcTest {
	//虚拟的MVC请求，获取结果
	@Autowired
	WebApplicationContext context;
	MockMvc mockMvc;
	@Before
	public void initMokcMvc() {
		// TODO Auto-generated method stub
		mockMvc=MockMvcBuilders.webAppContextSetup(context).build();
	}
	@Test
	public void testPage() {
		// TODO Auto-generated method stub
		//模拟请求拿到返回值
		System.out.println("开始测试。。。1");
		try {
			MvcResult result = mockMvc.perform(MockMvcRequestBuilders
					.get("/emps")
					.param("pn", "1")).andReturn();//请求成功以后，请求域中会有Pageinfo，可以取出pageinfo进行验证
			
			MockHttpServletRequest request = result.getRequest();
			System.out.println(result.toString().length());
			System.out.println("============================================");
			System.out.println(request.getAttribute("pn"));
			System.out.println("============================================");
			PageInfo pi = (PageInfo)request.getAttribute("pn");
			System.out.println("当前页码:"+pi.getPageNum());
			System.out.println("总页码:"+pi.getPages());
			System.out.println("总记录数:"+pi.getTotal());
			System.out.println("在页面需要连续显示的页码");
			int[]  nums= pi.getNavigatepageNums();
			for(int i:nums){
				System.out.println(" "+i);
			}
			List<Employee> list= pi.getList();
			for(Employee employee : list ){
				System.out.println("ID:" +employee.getEmpId()+"==>Name:"+employee.getEmpName());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("出现异常");
			e.printStackTrace();
		}
		
		
	} 
}
