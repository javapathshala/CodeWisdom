/*
 * Created on Aug 7, 2006
 *
 */
package SimpleBasic;

/**
 * @author dimit
 *
 * Simple POJO
 */
public class Employee {

	private int employeeId;
	private String name;
	private String address;
	private double salary;

	Employee(){
	}
	/**
	 * @return address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @return employeeId
	 */
	public int getEmployeeId() {
		return employeeId;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return salary
	 */
	public double getSalary() {
		return salary;
	}

	/**
	 * @param  add String
	 */
	public void setAddress(String add) {
		address = add;
	}

	/**
	 * @param  empid int
	 */
	public void setEmployeeId(int empid) {
		employeeId = empid;
	}

	/**
	 * @param  empname String
	 */
	public void setName(String empname) {
		name = empname;
	}

	/**
	 * @param sal double
	 */
	public void setSalary(double sal) {
		salary = sal;
	}
}
