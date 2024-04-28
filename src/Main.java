import com.romeulima.streampipeline.domain.employee.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        List<Employee> employeeList = new ArrayList<>();

        System.out.print("Enter full file path: ");
        String path = sc.nextLine();
        System.out.print("Enter salary: ");
        Double salary = sc.nextDouble();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line = br.readLine();

            while (line != null) {
                String[] fields = line.split(",");
                employeeList.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
                line = br.readLine();
            }

            List<String> emailList = employeeList.stream()
                    .filter(e -> e.getSalary() > salary)
                    .map(e -> e.getEmail())
                    .sorted()
                    .toList();

            double sum = employeeList.stream()
                            .filter(e -> e.getName().charAt(0) == 'M')
                                    .map(e -> e.getSalary())
                                            .reduce(0.0, (x, y) -> x + y);




            System.out.println("Email of people  whose salary is more than " + salary + ":");
            emailList.stream().forEach(System.out::println);
            System.out.println("Sum of salary of peoples whose name start with 'M': " + sum);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}