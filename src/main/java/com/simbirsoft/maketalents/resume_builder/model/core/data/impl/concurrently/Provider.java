package com.simbirsoft.maketalents.resume_builder.model.core.data.impl.concurrently;

import com.simbirsoft.maketalents.resume_builder.entity.Resume;
import com.simbirsoft.maketalents.resume_builder.model.core.data.ManagerDataSource;

import java.util.Objects;

/**
 * Class extends Thread for getting info about Resume by running in new thread getting Resume through managerDataSource
 * In fact this is wrapper managerDataSource, for getting Resume in separate thread
 * <p>
 * no use start()
 * method startGettingResume(idResume) starts this thread. method start() equals startGettingResume(null)
 * <p>
 * After calling startGettingResume(idResume), method getIdCalculatedResume() returns idResume, that was passed to startGettingResume(idResume)
 * and method getResume(id) gets calculated resume, if process calculation terminated and id equals idCalculatedResume.
 *
 * managerDataSource must be setting, before startGettingResume(id)
 *
 * method this.getResume(id) throws NPE if managerDataSource not setting at moment startGettingResume(idResume),
 * throws IllegalThreadStateException if this thread not terminated yet, throws  IllegalArgumentException if id not equals idCalculatedResume
 * Also method getResume(id) throws exception if getManagerDataSource().getResume(id) throwed exception
 *
 * Warning:
 * this.getResume(id) returns Resume in state which managerDataSource gives it at the moment of execution this thread
 * if managerDataSource gives Resume from storage, and info in storage are changing, this.getResume() in different
 * moments will return the same result, which was solved at time execution thread
 */
public class Provider extends Thread implements ManagerDataSource {

    private String idCalculatedResume;
    private Resume calculatedResume;
    private ManagerDataSource managerDataSource;
    private String description;
    private Exception exception;

    public Provider() {
        idCalculatedResume = null;
        calculatedResume = null;
    }

    /**
     * implementation method for obtains resume in separate thread. uses managerDataSource.get(idCalculatedResume)
     * use startGettingResume(idForResume) for obtain resume by managerDataSource in separate thread
     */
    @Override
    public void run() {
        try {
            calculatedResume = managerDataSource.getResume(this.idCalculatedResume);
        } catch (Exception e) {
            processing(e);
        }
    }

    /**
     * Method starts getting Resume in separate thread
     *
     * @param idResume id resume
     */
    public void startGettingResume(String idResume) {
        if (this.getState() == State.NEW) {
            idCalculatedResume = idResume;
        }
        start();
    }

    /**
     * processing exception, that can be thrown out by managerDataSource.getResume
     * method transfers e to this.getResume(id)
     *
     * @param e
     */
    public void processing(Exception e) {
        exception = e;
    }

    /**
     * method get Resume from managerDataSource, that managerDataSource gives at moment of start present thread
     * before getting Resume present thread must be started and terminated by this.startGettingResume(String idResume)
     *
     * @return calculated resume from managerDataSource at moment start present thread
     * @throws Exception throws IllegalThreadStateException if present thread's state not terminated,
     * throws IllegalArgumentException if passed id not equals id, that was used at moment running present thread,
     * also throws exception which was thrown by managerDataSource at moment started present thread
     */
    @Override
    public Resume getResume(String id) throws Exception {
        if (this.getState() != State.TERMINATED) {
            throw new IllegalThreadStateException("Thread " + this.getName() + " is " + this.getState() + ". Before getting Resume thread should be terminated."
                    + ((description != null) ? " Description: " + description : ""));
        } else {
            if (!Objects.equals(id, idCalculatedResume)){
                throw new IllegalArgumentException("Resume determinated for key = " + idCalculatedResume + ". Result for '" + id + "' unknown.");
            }
            if (exception != null) {
                throw exception;
            }
            return this.calculatedResume;
        }
    }

    /**
     * @return managerDataSource, that are used for getting Resume
     */
    public ManagerDataSource getManagerDataSource() {
        return managerDataSource;
    }

    public void setManagerDataSource(ManagerDataSource managerDataSource) {
        this.managerDataSource = managerDataSource;
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

    /**
     * Method for getting info about id resume, that was used at moment determination resume
     * @return id for resume.
     * @throws IllegalStateException if thread for determination resume not terminated (process for defined resume not started yet, or works)
     */
   public String getIdCalculatedResume() throws IllegalStateException{
        if (this.getState() != State.TERMINATED) {
            throw new IllegalStateException("id for determinated resume unknown. not terminated thread that gets resume. Status " + this.getState());
        }
        return idCalculatedResume;
   }

    @Override
    public String toString() {
        return super.toString() + " " + this.getState() + ((description != null) ? " Description: " + description : "");
    }
}
