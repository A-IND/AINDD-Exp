package de.metanome.algorithm_integration.input;

import java.util.List;

public interface RelationalInput extends AutoCloseable {
  boolean hasNext() throws InputIterationException;
  
  List<String> next() throws InputIterationException;
  
  int numberOfColumns();
  
  String relationName();
  
  List<String> columnNames();
}


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\input\RelationalInput.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */