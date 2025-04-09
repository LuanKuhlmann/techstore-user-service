package io.techstore.user_services.core.port.out;

import java.util.List;
import java.util.Optional;

public interface GenericDataPort<T, ID> {

    List<T> listar();

    List<T> listar(List<ID> ids);

    List<T> filtrar(T tipo);

    Boolean existe(ID id);

    Optional<T> consultar(ID id);

    T incluir(T tipo);

    T alterar(T tipo);

    void excluir(ID id);

    List<T> incluirEmLote(List<T> tipos);

    List<T> alterarEmLote(List<T> tipos);

    void excluirEmLote(List<ID> ids);
}
