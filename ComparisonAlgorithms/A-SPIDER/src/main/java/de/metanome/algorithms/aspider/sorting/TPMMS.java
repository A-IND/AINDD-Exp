//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package de.metanome.algorithms.aspider.sorting;

import de.metanome.algorithm_integration.AlgorithmExecutionException;
import de.metanome.algorithm_integration.input.RelationalInput;
import de.metanome.algorithm_integration.input.RelationalInputGenerator;
import de.metanome.algorithms.aspider.structures.ColumnSize;
import de.uni_potsdam.hpi.utils.FileUtils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

public class TPMMS {
    public TPMMS() {
    }

    public static void sortToDisk(RelationalInputGenerator inputGenerator, int inputRowLimit, String filePath, int relativeAttributeIndex, long maxMemoryUsage, int memoryCheckFrequency, ColumnSize colSize) throws AlgorithmExecutionException {
        RelationalInput relationalInput = null;
        int numValuesSinceLastMemoryCheck = 0;
        List<String> spilledFiles = new ArrayList();

        try {
            relationalInput = inputGenerator.generateNewCopy();
            Set<String> values = new TreeSet();

            String spillFilePath;
            while(relationalInput.hasNext() && inputRowLimit != 0) {
                spillFilePath = (String)relationalInput.next().get(relativeAttributeIndex);
                --inputRowLimit;
                if (spillFilePath != null) {
                    spillFilePath = spillFilePath.replaceAll("\n", "\u0000");
                    values.add(spillFilePath);
                    ++numValuesSinceLastMemoryCheck;
                    if (numValuesSinceLastMemoryCheck >= memoryCheckFrequency) {
                        numValuesSinceLastMemoryCheck = 0;
                        if (ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed() > maxMemoryUsage) {
                            //String spillFilePath = filePath + "#" + spilledFiles.size();
                            spillFilePath = filePath + "#" + spilledFiles.size();
                            write(spillFilePath, values);
                            spilledFiles.add(spillFilePath);
                            values = new TreeSet();
                            System.gc();
                        }
                    }
                }
            }

            if (spilledFiles.isEmpty()) {
                write(filePath, values);
                colSize.setColsize(values.size());
            } else {
                if (!values.isEmpty()) {
                    spillFilePath = filePath + "#" + spilledFiles.size();
                    write(spillFilePath, values);
                    spilledFiles.add(spillFilePath);
                    new TreeSet();
                    System.gc();
                }

                merge(filePath, spilledFiles, colSize);
            }
        } finally {
            try {
                relationalInput.close();
            } catch (Exception var18) {
            }

        }

    }

    private static void write(String filePath, Set<String> values) throws AlgorithmExecutionException {
        BufferedWriter writer = null;

        try {
            writer = FileUtils.buildFileWriter(filePath, false);
            Iterator<String> valueIterator = values.iterator();

            while(valueIterator.hasNext()) {
                writer.write((String)valueIterator.next());
                writer.newLine();
            }

            writer.flush();
        } catch (IOException var7) {
            var7.printStackTrace();
            throw new AlgorithmExecutionException(var7.getMessage());
        } finally {
            FileUtils.close(writer);
        }
    }

    private static void merge(String filePath, List<String> spilledFiles, ColumnSize colSize) throws AlgorithmExecutionException {
        BufferedReader[] readers = new BufferedReader[spilledFiles.size()];
        PriorityQueue<TPMMSTuple> values = new PriorityQueue(spilledFiles.size());
        BufferedWriter writer = null;
        boolean var17 = false;
        int count = 0;
        try {
            var17 = true;
            int readerNumber = 0;

            while(true) {
                if (readerNumber >= spilledFiles.size()) {
                    writer = FileUtils.buildFileWriter(filePath, false);
                    String previousValue = null;

                    while(!values.isEmpty()) {
                        TPMMSTuple tuple = (TPMMSTuple)values.remove();
                        if (previousValue == null || !previousValue.equals(tuple.value)) {
                            writer.write(tuple.value);
                            count++;
                            writer.newLine();
                        }

                        previousValue = tuple.value;
                        tuple.value = readers[tuple.readerNumber].readLine();
                        if (tuple.value != null) {
                            values.add(tuple);
                        }
                    }

                    writer.flush();
                    colSize.setColsize(count);
                    var17 = false;
                    break;
                }

                BufferedReader reader = FileUtils.buildFileReader((String)spilledFiles.get(readerNumber));
                readers[readerNumber] = reader;
                String value = reader.readLine();
                if (value != null) {
                    values.add(new TPMMSTuple(value, readerNumber));
                }

                ++readerNumber;
            }


        } catch (FileNotFoundException var18) {
            var18.printStackTrace();
            throw new AlgorithmExecutionException(var18.getMessage());
        } catch (IOException var19) {
            var19.printStackTrace();
            throw new AlgorithmExecutionException(var19.getMessage());
        } finally {
            if (var17) {
                FileUtils.close(writer);

                BufferedReader[] var10 = readers;
                int var11 = readers.length;

                for(int var12 = 0; var12 < var11; ++var12) {
                    BufferedReader reader = var10[var12];
                    FileUtils.close(reader);
                }

            }
        }

        FileUtils.close(writer);
        BufferedReader[] var22 = readers;
        int var24 = readers.length;

        for(int var25 = 0; var25 < var24; ++var25) {
            BufferedReader reader = var22[var25];
            FileUtils.close(reader);
        }

    }
}
