package org.vidamais.DBO;

public class TipoSanguineoCampanha {

    private int idTipoSanguineoCampanha;
    private int idCampanha;
    private int idTipoSanguineo;

    public TipoSanguineoCampanha(int idTipoSanguineoCampanha, int idCampanha, int idTipoSanguineo) {
        this.idTipoSanguineoCampanha = idTipoSanguineoCampanha;
        this.idCampanha = idCampanha;
        this.idTipoSanguineo = idTipoSanguineo;
    }

    public int getIdTipoSanguineoCampanha() {
        return idTipoSanguineoCampanha;
    }

    public void setIdTipoSanguineoCampanha(int idTipoSanguineoCampanha) {
        this.idTipoSanguineoCampanha = idTipoSanguineoCampanha;
    }

    public int getIdCampanha() {
        return idCampanha;
    }

    public void setIdCampanha(int idCampanha) {
        this.idCampanha = idCampanha;
    }

    public int getIdTipoSanguineo() {
        return idTipoSanguineo;
    }

    public void setIdTipoSanguineo(int idTipoSanguineo) {
        this.idTipoSanguineo = idTipoSanguineo;
    }

    @Override
    public String toString() {
        return "TipoSanguineoCampanha{" +
                "idTipoSanguineoCampanha=" + idTipoSanguineoCampanha +
                ", idCampanha=" + idCampanha +
                ", idTipoSanguineo=" + idTipoSanguineo +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        TipoSanguineoCampanha that = (TipoSanguineoCampanha) obj;

        if (idTipoSanguineoCampanha != that.idTipoSanguineoCampanha) return false;
        if (idCampanha != that.idCampanha) return false;
        return idTipoSanguineo == that.idTipoSanguineo;
    }

    @Override
    public int hashCode() {
        int result = idTipoSanguineoCampanha;
        result = 31 * result + idCampanha;
        result = 31 * result + idTipoSanguineo;
        return result;
    }
}
