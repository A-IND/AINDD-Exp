/*     */ package de.metanome.algorithm_integration.results;
/*     */ 
/*     */ import com.fasterxml.jackson.annotation.JsonTypeName;
/*     */ import de.metanome.algorithm_integration.ColumnCombination;
/*     */ import de.metanome.algorithm_integration.ColumnIdentifier;
/*     */ import de.metanome.algorithm_integration.result_receiver.ColumnNameMismatchException;
/*     */ import de.metanome.algorithm_integration.result_receiver.CouldNotReceiveResultException;
/*     */ import de.metanome.algorithm_integration.result_receiver.OmniscientResultReceiver;
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
/*     */ @JsonTypeName("UniqueColumnCombination")
/*     */ public class UniqueColumnCombination
/*     */   implements Result
/*     */ {
/*     */   private static final long serialVersionUID = -8782723135088616653L;
/*     */   protected ColumnCombination columnCombination;
/*     */   
/*     */   protected UniqueColumnCombination() {
/*  43 */     this.columnCombination = new ColumnCombination();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UniqueColumnCombination(ColumnIdentifier... columnIdentifier) {
/*  52 */     this.columnCombination = new ColumnCombination(columnIdentifier);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UniqueColumnCombination(ColumnCombination columnCombination) {
/*  61 */     this.columnCombination = columnCombination;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColumnCombination getColumnCombination() {
/*  68 */     return this.columnCombination;
/*     */   }
/*     */   
/*     */   public void setColumnCombination(ColumnCombination columnCombination) {
/*  72 */     this.columnCombination = columnCombination;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @XmlTransient
/*     */   public void sendResultTo(OmniscientResultReceiver resultReceiver) throws CouldNotReceiveResultException, ColumnNameMismatchException {
/*  79 */     resultReceiver.receiveResult(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  84 */     return this.columnCombination.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString(Map<String, String> tableMapping, Map<String, String> columnMapping) {
/*  94 */     return this.columnCombination.toString(tableMapping, columnMapping);
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
/*     */   public static UniqueColumnCombination fromString(Map<String, String> tableMapping, Map<String, String> columnMapping, String str) throws NullPointerException, IndexOutOfBoundsException {
/* 106 */     return new UniqueColumnCombination(ColumnCombination.fromString(tableMapping, columnMapping, str));
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 111 */     int prime = 31;
/* 112 */     int result = 1;
/*     */ 
/*     */ 
/*     */     
/* 116 */     result = 31 * result + ((this.columnCombination == null) ? 0 : this.columnCombination.hashCode());
/* 117 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 122 */     if (this == obj) {
/* 123 */       return true;
/*     */     }
/* 125 */     if (obj == null) {
/* 126 */       return false;
/*     */     }
/* 128 */     if (getClass() != obj.getClass()) {
/* 129 */       return false;
/*     */     }
/* 131 */     UniqueColumnCombination other = (UniqueColumnCombination)obj;
/* 132 */     if (this.columnCombination == null) {
/* 133 */       if (other.columnCombination != null) {
/* 134 */         return false;
/*     */       }
/* 136 */     } else if (!this.columnCombination.equals(other.columnCombination)) {
/* 137 */       return false;
/*     */     } 
/* 139 */     return true;
/*     */   }
/*     */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\results\UniqueColumnCombination.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */