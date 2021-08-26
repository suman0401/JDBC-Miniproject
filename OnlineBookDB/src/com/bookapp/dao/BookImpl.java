package com.bookapp.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.bookapp.bean.Book;
import com.bookapp.exception.AuthorNotFoundException;
import com.bookapp.exception.BookNotFoundException;
import com.bookapp.exception.CategoryNotFoundException;

public class BookImpl implements BookInter {
	PreparedStatement st = null;
	Connection connection = ModalDAO.openConnection();

	Scanner sc = new Scanner(System.in);

	public void addBook(Book book) {
		String sql1 = "insert into book values(?,?,?,?,?)";

		try {
			Connection connection = ModalDAO.openConnection();
			st = connection.prepareStatement(sql1);
			st.setString(1, book.getTitle());
			st.setString(2, book.getAuthor());
			st.setString(3, book.getCategory());
			st.setInt(4, book.getBookId());
			st.setInt(5, book.getPrice());
			st.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (st != null)
					st.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	@Override
	public boolean deleteBook(int bookId) throws BookNotFoundException {
		boolean result = false;
		String delete = "delete from book where bookid=?";
	try {
			Connection connection = ModalDAO.openConnection();
			st = connection.prepareStatement(delete);

			st.setInt(1, bookId);
			result = true;
			st.execute();
			
			if (!result) {
				throw new BookNotFoundException("Book Not Found");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (st != null)
					st.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
	
		
		

		return result;
	}

	@Override
	public boolean updateBook(int bookId, int price) throws BookNotFoundException {
		String update = "update book set bookid=? where price=?";
		boolean result = false;
		try {
			Connection connection = ModalDAO.openConnection();
			st = connection.prepareStatement(update);
			st.setInt(1, bookId);
			st.setInt(2, price);
			result = true;
			st.execute();
			if (!result) {
				throw new BookNotFoundException("No book found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (st != null)
					st.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
		return result;
	}

	@Override
	public Book getBookById(int bookId) throws BookNotFoundException {
		Book book = new Book();
		String idsql = "select * from book where bookid=?";
		try {
			Connection connection = ModalDAO.openConnection();
			st = connection.prepareStatement(idsql);
			
			st.setInt(1, bookId);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				// Book book= new Book();
				book.setTitle(rs.getString(1));
				book.setAuthor(rs.getString(2));
				book.setCategory(rs.getString(3));
				book.setBookId(rs.getInt(4));
				book.setPrice(rs.getInt(5));

				// booksById.add(book);
				
			}
			if(rs.next()== false)
			{
				throw new BookNotFoundException("Bookid not found");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (st != null)
					st.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return book;
	}

	@Override
	public List<Book> getallBook() {
		List<Book> bookAll = new ArrayList<>();
		String allsql = "select * from book";
		try {
			Connection connection = ModalDAO.openConnection();
			st = connection.prepareStatement(allsql);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Book book = new Book();
				book.setTitle(rs.getString(1));
				book.setAuthor(rs.getString(2));
				book.setCategory(rs.getString(3));
				book.setBookId(rs.getInt(4));
				book.setPrice(rs.getInt(5));

				bookAll.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (st != null)
					st.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return bookAll;
	}

	@Override
	public List<Book> getBookByAuthor(String author) throws AuthorNotFoundException {
		List<Book> booksByAuthor = new ArrayList<>();

		String authorsql = "select * from book where author=?";
		try {
			Connection connection = ModalDAO.openConnection();
			st = connection.prepareStatement(authorsql);

			st.setString(1, author);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Book book = new Book();
				book.setTitle(rs.getString(1));
				book.setAuthor(rs.getString(2));
				book.setCategory(rs.getString(3));
				book.setBookId(rs.getInt(4));
				book.setPrice(rs.getInt(5));

				booksByAuthor.add(book);

			}
			if (booksByAuthor.isEmpty()) {
				throw new AuthorNotFoundException("Invalid Author name");
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {

			try {
				if (st != null)
					st.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return booksByAuthor;
	}

	@Override
	public List<Book> getBookByCategory(String category) throws CategoryNotFoundException {
		List<Book> booksByCategory = new ArrayList<>();
		String catsql = "select * from book where category=?";
		try {
			Connection connection = ModalDAO.openConnection();
			st = connection.prepareStatement(catsql);

			st.setString(1, category);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Book book = new Book();
				book.setTitle(rs.getString(1));
				book.setAuthor(rs.getString(2));
				book.setCategory(rs.getString(3));
				book.setBookId(rs.getInt(4));
				book.setPrice(rs.getInt(5));

				booksByCategory.add(book);
			}
			
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (st != null)
					st.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		if(booksByCategory.isEmpty()) {
			throw new CategoryNotFoundException("Ivalid category ");
		}
		
		return booksByCategory;
	}

}
