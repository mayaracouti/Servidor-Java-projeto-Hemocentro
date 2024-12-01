package org.vidamais.DBO;

public class TipoSanguineoCampanha {

    private int idTipoSanguineoCampanha;
    private int idCampanha;
    private int idTipoSangue;

    public TipoSanguineoCampanha(int idTipoSanguineoCampanha, int idCampanha, int idTipoSangue) {
        this.idTipoSanguineoCampanha = idTipoSanguineoCampanha;
        this.idCampanha = idCampanha;
        this.idTipoSangue = idTipoSangue;
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

    public int getIdTipoSangue() {
        return idTipoSangue;
    }

    public void setIdTipoSangue(int idTipoSangue) {
        this.idTipoSangue = idTipoSangue;
    }

    @Override
    public String toString() {
        return "TipoSanguineoCampanha{" +
                "idTipoSanguineoCampanha=" + idTipoSanguineoCampanha +
                ", idCampanha=" + idCampanha +
                ", idTipoSangue=" + idTipoSangue +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        TipoSanguineoCampanha that = (TipoSanguineoCampanha) obj;

        if (idTipoSanguineoCampanha != that.idTipoSanguineoCampanha) return false;
        if (idCampanha != that.idCampanha) return false;
        return idTipoSangue == that.idTipoSangue;
    }

    @Override
    public int hashCode() {
        int result = idTipoSanguineoCampanha;
        result = 31 * result + idCampanha;
        result = 31 * result + idTipoSangue;
        return result;
    }
}
