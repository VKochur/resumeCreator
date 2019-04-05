package com.simbirsoft.maketalents.resume_builder.dao.db;

import com.simbirsoft.maketalents.resume_builder.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface Repository extends JpaRepository<Resume, Long>{

}
