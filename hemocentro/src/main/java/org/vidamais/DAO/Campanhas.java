package org.vidamais.DAO;

import org.vidamais.DBMySQLServer;
import org.vidamais.DBO.Campanha;
import org.vidamais.DBO.TipoSanguineoCampanha;
import org.vidamais.Core.MeuResultSet;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Campanhas {

    public static boolean isCadastrada (int idCampanha) throws Exception {
        boolean retorno = false;

        try {
            String sql = "SELECT * " +
                    "FROM CAMPANHA " +
                    "WHERE ID_CAMPANHA = ?";

            DBMySQLServer.STATEMENT.prepareStatement (sql);

            DBMySQLServer.STATEMENT.setInt (1, idCampanha);

            MeuResultSet resultado = (MeuResultSet)DBMySQLServer.STATEMENT.executeQuery ();

            retorno = resultado.first();
        } catch (SQLException erro) {
            throw new Exception ("Erro ao procurar campanha");
        }

        return retorno;
    }

    public static void incluir (
        Campanha campanha,
        List<Integer> tiposSanguineosCampanha
    ) throws Exception {
        if (campanha == null)
            throw new Exception("Campanha não fornecida");
        if (tiposSanguineosCampanha == null)
            throw new Exception("Tipo sanguíneo de campanha não fornecido");

        try {
            String sql = "INSERT INTO CAMPANHA " +
                    "(ID_HEMOCENTRO, DATA_INICIO, DATA_FIM, HORARIO_INICIO, " +
                    "HORARIO_FIM, ID_TEMPO_COLETA, QTD_ATENDIMENTOS_SIMULTANEOS, INCENTIVO, DISPARO_CONTATO_FEITO) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            DBMySQLServer.STATEMENT.prepareStatement(sql);

            DBMySQLServer.STATEMENT.setInt(1, campanha.getIdHemocentro());
            DBMySQLServer.STATEMENT.setDate(2, Date.valueOf(campanha.getDataInicio()));
            DBMySQLServer.STATEMENT.setDate(3, Date.valueOf(campanha.getDataFim()));
            DBMySQLServer.STATEMENT.setString(4, campanha.getHorarioInicio());
            DBMySQLServer.STATEMENT.setString(5, campanha.getHorarioFim());
            DBMySQLServer.STATEMENT.setInt(6, campanha.getIdTempoColeta());
            DBMySQLServer.STATEMENT.setInt(7, campanha.getQtdAtendimentosSimultaneos());
            DBMySQLServer.STATEMENT.setString(8, campanha.getIncentivo());
            DBMySQLServer.STATEMENT.setBoolean(9, campanha.isDisparoContatoFeito());

            DBMySQLServer.STATEMENT.executeUpdate();

            sql = "SELECT LAST_INSERT_ID()";
            DBMySQLServer.STATEMENT.prepareStatement(sql);
            MeuResultSet resultado = (MeuResultSet)DBMySQLServer.STATEMENT.executeQuery();
            resultado.first();                                  // por algum motivo, só funciona com esse comando
            int idCampanha = resultado.getInt(1);

            sql = "INSERT INTO TIPO_SANGUINEO_CAMPANHA " +
                    "(ID_CAMPANHA, ID_TIPO_SANGUINEO) " +
                    "VALUES (?, ?)";
            DBMySQLServer.STATEMENT.prepareStatement(sql);

            for (Integer tsc : tiposSanguineosCampanha) {
                DBMySQLServer.STATEMENT.setInt(1, idCampanha);
                DBMySQLServer.STATEMENT.setInt(2, tsc);
                DBMySQLServer.STATEMENT.executeUpdate();
            }

            DBMySQLServer.STATEMENT.commit();
        } catch (SQLException e) {
            DBMySQLServer.STATEMENT.rollback();
            throw new Exception("Erro ao inserir campanha");
        }
    }

    public static void excluir (int idCampanha) throws Exception {
        if (!isCadastrada (idCampanha))
            throw new Exception ("Campanha nao cadastrada");

        try {
            String sql = "DELETE FROM TIPO_SANGUINEO_CAMPANHA " +
                            "WHERE ID_CAMPANHA=?";

            DBMySQLServer.STATEMENT.prepareStatement (sql);

            DBMySQLServer.STATEMENT.setInt (1, idCampanha);

            DBMySQLServer.STATEMENT.executeUpdate();
            DBMySQLServer.STATEMENT.commit();

            sql = "DELETE FROM CAMPANHA " +
                    "WHERE ID_CAMPANHA=?";

            DBMySQLServer.STATEMENT.prepareStatement (sql);

            DBMySQLServer.STATEMENT.setInt (1, idCampanha);

            DBMySQLServer.STATEMENT.executeUpdate();
            DBMySQLServer.STATEMENT.commit();
        } catch (SQLException erro) {
            DBMySQLServer.STATEMENT.rollback();
            throw new Exception ("Erro ao excluir campanha");
        }
    }

    public static void atualizar (
        int idCampanha,
        Campanha campanha,
        List<Integer> tiposSanguineosCampanha
    ) throws Exception {
        if (campanha == null)
            throw new Exception ("Campanha nao fornecida");

        if (!isCadastrada (idCampanha))
            throw new Exception ("Campanha nao cadastrada");

        try {
            String sql = "UPDATE CAMPANHA " +
                    "SET ID_HEMOCENTRO=?, DATA_INICIO=?, " +
                    "DATA_FIM=?, HORARIO_INICIO=?, " +
                    "HORARIO_FIM=?, ID_TEMPO_COLETA=?, " +
                    "QTD_ATENDIMENTOS_SIMULTANEOS=?, INCENTIVO=?, " +
                    "DISPARO_CONTATO_FEITO=? " +
                    "WHERE ID_CAMPANHA = ?";

            DBMySQLServer.STATEMENT.prepareStatement (sql);

            DBMySQLServer.STATEMENT.setInt(1, campanha.getIdHemocentro());
            DBMySQLServer.STATEMENT.setDate(2, Date.valueOf(campanha.getDataInicio()));
            DBMySQLServer.STATEMENT.setDate(3, Date.valueOf(campanha.getDataFim()));
            DBMySQLServer.STATEMENT.setString(4, campanha.getHorarioInicio());
            DBMySQLServer.STATEMENT.setString(5, campanha.getHorarioFim());
            DBMySQLServer.STATEMENT.setInt(6, campanha.getIdTempoColeta());
            DBMySQLServer.STATEMENT.setInt(7, campanha.getQtdAtendimentosSimultaneos());
            DBMySQLServer.STATEMENT.setString(8, campanha.getIncentivo());
            DBMySQLServer.STATEMENT.setBoolean(9, campanha.isDisparoContatoFeito());
            DBMySQLServer.STATEMENT.setInt(10, idCampanha);

            DBMySQLServer.STATEMENT.executeUpdate();

            sql = "UPDATE TIPO_SANGUINEO_CAMPANHA " +
                    "SET ID_TIPO_SANGUINEO=? " +
                    "WHERE ID_CAMPANHA=?";
            DBMySQLServer.STATEMENT.prepareStatement(sql);

            for (Integer tsc : tiposSanguineosCampanha) {
                DBMySQLServer.STATEMENT.setInt(1, tsc);
                DBMySQLServer.STATEMENT.setInt(2, idCampanha);
                DBMySQLServer.STATEMENT.executeUpdate();
            }

            DBMySQLServer.STATEMENT.commit();
        } catch (SQLException erro) {
            DBMySQLServer.STATEMENT.rollback();
            throw new Exception ("Erro ao atualizar dados de campanha: " + erro.getMessage());
        }
    }

    public static Campanha obter (int idCampanha) throws Exception {
        Campanha campanha = null;

        try {
            String sql = "SELECT * " +
                    "FROM CAMPANHA " +
                    "WHERE ID_CAMPANHA = ?";

            DBMySQLServer.STATEMENT.prepareStatement(sql);

            DBMySQLServer.STATEMENT.setInt(1, idCampanha);

            MeuResultSet resultado = (MeuResultSet)DBMySQLServer.STATEMENT.executeQuery ();

            if (!resultado.first())
                throw new Exception ("Campanha nao cadastrada");

            campanha = new org.vidamais.DBO.Campanha(
                    resultado.getInt("ID_CAMPANHA"),
                    resultado.getInt("ID_HEMOCENTRO"),
                    resultado.getString("DATA_INICIO"),
                    resultado.getString("DATA_FIM"),
                    resultado.getString("HORARIO_INICIO"),
                    resultado.getString("HORARIO_FIM"),
                    resultado.getInt("ID_TEMPO_COLETA"),
                    resultado.getInt("QTD_ATENDIMENTOS_SIMULTANEOS"),
                    resultado.getString("INCENTIVO"),
                    resultado.getBoolean("DISPARO_CONTATO_FEITO"));
        } catch (SQLException erro) {
            throw new Exception ("Erro ao procurar campanha");
        }

        return campanha;
    }

    public static MeuResultSet obterTiposSanguineos (int idCampanha) throws Exception {
        MeuResultSet resultado = null;

        try {
            String sql = "SELECT * " +
                    "FROM TIPO_SANGUINEO_CAMPANHA " +
                    "WHERE ID_CAMPANHA = ?";

            DBMySQLServer.STATEMENT.prepareStatement(sql);

            DBMySQLServer.STATEMENT.setInt(1, idCampanha);

            resultado = (MeuResultSet)DBMySQLServer.STATEMENT.executeQuery();

            if (!resultado.first())
                throw new Exception ("Campanha nao cadastrada");

        } catch (SQLException erro) {
            throw new Exception ("Erro ao procurar tipos sanguineo de campanha");
        }

        return resultado;
    }

    public static List<Campanha> obter () throws Exception {
        List<Campanha> campanhas = null;

        try {
            String sql = "SELECT * " +
                    "FROM CAMPANHA";

            DBMySQLServer.STATEMENT.prepareStatement (sql);
            MeuResultSet rs = (MeuResultSet)DBMySQLServer.STATEMENT.executeQuery();

            campanhas = new ArrayList<>();
            while (rs.next()) {
                Campanha campanha = new Campanha(
                        rs.getInt("ID_CAMPANHA"),
                        rs.getInt("ID_HEMOCENTRO"),
                        rs.getDate("DATA_INICIO").toString(),
                        rs.getDate("DATA_FIM").toString(),
                        rs.getString("HORARIO_INICIO"),
                        rs.getString("HORARIO_FIM"),
                        rs.getInt("ID_TEMPO_COLETA"),
                        rs.getInt("QTD_ATENDIMENTOS_SIMULTANEOS"),
                        rs.getString("INCENTIVO"),
                        rs.getBoolean("DISPARO_CONTATO_FEITO")
                );
                campanhas.add(campanha);
            }
        } catch (SQLException erro) {
            throw new Exception ("Erro ao recuperar campanhas");
        }

        return campanhas;
    }
}
