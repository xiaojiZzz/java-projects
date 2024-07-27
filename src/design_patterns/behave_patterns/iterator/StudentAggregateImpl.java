package design_patterns.behave_patterns.iterator;

import java.util.ArrayList;
import java.util.List;

public class StudentAggregateImpl implements StudentAggregate {

    private List<Student> list = new ArrayList<>();

    @Override
    public void addStudent(Student stu) {
        list.add(stu);
    }

    @Override
    public void removeStudent(Student stu) {
        list.remove(stu);
    }

    @Override
    //获取迭代器对象
    public StudentIterator getStudentIterator() {
        return new StudentIteratorImpl(list);
    }
}
