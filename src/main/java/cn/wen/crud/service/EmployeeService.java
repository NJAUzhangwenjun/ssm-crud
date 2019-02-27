package cn.wen.crud.service;

import cn.wen.crud.bean.Employee;
import cn.wen.crud.bean.EmployeeExample;
import cn.wen.crud.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 张文军
 * @Description:员工业务处
 * @Company:南京农业大学工学院
 * @version:1.0
 * @date 2019/2/2510:38
 */
@Service
public class EmployeeService {
		@Autowired
		EmployeeMapper employeeMapper;
		/**
		 * 查询所有员工信息（分页查询）
		 * @return
		 */
		public List<Employee> getAll() {
				List<Employee> employees = employeeMapper.selectByExampleWithDept(null);
				return employees;
		}

		/**
		 * 添加员工
		 * @param employee
		 */
		public void save(Employee employee) {
				employeeMapper.insertSelective(employee);
		}

		/**
		 * 查询用户名是否存在
		 * @return
		 */
		public Boolean exitEmpName(String empName) {
				EmployeeExample employeeExample = new EmployeeExample();
				EmployeeExample.Criteria criteria = employeeExample.createCriteria().andEmpNameEqualTo(empName);
				long count = employeeMapper.countByExample(employeeExample);
				return count == 0;
		}
}
