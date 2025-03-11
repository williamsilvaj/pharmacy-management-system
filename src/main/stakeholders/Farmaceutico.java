import java.util.List;

public class Farmaceutico {
    private String turno;
    private String crf;
    private float cargaHoraria;

    // Construtor
    public Farmaceutico(String turno, String crf, float cargaHoraria) {
        this.turno = turno;
        this.crf = crf;
        this.cargaHoraria = cargaHoraria;
    }

    // Getters e Setters
    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getCrf() {
        return crf;
    }

    public void setCrf(String crf) {
        this.crf = crf;
    }

    public float getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(float cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    // Método para exibir as informações do Farmacêutico
    @Override
    public String toString() {
        return "Farmacêutico{" +
                "turno='" + turno + '\'' +
                ", crf='" + crf + '\'' +
                ", cargaHoraria=" + cargaHoraria +
                '}';
    }
}
