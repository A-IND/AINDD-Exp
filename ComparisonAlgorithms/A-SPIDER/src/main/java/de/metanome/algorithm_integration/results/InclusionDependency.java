/*     */ package de.metanome.algorithm_integration.results;
/*     */ 
/*     */ import com.fasterxml.jackson.annotation.JsonTypeName;
/*     */ import de.metanome.algorithm_integration.ColumnPermutation;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ @JsonTypeName("InclusionDependency")
/*     */ public class InclusionDependency
/*     */   implements Result
/*     */ {
/*     */   public static final String IND_SEPARATOR = "[=";
/*     */   public static final String IND_SEPARATOR_ESC = "\\[=";
/*     */   private static final long serialVersionUID = -760072975848083178L;
/*     */   protected ColumnPermutation dependant;
/*     */   protected ColumnPermutation referenced;
/*     */   
/*     */   protected InclusionDependency() {
/*  48 */     this.referenced = new ColumnPermutation();
/*  49 */     this.dependant = new ColumnPermutation();
/*     */   }
/*     */ 
/*     */   
/*     */   public InclusionDependency(ColumnPermutation dependant, ColumnPermutation referenced) {
/*  54 */     this.dependant = dependant;
/*  55 */     this.referenced = referenced;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColumnPermutation getDependant() {
/*  62 */     return this.dependant;
/*     */   }
/*     */   
/*     */   public void setDependant(ColumnPermutation dependant) {
/*  66 */     this.dependant = dependant;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColumnPermutation getReferenced() {
/*  73 */     return this.referenced;
/*     */   }
/*     */   
/*     */   public void setReferenced(ColumnPermutation referenced) {
/*  77 */     this.referenced = referenced;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @XmlTransient
/*     */   public void sendResultTo(OmniscientResultReceiver resultReceiver) throws CouldNotReceiveResultException, ColumnNameMismatchException {
/*  84 */     resultReceiver.receiveResult(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  89 */     return this.dependant.toString() + "[=" + this.referenced.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString(Map<String, String> tableMapping, Map<String, String> columnMapping) {
/*  99 */     return this.dependant.toString(tableMapping, columnMapping) + "[=" + this.referenced.toString(tableMapping, columnMapping);
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
/*     */   public static InclusionDependency fromString(Map<String, String> tableMapping, Map<String, String> columnMapping, String str) throws NullPointerException, IndexOutOfBoundsException {
/* 111 */     String[] parts = str.split("\\[=");
/* 112 */     ColumnPermutation dependant = ColumnPermutation.fromString(tableMapping, columnMapping, parts[0]);
/* 113 */     ColumnPermutation referenced = ColumnPermutation.fromString(tableMapping, columnMapping, parts[1]);
/*     */     
/* 115 */     return new InclusionDependency(dependant, referenced);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 120 */     int prime = 31;
/* 121 */     int result = 1;
/*     */     
/* 123 */     result = 31 * result + ((this.dependant == null) ? 0 : this.dependant.hashCode());
/*     */     
/* 125 */     result = 31 * result + ((this.referenced == null) ? 0 : this.referenced.hashCode());
/* 126 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 131 */     if (this == obj) {
/* 132 */       return true;
/*     */     }
/* 134 */     if (obj == null) {
/* 135 */       return false;
/*     */     }
/* 137 */     if (getClass() != obj.getClass()) {
/* 138 */       return false;
/*     */     }
/* 140 */     InclusionDependency other = (InclusionDependency)obj;
/* 141 */     if (this.dependant == null) {
/* 142 */       if (other.dependant != null) {
/* 143 */         return false;
/*     */       }
/* 145 */     } else if (!this.dependant.equals(other.dependant)) {
/* 146 */       return false;
/*     */     } 
/* 148 */     if (this.referenced == null) {
/* 149 */       if (other.referenced != null) {
/* 150 */         return false;
/*     */       }
/* 152 */     } else if (!this.referenced.equals(other.referenced)) {
/* 153 */       return false;
/*     */     } 
/* 155 */     return true;
/*     */   }
/*     */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\results\InclusionDependency.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */