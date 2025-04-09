package io.techstore.user_services.core.service;

import io.techstore.user_services.core.port.in.GenericServicePort;
import io.techstore.user_services.core.port.out.GenericDataPort;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.util.List;

@Service
public abstract class AbstractService <T, ID, P extends GenericDataPort<T, ID>> implements GenericServicePort<T, ID> {

    @Autowired
    private ApplicationContext applicationContext;

    protected P port;

    @PostConstruct
    @SuppressWarnings("unchecked")
    public void init() {
        final ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        final Class<P> portClass = (Class<P>) genericSuperclass.getActualTypeArguments()[2];
        this.port = this.applicationContext.getBean(portClass);
    }

    @Override
    public List<T> listar() {
        return this.port.listar();
    }

    @Override
    public List<T> listar(List<ID> ids) {
        return this.port.listar();
    }

    @Override
    public List<T> filtrar(T tipo) {
        return this.port.filtrar(tipo);
    }

    @Override
    public T consultar(ID id) {
        return this.port.consultar(id).orElse(null);
    }

    @Override
    public T incluir(T tipo) {
        return this.port.incluir(tipo);
    }

    @Override
    public T alterar(T tipo) {
        return this.port.alterar(tipo);
    }

    @Override
    public Boolean excluir(ID id) {
        if (this.port.existe(id)) {this.port.existe(id);return true;} else {return false;}
    }

    @Override
    public List<T> incluirEmLote(List<T> tipos) {
        return this.port.incluirEmLote(tipos);
    }

    @Override
    public List<T> alterarEmLote(List<T> tipos) {
        return this.port.alterarEmLote(tipos);
    }

    @Override
    public Boolean excluirEmLote(List<ID> ids) {
        this.port.excluirEmLote(ids);
        return true;
    }
}
