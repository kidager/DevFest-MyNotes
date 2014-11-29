package tn.devfest.entities;

import java.sql.Timestamp;

public class NoteEntity {
  private long id;
  private String title;
  private String content;
  private long groupId;
  private Timestamp timestamp;

  public NoteEntity(long id, String title, String content, long groupId,
      Timestamp timestamp) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.groupId = groupId;
    this.timestamp = timestamp;
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

  public long getGroupId() {
    return groupId;
  }

  public void setGroupId(long groupId) {
    this.groupId = groupId;
  }

  public Timestamp getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Timestamp timestamp) {
    this.timestamp = timestamp;
  }
}
