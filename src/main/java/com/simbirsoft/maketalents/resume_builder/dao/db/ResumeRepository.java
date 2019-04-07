package com.simbirsoft.maketalents.resume_builder.dao.db;

import com.simbirsoft.maketalents.resume_builder.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Long>{
}
