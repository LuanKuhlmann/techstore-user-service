package io.techstore.user_services.infrastructure.adapter;

import io.techstore.user_services.core.port.out.GenericDataPort;
import io.techstore.user_services.infrastructure.config.annotation.Adapter;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

@Adapter
public abstract class AbstractRepositoryAdapter<T, ID, REPO extends JpaRepository<T, ID>> implements GenericDataPort<T, ID> {

    @Autowired
    private ApplicationContext applicationContext;

    protected REPO repository;

    @PostConstruct
    @SuppressWarnings("unchecked")
    public void init() {
        final ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        final Class<REPO> repositoryClass = (Class<REPO>) genericSuperclass.getActualTypeArguments()[2];
        this.repository = this.applicationContext.getBean(repositoryClass);
    }

    @Override
    public List<T> listar() {
        return this.repository.findAll();
    }

    @Override
    public List<T> listar(final List<ID> ids) {
        return this.repository.findAllById(ids);
    }

    @Override
    public List<T> filtrar(final T tipo) {
        return this.repository.findAll(Example.of(tipo));
    }

    @Override
    public Boolean existe(final ID id) {return this.repository.existsById(id);}

    @Override
    public Optional<T> consultar(final ID id) {return this.repository.findById(id);}

    @Override
    public T incluir(final T tipo) {
        return this.repository.save(tipo);
    }

    @Override
    public T alterar(T tipo) {
        return this.repository.save(tipo);
    }

    @Override
    public void excluir(ID id) {this.repository.deleteById(id);}

    @Override
    public List<T> incluirEmLote(List<T> tipos) {
        return this.repository.saveAll(tipos);
    }

    @Override
    public List<T> alterarEmLote(List<T> tipos) {
        return this.repository.saveAll(tipos);
    }

    @Override
    public void excluirEmLote(List<ID> ids) {this.repository.deleteAllById(ids);}
}
