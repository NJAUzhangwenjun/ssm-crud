package cn.wen.crud.service;

import cn.wen.crud.bean.Department;
import cn.wen.crud.dao.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 张文军
 * @Description:部门业务处
 * @Company:南京农业大学工学院
 * @version:1.0
 * @date 2019/2/268:26
 */
@Service
public class DepartmentService {
		@Autowired
		private DepartmentMapper departmentMapper;

		/**
		 * 查询部门数据
		 * @return
		 */
		public List<Department> getAll() {
				return departmentMapper.selectByExample(null);
		}
}
