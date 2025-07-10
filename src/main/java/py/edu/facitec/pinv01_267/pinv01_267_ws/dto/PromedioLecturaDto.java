package py.edu.facitec.pinv01_267.pinv01_267_ws.dto;

import lombok.Data;

@Data
public class PromedioLecturaDto {
    private Integer year;
    private Integer month;
    private Integer day;    // puede ser null
    private Integer hour;   // puede ser null

    private Double ph;
    private Double od;
    private Double con;
    private Double tsd;
    private Double tur;
    private Double tem;

    // Constructores adaptables según la granularidad
    public PromedioLecturaDto(Integer year, Integer month, Integer day, Integer hour,
                               Double ph, Double od, Double con, Double tsd, Double tur, Double tem) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.ph = ph;
        this.od = od;
        this.con = con;
        this.tsd = tsd;
        this.tur = tur;
        this.tem = tem;
    }

    public PromedioLecturaDto(Integer year, Integer month, Integer day,
                               Double ph, Double od, Double con, Double tsd, Double tur, Double tem) {
        this(year, month, day, null, ph, od, con, tsd, tur, tem);
    }

    public PromedioLecturaDto(Integer year, Integer month,
                               Double ph, Double od, Double con, Double tsd, Double tur, Double tem) {
        this(year, month, null, null, ph, od, con, tsd, tur, tem);
    }
}
