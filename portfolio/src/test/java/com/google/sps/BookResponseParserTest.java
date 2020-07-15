import com.google.sps.data.BookResponseParser;
import com.google.sps.data.Book;
import java.io.File;
import java.text.ParseException;
import java.lang.Exception;
import java.lang.IllegalAccessException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
 
/**
   This is a test class for BookResponseParserTest.java
    Tests the implementations ability to convert
    strings into a Books with the correct fields
*/
 
@RunWith(JUnit4.class)
public final class BookResponseParserTest {
  
  private static String readFile(String path){
    String fileString = "";
    File file = new File(path);
    try (Scanner scanner = new Scanner(file, "utf-8")){
      while(scanner.hasNextLine()){
          fileString += scanner.nextLine();
      }
    } catch (Exception ex) {
      Assert.fail("Could not open file. " + ex);
    }
    return fileString;
  }
  
  private static Book createBook(){
    //creates a book from these hard coded values
    String title = "A Court of Wings and Ruin";
    ArrayList<String> authors = new ArrayList<String>();
    authors.add("Sarah J. Maas");
    String isbn = "9781619634497";
    int pageCount = 432;
    ArrayList<String> categories = new ArrayList<String>();
    categories.add("Fiction");
    String publisher = "Bloomsbury Publishing USA";
    String publishedDate = "2017-05-02";
    String maturityRating = "NOT_MATURE";
    String thumbnail = "http://books.google.com/books/content?id=pLL-DAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api";
    String language = "en";
    String infoLink = "https://play.google.com/store/books/details?id=pLL-DAAAQBAJ&source=gbs_api";
    String description = "Looming war threatens all Feyre holds dear in the third volume of the #1 "
        +"New York Times bestselling A Court of Thorns and Roses series. Feyre has returned to the "
        +"Spring Court, determined to gather information on Tamlin\'s maneuverings and the invading "
        +"king threatening to bring Prythian to its knees. But to do so she must play a deadly game "
        +"of deceit-and one slip may spell doom not only for Feyre, but for her world as well. As war "
        +"bears down upon them all, Feyre must decide who to trust amongst the dazzling and lethal "
        +"High Lords-and hunt for allies in unexpected places. In this thrilling third book in the "
        +"#1 New York Times bestselling series from Sarah J. Maas, the earth will be painted red as "
        +"mighty armies grapple for power over the one thing that could destroy them all.";

    Book.Builder builder = Book.builder().title(title)
            .categories(categories).authors(authors).language(language)
            .description(description).infoLink(infoLink).pageCount(pageCount)
            .publishedDate(publishedDate).publisher(publisher)
            .maturityRating(maturityRating).thumbnail(thumbnail).isbn(isbn);
    Book expected = builder.build();
  }

  @Test
  public void compareWithHardCoded(){
    //parses the string in a json file and compares with a hard-coded book
    Book expected = createBook();

    String fileString = readFile("src/test/java/com/google/sps/BookParserTestFile.csv");

    try{
        if(!fileString.equals("")){
            Book actual = BookResponseParser.parseBook(fileString);
            Assert.assertEquals(expected.title(),actual.title());
            Assert.assertEquals(expected.genre(),actual.genre());
            Assert.assertEquals(expected.categories(),actual.categories());
            Assert.assertEquals(expected.authors(),actual.authors());
            Assert.assertEquals(expected.language(),actual.language());
            Assert.assertEquals(expected.description(),actual.description());
            Assert.assertEquals(expected.infoLink(),actual.infoLink());
            Assert.assertEquals(expected.pageCount(),actual.pageCount());
            Assert.assertEquals(expected.publishedDate(),actual.publishedDate());
            Assert.assertEquals(expected.publisher(),actual.publisher());
            Assert.assertEquals(expected.maturityRating(),actual.maturityRating());
            Assert.assertEquals(expected.thumbnail(),actual.thumbnail());
            Assert.assertEquals(expected.isbn(),actual.isbn());
        }
    }
    catch(Exception e){
        Assert.fail("Error comparing expected and actual books. " + ex);
    }


  }

  @Test(expected = JsonException.class)
  public void parseNull() throws Exception{
      //attempts to parse a null value
      BookResponseParser.parseBook(null);

  }

   @Test(expected = JsonException.class)
  public void parseEmpty() throws Exception{
      //attempts to parse a empty value
      BookResponseParser.parseBook("");

  }

  @Test(expected = JsonException.class)
  public void parseBadString() throws Exception{
    //attempts to parse a string that is unsiutable
    BookResponseParser.parseBook("puppy");
  }

  @Test(expected = JsonException.class)
  public void parseFullApiResponse() throws Exception{
    //attempts to parse the full response from the book api
    String fileString = readFile("src/test/java/com/google/sps/ResponseSample.csv");
    BookResponseParser.parseBook(fileString);
  }
}

