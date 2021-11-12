package quests.reflection.factory;

import java.lang.reflect.ParameterizedType;

/**
 * Создает объект типа Т
 *
 * @param <T>
 * @author MeSeenDev
 */
public abstract class myFactory<T> {

    @SuppressWarnings("unchecked")
    public T generateInstance() throws Exception {
        Class<T> persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        return persistentClass.getDeclaredConstructor().newInstance();
    }
}
