package de.metanome.algorithm_integration.algorithm_types;

import de.metanome.algorithm_integration.Algorithm;
import de.metanome.algorithm_integration.AlgorithmConfigurationException;
import de.metanome.algorithm_integration.input.DatabaseConnectionGenerator;

public interface DatabaseConnectionParameterAlgorithm extends Algorithm {
  void setDatabaseConnectionGeneratorConfigurationValue(String paramString, DatabaseConnectionGenerator... paramVarArgs) throws AlgorithmConfigurationException;
}


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\algorithm_types\DatabaseConnectionParameterAlgorithm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */