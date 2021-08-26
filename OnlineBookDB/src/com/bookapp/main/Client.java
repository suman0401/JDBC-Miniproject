package com.bookapp.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.bookapp.bean.Book;
import com.bookapp.dao.BookImpl;
import com.bookapp.dao.BookInter;
import com.bookapp.exception.AuthorNotFoundException;
import com.bookapp.exception.BookNotFoundException;
import com.bookapp.exception.CategoryNotFoundException;

public class Client {

	public static void main(String[] args)
			throws AuthorNotFoundException, CategoryNotFoundException, BookNotFoundException {
		BookInter bookref = new BookImpl();
		List<Book> book1 = new ArrayList<>();
		
		
		System.out.println(
				"1.addbook 2.delete book 3.update book 4.bookById \n 5.BookByAuthor 6. BookByCategory 7.GetAllBook 8.exit");
		System.out.println("Enter your choice");
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		
		Book book = new Book();
		switch (choice) {
		case 1:
			System.out.println("Enter the book details");
			String title = sc.next();
			String author = sc.next();
			String category = sc.next();
			
	        int id=sc.nextInt();
			int price = sc.nextInt(); 
			
			book.setTitle(title);
			book.setAuthor(author);
			book.setCategory(category);
			book.setBookId(id);
			book.setPrice(price);
			bookref.addBook(book);
			break;
		case 2:
			System.out.println("Enter id");
			int bookId = sc.nextInt();
            try {
            	
				if(bookref.deleteBook(bookId)) {
					System.out.println("book deleted");
				}
			} catch (BookNotFoundException e2) {
				System.out.println(e2.getMessage());
			}

			break;
		case 3:
			System.out.println("Enter id to set");
			int Id = sc.nextInt();
			System.out.println("Enter price");
			int prices = sc.nextInt();
			try {
				if((bookref.updateBook(Id, prices))){
					System.out.println("Book updated");
				}
			} catch (BookNotFoundException e1) {
				System.out.println(e1.getMessage());
			}
			break;
		case 4:
			System.out.println("Enter bookid");
			int bookid = sc.nextInt();
			try {
				
				book = bookref.getBookById(bookid);
				System.out.println(bookref.getBookById(bookid));
				bookref.addBook(book);

			} catch (BookNotFoundException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 5:
			try {
				System.out.println("Enter author name");
				String newauthor = sc.next();
//				for(Book b: book1) {
//					System.out.println(b.toString());
//				}
				System.out.println(bookref.getBookByAuthor(newauthor));

			} catch (AuthorNotFoundException e) {
				System.out.println(e.getMessage());
			}

			break;
		case 6:
			System.out.println("Enter category");
			
			String newcategory = sc.next();
			try {
				
				

				System.out.println(bookref.getBookByCategory(newcategory));
			} catch (CategoryNotFoundException e) {
				System.out.println(e.getMessage());
			}
			break;

		case 7:
			try {
				book1 = bookref.getallBook();
				System.out.println(bookref.getallBook());
			} catch (BookNotFoundException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 8:
			System.out.println("Exiting...");
			System.exit(0);

		default:
			System.out.println("Invalid input");

		}

	}

}
