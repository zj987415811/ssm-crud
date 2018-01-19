package com.crud.test;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.crud.bean.Department;
import com.crud.bean.Employee;
/**
 * Spring单元测试
 * 1.导入spring测试单元；
 * 2.@ContextConfiguration指定Spring配置文件的位置
 * 3.直接autowired要使用的组件
 */
import com.crud.dao.DepartmentMapper;
import com.crud.dao.EmployeeMapper;
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:applicationContext.xml"})

public class MapperTest {
	/**
	 * 测试DepartmentMapper
	 */
	@Autowired
	DepartmentMapper departmentMapper;
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	SqlSession sqlssion;
	@Test
	public void testCrud() {
		//1.创建SpringIOC容器
//		ApplicationContext applicationContext=new ClassPathXmlApplicationContext();
//		//2.从容器中获取mapper
//		DepartmentMapper bean=applicationContext.getBean(DepartmentMapper.class);
		System.out.println(departmentMapper);
		
		//1.插入几个部门
		//departmentMapper.insertSelective(new Department(3,"开发部"));
		//departmentMapper.insertSelective(new Department(4,"测试部"));
		//2.员工插入
		//employeeMapper.insertSelective(new Employee(3, "zhoujin", "M", "987415811@qq.com", 1));
		//3.批量插入:使用批量操作的sqlSession
//		for() {
//			employeeMapper.insertSelective(new Employee(3, "zhoujin", "M", "987415811@qq.com", 1));
//		}
		EmployeeMapper mapper=sqlssion.getMapper(EmployeeMapper.class);
		for (int i = 0; i < 1000; i++) {
			String uId=UUID.randomUUID().toString().substring(0,5)+i;
			mapper.insertSelective(new Employee(null, uId, "M", uId+"@qq", 1));
		}
		
	}
}
