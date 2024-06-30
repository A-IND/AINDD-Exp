/*     */ package de.metanome.algorithm_integration;
/*     */ 
/*     */ import com.fasterxml.jackson.annotation.JsonIgnore;
/*     */ import com.fasterxml.jackson.annotation.JsonTypeName;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
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
/*     */ @JsonTypeName("ColumnConditionAnd")
/*     */ public class ColumnConditionAnd
/*     */   implements ColumnCondition
/*     */ {
/*     */   private static final long serialVersionUID = 1773856119866618862L;
/*     */   protected boolean isNegated = false;
/*  36 */   protected float coverage = Float.NaN;
/*     */   protected Set<ColumnCondition> columnValues;
/*     */   
/*     */   protected ColumnConditionAnd() {
/*  40 */     this.columnValues = new TreeSet<>();
/*     */   }
/*     */   
/*     */   public ColumnConditionAnd(Map<ColumnIdentifier, String> conditionMap) {
/*  44 */     this();
/*  45 */     for (ColumnIdentifier column : conditionMap.keySet()) {
/*  46 */       this.columnValues.add(new ColumnConditionValue(column, conditionMap.get(column)));
/*     */     }
/*     */   }
/*     */   
/*     */   public ColumnConditionAnd(TreeSet<ColumnCondition> treeSet) {
/*  51 */     this.columnValues = new TreeSet<>(treeSet);
/*     */   }
/*     */   
/*     */   public ColumnConditionAnd(ColumnCondition... conditions) {
/*  55 */     this();
/*  56 */     Collections.addAll(this.columnValues, conditions);
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<ColumnCondition> getColumnValues() {
/*  61 */     return this.columnValues;
/*     */   }
/*     */   
/*     */   public void setColumnValues(Set<ColumnCondition> columnValues) {
/*  65 */     this.columnValues = columnValues;
/*     */   }
/*     */   
/*     */   public boolean isNegated() {
/*  69 */     return this.isNegated;
/*     */   }
/*     */   
/*     */   public void setNegated(boolean isNegated) {
/*  73 */     this.isNegated = isNegated;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getCoverage() {
/*  78 */     if (Float.isNaN(this.coverage)) {
/*  79 */       float coverage = Float.MAX_VALUE;
/*  80 */       for (ColumnCondition subCondition : this.columnValues) {
/*  81 */         if (coverage > subCondition.getCoverage()) {
/*  82 */           coverage = subCondition.getCoverage();
/*     */         }
/*     */       } 
/*  85 */       return coverage;
/*     */     } 
/*  87 */     return this.coverage;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCoverage(float coverage) {
/*  93 */     this.coverage = coverage;
/*     */   }
/*     */ 
/*     */   
/*     */   public ColumnCondition add(ColumnCondition condition) {
/*  98 */     this.columnValues.add(condition);
/*  99 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   @JsonIgnore
/*     */   public TreeSet<ColumnIdentifier> getContainedColumns() {
/* 105 */     TreeSet<ColumnIdentifier> result = new TreeSet<>();
/* 106 */     for (ColumnCondition subElement : this.columnValues) {
/* 107 */       result.addAll(subElement.getContainedColumns());
/*     */     }
/* 109 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   @JsonIgnore
/*     */   public List<Map<ColumnIdentifier, String>> getPatternConditions() {
/* 115 */     List<Map<ColumnIdentifier, String>> result = new LinkedList<>();
/* 116 */     Map<ColumnIdentifier, String> condition = new TreeMap<>();
/* 117 */     for (ColumnCondition columnCondition : this.columnValues) {
/* 118 */       condition.putAll(columnCondition.getPatternConditions().get(0));
/*     */     }
/* 120 */     result.add(condition);
/* 121 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public int compareTo(ColumnCondition o) {
/* 126 */     if (o instanceof ColumnConditionAnd) {
/* 127 */       ColumnConditionAnd other = (ColumnConditionAnd)o;
/* 128 */       int lengthComparison = this.columnValues.size() - other.columnValues.size();
/* 129 */       if (lengthComparison != 0) {
/* 130 */         return lengthComparison;
/*     */       }
/* 132 */       Iterator<ColumnCondition> otherIterator = other.columnValues.iterator();
/* 133 */       int equalCount = 0;
/*     */       
/* 135 */       while (otherIterator.hasNext()) {
/* 136 */         ColumnCondition currentOther = otherIterator.next();
/*     */ 
/*     */         
/* 139 */         for (ColumnCondition currentThis : this.columnValues) {
/* 140 */           int currentComparison = currentThis.compareTo(currentOther);
/* 141 */           if (currentComparison == 0) {
/* 142 */             equalCount++;
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 147 */       if (equalCount == this.columnValues.size()) {
/* 148 */         return 0;
/*     */       }
/* 150 */       return 1;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 155 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 161 */     String delimiter = " ^ ";
/* 162 */     StringBuilder builder = new StringBuilder();
/* 163 */     if (this.isNegated) {
/* 164 */       builder.append("¬");
/*     */     }
/* 166 */     builder.append("[");
/* 167 */     for (ColumnCondition value : this.columnValues) {
/* 168 */       builder.append(value.toString());
/* 169 */       builder.append(delimiter);
/*     */     } 
/* 171 */     return builder.substring(0, builder.length() - delimiter.length())
/* 172 */       .concat("]");
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 177 */     if (this == o) {
/* 178 */       return true;
/*     */     }
/* 180 */     if (o == null || getClass() != o.getClass()) {
/* 181 */       return false;
/*     */     }
/*     */     
/* 184 */     ColumnConditionAnd that = (ColumnConditionAnd)o;
/*     */     
/* 186 */     if (this.isNegated != that.isNegated) {
/* 187 */       return false;
/*     */     }
/* 189 */     if ((this.columnValues != null) ? !this.columnValues.equals(that.columnValues) : (that.columnValues != null))
/*     */     {
/* 191 */       return false;
/*     */     }
/*     */     
/* 194 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 199 */     int result = this.isNegated ? 1 : 0;
/* 200 */     result = 31 * result + ((this.columnValues != null) ? this.columnValues.hashCode() : 0);
/* 201 */     return result;
/*     */   }
/*     */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\ColumnConditionAnd.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */