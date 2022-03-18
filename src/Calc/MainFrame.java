package Calc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

class BadExpression extends IOException {
    private String msg;
    public String getMessage() {
        return msg;
    }
    public BadExpression(String msg) {
        this.msg = msg;
    }
}

public class MainFrame extends JFrame {
    private final JTextField resultField;
    private JPanel numbersPanel;

    public double calculateExpr(String expr) throws BadExpression {
        if(!expr.isBlank()) {
            int charPosition = -1;
            if (expr.contains("+"))
                charPosition = expr.indexOf("+");
            else if (expr.contains("*"))
                charPosition = expr.indexOf("*");
            else if (expr.contains("/"))
                charPosition = expr.indexOf("/");
            else if (expr.contains("-")) {
                if(expr.charAt(0) == '-') {
                    if(expr.substring(1).contains("-"))
                        charPosition = expr.substring(1).indexOf("-");
                }
                else
                    charPosition = expr.indexOf("-");
            }

            String first_number;
            String second_number;
            if(expr.contains("-") && !expr.contains("+") && !expr.contains("*") && !expr.contains("/")) {
                if(expr.charAt(0) == '-' && expr.substring(1).contains("-")) {
                    first_number = expr.substring(0, charPosition+1);
                    second_number = expr.substring(charPosition+2);
                }
                else {
                    first_number = expr.substring(0, charPosition);
                    second_number = expr.substring(charPosition+1);
                }
            }
            else {
                first_number = expr.substring(0, charPosition);
                second_number = expr.substring(charPosition + 1);
            }
            double result = -1;
            if (expr.contains("+"))
                result = Double.parseDouble(first_number) + Double.parseDouble(second_number);
            else if (expr.contains("*"))
                result = Double.parseDouble(first_number) * Double.parseDouble(second_number);
            else if (expr.contains("/"))
                result = Double.parseDouble(first_number) / Double.parseDouble(second_number);
            else if(expr.contains("-"))
                result = Double.parseDouble(first_number) - Double.parseDouble(second_number);
            return result;
        }
        throw new BadExpression("Invalid expression!");
    }
    public MainFrame() {

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        Image smallIcon = toolkit.createImage("calc.png");
        setIconImage(smallIcon);
        int width = screenSize.width;
        int height = screenSize.height;
        setLocationByPlatform(true);
        setSize(width/4, height/2);


        resultField = new JTextField();
        resultField.setEditable(false);
        numbersPanel = new JPanel();

        add(resultField, BorderLayout.BEFORE_FIRST_LINE);

        JButton ceButton = new JButton("CE");
        JButton cButton = new JButton("C");

        JButton zeroButton = new JButton("0");
        JButton oneButton = new JButton("1");
        JButton twoButton = new JButton("2");
        JButton threeButton = new JButton("3");
        JButton fourButton = new JButton("4");
        JButton fiveButton = new JButton("5");
        JButton sixButton = new JButton("6");
        JButton sevenButton = new JButton("7");
        JButton eightButton = new JButton("8");
        JButton nineButton = new JButton("9");

        JButton reciprocalButton = new JButton("1/x");
        JButton powButton = new JButton("x^2");
        JButton sqrtButton = new JButton("sqrt");

        JButton plusButton = new JButton("+");
        JButton minusButton = new JButton("-");
        JButton multiButton = new JButton("*");
        JButton divButton = new JButton("/");

        JButton plusMinusButton = new JButton("+-");
        JButton sepButton = new JButton(",");
        JButton eqButton = new JButton("=");

        ceButton.setBackground(Color.gray);
        cButton.setBackground(Color.gray);
        sevenButton.setBackground(Color.lightGray);
        oneButton.setBackground(Color.lightGray);
        twoButton.setBackground(Color.lightGray);
        threeButton.setBackground(Color.lightGray);
        fourButton.setBackground(Color.lightGray);
        fiveButton.setBackground(Color.lightGray);
        sixButton.setBackground(Color.lightGray);
        sevenButton.setBackground(Color.lightGray);
        eightButton.setBackground(Color.lightGray);
        nineButton.setBackground(Color.lightGray);

        reciprocalButton.setBackground(Color.gray);
        powButton.setBackground(Color.gray);
        sqrtButton.setBackground(Color.gray);
        plusButton.setBackground(Color.gray);
        minusButton.setBackground(Color.gray);
        multiButton.setBackground(Color.gray);
        divButton.setBackground(Color.gray);

        plusMinusButton.setBackground(Color.lightGray);
        sepButton.setBackground(Color.lightGray);
        zeroButton.setBackground(Color.lightGray);
        eqButton.setBackground(Color.blue);

        resultField.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                resultField.setText("");
            }
        });
        ceButton.addActionListener(e -> {
            String content = resultField.getText();
            if(!content.isBlank())
                resultField.setText(content.substring(0,content.length()-1));
        });
        cButton.addActionListener(event -> resultField.setText(""));
        zeroButton.addActionListener( event -> resultField.setText(resultField.getText()+"0"));
        oneButton.addActionListener(event -> resultField.setText(resultField.getText()+"1"));
        twoButton.addActionListener(event -> resultField.setText(resultField.getText()+"2"));
        threeButton.addActionListener(event -> resultField.setText(resultField.getText()+"3"));
        fourButton.addActionListener(event -> resultField.setText(resultField.getText()+"4"));
        fiveButton.addActionListener(event -> resultField.setText(resultField.getText()+"5"));
        sixButton.addActionListener(event -> resultField.setText(resultField.getText()+"6"));
        sevenButton.addActionListener(event -> resultField.setText(resultField.getText()+"7"));
        eightButton.addActionListener(event -> resultField.setText(resultField.getText()+"8"));
        nineButton.addActionListener(event -> resultField.setText(resultField.getText()+"9"));
        plusButton.addActionListener(event -> {
            String content = resultField.getText();
            if(!content.contains("+") && !content.contains("*") && !content.contains("/")) {
                if(content.contains("-") && content.substring(1).contains("-")) {
                    try {
                        double result = calculateExpr(content);
                        resultField.setText(result + "+");
                    } catch (BadExpression badExpression) {
                        System.out.println("Invalid expression");
                    }
                }
                else {
                    if(!content.isBlank())
                        resultField.setText(content + "+");
                }
            }
            else {
                try {
                    if(!content.isBlank()) {
                        double result = calculateExpr(content);
                        resultField.setText(result + "+");
                    }
                }
                catch(BadExpression badExpression){
                    System.out.println("Invalid expression");
                }
            }
        });
        minusButton.addActionListener( event -> {
            String content = resultField.getText();
            if (content.contains("-")) {
                if (content.indexOf("-") == 0) {
                    if (!content.contains("+") && !content.contains("*") && !content.contains("/") && !content.substring(1).contains("-")) {
                        resultField.setText(content + "-");
                    } else {
                        try {
                            if (content.charAt(content.length() - 1) == '-')
                                resultField.setText(content + "-");
                            else if (content.charAt(content.length() - 1) != '+' && content.charAt(content.length() - 1) != '*'
                                    && content.charAt(content.length() - 1) != '/') {
                                double result = calculateExpr(content);
                                resultField.setText(result + "-");
                            } else {
                                if (!content.isBlank())
                                    resultField.setText(content + "-");
                            }
                        } catch (BadExpression badExpression) {
                            System.out.println("Empty expression");
                        }
                    }
                } else {
                    if (content.contains("+") || content.contains("*") || content.contains("/") || content.contains("-")) {
                        int temp = -1;
                        if (content.substring(1).contains("+"))
                            temp = content.substring(1).indexOf("+") + 1;
                        else if (content.substring(1).contains("*"))
                            temp = content.substring(1).indexOf("*") + 1;
                        else if (content.substring(1).contains("/"))
                            temp = content.substring(1).indexOf("/") + 1;
                        else if (content.substring(1).contains("-")) {
                            if (content.charAt(content.length() - 1) == '-') {
                                resultField.setText(content + "-");
                                temp = 0;
                            } else {
                                temp = content.indexOf("-");
                            }
                        }
                        if (temp != 0) {
                            String firstNumber = content.substring(0, temp);
                            String secondNumber = content.substring(temp + 1);
                            double result = 0;
                            switch (content.charAt(temp)) {
                                case '+' -> result = Double.parseDouble(firstNumber) + Double.parseDouble(secondNumber);
                                case '*' -> result = Double.parseDouble(firstNumber) * Double.parseDouble(secondNumber);
                                case '/' -> result = Double.parseDouble(firstNumber) / Double.parseDouble(secondNumber);
                                case '-' -> result = Double.parseDouble(firstNumber) - Double.parseDouble(secondNumber);
                            }
                            resultField.setText(result + "-");
                        }
                    } else
                        resultField.setText(content + "-");
                }
            } else {
                if (!content.contains("+") && !content.contains("*") && !content.contains("/"))
                    resultField.setText(content + "-");
                else {
                    try {
                        if (content.charAt(content.length() - 1) != '+' && content.charAt(content.length() - 1) != '*'
                                && content.charAt(content.length() - 1) != '/') {
                            double result = calculateExpr(content);
                            resultField.setText(result + "-");
                        } else {
                            if (!content.isBlank())
                                resultField.setText(content + "-");
                        }
                    } catch (BadExpression badExpression) {
                        System.out.println("Empty expression");
                    }
                }
            }
        });
        multiButton.addActionListener( event -> {       // jeśli są dwie akcje do tego samego przycisku to one się kumulują
            String content = resultField.getText();
            if (!content.contains("+") && !content.contains("*") && !content.contains("/")) {
                if (content.contains("-")) {
                    if (content.substring(1).contains("-")) {
                        try {
                            double result = calculateExpr(content);
                            resultField.setText(result + "*");
                        } catch (BadExpression badExpression) {
                            System.out.println("Empty expression");
                        }
                    } else {
                        if (!content.isBlank())
                            resultField.setText(content + "*");
                    }
                } else {
                    if (!content.isBlank())
                        resultField.setText(content + "*");
                }
            } else {
                try {
                    double result = calculateExpr(content);
                    resultField.setText(result + "*");
                } catch (BadExpression badExpression) {
                    System.out.println("Empty expression");
                }
            }
        });
        divButton.addActionListener( event -> {
            String content = resultField.getText();
            if (!content.contains("+") && !content.contains("*") && !content.contains("/")) {
                if (content.contains("-")) {
                    if (content.substring(1).contains("-")) {
                        try {
                            double result = calculateExpr(content);
                            resultField.setText(result + "/");
                        } catch (BadExpression badExpression) {
                            System.out.println("Empty expression");
                        }
                    } else {
                        if (!content.isBlank())
                            resultField.setText(content + "/");
                    }
                } else {
                    if (!content.isBlank())
                        resultField.setText(content + "/");
                }
            } else {
                try {

                    double result = calculateExpr(resultField.getText());
                    resultField.setText(result + "/");
                } catch (BadExpression badExpression) {
                    System.out.println("Empty expression");
                }
            }
        });
        sepButton.addActionListener( event -> {
            String content = resultField.getText();
            if (!content.isBlank() || content.charAt(content.length()-1) != '.')
                resultField.setText(content + ".");
        });
        eqButton.addActionListener( event -> {
            String resultText = resultField.getText();
            double result = 0;
            int temp = -1;
            if (resultText.contains("*"))
                temp = resultText.indexOf("*");
            else if (resultText.contains("/"))
                temp = resultText.indexOf("/");
            else if (resultText.contains("+"))
                temp = resultText.indexOf("+");
            else if (resultText.contains("-")) {
                if (resultText.indexOf("-") == 0) {
                    if (resultText.substring(1).contains("-"))
                        temp = resultText.substring(1).indexOf("-") + 1;
                } else {
                    if (!resultText.isBlank())
                        temp = resultText.indexOf("-");
                }
            }
            String firstNumber = resultText.substring(0, temp);
            String secondNumber = resultText.substring(temp + 1);
            switch (resultText.charAt(temp)) {
                case '+' -> result = Double.parseDouble(firstNumber) + Double.parseDouble(secondNumber);
                case '-' -> result = Double.parseDouble(firstNumber) - Double.parseDouble(secondNumber);
                case '*' -> result = Double.parseDouble(firstNumber) * Double.parseDouble(secondNumber);
                case '/' -> result = Double.parseDouble(firstNumber) / Double.parseDouble(secondNumber);
            }
            resultField.setText(Double.toString(result));
        });
        reciprocalButton.addActionListener(e -> {
            String content = resultField.getText();
            if(!content.contains("+") && !content.contains("*") && !content.contains("/")) {
                if(content.contains("-")) {
                    if (content.charAt(0) == '-' && !content.substring(1).contains("-")) {
                        double result = Double.parseDouble(content);
                        resultField.setText(1/result + "");
                    }
                    else if (content.substring(1).contains("-")) {
                        try {
                            double result = calculateExpr(content);
                            resultField.setText(1/result + "");
                        } catch (BadExpression badExpression) {
                            System.out.println("Empty expression");
                        }
                    }
                }
                else {
                    double result = Double.parseDouble(resultField.getText());
                    resultField.setText(1/result + "");
                }
            }
            else {
                try {
                    if(content.contains("+") || content.contains("*") || content.contains("/")) {
                        double result = calculateExpr(resultField.getText());
                        resultField.setText(1 / result + "");
                    }
                }
                catch(BadExpression badExpression){
                    System.out.println("Empty expression");
                }
            }
        });
        powButton.addActionListener(event -> {
            String content = resultField.getText();
            if (!content.contains("+") && !content.contains("*") && !content.contains("/")) {
                if (content.contains("-")) {
                    if (content.charAt(0) == '-' && !content.substring(1).contains("-")) {
                        double result = Double.parseDouble(content);
                        resultField.setText(result * result + "");
                    } else if (content.substring(1).contains("-")) {
                        try {
                            double result = calculateExpr(content);
                            resultField.setText(result * result + "");
                        } catch (BadExpression badExpression) {
                            System.out.println("Empty expression");
                        }
                    }
                } else {
                    double result = Double.parseDouble(resultField.getText());
                    resultField.setText(result * result + "");
                }
            } else {
                try {
                    if (content.contains("+") || content.contains("*") || content.contains("/")) {
                        double result = calculateExpr(resultField.getText());
                        resultField.setText(result * result + "");
                    }
                } catch (BadExpression badExpression) {
                    System.out.println("Empty expression");
                }
            }
        });
        sqrtButton.addActionListener(event -> {
            String content = resultField.getText();
            if (!content.contains("+") && !content.contains("*") && !content.contains("/")) {
                if (content.contains("-")) {
                    if (content.charAt(0) == '-' && !content.substring(1).contains("-")) {
                        double result = Double.parseDouble(content);
                        if (result >= 0)
                            resultField.setText(Math.sqrt(result) + "");
                    } else if (content.substring(1).contains("-")) {
                        try {
                            double result = calculateExpr(content);
                            if (result >= 0)
                                resultField.setText(Math.sqrt(result) + "");
                        } catch (BadExpression badExpression) {
                            System.out.println("Invalid expression");
                        }
                    }
                } else {
                    double result = Double.parseDouble(resultField.getText());
                    if (result >= 0)
                        resultField.setText(Math.sqrt(result) + "");
                }
            } else {
                try {
                    if (content.contains("+") || content.contains("*") || content.contains("/")) {
                        double result = calculateExpr(resultField.getText());
                        if (result >= 0)
                            resultField.setText(Math.sqrt(result) + "");
                    }
                } catch (BadExpression badExpression) {
                    System.out.println("Empty expression");
                }
            }
        });
        plusMinusButton.addActionListener(event -> {
            String content = resultField.getText();
            if (!content.isBlank() && content.charAt(0) != '-')
                resultField.setText("-" + content);
            if (!content.isBlank() && content.charAt(0) == '-')
                resultField.setText(content.substring(1));
        });


        add(ceButton,BorderLayout.WEST);
        add(cButton,BorderLayout.EAST);
        numbersPanel.add(reciprocalButton);
        numbersPanel.add(powButton);
        numbersPanel.add(sqrtButton);
        numbersPanel.add(divButton);
        numbersPanel.add(sevenButton);
        numbersPanel.add(eightButton);
        numbersPanel.add(nineButton);
        numbersPanel.add(multiButton);
        numbersPanel.add(fourButton);
        numbersPanel.add(fiveButton);
        numbersPanel.add(sixButton);
        numbersPanel.add(minusButton);
        numbersPanel.add(oneButton);
        numbersPanel.add(twoButton);
        numbersPanel.add(threeButton);
        numbersPanel.add(plusButton);
        numbersPanel.add(plusMinusButton);
        numbersPanel.add(zeroButton);
        numbersPanel.add(sepButton);
        numbersPanel.add(eqButton);
        numbersPanel.setBounds(ceButton.getX(), ceButton.getY()+ceButton.getHeight()+5, resultField.getWidth()/4-5, ceButton.getHeight());
        numbersPanel.setLayout(new GridLayout(5,4));
        add(numbersPanel, BorderLayout.CENTER);
    }
}
