package com.mycompany.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MainController {
    
    public static JSONObject libraryObject = new JSONObject();

    public void createJSON() {       

        Scanner input = new Scanner(System.in);
        System.out.println("Enter name of the library ");
        String libraryName = input.nextLine();       

        //This is the main name of the JSON
        libraryObject.put("Library Name", libraryName);

        JSONArray books = new JSONArray();

        //This will create a list of books inside our JSON
        while (true) {

            System.out.println("Enter the book information");
            System.out.println("Enter the title of the book");
            System.out.println("or press Q to exit");
            String bookTitle = input.nextLine();

            if (bookTitle.toUpperCase().equals("Q")) {
                break;
            }

            System.out.println("Enter the total Chapters of the book:");
            String totalChapters = input.nextLine();

            System.out.println("Enter Author's Name: ");
            String authorName = input.nextLine();

            System.out.println("Enter the year of the book:");
            String year = input.nextLine();

            JSONObject bookObject = new JSONObject();
            bookObject.put("bookTitle", bookTitle);
            bookObject.put("totalChapters", totalChapters);
            bookObject.put("authorName", authorName);
            bookObject.put("year", year);

            books.add(bookObject);

        }

        //this will add the list to the JSON object 'Library'
        libraryObject.put("Books", books);
        //This is just to display the JSON information
        System.out.println("JSON file: " + libraryObject.toJSONString());
        
    }

    public void createFileFromJSON() throws FileNotFoundException { 
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a name for your file without the extension");
        String fileName = input.nextLine(); 
        File file = new File(fileName + ".json");

        try (PrintWriter writeFile = new PrintWriter(file)) {
            //This will write the information into the file
            writeFile.print(libraryObject.toJSONString());
            System.out.println("File created");
        } catch (FileNotFoundException ex) {
            //if there is an error it will be display
            System.out.println(ex.toString());
        }       

        try {
            //This block will display the information of the file
            input = new Scanner(file);
            StringBuilder jsonIn = new StringBuilder();
            while (input.hasNextLine()) {
                jsonIn.append(input.nextLine());
            }
            System.out.println(jsonIn.toString());

            JSONParser parser = new JSONParser();
            JSONObject objLibrary = (JSONObject) parser.parse(jsonIn.toString());
            System.out.println("File created... ");
            System.out.println("Library name: " + objLibrary.get("Library Name").toString());
            //get all the books from the list of the file to display it
            JSONArray booksIn = (JSONArray) objLibrary.get("Books");

            //display each book
            for (int i = 0; i < booksIn.size(); i++) {
                JSONObject bookIn = (JSONObject) booksIn.get(i);
                String totalChaptersIn = (String) bookIn.get("totalChapters");
                String yearIn = (String) bookIn.get("year");
                String authorNameIn = (String) bookIn.get("authorName");
                String bookTitleIn = (String) bookIn.get("bookTitle");

                System.out.println("Book Information:" + "\n\t" + bookTitleIn + "\n\t" + totalChaptersIn + "\n\t" + authorNameIn + "\n\t" + yearIn);
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        } catch (ParseException ex) {
            System.out.println(ex.toString());
        }
    }
}
