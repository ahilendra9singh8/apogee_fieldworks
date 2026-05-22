package fieldworks.geospatial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fieldworks.geospatial.entity.GeoPointEntity;

public interface GeoPointRepository extends JpaRepository<GeoPointEntity, Long> {
}