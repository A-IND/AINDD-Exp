package de.metanome.algorithm_integration;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({@Type(value = ColumnConditionAnd.class, name = "ColumnConditionAnd"), @Type(value = ColumnConditionOr.class, name = "ColumnConditionOr"), @Type(value = ColumnConditionValue.class, name = "ColumnConditionValue")})
public interface ColumnCondition extends Serializable, Comparable<ColumnCondition> {
  public static final String OPEN_BRACKET = "[";
  
  public static final String CLOSE_BRACKET = "]";
  
  public static final String AND = "^";
  
  public static final String OR = "v";
  
  public static final String NOT = "¬";
  
  String toString();
  
  ColumnCondition add(ColumnCondition paramColumnCondition);
  
  float getCoverage();
  
  void setCoverage(float paramFloat);
  
  TreeSet<ColumnIdentifier> getContainedColumns();
  
  List<Map<ColumnIdentifier, String>> getPatternConditions();
  
  boolean isNegated();
  
  void setNegated(boolean paramBoolean);
}


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\ColumnCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */