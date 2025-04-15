package br.com.fiap.alarme.exception;

public class EmailDuplicadoException extends RuntimeException {

  public EmailDuplicadoException(String message) {
    super(message);
  }
}
