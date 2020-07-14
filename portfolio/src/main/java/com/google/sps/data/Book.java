package com.google.sps.data;
//BOOK VERSION 2

import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Set;

@AutoValue
public abstract class Book {
  public static Builder builder() {
    return new AutoValue_Book.Builder();
  }

  public abstract Builder toBuilder();
  public abstract String title();
  public abstract Set<String> genre();
  public abstract ImmutableList<String> reviews();
  public abstract ArrayList<String> categories();
  public abstract ArrayList<String> authors();
  public abstract String language();
  public abstract String description();
  public abstract String infoLink();
  public abstract String pageCount();
  public abstract String publishedDate();
  public abstract String publisher();
  public abstract String maturityRating();
  public abstract String thumbnail();
  public abstract String isbn();

  @AutoValue.Builder
  public static abstract class Builder {
    public abstract Builder title(String title);
    public abstract Builder genre(Set<String> genre);
    public abstract Builder categories(ArrayList<String> categories);
    public abstract Builder authors(ArrayList<String> author);
    public abstract Builder language(String language);
    public abstract Builder description(String description);
    public abstract Builder infoLink(String infoLink);
    public abstract Builder pageCount(String pageCount);
    public abstract Builder publishedDate(String publishedDate);
    public abstract Builder publisher(String publisher);
    public abstract Builder maturityRating(String maturityRating);
    public abstract Builder thumbnail(String thumbnail);
    public abstract Builder isbn(String isbn);
    protected abstract ImmutableList.Builder<String> reviewsBuilder();

    public Builder addReview(String review) {
      review = cleanReview(review);
      reviewsBuilder().add(review);
      return this;
    }

    /**
     *In this function, we use replaceAll instead of encoding/decoding
     * methods because the bad characters do not come in during our encoding,
     * but rather, already exist in the file that we're reading from.
     * The two replacements in the first line are in fact, different
     * If anyone knows why or how to fix this in a cleaner way, please let ankita know!
     **/
    private String cleanReview(String original) {
      String newString = original.replaceAll("â€", "'").replaceAll("â€", "'");
      newString = newString.replaceAll("[^a-zA-Z0-9, .:*?!'#<>(){}/-]", "");
      return newString;
    }
    public abstract Book build();
  }
}
