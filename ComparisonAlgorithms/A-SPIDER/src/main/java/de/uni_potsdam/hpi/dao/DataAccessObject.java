/*     */ package de.uni_potsdam.hpi.dao;
/*     */ 
/*     */ import de.uni_potsdam.hpi.utils.CollectionUtils;
/*     */ import de.uni_potsdam.hpi.utils.DatabaseUtils;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ public abstract class DataAccessObject
/*     */ {
/*     */   public String buildDropTableQuery(String tableName) {
/*  15 */     return "DROP TABLE " + tableName;
/*     */   }
/*     */   
/*     */   public String buildCreateTableQuery(String tableName, String[] attributeNames, String[] types) {
/*  19 */     return "CREATE TABLE " + tableName + " (" + CollectionUtils.concat(attributeNames, types, " ", ",") + ")";
/*     */   }
/*     */   
/*     */   public String buildInsertQuery(String tableName, int numAttributes) {
/*  23 */     return "INSERT INTO " + tableName + " VALUES (" + CollectionUtils.concat(numAttributes, "?", ",") + ")";
/*     */   }
/*     */   
/*     */   public String buildDeleteQuery(String tableName, String[] attributeNames) {
/*  27 */     return "DELETE FROM " + tableName + " WHERE " + CollectionUtils.concat(attributeNames, "", " = ? ", "AND ");
/*     */   }
/*     */   
/*     */   public String buildSelectColumnQuery(String tableName, String attributeName) {
/*  31 */     return "SELECT " + attributeName + " FROM " + tableName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String buildSelectColumnQuery(String tableName, String attributeName, int limit) {
/*  36 */     return buildSelectColumnQuery(tableName, attributeName) + limitSuffix(limit);
/*     */   }
/*     */   
/*     */   public String buildSelectDistinctColumnQuery(String tableName, String attributeName) {
/*  40 */     return buildSelectColumnQuery(tableName, "DISTINCT " + attributeName);
/*     */   }
/*     */   
/*     */   public String buildSelectDistinctColumnQuery(String tableName, String attributeName, int limit) {
/*  44 */     return buildSelectColumnQuery(tableName, "DISTINCT " + attributeName, limit);
/*     */   }
/*     */   
/*     */   public String buildSelectEverythingQuery(String tableName) {
/*  48 */     return buildSelectColumnQuery(tableName, "*");
/*     */   }
/*     */   
/*     */   public String buildSelectEverythingQuery(String tableName, int limit) {
/*  52 */     return buildSelectColumnQuery(tableName, "*", limit);
/*     */   }
/*     */   
/*     */   public String buildSelectDistinctSortedColumnQuery(String tableName, String attributeName, String attributeType) {
/*  56 */     if (DatabaseUtils.isString(attributeType))
/*  57 */       return buildSelectDistinctSortedStringColumnQuery(tableName, attributeName); 
/*  58 */     if (DatabaseUtils.isNumeric(attributeType) || DatabaseUtils.isTemporal(attributeType)) {
/*  59 */       return buildSelectDistinctSortedNumberColumnQuery(tableName, attributeName);
/*     */     }
/*  61 */     return buildSelectDistinctSortedBinaryColumnQuery(tableName, attributeName);
/*     */   }
/*     */   
/*     */   public String buildSelectDistinctSortedColumnQuery(String tableName, String attributeName, String attributeType, int limit) {
/*  65 */     if (limit <= 0)
/*  66 */       return buildSelectDistinctSortedColumnQuery(tableName, attributeName, attributeType); 
/*  67 */     String subquery = "(" + buildSelectColumnQuery(tableName, attributeName, limit) + ") AS " + tableName + "_subset";
/*  68 */     return buildSelectDistinctSortedColumnQuery(subquery, attributeName, attributeType);
/*     */   }
/*     */   
/*     */   public String buildCountRowsQuery(String tableName) {
/*  72 */     return "SELECT COUNT(*) FROM " + tableName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String buildSelectValueQuery(String tableName, String attributeName, String value) {
/*  77 */     return "SELECT " + attributeName + " FROM " + tableName + " WHERE " + attributeName + " = '" + value + "' ";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String buildSelectValueQuery(String tableName, String attributeName, String value, int limit) {
/*  83 */     return buildSelectValueQuery(tableName, attributeName, value) + limitSuffix(limit);
/*     */   }
/*     */   
/*     */   public String buildSelectValuesQuery(String tableName, String attributeName, Set<String> values) {
/*  87 */     StringBuilder builder = new StringBuilder("SELECT DISTINCT " + attributeName + " FROM " + tableName + " WHERE ");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  92 */     int valuesRemaining = values.size();
/*  93 */     for (String value : values) {
/*  94 */       builder.append(attributeName + " = '" + value + "' ");
/*  95 */       valuesRemaining--;
/*  96 */       if (valuesRemaining > 0) {
/*  97 */         builder.append("OR ");
/*     */       }
/*     */     } 
/* 100 */     return builder.toString();
/*     */   }
/*     */   
/*     */   public String buildSelectValuesQuery(String tableName, String attributeName, Set<String> values, int limit) {
/* 104 */     return buildSelectValuesQuery(tableName, attributeName, values) + limitSuffix(limit);
/*     */   }
/*     */ 
/*     */   
/*     */   public String buildSelectColumnExceptColumnQuery(String fromTableName, String fromAttributeName, String exceptTableName, String exceptAttributeName, int limit) {
/* 109 */     return "SELECT DISTINCT fromTable." + fromAttributeName + " FROM " + fromTableName + " fromTable LEFT OUTER JOIN " + exceptTableName + " exceptTable ON fromTable." + fromAttributeName + " = exceptTable." + exceptAttributeName + " WHERE exceptTable." + exceptAttributeName + " IS NULL AND fromTable." + fromAttributeName + " IS NOT NULL " + 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 114 */       limitSuffix(limit);
/*     */   }
/*     */ 
/*     */   
/*     */   public String buildSelectColumnCombinationNotInColumnCombinationQuery(String fromTableName, String[] fromAttributeNames, String exceptTableName, String[] exceptAttributeNames, int limit) {
/* 119 */     String selectAttributes = CollectionUtils.concat(fromAttributeNames, "fromTable.", "", ", ");
/* 120 */     StringBuilder joinAttributes = new StringBuilder();
/* 121 */     for (int i = 0; i < fromAttributeNames.length; i++) {
/* 122 */       joinAttributes.append("fromTable." + fromAttributeNames[i] + " = exceptTable." + exceptAttributeNames[i] + " ");
/* 123 */       if (i + 1 < fromAttributeNames.length) {
/* 124 */         joinAttributes.append("AND ");
/*     */       }
/*     */     } 
/* 127 */     String nullAttributes = CollectionUtils.concat(exceptAttributeNames, "exceptTable.", " IS NULL ", "AND ");
/* 128 */     String notNullAttributes = CollectionUtils.concat(fromAttributeNames, "fromTable.", " IS NOT NULL ", "OR ");
/*     */     
/* 130 */     return "SELECT DISTINCT " + selectAttributes + " FROM " + fromTableName + " fromTable LEFT OUTER JOIN " + exceptTableName + " exceptTable ON " + joinAttributes
/*     */       
/* 132 */       .toString() + "WHERE " + nullAttributes + "AND ( " + notNullAttributes + ") " + 
/*     */ 
/*     */       
/* 135 */       limitSuffix(limit);
/*     */   }
/*     */ 
/*     */   
/*     */   public abstract String getDriverClassName();
/*     */ 
/*     */   
/*     */   public abstract String limitSuffix(int paramInt);
/*     */ 
/*     */   
/*     */   public abstract PreparedStatement insertValuesIntoStatement(PreparedStatement paramPreparedStatement, String[] paramArrayOfString1, String[] paramArrayOfString2, int paramInt) throws NumberFormatException, SQLException;
/*     */ 
/*     */   
/*     */   public abstract String buildSelectDistinctSortedStringColumnQuery(String paramString1, String paramString2);
/*     */ 
/*     */   
/*     */   public abstract String buildSelectDistinctSortedNumberColumnQuery(String paramString1, String paramString2);
/*     */ 
/*     */   
/*     */   public abstract String buildSelectDistinctSortedBinaryColumnQuery(String paramString1, String paramString2);
/*     */ 
/*     */   
/*     */   public abstract String buildCreateIndexQuery(String paramString1, String paramString2);
/*     */ 
/*     */   
/*     */   public abstract String buildDropIndexQuery(String paramString1, String paramString2);
/*     */   
/*     */   public abstract String buildColumnMetaQuery(String paramString1, String paramString2);
/*     */   
/*     */   public abstract String buildTableQuery(String paramString);
/*     */   
/*     */   public abstract void extract(List<String> paramList1, List<String> paramList2, List<String> paramList3, ResultSet paramResultSet) throws SQLException;
/*     */   
/*     */   public int hashCode() {
/* 169 */     return getDriverClassName().hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 174 */     return getDriverClassName().equals(obj);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 179 */     return getDriverClassName();
/*     */   }
/*     */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\d\\uni_potsdam\hpi\dao\DataAccessObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */