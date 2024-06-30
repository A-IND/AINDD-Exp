/*    */ package de.metanome.algorithm_integration.results.basic_statistic_values;
/*    */ 
/*    */ import com.fasterxml.jackson.annotation.JsonTypeName;
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
/*    */ @JsonTypeName("BasicStatisticValueInteger")
/*    */ public class BasicStatisticValueInteger
/*    */   extends BasicStatisticValue<Integer>
/*    */ {
/*    */   public BasicStatisticValueInteger() {}
/*    */   
/*    */   public BasicStatisticValueInteger(Integer value) {
/* 32 */     super(value);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 37 */     return this.value.toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 42 */     return this.value.hashCode();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 47 */     return (obj instanceof BasicStatisticValueInteger && this.value.equals(((BasicStatisticValueInteger)obj).getValue()));
/*    */   }
/*    */ 
/*    */   
/*    */   public int compareTo(Object o) {
/* 52 */     if (o instanceof BasicStatisticValueInteger) {
/* 53 */       BasicStatisticValueInteger other = (BasicStatisticValueInteger)o;
/* 54 */       return other.getValue().compareTo(this.value);
/*    */     } 
/* 56 */     return -1;
/*    */   }
/*    */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\results\basic_statistic_values\BasicStatisticValueInteger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */