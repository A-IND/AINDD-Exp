package de.metanome.algorithm_integration.input;

import de.metanome.algorithm_integration.AlgorithmConfigurationException;
import java.sql.ResultSet;

public interface TableInputGenerator extends RelationalInputGenerator {
  ResultSet sortBy(String paramString, Boolean paramBoolean) throws InputGenerationException, AlgorithmConfigurationException;
  
  ResultSet filter(String paramString) throws InputGenerationException, AlgorithmConfigurationException;
  
  ResultSet select() throws InputGenerationException, AlgorithmConfigurationException;
  
  DatabaseConnectionGenerator getDatabaseConnectionGenerator();
}


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\input\TableInputGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */