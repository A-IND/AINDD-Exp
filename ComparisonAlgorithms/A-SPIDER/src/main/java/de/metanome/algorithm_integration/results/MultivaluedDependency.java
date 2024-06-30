/*     */ package de.metanome.algorithm_integration.results;
/*     */ 
/*     */ import com.fasterxml.jackson.annotation.JsonTypeName;
/*     */ import de.metanome.algorithm_integration.ColumnCombination;
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
/*     */ @JsonTypeName("MultivaluedDependency")
/*     */ public class MultivaluedDependency
/*     */   implements Result
/*     */ {
/*     */   public static final String MVD_SEPARATOR = "->>";
/*     */   private static final long serialVersionUID = 7625471410289776655L;
/*     */   protected ColumnCombination determinant;
/*     */   protected ColumnCombination dependant;
/*     */   
/*     */   public MultivaluedDependency() {
/*  46 */     this.dependant = new ColumnCombination();
/*  47 */     this.determinant = new ColumnCombination();
/*     */   }
/*     */ 
/*     */   
/*     */   public MultivaluedDependency(ColumnCombination determinant, ColumnCombination dependant) {
/*  52 */     this.determinant = determinant;
/*  53 */     this.dependant = dependant;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColumnCombination getDeterminant() {
/*  60 */     return this.determinant;
/*     */   }
/*     */   
/*     */   public void setDependant(ColumnCombination dependant) {
/*  64 */     this.dependant = dependant;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColumnCombination getDependant() {
/*  71 */     return this.dependant;
/*     */   }
/*     */   
/*     */   public void setDeterminant(ColumnCombination determinant) {
/*  75 */     this.determinant = determinant;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @XmlTransient
/*     */   public void sendResultTo(OmniscientResultReceiver resultReceiver) throws CouldNotReceiveResultException, ColumnNameMismatchException {
/*  82 */     resultReceiver.receiveResult(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  87 */     return this.determinant.toString() + "->>" + this.dependant.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString(Map<String, String> tableMapping, Map<String, String> columnMapping) {
/*  97 */     return this.determinant.toString(tableMapping, columnMapping) + "->>" + this.dependant.toString(tableMapping, columnMapping);
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
/*     */   public static MultivaluedDependency fromString(Map<String, String> tableMapping, Map<String, String> columnMapping, String str) throws NullPointerException, IndexOutOfBoundsException {
/* 109 */     String[] parts = str.split("->>");
/* 110 */     ColumnCombination determinant = ColumnCombination.fromString(tableMapping, columnMapping, parts[0]);
/* 111 */     ColumnCombination dependant = ColumnCombination.fromString(tableMapping, columnMapping, parts[1]);
/*     */ 
/*     */     
/* 114 */     System.out.println("\n" + determinant.toString() + "->>" + dependant.toString() + "\n");
/*     */     
/* 116 */     return new MultivaluedDependency(determinant, dependant);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 122 */     int prime = 31;
/* 123 */     int result = 1;
/*     */     
/* 125 */     result = 31 * result + ((this.dependant == null) ? 0 : this.dependant.hashCode());
/*     */     
/* 127 */     result = 31 * result + ((this.determinant == null) ? 0 : this.determinant.hashCode());
/* 128 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 133 */     if (this == obj) {
/* 134 */       return true;
/*     */     }
/* 136 */     if (obj == null) {
/* 137 */       return false;
/*     */     }
/* 139 */     if (getClass() != obj.getClass()) {
/* 140 */       return false;
/*     */     }
/* 142 */     MultivaluedDependency other = (MultivaluedDependency)obj;
/* 143 */     if (this.dependant == null) {
/* 144 */       if (other.dependant != null) {
/* 145 */         return false;
/*     */       }
/* 147 */     } else if (!this.dependant.equals(other.dependant)) {
/* 148 */       return false;
/*     */     } 
/* 150 */     if (this.determinant == null) {
/* 151 */       if (other.determinant != null) {
/* 152 */         return false;
/*     */       }
/* 154 */     } else if (!this.determinant.equals(other.determinant)) {
/* 155 */       return false;
/*     */     } 
/* 157 */     return true;
/*     */   }
/*     */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\results\MultivaluedDependency.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */