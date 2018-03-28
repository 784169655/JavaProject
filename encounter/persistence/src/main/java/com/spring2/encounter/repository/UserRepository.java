package com.spring2.encounter.repository;

import com.spring2.encounter.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by  邱伟
 * 2018/3/27 09:45
 * {@link User} {@link Repository}
 * @see  User#id
 */

@Repository
public class UserRepository {

    /**
     * 采用内存型储存方式  -> Map
     */
    private final ConcurrentMap<Integer, User> repository = new ConcurrentHashMap<>();

    private final static AtomicInteger idGenerator = new AtomicInteger();

    /**
     * 如果保存成功，返回<code>true</code> 否则返回<code>false</code>
     * @param user {@link User}
     * @return
     */
    public boolean save(User user) {

        //ID  从一开始
        Integer id = idGenerator.incrementAndGet();
        user.setId(id);
        //保存成功
        return repository.put(id, user) == null;
    }

    public Collection<User> findAll(){

        return repository.values();
    }
}