package org.example;

import java.sql.*;

public class enviaCadastroDeCampanha {
    public static void inserirDados(String tipoSanguineo , String regiaoColeta ,Date dataInicio ,Date dataFim,
                                    String horarioPrimeiraColeta,String horarioUltimaColeta,String tempoColeta,
                                    String quantidadeAtendimentos,String incentivoCheckbox,String descricaoIncentivo) {

        // Configuração da conexão
        String url = "jdbc:mysql://localhost:3306/hemocentro_mais";
        String user = "root";
        String password = "Admin2024#";

        // Conectar ao banco de dados
        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            // Consulta SQL para inserir dados
            String query = "INSERT INTO campanhas (\n" +
                    "    tipo_sangue, regiao, data_inicio, data_fim, horario_inicio, horario_fim, \n" +
                    "    tempo_coleta, atendimentos_simultaneos, existe_incentivo, descricao_incentivo\n" +
                    ") VALUES (?, ?, ? , ?, ? , ? , ? , ? , ? , ?)";

            // Preparar o Statement
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                // Definir os valores dos parâmetros
                stmt.setString(1, tipoSanguineo);
                stmt.setString(2, regiaoColeta);
                stmt.setDate(3, dataInicio);
                stmt.setDate(4, dataFim);
                stmt.setTime(5, Time.valueOf(horarioPrimeiraColeta + ":00"));
                stmt.setTime(6, Time.valueOf(horarioUltimaColeta + ":00"));
                stmt.setString(7, tempoColeta);
                stmt.setInt(8, Integer.parseInt(quantidadeAtendimentos));
                stmt.setBoolean(9, Boolean.parseBoolean(incentivoCheckbox));
                stmt.setString(10, descricaoIncentivo);


                // Executar a inserção
                int rowsAffected = stmt.executeUpdate();
                System.out.println(rowsAffected + " linha(s) inserida(s) com sucesso!");

            } catch (SQLException e) {
                System.out.println("Erro ao inserir dados: " + e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println("Erro de conexão: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

    }

}
