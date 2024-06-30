package de.metanome.algorithm_integration.result_receiver;

import de.metanome.algorithm_integration.results.ConditionalUniqueColumnCombination;

public interface ConditionalUniqueColumnCombinationResultReceiver {
  void receiveResult(ConditionalUniqueColumnCombination paramConditionalUniqueColumnCombination) throws CouldNotReceiveResultException, ColumnNameMismatchException;
  
  Boolean acceptedResult(ConditionalUniqueColumnCombination paramConditionalUniqueColumnCombination);
}


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\result_receiver\ConditionalUniqueColumnCombinationResultReceiver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */