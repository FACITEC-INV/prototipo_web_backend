package py.edu.facitec.pinv01_267.pinv01_267_ws.dto;

import lombok.Data;

@Data
public class PromedioLecturaDto {

    private Integer year;
    private Integer month;
    private Integer day;
    private Integer hour; 
    private String hourStr;   // <── NUEVO

    private Double ph;
    private Double od;
    private Double con;
    private Double tsd;
    private Double tur;
    private Double tem;

    // Constructor para promedios por hora
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

        if (hour != null) {
            this.hourStr = String.format("%02d:00 a %02d:59", hour, hour);
        }
    }

    // Constructor para promedios por día
    public PromedioLecturaDto(Integer year, Integer month, Integer day,
                              Double ph, Double od, Double con, Double tsd, Double tur, Double tem) {
        this(year, month, day, null, ph, od, con, tsd, tur, tem);
    }

    // Constructor para promedios por mes
    public PromedioLecturaDto(Integer year, Integer month,
                              Double ph, Double od, Double con, Double tsd, Double tur, Double tem) {
        this(year, month, null, null, ph, od, con, tsd, tur, tem);
    }
}
