/*     */ package de.metanome.algorithm_integration;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Map;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
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
/*     */ public class MatchingIdentifier
/*     */   implements Comparable<MatchingIdentifier>, Serializable
/*     */ {
/*     */   public static final String IDENTIFIER_SEPARATOR = ",";
/*     */   public static final String SIMILARITY_SEPARATOR = "|";
/*     */   public static final String THRESHOLD_SEPARATOR = "@";
/*  29 */   private static final Pattern PATTERN = Pattern.compile("(.*)" + 
/*     */       
/*  31 */       Pattern.quote(",") + "(.*)" + 
/*  32 */       Pattern.quote("|") + "(.*)" + 
/*  33 */       Pattern.quote("@") + "(.*)");
/*     */ 
/*     */   
/*     */   private ColumnIdentifier left;
/*     */ 
/*     */   
/*     */   private ColumnIdentifier right;
/*     */ 
/*     */   
/*     */   private String similarityMeasure;
/*     */   
/*     */   private double threshold;
/*     */ 
/*     */   
/*     */   public MatchingIdentifier() {}
/*     */ 
/*     */   
/*     */   public MatchingIdentifier(ColumnIdentifier leftIdentifier, ColumnIdentifier rightIdentifier, String similarityMeasure, double threshold) {
/*  51 */     this.left = leftIdentifier;
/*  52 */     this.right = rightIdentifier;
/*  53 */     this.similarityMeasure = similarityMeasure;
/*  54 */     this.threshold = threshold;
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
/*     */   public static MatchingIdentifier fromString(Map<String, String> tableMapping, Map<String, String> columnMapping, String str) throws NullPointerException, IndexOutOfBoundsException {
/*  68 */     if (str.isEmpty()) {
/*  69 */       throw new IllegalArgumentException("Attempting to parse MatchingIdentifier from empty string");
/*     */     }
/*     */ 
/*     */     
/*  73 */     Matcher matcher = PATTERN.matcher(str);
/*  74 */     if (matcher.find()) {
/*     */ 
/*     */       
/*  77 */       ColumnIdentifier leftIdentifier = ColumnIdentifier.fromString(tableMapping, columnMapping, matcher.group(1));
/*     */       
/*  79 */       ColumnIdentifier rightIdentifier = ColumnIdentifier.fromString(tableMapping, columnMapping, matcher.group(2));
/*  80 */       double threshold = Double.parseDouble(matcher.group(4));
/*  81 */       return new MatchingIdentifier(leftIdentifier, rightIdentifier, matcher.group(3), threshold);
/*     */     } 
/*     */     
/*  84 */     throw new IllegalArgumentException("Cannot parse matching identifier from '" + str + "'");
/*     */   }
/*     */ 
/*     */   
/*     */   public ColumnIdentifier getLeft() {
/*  89 */     return this.left;
/*     */   }
/*     */   
/*     */   public void setLeft(ColumnIdentifier left) {
/*  93 */     this.left = left;
/*     */   }
/*     */   
/*     */   public ColumnIdentifier getRight() {
/*  97 */     return this.right;
/*     */   }
/*     */   
/*     */   public void setRight(ColumnIdentifier right) {
/* 101 */     this.right = right;
/*     */   }
/*     */   
/*     */   public String getSimilarityMeasure() {
/* 105 */     return this.similarityMeasure;
/*     */   }
/*     */   
/*     */   public void setSimilarityMeasure(String similarityMeasure) {
/* 109 */     this.similarityMeasure = similarityMeasure;
/*     */   }
/*     */   
/*     */   public double getThreshold() {
/* 113 */     return this.threshold;
/*     */   }
/*     */   
/*     */   public void setThreshold(double threshold) {
/* 117 */     this.threshold = threshold;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 122 */     return this.left + "," + this.right + "|" + this.similarityMeasure + "@" + this.threshold;
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
/*     */   public String toString(Map<String, String> tableMapping, Map<String, String> columnMapping) {
/* 135 */     return this.left.toString(tableMapping, columnMapping) + "," + this.right
/* 136 */       .toString(tableMapping, columnMapping) + "|" + this.similarityMeasure + "@" + this.threshold;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 142 */     int prime = 31;
/* 143 */     int result = 1;
/*     */ 
/*     */     
/* 146 */     result = 31 * result + ((this.left == null) ? 0 : this.left.hashCode());
/*     */     
/* 148 */     result = 31 * result + ((this.right == null) ? 0 : this.right.hashCode());
/*     */     
/* 150 */     result = 31 * result + ((this.similarityMeasure == null) ? 0 : this.similarityMeasure.hashCode());
/*     */     
/* 152 */     result = 31 * result + Double.hashCode(this.threshold);
/* 153 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 158 */     if (this == obj) {
/* 159 */       return true;
/*     */     }
/* 161 */     if (obj == null) {
/* 162 */       return false;
/*     */     }
/* 164 */     if (getClass() != obj.getClass()) {
/* 165 */       return false;
/*     */     }
/* 167 */     MatchingIdentifier other = (MatchingIdentifier)obj;
/* 168 */     if (this.left == null) {
/* 169 */       if (other.left != null) {
/* 170 */         return false;
/*     */       }
/* 172 */     } else if (!this.left.equals(other.left)) {
/* 173 */       return false;
/*     */     } 
/* 175 */     if (this.right == null) {
/* 176 */       if (other.right != null) {
/* 177 */         return false;
/*     */       }
/* 179 */     } else if (!this.right.equals(other.right)) {
/* 180 */       return false;
/*     */     } 
/* 182 */     if (this.similarityMeasure == null) {
/* 183 */       if (other.similarityMeasure != null) {
/* 184 */         return false;
/*     */       }
/* 186 */     } else if (!this.similarityMeasure.equals(other.similarityMeasure)) {
/* 187 */       return false;
/*     */     } 
/* 189 */     return (this.threshold == other.threshold);
/*     */   }
/*     */ 
/*     */   
/*     */   public int compareTo(MatchingIdentifier other) {
/* 194 */     int thresholdComparison = -Double.compare(this.threshold, other.threshold);
/* 195 */     if (0 != thresholdComparison) {
/* 196 */       return thresholdComparison;
/*     */     }
/*     */     
/* 199 */     int leftComparison = compare(this.left, other.left);
/*     */     
/* 201 */     if (0 != leftComparison) {
/* 202 */       return leftComparison;
/*     */     }
/*     */     
/* 205 */     int rightComparison = compare(this.right, other.right);
/* 206 */     if (0 != rightComparison) {
/* 207 */       return rightComparison;
/*     */     }
/* 209 */     return compare(this.similarityMeasure, other.similarityMeasure);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static <T extends Comparable<T>> int compare(T comparable, T otherComparable) {
/* 216 */     if (comparable == null) {
/* 217 */       if (otherComparable == null) {
/* 218 */         return 0;
/*     */       }
/* 220 */       return 1;
/*     */     } 
/* 222 */     if (otherComparable == null) {
/* 223 */       return -1;
/*     */     }
/* 225 */     return comparable.compareTo(otherComparable);
/*     */   }
/*     */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\MatchingIdentifier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */