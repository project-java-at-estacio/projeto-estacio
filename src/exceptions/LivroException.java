package exceptions;

public class LivroException extends Exception {

  public LivroException(String mensagem) {
      super(mensagem);
  }

  public LivroException(String mensagem, Throwable causa) {
      super(mensagem, causa);
  }
  
}
