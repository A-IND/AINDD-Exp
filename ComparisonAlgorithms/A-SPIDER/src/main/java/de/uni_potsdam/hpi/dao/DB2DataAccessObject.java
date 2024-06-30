/*    */ package de.uni_potsdam.hpi.dao;
/*    */ 
/*    */ import java.sql.PreparedStatement;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import java.util.List;
/*    */ 
/*    */ public class DB2DataAccessObject
/*    */   extends DataAccessObject
/*    */ {
/*    */   public String getDriverClassName() {
/* 12 */     return "com.ibm.db2.jcc.DB2Driver";
/*    */   }
/*    */ 
/*    */   
/*    */   public String limitSuffix(int limit) {
/* 17 */     if (limit <= 0)
/* 18 */       return ""; 
/* 19 */     return " FETCH FIRST " + limit + " ROWS ONLY";
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
/* 31 */     return "SELECT DISTINCT " + attributeName + " FROM " + tableName + " ORDER BY " + attributeName + " ";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String buildSelectDistinctSortedNumberColumnQuery(String tableName, String attributeName) {
/* 38 */     return "SELECT DISTINCT " + attributeName + ", CAST(" + attributeName + " AS VARCHAR(50)) FROM " + tableName + " ORDER BY CAST(" + attributeName + " AS VARCHAR(50))";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String buildSelectDistinctSortedBinaryColumnQuery(String tableName, String attributeName) {
/* 45 */     return "SELECT DISTINCT CAST(" + attributeName + " AS VARCHAR(255)) FROM " + tableName + " ORDER BY CAST(" + attributeName + " AS VARCHAR(255))";
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
/* 57 */     return "DROP INDEX index_" + tableName + "_" + attributeName;
/*    */   }
/*    */ 
/*    */   
/*    */   public String buildColumnMetaQuery(String databaseName, String tableName) {
/* 62 */     return "SELECT DISTINCT COLUMN_NAME, ORDINAL_POSITION, DATA_TYPE, CHARACTER_MAXIMUM_LENGTH FROM SYSIBM.COLUMNS WHERE LOWER(TABLE_NAME) = LOWER('" + tableName + "') ORDER BY ORDINAL_POSITION ASC";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String buildTableQuery(String databaseName) {
/* 70 */     return "SELECT DISTINCT TABLE_NAME FROM SYSIBM.COLUMNS WHERE TABLE_SCHEMA = '" + databaseName + "' ";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void extract(List<String> names, List<String> types, List<String> basicTypes, ResultSet columnsResultSet) throws SQLException {
/* 77 */     while (columnsResultSet.next()) {
/* 78 */       names.add(columnsResultSet.getString("COLUMN_NAME"));
/*    */       
/* 80 */       String basicType = columnsResultSet.getString("DATA_TYPE");
/* 81 */       if (basicType.equals("CHARACTER VARYING")) {
/* 82 */         types.add("VARCHAR(" + columnsResultSet.getInt("CHARACTER_MAXIMUM_LENGTH") + ")");
/*    */       } else {
/* 84 */         types.add(basicType);
/* 85 */       }  basicTypes.add(basicType);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\d\\uni_potsdam\hpi\dao\DB2DataAccessObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */