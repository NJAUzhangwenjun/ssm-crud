package cn.wen.crud.controller;

import cn.wen.crud.bean.Department;
import cn.wen.crud.bean.Employee;
import cn.wen.crud.bean.Msg;
import cn.wen.crud.service.DepartmentService;
import cn.wen.crud.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 张文军
 * @Description:处理员工请求
 * @Company:南京农业大学工学院
 * @version:1.0
 * @date 2019/2/2510:34
 */
@Controller
public class EmployeeController {
		@Autowired
		EmployeeService employeeService;

		@Autowired
		DepartmentService departmentService;



		/**
		 * 查询员工数据（分页查询）
		 * @return
		 */
		@RequestMapping("/emps")
		@ResponseBody
		public Msg getEmpsWithJson(
						@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
				// 这不是一个分页查询
				// 引入PageHelper分页插件
				// 在查询之前只需要调用，传入页码，以及每页的大小
				PageHelper.startPage(pn, 8);
				// startPage后面紧跟的这个查询就是一个分页查询
				List<Employee> emps = employeeService.getAll();
				// 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
				// 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
				PageInfo page = new PageInfo(emps, 5);
				return Msg.success().add("pageInfo",page);
		}


		/**
		 * 查询部门数据
		 * @return
		 */
		@RequestMapping(value = "/depts")
		@ResponseBody
		public Msg getDeptsWithJson() {
				List<Department> depts = departmentService.getAll();
				return Msg.success().add("depts",depts);
		}

		/**
		 * 查询部门数据
		 * @return
		 */
		@RequestMapping(value = "/exitEmpName")
		@ResponseBody
		public Msg exitEmpName(String empName) {
				Boolean exit = employeeService.exitEmpName(empName);
				if(exit){
						return Msg.success();
				}else{
						return Msg.fail();
				}

		}



		/**
		 * 添加员工
		 * @return
		 */
		@RequestMapping(value = "/emp",method = RequestMethod.POST)
		@ResponseBody
		public Msg saveWithJson(Employee employee) {
				try {
						employeeService.save(employee);
						return Msg.success();
				} catch (Exception e) {
						e.printStackTrace();
						return Msg.fail();
				}
		}







	/*	//**
		 * 查询员工数据（分页查询）
		 * @return
		 *//*
		@RequestMapping("/emps")
		public String getEmps(@RequestParam(value = "pn",defaultValue = "1") Integer pn, Model model){
				***传入页码和每页记录数*//*
				PageHelper.startPage(pn, 5);
				List<Employee> emps = employeeService.getAll();
				//用PageInfo对结果进行包装（里面有预定义的详细分页信息）传入需要在页面连续显示的页码数
				PageInfo page = new PageInfo(emps,5);
				model.addAttribute("pageInfo", page);
				return "list";
		}*/
}
