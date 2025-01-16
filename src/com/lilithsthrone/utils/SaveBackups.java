package com.lilithsthrone.utils;

import java.io.File;
import java.nio.file.Files;

public class SaveBackups {
    private static final File BACKUP_DIR = new File("./data/backups/");
    public static boolean FAILURE_DETECTED = false;

    public static void backupSave(String saveName) {
//        FAILURE_DETECTED = true; // debug line to test that backups are indeed made.
        if (!BACKUP_DIR.exists()) BACKUP_DIR.mkdirs();
        File saveFile = new File("./data/saves/", saveName + ".xml");
        File backupFile = new File(BACKUP_DIR, saveName + ".xml.bak");
        /* Normal Backups are temporary and just are save_name.xml.bak
         *   however we also create cold backups when anything goes wrong
         *   these take the form of save_name.filetimestamp.bak
         */
        if (!FAILURE_DETECTED && backupFile.exists()) {
            try {
                Files.delete(backupFile.toPath());
            } catch (Exception e) {
                System.err.println("Failed to delete backup file: " + backupFile.getAbsolutePath());
                e.printStackTrace();
                /*If failed to delete attempt to rename it instead */
                backupFile.renameTo(new File(BACKUP_DIR,saveName + "." + saveFile.lastModified() + ".backup"));
                backupFile = new File(BACKUP_DIR, saveName + ".bak");
            }
        }

        if (saveFile.exists()) {
            try {
                if (!FAILURE_DETECTED) {// if there was no failure, make standard backup
                    Files.copy(saveFile.toPath(), backupFile.toPath());
                } else {//if there was a failure, make cold backups of both the last save, and the previous backup. Just in case.
                    String backupLocation = saveName + "." + saveFile.lastModified() + ".backup";
                    Files.copy(saveFile.toPath(), new File(BACKUP_DIR, backupLocation).toPath());
                    if (backupFile.exists()) {
                        String backup2 = saveName + "." + backupFile.lastModified() + ".backup";
                        backupLocation += ", " + backup2;// this is because I intended to tell the user where the back(s) were stored
                        Files.move(backupFile.toPath(), new File(BACKUP_DIR, backup2).toPath());
                    }
                    FAILURE_DETECTED = false;
                    /* DOESN'T WORK, doesn't do anything, not sure why, not my expertise */
//                    Main.game.flashMessage(PresetColour.GENERIC_BAD, "Save Error Found</br>backed up your saves to </br>" + backupLocation.replace(", ", "</br>"));
                }
            } catch (Exception e) {
                System.err.println("Failed to copy save file: " + saveFile.getAbsolutePath());
                e.printStackTrace();
            }
        }
    }
}