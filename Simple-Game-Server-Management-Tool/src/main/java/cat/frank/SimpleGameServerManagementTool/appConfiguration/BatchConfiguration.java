package cat.frank.SimpleGameServerManagementTool.appConfiguration;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    private JobLauncher jobLauncher;
    private JobRepository jobRepository;

    @Autowired
    public BatchConfiguration(JobLauncher jobLauncher, JobRepository jobRepository) {
        this.jobLauncher = jobLauncher;
        this.jobRepository = jobRepository;
    }
    // https://www.youtube.com/watch?v=Itvgrt9QXto
    // https://www.youtube.com/watch?v=ZXlxQ3z4zDE
}
