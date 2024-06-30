/*     */ package de.metanome.algorithm_integration.results;
/*     */ 
/*     */ import com.fasterxml.jackson.annotation.JsonTypeName;
/*     */ import de.metanome.algorithm_integration.ColumnPermutation;
/*     */ import de.metanome.algorithm_integration.result_receiver.ColumnNameMismatchException;
/*     */ import de.metanome.algorithm_integration.result_receiver.CouldNotReceiveResultException;
/*     */ import de.metanome.algorithm_integration.result_receiver.OmniscientResultReceiver;
/*     */ import java.util.HashMap;
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
/*     */ @JsonTypeName("OrderDependency")
/*     */ public class OrderDependency
/*     */   implements Result
/*     */ {
/*     */   private static final long serialVersionUID = 7183027964261217753L;
/*     */   public static final String OD_SEPARATOR = "~>";
/*     */   protected ComparisonOperator comparisonOperator;
/*     */   protected ColumnPermutation lhs;
/*     */   protected OrderType orderType;
/*     */   protected ColumnPermutation rhs;
/*     */   
/*     */   public enum ComparisonOperator
/*     */   {
/*  42 */     SMALLER_EQUAL("<="), STRICTLY_SMALLER("<");
/*     */ 
/*     */     
/*  45 */     private static Map<String, ComparisonOperator> str2Operator = new HashMap<>(); private String str;
/*     */     
/*     */     static {
/*  48 */       for (ComparisonOperator co : values()) {
/*  49 */         str2Operator.put(co.getStr(), co);
/*     */       }
/*     */     }
/*     */     
/*     */     ComparisonOperator(String str) {
/*  54 */       this.str = str;
/*     */     }
/*     */     
/*     */     public String getStr() {
/*  58 */       return this.str;
/*     */     }
/*     */     public static ComparisonOperator get(String str) {
/*  61 */       return str2Operator.get(str);
/*     */     }
/*     */   }
/*     */   
/*     */   public enum OrderType {
/*  66 */     LEXICOGRAPHICAL("lex"), POINTWISE("pnt");
/*     */ 
/*     */     
/*  69 */     private static Map<String, OrderType> str2Type = new HashMap<>(); private String str;
/*     */     
/*     */     static {
/*  72 */       for (OrderType ot : values()) {
/*  73 */         str2Type.put(ot.getStr(), ot);
/*     */       }
/*     */     }
/*     */     
/*     */     OrderType(String str) {
/*  78 */       this.str = str;
/*     */     }
/*     */     
/*     */     public String getStr() {
/*  82 */       return this.str;
/*     */     }
/*     */     public static OrderType get(String str) {
/*  85 */       return str2Type.get(str);
/*     */     }
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
/*     */   
/*     */   protected OrderDependency() {
/* 101 */     this.lhs = new ColumnPermutation();
/* 102 */     this.rhs = new ColumnPermutation();
/* 103 */     this.orderType = OrderType.LEXICOGRAPHICAL;
/* 104 */     this.comparisonOperator = ComparisonOperator.SMALLER_EQUAL;
/*     */   }
/*     */ 
/*     */   
/*     */   public OrderDependency(ColumnPermutation lhs, ColumnPermutation rhs, OrderType orderType, ComparisonOperator comparisonOperator) {
/* 109 */     this.lhs = lhs;
/* 110 */     this.rhs = rhs;
/* 111 */     this.orderType = orderType;
/* 112 */     this.comparisonOperator = comparisonOperator;
/*     */   }
/*     */   
/*     */   public void setLhs(ColumnPermutation lhs) {
/* 116 */     this.lhs = lhs;
/*     */   }
/*     */   
/*     */   public void setOrderType(OrderType orderType) {
/* 120 */     this.orderType = orderType;
/*     */   }
/*     */   
/*     */   public void setRhs(ColumnPermutation rhs) {
/* 124 */     this.rhs = rhs;
/*     */   }
/*     */   
/*     */   public void setComparisonOperator(ComparisonOperator comparisonOperator) {
/* 128 */     this.comparisonOperator = comparisonOperator;
/*     */   }
/*     */   
/*     */   public ComparisonOperator getComparisonOperator() {
/* 132 */     return this.comparisonOperator;
/*     */   }
/*     */   
/*     */   public ColumnPermutation getLhs() {
/* 136 */     return this.lhs;
/*     */   }
/*     */   
/*     */   public OrderType getOrderType() {
/* 140 */     return this.orderType;
/*     */   }
/*     */   
/*     */   public ColumnPermutation getRhs() {
/* 144 */     return this.rhs;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 149 */     if (this == obj) {
/* 150 */       return true;
/*     */     }
/* 152 */     if (obj == null) {
/* 153 */       return false;
/*     */     }
/* 155 */     if (getClass() != obj.getClass()) {
/* 156 */       return false;
/*     */     }
/* 158 */     OrderDependency other = (OrderDependency)obj;
/* 159 */     if (this.comparisonOperator != other.comparisonOperator) {
/* 160 */       return false;
/*     */     }
/* 162 */     if (this.lhs == null) {
/* 163 */       if (other.lhs != null) {
/* 164 */         return false;
/*     */       }
/* 166 */     } else if (!this.lhs.equals(other.lhs)) {
/* 167 */       return false;
/*     */     } 
/* 169 */     if (this.orderType != other.orderType) {
/* 170 */       return false;
/*     */     }
/* 172 */     if (this.rhs == null) {
/* 173 */       if (other.rhs != null) {
/* 174 */         return false;
/*     */       }
/* 176 */     } else if (!this.rhs.equals(other.rhs)) {
/* 177 */       return false;
/*     */     } 
/* 179 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 184 */     int prime = 31;
/* 185 */     int result = 1;
/* 186 */     result = 31 * result + ((this.comparisonOperator == null) ? 0 : this.comparisonOperator.hashCode());
/* 187 */     result = 31 * result + ((this.lhs == null) ? 0 : this.lhs.hashCode());
/* 188 */     result = 31 * result + ((this.orderType == null) ? 0 : this.orderType.hashCode());
/* 189 */     result = 31 * result + ((this.rhs == null) ? 0 : this.rhs.hashCode());
/* 190 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @XmlTransient
/*     */   public void sendResultTo(OmniscientResultReceiver resultReceiver) throws CouldNotReceiveResultException, ColumnNameMismatchException {
/* 197 */     resultReceiver.receiveResult(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 202 */     String orderTypeStr = this.orderType.getStr();
/* 203 */     String comparisonOperatorStr = this.comparisonOperator.getStr();
/*     */     
/* 205 */     return this.lhs + "~>" + "[" + comparisonOperatorStr + "," + orderTypeStr + "]" + this.rhs;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString(Map<String, String> tableMapping, Map<String, String> columnMapping) {
/* 210 */     String orderTypeStr = this.orderType.getStr();
/* 211 */     String comparisonOperatorStr = this.comparisonOperator.getStr();
/*     */     
/* 213 */     return this.lhs.toString(tableMapping, columnMapping) + "~>" + "[" + comparisonOperatorStr + "," + orderTypeStr + "]" + this.rhs
/* 214 */       .toString(tableMapping, columnMapping);
/*     */   }
/*     */ 
/*     */   
/*     */   public static OrderDependency fromString(Map<String, String> tableMapping, Map<String, String> columnMapping, String str) throws NullPointerException, IndexOutOfBoundsException {
/* 219 */     String[] parts1 = str.split("~>");
/* 220 */     String[] parts2 = parts1[1].split("]");
/* 221 */     String[] parts3 = parts2[0].substring(1).split(",");
/*     */     
/* 223 */     ColumnPermutation lhs = ColumnPermutation.fromString(tableMapping, columnMapping, parts1[0]);
/* 224 */     ColumnPermutation rhs = ColumnPermutation.fromString(tableMapping, columnMapping, parts2[1]);
/* 225 */     ComparisonOperator operator = ComparisonOperator.get(parts3[0]);
/* 226 */     OrderType order = OrderType.get(parts3[1]);
/*     */     
/* 228 */     return new OrderDependency(lhs, rhs, order, operator);
/*     */   }
/*     */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\results\OrderDependency.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */