package secondaryrepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.space.ingestion.primarymodel.MinuteStats;

public interface SecondaryRepository extends JpaRepository<MinuteStats, Long>  {

}
