package cat.frank.SimpleGameServerManagementTool.config;

import cat.frank.SimpleGameServerManagementTool.utility.EmailService;
import cat.frank.SimpleGameServerManagementTool.utility.ShellScriptService;
import cat.frank.SimpleGameServerManagementTool.utility.StaticVariables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IPService {
    // logger for this class
    private static final Logger logger = LoggerFactory.getLogger(IPService.class);

    private final ImportantDataService importantDataService;
    private final EmailService emailService;

    @Autowired
    public IPService(ImportantDataService importantDataService,
                     EmailService emailService) {
        this.importantDataService = importantDataService;
        this.emailService = emailService;
    }

    public void checkIfIpChanged() {
        ImportantDataModel currentImportantData = importantDataService.getImportantData();
        // run the script to get the ip
        String ipFindScriptPath = currentImportantData.getScriptsPath()
                + StaticVariables.Get_IP_Script_Name;

        // run the shell script and get the return value
        String ip = ShellScriptService.runShellScript(ipFindScriptPath);
        logger.info("ip: " + ip);

        // check if the ip is changed, if changed save the new ip and send email to admin with the new ip
        if (ip ==  null || !ip.equals(currentImportantData.getServerIp())) {
            logger.warn("ip changed from {} to {}", currentImportantData.getServerIp(), ip);
            currentImportantData.setServerIp(ip);
            importantDataService.saveImportantData(currentImportantData);
            if(currentImportantData.getEmailEnabled()){
                emailService.sendEmail(currentImportantData.getEmailSender(),
                        currentImportantData.getEmailReceiver(),
                        StaticVariables.IP_Change_Email_Subject,
                        "Server IP changed to: " + ip);
            }
        }
    }
}
