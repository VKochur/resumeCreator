package com.simbirsoft.maketalents.resume_builder;

import com.simbirsoft.maketalents.resume_builder.launcher.LauncherFactory;
import com.simbirsoft.maketalents.resume_builder.launcher.Launcher;
import com.simbirsoft.maketalents.resume_builder.launcher.LauncherTypes;

import java.util.Arrays;

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
            System.out.println(e.getMessage() + "\nCheck that launcher was indicated correct.");
            e.printStackTrace();
        }


    }

    private static void showArgs() {
        System.out.println("----args for use----");
        System.out.println(Arrays.toString(argsForLauncher));
        System.out.println("---------------------");
    }

    private static void showDescription() {
        System.out.println("------DESCRIPTION PROGRAMM------");
        System.out.println(launcher.getDescription());
    }

    private static void defineParamsForRunning(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Wrong parametrs for running. Must indicate launcher.\n" +
                    " Valid: " + Arrays.toString(LauncherFactory.getAnalizedTypes()));
        } else {
            try {
                LauncherTypes launcherType = LauncherTypes.valueOf(args[0]);
                argsForLauncher = new String[args.length - 1];
                System.arraycopy(args, 1, argsForLauncher, 0, args.length - 1);
                launcher = LauncherFactory.getInstance(launcherType);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(e.getMessage() + "\nValid indicate launcher: " + Arrays.toString(LauncherFactory.getAnalizedTypes()));
            }
        }
    }
}
