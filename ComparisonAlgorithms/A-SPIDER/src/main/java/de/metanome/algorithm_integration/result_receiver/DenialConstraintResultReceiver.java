package de.metanome.algorithm_integration.result_receiver;

import de.metanome.algorithm_integration.results.DenialConstraint;

public interface DenialConstraintResultReceiver {
  void receiveResult(DenialConstraint paramDenialConstraint) throws CouldNotReceiveResultException, ColumnNameMismatchException;
  
  Boolean acceptedResult(DenialConstraint paramDenialConstraint);
}


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\result_receiver\DenialConstraintResultReceiver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */