package client;

import java.io.*;
import java.util.*;

public class Logger {

    private static List<String> log = new ArrayList<>();

    public static List<String> getLog() {
        return log;
    }

    public static void log (String login, String nick, String logLine){
        String logFileName = String.format("Logs/history_%s.log", login);
        File logFile = new File(logFileName);
        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (BufferedWriter br = new BufferedWriter(new FileWriter(logFile, true))){
            br.write(String.format("%s: %s%n", nick, logLine));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readLogFile(File logFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(logFile))){
            log.clear();
            while (br.ready()){
                log.add(br.readLine());
            }
            Collections.sort(log, Comparator.reverseOrder());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
