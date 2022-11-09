package com.jpaul.dao;
import java.util.List;

public interface IDAO<O, String> {
    List<O> fetch() throws Exception;
    O gotoId(O o) throws Exception;
    O save(O o) throws Exception;
    List<O> search(String pattern) throws Exception;
    O update(O o) throws Exception;
    O delete(O o) throws Exception;
    O set() throws Exception;
    List<O> setAll()throws Exception;
}