/*     */ package de.metanome.algorithm_integration;
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
/*     */ public enum Operator
/*     */ {
/*  19 */   EQUAL, UNEQUAL, GREATER, LESS, GREATER_EQUAL, LESS_EQUAL;
/*     */   
/*     */   private String shortString;
/*     */   
/*     */   private Operator[] transitives;
/*     */   private Operator[] implications;
/*     */   private Operator symmetric;
/*     */   private Operator inverse;
/*     */   private static final double EPSILON = 1.0E-5D;
/*     */   
/*     */   public Operator getInverse() {
/*  30 */     return this.inverse;
/*     */   }
/*     */   
/*     */   public Operator getSymmetric() {
/*  34 */     return this.symmetric;
/*     */   }
/*     */   
/*     */   public Operator[] getImplications() {
/*  38 */     return this.implications;
/*     */   }
/*     */   
/*     */   public String getShortString() {
/*  42 */     return this.shortString;
/*     */   }
/*     */   
/*     */   public Operator[] getTransitives() {
/*  46 */     return this.transitives;
/*     */   }
/*     */   
/*     */   public boolean isTransitiveWith(Operator op) {
/*  50 */     for (Operator i : this.transitives) {
/*  51 */       if (i == op)
/*  52 */         return true; 
/*     */     } 
/*  54 */     return false;
/*     */   }
/*     */   
/*     */   public <T> boolean eval(Comparable<T> value1, T value2) {
/*  58 */     if (this == EQUAL)
/*  59 */       return value1.equals(value2); 
/*  60 */     if (this == UNEQUAL) {
/*  61 */       return !value1.equals(value2);
/*     */     }
/*  63 */     int c = value1.compareTo(value2);
/*  64 */     switch (this) {
/*     */       case GREATER_EQUAL:
/*  66 */         return (c >= 0);
/*     */       case LESS:
/*  68 */         return (c < 0);
/*     */       case LESS_EQUAL:
/*  70 */         return (c <= 0);
/*     */       case GREATER:
/*  72 */         return (c > 0);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  78 */     return false;
/*     */   }
/*     */   
/*     */   public boolean eval(int value1, int value2) {
/*  82 */     switch (this) {
/*     */       case EQUAL:
/*  84 */         return (value1 == value2);
/*     */       case GREATER:
/*  86 */         return (value1 > value2);
/*     */       case GREATER_EQUAL:
/*  88 */         return (value1 >= value2);
/*     */       case LESS:
/*  90 */         return (value1 < value2);
/*     */       case LESS_EQUAL:
/*  92 */         return (value1 <= value2);
/*     */       case UNEQUAL:
/*  94 */         return (value1 != value2);
/*     */     } 
/*  96 */     return false;
/*     */   }
/*     */   
/*     */   public boolean eval(double value1, double value2) {
/* 100 */     switch (this) {
/*     */       case EQUAL:
/* 102 */         return (Math.abs(value1 - value2) < 1.0E-5D);
/*     */       case UNEQUAL:
/* 104 */         return (Math.abs(value1 - value2) >= 1.0E-5D);
/*     */       case GREATER:
/* 106 */         return (value1 > value2);
/*     */       case GREATER_EQUAL:
/* 108 */         return (value1 >= value2);
/*     */       case LESS:
/* 110 */         return (value1 < value2);
/*     */       case LESS_EQUAL:
/* 112 */         return (value1 <= value2);
/*     */     } 
/* 114 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/* 121 */     EQUAL.inverse = UNEQUAL;
/* 122 */     UNEQUAL.inverse = EQUAL;
/* 123 */     GREATER.inverse = LESS_EQUAL;
/* 124 */     LESS.inverse = GREATER_EQUAL;
/* 125 */     GREATER_EQUAL.inverse = LESS;
/* 126 */     LESS_EQUAL.inverse = GREATER;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 133 */     EQUAL.symmetric = EQUAL;
/* 134 */     UNEQUAL.symmetric = UNEQUAL;
/* 135 */     GREATER.symmetric = LESS;
/* 136 */     LESS.symmetric = GREATER;
/* 137 */     GREATER_EQUAL.symmetric = LESS_EQUAL;
/* 138 */     LESS_EQUAL.symmetric = GREATER_EQUAL;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 145 */     EQUAL.implications = new Operator[] { EQUAL, GREATER_EQUAL, LESS_EQUAL };
/* 146 */     UNEQUAL.implications = new Operator[] { UNEQUAL };
/* 147 */     GREATER.implications = new Operator[] { GREATER, GREATER_EQUAL, UNEQUAL };
/* 148 */     LESS.implications = new Operator[] { LESS, LESS_EQUAL, UNEQUAL };
/* 149 */     GREATER_EQUAL.implications = new Operator[] { GREATER_EQUAL };
/* 150 */     LESS_EQUAL.implications = new Operator[] { LESS_EQUAL };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 157 */     EQUAL.transitives = new Operator[] { EQUAL };
/* 158 */     UNEQUAL.transitives = new Operator[] { EQUAL };
/* 159 */     GREATER.transitives = new Operator[] { GREATER, GREATER_EQUAL, EQUAL };
/* 160 */     LESS.transitives = new Operator[] { LESS, LESS_EQUAL, EQUAL };
/* 161 */     GREATER_EQUAL.transitives = new Operator[] { GREATER, GREATER_EQUAL, EQUAL };
/* 162 */     LESS_EQUAL.transitives = new Operator[] { LESS, LESS_EQUAL, EQUAL };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 169 */     EQUAL.shortString = "==";
/* 170 */     UNEQUAL.shortString = "<>";
/* 171 */     GREATER.shortString = ">";
/* 172 */     LESS.shortString = "<";
/* 173 */     GREATER_EQUAL.shortString = ">=";
/* 174 */     LESS_EQUAL.shortString = "<=";
/*     */   }
/*     */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\Operator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */