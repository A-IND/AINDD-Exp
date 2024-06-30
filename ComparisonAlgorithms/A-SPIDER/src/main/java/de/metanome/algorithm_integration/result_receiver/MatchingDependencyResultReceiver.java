package de.metanome.algorithm_integration.result_receiver;

import de.metanome.algorithm_integration.results.MatchingDependency;

public interface MatchingDependencyResultReceiver {
  void receiveResult(MatchingDependency paramMatchingDependency) throws CouldNotReceiveResultException, ColumnNameMismatchException;
  
  Boolean acceptedResult(MatchingDependency paramMatchingDependency);
}


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\result_receiver\MatchingDependencyResultReceiver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */