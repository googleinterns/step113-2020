package com.google.sps.servlets;

import com.google.gson.Gson;
import com.google.sps.data.Book;
import com.google.sps.data.BookReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/singleBookById")
public class SingleBookServlet extends HttpServlet {
  @Override
  public void init() throws ServletException {
    BookReader reader = new BookReader(System.getProperty("user.home")
        + "/step-2020-wipeout-jr/portfolio/src/main/webapp/WEB-INF/20_books.csv");
    bookList = reader.makeBookList();
  }
  private static String toJSON(Book book) {
    Gson gson = new Gson();
    return gson.toJson(book);
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    int bookId = Integer.parseInt(request.getParameter("id"));
    Book book =
        bookList.get(bookdId); // TODO: change BookReader to returna hashmap instead of an array

    String jsonBook = toJSON(book);
    response.setContentType("application/json;");
    response.getWriter().println(jsonBook);
  }
}