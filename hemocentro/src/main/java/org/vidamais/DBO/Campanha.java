package org.vidamais.DBO;

public class Campanha implements Cloneable {
    private int idCampanha;
    private int idHemocentro;
    private String dataInicio;
    private String dataFim;
    private String horarioInicio;
    private String horarioFim;
    private int idTempoColeta;
    private int qtdAtendimentosSimultaneos;
    private String incentivo;
    private boolean disparoContatoFeito;

    public Campanha(int idCampanha, int idHemocentro, String dataInicio, String dataFim,
                    String horarioInicio, String horarioFim, int idTempoColeta,
                    int qtdAtendimentosSimultaneos, String incentivo, boolean disparoContatoFeito) {
        this.setIdCampanha(idCampanha);
        this.setIdHemocentro(idHemocentro);
        this.setDataInicio(dataInicio);
        this.setDataFim(dataFim);
        this.setHorarioInicio(horarioInicio);
        this.setHorarioFim(horarioFim);
        this.setIdTempoColeta(idTempoColeta);
        this.setQtdAtendimentosSimultaneos(qtdAtendimentosSimultaneos);
        this.setIncentivo(incentivo);
        this.setDisparoContatoFeito(disparoContatoFeito);
    }

    public int getIdCampanha() {
        return idCampanha;
    }

    public void setIdCampanha(int idCampanha) {
        this.idCampanha = idCampanha;
    }

    public int getIdHemocentro() {
        return idHemocentro;
    }

    public void setIdHemocentro(int idHemocentro) {
        this.idHemocentro = idHemocentro;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public String getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(String horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public String getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(String horarioFim) {
        this.horarioFim = horarioFim;
    }

    public int getIdTempoColeta() {
        return idTempoColeta;
    }

    public void setIdTempoColeta(int idTempoColeta) {
        this.idTempoColeta = idTempoColeta;
    }

    public int getQtdAtendimentosSimultaneos() {
        return qtdAtendimentosSimultaneos;
    }

    public void setQtdAtendimentosSimultaneos(int qtdAtendimentosSimultaneos) {
        this.qtdAtendimentosSimultaneos = qtdAtendimentosSimultaneos;
    }

    public String getIncentivo() {
        return incentivo;
    }

    public void setIncentivo(String incentivo) {
        this.incentivo = incentivo;
    }

    public boolean isDisparoContatoFeito() {
        return disparoContatoFeito;
    }

    public void setDisparoContatoFeito(boolean disparoContatoFeito) {
        this.disparoContatoFeito = disparoContatoFeito;
    }

    @Override
    public String toString() {
        return "Campanha{" +
                "idCampanha=" + idCampanha +
                ", idHemocentro=" + idHemocentro +
                ", dataInicio='" + dataInicio + '\'' +
                ", dataFim='" + dataFim + '\'' +
                ", horarioInicio='" + horarioInicio + '\'' +
                ", horarioFim='" + horarioFim + '\'' +
                ", idTempoColeta=" + idTempoColeta +
                ", qtdAtendimentosSimultaneos=" + qtdAtendimentosSimultaneos +
                ", incentivo='" + incentivo + '\'' +
                ", disparoContatoFeito=" + disparoContatoFeito +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Campanha campanha = (Campanha) o;

        return idCampanha == campanha.idCampanha &&
                idHemocentro == campanha.idHemocentro &&
                idTempoColeta == campanha.idTempoColeta &&
                qtdAtendimentosSimultaneos == campanha.qtdAtendimentosSimultaneos &&
                disparoContatoFeito == campanha.disparoContatoFeito &&
                (dataInicio != null ? dataInicio.equals(campanha.dataInicio) : campanha.dataInicio == null) &&
                (dataFim != null ? dataFim.equals(campanha.dataFim) : campanha.dataFim == null) &&
                (horarioInicio != null ? horarioInicio.equals(campanha.horarioInicio) : campanha.horarioInicio == null) &&
                (horarioFim != null ? horarioFim.equals(campanha.horarioFim) : campanha.horarioFim == null) &&
                (incentivo != null ? incentivo.equals(campanha.incentivo) : campanha.incentivo == null);
    }

    @Override
    public int hashCode() {
        int result = idCampanha;
        result = 31 * result + idHemocentro;
        result = 31 * result + (dataInicio != null ? dataInicio.hashCode() : 0);
        result = 31 * result + (dataFim != null ? dataFim.hashCode() : 0);
        result = 31 * result + (horarioInicio != null ? horarioInicio.hashCode() : 0);
        result = 31 * result + (horarioFim != null ? horarioFim.hashCode() : 0);
        result = 31 * result + idTempoColeta;
        result = 31 * result + qtdAtendimentosSimultaneos;
        result = 31 * result + (incentivo != null ? incentivo.hashCode() : 0);
        result = 31 * result + (disparoContatoFeito ? 1 : 0);
        return result;
    }

    @Override
    public Campanha clone() {
        try {
            return (Campanha) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // Não deveria acontecer, já que implementamos Cloneable
        }
    }
}
