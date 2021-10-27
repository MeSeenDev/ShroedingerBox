package quests.reflection.factory;

import java.lang.reflect.ParameterizedType;
import java.util.Optional;

/**
 * Создает объект типа Т
 *
 * @param <Type>
 * @author MeSeenDev
 */
public interface optionalFactory<Type> {

    @SuppressWarnings("unchecked")
    default Type generateOptInstance() throws Exception {
        Class<Type> mClass = (Class<Type>) Optional.of(getClass())
                .map(Class::getGenericSuperclass)
                .filter(el -> el instanceof ParameterizedType)
                .map(el -> (ParameterizedType) el)
                .filter(el -> el.getActualTypeArguments().length > 0)
                .map(el -> el.getActualTypeArguments()[0])
                .filter(el -> el instanceof Class).orElse(null);
        return mClass != null ? mClass.getDeclaredConstructor().newInstance() : null;
    }
}
