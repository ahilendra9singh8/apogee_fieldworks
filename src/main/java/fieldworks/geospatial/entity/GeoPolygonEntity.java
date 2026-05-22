package fieldworks.geospatial.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import org.locationtech.jts.geom.MultiPolygon;

@Entity
@Table(name = "geo_polygon")
//@Getter
//@Setter
public class GeoPolygonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String village;

	@Column(columnDefinition = "geometry(MultiPolygon,4326)")
	private MultiPolygon geom;

	public GeoPolygonEntity(Long id, String name, String village, MultiPolygon geom) {
		super();
		this.id = id;
		this.name = name;
		this.village = village;
		this.geom = geom;
	}

	public GeoPolygonEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public MultiPolygon getGeom() {
		return geom;
	}

	public void setGeom(MultiPolygon geom) {
		this.geom = geom;
	}

}