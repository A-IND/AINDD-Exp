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
/*     */ @JsonTypeName("FunctionalDependency")
/*     */ public class FunctionalDependency
/*     */   implements Result
/*     */ {
/*     */   public static final String FD_SEPARATOR = "->";
/*     */   private static final long serialVersionUID = 7625471410289776666L;
/*     */   protected ColumnCombination determinant;
/*     */   protected ColumnIdentifier dependant;
/*     */   
/*     */   public FunctionalDependency() {
/*  44 */     this.dependant = new ColumnIdentifier();
/*  45 */     this.determinant = new ColumnCombination();
/*     */   }
/*     */ 
/*     */   
/*     */   public FunctionalDependency(ColumnCombination determinant, ColumnIdentifier dependant) {
/*  50 */     this.determinant = determinant;
/*  51 */     this.dependant = dependant;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColumnCombination getDeterminant() {
/*  58 */     return this.determinant;
/*     */   }
/*     */   
/*     */   public void setDependant(ColumnIdentifier dependant) {
/*  62 */     this.dependant = dependant;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColumnIdentifier getDependant() {
/*  69 */     return this.dependant;
/*     */   }
/*     */   
/*     */   public void setDeterminant(ColumnCombination determinant) {
/*  73 */     this.determinant = determinant;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @XmlTransient
/*     */   public void sendResultTo(OmniscientResultReceiver resultReceiver) throws CouldNotReceiveResultException, ColumnNameMismatchException {
/*  80 */     resultReceiver.receiveResult(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  85 */     return this.determinant.toString() + "->" + this.dependant.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString(Map<String, String> tableMapping, Map<String, String> columnMapping) {
/*  95 */     return this.determinant.toString(tableMapping, columnMapping) + "->" + this.dependant.toString(tableMapping, columnMapping);
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
/*     */   public static FunctionalDependency fromString(Map<String, String> tableMapping, Map<String, String> columnMapping, String str) throws NullPointerException, IndexOutOfBoundsException {
/* 107 */     String[] parts = str.split("->");
/* 108 */     ColumnCombination determinant = ColumnCombination.fromString(tableMapping, columnMapping, parts[0]);
/* 109 */     ColumnIdentifier dependant = ColumnIdentifier.fromString(tableMapping, columnMapping, parts[1]);
/*     */     
/* 111 */     return new FunctionalDependency(determinant, dependant);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 117 */     int prime = 31;
/* 118 */     int result = 1;
/*     */     
/* 120 */     result = 31 * result + ((this.dependant == null) ? 0 : this.dependant.hashCode());
/*     */     
/* 122 */     result = 31 * result + ((this.determinant == null) ? 0 : this.determinant.hashCode());
/* 123 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 128 */     if (this == obj) {
/* 129 */       return true;
/*     */     }
/* 131 */     if (obj == null) {
/* 132 */       return false;
/*     */     }
/* 134 */     if (getClass() != obj.getClass()) {
/* 135 */       return false;
/*     */     }
/* 137 */     FunctionalDependency other = (FunctionalDependency)obj;
/* 138 */     if (this.dependant == null) {
/* 139 */       if (other.dependant != null) {
/* 140 */         return false;
/*     */       }
/* 142 */     } else if (!this.dependant.equals(other.dependant)) {
/* 143 */       return false;
/*     */     } 
/* 145 */     if (this.determinant == null) {
/* 146 */       if (other.determinant != null) {
/* 147 */         return false;
/*     */       }
/* 149 */     } else if (!this.determinant.equals(other.determinant)) {
/* 150 */       return false;
/*     */     } 
/* 152 */     return true;
/*     */   }
/*     */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\results\FunctionalDependency.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */