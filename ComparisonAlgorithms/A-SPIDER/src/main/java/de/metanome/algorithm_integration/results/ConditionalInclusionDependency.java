/*     */ package de.metanome.algorithm_integration.results;
/*     */ 
/*     */ import com.fasterxml.jackson.annotation.JsonTypeName;
/*     */ import de.metanome.algorithm_integration.ColumnPermutation;
/*     */ import java.util.Map;
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
/*     */ @JsonTypeName("ConditionalInclusionDependency")
/*     */ public class ConditionalInclusionDependency
/*     */   extends InclusionDependency
/*     */   implements Result
/*     */ {
/*     */   public static final String TABLEAU_SEPARATOR = "#";
/*     */   private static final long serialVersionUID = 7828486818686878686L;
/*     */   protected String patternTableau;
/*     */   
/*     */   protected ConditionalInclusionDependency() {
/*  41 */     this.patternTableau = "";
/*     */   }
/*     */   
/*     */   public ConditionalInclusionDependency(ColumnPermutation dependant, ColumnPermutation referenced, String patternTableau) {
/*  45 */     super(dependant, referenced);
/*  46 */     this.patternTableau = patternTableau;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  51 */     return super.toString() + "#" + this.patternTableau;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString(Map<String, String> tableMapping, Map<String, String> columnMapping) {
/*  61 */     return super.toString(tableMapping, columnMapping) + "#" + this.patternTableau;
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
/*     */   public static ConditionalInclusionDependency fromString(Map<String, String> tableMapping, Map<String, String> columnMapping, String str) throws NullPointerException, IndexOutOfBoundsException {
/*  73 */     String[] parts = str.split("\\[=");
/*  74 */     ColumnPermutation dependant = ColumnPermutation.fromString(tableMapping, columnMapping, parts[0]);
/*  75 */     parts = parts[1].split("#");
/*  76 */     ColumnPermutation referenced = ColumnPermutation.fromString(tableMapping, columnMapping, parts[0]);
/*  77 */     String patternTableau = parts[1];
/*     */     
/*  79 */     return new ConditionalInclusionDependency(dependant, referenced, patternTableau);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/*  84 */     if (this == obj) {
/*  85 */       return true;
/*     */     }
/*  87 */     if (obj == null || getClass() != obj.getClass()) {
/*  88 */       return false;
/*     */     }
/*  90 */     ConditionalInclusionDependency other = (ConditionalInclusionDependency)obj;
/*  91 */     if ((this.dependant != null) ? !this.dependant.equals(other.dependant) : (other.dependant != null)) return false; 
/*  92 */     if ((this.referenced != null) ? !this.referenced.equals(other.referenced) : (other.referenced != null)) return false; 
/*  93 */     return (this.patternTableau != null) ? this.patternTableau.equals(other.patternTableau) : ((other.patternTableau == null));
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  98 */     int prime = 31;
/*  99 */     int result = 1;
/*     */     
/* 101 */     result = 31 * result + ((this.dependant == null) ? 0 : this.dependant.hashCode());
/*     */     
/* 103 */     result = 31 * result + ((this.referenced == null) ? 0 : this.referenced.hashCode());
/* 104 */     return result;
/*     */   }
/*     */   
/*     */   public String getPatternTableau() {
/* 108 */     return this.patternTableau;
/*     */   }
/*     */   
/*     */   public void setPatternTableau(String patternTableau) {
/* 112 */     this.patternTableau = patternTableau;
/*     */   }
/*     */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\results\ConditionalInclusionDependency.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */