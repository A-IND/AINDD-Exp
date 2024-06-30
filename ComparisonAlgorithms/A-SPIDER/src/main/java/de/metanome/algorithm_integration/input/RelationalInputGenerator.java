package de.metanome.algorithm_integration.input;

import de.metanome.algorithm_integration.AlgorithmConfigurationException;

public interface RelationalInputGenerator extends AutoCloseable {
  RelationalInput generateNewCopy() throws InputGenerationException, AlgorithmConfigurationException;
}


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\input\RelationalInputGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */