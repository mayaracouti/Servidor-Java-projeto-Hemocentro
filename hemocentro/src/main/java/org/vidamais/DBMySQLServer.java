package org.vidamais;

import org.vidamais.Core.MeuPreparedStatement;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBMySQLServer {

    public static final MeuPreparedStatement STATEMENT;

    static {
        Properties props = new Properties();

        try (FileInputStream fis = new FileInputStream("/Users/joaocarlosmartinsdealmeida/pi4/Servidor-Java-projeto-Hemocentro/db.properties")) {
            props.load(fis);
        } catch (IOException e) {
            System.err.println ("Problemas de acesso as credenciais do BD: " + e.getMessage());
            System.exit(0);
        }

        String dbDrv = props.getProperty("db.drv");
        String dbUrl = props.getProperty("db.url");
        String dbUser = props.getProperty("db.user");
        String dbPassword = props.getProperty("db.password");

        MeuPreparedStatement stmt = null;

        try
        {
            // Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            // stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt = new MeuPreparedStatement (dbDrv, dbUrl, dbUser, dbPassword);
        }
        catch (Exception e)
        {
            System.err.println ("Problemas de conexao com o BD: " + e.getMessage());
            System.exit(0);
        }

        STATEMENT = stmt;
    }
}
