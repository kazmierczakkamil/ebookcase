package pl.java.ebookcase.mapper;

public interface DtoMapper<E, D> {
    E mapToEntity(D dto);
    D mapToDto(E entity);
}
