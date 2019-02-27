package cn.wen.crud.test;

/**
 * @author 张文军
 * @Description:测试dao层的工作
 * @Company:南京农业大学工学院
 * @version:1.0
 * @date 2019/2/252:03
 */

import cn.wen.crud.bean.Employee;
import cn.wen.crud.dao.DepartmentMapper;
import cn.wen.crud.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 *推荐Spring的项目就可以使用Spring的单元测试，可以自动注入我们需要的组件
 *1、导入SpringTest模块
 *2、@ContextConfiguration指定Spring配置文件的位置
 *3、直接autowired要使用的组件即可
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class CRUDTest {
		@Autowired
		DepartmentMapper departmentMapper;

		@Autowired
		EmployeeMapper employeeMapper;

		@Autowired
		SqlSession sqlSession;
		@Test
		public void test1(){
//		    System.out.println(departmentMapper);
//				Department department = new Department(null, "开发部");
//				int i = departmentMapper.insertSelective(department);
//				System.out.println(i);
				/*Employee employee = new Employee(null, "张文军", "男", "2447950131@qq.com", 1);
				int i = employeeMapper.insertSelective(employee);
				System.out.println(i);
				*/
				EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
				for(int i = 0;i<1000;i++){
						String uid = UUID.randomUUID().toString().substring(0,5)+i;
						mapper.insertSelective(new Employee(null,uid+"张文军", "M", uid+"2447950131@qq.com", 1));
				}
				System.out.println("批量完成");
		}


}
