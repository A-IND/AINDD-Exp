package de.metanome.algorithm_integration;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.Collection;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "type")
public interface Predicate {
  @JsonIgnore
  Collection<ColumnIdentifier> getColumnIdentifiers();
}


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\Predicate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */