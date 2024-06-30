/*     */ package de.metanome.algorithm_integration;
/*     */ 
/*     */ import com.google.common.base.Joiner;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
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
/*     */ public class MatchingCombination
/*     */   implements Serializable, Comparable<Object>
/*     */ {
/*     */   public static final String COLUMN_CONNECTOR = ";";
/*     */   private static final long serialVersionUID = -1675606730574675390L;
/*     */   protected Set<MatchingIdentifier> matchingIdentifiers;
/*     */   
/*     */   public MatchingCombination() {
/*  41 */     this.matchingIdentifiers = new TreeSet<>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MatchingCombination(MatchingIdentifier... matchingIdentifiers) {
/*  50 */     this.matchingIdentifiers = new TreeSet<>(Arrays.asList(matchingIdentifiers));
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
/*     */   public static MatchingCombination fromString(Map<String, String> tableMapping, Map<String, String> columnMapping, String str) throws NullPointerException, IndexOutOfBoundsException {
/*  64 */     if (str.isEmpty()) {
/*  65 */       return new MatchingCombination(new MatchingIdentifier[0]);
/*     */     }
/*  67 */     String[] parts = str.split(";");
/*     */     
/*  69 */     MatchingIdentifier[] identifiers = new MatchingIdentifier[parts.length];
/*  70 */     for (int i = 0; i < parts.length; i++) {
/*  71 */       identifiers[i] = MatchingIdentifier.fromString(tableMapping, columnMapping, parts[i].trim());
/*     */     }
/*  73 */     return new MatchingCombination(identifiers);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<MatchingIdentifier> getMatchingIdentifiers() {
/*  82 */     return this.matchingIdentifiers;
/*     */   }
/*     */   
/*     */   public void setMatchingIdentifiers(Set<MatchingIdentifier> identifiers) {
/*  86 */     this.matchingIdentifiers = identifiers;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  91 */     return this.matchingIdentifiers.toString();
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
/*     */   public String toString(Map<String, String> tableMapping, Map<String, String> columnMapping) throws NullPointerException {
/* 103 */     List<String> mis = new ArrayList<>();
/* 104 */     for (MatchingIdentifier mi : this.matchingIdentifiers) {
/* 105 */       mis.add(mi.toString(tableMapping, columnMapping));
/*     */     }
/* 107 */     return Joiner.on(";").join(mis);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 112 */     int prime = 31;
/* 113 */     int result = 1;
/*     */     
/* 115 */     result = 31 * result + ((this.matchingIdentifiers == null) ? 0 : this.matchingIdentifiers.hashCode());
/* 116 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public int compareTo(Object o) {
/* 121 */     if (o != null && o instanceof MatchingCombination) {
/* 122 */       MatchingCombination other = (MatchingCombination)o;
/*     */       
/* 124 */       int lengthComparison = this.matchingIdentifiers.size() - other.matchingIdentifiers.size();
/* 125 */       if (lengthComparison != 0) {
/* 126 */         return lengthComparison;
/*     */       }
/*     */       
/* 129 */       Iterator<MatchingIdentifier> otherIterator = other.matchingIdentifiers.iterator();
/* 130 */       int equalCount = 0;
/* 131 */       int negativeCount = 0;
/* 132 */       int positiveCount = 0;
/*     */       
/* 134 */       while (otherIterator.hasNext()) {
/* 135 */         MatchingIdentifier currentOther = otherIterator.next();
/*     */ 
/*     */         
/* 138 */         for (MatchingIdentifier currentThis : this.matchingIdentifiers) {
/* 139 */           int currentComparison = currentThis.compareTo(currentOther);
/* 140 */           if (currentComparison == 0) {
/* 141 */             equalCount++; continue;
/* 142 */           }  if (currentComparison > 0) {
/* 143 */             positiveCount++; continue;
/* 144 */           }  if (currentComparison < 0) {
/* 145 */             negativeCount++;
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 150 */       if (equalCount == this.matchingIdentifiers.size())
/* 151 */         return 0; 
/* 152 */       if (positiveCount > negativeCount) {
/* 153 */         return 1;
/*     */       }
/* 155 */       return -1;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 160 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 166 */     if (this == obj) {
/* 167 */       return true;
/*     */     }
/* 169 */     if (obj == null) {
/* 170 */       return false;
/*     */     }
/* 172 */     if (getClass() != obj.getClass()) {
/* 173 */       return false;
/*     */     }
/* 175 */     MatchingCombination other = (MatchingCombination)obj;
/* 176 */     if (this.matchingIdentifiers == null) {
/* 177 */       if (other.matchingIdentifiers != null) {
/* 178 */         return false;
/*     */       }
/* 180 */     } else if (!this.matchingIdentifiers.equals(other.matchingIdentifiers)) {
/* 181 */       return false;
/*     */     } 
/* 183 */     return true;
/*     */   }
/*     */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\MatchingCombination.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */