/*     */ package de.uni_potsdam.hpi.utils;
/*     */ 
/*     */ import au.com.bytecode.opencsv.CSVReader;
/*     */ import au.com.bytecode.opencsv.CSVWriter;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.Closeable;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.Writer;
/*     */ import java.nio.charset.Charset;
/*     */ 
/*     */ 
/*     */ public class FileUtils
/*     */ {
/*  21 */   public static String CHARSET_NAME = "ISO-8859-1";
/*     */   
/*     */   public static void close(Closeable closeable) {
/*  24 */     if (closeable != null) {
/*     */       try {
/*  26 */         closeable.close();
/*     */       }
/*  28 */       catch (IOException e) {
/*  29 */         e.printStackTrace();
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public static void close(AutoCloseable closeable) {
/*  35 */     if (closeable != null) {
/*     */       try {
/*  37 */         closeable.close();
/*     */       }
/*  39 */       catch (Exception e) {
/*  40 */         e.printStackTrace();
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public static boolean isRoot(File directory) {
/*  46 */     File rootSlash = new File("/");
/*  47 */     File rootBackslash = new File("\\");
/*  48 */     if (directory.getAbsolutePath().equals(rootSlash.getAbsolutePath()) || directory.getAbsolutePath().equals(rootBackslash.getAbsolutePath()))
/*  49 */       return true; 
/*  50 */     return false;
/*     */   }
/*     */   
/*     */   public static void deleteDirectory(File directory) {
/*  54 */     if (isRoot(directory)) {
/*     */       return;
/*     */     }
/*  57 */     if (directory.exists()) {
/*  58 */       File[] files = directory.listFiles();
/*  59 */       if (null != files)
/*  60 */         for (int i = 0; i < files.length; i++) {
/*  61 */           if (files[i].isDirectory()) {
/*  62 */             deleteDirectory(files[i]);
/*     */           } else {
/*  64 */             files[i].delete();
/*     */           } 
/*     */         }  
/*     */     } 
/*  68 */     directory.delete();
/*     */   }
/*     */   
/*     */   public static void cleanDirectory(File directory) {
/*  72 */     if (isRoot(directory)) {
/*     */       return;
/*     */     }
/*  75 */     if (directory.exists()) {
/*  76 */       File[] files = directory.listFiles();
/*  77 */       if (null != files)
/*  78 */         for (int i = 0; i < files.length; i++) {
/*  79 */           if (files[i].isDirectory()) {
/*  80 */             deleteDirectory(files[i]);
/*     */           } else {
/*  82 */             files[i].delete();
/*     */           } 
/*     */         }  
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void createFile(String filePath, boolean recreateIfExists) throws IOException {
/*  89 */     File file = new File(filePath);
/*  90 */     File folder = file.getParentFile();
/*     */     
/*  92 */     if (folder != null && !folder.exists()) {
/*  93 */       folder.mkdirs();
/*  94 */       while (!folder.exists());
/*     */     } 
/*     */     
/*  97 */     if (recreateIfExists && file.exists()) {
/*  98 */       file.delete();
/*     */     }
/* 100 */     if (!file.exists()) {
/* 101 */       file.createNewFile();
/* 102 */       while (!file.exists());
/*     */     } 
/*     */   }
/*     */   
/*     */   public static CSVReader buildCSVFileReader(String filePath, char separator, boolean skipHeadline) throws FileNotFoundException {
/* 107 */     int line = skipHeadline ? 1 : 0;
/* 108 */     return new CSVReader(new InputStreamReader(new FileInputStream(filePath), Charset.forName(CHARSET_NAME)), separator, '"', line);
/*     */   }
/*     */   
/*     */   public static CSVWriter buildCSVFileWriter(String filePath, char separator, boolean append) throws IOException {
/* 112 */     createFile(filePath, !append);
/* 113 */     return new CSVWriter(new OutputStreamWriter(new FileOutputStream(new File(filePath), append), Charset.forName(CHARSET_NAME)), separator, '"', '\\');
/*     */   }
/*     */   
/*     */   public static BufferedReader buildFileReader(String filePath) throws FileNotFoundException {
/* 117 */     return new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath)), Charset.forName(CHARSET_NAME)));
/*     */   }
/*     */   
/*     */   public static BufferedWriter buildFileWriter(String filePath, boolean append) throws IOException {
/* 121 */     createFile(filePath, !append);
/* 122 */     return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(filePath), append), Charset.forName(CHARSET_NAME)));
/*     */   }
/*     */   
/*     */   public static void writeToFile(String content, String filePath) throws IOException {
/* 126 */     Writer writer = null;
/*     */     try {
/* 128 */       writer = buildFileWriter(filePath, false);
/* 129 */       writer.write(content);
/*     */     } finally {
/*     */       
/* 132 */       if (writer != null)
/* 133 */         writer.close(); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\d\\uni_potsdam\hp\\utils\FileUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */