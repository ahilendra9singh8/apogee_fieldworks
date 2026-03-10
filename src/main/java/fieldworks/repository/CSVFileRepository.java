package fieldworks.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fieldworks.models.CSVFiles;

public interface CSVFileRepository extends JpaRepository<CSVFiles, Long> {
    Optional<CSVFiles> findByChecksum(String checksum);
    Optional<CSVFiles> findByJobId(String jobId);
}
