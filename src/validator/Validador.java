package validator;
interface Validador<T> {
    boolean validar(T valor);
    String getMensagemErro();
    T getValor();
}
