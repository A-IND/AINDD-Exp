package de.metanome.algorithm_integration.configuration;

import de.metanome.algorithm_integration.Algorithm;
import de.metanome.algorithm_integration.AlgorithmConfigurationException;
import java.util.Set;

public interface ConfigurationValue {
  void triggerSetValue(Algorithm paramAlgorithm, Set<Class<?>> paramSet) throws AlgorithmConfigurationException;
}


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\configuration\ConfigurationValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */