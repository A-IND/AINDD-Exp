/*    */ package de.metanome.algorithm_integration.results.basic_statistic_values;
/*    */ 
/*    */ import com.fasterxml.jackson.annotation.JsonTypeName;
/*    */ import com.google.common.base.Joiner;
/*    */ import java.util.List;
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
/*    */ @JsonTypeName("BasicStatisticValueStringList")
/*    */ public class BasicStatisticValueStringList
/*    */   extends BasicStatisticValue<List<String>>
/*    */ {
/*    */   public BasicStatisticValueStringList() {}
/*    */   
/*    */   public BasicStatisticValueStringList(List<String> value) {
/* 35 */     super(value);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 40 */     return Joiner.on(",").join(this.value);
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 45 */     return this.value.hashCode();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 50 */     return (obj instanceof BasicStatisticValueStringList && this.value.equals(((BasicStatisticValueStringList)obj).getValue()));
/*    */   }
/*    */ 
/*    */   
/*    */   public int compareTo(Object o) {
/* 55 */     if (o instanceof BasicStatisticValueStringList) {
/* 56 */       BasicStatisticValueStringList other = (BasicStatisticValueStringList)o;
/* 57 */       if (other.getValue().containsAll(this.value) && other.getValue().containsAll(this.value)) {
/* 58 */         return 0;
/*    */       }
/* 60 */       if (other.getValue().containsAll(this.value) && !other.getValue().containsAll(this.value)) {
/* 61 */         return -1;
/*    */       }
/* 63 */       if (!other.getValue().containsAll(this.value) && other.getValue().containsAll(this.value)) {
/* 64 */         return 1;
/*    */       }
/*    */     } 
/* 67 */     return -1;
/*    */   }
/*    */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\results\basic_statistic_values\BasicStatisticValueStringList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */