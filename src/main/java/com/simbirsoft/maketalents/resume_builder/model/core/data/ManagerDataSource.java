package com.simbirsoft.maketalents.resume_builder.model.core.data;

import com.simbirsoft.maketalents.resume_builder.entity.Resume;

public interface ManagerDataSource {

    /**
     * method gets Resume by unique key from storage
     * @param id unique key
     * @return data from storage
     * @throws Exception implementations may throws
     */
    Resume getResume(String id) throws Exception;
}
