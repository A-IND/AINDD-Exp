/*     */ package de.uni_potsdam.hpi.dao;
/*     */ 
/*     */ import java.sql.Date;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.List;
/*     */ 
/*     */ public class PostgreSQLDataAccessObject
/*     */   extends DataAccessObject
/*     */ {
/*     */   public String getDriverClassName() {
/*  13 */     return "org.postgresql.Driver";
/*     */   }
/*     */ 
/*     */   
/*     */   public String limitSuffix(int limit) {
/*  18 */     if (limit <= 0)
/*  19 */       return ""; 
/*  20 */     return " LIMIT " + limit;
/*     */   }
/*     */ 
/*     */   
/*     */   public PreparedStatement insertValuesIntoStatement(PreparedStatement statement, String[] values, String[] valueTypes, int offset) throws NumberFormatException, SQLException {
/*  25 */     for (int i = 0; i < values.length; i++) {
/*  26 */       String valueType = valueTypes[i + offset].toLowerCase();
/*     */       
/*  28 */       if (values[i] == null || values[i].equals("")) {
/*  29 */         if (valueType.contains("long") || valueType.contains("big") || valueType.contains("int8")) {
/*  30 */           statement.setNull(i + 1, -5);
/*  31 */         } else if (valueType.contains("int") || valueType.contains("serial")) {
/*  32 */           statement.setNull(i + 1, 4);
/*  33 */         } else if (valueType.contains("float") || valueType.contains("numeric") || valueType.contains("decimal") || valueType.contains("real") || valueType.contains("precision")) {
/*  34 */           statement.setNull(i + 1, 6);
/*  35 */         } else if (valueType.contains("bool")) {
/*  36 */           statement.setNull(i + 1, 16);
/*  37 */         } else if (valueType.contains("date")) {
/*  38 */           statement.setNull(i + 1, 91);
/*     */         } else {
/*  40 */           statement.setNull(i + 1, 12);
/*     */         }
/*     */       
/*  43 */       } else if (valueType.contains("long") || valueType.contains("big") || valueType.contains("int8")) {
/*  44 */         statement.setLong(i + 1, Long.valueOf(values[i]).longValue());
/*  45 */       } else if (valueType.contains("int") || valueType.contains("serial")) {
/*  46 */         statement.setInt(i + 1, Integer.valueOf(values[i]).intValue());
/*  47 */       } else if (valueType.contains("float") || valueType.contains("numeric") || valueType.contains("decimal") || valueType.contains("real") || valueType.contains("precision")) {
/*  48 */         statement.setFloat(i + 1, Float.valueOf(values[i]).shortValue());
/*  49 */       } else if (valueType.contains("bool")) {
/*  50 */         statement.setBoolean(i + 1, Boolean.valueOf(values[i]).booleanValue());
/*  51 */       } else if (valueType.contains("date")) {
/*  52 */         statement.setDate(i + 1, Date.valueOf(values[i]));
/*     */       } else {
/*  54 */         statement.setString(i + 1, values[i]);
/*     */       } 
/*     */     } 
/*  57 */     return statement;
/*     */   }
/*     */ 
/*     */   
/*     */   public String buildSelectDistinctSortedStringColumnQuery(String tableName, String attributeName) {
/*  62 */     return "SELECT DISTINCT " + attributeName + " COLLATE \"C\" AS " + attributeName + "_sorted FROM " + tableName + " ORDER BY " + attributeName + "_sorted";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String buildSelectDistinctSortedNumberColumnQuery(String tableName, String attributeName) {
/*  69 */     return "SELECT DISTINCT " + attributeName + ", CAST(" + attributeName + " AS VARCHAR(50)) COLLATE \"C\" AS " + attributeName + "_sorted FROM " + tableName + " ORDER BY " + attributeName + "_sorted";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String buildSelectDistinctSortedBinaryColumnQuery(String tableName, String attributeName) {
/*  76 */     return "SELECT DISTINCT CAST(" + attributeName + " AS VARCHAR(255)) COLLATE \"C\" AS " + attributeName + "_sorted FROM " + tableName + " ORDER BY " + attributeName + "_sorted";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String buildCreateIndexQuery(String tableName, String attributeName) {
/*  83 */     return "CREATE INDEX index_" + tableName + "_" + attributeName + " ON " + tableName + " (" + attributeName + ")";
/*     */   }
/*     */ 
/*     */   
/*     */   public String buildDropIndexQuery(String tableName, String attributeName) {
/*  88 */     return "DROP INDEX index_" + tableName + "_" + attributeName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String buildColumnMetaQuery(String databaseName, String tableName) {
/*  93 */     return "SELECT DISTINCT COLUMN_NAME, UDT_NAME, CHARACTER_MAXIMUM_LENGTH, ORDINAL_POSITION FROM INFORMATION_SCHEMA.COLUMNS WHERE LOWER(TABLE_NAME) = LOWER('" + tableName + "') AND LOWER(TABLE_CATALOG) = LOWER('" + databaseName + "') ORDER BY ORDINAL_POSITION ASC";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String buildTableQuery(String databaseName) {
/* 102 */     return "SELECT DISTINCT TABLE_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = 'public' AND TABLE_CATALOG = '" + databaseName + "'";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void extract(List<String> names, List<String> types, List<String> basicTypes, ResultSet columnsResultSet) throws SQLException {
/* 110 */     while (columnsResultSet.next()) {
/* 111 */       names.add(columnsResultSet.getString("COLUMN_NAME"));
/*     */       
/* 113 */       String type = columnsResultSet.getString("UDT_NAME");
/* 114 */       String typeLength = columnsResultSet.getString("CHARACTER_MAXIMUM_LENGTH");
/*     */       
/* 116 */       if (typeLength == null || typeLength.equals("")) {
/* 117 */         types.add(type);
/*     */       } else {
/* 119 */         types.add(type + "(" + typeLength + ")");
/* 120 */       }  basicTypes.add(type);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\d\\uni_potsdam\hpi\dao\PostgreSQLDataAccessObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */