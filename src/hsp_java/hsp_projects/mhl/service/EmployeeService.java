package hsp_java.hsp_projects.mhl.service;

import hsp_java.hsp_projects.mhl.dao.EmployeeDAO;
import hsp_java.hsp_projects.mhl.domain.Employee;

//该类完成对employee表的各种操作（通过调用EmployeeDAO对象完成）
public class EmployeeService { //业务层

    //定义一个 EmployeeDAO 属性
    private EmployeeDAO employeeDAO = new EmployeeDAO();

    //方法：根据empId和pwd返回一个Employee对象，如果查询不到就返回null
    public Employee getEmployeeByIdAndPwd(String empId, String pwd) {
        return employeeDAO.querySingle("select * from employee where empId = ? and pwd = md5(?)", Employee.class, empId, pwd);
    }

    //
}
