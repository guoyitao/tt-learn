package priorityqueue.minheap;

public class Employee implements Comparable<Employee>{
    private String name;
    private Double payRate;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPayRate() {
        return payRate;
    }

    public void setPayRate(Double payRate) {
        this.payRate = payRate;
    }

    public Employee(String name, Double payRate) {
        this.name = name;
        this.payRate = payRate;
    }


    @Override
    public int compareTo(Employee o) {
        if (o != null){
            return this.payRate.compareTo(o.getPayRate());
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", payRate=" + payRate +
                '}';
    }
}
