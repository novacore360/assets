package calculator;
//KUNG MAG COPY MO PLEASE CHANGE THE COLOR OR LAYOUT, SIZES, FONTS OR DESIGNS
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
    private boolean calculationPerformed = false; // NEW: Track if calculation was done
    
    public Calculator() {
        initializeGUI();
    }
    
    private void initializeGUI() {
        // Set up the frame something
        setTitle("Java Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 450);
        setResizable(false);
        setLocationRelativeTo(null);
        
        // Initialize components 
        initializeComponents();
        setupLayout();
        addActionListeners();
    }
    
    private void initializeComponents() {
        // Display field
        display = new JTextField("0");
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setBackground(Color.WHITE);
        display.setMargin(new Insets(10, 10, 10, 10));
        
        // Number buttons (0-9)
        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(new Font("Arial", Font.BOLD, 16));
            numberButtons[i].setBackground(Color.WHITE);
            numberButtons[i].setFocusable(false);
        }


        
        // Function buttons para sa operations na bhie
        addButton = createFunctionButton("+");
        subButton = createFunctionButton("-");
        mulButton = createFunctionButton("×");
        divButton = createFunctionButton("÷");
        decButton = createFunctionButton(".");
        equButton = createFunctionButton("=");
        delButton = createFunctionButton("Dlt");
        clrButton = createFunctionButton("Clr");
        negButton = createFunctionButton("±");
      
        // color color ragud pud
setButtonColor(equButton, new Color(11, 218, 81), Color.WHITE);
setButtonColor(clrButton, new Color(220, 20, 60), Color.WHITE);
setButtonColor(addButton, new Color(255, 255, 0), Color.BLACK);
setButtonColor(subButton, new Color(255, 165, 0), Color.BLACK);
setButtonColor(mulButton, new Color(135, 206, 235), Color.BLACK);
setButtonColor(divButton, new Color(173, 216, 230), Color.BLACK);
setButtonColor(decButton, new Color(200, 200, 200), Color.BLACK);
setButtonColor(negButton, new Color(255, 182, 193), Color.BLACK);
setButtonColor(delButton, new Color(255, 99, 71), Color.WHITE);


}
    private void setButtonColor(JButton button, Color bg, Color fg) {
    button.setBackground(bg);
    button.setForeground(fg);
    button.setOpaque(true);
    button.setBorderPainted(false);
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
        
        // Display panel
        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        displayPanel.add(display, BorderLayout.CENTER);
        
        // Button panel, E GridLayout jud na nato besh lisod pud ug flexbox
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        buttonPanel.setBackground(new Color(240, 240, 240));
        
        // Add buttons in calculator layout, matic nana mo grid ang layout besh kita man kaha kas babaw sa setLayout?
        // Mao ni siya sa first ROW
        buttonPanel.add(clrButton);
        buttonPanel.add(delButton);
        buttonPanel.add(negButton);
        buttonPanel.add(divButton);
        //SECOND ROW SAD NI
        buttonPanel.add(numberButtons[7]);
        buttonPanel.add(numberButtons[8]);
        buttonPanel.add(numberButtons[9]);
        buttonPanel.add(mulButton);
        //THIRD RAW SA GRID LAYOUT
        buttonPanel.add(numberButtons[4]);
        buttonPanel.add(numberButtons[5]);
        buttonPanel.add(numberButtons[6]);
        buttonPanel.add(subButton);
        //FOURTH BESH
        buttonPanel.add(numberButtons[1]);
        buttonPanel.add(numberButtons[2]);
        buttonPanel.add(numberButtons[3]);
        buttonPanel.add(addButton);
        //OF COURSE LAST
        buttonPanel.add(numberButtons[0]);
        buttonPanel.add(decButton);
        buttonPanel.add(equButton);

        // Add empty panel for grid alignment
        buttonPanel.add(new JPanel());
        
        add(displayPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }
    
    private void addActionListeners() {
        for (int i = 0; i < 10; i++) {
            numberButtons[i].addActionListener(this);
        }
        
        addButton.addActionListener(this);
        subButton.addActionListener(this);
        mulButton.addActionListener(this);
        divButton.addActionListener(this);
        decButton.addActionListener(this);
        equButton.addActionListener(this);
        delButton.addActionListener(this);
        clrButton.addActionListener(this);
        negButton.addActionListener(this);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String currentText = display.getText();
        
        // Number buttons
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                if (newCalculation || currentText.equals("0") || calculationPerformed) {
                    display.setText(String.valueOf(i));
                    newCalculation = false;
                    calculationPerformed = false; // Reset when new number is entered
                } else {
                    display.setText(currentText + i);
                }
                return;
            }
        }
        
        // Decimal button
        if (e.getSource() == decButton) {
            if (newCalculation || calculationPerformed) {
                display.setText("0.");
                newCalculation = false;
                calculationPerformed = false; // Reset when decimal is entered
            } else if (!currentText.contains(".")) {
                display.setText(currentText + ".");
            }
            return;
        }
        
        // Arithmetic operations
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
            num1 = 0;
            num2 = 0;
            result = 0;
            newCalculation = true;
            calculationPerformed = false;
            return;
        }
        
        // Delete button
        if (e.getSource() == delButton) {
            if (currentText.length() > 1) {
                display.setText(currentText.substring(0, currentText.length() - 1));
                calculationPerformed = false; // Reset when deleting
            } else {
                display.setText("0");
                newCalculation = true;
            }
            return;
        
        }
        
        // Negate button
        if (e.getSource() == negButton) {
            if (!currentText.equals("0")) {
                if (currentText.charAt(0) == '-') {
                    display.setText(currentText.substring(1));
                } else {
                    display.setText("-" + currentText);
                }
            }
            calculationPerformed = false; // Reset when negating
            return;
        }
    }
    //BY JAMES BRYLLE GARAY
    private void performOperation(char op) {
        try {
            num1 = Double.parseDouble(display.getText());
            operator = op;
            newCalculation = true;
            calculationPerformed = false; // Reset when new operation is started
        } catch (NumberFormatException e) {
            display.setText("Error");
        }
    }
    
    private void calculateResult() {
        try {
            // If calculation was already performed, just keep showing the same result
            if (calculationPerformed) {
                return; // Do nothing, just keep showing the same result
            }
            
            // Perform calculation only if it hasn't been done yet
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
            
            // Format result
            if (result == (long) result) {
                display.setText(String.valueOf((long) result));
            } else {
                display.setText(String.valueOf(result));
            }
            
            newCalculation = true;
            calculationPerformed = true; // Mark that calculation was performed
            
        } catch (NumberFormatException e) {
            display.setText("Error");
        }
    }
    
    public static void main(String[] args) {
        // Fixed the UIManager line - removed the problematic method call
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            new Calculator().setVisible(true);
        });
    }
}
