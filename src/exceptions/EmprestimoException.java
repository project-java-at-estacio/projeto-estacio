package exceptions;

public class EmprestimoException extends Exception {

  public EmprestimoException() {
      super("Erro na operação de empréstimo.");
  }

  public EmprestimoException(String mensagem) {
      super(mensagem);
  }

  public EmprestimoException(String mensagem, Throwable causa) {
      super(mensagem, causa);
  }

  public EmprestimoException(Throwable causa) {
      super("Erro na operação de empréstimo.", causa);
  }
}
