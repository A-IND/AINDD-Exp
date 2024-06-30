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
/*    */ @JsonTypeName("BasicStatisticValueString")
/*    */ public class BasicStatisticValueString
/*    */   extends BasicStatisticValue<String>
/*    */ {
/*    */   public BasicStatisticValueString() {}
/*    */   
/*    */   public BasicStatisticValueString(String value) {
/* 32 */     super(value);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 37 */     return this.value;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 42 */     return this.value.hashCode();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 47 */     return (obj instanceof BasicStatisticValueString && this.value.equals(((BasicStatisticValueString)obj).getValue()));
/*    */   }
/*    */ 
/*    */   
/*    */   public int compareTo(Object o) {
/* 52 */     if (o instanceof BasicStatisticValueString) {
/* 53 */       BasicStatisticValueString other = (BasicStatisticValueString)o;
/* 54 */       return other.getValue().compareTo(this.value);
/*    */     } 
/* 56 */     return -1;
/*    */   }
/*    */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\results\basic_statistic_values\BasicStatisticValueString.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */