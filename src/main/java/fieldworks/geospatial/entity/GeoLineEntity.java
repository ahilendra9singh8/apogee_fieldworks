package fieldworks.geospatial.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.MultiLineString;

@Entity
@Table(name = "geo_line")
@Getter
@Setter
public class GeoLineEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String roadType;

//	@Column(columnDefinition = "geometry(LineString,4326)")
//	private LineString geom;
	
	@Column(columnDefinition = "geometry(MultiLineString,4326)")
	private MultiLineString geom;

	public MultiLineString getGeom() {
		return geom;
	}

	public void setGeom(MultiLineString geom) {
		this.geom = geom;
	}

//	public GeoLineEntity(Long id, String name, String roadType, LineString geom) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.roadType = roadType;
//		this.geom = geom;
//	}
	public GeoLineEntity(Long id, String name, String roadType, MultiLineString geom) {
		super();
		this.id = id;
		this.name = name;
		this.roadType = roadType;
		this.geom = geom;
	}

	public GeoLineEntity() {
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

	public String getRoadType() {
		return roadType;
	}

	public void setRoadType(String roadType) {
		this.roadType = roadType;
	}

//	public LineString getGeom() {
//		return geom;
//	}
//
//	public void setGeom(LineString geom) {
//		this.geom = geom;
//	}

}