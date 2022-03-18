package Calc;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater( () -> {
            JFrame frame = new MainFrame();
            frame.setTitle("Kalkulator");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}


/**
 * 1) Dodać ewentualnie klasy adaptacyjne
 * */