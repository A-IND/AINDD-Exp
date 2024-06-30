/*     */ package de.uni_potsdam.hpi.utils;
/*     */ 
/*     */ import java.sql.Connection;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ 
/*     */ public class DatabaseUtils
/*     */ {
/*     */   public static void close(ResultSet resultSet) {
/*     */     try {
/*  12 */       if (resultSet != null) {
/*  13 */         resultSet.close();
/*     */       }
/*  15 */     } catch (SQLException e) {
/*  16 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void close(Statement statement) {
/*     */     try {
/*  22 */       if (statement != null) {
/*  23 */         statement.close();
/*     */       }
/*  25 */     } catch (SQLException e) {
/*  26 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void close(Connection connection) {
/*     */     try {
/*  32 */       if (connection != null) {
/*  33 */         if (!connection.getAutoCommit())
/*  34 */           connection.commit(); 
/*  35 */         connection.close();
/*     */       }
/*     */     
/*  38 */     } catch (SQLException e) {
/*  39 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String[] generateAttributeNames(int numAttributes) {
/*  44 */     String prefix = "A";
/*  45 */     String[] names = new String[numAttributes];
/*     */     
/*  47 */     for (int i = 0; i < numAttributes; i++) {
/*  48 */       names[i] = prefix + (i + 1);
/*     */     }
/*  50 */     return names;
/*     */   }
/*     */   
/*     */   public static String[] generateAttributeTypes(int numAttributes) {
/*  54 */     String[] types = new String[numAttributes];
/*     */     
/*  56 */     for (int i = 0; i < numAttributes; i++) {
/*  57 */       types[i] = "VARCHAR(255)";
/*     */     }
/*  59 */     return types;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void formatAttributeLabels(String[] firstLine) {
/*  64 */     for (int i = 0; i < firstLine.length; i++)
/*  65 */       firstLine[i] = firstLine[i].replace("-", "_").replace("/", "_"); 
/*     */   }
/*     */   
/*     */   public static boolean matchSameDataTypeClass(String dataType1, String dataType2) {
/*  69 */     if (dataType1.equals(dataType2))
/*  70 */       return true; 
/*  71 */     if (isNumeric(dataType1) && isNumeric(dataType2))
/*  72 */       return true; 
/*  73 */     if (isString(dataType1) && isString(dataType2))
/*  74 */       return true; 
/*  75 */     if (isTemporal(dataType1) && isTemporal(dataType2))
/*  76 */       return true; 
/*  77 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean isNumeric(String dataType) {
/*  81 */     if (dataType == null)
/*  82 */       return false; 
/*  83 */     String type = dataType.toLowerCase();
/*  84 */     if (type.contains("int") || type
/*  85 */       .contains("float") || type
/*  86 */       .contains("double") || type
/*  87 */       .contains("numeric") || type
/*  88 */       .contains("decimal") || type
/*  89 */       .contains("real") || type
/*  90 */       .contains("precision") || type
/*  91 */       .contains("serial") || type
/*  92 */       .contains("bit"))
/*  93 */       return true; 
/*  94 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean isString(String dataType) {
/*  98 */     if (dataType == null || isLargeObject(dataType))
/*  99 */       return false; 
/* 100 */     String type = dataType.toLowerCase();
/* 101 */     if (type.contains("char") || type
/* 102 */       .contains("text"))
/* 103 */       return true; 
/* 104 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean isTemporal(String dataType) {
/* 108 */     if (dataType == null)
/* 109 */       return false; 
/* 110 */     String type = dataType.toLowerCase();
/* 111 */     if (type.contains("date") || type
/* 112 */       .contains("time") || type
/* 113 */       .contains("year"))
/* 114 */       return true; 
/* 115 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean isLargeObject(String dataType) {
/* 119 */     if (dataType == null)
/* 120 */       return false; 
/* 121 */     String type = dataType.toLowerCase();
/* 122 */     if (type.contains("object") || type
/* 123 */       .contains("clob") || type
/* 124 */       .contains("blob"))
/* 125 */       return true; 
/* 126 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean isIndexable(String dataType) {
/* 130 */     if (dataType == null || dataType.equals("")) {
/* 131 */       return false;
/*     */     }
/*     */     
/* 134 */     String size = dataType.replaceAll("\\D", "");
/* 135 */     if (size.equals("") || Integer.parseInt(size) > 200) {
/* 136 */       return false;
/*     */     }
/* 138 */     return true;
/*     */   }
/*     */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\d\\uni_potsdam\hp\\utils\DatabaseUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */