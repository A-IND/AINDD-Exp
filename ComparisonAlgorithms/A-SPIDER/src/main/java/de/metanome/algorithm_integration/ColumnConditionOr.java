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
/*     */ @JsonTypeName("ColumnConditionOr")
/*     */ public class ColumnConditionOr
/*     */   implements ColumnCondition
/*     */ {
/*     */   private static final long serialVersionUID = 4484094838499696950L;
/*     */   protected boolean isNegated = false;
/*     */   protected Set<ColumnCondition> columnValues;
/*  36 */   protected float coverage = Float.NaN;
/*     */   
/*     */   protected ColumnConditionOr() {
/*  39 */     this.columnValues = new TreeSet<>();
/*     */   }
/*     */   
/*     */   public ColumnConditionOr(Map<ColumnIdentifier, String> conditionMap) {
/*  43 */     this();
/*  44 */     for (ColumnIdentifier column : conditionMap.keySet()) {
/*  45 */       this.columnValues.add(new ColumnConditionValue(column, conditionMap.get(column)));
/*     */     }
/*     */   }
/*     */   
/*     */   public ColumnConditionOr(TreeSet<ColumnCondition> treeSet) {
/*  50 */     this.columnValues = new TreeSet<>(treeSet);
/*     */   }
/*     */   
/*     */   public ColumnConditionOr(ColumnCondition... conditions) {
/*  54 */     this();
/*  55 */     Collections.addAll(this.columnValues, conditions);
/*     */   }
/*     */   
/*     */   public ColumnConditionOr(ColumnIdentifier identifier, String... values) {
/*  59 */     this();
/*  60 */     for (String value : values) {
/*  61 */       this.columnValues.add(new ColumnConditionValue(identifier, value));
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isNegated() {
/*  66 */     return this.isNegated;
/*     */   }
/*     */   
/*     */   public void setNegated(boolean isNegated) {
/*  70 */     this.isNegated = isNegated;
/*     */   }
/*     */   
/*     */   public Set<ColumnCondition> getColumnValues() {
/*  74 */     return this.columnValues;
/*     */   }
/*     */   
/*     */   public void setColumnValues(Set<ColumnCondition> columnValues) {
/*  78 */     this.columnValues = columnValues;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getCoverage() {
/*  83 */     if (Float.isNaN(this.coverage)) {
/*  84 */       float coverage = 0.0F;
/*  85 */       for (ColumnCondition subCondition : this.columnValues) {
/*  86 */         coverage += subCondition.getCoverage();
/*     */       }
/*  88 */       return coverage;
/*     */     } 
/*  90 */     return this.coverage;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCoverage(float coverage) {
/*  96 */     this.coverage = coverage;
/*     */   }
/*     */ 
/*     */   
/*     */   public ColumnCondition add(ColumnCondition condition) {
/* 101 */     this.columnValues.add(condition);
/* 102 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   @JsonIgnore
/*     */   public TreeSet<ColumnIdentifier> getContainedColumns() {
/* 108 */     TreeSet<ColumnIdentifier> result = new TreeSet<>();
/* 109 */     for (ColumnCondition subElement : this.columnValues) {
/* 110 */       result.addAll(subElement.getContainedColumns());
/*     */     }
/* 112 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   @JsonIgnore
/*     */   public List<Map<ColumnIdentifier, String>> getPatternConditions() {
/* 118 */     List<Map<ColumnIdentifier, String>> result = new LinkedList<>();
/* 119 */     for (ColumnCondition columnCondition : this.columnValues) {
/* 120 */       result.addAll(columnCondition.getPatternConditions());
/*     */     }
/* 122 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public int compareTo(ColumnCondition o) {
/* 127 */     if (o instanceof ColumnConditionOr) {
/* 128 */       ColumnConditionOr other = (ColumnConditionOr)o;
/* 129 */       int lengthComparison = this.columnValues.size() - other.columnValues.size();
/* 130 */       if (lengthComparison != 0) {
/* 131 */         return lengthComparison;
/*     */       }
/* 133 */       Iterator<ColumnCondition> otherIterator = other.columnValues.iterator();
/* 134 */       int equalCount = 0;
/*     */       
/* 136 */       while (otherIterator.hasNext()) {
/* 137 */         ColumnCondition currentOther = otherIterator.next();
/*     */ 
/*     */         
/* 140 */         for (ColumnCondition currentThis : this.columnValues) {
/* 141 */           int currentComparison = currentThis.compareTo(currentOther);
/* 142 */           if (currentComparison == 0) {
/* 143 */             equalCount++;
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 148 */       if (equalCount == this.columnValues.size()) {
/* 149 */         return 0;
/*     */       }
/* 151 */       return 1;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 156 */     if (o instanceof ColumnConditionValue) {
/* 157 */       return 1;
/*     */     }
/* 159 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 166 */     String delimiter = " v ";
/* 167 */     StringBuilder builder = new StringBuilder();
/* 168 */     if (this.isNegated) {
/* 169 */       builder.append("¬");
/*     */     }
/* 171 */     builder.append("[");
/* 172 */     for (ColumnCondition value : this.columnValues) {
/* 173 */       builder.append(value.toString());
/* 174 */       builder.append(delimiter);
/*     */     } 
/* 176 */     return builder.substring(0, builder.length() - delimiter.length())
/* 177 */       .concat("]");
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 182 */     if (this == o) {
/* 183 */       return true;
/*     */     }
/* 185 */     if (o == null || getClass() != o.getClass()) {
/* 186 */       return false;
/*     */     }
/*     */     
/* 189 */     ColumnConditionOr that = (ColumnConditionOr)o;
/*     */     
/* 191 */     if (this.isNegated != that.isNegated) {
/* 192 */       return false;
/*     */     }
/* 194 */     if ((this.columnValues != null) ? !this.columnValues.equals(that.columnValues) : (that.columnValues != null))
/*     */     {
/* 196 */       return false;
/*     */     }
/*     */     
/* 199 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 204 */     int result = this.isNegated ? 1 : 0;
/* 205 */     result = 31 * result + ((this.columnValues != null) ? this.columnValues.hashCode() : 0);
/* 206 */     return result;
/*     */   }
/*     */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\ColumnConditionOr.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */