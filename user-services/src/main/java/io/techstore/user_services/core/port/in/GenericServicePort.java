package io.techstore.user_services.core.port.in;

import java.util.List;

public interface GenericServicePort<T, ID>{

    List<T> listar();

    List<T> listar(List<ID> ids);

    List<T> filtrar(T tipo);

    T consultar(ID id);

    T incluir(T tipo);

    T alterar(T tipo);

    Boolean excluir(ID id);

    List<T> incluirEmLote(List<T> tipos);

    List<T> alterarEmLote(List<T> tipos);

    Boolean excluirEmLote(List<ID> ids);
}
