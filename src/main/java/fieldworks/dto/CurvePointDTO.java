package fieldworks.dto;

public class CurvePointDTO {
    private double easting;
    private double northing;
    private double chainage;

    public CurvePointDTO() {}
    public CurvePointDTO(double easting, double northing, double chainage) {
        this.easting = easting;
        this.northing = northing;
        this.chainage = chainage;
    }

    public double getEasting() { return easting; }
    public void setEasting(double easting) { this.easting = easting; }

    public double getNorthing() { return northing; }
    public void setNorthing(double northing) { this.northing = northing; }

    public double getChainage() { return chainage; }
    public void setChainage(double chainage) { this.chainage = chainage; }
}
