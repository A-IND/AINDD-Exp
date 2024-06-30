/*    */ package de.metanome.algorithm_integration.results.basic_statistic_values;
/*    */ 
/*    */ import com.fasterxml.jackson.annotation.JsonSubTypes;
/*    */ import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
/*    */ import com.fasterxml.jackson.annotation.JsonTypeInfo;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
/*    */ @JsonSubTypes({@Type(value = BasicStatisticValueDouble.class, name = "BasicStatisticValueDouble"), @Type(value = BasicStatisticValueFloat.class, name = "BasicStatisticValueFloat"), @Type(value = BasicStatisticValueInteger.class, name = "BasicStatisticValueInteger"), @Type(value = BasicStatisticValueString.class, name = "BasicStatisticValueString"), @Type(value = BasicStatisticValueLong.class, name = "BasicStatisticValueLong"), @Type(value = BasicStatisticValueStringList.class, name = "BasicStatisticValueStringList"), @Type(value = BasicStatisticValueIntegerList.class, name = "BasicStatisticValueIntegerList")})
/*    */ public abstract class BasicStatisticValue<T>
/*    */   implements Comparable<Object>
/*    */ {
/*    */   protected T value;
/*    */   
/*    */   public BasicStatisticValue() {}
/*    */   
/*    */   public BasicStatisticValue(T value) {
/* 47 */     this.value = value;
/*    */   }
/*    */   
/*    */   public T getValue() {
/* 51 */     return this.value;
/*    */   }
/*    */   
/*    */   public void setValue(T value) {
/* 55 */     this.value = value;
/*    */   }
/*    */   
/*    */   public abstract String toString();
/*    */   
/*    */   public abstract int hashCode();
/*    */   
/*    */   public abstract boolean equals(Object paramObject);
/*    */   
/*    */   public abstract int compareTo(Object paramObject);
/*    */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\results\basic_statistic_values\BasicStatisticValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */