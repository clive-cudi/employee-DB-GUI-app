package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AppFrame extends JFrame implements ActionListener {
    // initialize global inputs

    CustomInput inputSalary;
    CustomInput inputName;
    JCheckBox isActiveCheckbox;
    JButton buttonAddEmployee;
    JButton buttonRemoveEmployee;
    CustomInput inputRemoveEmployeeID;
    JTextField inputStatusDisplay;
    boolean markAsActive;
    public AppFrame(Employee[] users) {
        this.setTitle("Employees in Database");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setSize(600, 500);
        this.setResizable(false);

        List<Employee> employeeList = new ArrayList<Employee>(List.of(users));

        EmployeeModel model = new EmployeeModel(employeeList);

        // ****************UI***************** //
        GridLayout formLayout = new GridLayout(9, 2);

        JTable table = new JTable(model);

        JPanel formPanel = new JPanel();
        inputName = new CustomInput();
        inputSalary = new CustomInput();
        isActiveCheckbox = new JCheckBox();
        buttonAddEmployee = new JButton("Add Employee");
        JPanel checkboxPanel = new JPanel();
        GridLayout checkboxPanelGrid = new GridLayout(1, 2);
        buttonRemoveEmployee = new JButton("Remove Employee");
        inputRemoveEmployeeID = new CustomInput();
        inputStatusDisplay = new JTextField();

        formPanel.setLayout(formLayout);
        checkboxPanel.setLayout(checkboxPanelGrid);


        JLabel addEmployeeTitle = new JLabel("Remove Employee By ID");
        addEmployeeTitle.setFont(new Font(getName(), Font.BOLD, 16));
        formPanel.add(addEmployeeTitle);
        formPanel.add(new JPanel());
        formPanel.add(new JLabel("Enter Employee Name: "));
        formPanel.add(inputName);
        formPanel.add(new JLabel("Enter Employee Salary: "));
        formPanel.add(inputSalary);
        checkboxPanel.add(new JLabel("Is Active"));
        checkboxPanel.add(isActiveCheckbox);
        formPanel.add(checkboxPanel);
        formPanel.add(new Panel());
        formPanel.add(buttonAddEmployee);
        formPanel.add(new Panel());

        // removing Employee
        JLabel removeEmployeeTitle = new JLabel("Remove Employee By ID");
        removeEmployeeTitle.setFont(new Font(getName(), Font.BOLD, 16));
        formPanel.add(removeEmployeeTitle);
        formPanel.add(new Panel());
        formPanel.add(new JLabel("Enter Employee ID:"));
        formPanel.add(inputRemoveEmployeeID);
        formPanel.add(buttonRemoveEmployee);

        // app status display
        formPanel.add(new JPanel());
        inputStatusDisplay.setEditable(false);
        inputStatusDisplay.setSize(100, 200);
        formPanel.add(inputStatusDisplay);

        // action listeners
        buttonAddEmployee.addActionListener((e) -> {
            handleAddSubmit();
        });

        this.add(formPanel);
        this.add(new JScrollPane(table));
        this.pack();
        this.setVisible(true);
    }

    public void handleAddSubmit() {
        try {
            // check if respective inputs are valid
            if (inputName.getText().length() < 1 || isInteger(inputName.getText())) {
                throw new Exception("Enter a valid name");
            }
            if (!isDouble(inputSalary.getText())) {
                throw new Exception("Salary should be an integer or double");
            }
            // checks passed...write to DB
            System.out.println("Checks passed");
            raiseGreenMessageStatus("");
        } catch (Exception e) {
            raiseErrorMessageStatus(e.getMessage());
            System.out.println(e);
        }
    }

    public void raiseGreenMessageStatus(String message) {
        inputStatusDisplay.setText(message);
        inputStatusDisplay.setForeground(Color.green);
    }

    public void raiseErrorMessageStatus(String message) {
        inputStatusDisplay.setText(message);
        inputStatusDisplay.setForeground(Color.red);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == isActiveCheckbox) {
            markAsActive = isActiveCheckbox.isSelected();
        }
    }

    public boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isDouble(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
