/*     */ package de.metanome.algorithm_integration.results;
/*     */ 
/*     */ import com.fasterxml.jackson.annotation.JsonTypeName;
/*     */ import de.metanome.algorithm_integration.MatchingCombination;
/*     */ import de.metanome.algorithm_integration.MatchingIdentifier;
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
/*     */ @JsonTypeName("MatchingDependency")
/*     */ public class MatchingDependency
/*     */   implements Result
/*     */ {
/*     */   static final String MD_SEPARATOR = "->";
/*     */   static final String SUPPORT_SEPARATOR = "§";
/*     */   private static final long serialVersionUID = 7625471410289776666L;
/*     */   private MatchingCombination determinant;
/*     */   private MatchingIdentifier dependant;
/*     */   private long support;
/*     */   
/*     */   public MatchingDependency() {}
/*     */   
/*     */   public MatchingDependency(MatchingCombination determinant, MatchingIdentifier dependant, long support) {
/*  47 */     this.determinant = determinant;
/*  48 */     this.dependant = dependant;
/*  49 */     this.support = support;
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
/*     */ 
/*     */   
/*     */   public static MatchingDependency fromString(Map<String, String> tableMapping, Map<String, String> columnMapping, String str) throws NullPointerException, IndexOutOfBoundsException {
/*  63 */     String[] supportParts = str.split("§");
/*  64 */     long support = Long.parseLong(supportParts[1]);
/*  65 */     String[] parts = supportParts[0].split("->");
/*     */     
/*  67 */     MatchingCombination determinant = MatchingCombination.fromString(tableMapping, columnMapping, parts[0]);
/*     */     
/*  69 */     MatchingIdentifier dependant = MatchingIdentifier.fromString(tableMapping, columnMapping, parts[1]);
/*     */     
/*  71 */     return new MatchingDependency(determinant, dependant, support);
/*     */   }
/*     */   
/*     */   public long getSupport() {
/*  75 */     return this.support;
/*     */   }
/*     */   
/*     */   public void setSupport(long support) {
/*  79 */     this.support = support;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MatchingCombination getDeterminant() {
/*  86 */     return this.determinant;
/*     */   }
/*     */   
/*     */   public void setDeterminant(MatchingCombination determinant) {
/*  90 */     this.determinant = determinant;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MatchingIdentifier getDependant() {
/*  97 */     return this.dependant;
/*     */   }
/*     */   
/*     */   public void setDependant(MatchingIdentifier dependant) {
/* 101 */     this.dependant = dependant;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @XmlTransient
/*     */   public void sendResultTo(OmniscientResultReceiver resultReceiver) throws CouldNotReceiveResultException, ColumnNameMismatchException {
/* 108 */     resultReceiver.receiveResult(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 113 */     return this.determinant.toString() + "->" + this.dependant.toString() + "§" + this.support;
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
/*     */   public String toString(Map<String, String> tableMapping, Map<String, String> columnMapping) {
/* 125 */     return this.determinant.toString(tableMapping, columnMapping) + "->" + this.dependant
/* 126 */       .toString(tableMapping, columnMapping) + "§" + this.support;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 132 */     int prime = 31;
/* 133 */     int result = 1;
/*     */     
/* 135 */     result = 31 * result + ((this.dependant == null) ? 0 : this.dependant.hashCode());
/*     */     
/* 137 */     result = 31 * result + ((this.determinant == null) ? 0 : this.determinant.hashCode());
/*     */     
/* 139 */     result = 31 * result + Long.hashCode(this.support);
/* 140 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 145 */     if (this == obj) {
/* 146 */       return true;
/*     */     }
/* 148 */     if (obj == null) {
/* 149 */       return false;
/*     */     }
/* 151 */     if (getClass() != obj.getClass()) {
/* 152 */       return false;
/*     */     }
/* 154 */     MatchingDependency other = (MatchingDependency)obj;
/* 155 */     if (this.dependant == null) {
/* 156 */       if (other.dependant != null) {
/* 157 */         return false;
/*     */       }
/* 159 */     } else if (!this.dependant.equals(other.dependant)) {
/* 160 */       return false;
/*     */     } 
/* 162 */     if (this.determinant == null) {
/* 163 */       if (other.determinant != null) {
/* 164 */         return false;
/*     */       }
/* 166 */     } else if (!this.determinant.equals(other.determinant)) {
/* 167 */       return false;
/*     */     } 
/* 169 */     return (this.support == other.support);
/*     */   }
/*     */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\results\MatchingDependency.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */