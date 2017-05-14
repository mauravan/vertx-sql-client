package io.vertx.pgclient.codec.decoder.message;


import io.vertx.pgclient.codec.Message;

import java.util.Objects;

/**
 * @author <a href="mailto:emad.albloushi@gmail.com">Emad Alblueshi</a>
 */

public class CommandComplete implements Message {

  private final String command;
  private final int rowsAffected;

  public CommandComplete(String command, int rowsAffected) {
    this.command = command;
    this.rowsAffected = rowsAffected;
  }

  public String getCommand() {
    return command;
  }

  public int getRowsAffected() {
    return rowsAffected;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CommandComplete that = (CommandComplete) o;
    return Objects.equals(command, that.command) &&
      Objects.equals(rowsAffected, that.rowsAffected);
  }

  @Override
  public int hashCode() {
    return Objects.hash(command, rowsAffected);
  }

  @Override
  public String toString() {
    return "CommandComplete{" +
      "command='" + command + '\'' +
      ", rowsAffected=" + rowsAffected +
      '}';
  }

}
