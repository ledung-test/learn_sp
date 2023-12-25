package com.example.stream_api.dao;

import com.example.stream_api.database.PersonDB;
import com.example.stream_api.model.Person;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class PersonDAOImpl implements PersonDAO {

    //in ra danh sách
    @Override
    public void printListPeople(List<Person> persons) {
        persons.forEach(System.out::println);
    }

    //Liệt kê danh sách tất cả
    @Override
    public List<Person> getAll() {
        return PersonDB.personList;
    }

    //Liệt kê danh sách sắp xếp theo tên full name từ A-Z
    @Override
    public List<Person> sortPeopleByFullName() {
        return PersonDB.personList.stream()
                .sorted((o1, o2) -> o1.getName().compareTo(o2.getName()))
                .toList();
    }

    //Liệt kê danh sách sắp xếp theo tên full name từ Z-A
    @Override
    public List<Person> sortPeopleByFullNameReversed() {
        return PersonDB.personList.stream()
                .sorted((o1, o2) -> o2.getName().compareTo(o1.getName()))
                .toList();
    }

    //Lấy danh sách tất cả nghề nghiệp đã được sắp xếp từ A-Z
    @Override
    public List<String> getSortedJobs() {
        return PersonDB.personList.stream()
                .map(person -> person.getJob())
                .sorted((o1, o2) -> o1.compareTo(o2))
                .distinct()
                .toList();
    }

    //Lấy danh sách tất cả thành phố đã được sắp xếp từ A-Z
    @Override
    public List<String> getSortedCities() {
        return PersonDB.personList.stream()
                .map(person -> person.getCity())
                .sorted((o1, o2) -> o1.compareTo(o2))
                .distinct()
                .toList();
    }

    // Lấy danh sách tất cả tên của những người có giới tính nữ
    @Override
    public List<String> femaleNames() {
        return PersonDB.personList.stream()
                .filter(person -> person.getGender().equals("Female"))
                .map(person -> person.getName())
                .toList();

    }

    // Tìm người có mức lương cao nhất
    @Override
    public Person highestEarner() {
        return PersonDB.personList.stream()
                .max(Comparator.comparingInt(person -> person.getSalary()))
                .orElse(null);
    }

    // Lọc ra những người có năm sinh sau năm 1990
    @Override
    public List<Person> bornAfter1990() {
        return PersonDB.personList.stream()
                .filter(person -> person.getBirthday().getYear() > 1990)
                .toList();
    }

    // Tính lương trung bình của mọi người
    @Override
    public double averageSalary() {
        return PersonDB.personList.stream()
                .mapToInt(value -> value.getSalary())
                .average()
                .orElse(0.0);
    }

    // Tính tuổi trung bình của mọi người
    @Override
    public double averageAge() {
        return PersonDB.personList.stream()
                .mapToInt(value -> LocalDate.now().getYear() - (value.getBirthday().getYear()))
                .average()
                .orElse(0.0);
    }

    // Lọc ra các person trong tên có chứa keyword
    @Override
    public List<Person> nameContains(String keyword) {
        return PersonDB.personList.stream()
                .filter(person -> person.getName().contains(keyword))
                .toList();
    }

    // Sắp xếp tuổi của những người có giới tính Male giảm dần
    @Override
    public List<Person> sortedByAgeForMale() {
        return PersonDB.personList.stream()
                .filter(person -> person.getGender().equals("Male"))
                .sorted(((o1, o2) -> o1.getBirthday().getYear() - o2.getBirthday().getYear()))
                .toList();
    }

    // Tìm người có tên dài nhất
    @Override
    public Person longestName() {
        return PersonDB.personList.stream()
                .max(Comparator.comparingInt(value -> value.getName().length()))
                .orElse(null);
    }

    // Lấy danh sách những người có mức lương lớn hơn mức lương trung bình
    @Override
    public List<Person> aboveAverageSalary() {
        double avgSalary = averageSalary();
        return PersonDB.personList.stream()
                .filter(person -> person.getSalary() > avgSalary)
                .toList();
    }

    //Gom tất cả những người trong cùng một thành phố lại
    @Override
    public Map<String, List<Person>> groupPeopleByCity() {
        return PersonDB.personList.stream()
                .collect(Collectors.groupingBy(person -> person.getCity()));
    }

    //Nhóm các nghề nghiệp và đếm số người làm mỗi nghề
    @Override
    public Map<String, Integer> groupJobByCount() {
        return PersonDB.personList.stream()
                .collect(Collectors.groupingBy(person -> person.getJob(), Collectors.summingInt(p -> 1)));
    }

    // Liệt kê danh sách những người có mức lương trong khoảng được chỉ định start -> end
    @Override
    public List<Person> inSalaryRange(int start, int end) {
        return PersonDB.personList.stream()
                .filter(person -> person.getSalary() >= start && person.getSalary() <= end)
                .toList();
    }

    // Liệt kê danh sách những người có tên bắt đầu bằng 1 prefix nào đó
    @Override
    public List<Person> startsWith(String prefix) {
        return PersonDB.personList.stream()
                .filter(person -> person.getName().startsWith(prefix))
                .toList();
    }

    // Sắp xếp danh sách theo năm sinh giảm dần
    @Override
    public List<Person> sortByBirthYearDescending() {
        return PersonDB.personList.stream()
                .sorted((o1, o2) -> o2.getBirthday().getYear() - o1.getBirthday().getYear())
                .toList();
    }

    // Lấy danh sách 5 người có mức lương cao nhất
    @Override
    public List<Person> top5HighestPaid() {
        return PersonDB.personList.stream()
                .sorted((o1, o2) -> o2.getSalary() - o1.getSalary())
                .limit(5)
                .toList();
    }

    // Liệt kê danh sách những người có tuổi trong khoảng được chỉ định start -> end
    @Override
    public List<Person> inAgeRange(int start, int end) {
        return PersonDB.personList.stream()
                .filter(person -> (LocalDate.now().getYear() - person.getBirthday().getYear()) >= start
                        && (LocalDate.now().getYear() - person.getBirthday().getYear()) <= end)
                .toList();
    }
}
