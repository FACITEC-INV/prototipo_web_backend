package py.edu.facitec.pinv01_267.pinv01_267_ws.sevices;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseService<T, D> {
    @Autowired
    private ModelMapper modelMapper;

    protected Class<T> entityClass;
    protected Class<D> dtoClass;

    public BaseService(Class<T> entityClass, Class<D> dtoClass) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    protected D convertToDto(T entity) {
        return modelMapper.map(entity, dtoClass);
    }

    protected T convertToEntity(D dto) {
        return modelMapper.map(dto, entityClass);
    }
}
