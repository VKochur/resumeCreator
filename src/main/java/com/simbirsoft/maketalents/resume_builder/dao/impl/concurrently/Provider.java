package com.simbirsoft.maketalents.resume_builder.dao.impl.concurrently;

import com.simbirsoft.maketalents.resume_builder.dao.ResumeDao;
import com.simbirsoft.maketalents.resume_builder.entity.Resume;

/**
 * Class extends Thread for getting info about resume by running in new thread getting Resume through resumeDao
 * In fact this is wrapper resumeDao, for getting Resume in separate thread
 * resumeDao must be setting, and this thread  must be termimated before getting Resume,
 * otherwise method this.getResume() throws NPE or IllegalThreadStateException.
 * Also method getResume() throws exception if getResumeDao().getResume() throwed exception
 *
 * Warning:
 * this.getResume() returns Resume in state which resumeDao gives it at the moment of execution this thread
 * if resumeDao gives Resume from storage, and info in storage are changes in time, this.getResume() in different
 * moments will return the same result, which was solved at time execution thread
 */
public class Provider extends Thread implements ResumeDao {

    private Resume resume;
    private ResumeDao resumeDao;
    private String description;
    private Exception exception;

    public Provider() {
    }

    /**
     * obtain resume by resumeDao in separate thread
     */
    @Override
    public void run() {
        try {
            resume = resumeDao.getResume();
        } catch (Exception e) {
            processing(e);
        }
    }

    /**
     * processing exception, that can be thrown out by resumeDao.getResume
     * method transfer e to this.getResume()
     *
     * @param e
     */
    public void processing(Exception e) {
        exception = e;
    }

    /**
     * method get Resume from resumeDao, that resumeDao gives at moment of start present thread
     * before getting Resume present thread must be started and terminated
     *
     * @return resume from resumeDao at moment start present thread
     * @throws Exception throws IllegalThreadStateException if present thread's state not terminated. Also throws exception which was
     *                   thrown by resumeDao at moment started present thread
     */
    @Override
    public Resume getResume() throws Exception {
        if (this.getState() != State.TERMINATED) {
            throw new IllegalThreadStateException("Thread " + this.getName() + " is " + this.getState() + ". Before getting Resume thread should be terminated."
                    + ((description != null) ? " Description: " + description : ""));
        } else {
            if (exception != null) {
                throw exception;
            }
            return this.resume;
        }
    }

    /**
     * @return resumeDao, that are used for getting Resume
     */
    public ResumeDao getResumeDao() {
        return resumeDao;
    }

    public void setResumeDao(ResumeDao resumeDao) {
        this.resumeDao = resumeDao;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return super.toString() + " " + this.getState() + ((description != null) ? " Description: " + description : "");
    }
}
