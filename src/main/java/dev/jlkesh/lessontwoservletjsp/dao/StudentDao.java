package dev.jlkesh.lessontwoservletjsp.dao;

import dev.jlkesh.lessontwoservletjsp.dto.StudentCreateDTO;
import dev.jlkesh.lessontwoservletjsp.domain.Student;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StudentDao extends DAO {

    private static final ThreadLocal<StudentDao> studentDaoThreadLocal = ThreadLocal.withInitial(StudentDao::new);

    public static StudentDao getInstance() {
        return studentDaoThreadLocal.get();
    }

    public static final String INSERT_STUDENT_QUERY = """
            insert into students (firstname, lastname, age) values (?, ? , ?) returning id, createdat;""";

    public static final String UPDATE_STUDENT_QUERY= """
            update students set firstname=?,lastname=?, age=? where id=?
            """;

    public static final String DELETE_STUDENT_QUERY= """
            delete from students where id=?;
            """;

    public Student save(@NonNull StudentCreateDTO dto) {
        Student student = Student.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .age(dto.age())
                .count(dto.count())
                .build();
        Connection connection = getConnection();
        try (PreparedStatement pr = connection.prepareStatement(INSERT_STUDENT_QUERY)) {
            pr.setString(1, student.getFirstName());
            pr.setString(2, student.getLastName());
            pr.setShort(3, student.getAge());
            ResultSet resultSet = pr.executeQuery();
            if (resultSet.next()) {
                student.setId(resultSet.getLong("id"));
                student.setCreatedAt(resultSet.getTimestamp("createdat").toLocalDateTime());
            }
            return student;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Student> findAll() {
        int c=0;
        List<Student> students = new ArrayList<>();
        Connection connection = getConnection();
        try (PreparedStatement pr = connection.prepareStatement("select * from students order by createdat desc ")) {
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                students.add(Student.builder()
                        .id(rs.getLong("id"))
                        .firstName(rs.getString("firstname"))
                        .lastName(rs.getString("lastname"))
                        .age(rs.getShort("age"))
                        .count(++c)
                        .createdAt(rs.getTimestamp("createdat").toLocalDateTime())
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    public Student findById(long studentID) {
        Connection connection = getConnection();
        try (PreparedStatement pr = connection.prepareStatement("select * from students t where t.id = ?;")) {
            pr.setLong(1, studentID);
            ResultSet rs = pr.executeQuery();

            if (rs.next())
                return Student.builder()
                        .id(rs.getLong("id"))
                        .firstName(rs.getString("firstname"))
                        .lastName(rs.getString("lastname"))
                        .age(rs.getShort("age"))
                        .createdAt(rs.getTimestamp("createdat").toLocalDateTime())
                        .build();
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Student update(Student student, StudentCreateDTO studentCreateDTO) {

        student.setFirstName(studentCreateDTO.firstName());
        student.setLastName(studentCreateDTO.lastName());
        student.setAge(studentCreateDTO.age());

        Connection connection = getConnection();
        try (PreparedStatement pr = connection.prepareStatement(UPDATE_STUDENT_QUERY)) {
            pr.setString(1, student.getFirstName());
            pr.setString(2, student.getLastName());
            pr.setShort(3, student.getAge());
            pr.setLong(4,student.getId());
            pr.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return student;
    }

    public Student delete(Long byId) {
        Student byId1 = findById(byId);
        Connection connection = getConnection();
        try (PreparedStatement pr = connection.prepareStatement(DELETE_STUDENT_QUERY)) {
            pr.setLong(1, byId);
            pr.execute();
            return byId1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
