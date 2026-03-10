package fieldworks.worker;

import org.redisson.api.RQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import fieldworks.repository.CSVFileRepository;

@Component
public class WorkerRunner implements CommandLineRunner {

	@Autowired
	private RedissonClient redissonClient;
	@Autowired
	private FileProcessor fileProcessor;
	@Autowired
	private CSVFileRepository cSVFileRepository;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("🚀 Worker started and listening for jobs...");
		RQueue<Long> queue = redissonClient.getQueue("file-processing");

		new Thread(() -> {
			while (true) {
				Long recordId = queue.poll();
				if (recordId != null) {
					cSVFileRepository.findById(recordId).ifPresent(record -> {
						fileProcessor.process(record.getId(), record.getFilePath(),
								"D:/Shailendra Singh/Projects/Backend/file_dir_2", record.getJobId());
					});
				}
				try {
					Thread.sleep(2000);
				} catch (InterruptedException ignored) {
				}
			}
		}).start();
	}
}
