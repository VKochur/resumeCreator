package com.simbirsoft.maketalents.resume_builder;

import com.simbirsoft.maketalents.resume_builder.launcher.Launcher;
import com.simbirsoft.maketalents.resume_builder.launcher.LauncherFactory;
import com.simbirsoft.maketalents.resume_builder.launcher.LauncherTypes;

import java.util.Arrays;

/**
 * Contains entry point for running program
 * <p>
 * main(String[] args)
 * program start from console
 * args[0] must be one of
 * BASIC,
 * SPRING,
 * MULTI_THREADS_SPRING,
 * WEB
 * <p>
 * ------------------
 * if args[0] == BASIC
 * <p>
 * run program for create html by properties file. not uses spring-core
 * program writes log in executable dir/resume_builder.log
 * <p>
 * args must be:
 * args[1] - path to file properties
 * args[2] - path to directory html file
 * args[3] - name for html file
 * example
 * args = ["BASIC", "D:\test\1\anyPerson.properties", "D:\temp\", "createdAnyPerson"]
 * <p>
 * if args.length < 3, and args[0] = BASIC
 * program creates file 'resume.html' in executable directory, from 'resume.properties' located in executable directory
 * <p>
 * -----------------
 * if args[0] == SPRING
 * <p>
 * like case with BASIC run program for create html by properties file.
 * unlike case then args[0] = BASIC, case args[0] = SPRING USES spring-core
 * program writes log in executable dir/resume_builder.log
 * <p>
 * args must be:
 * args[1] - path to file properties
 * args[2] - path to directory html file
 * args[3] - name for html file
 * example
 * args = ["BASIC", "D:\test\1\anyPerson.properties", "D:\temp\", "createdAnyPerson"]
 * <p>
 * if args.length < 3, and args[0] = BASIC
 * program creates file 'resume.html' in executable directory, from 'resume.properties' located in executable directory
 * <p>
 * ------------------
 * if args[0] == MULTI_THREADS_SPRING
 * <p>
 * run program for create one html 2 by properties files.
 * properties files are reading in separate threads
 * <p>
 * resulting resume html contains by data from first file, and gets from second properties file info,
 * that not specified in first.
 * <p>
 * args must be:
 * args[1] - path to first properties file, that contains data about resume
 * args[2] - path to second properties file, that contains data about resume
 * args[3] - path to html file for creation resume
 * example
 * args = {"MULTI_THREADS_SPRING", "C:\Folder\baseInfo.properties", "C:\Folder2\Folder3\addedInfo.properties", "D:\summary.html"}
 * <p>
 * if args.length < 4, uses default:
 * first file = executable dir/person1.properties
 * second file = executable dir/person2.properties
 * html file = executable dir/createdFromPerson1Person2.html
 * <p>
 * --------------------
 * if args[0] == WEB
 * run web application that are available by http://localhost:8080/
 */
public class MainJar {

    private static String[] argsForLauncher;
    private static Launcher launcher;

    public static void main(String[] args) {
        runApplication(args);
    }

    private static void runApplication(String[] args) {
        try {
            defineParamsForRunning(args);
            showDescription();
            showArgs();
            launcher.launch(argsForLauncher);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + "\n" +
                    "Check that launcher was indicated correct.");
            e.printStackTrace();
        }
    }

    private static void showArgs() {
        System.out.println("--------args for use--------");
        System.out.println(Arrays.toString(argsForLauncher));
        System.out.println("----------------------------");
    }

    private static void showDescription() {
        System.out.println("------DESCRIPTION PROGRAM------");
        System.out.println(launcher.getDescription());
        System.out.println("--------------------------------");
    }

    private static void defineParamsForRunning(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Wrong parametrs for running. Must indicate launcher.\n" +
                    "Valid: " + Arrays.toString(LauncherFactory.getAnalizedTypes()));
        } else {
            try {
                LauncherTypes launcherType = LauncherTypes.valueOf(args[0]);
                argsForLauncher = new String[args.length - 1];
                System.arraycopy(args, 1, argsForLauncher, 0, args.length - 1);
                launcher = LauncherFactory.getInstance(launcherType);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(e.getMessage() + "\n" +
                        "Valid indicate launcher: " + Arrays.toString(LauncherFactory.getAnalizedTypes()));
            }
        }
    }
}
