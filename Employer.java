public class Employer extends Person {
    private String department;
    private String jobTitle;
    private double salary;
    private String dateOfHire;
    private int age;
    private String city;

    public Employer(int id, String name, String email, String department, String jobTitle, double salary, String dateOfHire, int age, String city) {
        super(id, name, email);
        this.department = department;
        this.jobTitle = jobTitle;
        this.salary = salary;
        this.dateOfHire = dateOfHire;
        this.age = age;
        this.city = city;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setId(int id) {
        super.setID(id);
    }

    public String getDateOfHire() {
        return dateOfHire;
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toCSV() {
        return super.toCSV() + "," + department + "," + jobTitle + "," + salary + "," + dateOfHire + "," + age + "," + city;
    }

}
