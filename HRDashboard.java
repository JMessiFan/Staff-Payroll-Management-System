import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Graphics;

public class HRDashboard extends JFrame {
    private List<Person> persons;
    private int idCounter;

    private JButton editEmployeeButton;

    public HRDashboard() {
        persons = new ArrayList<>();
        loadEmployers();
        idCounter = loadIdCounter();

        setTitle("HR Dashboard");
        setSize(750, 730);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Set the background color to wood brown
                Color woodBrown = new Color(88, 57, 39);
                g.setColor(woodBrown);
                g.fillRect(0, 0, getWidth(), getHeight());
                }
        };
        panel.setLayout(null);
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }
    
    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        // Load the background image
        ImageIcon backgroundImage = new ImageIcon("HRDashboard.jpg");

        // Create a JLabel to hold the background image
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(-10, 35, getWidth(), getHeight());
        panel.add(backgroundLabel, 0);

        JButton addEmployerButton = new JButton("Add Employee");
        addEmployerButton.setBounds(200, 20, 160, 25);
        addEmployerButton.setBackground(new Color(245, 245, 220)); // Beige
        panel.add(addEmployerButton);

        JButton viewEmployersButton = new JButton("View Employees");
        viewEmployersButton.setBounds(370, 20, 160, 25);
        viewEmployersButton.setBackground(new Color(245, 245, 220));
        panel.add(viewEmployersButton);

        addEmployerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EmployerForm(HRDashboard.this);
            }
        });

        viewEmployersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewEmployers();
            }
        });

        JButton deleteEmployeeButton = new JButton("Delete Employee");
        deleteEmployeeButton.setBounds(200, 50, 160, 25);
        deleteEmployeeButton.setBackground(new Color(245, 245, 220)); 
        panel.add(deleteEmployeeButton);

        deleteEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        panel.setBackground(new Color(245, 245, 220)); // Change background color to beige
                        panel.repaint(); // Repaint the panel
                        deleteEmployee();
                        }
                });
            }
        });


        editEmployeeButton = new JButton("Edit Employee Info");
        editEmployeeButton.setBounds(370, 50, 160, 25);
        editEmployeeButton.setBackground(new Color(245, 245, 220)); 
        panel.add(editEmployeeButton);

        editEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editEmployeeInformation();
            }
        });
        
        JButton managePayrollButton = new JButton("Manage Payroll");
        managePayrollButton.setBounds(200, 80, 160, 25);
        managePayrollButton.setBackground(new Color(245, 245, 220)); 
        panel.add(managePayrollButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(370, 80, 160, 25);
        logoutButton.setBackground(new Color(150, 0, 50)); // Burgundy color
        logoutButton.setForeground(Color.WHITE);
        panel.add(logoutButton);
        
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });
        
        managePayrollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                managePayroll();
            }
        });
    }

    private void managePayroll() {
        try {
            String inputId = JOptionPane.showInputDialog(this, "Enter Employee ID for Payroll Management:");
            int employeeId = Integer.parseInt(inputId);
            
            Employer employee = findEmployeeById(employeeId);
            if (employee != null) {
                displayPayrollManagementGUI(employee);
            } else {
                JOptionPane.showMessageDialog(this, "Employee with ID " + employeeId + " not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid Employee ID. Please enter a valid numeric ID.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void logout() {
    // Perform logout operations here, such as closing the HRDashboard window and opening the login window
    dispose(); // Close the HRDashboard window

    // Create a custom JOptionPane with a red OK button
    JButton okButton = new JButton("OK");
    okButton.setBackground(Color.RED);
    okButton.setForeground(Color.WHITE);

    JOptionPane.showOptionDialog(
            this,
            "Logged out successfully.",
            "Logout",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            new Object[]{okButton},
            okButton);

    new LoginPage(); // Open the login window
}


    private Employer findEmployeeById(int id) {
        for (Person person : persons) {
            if (person instanceof Employer && person.getId() == id) {
                return (Employer) person;
            }
        }
        return null;
    }

    private void displayPayrollManagementGUI(Employer employee) {
        JFrame payrollFrame = new JFrame("Payroll Management");
        JPanel payrollPanel = new JPanel();
        payrollPanel.setBackground(new Color(0, 100, 0)); // Dark Green background color
        payrollFrame.add(payrollPanel);
        payrollFrame.setSize(330, 320);
        payrollFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        payrollPanel.setLayout(null);

        JLabel employeeIdLabel = new JLabel("Employee ID:");
        employeeIdLabel.setBounds(20, 20, 100, 25);
        payrollPanel.add(employeeIdLabel);

        JLabel employeeIdValue = new JLabel(String.valueOf(employee.getId()));
        employeeIdValue.setBounds(130, 20, 150, 25);
        payrollPanel.add(employeeIdValue);

        JLabel employeeNameLabel = new JLabel("Employee Name:");
        employeeNameLabel.setBounds(20, 50, 100, 25);
        payrollPanel.add(employeeNameLabel);

        JLabel employeeNameValue = new JLabel(employee.getName());
        employeeNameValue.setBounds(130, 50, 150, 25);
        payrollPanel.add(employeeNameValue);

        JLabel baseSalaryLabel = new JLabel("Base Salary:");
        baseSalaryLabel.setBounds(20, 80, 100, 25);
        payrollPanel.add(baseSalaryLabel);

        JLabel baseSalaryValue = new JLabel(String.valueOf(employee.getSalary()));
        baseSalaryValue.setBounds(130, 80, 150, 25);
        payrollPanel.add(baseSalaryValue);

        JLabel monthLabel = new JLabel("Month:");
        monthLabel.setBounds(20, 110, 100, 25);
        payrollPanel.add(monthLabel);

        JTextField monthField = new JTextField();
        monthField.setBounds(130, 110, 150, 25);
        payrollPanel.add(monthField);

        JLabel yearLabel = new JLabel("Year:");
        yearLabel.setBounds(20, 140, 100, 25);
        payrollPanel.add(yearLabel);

        JTextField yearField = new JTextField();
        yearField.setBounds(130, 140, 150, 25);
        payrollPanel.add(yearField);

        JLabel overtimeLabel = new JLabel("Overtime Total:");
        overtimeLabel.setBounds(20, 170, 100, 25);
        payrollPanel.add(overtimeLabel);

        JTextField overtimeField = new JTextField();
        overtimeField.setBounds(130, 170, 150, 25);
        payrollPanel.add(overtimeField);

        JLabel bonusesLabel = new JLabel("Bonuses:");
        bonusesLabel.setBounds(20, 200, 100, 25);
        payrollPanel.add(bonusesLabel);

        JTextField bonusesField = new JTextField();
        bonusesField.setBounds(130, 200, 150, 25);
        payrollPanel.add(bonusesField);

        JButton calculateButton = new JButton("Calculate Salary");
        calculateButton.setBounds(100, 240, 150, 25);
        calculateButton.setBackground(new Color(255, 253, 208)); // Cream background color
        payrollPanel.add(calculateButton);

        employeeIdLabel.setForeground(Color.WHITE);
        employeeIdValue.setForeground(Color.WHITE);
        employeeNameLabel.setForeground(Color.WHITE);
        baseSalaryLabel.setForeground(Color.WHITE);
        baseSalaryValue.setForeground(Color.WHITE);
        monthLabel.setForeground(Color.WHITE);
        yearLabel.setForeground(Color.WHITE);
        employeeNameValue.setForeground(Color.WHITE);
        overtimeLabel.setForeground(Color.WHITE);
        bonusesLabel.setForeground(Color.WHITE);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String month = monthField.getText();
                String year = yearField.getText();
                String overtime = overtimeField.getText();
                String bonuses = bonusesField.getText();

                calculateSalary(employee, month, year, overtime, bonuses);
            }
        });

        payrollFrame.setVisible(true);
    }

    private void calculateSalary(Employer employee, String month, String year, String overtime, String bonuses) {
        double baseSalary = employee.getSalary();
        double overtimeHours = Double.parseDouble(overtime);
        double bonusAmount = Double.parseDouble(bonuses);
        double totalSalary = baseSalary + overtimeHours + bonusAmount;

        String resultMessage = "Employee " + employee.getName() + "'s salary for " + month + " " + year + " is RM" + totalSalary;

        JOptionPane.showMessageDialog(this, resultMessage, "Salary Calculation", JOptionPane.INFORMATION_MESSAGE);

        saveSalaryDetails(employee.getId(), month, year, totalSalary);
    }

    private void saveSalaryDetails(int employeeId, String month, String year, double totalSalary) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("salary_details.txt", true))) {
            writer.println(employeeId + "," + month + "," + year + "," + totalSalary);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void editEmployeeInformation() {
    try {
        String inputId = JOptionPane.showInputDialog(this, "Enter Employee ID to edit:");
        int employeeId = Integer.parseInt(inputId);

        Employer employeeToEdit = null;
        for (Person person : persons) {
            if (person instanceof Employer && person.getId() == employeeId) {
                employeeToEdit = (Employer) person;
                break;
            }
        }

        if (employeeToEdit != null) {
            JTextField nameField = new JTextField(employeeToEdit.getName(), 20);
            JTextField emailField = new JTextField(employeeToEdit.getEmail(), 20);
            JTextField departmentField = new JTextField(employeeToEdit.getDepartment(), 20);
            JTextField jobTitleField = new JTextField(employeeToEdit.getJobTitle(), 20);
            JTextField salaryField = new JTextField(String.valueOf(employeeToEdit.getSalary()), 20);
            JTextField dateOfHireField = new JTextField(employeeToEdit.getDateOfHire(), 20);
            JTextField ageField = new JTextField(String.valueOf(employeeToEdit.getAge()), 20);
            JTextField cityField = new JTextField(employeeToEdit.getCity(), 20);

            JPanel editPanel = new JPanel();
            editPanel.setBackground(new Color(0, 0, 128)); // Set background color to navy blue
            editPanel.setLayout(new GridLayout(0, 1));
            editPanel.add(new JLabel("Name:")).setForeground(Color.WHITE);
            editPanel.add(nameField);
            editPanel.add(new JLabel("Email:")).setForeground(Color.WHITE);
            editPanel.add(emailField);
            editPanel.add(new JLabel("Department:")).setForeground(Color.WHITE);
            editPanel.add(departmentField);
            editPanel.add(new JLabel("Job Title:")).setForeground(Color.WHITE);
            editPanel.add(jobTitleField);
            editPanel.add(new JLabel("Salary:")).setForeground(Color.WHITE);
            editPanel.add(salaryField);
            editPanel.add(new JLabel("Date of Hire:")).setForeground(Color.WHITE);
            editPanel.add(dateOfHireField);
            editPanel.add(new JLabel("Age:")).setForeground(Color.WHITE);
            editPanel.add(ageField);
            editPanel.add(new JLabel("City:")).setForeground(Color.WHITE);
            editPanel.add(cityField);

            int option = JOptionPane.showConfirmDialog(this, editPanel, "Edit Employee Details", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (option == JOptionPane.OK_OPTION) {
                String name = nameField.getText();
                String email = emailField.getText();
                String department = departmentField.getText();
                String jobTitle = jobTitleField.getText();
                double salary = Double.parseDouble(salaryField.getText());
                String dateOfHire = dateOfHireField.getText();
                int age = Integer.parseInt(ageField.getText());
                String city = cityField.getText();

                employeeToEdit.setName(name);
                employeeToEdit.setEmail(email);
                employeeToEdit.setDepartment(department);
                employeeToEdit.setJobTitle(jobTitle);
                employeeToEdit.setSalary(salary);

                saveEmployers();

                JOptionPane.showMessageDialog(this, "Employee with ID " + employeeId + " updated successfully.", "Edit Employee", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Employee with ID " + employeeId + " does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Invalid Employee ID. Please enter a valid numeric ID.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}


    public void addEmployer(Employer employer) {
        idCounter++;
        employer.setId(idCounter);

        if (persons.stream().noneMatch(p -> p.getId() == idCounter)) {
            persons.add(employer);
            saveEmployers();
            saveIdCounter();
        } else {
            JOptionPane.showMessageDialog(this, "An employer with the same ID already exists.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewEmployers() {
    loadEmployers();

    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    getContentPane().add(panel);

    Color beigeColor = new Color(223, 204, 166);
    Color lightBrownColor = new Color(88, 57, 39);
    Color currentColor;

    for (int i = 0; i < persons.size(); i++) {
        if (i % 2 == 0) {
            currentColor = beigeColor;
        } else {
            currentColor = lightBrownColor;
        }

        if (persons.get(i) instanceof Employer) {
            Employer employer = (Employer) persons.get(i);
            JPanel detailsPanel = new JPanel(new GridLayout(0, 1));
            detailsPanel.setBackground(currentColor);

            JLabel idLabel = new JLabel("ID: " + employer.getId());
            JLabel nameLabel = new JLabel("Name: " + employer.getName());
            JLabel emailLabel = new JLabel("Email: " + employer.getEmail());
            JLabel departmentLabel = new JLabel("Department: " + employer.getDepartment());
            JLabel jobTitleLabel = new JLabel("Job Title: " + employer.getJobTitle());
            JLabel salaryLabel = new JLabel("Salary: " + employer.getSalary());
            JLabel dateOfHireLabel = new JLabel("Date of Hire: " + employer.getDateOfHire());
            JLabel ageLabel = new JLabel("Age: " + employer.getAge());
            JLabel cityLabel = new JLabel("City: " + employer.getCity());

            // Set font color to white for dark brown sections
            if (currentColor.equals(lightBrownColor)) {
            idLabel.setForeground(Color.WHITE);
            nameLabel.setForeground(Color.WHITE);
            emailLabel.setForeground(Color.WHITE);
            departmentLabel.setForeground(Color.WHITE);
            jobTitleLabel.setForeground(Color.WHITE);
            salaryLabel.setForeground(Color.WHITE);
            dateOfHireLabel.setForeground(Color.WHITE);
            ageLabel.setForeground(Color.WHITE);
            cityLabel.setForeground(Color.WHITE);
            }

            detailsPanel.add(idLabel);
            detailsPanel.add(nameLabel);
            detailsPanel.add(emailLabel);
            detailsPanel.add(departmentLabel);
            detailsPanel.add(jobTitleLabel);
            detailsPanel.add(salaryLabel);
            detailsPanel.add(dateOfHireLabel);
            detailsPanel.add(ageLabel);
            detailsPanel.add(cityLabel);

            panel.add(detailsPanel);
        }
    }

    if (panel.getComponentCount() == 0) {
        JOptionPane.showMessageDialog(this, "No employers to display.", "View Employers", JOptionPane.INFORMATION_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(this, panel, "View Employers", JOptionPane.PLAIN_MESSAGE);
    }
}

private String getHexColor(Color color) {
    return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
}



    private void loadEmployers() {
        persons.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader("employers.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length >= 9) {
                    int id = Integer.parseInt(data[0]);
                    String name = data[1];
                    String email = data[2];
                    String department = data[3];
                    String jobTitle = data[4];
                    double salary = Double.parseDouble(data[5]);
                    String dateOfHire = data[6];
                    int age = Integer.parseInt(data[7]);
                    String city = data[8];

                    Employer employer = new Employer(id, name, email, department, jobTitle, salary, dateOfHire, age, city);
                    persons.add(employer);
                } else {
                    System.out.println("Invalid data format: " + line);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void saveEmployers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("employers.csv"))) {
            for (Person person : persons) {
                if (person instanceof Employer) {
                    writer.write(((Employer) person).toCSV());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int loadIdCounter() {
        try (BufferedReader reader = new BufferedReader(new FileReader("idCounter.txt"))) {
            return Integer.parseInt(reader.readLine());
        } catch (FileNotFoundException e) {
            return 0;
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private void saveIdCounter() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("idCounter.txt"))) {
            writer.write(String.valueOf(idCounter));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void editEmployeeDetails(String employeeID, String name, String email, String department, String jobTitle, double salary, String dateOfHire, int age, String city) {
        for (Person person : persons) {
            if (person instanceof Employer && person.getId() == Integer.parseInt(employeeID)) {
                Employer employer = (Employer) person;
                employer.setName(name);
                employer.setEmail(email);
                employer.setDepartment(department);
                employer.setJobTitle(jobTitle);
                employer.setSalary(salary);

                saveEmployers();
                JOptionPane.showMessageDialog(this, "Employee details updated successfully.", "Edit Employee", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Employee with ID " + employeeID + " not found.", "Edit Employee", JOptionPane.ERROR_MESSAGE);
    }

    private void deleteEmployee() {
    try {
        JPanel deletePanel = new JPanel();
        deletePanel.setBackground(Color.RED); // Red background color
        deletePanel.setLayout(new GridLayout(0, 1));

        JLabel deleteLabel = new JLabel("Enter Employee ID to delete:");
        deleteLabel.setForeground(Color.WHITE); // White font color
        deletePanel.add(deleteLabel);

        JTextField idField = new JTextField();
        deletePanel.add(idField);

        JButton okButton = new JButton("OK");
        okButton.setBackground(Color.RED);
        okButton.setForeground(Color.WHITE);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String inputId = idField.getText();
                    int employeeId = Integer.parseInt(inputId);

                    persons.removeIf(person -> person instanceof Employer && person.getId() == employeeId);

                    saveEmployers();

                    JOptionPane.showMessageDialog(HRDashboard.this, "Employee with ID " + employeeId + " deleted successfully.", "Delete Employee", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(HRDashboard.this, "Invalid Employee ID. Please enter a valid numeric ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        int result = JOptionPane.showOptionDialog(
                this,
                deletePanel,
                "Delete Employee",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                new Object[]{okButton},
                okButton);

        if (result != JOptionPane.OK_OPTION) {
            JOptionPane.showMessageDialog(HRDashboard.this, "Delete operation canceled.", "Cancel", JOptionPane.INFORMATION_MESSAGE);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(HRDashboard.this, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}





    protected int getNextId() {
        return idCounter + 1;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HRDashboard());
    }
}
