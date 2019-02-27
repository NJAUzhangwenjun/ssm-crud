package cn.wen.crud.test;

import cn.wen.crud.bean.Department;
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

import java.util.List;

/**
 * @author 张文军
 * @Description:测试springmvc请求的单元测试
 * @Company:南京农业大学工学院
 * @version:1.0
 * @date 2019/2/2511:13
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:applicationContext.xml","file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml"})
public class MVCTest {

	/**
	 * 通过测试
	 * @throws Exception
	 */
		// 传入Springmvc的ioc
		@Autowired
		WebApplicationContext context;
		// 虚拟mvc请求，获取到处理结果。
		MockMvc mockMvc;

		@Before
		public void initMokcMvc() {
				mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		}

	/**
	 * 通过测试
	 * @throws Exception
     */
		@Test
		public void testPage() throws Exception {
				//模拟请求拿到返回值
				MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/depts")).andReturn();

				//请求成功以后，请求域中会有pageInfo；我们可以取出pageInfo进行验证
				MockHttpServletRequest request = result.getRequest();
				List<Department> depts = (List<Department>) request.getAttribute("depts");
				for (Department dept : depts) {
						System.out.println(dept.getDeptName());
				}
				/*MockHttpServletRequest request = result.getRequest();
				PageInfo pi = (PageInfo) request.getAttribute("pageInfo");
				*/

				/*
				System.out.println("当前页码："+pi.getPageNum());
				System.out.println("总页码："+pi.getPages());
				System.out.println("总记录数："+pi.getTotal());
				System.out.println("在页面需要连续显示的页码");
				int[] nums = pi.getNavigatepageNums();
				for (int i : nums) {
						System.out.print(" "+i);
				}




				//获取员工数据
				List<Employee> list = pi.getList();
				for (Employee employee : list) {
						System.out.println("ID："+employee.getEmpId()+"==>Name:"+employee.getEmpName());
				}
*/
		}

}
