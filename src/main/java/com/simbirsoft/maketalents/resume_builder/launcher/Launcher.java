package com.simbirsoft.maketalents.resume_builder.launcher;

/**
 * launcher program
 *
 * Instance of launcher should be getting from LauncherFactory
 * Implementations must have public constructor without args
 */
public interface Launcher {

     String getDescription();

     void launch(String[] args);

}
