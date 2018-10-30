package com.mycompany.view;

import com.mycompany.controller.MainController;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainView {

    public String selection = "";

    public void display() {
        while (!selection.toUpperCase().equals("Q")) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Which operation do you want to do?");
            System.out.println("----------------------------------");
            System.out.println("C) Create a JSON");
            System.out.println("R) Create File from JSON");
            System.out.println("Q) Quit");
            selection = scan.nextLine();
            MainController mainController = new MainController();
            switch (selection.toUpperCase()) {
                case "C":
                    mainController.createJSON();
                    break;
                case "R": {
                    try {
                        mainController.createFileFromJSON();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                default:
            }
        }
    }
}
