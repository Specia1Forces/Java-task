package info.kgeorgiy.ja.stolbov.student;

import info.kgeorgiy.java.advanced.student.GroupName;
import info.kgeorgiy.java.advanced.student.Student;
import info.kgeorgiy.java.advanced.student.StudentQuery;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class StudentDB implements StudentQuery {

    static final Comparator<Student> STUDENT_COMPARATOR = Comparator.comparing(Student::firstName).thenComparing(Student::lastName).thenComparing(Student::id);

    static final String MAX_EMPTY = "";

    private <T> List<T> mapHelp(List<Student> students, Function<Student, T> mapper) {
        return students.stream()
                .map(mapper)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getFirstNames(List<Student> students) {
        return mapHelp(students, Student::firstName);
    }

    @Override
    public List<String> getLastNames(List<Student> students) {
        return mapHelp(students, Student::lastName);
    }

    @Override
    public List<GroupName> getGroupNames(List<Student> students) {
        return mapHelp(students, Student::groupName);
    }

    @Override
    public List<String> getFullNames(List<Student> students) {
        return mapHelp(students, student -> student.firstName() + " " + student.lastName());
    }

    @Override
    public Set<String> getDistinctFirstNames(List<Student> students) {
        return students.stream()
                .map(Student::firstName)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    @Override
    public String getMaxStudentFirstName(List<Student> students) {
        return students.stream()
                .max(Student::compareTo)
                .map(Student::firstName)
                .orElse(MAX_EMPTY);
    }

    @Override
    public List<Student> sortStudentsById(Collection<Student> students) {
        return students.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<Student> sortAndFilter(Collection<Student> students, Predicate<? super Student> predicate) {
        return students.stream()
                .filter(predicate)
                .sorted(STUDENT_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> sortStudentsByName(Collection<Student> students) {
        return students.stream()
                .sorted(STUDENT_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> findStudentsByFirstName(Collection<Student> students, String name) {
        return sortAndFilter(students, student -> student.firstName().equals(name));
    }

    @Override
    public List<Student> findStudentsByLastName(Collection<Student> students, String name) {
        return sortAndFilter(students, student -> student.lastName().equals(name));
    }

    @Override
    public List<Student> findStudentsByGroup(Collection<Student> students, GroupName group) {
        return sortAndFilter(students, student -> student.groupName().equals(group));
    }

    @Override
    public Map<String, String> findStudentNamesByGroup(Collection<Student> students, GroupName group) {
        return students.stream()
                .filter(student -> student.groupName().equals(group))
                .collect(Collectors.toMap(Student::lastName, Student::firstName,
                        (firstName1, firstName2) -> firstName1.compareTo(firstName2) < 0 ? firstName1 : firstName2));
    }

    @Override
    public List<Map.Entry<String, String>> findStudentNamesByGroupList(List<Student> students, GroupName group) {
        return StudentQuery.super.findStudentNamesByGroupList(students, group);
    }


}

