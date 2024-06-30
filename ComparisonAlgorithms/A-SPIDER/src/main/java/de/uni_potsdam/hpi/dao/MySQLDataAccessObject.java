/*    */ package de.uni_potsdam.hpi.dao;
/*    */ 
/*    */ import java.sql.PreparedStatement;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import java.util.List;
/*    */ 
/*    */ public class MySQLDataAccessObject
/*    */   extends DataAccessObject
/*    */ {
/*    */   public String getDriverClassName() {
/* 12 */     return "com.mysql.jdbc.Driver";
/*    */   }
/*    */ 
/*    */   
/*    */   public String limitSuffix(int limit) {
/* 17 */     if (limit <= 0)
/* 18 */       return ""; 
/* 19 */     return " LIMIT " + limit;
/*    */   }
/*    */ 
/*    */   
/*    */   public PreparedStatement insertValuesIntoStatement(PreparedStatement statement, String[] values, String[] valueTypes, int offset) throws NumberFormatException, SQLException {
/* 24 */     for (int i = 0; i < values.length; i++)
/* 25 */       statement.setString(i + 1, values[i]); 
/* 26 */     return statement;
/*    */   }
/*    */ 
/*    */   
/*    */   public String buildSelectDistinctSortedStringColumnQuery(String tableName, String attributeName) {
/* 31 */     return "SELECT DISTINCT " + attributeName + " COLLATE utf8_bin AS " + attributeName + "_sorted FROM " + tableName + " ORDER BY " + attributeName + "_sorted";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String buildSelectDistinctSortedNumberColumnQuery(String tableName, String attributeName) {
/* 38 */     return "SELECT DISTINCT " + attributeName + ", CAST(" + attributeName + " AS VARCHAR(50)) COLLATE utf8_bin AS " + attributeName + "_sorted FROM " + tableName + " ORDER BY " + attributeName + "_sorted";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String buildSelectDistinctSortedBinaryColumnQuery(String tableName, String attributeName) {
/* 45 */     return "SELECT DISTINCT CAST(" + attributeName + " AS VARCHAR(255)) COLLATE utf8_bin AS " + attributeName + "_sorted FROM " + tableName + " ORDER BY " + attributeName + "_sorted";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String buildCreateIndexQuery(String tableName, String attributeName) {
/* 52 */     return "CREATE INDEX index_" + tableName + "_" + attributeName + " ON " + tableName + " (" + attributeName + ")";
/*    */   }
/*    */ 
/*    */   
/*    */   public String buildDropIndexQuery(String tableName, String attributeName) {
/* 57 */     return "DROP INDEX index_" + tableName + "_" + attributeName + " ON " + tableName;
/*    */   }
/*    */ 
/*    */   
/*    */   public String buildColumnMetaQuery(String databaseName, String tableName) {
/* 62 */     return "SELECT DISTINCT COLUMN_NAME, COLUMN_TYPE, ORDINAL_POSITION FROM INFORMATION_SCHEMA.COLUMNS WHERE LOWER(TABLE_NAME) = LOWER('" + tableName + "') AND LOWER(TABLE_SCHEMA) = LOWER('" + databaseName + "') ORDER BY ORDINAL_POSITION ASC";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String buildTableQuery(String databaseName) {
/* 71 */     return "SELECT DISTINCT TABLE_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = '" + databaseName + "'";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void extract(List<String> names, List<String> types, List<String> basicTypes, ResultSet columnsResultSet) throws SQLException {
/* 78 */     while (columnsResultSet.next()) {
/* 79 */       names.add(columnsResultSet.getString("COLUMN_NAME"));
/*    */       
/* 81 */       String type = columnsResultSet.getString("COLUMN_TYPE");
/* 82 */       types.add(type);
/* 83 */       basicTypes.add(type.replaceAll("\\d|\\(|\\)", ""));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\d\\uni_potsdam\hpi\dao\MySQLDataAccessObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */