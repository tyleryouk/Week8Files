import java.util.Comparator;

/** This class represents employees */
public class EmployeeComparable extends Object implements Comparable<Employee> {
  
  // a field to store the employee name
  private String name;
  
  // a field to store the employee salary
  private double salary;
  
  // a field to store the emploee number
  private final int number;
  
  // a field to store the last employee number used
  private static int lastEmployeeNumber = 0;
  
  /* A constructor to make an employee with the given name and salary */
  public Employee(String name, double salary) {
    this(Employee.lastEmployeeNumber + 1, name, salary);
  }
  
  /* A constructor to make an employe with the given name, number, and salary */
  public Employee(int number, String name, double salary) {
    super();
    this.number = number;
    this.name = name;
    this.salary = salary;
    if (this.number > Employee.lastEmployeeNumber)
      Employee.lastEmployeeNumber = number;
  }
  
  /* get the name of the employee */
  public String getName() {
    return name;
  }
  
  /* set the name of the employee */
  public void setName(String name) {
    this.name = name;
  }
  
  /* get the salary of the employee */
  public double getSalary() {
    return this.salary;
  }
  
  /* set the salary of the employee */
  public void setSalary(double salary) {
    this.salary = salary;
  }
  
  /* get the employee number */
  public int getNumber() {
    return number;
  }
  
  /* change the behavior of the inherited toString */
  public String toString() {
    return getNumber() + ": " + getName();
  }
  
  /* change the behavior of the inherited equals method.  Two employee instances are the same
   * if they have the same number and name 
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof Employee) {
      Employee e = (Employee)o;
      return (this.getNumber() == e.getNumber() && this.getName().equals(e.getName()));
    }
    return false;
  }
  
  /* Returns true if this employee earns more than the input employee */
  public boolean earnsMoreThan(Employee e) {
    return this.getSalary() > e.getSalary();
  }
  
  /**
   * Compare this employee with the input employee, and order them by the employee name
   * @param employee the employee to compare this employee to
   * @return < 0,0, > 0, if this employee is ordered before, the same as, or after the input
   */
  public int compareTo(Employee employee) {
    return this.getName().compareTo(employee.getName());
  }
  
  /** Returns a comparator that compares employees by salary
    * @return a comparator that compares employees by salary
    */
  public static Comparator<Employee> compareBySalary() {
    return new CompareBySalary();
  }
  
  /** A class that implements Comparator, comparing two employees by their salary */
  private static class CompareBySalary implements Comparator<Employee> {
   
    /**
     * Compares employees by salary
     * @param employee1 the first employee to compare
     * @param employee2 the second employee to compare
     * @return < 0, = 0, or > 0 if employee1 has a smaller, same as, or larger salary than employee2
     */
    public int compare(Employee employee1, Employee employee2) {
      return (int)((employee1.getSalary() - employee2.getSalary()) * 100 + 0.5);
    }
    
  }
  
  /**
   * Returns a Comparator that compares employees by how close their salary is to this employee
   * @return a comparator that compares employees by how close their salary is to this employee
   */
  public Comparator<Employee> compareByClosestSalary() {
    return new CompareSalaryToThisEmployee();
  }
  
  /* A class that implements Comparator that compares by how close an employee's salary is to
   * this employee */
  private class CompareSalaryToThisEmployee implements Comparator<Employee> {
    
    /**
     * Compares two employees by how close they are to this employee's salary
     * @param employee1 the first employee to compare
     * @param employee2 the second employee to compare
     * @return < 0, = 0, or > 0 if employee1's salary is closer to, the same amount from, or further from this employee's salary than employee2's salary
     */
    public int compare(Employee employee1, Employee employee2) {
      int diff1 = (int)(Math.abs(employee1.getSalary() - Employee.this.getSalary()) * 100 + 0.5);
      int diff2 = (int)(Math.abs(employee2.getSalary() - Employee.this.getSalary()) * 100 + 0.5);
      
      return diff1 - diff2;
    }
  }
  

  /** 
   * Return a comparator that sorts by employee number
   * @return the comparator that compares employees by employee number
   */
  public static Comparator<Employee> compareByNumber() {
    return new Comparator<Employee>() {
      public int compare(Employee employee1, Employee employee2) {
        return employee1.getNumber() - employee2.getNumber();
      }
    };
  }
}