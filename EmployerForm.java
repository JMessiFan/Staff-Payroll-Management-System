import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class EmployerForm extends JFrame {

    private JTextField nameField;
    private JTextField emailField;
    private JTextField departmentField;
    private JTextField jobTitleField;
    private JTextField salaryField;
    private JTextField dateOfHireField;
    private JTextField ageField;
    private JTextField cityField;

    private HRDashboard hrDashboard;

    public EmployerForm(HRDashboard hrDashboard) {
        this.hrDashboard = hrDashboard;

        setTitle("Add Employee");
        setSize(350, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 253, 208)); // Set background color to beige
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(10, 20, 80, 25);
        panel.add(nameLabel);

        nameField = new JTextField(20);
        nameField.setBounds(150, 20, 165, 25);
        panel.add(nameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 50, 80, 25);
        panel.add(emailLabel);

        emailField = new JTextField(20);
        emailField.setBounds(150, 50, 165, 25);
        panel.add(emailField);

        JLabel departmentLabel = new JLabel("Department:");
        departmentLabel.setBounds(10, 80, 80, 25);
        panel.add(departmentLabel);

        departmentField = new JTextField(20);
        departmentField.setBounds(150, 80, 165, 25);
        panel.add(departmentField);

        JLabel jobTitleLabel = new JLabel("Job Title:");
        jobTitleLabel.setBounds(10, 110, 80, 25);
        panel.add(jobTitleLabel);

        jobTitleField = new JTextField(20);
        jobTitleField.setBounds(150, 110, 165, 25);
        panel.add(jobTitleField);

        JLabel salaryLabel = new JLabel("Salary:");
        salaryLabel.setBounds(10, 140, 80, 25);
        panel.add(salaryLabel);

        salaryField = new JTextField(20);
        salaryField.setBounds(150, 140, 165, 25);
        panel.add(salaryField);

        // Added fields
        JLabel dateOfHireLabel = new JLabel("Date of Hire:");
        dateOfHireLabel.setBounds(10, 170, 120, 25);
        panel.add(dateOfHireLabel);

        dateOfHireField = new JTextField(20);
        dateOfHireField.setBounds(150, 170, 165, 25);
        panel.add(dateOfHireField);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(10, 200, 80, 25);
        panel.add(ageLabel);

        ageField = new JTextField(20);
        ageField.setBounds(150, 200, 165, 25);
        panel.add(ageField);

        JLabel cityLabel = new JLabel("City:");
        cityLabel.setBounds(10, 230, 80, 25);
        panel.add(cityLabel);

        cityField = new JTextField(20);
        cityField.setBounds(150, 230, 165, 25);
        panel.add(cityField);

        JButton addButton = new JButton("Add");
        addButton.setBounds(10, 260, 80, 25);
        addButton.setBackground(new Color(0, 100, 0)); // Set button background color to dark green
        addButton.setForeground(Color.WHITE); // Set font color to white
        panel.add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEmployer();
            }
        });
    }

    private void addEmployer() {
        String name = nameField.getText();
        String email = emailField.getText();
        String department = departmentField.getText();
        String jobTitle = jobTitleField.getText();
        String salaryStr = salaryField.getText();

        // Added fields
        String dateOfHire = dateOfHireField.getText();
        String ageStr = ageField.getText();
        String city = cityField.getText();

        // Validate input (you may want to add more validation)
        if (name.isEmpty() || email.isEmpty() || department.isEmpty() || jobTitle.isEmpty() || salaryStr.isEmpty() ||
            dateOfHire.isEmpty() || ageStr.isEmpty() || city.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled out");
            return;
        }

        // Convert salary and age to appropriate types
        double salary = Double.parseDouble(salaryStr);
        int age = Integer.parseInt(ageStr);

        // Create Employer object and add to HRDashboard
        int id = hrDashboard.getNextId();
        Employer employer = new Employer(id, name, email, department, jobTitle, salary, dateOfHire, age, city);
        hrDashboard.addEmployer(employer);

        // Close the form
        dispose();
    }

}

