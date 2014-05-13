package com.supinfo.supcourses.dao;

import javax.ejb.Local;

@Local
public interface MockDao {
    boolean CreateFakeDataInDb();
}
