/*     */ package de.metanome.algorithm_integration.results;
/*     */ 
/*     */ import com.fasterxml.jackson.annotation.JsonTypeName;
/*     */ import de.metanome.algorithm_integration.ColumnCombination;
/*     */ import de.metanome.algorithm_integration.ColumnIdentifier;
/*     */ import de.metanome.algorithm_integration.result_receiver.ColumnNameMismatchException;
/*     */ import de.metanome.algorithm_integration.result_receiver.CouldNotReceiveResultException;
/*     */ import de.metanome.algorithm_integration.result_receiver.OmniscientResultReceiver;
/*     */ import de.metanome.algorithm_integration.results.basic_statistic_values.BasicStatisticValue;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javax.xml.bind.annotation.XmlTransient;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @JsonTypeName("BasicStatistic")
/*     */ public class BasicStatistic
/*     */   implements Result
/*     */ {
/*     */   public static final String NAME_COLUMN_SEPARATOR = " - ";
/*     */   public static final String COLUMN_VALUE_SEPARATOR = ": ";
/*     */   public static final String STATISTIC_SEPARATOR = "; ";
/*     */   private static final long serialVersionUID = -8010850754433867718L;
/*     */   protected ColumnCombination columnCombination;
/*     */   protected Map<String, BasicStatisticValue> statisticMap;
/*     */   
/*     */   protected BasicStatistic() {
/*  50 */     this.columnCombination = new ColumnCombination();
/*  51 */     this.statisticMap = new HashMap<>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BasicStatistic(ColumnIdentifier... columnIdentifier) {
/*  59 */     this.columnCombination = new ColumnCombination(columnIdentifier);
/*  60 */     this.statisticMap = new HashMap<>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BasicStatistic(Map<String, BasicStatisticValue> statisticMap, ColumnIdentifier... columnIdentifier) {
/*  69 */     this.columnCombination = new ColumnCombination(columnIdentifier);
/*  70 */     this.statisticMap = statisticMap;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addStatistic(String statisticName, BasicStatisticValue statisticValue) {
/*  79 */     this.statisticMap.put(statisticName, statisticValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColumnCombination getColumnCombination() {
/*  86 */     return this.columnCombination;
/*     */   }
/*     */   
/*     */   public void setColumnCombination(ColumnCombination columnCombination) {
/*  90 */     this.columnCombination = columnCombination;
/*     */   }
/*     */   
/*     */   public Map<String, BasicStatisticValue> getStatisticMap() {
/*  94 */     return this.statisticMap;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStatisticMap(Map<String, BasicStatisticValue> statisticMap) {
/* 102 */     this.statisticMap = statisticMap;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @XmlTransient
/*     */   public void sendResultTo(OmniscientResultReceiver resultReceiver) throws CouldNotReceiveResultException, ColumnNameMismatchException {
/* 109 */     resultReceiver.receiveResult(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 114 */     String str = this.columnCombination.toString() + ": ";
/*     */     
/* 116 */     for (Map.Entry<String, BasicStatisticValue> entry : this.statisticMap.entrySet()) {
/* 117 */       str = str + (String)entry.getKey() + " - " + entry.getValue() + "; ";
/*     */     }
/*     */     
/* 120 */     return str;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 125 */     int prime = 31;
/* 126 */     int result = 1;
/* 127 */     result = 31 * result + ((this.columnCombination == null) ? 0 : this.columnCombination.hashCode());
/*     */     
/* 129 */     result = 31 * result + (this.statisticMap.isEmpty() ? 0 : this.statisticMap.hashCode());
/* 130 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 135 */     if (this == obj) {
/* 136 */       return true;
/*     */     }
/* 138 */     if (obj == null) {
/* 139 */       return false;
/*     */     }
/* 141 */     if (getClass() != obj.getClass()) {
/* 142 */       return false;
/*     */     }
/* 144 */     BasicStatistic other = (BasicStatistic)obj;
/* 145 */     if (this.columnCombination == null) {
/* 146 */       if (other.columnCombination != null) {
/* 147 */         return false;
/*     */       }
/* 149 */     } else if (!this.columnCombination.equals(other.columnCombination)) {
/* 150 */       return false;
/*     */     } 
/* 152 */     if (!this.statisticMap.equals(other.statisticMap)) {
/* 153 */       return false;
/*     */     }
/* 155 */     return true;
/*     */   }
/*     */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\results\BasicStatistic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */