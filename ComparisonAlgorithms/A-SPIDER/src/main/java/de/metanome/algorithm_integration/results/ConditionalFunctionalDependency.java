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
/*     */ @JsonTypeName("ConditionalFunctionalDependency")
/*     */ public class ConditionalFunctionalDependency
/*     */   implements Result
/*     */ {
/*     */   public static final String FD_SEPARATOR = "->";
/*     */   public static final String TABLEAU_SEPARATOR = "#";
/*     */   private static final long serialVersionUID = 7625466610666776666L;
/*     */   protected ColumnCombination determinant;
/*     */   protected ColumnIdentifier dependant;
/*     */   protected String patternTableau;
/*     */   
/*     */   public ConditionalFunctionalDependency() {
/*  46 */     this.dependant = new ColumnIdentifier();
/*  47 */     this.determinant = new ColumnCombination();
/*  48 */     this.patternTableau = "";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ConditionalFunctionalDependency(ColumnCombination determinant, ColumnIdentifier dependant, String patternTableau) {
/*  54 */     this.determinant = determinant;
/*  55 */     this.dependant = dependant;
/*  56 */     this.patternTableau = patternTableau;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColumnCombination getDeterminant() {
/*  63 */     return this.determinant;
/*     */   }
/*     */   
/*     */   public void setDependant(ColumnIdentifier dependant) {
/*  67 */     this.dependant = dependant;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColumnIdentifier getDependant() {
/*  74 */     return this.dependant;
/*     */   }
/*     */   
/*     */   public void setDeterminant(ColumnCombination determinant) {
/*  78 */     this.determinant = determinant;
/*     */   }
/*     */   
/*     */   public String getPatternTableau() {
/*  82 */     return this.patternTableau;
/*     */   }
/*     */   
/*     */   public void setPatternTableau(String patternTableau) {
/*  86 */     this.patternTableau = patternTableau;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @XmlTransient
/*     */   public void sendResultTo(OmniscientResultReceiver resultReceiver) throws CouldNotReceiveResultException, ColumnNameMismatchException {
/*  93 */     resultReceiver.receiveResult(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  98 */     return this.determinant.toString() + "->" + this.dependant.toString() + "#" + this.patternTableau;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString(Map<String, String> tableMapping, Map<String, String> columnMapping) {
/* 108 */     return this.determinant.toString(tableMapping, columnMapping) + "->" + this.dependant
/* 109 */       .toString(tableMapping, columnMapping) + "#" + this.patternTableau;
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
/*     */   public static ConditionalFunctionalDependency fromString(Map<String, String> tableMapping, Map<String, String> columnMapping, String str) throws NullPointerException, IndexOutOfBoundsException {
/* 121 */     String[] parts = str.split("->");
/* 122 */     ColumnCombination determinant = ColumnCombination.fromString(tableMapping, columnMapping, parts[0]);
/* 123 */     parts = parts[1].split("#");
/* 124 */     ColumnIdentifier dependant = ColumnIdentifier.fromString(tableMapping, columnMapping, parts[0]);
/* 125 */     String patternTableau = parts[1];
/*     */     
/* 127 */     return new ConditionalFunctionalDependency(determinant, dependant, patternTableau);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 132 */     if (this == o) return true; 
/* 133 */     if (o == null || getClass() != o.getClass()) return false;
/*     */     
/* 135 */     ConditionalFunctionalDependency that = (ConditionalFunctionalDependency)o;
/*     */     
/* 137 */     if ((this.determinant != null) ? !this.determinant.equals(that.determinant) : (that.determinant != null)) return false; 
/* 138 */     if ((this.dependant != null) ? !this.dependant.equals(that.dependant) : (that.dependant != null)) return false; 
/* 139 */     return (this.patternTableau != null) ? this.patternTableau.equals(that.patternTableau) : ((that.patternTableau == null));
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 144 */     int result = (this.determinant != null) ? this.determinant.hashCode() : 0;
/* 145 */     result = 31 * result + ((this.dependant != null) ? this.dependant.hashCode() : 0);
/* 146 */     result = 31 * result + ((this.patternTableau != null) ? this.patternTableau.hashCode() : 0);
/* 147 */     return result;
/*     */   }
/*     */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\results\ConditionalFunctionalDependency.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */