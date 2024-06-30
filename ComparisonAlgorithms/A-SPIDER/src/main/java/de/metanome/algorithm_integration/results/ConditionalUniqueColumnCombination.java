/*     */ package de.metanome.algorithm_integration.results;
/*     */ 
/*     */ import com.fasterxml.jackson.annotation.JsonTypeName;
/*     */ import de.metanome.algorithm_integration.ColumnCombination;
/*     */ import de.metanome.algorithm_integration.ColumnCondition;
/*     */ import de.metanome.algorithm_integration.ColumnConditionValue;
/*     */ import de.metanome.algorithm_integration.ColumnIdentifier;
/*     */ import de.metanome.algorithm_integration.result_receiver.ColumnNameMismatchException;
/*     */ import de.metanome.algorithm_integration.result_receiver.CouldNotReceiveResultException;
/*     */ import de.metanome.algorithm_integration.result_receiver.OmniscientResultReceiver;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.TreeSet;
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
/*     */ 
/*     */ @JsonTypeName("ConditionalUniqueColumnCombination")
/*     */ public class ConditionalUniqueColumnCombination
/*     */   implements Result
/*     */ {
/*     */   public static final String LINESEPARATOR = "\n";
/*     */   public static final String CUCC_SEPARATOR = " | ";
/*     */   private static final long serialVersionUID = -7793914512848983094L;
/*     */   protected ColumnCombination columnCombination;
/*     */   protected ColumnCondition condition;
/*     */   
/*     */   protected ConditionalUniqueColumnCombination() {
/*  52 */     this.columnCombination = new ColumnCombination();
/*  53 */     this.condition = (ColumnCondition)new ColumnConditionValue(new ColumnIdentifier("", ""), "");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConditionalUniqueColumnCombination(ColumnCombination columnCombination, ColumnCondition columnCondition) {
/*  65 */     this.columnCombination = columnCombination;
/*  66 */     this.condition = columnCondition;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColumnCombination getColumnCombination() {
/*  74 */     return this.columnCombination;
/*     */   }
/*     */   
/*     */   public void setColumnCombination(ColumnCombination columnCombination) {
/*  78 */     this.columnCombination = columnCombination;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColumnCondition getCondition() {
/*  85 */     return this.condition;
/*     */   }
/*     */   
/*     */   public void setCondition(ColumnCondition condition) {
/*  89 */     this.condition = condition;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @XmlTransient
/*     */   public void sendResultTo(OmniscientResultReceiver resultReceiver) throws CouldNotReceiveResultException, ColumnNameMismatchException {
/*  96 */     resultReceiver.receiveResult(this);
/*     */   }
/*     */   
/*     */   public String buildPatternTableauTable() {
/* 100 */     StringBuilder builder = new StringBuilder();
/* 101 */     TreeSet<ColumnIdentifier> patternTableauHead = this.condition.getContainedColumns();
/* 102 */     builder.append(patternTableauHead);
/*     */     
/* 104 */     List<Map<ColumnIdentifier, String>> conditions = this.condition.getPatternConditions();
/* 105 */     for (Map<ColumnIdentifier, String> condition : conditions) {
/* 106 */       builder.append("\n");
/* 107 */       for (ColumnIdentifier column : patternTableauHead) {
/* 108 */         if (condition.containsKey(column)) {
/* 109 */           String value = condition.get(column);
/* 110 */           if (value.length() < column.toString().length()) {
/* 111 */             StringBuilder stringBuilder = new StringBuilder();
/* 112 */             for (int j = 0; j < (column.toString().length() - value.length()) / 2; j++) {
/* 113 */               stringBuilder.append(" ");
/*     */             }
/* 115 */             builder.append(stringBuilder);
/* 116 */             builder.append(value);
/* 117 */             builder.append(stringBuilder); continue;
/*     */           } 
/* 119 */           builder.append(value);
/* 120 */           builder.append(" ");
/*     */           continue;
/*     */         } 
/* 123 */         StringBuilder whitespaceBuilder = new StringBuilder();
/* 124 */         for (int i = 0; i < column.toString().length() / 2; i++) {
/* 125 */           whitespaceBuilder.append(" ");
/*     */         }
/* 127 */         builder.append(whitespaceBuilder);
/* 128 */         builder.append("-");
/* 129 */         builder.append(whitespaceBuilder);
/*     */       } 
/*     */     } 
/*     */     
/* 133 */     return builder.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 138 */     StringBuilder builder = new StringBuilder();
/* 139 */     builder.append(this.columnCombination.toString());
/* 140 */     builder.append(" | ");
/* 141 */     builder.append(this.condition.toString());
/* 142 */     builder.append(" Coverage: ");
/* 143 */     builder.append(this.condition.getCoverage());
/* 144 */     return builder.toString();
/*     */   }
/*     */   
/*     */   public String buildPatternTableau() {
/* 148 */     StringBuilder builder = new StringBuilder();
/* 149 */     builder.append(this.columnCombination.toString());
/* 150 */     builder.append("\n");
/*     */     
/* 152 */     builder.append(buildPatternTableauTable());
/*     */     
/* 154 */     builder.append("\n");
/* 155 */     builder.append("Coverage: ");
/* 156 */     builder.append(this.condition.getCoverage());
/* 157 */     builder.append("\n");
/* 158 */     return builder.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 163 */     if (this == o) {
/* 164 */       return true;
/*     */     }
/* 166 */     if (o == null || getClass() != o.getClass()) {
/* 167 */       return false;
/*     */     }
/*     */     
/* 170 */     ConditionalUniqueColumnCombination that = (ConditionalUniqueColumnCombination)o;
/*     */     
/* 172 */     if ((this.columnCombination != null) ? !this.columnCombination.equals(that.columnCombination) : (that.columnCombination != null))
/*     */     {
/* 174 */       return false;
/*     */     }
/* 176 */     if ((this.condition != null) ? !this.condition.equals(that.condition) : (that.condition != null)) {
/* 177 */       return false;
/*     */     }
/*     */     
/* 180 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 185 */     int result = (this.columnCombination != null) ? this.columnCombination.hashCode() : 0;
/* 186 */     result = 31 * result + ((this.condition != null) ? this.condition.hashCode() : 0);
/* 187 */     return result;
/*     */   }
/*     */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\results\ConditionalUniqueColumnCombination.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */