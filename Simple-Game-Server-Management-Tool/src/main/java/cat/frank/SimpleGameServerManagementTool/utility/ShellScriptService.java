package cat.frank.SimpleGameServerManagementTool.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service
public class ShellScriptService {

    // logger for this class
    private static final Logger logger = LoggerFactory.getLogger(ShellScriptService.class);

    public static String runShellScript(String scriptPath) {
        logger.info("Running script: " + scriptPath);
        StringBuilder output = null;
        BufferedReader reader = null;
        ProcessBuilder pb = null;
        Process process = null;
        try {
            pb = new ProcessBuilder(scriptPath);
            process = pb.start();
            reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            output = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            int exitCode = process.waitFor();
            logger.info("Script execution completed with exit code: " + exitCode);
            logger.info("Script output: \n" + output.toString());

        } catch (Exception e) {
            logger.error("Error while executing script: " + e.getMessage());
            e.printStackTrace();
        }finally {
            // close the reader
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    logger.error("Error while closing reader: " + e.getMessage());
                    e.printStackTrace();
                }
            }
            // destroy the process
            if (process != null) {
                try {
                    process.destroy();
                } catch (Exception e) {
                    logger.error("Error while destroying process: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        return output == null ? null : output.toString();
    }

}
