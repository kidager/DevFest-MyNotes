package tn.devfest.entities;

public class MediaEntity {
  private long id;
  private String path;
  private long noteId;

  public MediaEntity(String path, long noteId) {
    this.path = path;
    this.noteId = noteId;
  }
  
  public MediaEntity(long id, String path, long noteId) {
    this(path, noteId);
    this.id = id;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public long getNoteId() {
    return noteId;
  }

  public void setNoteId(long noteId) {
    this.noteId = noteId;
  }

}
