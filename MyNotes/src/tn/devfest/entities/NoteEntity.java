package tn.devfest.entities;

import java.sql.Timestamp;

public class NoteEntity {
  private long id;
  private String title;
  private String content;
  private long categoryId;
  private Timestamp timestamp;
  
  public NoteEntity() {}
  
  public NoteEntity(String title, String content, long categoryId,
      Timestamp timestamp) {
    this.title = title;
    this.content = content;
    this.categoryId = categoryId;
    this.timestamp = timestamp;
  }
  
  public NoteEntity(long id, String title, String content, long categoryId, Timestamp timestamp) {
    this(title, content, categoryId, timestamp);
    this.id = id;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(long categoryId) {
    this.categoryId = categoryId;
  }

  public Timestamp getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Timestamp timestamp) {
    this.timestamp = timestamp;
  }
}
