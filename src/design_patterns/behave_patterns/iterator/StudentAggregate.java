package design_patterns.behave_patterns.iterator;

//抽象聚合角色接口
public interface StudentAggregate {
    //添加学生的功能
    void addStudent(Student stu);

    //删除学生的功能
    void removeStudent(Student stu);

    //获取迭代器对象功能
    StudentIterator getStudentIterator();
}
