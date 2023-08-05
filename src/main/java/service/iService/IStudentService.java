package service.iService;

import model.Student;

import java.util.List;

public interface IStudentService<E> {
    void add(E e);

    void edit(int id, E e);

    void delete(int id);

    List<E> showAll();

    Student findStudentById(int id);

    List<E> findByName(String name);
}
