// Calculator.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {
    
    private JTextField display;
    private JButton[] numberButtons;
    private JButton addButton, subButton, mulButton, divButton;
    private JButton decButton, equButton, delButton, clrButton, negButton;
    
    private double num1 = 0, num2 = 0, result = 0;
    private char operator;
    private boolean newCalculation = true;
    
    public Calculator() {
        initializeGUI();
    }
    
    private void initializeGUI() {
        setTitle("Java Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 450);
        setResizable(false);
        setLocationRelativeTo(null);
        
        initializeComponents();
        setupLayout();
        addActionListeners();
    }
    
    private void initializeComponents() {
        display = new JTextField("0");
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setBackground(Color.WHITE);
        
        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(new Font("Arial", Font.BOLD, 18));
            numberButtons[i].setBackground(Color.WHITE);
            numberButtons[i].setFocusable(false);
        }
        
        // Use standard ASCII characters to avoid encoding issues
        addButton = createFunctionButton("+");
        subButton = createFunctionButton("-");
        mulButton = createFunctionButton("*");
        divButton = createFunctionButton("/");
        decButton = createFunctionButton(".");
        equButton = createFunctionButton("=");
        delButton = createFunctionButton("Del");
        clrButton = createFunctionButton("C");
        negButton = createFunctionButton("±");
        
        equButton.setBackground(new Color(255, 165, 0));
        equButton.setForeground(Color.WHITE);
        clrButton.setBackground(new Color(220, 20, 60));
        clrButton.setForeground(Color.WHITE);
    }
    
    private JButton createFunctionButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(new Color(240, 240, 240));
        button.setFocusable(false);
        return button;
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        displayPanel.add(display, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        
        // Row 1
        buttonPanel.add(clrButton);
        buttonPanel.add(delButton);
        buttonPanel.add(negButton);
        buttonPanel.add(divButton);
        
        // Row 2
        buttonPanel.add(numberButtons[7]);
        buttonPanel.add(numberButtons[8]);
        buttonPanel.add(numberButtons[9]);
        buttonPanel.add(mulButton);
        
        // Row 3
        buttonPanel.add(numberButtons[4]);
        buttonPanel.add(numberButtons[5]);
        buttonPanel.add(numberButtons[6]);
        buttonPanel.add(subButton);
        
        // Row 4
        buttonPanel.add(numberButtons[1]);
        buttonPanel.add(numberButtons[2]);
        buttonPanel.add(numberButtons[3]);
        buttonPanel.add(addButton);
        
        // Row 5
        buttonPanel.add(numberButtons[0]);
        buttonPanel.add(decButton);
        buttonPanel.add(equButton);
        // Empty space for the 4th column
        buttonPanel.add(new JLabel());
        
        add(displayPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }
    
    private void addActionListeners() {
        for (int i = 0; i < 10; i++) {
            numberButtons[i].addActionListener(this);
        }
        
        JButton[] functionButtons = {addButton, subButton, mulButton, divButton, 
                                   decButton, equButton, delButton, clrButton, negButton};
        for (JButton button : functionButtons) {
            button.addActionListener(this);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String currentText = display.getText();
        
        // Number buttons
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                if (newCalculation || currentText.equals("0")) {
                    display.setText(String.valueOf(i));
                    newCalculation = false;
                } else {
                    display.setText(currentText + i);
                }
                return;
            }
        }
        
        // Decimal button
        if (e.getSource() == decButton) {
            if (newCalculation) {
                display.setText("0.");
                newCalculation = false;
            } else if (!currentText.contains(".")) {
                display.setText(currentText + ".");
            }
            return;
        }
        
        // Operation buttons
        if (e.getSource() == addButton) { 
            performOperation('+'); 
            return; 
        }
        if (e.getSource() == subButton) { 
            performOperation('-'); 
            return; 
        }
        if (e.getSource() == mulButton) { 
            performOperation('*'); 
            return; 
        }
        if (e.getSource() == divButton) { 
            performOperation('/'); 
            return; 
        }
        
        // Equals button
        if (e.getSource() == equButton) { 
            calculateResult(); 
            return; 
        }
        
        // Clear button
        if (e.getSource() == clrButton) {
            display.setText("0");
            num1 = num2 = result = 0;
            operator = '\0';
            newCalculation = true;
            return;
        }
        
        // Delete button
        if (e.getSource() == delButton) {
            if (currentText.length() > 1 && !currentText.equals("0")) {
                display.setText(currentText.substring(0, currentText.length() - 1));
            } else {
                display.setText("0");
                newCalculation = true;
            }
            return;
        }
        
        // Negative button
        if (e.getSource() == negButton) {
            if (!currentText.equals("0")) {
                if (currentText.charAt(0) == '-') {
                    display.setText(currentText.substring(1));
                } else {
                    display.setText("-" + currentText);
                }
            }
            return;
        }
    }
    
    private void performOperation(char op) {
        try {
            num1 = Double.parseDouble(display.getText());
            operator = op;
            newCalculation = true;
        } catch (NumberFormatException e) {
            display.setText("Error");
            newCalculation = true;
        }
    }
    
    private void calculateResult() {
        try {
            num2 = Double.parseDouble(display.getText());
            
            switch (operator) {
                case '+': 
                    result = num1 + num2; 
                    break;
                case '-': 
                    result = num1 - num2; 
                    break;
                case '*': 
                    result = num1 * num2; 
                    break;
                case '/': 
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else { 
                        display.setText("Error: Div by 0"); 
                        return; 
                    }
                    break;
                default: 
                    return;
            }
            
            // Format the result (remove .0 for whole numbers)
            if (result == (long) result) {
                display.setText(String.valueOf((long) result));
            } else {
                display.setText(String.valueOf(result));
            }
            newCalculation = true;
            
        } catch (NumberFormatException e) {
            display.setText("Error");
            newCalculation = true;
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Calculator().setVisible(true);
        });
    }
}
