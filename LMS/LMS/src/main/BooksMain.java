package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import dao.BooksDao;
import dto.Books;
import util.DbConn;



public class BooksMain {
	public static void main(String[] args) {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int choice=0;
		BooksDao bookDao = new BooksDao();
		
		do {
			try {
				System.out.println("Select what you want to run");
				System.out.println("1. Insert Book");
				System.out.println("2. Get All Book Details");
				System.out.println("3. Update Book");
				System.out.println("4. Delete Book");
				System.out.println("5. Exit");
				System.out.println("Enter your choice");
				choice = Integer.parseInt(br.readLine());
				
				switch(choice) {
				case 1:
					int id=0;
					String name = null;
					String author = null;
					System.out.println("\nEnter Accession Number:");
					try {
						id = Integer.parseInt(br.readLine());
					} catch (NumberFormatException e) {
						System.out.println("Enter a number\n");
						
					}
					
					System.out.println("Enter name: ");
					name = br.readLine();
					System.out.println("Enter Author name: ");
					author = br.readLine();
					Books book = new Books(id, name, author, null);
					boolean status = bookDao.insertBook(book);
					if(status) {
						System.out.println("\nBook details Inserted\n");
					}
					else {
						System.out.println("\nBook details NOT inserted\n");
					}
					
					break;
					
					
				case 2:
					ArrayList<Books> list = bookDao.getBooks();
					System.out.println("Book Details\n_______________________________________\n");
					for(Books b:list) {
						System.out.println("Acession Number: "+ b.getId());
						System.out.println("Name: "+b.getName());
						System.out.println("Author: "+b.getAuthor());
						System.out.println("Status: "+ b.getAvailability());
						System.out.println("_______________________________\n");
					}
					break;
					
					
				case 3://update
					System.out.println("Enter Accession Number that you want to change");
					id=0;
					name=null;
					author = null;
					try {
						 id = Integer.parseInt(br.readLine());
					} catch (NumberFormatException e) {
						System.out.println("Enter a number");
					}
					System.out.println("Enter the Name you want to change: ");
					name = br.readLine();
					System.out.println("Enter the Author you want to change: ");
					author = br.readLine();
					status = false;
					status = bookDao.updateBooks(id, name, author);
					if(status) {
						System.out.println("\nBook details have been Updated\n");
					}
					else {
						System.out.println("\nBook details have NOT been Updated\n");
					}
					break;
					
					
				case 4://delete
					System.out.println("Enter Accession Number that you want to delete");
					try {
						id = Integer.parseInt(br.readLine());
						boolean status1 = bookDao.deleteBooks(id);
						if(status1) {
							System.out.println("\nBook details have been deleted\n");
						}
						else {
							System.out.println("\nBook details have NOT been deleted\n");
						}
					} catch (NumberFormatException e) {
						System.out.println("Enter a number");
					}
					break;
					
					
				case 5://exit
					System.out.println("\nGoodbye!");
					DbConn.closeConnection();
					System.exit(1);
					break;
					
					
				default:
					System.out.println("\nPlease Enter a Proper Choice\n");
				}

			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} while (choice!=5);
	}
}
