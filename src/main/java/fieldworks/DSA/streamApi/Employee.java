package fieldworks.DSA.streamApi;


public class Employee implements Comparable<Employee> {
    int age;
    String name;
    String department;
    String gender;
    String city;
    int salary;

    Employee(int age, String name, String department, String gender, String city, int salary) {
        this.age = age;
        this.name = name;
        this.department = department;
        this.gender = gender;
        this.city = city;
        this.salary = salary;
    }

    public int getAge() {
        return this.age;
    }

    public String getName() {
        return this.name;
    }

    public String getDepartment() {
        return this.department;
    }

    public String getGender() {
        return this.gender;
    }

    public String getCity() {
        return this.city;
    }

    public int getSalary() {
        return this.salary;
    }

    @Override
    public int compareTo(Employee other) {
        // TODO Auto-generated method stub
        return this.name.compareTo(other.name);
    }

    // // by age
    // @Override
    // public int compareTo(Employee other) {
    //     return Integer.compare(this.age, other.age);
    // }

    // // descending
    // @Override
    // public int compareTo(Employee other) {
    //     return Integer.compare(other.age, this.age);
    // }

}
