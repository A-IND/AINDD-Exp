package de.metanome.algorithm_integration.input;

import de.metanome.algorithm_integration.AlgorithmConfigurationException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface DatabaseConnectionGenerator extends AutoCloseable {
  RelationalInput generateRelationalInputFromSql(String paramString1, String paramString2) throws InputGenerationException, AlgorithmConfigurationException;
  
  ResultSet generateResultSetFromSql(String paramString) throws InputGenerationException, AlgorithmConfigurationException;
  
  void closeAllStatements() throws SQLException;
  
  Connection getConnection();
}


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\input\DatabaseConnectionGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */